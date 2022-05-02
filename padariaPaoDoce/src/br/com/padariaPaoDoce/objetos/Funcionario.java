package br.com.padariaPaoDoce.objetos;

import java.io.Serializable;

public class Funcionario extends MaeDevolve implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id; // id do usuário
	private String idcargos;
	private String nome;
	private String cpf;
	private String telefone;
	private String data_nasc;
	private String email;
	private String idUsuario;
	private String usuario;
	private String senha;
	private String nome_cargo;
	private String permissao;
	private String funcionarios_cpf;
	private String status;
	private int max = 10;
	public int getMax() {
		return max;
	}
	public int getId() {
		return id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome_cargo() {
		return nome_cargo;
	}
	public void setNome_cargo(String nome_cargo) {
		this.nome_cargo = nome_cargo;
	}
	public String getFuncionarios_cpf() {
		return funcionarios_cpf;
	}
	public void setFuncionarios_cpf(String funcionarios_cpf) {
		this.funcionarios_cpf = funcionarios_cpf;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public String getPermissao() {
		return permissao;
	}
	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getIdcargos() {
		return idcargos;
	}
	public void setIdcargos(String idcargos) {
		this.idcargos = idcargos;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getData_nasc() {
		return data_nasc;
	}
	public void setData_nasc(String data_nasc) {
		this.data_nasc = data_nasc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	
	
}
