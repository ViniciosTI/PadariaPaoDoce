package br.com.padariaPaoDoce.formatar;


public abstract class Operacao {
	public String data="";
	public void frm (String data){
		this.data = data; 
	}
	abstract public String desorganizar();
	abstract public String organizar();

}
