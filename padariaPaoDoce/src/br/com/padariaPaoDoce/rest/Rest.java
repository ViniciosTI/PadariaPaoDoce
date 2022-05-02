package br.com.padariaPaoDoce.rest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
//requisiçoes
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
//parametros enviados
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
//configuraçao
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import br.com.padariaPaoDoce.bd.conexao.Conexao;
//banco de dados
import br.com.padariaPaoDoce.crud.Banco;
import br.com.padariaPaoDoce.formatar.Formatar;
import br.com.padariaPaoDoce.formatar.Oper;
import br.com.padariaPaoDoce.jdbc.JDBCSelecionarDAO;
import br.com.padariaPaoDoce.objetos.*;
import br.com.padariaPaoDoce.validar.Validar;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
//outros
import java.util.Map;
import java.util.HashMap;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.sql.Connection;
@Path("PaoDoce")
public class Rest extends UtilRest {
	@Context
	private HttpServletRequest httpRequest;
	
	private Map<String, String> valores = new HashMap<String, String>();
	private String condicao="";
	private String msg = "";
	
	private Boolean retorno;

	@GET
	@Path("/buscarProdPorNome/{nome}/{pag}/{tipo}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response buscarProdutoPorNome(@PathParam("nome") String dado,@PathParam("pag") int pag,@PathParam("tipo") String tipo){
		try {
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String[] campos = {"produtos", "id","nome", "unid_medida", "preco"}; 
			condicao += "status=1";
			if(!dado.equals("null")&&!dado.equals("")){
				condicao += " and (id='"+dado+"' OR nome like '"+dado+"%')";
			}
			if(tipo.equals("b")){
				classe = Banco.selecionar(condicao, campos, Produto.class.getName(),pag);
			}else if(tipo.equals("c")){
				String result = numeroRegistros("p",condicao);
				return this.buildResponse(result);
			}
			return this.buildResponse(classe);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@GET
	@Path("/buscarProdfiltrado/{nome}/{unid}/{preco}/{ate}/{pag}/{tipo}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response buscarProdfiltrado(@PathParam("nome") String dado,
			@PathParam("unid") String unid,
			@PathParam("preco") String preco,
			@PathParam("ate") String ate,
			@PathParam("pag") int pag,
			@PathParam("tipo") String tipo){
		try {
			
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String[] campos = {"produtos", "id","nome", "unid_medida", "preco"}; 
			condicao += "status=1";
			if(!dado.equals("null")&&!dado.equals("")){
				condicao += " and (id='"+dado+"' OR nome like '"+dado+"%')";
			}
			if(!unid.equals("null")&&!unid.equals("")){
				condicao += " and unid_medida='"+unid+"'";
			}
			if(!preco.equals("null")&&!preco.equals("")||!ate.equals("null")&&!ate.equals("")){
				if(ate.equals("null")||ate.equals("")&&!preco.equals("null")&&!preco.equals("")){
					ate="0";
				}else{
					ate = Formatar.formatar(ate, "preco",Oper.DESORGANIZAR);
				}
				if(!ate.equals("null")&&!ate.equals("")&&preco.equals("null")||preco.equals("")){
					preco = "0";
				}else{
					preco = Formatar.formatar(preco, "preco",Oper.DESORGANIZAR);
				}
				condicao += " and preco between '"+preco+"' and '"+ate+"'";
			}
			if(tipo.equals("b")){
			classe = Banco.selecionar(condicao, campos, Produto.class.getName(),pag);
			}else if(tipo.equals("c")){
				String result = numeroRegistros("p",condicao);
				return this.buildResponse(result);
			}
			return this.buildResponse(classe);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@GET
	@Path("/buscarProdPorId/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

	public Response buscarProdutoPeloId (@PathParam("id") int dado){
		try{
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String[] campos = {"produtos", "id","nome", "unid_medida", "preco"}; 
			condicao += "status=1 and id="+dado;
			classe = Banco.selecionar(condicao, campos, Produto.class.getName(),0);
			return this.buildResponse(classe);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@POST 
	@Path("/addProduto")
	@Consumes("application/*")

	public Response adicionaProduto(String o){
		o = Formatar.formatar(o, "cripBase64", Oper.ORGANIZAR);
		try{
			Produto classe = new ObjectMapper().readValue(o, Produto.class);
			this.msg = Validar.produto(classe,"cad");
			if(this.msg==""||this.msg.equals("")){		
				String[] campos = {"produtos" ,"nome", "unid_medida", "preco","status"}; 
				this.valores = classe.devolve(campos);
				this.retorno = Banco.inserir(valores, campos);
				if(retorno){
					this.msg += "Produto cadastrado com sucesso.";
				}else{
					this.msg += "Erro ao cadastrar o Produto.";
				}
			}
			return this.buildResponse(this.msg);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@PUT
	@Path("/editProduto")
	@Consumes("application/*")
	public Response editarProduto (String o){
		o = Formatar.formatar(o, "cripBase64", Oper.ORGANIZAR);
		try{
			Produto classe = new ObjectMapper().readValue(o, Produto.class);
			this.msg = Validar.produto(classe,"upd");
			if(this.msg==""||this.msg.equals("")){					
				String[] campos = {"produtos" ,"nome", "unid_medida", "preco"}; 
				this.valores = classe.devolve(campos);
				this.condicao = "id="+classe.getId();
				this.retorno = Banco.alterar(valores, campos, condicao);
				if(this.retorno){
					this.msg += "Produto editado com sucesso!";
				}else{
					this.msg += "Erro ao editar o produto!";
				}
			}
			return this.buildResponse(this.msg);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@DELETE
	@Path("/delProduto/{id}")
	@Consumes("application/*")
	public Response deletarProduto(@PathParam("id") String id){
		try{
			String[] campos = {"produtos", "status"};
			this.valores.put("status","0");
			condicao = "id="+id;
			this.retorno = Banco.alterar(valores,campos,condicao);
			if(this.retorno){
				this.msg += "Produto deletado com sucesso.";
			}else{
				this.msg += "Erro ao deletar o produto";
			}
			return this.buildResponse(this.msg);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@GET
	@Path("/campoCargo")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response campoCargo(){
		try {
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String[] campos = {"cargos", "id","nome"};
			this.condicao +="permissao>0 and status=1";
			classe = Banco.selecionar(condicao, campos, Cargo.class.getName(),0);
			return this.buildResponse(classe);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@GET
	@Path("/buscarFuncPorNome/{nome}/{pag}/{tipo}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response buscarFuncPorNome(@PathParam("nome") String dado, @PathParam("pag") int pag,@PathParam("tipo") String tipo){
		try {
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String[] campos = {"view_FuncUser", "usuario","id","email","data_nasc","telefone","cpf","nome","nome_cargo"};
			this.condicao += "status='1'";
			if(!dado.equals("null")&&!dado.equals("")){
				this.condicao +=" and (nome like '%"+dado+"%' or id='"+dado+"' or email like '%"+dado+"%')";
			}
			if(tipo.equals("b")){
				classe = Banco.selecionar(condicao, campos, Funcionario.class.getName(),pag);
			}else if(tipo.equals("c")){
				String result = numeroRegistros("f",condicao);
				return this.buildResponse(result);
			}
			return this.buildResponse(classe);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@GET
	@Path("/buscarfuncPorId/{cpf}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response buscarFuncionariosPeloId (@PathParam("cpf") String dado){
		try{
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String[] campos = {"view_FuncUser", "usuario","id","email","data_nasc","telefone","cpf","nome","nome_cargo","idcargos"};
			condicao += "cpf="+dado;
			classe = Banco.selecionar(condicao, campos, Funcionario.class.getName(),0);
			return this.buildResponse(classe);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@POST 
	@Path("/addFuncionario")
	@Consumes("application/*")
	public Response adicionaFuncionario(String o){
		o = Formatar.formatar(o, "cripBase64", Oper.ORGANIZAR);
		try{
			Funcionario classe = new ObjectMapper().readValue(o, Funcionario.class);
			this.msg = Validar.funcionario(classe,"cad");
			if(this.msg==""||this.msg.equals("")){					
				String[] campos_f = {"funcionarios", "idcargos", "nome", "cpf", "telefone", "data_nasc", "email","status"};
				this.valores = classe.devolve(campos_f);
				this.valores.put("status","1");
				this.retorno = Banco.inserir(valores, campos_f);
				if(this.retorno){	
					Map<String, String> valores2 = new HashMap<String, String>();
					String[] campos_u = {"usuarios", "usuario","senha", "funcionarios_cpf"};
					classe.setFuncionarios_cpf(classe.getCpf());
					valores2 = classe.devolve(campos_u);
					this.retorno = Banco.inserir(valores2, campos_u);
					if(this.retorno){
						this.msg += "Funcionario cadastrado com sucesso.";
					}else{
						this.msg += "#2 - Erro ao cadastrar o Funcionario.";
					}
				}else{
					this.msg += "#1 - Erro ao cadastrar o Funcionario.";
				}
			}	
			return this.buildResponse(this.msg);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@PUT
	@Path("/editFuncionario")
	@Consumes("application/*")
	public Response editarFuncionario (String o){
		o = Formatar.formatar(o, "cripBase64", Oper.ORGANIZAR);
		try{
			Funcionario classe = new ObjectMapper().readValue(o, Funcionario.class);
			this.msg = Validar.funcionario(classe,"upd");
			if(this.msg==""||this.msg.equals("")){					
				Map<String, String> valores2 = new HashMap<String, String>();
				String[] campos_u = {"usuarios", "usuario"};
				valores2 = classe.devolve(campos_u);
				condicao = "funcionarios_cpf="+Formatar.formatar(classe.getFuncionarios_cpf(),"funcionarios_cpf", Oper.DESORGANIZAR);
				this.retorno = Banco.alterar(valores2, campos_u, condicao);
				if(retorno){	
					String[] campos_f = {"funcionarios", "idcargos", "nome", "cpf", "telefone", "data_nasc", "email"}; 
					this.valores = classe.devolve(campos_f);
					this.condicao = "cpf="+Formatar.formatar(valores.get("cpf"),"cpf", Oper.DESORGANIZAR);
					this.retorno = Banco.alterar(valores, campos_f, condicao);
					if(this.retorno){
						this.msg += "Funcionário editado com sucesso!";
					}else{
						this.msg += "#2 - Erro ao editar o Funcionário!";
					}
				}else{				
					this.msg += "#1 - Erro ao editar o Funcionário!";
				}
			}
			return this.buildResponse(this.msg);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@DELETE
	@Path("/delFuncionario/{cpf}")
	@Consumes("application/*")
	public Response deletarFuncionario(@PathParam("cpf") String i){
		try{
				String[] condicao2 = {"funcionarios_cpf",i};
				this.retorno = Banco.deletar("usuarios", condicao2);
				if(this.retorno){
					String[] campos = {"funcionarios", "status"};
					condicao = "cpf='"+i+"'";
					this.valores.put("status","0");
					this.retorno = Banco.alterar(valores, campos, condicao);
					if(retorno){
						this.msg += "Funcionario deletado com sucesso.";
					}else{
						this.msg +="2# Erro ao deletar o funcionário";
					}
				}else{
					this.msg +="1# Erro ao deletar o funcionário";
				}
			return this.buildResponse(this.msg);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@GET
	@Path("/buscarCargoPorNome/{nome}/{pag}/{tipo}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response buscarCargoPorNome(@PathParam("nome") String dado, @PathParam("pag") int pag,@PathParam("tipo") String tipo){
		try {
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String[] campos = {"cargos", "id","nome","descricao", "salario", "permissao"}; 
			this.condicao += "status=1";
			if(!dado.equals("null")&&!dado.equals("")){
				this.condicao += " and(nome like '%"+dado+"%' or id='"+dado+"')";
			}
			if(tipo.equals("b")){
				classe = Banco.selecionar(condicao, campos, Cargo.class.getName(),pag);
			}else if(tipo.equals("c")){
				String result = numeroRegistros("c",condicao);
				return this.buildResponse(result);
			}
			return this.buildResponse(classe);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@GET
	@Path("/buscarCargoPorId/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response buscarCargoPeloId (@PathParam("id") int dado){
		try{
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String[] campos = {"cargos", "id","nome","descricao", "salario", "permissao"}; 
			condicao += "status=1 and id="+dado;
			
			classe = Banco.selecionar(condicao, campos, Cargo.class.getName(),0);
			
			return this.buildResponse(classe);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@POST 
	@Path("/addCargo")
	@Consumes("application/*")
	public Response adicionaCargos(String o){
		o = Formatar.formatar(o, "cripBase64", Oper.ORGANIZAR);
		try{
			Cargo classe = new ObjectMapper().readValue(o, Cargo.class);
			this.msg = Validar.cargo(classe,"cad");
			if(this.msg==""||this.msg.equals("")){			
				String[] campos = {"cargos" , "nome", "descricao", "salario", "permissao","status"}; 
				this.valores = classe.devolve(campos);
				
				this.retorno = Banco.inserir(valores, campos);
				if(this.retorno){
					this.msg += "Cargo cadastrado com sucesso.";
				}else{
					this.msg += "Erro ao cadastrar o Cargo.";
				}
				
			}
			return this.buildResponse(this.msg);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@PUT
	@Path("/editCargo")
	@Consumes("application/*")
	public Response editarCargo (String o){
		o = Formatar.formatar(o, "cripBase64", Oper.ORGANIZAR);
		try{
			Cargo classe = new ObjectMapper().readValue(o, Cargo.class);
			this.msg = Validar.cargo(classe,"upd");
			if(this.msg==""||this.msg.equals("")){					
				String[] campos = {"cargos" , "nome", "descricao", "salario", "permissao"}; 
				this.valores = classe.devolve(campos);
				this.condicao = "id="+classe.getId();
				
				this.retorno = Banco.alterar(valores, campos, condicao);
				if(this.retorno){
					this.msg += "Cargo editado com sucesso!";
				}else{
					this.msg += "Erro ao editar o cargo!";
				}
				
			}
			return this.buildResponse(this.msg);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}	
	@DELETE
	@Path("/delCargo/{id}")
	@Consumes("application/*")
	public Response deletarCargo(@PathParam("id") String id){
		try{
			String[] campos = {"cargos", "status"};
			this.valores.put("status","0");
			condicao = "id="+id;
			
			this.retorno = Banco.alterar(valores,campos,condicao);
			if(this.retorno){
				this.msg += "Cargo deletado com sucesso.";
			}else{
				this.msg += "Erro ao deletar o cargo";
			}
			
			return this.buildResponse(this.msg);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@GET
	@Path("/buscarProduto/{valor}/{pag}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes("application/*")
	public Response buscaProduto(@PathParam("valor") String dado, @PathParam("pag") int pag){
		try {
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String[] campos = {"produtos", "id","nome", "unid_medida", "preco"}; 
			condicao += "status=1 and (id='"+dado+"' OR nome like '"+dado+"%')";
			
			classe = Banco.selecionar(condicao, campos, Produto.class.getName(),pag);
			for(MaeDevolve m : classe){
				String preco = ((Produto)m).getPreco();
				((Produto)m).setPreco(Formatar.formatar(preco,"preco",Oper.DESORGANIZAR));
			}
			
			return this.buildResponse(classe);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@POST 
	@Path("/realizaVenda")
	@Consumes("application/*")
	public Response realizaVenda(String o) throws JsonParseException, JsonMappingException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		o = Formatar.formatar(o, "cripBase64", Oper.ORGANIZAR);
		try{
		HttpSession session= httpRequest.getSession();
		Venda classe = new ObjectMapper().readValue(o, Venda.class);
		String[] campos = {"vendas" , "idusuario", "forma_pag", "data_emissao", "data_final"}; 
		classe.setIdusuario(session.getAttribute("id").toString());
		this.valores = classe.devolve(campos);
		
		this.retorno = Banco.inserir(valores, campos);
		if(retorno){
			Map<String, String> valores2 = null;
			for (Produto p : classe.getProdutos()){
				valores2 = new HashMap<String, String>();
				String[] campos2 = {"vendas_has_produtos" , "idvendas", "idprodutos", "quantidade"}; 
				p.setIdvendas(classe.getIdvendas().toString());
				valores2 = p.devolve(campos2);
				
				this.retorno = Banco.inserir(valores2, campos2);
			}
			if(retorno){
				this.msg += "Venda realizada com sucesso.";
			}else{				
				this.msg += "2# - Erro ao realizar a venda.";
			}
		}else{
			this.msg += "1# - Erro ao realizar a venda.";
		}
			
		return this.buildResponse(this.msg);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@GET
	@Path("/faturamento/{dia}/{ate}/{pag}/{tipo}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response Faturamento(@PathParam("dia") String data,
			@PathParam("ate") String ate,
			@PathParam("pag") int pag,
			@PathParam("tipo") String tipo
			){
		try {
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String[] campos = {"Faturamento", "quantidade", "data_final"}; 
			String i ="";

			if(!data.equals("null")&&!data.equals("")||!ate.equals("null")&&!ate.equals("")){
				condicao += " data_final between ";
				if(!ate.equals("null")&&!ate.equals("")&&data.equals("null")||data.equals("")){
					data = "now()";
					condicao+=""+data+" and ";
				}else{
					data = Formatar.formatar(data, "data_pesquisa",Oper.DESORGANIZAR);
					condicao+="'"+data+"' and ";
				}
				if(ate.equals("null")||ate.equals("")&&!data.equals("null")&&!data.equals("")){
					ate="now()";
					condicao+=""+ate+"";
				}else{
					ate = Formatar.formatar(ate, "data_pesquisa",Oper.DESORGANIZAR);
					condicao+="'"+ate+"'";
				}
				
				 condicao+=" order by data_final ASC";
					i = numeroRegistros("total",condicao);
			}
			if(tipo.equals("b")){
				classe = Banco.selecionar(condicao, campos, Venda.class.getName(),pag);
				for( MaeDevolve v : classe){
					((Venda)v).setResp(i);
					((Venda) v).setQuantidade(Formatar.formatar(((Venda) v).getQuantidade(), "preco", Oper.ORGANIZAR));
				}
				
			}else if(tipo.equals("c")){
				String result = numeroRegistros("ft",condicao);
				return this.buildResponse(result);
			}
			return this.buildResponse(classe);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
		
	}
	@GET
	@Path("/numeroVenda")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response campoNumeroVenda(){
		try {
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String[] campos = {"ultimaVenda", "id"};
			this.condicao +="";
			
			classe = Banco.selecionar(condicao, campos, Venda.class.getName(),0);
			
			int i = 0;
			for(MaeDevolve v : classe){
				i = ((Venda)v).getId()+1;
			}
			return this.buildResponse(i);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@GET
	@Path("/grafico-produto/{dia}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response graficoProduto(@PathParam("dia") String dia){
		try {
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String[] campos = {"graficoProduto", "nome", "prec"}; 
			String primeiroDiaDoMes = dia.substring(0, 8)+"01";
			condicao += "status=1 and data_final between '"+primeiroDiaDoMes+"' and '"+dia+"' order by prec desc";
			classe = Banco.selecionar(condicao, campos, Produto.class.getName(),0);
			return this.buildResponse(classe);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@GET
	@Path("/grafico-faturamento/{dia}/{mg}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response graficoFaturamento(@PathParam("dia") String dia,@PathParam("mg") String mg){
		try {
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String[] campos = {"Faturamento", "quantidade", "data_final"}; 
			
			condicao += "data_final between '"+mg+"' and '"+dia+"' order by data_final asc";
			classe = Banco.selecionar(condicao, campos, Venda.class.getName(),0);
			for(MaeDevolve v : classe){
				((Venda) v).setData_final(((Venda) v).getData_final().substring(0,5));
			}
			return this.buildResponse(classe);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@GET
	@Path("/grafico-venda/{dia}/{mg}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response graficoVenda(@PathParam("dia") String dia,@PathParam("mg") String mg){
		try {
			List<Venda> classe = new ArrayList<Venda>();
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCSelecionarDAO banco = new JDBCSelecionarDAO(conexao);
			classe = banco.graficoVenda(dia,mg);
			conec.fecharConexao();
			return this.buildResponse(classe);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@GET
	@Path("/buscaVenda/{numVenda}/{formaPag}/{dia}/{ate}/{pag}/{tipo}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response buscaVenda(
			@PathParam("numVenda") String numVenda,
			@PathParam("formaPag") String formaPag,
			@PathParam("dia") String data,
			@PathParam("ate") String ate,
			@PathParam("pag") int pag,
			@PathParam("tipo") String tipo
			){
		try {
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String[] campos = {"consulta_venda", "id","forma_pag","nome", "data_final","data_emissao","resp"}; 
			if(!numVenda.equals("null")&&!numVenda.equals("")){
				condicao += "id='"+numVenda+"'";
			}
			if(!formaPag.equals("null")&&!formaPag.equals("")){
				if(condicao!=""){
					condicao+= " and ";
				}
				condicao += "forma_pag='"+formaPag+"'";
			}
			if(!data.equals("null")&&!data.equals("")||!ate.equals("null")&&!ate.equals("")){
				if(condicao!=""){
					condicao+= " and ";
				}
				condicao += "data_final between ";
				if(!ate.equals("null")&&!ate.equals("")&&data.equals("null")||data.equals("")){
					data = "now() and ";
					condicao+=""+data+" and ";
				}else{
					data = Formatar.formatar(data, "data_pesquisa",Oper.DESORGANIZAR);
					condicao+="'"+data+"' and ";
				}
				if(ate.equals("null")||ate.equals("")&&!data.equals("null")&&!data.equals("")){
					ate="now()";
					condicao+=""+ate+"";
				}else{
					ate = Formatar.formatar(ate, "data_pesquisa",Oper.DESORGANIZAR);
					condicao+="'"+ate+"'";
				}
			}
			if(tipo.equals("b")||tipo.equals("g")){
				classe = Banco.selecionar(condicao, campos, Venda.class.getName(),pag);
				for(MaeDevolve v : classe){
					((Venda) v).setResp(Formatar.formatar(((Venda) v).getResp(), "preco", Oper.ORGANIZAR));
				}
				if(tipo.equals("g")){
					String r  = pegaNoBanco(condicao);
					for(MaeDevolve v : classe){
						String forma="";
						switch(((Venda) v).getForma_pag()){
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
						((Venda) v).setForma_pag(forma);
						((Venda) v).setQuantidade(r);
					}
				}
			}else if(tipo.equals("c")){
				String result = numeroRegistros("cv",condicao);
				return this.buildResponse(result);
			}
			
			return this.buildResponse(classe);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
		
	}
	@GET
	@Path("/listaDeItens/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response ListaDeProdutos (@PathParam("id") int dado){
		try{
			List<MaeDevolve> classe = new ArrayList<MaeDevolve>();
			String[] campos = {"listaVenda", "id","nome", "unid_medida", "preco","quantidade"}; 
			condicao += "idvendas="+dado;
			classe = Banco.selecionar(condicao, campos, Produto.class.getName(),0);
			return this.buildResponse(classe);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	@GET
	@Path("/checagem")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response checagem (@PathParam("id") int dado){
		try{
			HttpSession session= httpRequest.getSession();
			String s = session.getAttribute("permissao").toString();
			String c = session.getAttribute("checagem").toString();
			String results = "";
			if(!c.equals("")){

				if(c.equals("2")&&s.equals("2")){
					results="2";
				}else if(c.equals("1")&&(s.equals("1")||s.equals("2"))){
					if(c.equals("1")&&s.equals("1")){
						results="1";
					}else if (c.equals("1")&&s.equals("2")){
						results="2";
					}
				}else{
					results="0";
				}
			}else{
				results="";
			}
			return this.buildResponse(results);
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	public String numeroRegistros(String tipo ,String condicao){
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCSelecionarDAO banco = new JDBCSelecionarDAO(conexao);
			String i = banco.pegaNumeroRegistros(tipo,condicao);
			conec.fecharConexao();
			return i;
	}
	public String pegaNoBanco(String condicao){
		Conexao conec = new Conexao();
		Connection conexao = conec.abrirConexao();
		JDBCSelecionarDAO banco = new JDBCSelecionarDAO(conexao);
		String i = banco.pegaTotalPrecoVenda(condicao);
		conec.fecharConexao();
		return i;
	}
	
}


