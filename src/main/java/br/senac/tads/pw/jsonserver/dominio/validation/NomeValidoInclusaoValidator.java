package br.senac.tads.pw.jsonserver.dominio.validation;

import org.springframework.beans.factory.annotation.Autowired;

import br.senac.tads.pw.jsonserver.dominio.JsonFileDto;
import br.senac.tads.pw.jsonserver.dominio.JsonFileRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NomeValidoInclusaoValidator implements ConstraintValidator<NomeValidoInclusao, JsonFileDto> {

	private String defaultMessage;

	@Autowired
	private JsonFileRepository repository;

	@Override
	public void initialize(NomeValidoInclusao constraintAnnotation) {
		this.defaultMessage = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(JsonFileDto dados, ConstraintValidatorContext context) {
		if (!repository.existsByNome(dados.getNome())) {
			return true;
		}
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(defaultMessage)
				.addPropertyNode("nome")
				.addConstraintViolation();
		return false;
	}

}
