package br.com.padariaPaoDoce.objetos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public abstract class MaeDevolve {
	public Map<String, String> dados = new HashMap<String, String>(); // atr usado para mapear os campos
	
	public Map<String, String> devolve(String[] c)
			throws InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException{
		
		// argumentos nulos necess�rios para realizar a rotina
		Class<?>[] args = null;
		Object [] args1 = null;
		// String c tem os nomes dos campos que seram mapeados
		for(int i = 1; i<c.length ; i++){			
			String dado = Character.toUpperCase(c[i].charAt(0)) + c[i].substring(1);
			/* 
			 * Formata��o das strings dos campos 
			 * (posi��o 0 da string ser� usado o UpperCase para deixar em mai�sculo)
			 * � necess�rio pois o m�todo que ser� invocado possui a primeira
			 *  letra de cada nome do campo em mai�sculo
			*/
			Method n = this.getClass().getMethod("get"+dado, args);
			// buscando o m�todo usando o get(Padr�o) e o nome do campo formatado
			dados.put(c[i], (String) n.invoke(this, args1));
			
		}
		return dados;
	};
}
