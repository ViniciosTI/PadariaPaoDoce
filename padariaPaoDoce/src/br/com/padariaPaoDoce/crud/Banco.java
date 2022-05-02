package br.com.padariaPaoDoce.crud;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.padariaPaoDoce.bd.conexao.Conexao;
import br.com.padariaPaoDoce.objetos.MaeDevolve;
public class Banco {

	private static boolean flag;
	private static Connection conexao;
	private static ConnectionDAO operacao = null;
	private static Conexao conec;
	private static void connect() {
		conec = new Conexao();
		conexao = conec.abrirConexao();
	}
	public static boolean inserir(Map<String, String> valores, String[] campos){
		connect();
		Inserir jdbc = new Inserir();
		operacao = jdbc;
		operacao.connection(conexao);
		flag = operacao.inserir(valores, campos);
		conec.fecharConexao();
		return flag;
	}
	public static boolean alterar(Map<String, String>valores, String[]campos, String condicao){
		connect();
		Alterar jdbc = new Alterar();
		operacao = jdbc;
		operacao.connection(conexao);
		flag = operacao.alterar(valores, campos, condicao);
		conec.fecharConexao();
		return flag;
	}
	public static boolean deletar(String tabela, String[] condicao){
		connect();
		Deletar jdbc = new Deletar();
		operacao = jdbc;
		operacao.connection(conexao);
		flag = operacao.deletar(tabela, condicao);
		conec.fecharConexao();
		return flag;
	}
	public static List<MaeDevolve> selecionar(String condicao, String[] c,String clazz,int pag) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, SQLException{
		connect();
		Selecionar jdbc = new Selecionar();
		operacao = jdbc;
		operacao.connection(conexao);
		List<MaeDevolve> list = new ArrayList<MaeDevolve>();
		Object o = clazz.getClass().newInstance();
		list = operacao.selecionar(condicao, clazz, c, o,pag);
		conec.fecharConexao();
		return list;
	}
}
