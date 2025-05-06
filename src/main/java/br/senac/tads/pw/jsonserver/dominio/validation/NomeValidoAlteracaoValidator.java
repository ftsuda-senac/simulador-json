package br.senac.tads.pw.jsonserver.dominio.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senac.tads.pw.jsonserver.dominio.JsonFileDto;
import br.senac.tads.pw.jsonserver.dominio.JsonFileRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NomeValidoAlteracaoValidator implements ConstraintValidator<NomeValidoAlteracao, JsonFileDto> {

	private String defaultMessage;

	@Autowired
	private JsonFileRepository repository;

	@Override
	public void initialize(NomeValidoAlteracao constraintAnnotation) {
		this.defaultMessage = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(JsonFileDto dados, ConstraintValidatorContext context) {

		if (!repository.existsByIdNotAndNome(dados.getId(), dados.getNome())) {
			return true;
		}
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(defaultMessage)
				.addPropertyNode("nome")
				.addConstraintViolation();
		return false;
	}

	@ModelAttribute("serverDomain")
	public String getServerDomain() {
		return ServletUriComponentsBuilder.fromCurrentContextPath()
				.build()
				.toUriString();
	}
}
