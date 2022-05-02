package br.com.padariaPaoDoce.formatar;

public class Formatar {
	private static Operacao formata = null;
	private static String resposta;
	private static String formatado = "";
	
	public static String formatar(String valor,String operacao, Oper organizar){
		switch(operacao){
		case "cpf":
			case "funcionarios_cpf":
				formatado = cpf(valor, organizar);												
		break;
		case "data_nasc":
			case "data_final":
				case "data_emissao":
					case "data_pesquisa":
			formatado = data(valor, organizar);
		break;
		case "preco":
			case "salario":
				formatado = preco(valor, organizar);
		break;
		case "telefone":
			formatado = telefone(valor, organizar);
		break;
		case "cripBase64":
			formatado = cripBase64(valor,organizar);
			break;
		case "cripMD5":
			case "senha":
			formatado = cripMD5(valor, organizar);
			break;
		default:
			formatado = valor;
		break;
		}
		return formatado;
	}
	public static String respostaOperacao(Oper f) {
		if(f==Oper.DESORGANIZAR){			
			resposta = formata.desorganizar();
		}else if(f==Oper.ORGANIZAR){
			resposta = formata.organizar();
		}
		return resposta;
	}
	public static String data(String r,Oper f){
		Data o = new Data();
		formata = o;
		formata.frm(r);
		resposta = respostaOperacao(f);
		return resposta;
	}
	public static String telefone(String r,Oper f){
		Telefone o = new Telefone();
		formata = o;
		formata.frm(r);
		resposta = respostaOperacao(f);
		return resposta;
	}
	public static String cpf(String r,Oper f){
		Cpf o = new Cpf();
		formata = o;
		formata.frm(r);
		resposta = respostaOperacao(f);
		return resposta;
	}
	public static String preco(String r,Oper f){
		Preco o = new Preco();
		formata = o;
		formata.frm(r);
		resposta = respostaOperacao(f);
		return resposta;
	}
	public static String cripBase64(String r,Oper f){
		CB64 o = new CB64();
		formata = o;
		formata.frm(r);
		resposta = respostaOperacao(f);
		return resposta;
	}
	public static String cripMD5(String r,Oper f){
		CMD5 o = new CMD5();
		formata = o;
		formata.frm(r);
		resposta = respostaOperacao(f);
		return resposta;
	}
	
}
