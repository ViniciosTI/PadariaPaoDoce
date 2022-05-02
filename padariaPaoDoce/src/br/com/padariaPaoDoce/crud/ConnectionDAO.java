package br.com.padariaPaoDoce.crud;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import br.com.padariaPaoDoce.objetos.MaeDevolve;
public abstract class ConnectionDAO {
	protected Connection conexao;
	public void connection (Connection conexao){
		this.conexao = conexao; 
	}



	abstract boolean inserir(Map<String, String> valores, String[] campos);
	abstract boolean alterar(Map<String, String>valores, String[]campos, String condicao);
	abstract boolean deletar(String tabela, String[] condicao);
	abstract List<MaeDevolve> selecionar(String condicao,String classe, String[] c, Object o,int pag) throws  InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, SQLException ;



	
}
