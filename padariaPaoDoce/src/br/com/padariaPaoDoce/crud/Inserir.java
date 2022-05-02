package br.com.padariaPaoDoce.crud;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import br.com.padariaPaoDoce.formatar.Formatar;
import br.com.padariaPaoDoce.formatar.Oper;
import br.com.padariaPaoDoce.objetos.MaeDevolve;
public class Inserir extends ConnectionDAO {
	
	boolean inserir(Map<String, String> valores, String[]campos){
		String query   = "insert into " + campos[0] + " (";
		String cmdVal  = "(";
	
		
		for(int i = 1; i<campos.length;i++){
				if(i != 1){
					query += ",";
					cmdVal  += ",";		
				}
				query += campos[i];
				cmdVal  += "?";	
		}
		
		// Concatena a porra toda
		query += ") values ";
		cmdVal  += ")";
		
		/*
		 No final, se obtem como resultado 
		 Ex: "insert into funcionario (nome, endereco, telefone) values(?,?,?)"
		*/
		query += cmdVal;
		PreparedStatement p;
		try{
			p = this.conexao.prepareStatement(query);
			for(int i = 1; i<campos.length; i++){
				p.setString(i, Formatar.formatar(valores.get(campos[i]),campos[i],Oper.DESORGANIZAR));
			}
			p.execute();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}	
		return true;
	}
	
	boolean alterar(Map<String, String>valores, String[]campos, String condicao){return false;}
	boolean deletar(String tabela, String[] condicao){return false;}

	@Override
	List<MaeDevolve> selecionar(String condicao, String classe, String[] c,Object o,int pag)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
