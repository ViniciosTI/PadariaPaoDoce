package br.com.padariaPaoDoce.objetos;

import java.io.Serializable;

public class Cargo  extends MaeDevolve implements Serializable{
	private static final long serialVersionUID = 1L;
private int id;
private String nome;
private String descricao;
private String salario;
private String permissao;
private String status;
private int max = 10;
public int getMax() {
	return max;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}
public String getSalario() {
	return salario;
}
public void setSalario(String salario) {
	this.salario = salario;
}
public String getPermissao() {
	return permissao;
}
public void setPermissao(String permissao) {
	this.permissao = permissao;
}

}
