package com.fatec.SIG2.ports;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fatec.SIG2.model.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository <Cliente, Long>{
 Optional<Cliente> findByCpf(String cpf);
 List<Cliente> findAllByNomeIgnoreCaseContaining(String nome);
}
