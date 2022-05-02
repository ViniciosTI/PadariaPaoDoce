package br.com.padariaPaoDoce.formatar;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
public class CMD5 extends Operacao{
	public String organizar(){
		return this.data;
	}
	
	public String desorganizar(){
		StringBuilder build = new StringBuilder();;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String salt = "#adskfja234234daewr";
		byte[] crip = null;
		try {
			byte[] saltByte = salt.getBytes("UTF-8");
			md.update(saltByte);
			md.update(this.data.getBytes("UTF-8"));
			crip = md.digest();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 
		for(byte b : crip){
			build.append(String.format("%02X", 0xFF & b));
		}
		this.data = build.toString();
		return this.data;
	}
}


