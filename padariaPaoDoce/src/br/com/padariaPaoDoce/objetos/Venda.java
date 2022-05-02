package br.com.padariaPaoDoce.objetos;

import java.io.Serializable;
import java.util.List;

public class Venda extends MaeDevolve implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String idusuario;
	private String data_emissao;
	private String data_final;
	private String idvendas;
	private List<Produto> produtos;
	private String quantidade;
	private String forma_pag;
	private String status;
	private String resp;
	private int max = 10;
	public int getMax() {
		return max;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getResp() {
		return resp;
	}
	public void setResp(String resp) {
		this.resp = resp;
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
	public String getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}
	public String getData_emissao() {
		return data_emissao;
	}
	public void setData_emissao(String data_emissao) {
		this.data_emissao = data_emissao;
	}
	public String getData_final() {
		return data_final;
	}
	public void setData_final(String data_final) {
		this.data_final = data_final;
	}
	public String getIdvendas() {
		return idvendas;
	}
	public void setIdvendas(String idvendas) {
		this.idvendas = idvendas;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getForma_pag() {
		return forma_pag;
	}
	public void setForma_pag(String forma_pag) {
		this.forma_pag = forma_pag;
	}

	
	
}
