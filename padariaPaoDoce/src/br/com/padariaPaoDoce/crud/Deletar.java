package br.com.padariaPaoDoce.crud;
import java.sql.SQLException;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import br.com.padariaPaoDoce.objetos.MaeDevolve;
public class Deletar extends ConnectionDAO {
	boolean deletar(String tabela, String[] condicao){
		String query = "DELETE FROM "+tabela+" WHERE "+condicao[0]+"=?";
		PreparedStatement p;
		try{
			p = this.conexao.prepareStatement(query);
			p.setString(1, condicao[1]);
			p.execute();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}	
		return true;
	}

	boolean inserir(Map<String, String> valores, String[] campos){return false;}
	boolean alterar(Map<String, String>valores, String[]campos, String condicao){return false;}

	@Override
	List<MaeDevolve> selecionar(String condicao, String classe, String[] c,Object o,int pag)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}



	


}
