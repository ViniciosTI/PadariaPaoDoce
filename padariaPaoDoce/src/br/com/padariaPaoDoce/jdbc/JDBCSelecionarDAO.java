package br.com.padariaPaoDoce.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.padariaPaoDoce.formatar.Formatar;
import br.com.padariaPaoDoce.formatar.Oper;
import br.com.padariaPaoDoce.jdbcinterface.SelecionarDAO;
import br.com.padariaPaoDoce.objetos.*;

public class JDBCSelecionarDAO implements SelecionarDAO{
	private Connection conexao;
	public JDBCSelecionarDAO(Connection conexao){
		this.conexao = conexao;
	}
	
	public List<Venda> graficoVenda(String data,String mg){
		List<Venda> classe = new ArrayList<Venda>();
		Venda venda = null;
		String query = "select v.forma_pag,count(v.id) as total from vendas as v where data_final between '"+mg+"' and '"+data+"'  group by v.forma_pag";
		try{
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				venda = new Venda();
				String forma = rs.getString("forma_pag");
				switch(forma){
				case "0":
					forma= "Dinheiro";
					break;
				case "1":
					forma = "Débito";
					break;
				case "2":
					forma = "Crédito";
					break;
				default:
					forma = "Erro";
					break;
				}
				venda.setForma_pag(forma);  
				venda.setResp(rs.getString("Total"));  
				classe.add(venda);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return classe;
	}
	public String permissaoUsuario(String string){
		String query = "select permissao from funcionarios as f inner join cargos as c on c.id=f.idcargos where f.cpf='"+string+"'";
		String i = "";
		try{
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				i = rs.getString("permissao");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return i;
	}
	public String pegaTotalPrecoVenda(String string){
		String query = "select sum(resp) as i from consulta_venda ";
		if(string!=""){
			query+="where "+string;
		}
		String i = "";
		try{
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				i = Formatar.formatar(rs.getString("i"), "preco", Oper.ORGANIZAR);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return i;
	}
	public String pegaNumeroRegistros(String tipo,String condicao){
		String query = numReg(tipo);
		if(condicao!=""){
			query += " where  "+condicao;
		}
		String i = "";
		try{
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				i = rs.getString("i");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return i;
	}
	
	public String numReg(String tipo){
		switch(tipo){
		case "p":
			tipo = "select count(*) as i from produtos";
			break;
		case "c":
			tipo = "select count(*) as i from cargos";
			break;
		case "f":
			tipo = "select count(*) as i from funcionarios";
			break;
		case "ft":
			tipo = "select count(*) as i from faturamento";
			break;
		case "cv":
			tipo = "select count(*) as i from consulta_venda";
			break;
		case "total":
			tipo = "select sum(quantidade) as i from Faturamento";
			break;
		}
		return tipo;
	}
}

