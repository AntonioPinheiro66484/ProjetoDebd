package com.fatec.SIG2.model;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Random;

import javax.persistence.Column;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@Document(collection="Cliente")
public class Cliente {
	@Id
	private Long id;
	@Field
	@NotBlank(message = "Nome é requerido")
	private String nome;
	@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\/-](0?[1-9]|1[012])[\\/-]\\d{4}$", message = "A data de vencimento deve estar no formato dd/MM/YYYY")
	// https://www.regular-expressions.info/
	@Field
	private String dataNascimento;
	@Field
	private String dataCadastro;
	@Field
	private String sexo;
	@CPF
	@Column(unique = true) // nao funciona com @Valid tem que tratar na camada de persistencia
	@Field
	private String cpf;
	@NotBlank(message = "O CEP é obritatório.")
	@Field
	private String cep;
	@Field
	private String endereco;
	@NotBlank(message = "O complemento deve ser informado")
	
	private String complemento;

	public Cliente(String nome, String dataNascimento, String sexo, String cpf, String cep, String complemento) {
        Random rand = new Random();
        this.id = rand.nextLong();
        this.nome = nome;
        setDataNascimento(dataNascimento);
        this.sexo = sexo;
        this.cpf = cpf;
        this.cep = cep;
        this.complemento = complemento;

    }
	
    public Cliente() {
        Random rand = new Random();
        this.id = rand.nextLong();

    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataAtual) {
		this.dataCadastro = dataAtual;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		if (validaData(dataNascimento) == true) {
			this.dataNascimento = dataNascimento;
		} else {
			throw new IllegalArgumentException("Data invalida");
		}
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public boolean validaData(String data) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false); //
		try {
			df.parse(data); // data válida (exemplo 30 fev - 31 nov)
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	public void obtemDataAtual(DateTime dataAtual) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
		this.dataCadastro = dataAtual.toString(fmt);
	}

	// equals e tostring omitidos

}
