package br.senac.tads.pw.jsonserver.dominio.validation;

import jakarta.validation.groups.Default;

public class CrudValidationGroups {

	// https://stackoverflow.com/a/35359965
	public interface Create extends Default {

	}

	public interface Update extends Default {

	}

}
