package br.com.padariaPaoDoce.formatar;

public class Telefone extends Operacao{
 
	public String desorganizar() {
		if (this.data.length()==14){
			this.data = this.data.substring(1, 3) + this.data.substring(5, 9) + this.data.substring(10, 14);
		}else if(this.data.length()==15){			
			this.data = this.data.substring(1, 3) + this.data.substring(5, 9) + this.data.substring(10, 15);
		}
		return this.data;
	}

	public String organizar() {
		
		if (this.data.length()==10){			
			this.data = "(" + this.data.substring(0, 2) + ") " + this.data.substring(2, 6) + "-" + this.data.substring(6, 10);
		}else if(this.data.length()==11){
			this.data = "(" + this.data.substring(0, 2) + ") " + this.data.substring(2, 6) + "-" + this.data.substring(6, 11);
		}
		return this.data;
	}

	
}
