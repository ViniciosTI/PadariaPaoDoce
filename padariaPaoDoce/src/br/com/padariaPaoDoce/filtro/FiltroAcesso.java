package br.com.padariaPaoDoce.filtro;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.Filter;



public class FiltroAcesso implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		httpResponse.setHeader("Pragma", "no-cache");
		httpResponse.setDateHeader("Expires", 0);
		/*
		 * O m�todo getContextPath � responsavel por retornar o contexto da URL
		 * que realizou a requisi��o.
		 */

		
		
		String context = request.getServletContext().getContextPath();

		try {
			/*
			 * O m�todo getSession � responsavel por pegar a sess�o ativa. Aqui
			 * foi necessario fazer um casting pois o objeto request � do tipo
			 * ServletRequest e n�o HttpServletRequest como no servlet onde voc�
			 * utiliza o m�todo sem o uso do casting.
			 */
			HttpSession session = ((HttpServletRequest) request).getSession();
			String usuario = null;
			if (session != null) {
				usuario = (String) session.getAttribute("login");
				String uri = ((HttpServletRequest)request).getRequestURI();
				switch(uri.substring(25)){
					case "venda/venda.html":
						session.setAttribute("checagem","1");
						break;
					case "produto/consulta_produto.html":
						session.setAttribute("checagem","1");
						break;
					case "cargo/cargo.html":
						session.setAttribute("checagem","2");
						break;
					case "funcionario/funcionario.html":
						session.setAttribute("checagem","2");
						break;
					case "produto/produto.html":
						session.setAttribute("checagem","2");
						break;
					case "venda/faturamento.html":
						session.setAttribute("checagem","2");
						break;
					case "venda/consulta_venda.html":
						session.setAttribute("checagem","2");
						break;
					case "home/home.html":
						session.setAttribute("checagem","2");
						break;
					case "home/bloqueio.html":
						session.setAttribute("checagem","");
						break;
				}
				String s = session.getAttribute("permissao").toString();
				String c = session.getAttribute("checagem").toString();

					if(c.equals("2")&&(s.equals("1")||s.equals("0"))){
						((HttpServletResponse) response).sendRedirect(context
								+ "/resource/home/bloqueio.html");
					}else if(c.equals("")&&s.equals("")){
						session.invalidate();
						((HttpServletResponse) response).sendRedirect(context
								+ "/index.html");
					}
			}
 
		
			
			if (usuario == null) {
				

					/*
					 * Aqui esta sendo setado um atributo na sess�o para que
					 * depois possamos exibir uma mensagem ao usu�rio.
					 */
					session.setAttribute("msg",
							"Voc� n�o est� logado no sistema!");
					/*
					 * Utilizamos o m�todo sendRedirect que altera a URL do
					 * navegador para posicionar o usu�rio na tela do login, que
					 * neste caso � a p�gina index.html Note que n�o precisamos
					 * utilizar o recurso "../../" para informar o caminho da
					 * p�gina index.html, a vari�vel do contexto j� posiciona no
					 * inicio da URL.
					 */
					((HttpServletResponse) response).sendRedirect(context
							+ "/index.html");

				
			} else {
				/*
				 * Caso exista um usu�rio valido (diferente de nulo) envia a
				 * requisi��o para a pagina que se deseja acessar, ou seja,
				 * permite o acesso, deixa passa.
				 */
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
