package com.fatec.SIG2.services;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.SIG2.model.Produto;
import com.fatec.SIG2.ports.MantemProduto;
import com.fatec.SIG2.ports.ProdutoRepository;

@Service
public class MantemProdutoI implements MantemProduto {
	Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	ProdutoRepository repository;

	@Override
	public List<Produto> consultaTodos() {
		logger.info(">>>>>> servico consultaTodos chamado");
		return repository.findAll();
	}

	@Override
	public Optional<Produto> consultaPorId(Long idProduto) {
		logger.info(">>>>>> servico consultaPorId chamado");
		return repository.findById(idProduto);
	}
	
	

	@Override
	public Optional<Produto> save(Produto produto) {
		logger.info(">>>>>> servico save chamado ");
		return Optional.ofNullable(repository.save(produto));
	}

	@Override
	public void delete(Long idProduto) {
		logger.info(">>>>>> servico delete por id chamado");
		repository.deleteById(idProduto);
	}

	@Override
	public Optional<Produto> altera(Produto produto) {
		logger.info(">>>>>> 1.servico altera produto chamado");
		Optional<Produto> umProduto = consultaPorId(produto.getId());

		if (umProduto.isPresent()) {
			Produto produtoModificado = new Produto(produto.getNome(), produto.getPreco(),produto.getDescricao(),produto.getFabricante(),produto.getFornecedor(),produto.getCnpjFornecedor());
			produtoModificado.setId(produto.getId());
			produtoModificado.setQtdEstoque(produto.getQtdEstoque());
			produtoModificado.obtemDataAtual(new DateTime());
			produtoModificado.setNome(produto.getNome());
			produtoModificado.setPreco(produto.getPreco());
			produtoModificado.setDescricao(produto.getDescricao());
			produtoModificado.setFabricante(produto.getFabricante());
			produtoModificado.setFornecedor(produto.getFornecedor());
			produtoModificado.setCnpjFornecedor(produto.getCnpjFornecedor());
			return Optional.ofNullable(repository.save(produtoModificado));
		} else {
			return Optional.empty();
		}
	}


}