package br.com.padariaPaoDoce.objetos;

import java.io.Serializable;

public class Produto extends MaeDevolve implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String unid_medida;
	private String preco; 
	private String prec; 
	private String status;
	private String quantidade;
	private String idprodutos;
	private String idvendas;
	private int max = 10;
	public int getMax() {
		return max;
	}
	public String getPrec() {
		return prec;
	}
	public void setPrec(String prec) {
		this.prec = prec;
	}
	public String getIdvendas() {
		return idvendas;
	}
	public void setIdvendas(String idvendas) {
		this.idvendas = idvendas;
	}
	public String getIdprodutos() {
		return idprodutos;
	}
	public void setIdprodutos(String idprodutos) {
		this.idprodutos = idprodutos;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUnid_medida() {
		return unid_medida;
	}
	public void setUnid_medida(String unid_medida) {
		this.unid_medida = unid_medida;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	
	
	
}
