package br.com.padariaPaoDoce.formatar;
import java.util.Base64;
public class CB64 extends Operacao{
	public String organizar(){
		byte[] decodedBytes = Base64.getDecoder().decode(this.data);
		this.data = new String(decodedBytes);
		return this.data;
	}
	
	public String desorganizar(){
		this.data = Base64.getEncoder().encodeToString(this.data.getBytes());
		return this.data;
	}
}
