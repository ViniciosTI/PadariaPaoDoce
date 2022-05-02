package br.com.padariaPaoDoce.formatar;

public class Cpf extends Operacao{

	public String organizar(){
		
		String bloco1 = this.data.substring(0, 3);
		String bloco2 = this.data.substring(3, 6);
		String bloco3 = this.data.substring(6, 9);
		String bloco4 = this.data.substring(9, 11);
		data = String.format("%s.%s.%s-%s", bloco1, bloco2, bloco3, bloco4);
		return this.data;
	}
	
	public String desorganizar(){
		String bloco1 = this.data.substring(0, 3);
		String bloco2 = this.data.substring(4, 7);
		String bloco3 = this.data.substring(8, 11);
		String bloco4 = this.data.substring(12, 14);
		this.data = bloco1 + bloco2 + bloco3 + bloco4;
		
		return this.data;
	}
}
