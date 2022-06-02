package com.fatec.SIG2.ports;

import java.util.List;
import java.util.Optional;
import com.fatec.SIG2.model.Cliente;

public interface MantemCliente {
	List<Cliente> consultaTodos();

	Optional<Cliente> consultaPorCpf(String cpf);

	Optional<Cliente> consultaPorId(Long id);

	Optional<Cliente> save(Cliente cliente);

	void delete(Long id);

	Optional<Cliente> altera(Cliente cliente);
}
