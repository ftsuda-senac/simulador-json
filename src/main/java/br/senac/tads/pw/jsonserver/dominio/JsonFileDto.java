package br.senac.tads.pw.jsonserver.dominio;

import br.senac.tads.pw.jsonserver.dominio.validation.CrudValidationGroups.*;
import br.senac.tads.pw.jsonserver.dominio.validation.JsonValido;
import br.senac.tads.pw.jsonserver.dominio.validation.NomeValidoAlteracao;
import br.senac.tads.pw.jsonserver.dominio.validation.NomeValidoInclusao;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Usando class ao invés do record para compatibiilidade com Thymeleaf
@Getter
@Setter
@NoArgsConstructor
@NomeValidoInclusao(groups = Create.class)
@NomeValidoAlteracao(groups = Update.class)
public class JsonFileDto {

	private Integer id;

	@NotBlank
	private String nome;

	@NotBlank
	@JsonValido
	private String conteudo;

	public JsonFileDto(String nome, String conteudo) {
		this.nome = nome;
		this.conteudo = conteudo;
	}

	public JsonFileDto(JsonFileEntity entity) {
		this(entity.getNome(), entity.getConteudo());
		this.id = entity.getId();
	}

	public JsonFileEntity toEntity() {
		return new JsonFileEntity(nome, conteudo);
	}

	public JsonFileEntity updateEntity(JsonFileEntity entity) {
		entity.setNome(nome);
		entity.setConteudo(conteudo);
		return entity;
	}
}
