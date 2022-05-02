package br.com.padariaPaoDoce.formatar;

public class Preco extends Operacao{

	public String organizar(){
		
		data = "R$ "+data.replace(".", ",");
		
		return this.data;
	}
	
	public String desorganizar(){
		String bloco1 = this.data.substring(3);
		this.data = bloco1;

		return this.data.replace(",", ".");
	}
}
