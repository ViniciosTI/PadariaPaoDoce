package br.com.padariaPaoDoce.bd.conexao;
	import java.sql.Connection;
public class Conexao {
	/*
	 * Declara��o da vari�vel conexao, do tipo Connection, lembra-se. que receber� as informa��es
	 * para conex�o aplicativo x banco de dados via driver JDBC.
	 */
	private Connection conexao;
	// M�todo que abre a conex�o com o banco de dados
	public Connection abrirConexao(){
		try{
			//instru��o que identifica o tipo de driver utilizado para a conex�o como banco de dados.
			Class.forName("org.gjt.mm.mysql.Driver");
			//Notem o endere�amento feito do servidor de banco de driver.
			conexao = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/banco_pd", "root", "root");
		}catch(Exception e){
			e.printStackTrace();
		}
		return conexao;
	}
	
	public void fecharConexao(){
		try{
			conexao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
