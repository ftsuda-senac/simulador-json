package br.senac.tads.pw.jsonserver.dominio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonFileRepository extends JpaRepository<JsonFileEntity, Integer> {

	Optional<JsonFileEntity> findByNome(String nome);

	boolean existsByNome(String nome);

	boolean existsByIdNotAndNome(Integer id, String nome);

}
