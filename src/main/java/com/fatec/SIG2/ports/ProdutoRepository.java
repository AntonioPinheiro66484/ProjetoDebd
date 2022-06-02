package com.fatec.SIG2.ports;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fatec.SIG2.model.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, Long> {
	Optional<Produto> findById(String id);

	List<Produto> findAllByNomeIgnoreCaseContaining(String nome);

}