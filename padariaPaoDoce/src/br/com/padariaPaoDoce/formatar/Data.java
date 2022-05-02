package br.com.padariaPaoDoce.formatar;

public class Data extends Operacao{
	private String dia;
	private String mes;
	private String ano;
	public String organizar(){
		ano = this.data.substring(0, 4);// ano
		mes = this.data.substring(5, 7);// mes
		dia = this.data.substring(8, 10);// dia
		data = String.format("%s-%s-%s", dia, mes, ano);
	
		return this.data;
	}
	
	public String desorganizar(){
		dia = this.data.substring(0, 2);// dia
		mes = this.data.substring(3, 5);// mes
		ano = this.data.substring(6, 10);// ano
		data = String.format("%s-%s-%s", ano, mes, dia);
		
		return this.data;
	}
}
