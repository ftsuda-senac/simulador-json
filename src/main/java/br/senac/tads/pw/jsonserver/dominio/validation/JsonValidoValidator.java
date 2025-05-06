package br.senac.tads.pw.jsonserver.dominio.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class JsonValidoValidator implements ConstraintValidator<JsonValido, String> {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public boolean isValid(String conteudo, ConstraintValidatorContext context) {
		try {
			objectMapper.readTree(conteudo);
		} catch (Exception ex) {
			// context.disableDefaultConstraintViolation();
			// context.buildConstraintViolationWithTemplate("Formato do JSON é inválido.").addConstraintViolation();
			return false;
		}
		return true;
	}

}
