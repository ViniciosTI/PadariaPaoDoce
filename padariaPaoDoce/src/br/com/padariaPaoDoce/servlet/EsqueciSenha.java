package br.com.padariaPaoDoce.servlet;
// servlets
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;









import com.google.gson.Gson;

import br.com.padariaPaoDoce.crud.Banco;
import br.com.padariaPaoDoce.objetos.Funcionario;
import br.com.padariaPaoDoce.objetos.MaeDevolve;






// conexão com o banco
import java.sql.SQLException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// bibliotecas 
public class EsqueciSenha extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/*
	 * @see HttpServlet#HttpServlet()
	 */
	public EsqueciSenha(){
		super();
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, SQLException{
		Map<String, String> msg = new HashMap<String, String>();
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String usuario = request.getParameter("usuario");
			String email = request.getParameter("email");

			String[] campos = {"view_FuncUser", "id","email","usuario"};
			String condicao =" status='1' and usuario='"+usuario+"' and email='"+email+"'";
			classe = Banco.selecionar(condicao, campos, Funcionario.class.getName(),0);
			msg.put("msg","");

			if(classe!=null){
				for(MaeDevolve m :classe){
					String resUsuario = ((Funcionario)m).getUsuario();
					String resEmail = ((Funcionario)m).getEmail();
					Integer id = ((Funcionario)m).getId();
					if(usuario.equals(resUsuario)&&email.equals(resEmail)){
						msg.put("msg", "b");
						msg.put("id", id.toString());
					}
				}
			}
		if(msg.get("msg")==""){
			String senhaNova = request.getParameter("senha");
			String id = request.getParameter("id");
			
			String[] campos2 = {"usuarios" , "senha"}; 
			Map<String, String> valores = new HashMap<String, String>();
			valores.put("senha", senhaNova);
			String condicao2 = "id='"+id+"'";
			Boolean retorno = Banco.alterar(valores, campos2, condicao2);
			if(retorno){
				msg.put("msg", "Senha nova editada com sucesso!") ;
			}else{
				msg.put("msg","Erro ao editar a senha!");
			}
		}
		String json = new Gson().toJson(msg);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
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
