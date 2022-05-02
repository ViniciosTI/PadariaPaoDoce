package br.com.padariaPaoDoce.crud;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.padariaPaoDoce.formatar.Formatar;
import br.com.padariaPaoDoce.formatar.Oper;
import br.com.padariaPaoDoce.objetos.MaeDevolve;

public class Selecionar extends ConnectionDAO{
	private String query = "select ";
	private String formatado = "";
	public List<MaeDevolve> selecionar(String condicao,String classe, String[] c, Object o,int pag)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, SQLException {
		List<MaeDevolve> list = new ArrayList<MaeDevolve>();
		int i = 0;
		for(i=1; i<c.length ; i++){			
			if(i != 1){
				query += ",";
			}
			query+=c[i];
		}
		query += " from "+c[0];
		if(!condicao.equals("null") && !condicao.equals("")){
			query += " WHERE "+condicao;
		}
		Object clazz =null;
		if(pag!=0){
			Object [] args = null;
			clazz = Class.forName(classe).newInstance();
			Method pegaMax = clazz.getClass().getMethod("getMax");
			int max = (int) pegaMax.invoke(clazz,args);
			int inicio = (pag*max)-max; 
			//$inicio = ($pag * $maximo) - $maximo;
			query += " LIMIT "+inicio+","+max;
		}

		Statement stmt = conexao.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()){
			clazz = Class.forName(classe).newInstance();
			for(int t=1;t<c.length;t++){
				String dado = "set"+Character.toUpperCase(c[t].charAt(0)) + c[t].substring(1);
				for(Method metodos : clazz.getClass().getMethods()){
					if(metodos.getName().equals(dado)){
						if(c[t]=="id") {
							metodos.invoke(clazz, rs.getInt(c[t]));
						}else{		

							formatado = Formatar.formatar(rs.getString(c[t]), c[t], Oper.ORGANIZAR);
							metodos.invoke(clazz, formatado);
						}
					}
				}
			}
			list.add((MaeDevolve) clazz);
		}		
		return list;
	}
	@Override
	boolean inserir(Map<String, String> valores, String[] campos) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	boolean alterar(Map<String, String> valores, String[] campos, String condicao) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	boolean deletar(String tabela, String[] condicao) {
		// TODO Auto-generated method stub
		return false;
	}
}
