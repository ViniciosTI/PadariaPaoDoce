package br.com.padariaPaoDoce.servlet;
// servlets
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import java.sql.Connection;
// conexão com o banco
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.security.NoSuchAlgorithmException;

import br.com.padariaPaoDoce.bd.conexao.Conexao;
import br.com.padariaPaoDoce.crud.Banco;
import br.com.padariaPaoDoce.formatar.Formatar;
import br.com.padariaPaoDoce.formatar.Oper;
import br.com.padariaPaoDoce.jdbc.JDBCSelecionarDAO;
import br.com.padariaPaoDoce.objetos.Funcionario;
import br.com.padariaPaoDoce.objetos.MaeDevolve;
// bibliotecas 
public class Login extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/*
	 * @see HttpServlet#HttpServlet()
	 */
	public Login(){
		super();
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, SQLException{
		List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
		String senha = Formatar.formatar(request.getParameter("crip"), "cripBase64", Oper.ORGANIZAR);
		if(senha.length()>1){
			senha = Formatar.formatar(senha, "cripMD5", Oper.DESORGANIZAR);
		}
		// Chamar o metodo que grava o objeto usuario no banco de dados
		String[] campos = {"usuarios", "id","funcionarios_cpf","senha","usuario"};
		String condicao =" usuario='"+request.getParameter("usuario")+"' and senha='"+senha+"'";
		classe = Banco.selecionar(condicao, campos, Funcionario.class.getName(),0);
		try {
			HttpSession sessao = request.getSession();
			String senhaAntiga="";
			if (classe.size() != 0){

				for(MaeDevolve f:classe){
					senhaAntiga = ((Funcionario)f).getSenha();
					if(request.getParameter("usuario").equals(((Funcionario)f).getUsuario()) && senha.equals(senhaAntiga)){	
						Conexao conec = new Conexao();
						Connection conexao = conec.abrirConexao();
						JDBCSelecionarDAO banco = new JDBCSelecionarDAO(conexao);
						String i = banco.permissaoUsuario(Formatar.formatar(((Funcionario)f).getFuncionarios_cpf(), "funcionarios_cpf", Oper.DESORGANIZAR));
						conec.fecharConexao();
						if(i.equals("0")){
							sessao.invalidate();
							response.sendRedirect("index.html");
						}else if(i.equals("2")){
							sessao.setAttribute("login", request.getParameter("usuario"));
							sessao.setAttribute("id", ((Funcionario)f).getId());
							sessao.setAttribute("permissao", i);
							response.sendRedirect("resource/home/home.html");
						}else if(i.equals("1")){
							sessao.setAttribute("login", request.getParameter("usuario"));
							sessao.setAttribute("id", ((Funcionario)f).getId());
							sessao.setAttribute("permissao", i);
							response.sendRedirect("resource/venda/venda.html");
						}else{
							sessao.invalidate();
							response.sendRedirect("index.html");
						}
					}else{
						sessao.invalidate();
						response.sendRedirect("index.html");
					}

				}
			}else{
				sessao.invalidate();
				response.sendRedirect("index.html");
			}

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	/*
	 * @see HttpServlet#doGet(HttpServletRequest, HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		try {
			process(request, response);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			process(request, response);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
