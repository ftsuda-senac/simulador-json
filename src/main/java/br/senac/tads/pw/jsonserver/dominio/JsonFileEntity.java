package br.senac.tads.pw.jsonserver.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JsonFileEntity {

	@Id
	@SequenceGenerator(name = "seq_json_file_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_json_file_id")
	private Integer id;

	@Column(unique = true, nullable = false, columnDefinition = "text")
	private String nome;

	@Column(nullable = false, columnDefinition = "text")
	private String conteudo;

	public JsonFileEntity(String nome, String conteudo) {
		this.nome = nome;
		this.conteudo = conteudo;
	}

}
