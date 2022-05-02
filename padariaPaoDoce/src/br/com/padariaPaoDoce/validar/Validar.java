package br.com.padariaPaoDoce.validar;
import br.com.padariaPaoDoce.objetos.*;
import br.com.padariaPaoDoce.validar.Operacao;
public class Validar {

	private static String msg;
	private static Operacao operacao = null;
	
	public static String produto(Produto o,String f) throws Exception {
		VALProduto oper = new VALProduto();
		operacao = oper;
		msg = acao(o,f);
		return msg;
	}
	public static String cargo(Cargo o,String f) throws Exception {
		VALCargo oper = new VALCargo();
		operacao = oper;
		msg = acao(o,f);
		return msg;
	}
	public static String funcionario(Funcionario o,String f) throws Exception{
		VALFuncionario oper = new VALFuncionario();
		operacao = oper;
		msg = acao(o,f);
		return msg;
	}
	public static String acao(MaeDevolve o,String f) throws Exception {
		return operacao.acao(o,f);
	}
}
