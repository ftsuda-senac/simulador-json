package br.senac.tads.pw.jsonserver.webmvc;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senac.tads.pw.jsonserver.dominio.JsonFileDto;
import br.senac.tads.pw.jsonserver.dominio.JsonFileEntity;
import br.senac.tads.pw.jsonserver.dominio.JsonFileRepository;
import br.senac.tads.pw.jsonserver.dominio.validation.CrudValidationGroups;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/gerenciamento")
@RequiredArgsConstructor
public class ManagementController {

	private final JsonFileRepository jsonFileRepository;

	@GetMapping
	@Transactional(readOnly = true)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("lista");
		mv.addObject("itens", jsonFileRepository.findAll().stream().map(JsonFileDto::new).toList());
		return mv;
	}

	@GetMapping({ "/incluir", "/alterar/{id}" })
	@Transactional(readOnly = true)
	public ModelAndView mostrarFormulario(@PathVariable(required = false) Integer id) {
		if (id != null) {
			JsonFileEntity entity = jsonFileRepository.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("ID inválido"));
			return new ModelAndView("form").addObject("item", new JsonFileDto(entity));
		}
		return new ModelAndView("form").addObject("item", new JsonFileDto());
	}

	@PostMapping("/incluir")
	@Transactional
	public ModelAndView incluirDados(
			@ModelAttribute("item") @Validated(CrudValidationGroups.Create.class) JsonFileDto input,
			BindingResult bindingResult, RedirectAttributes redirAttr) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("form").addObject("msgErro", "Erro ao incluir JSON");
		}
		jsonFileRepository.save(input.toEntity());
		redirAttr.addFlashAttribute("msg", "JSON incluído com sucesso!");
		return new ModelAndView("redirect:/gerenciamento");
	}

	@PostMapping("/alterar/{id}")
	@Transactional
	public ModelAndView alterarDados(@PathVariable Integer id,
			@ModelAttribute("item") @Validated(CrudValidationGroups.Update.class) JsonFileDto input,
			BindingResult bindingResult, RedirectAttributes redirAttr) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("form").addObject("msgErro", "Erro ao incluir JSON");
		}
		JsonFileEntity entity = jsonFileRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido"));
		jsonFileRepository.save(input.updateEntity(entity));
		redirAttr.addFlashAttribute("msg", "JSON alterado com sucesso!");
		return new ModelAndView("redirect:/gerenciamento");
	}

	@PostMapping("/excluir/{id}")
	@Transactional
	public ModelAndView excluirDados(@PathVariable Integer id, RedirectAttributes redirAttr) {
		jsonFileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido"));
		jsonFileRepository.deleteById(id);
		redirAttr.addFlashAttribute("msg", "JSON excluido com sucesso!");
		return new ModelAndView("redirect:/gerenciamento");
	}

	@ModelAttribute("serverDomain")
	public String getServerDomain() {
		return ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
	}

}
