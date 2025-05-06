package br.senac.tads.pw.jsonserver.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.tads.pw.jsonserver.dominio.JsonFileRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class JsonFileController {

	private final JsonFileRepository jsonFileRepository;

	@GetMapping(path = "/{nome}", produces = "application/json")
	public ResponseEntity<String> getFileContent(@PathVariable String nome) {
		return jsonFileRepository.findByNome(nome)
				.map(entity -> ResponseEntity.ok(entity.getConteudo()))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

}
