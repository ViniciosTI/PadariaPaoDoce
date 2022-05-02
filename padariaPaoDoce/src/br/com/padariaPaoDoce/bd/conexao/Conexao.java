package br.com.padariaPaoDoce.bd.conexao;
	import java.sql.Connection;
public class Conexao {
	/*
	 * Declaração da variável conexao, do tipo Connection, lembra-se. que receberá as informações
	 * para conexão aplicativo x banco de dados via driver JDBC.
	 */
	private Connection conexao;
	// Método que abre a conexão com o banco de dados
	public Connection abrirConexao(){
		try{
			//instrução que identifica o tipo de driver utilizado para a conexão como banco de dados.
			Class.forName("org.gjt.mm.mysql.Driver");
			//Notem o endereçamento feito do servidor de banco de driver.
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
