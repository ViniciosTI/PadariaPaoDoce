package br.com.padariaPaoDoce.validar;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.padariaPaoDoce.crud.Banco;
import br.com.padariaPaoDoce.objetos.Funcionario;
import br.com.padariaPaoDoce.objetos.MaeDevolve;
import br.com.padariaPaoDoce.formatar.*;
public class VALFuncionario extends Operacao {
	private List<MaeDevolve> lista = new ArrayList<MaeDevolve>();

	public String acao(MaeDevolve obj, String f) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, SQLException {
		Funcionario o = (Funcionario) obj;
		String cpf = Formatar.formatar(o.getCpf(),"cpf",Oper.DESORGANIZAR);
		switch(f){
		case "cad":
			if(o.getNome().equals("")|| o.getNome()==null||
			o.getEmail().equals("")||o.getEmail()==null||
			o.getTelefone().equals("")||o.getTelefone()==null||
			o.getData_nasc().equals("")||o.getData_nasc()==null||
			o.getCpf().equals("")||o.getCpf()==null||
			o.getUsuario().equals("")||o.getUsuario()==null||
			o.getSenha().equals("")||o.getSenha()==null||
			o.getIdcargos().equals("")||o.getIdcargos()==null)
			{	
				msg="Alerta: Os campos obrigatórios devem ser preenchidos.";
			}else{
				String[] campos = {"view_FuncUser", "usuario","id","email","data_nasc","telefone","cpf","nome","nome_cargo"}; 
				String condicao = " cpf = '"+cpf+"' or email='"+o.getEmail()+"' or usuario = '"+o.getUsuario()+"'";
				
				lista = Banco.selecionar(condicao, campos, Funcionario.class.getName(),0);
				if(this.lista.size()>0){	
					for(MaeDevolve p :   this.lista){
						if(Formatar.formatar(((Funcionario) p).getCpf(),"cpf",Oper.DESORGANIZAR).equals(cpf)){
							msg="Alerta: Já possui um registro com este CPF.";
						}else if(((Funcionario) p).getEmail().equals(o.getEmail())){
							msg="Alerta: Já possui um registro com este Email.";
						}else if(((Funcionario) p).getUsuario().equals(o.getUsuario())){
							msg="Alerta: Já possui um registro com este Usuario.";
						}
					}
				}
			}
			break;
		case "upd":
			if(o.getNome().equals("")|| o.getNome()==null||
			o.getEmail().equals("")||o.getEmail()==null||
			o.getTelefone().equals("")||o.getTelefone()==null||
			o.getData_nasc().equals("")||o.getData_nasc()==null||
			o.getCpf().equals("")||o.getCpf()==null||
			o.getUsuario().equals("")||o.getUsuario()==null||
			o.getIdcargos().equals("")||o.getIdcargos()==null)
			{	
				msg="Alerta: Os campos obrigatórios devem ser preenchidos.";
			}else{
				String[] campos = {"view_FuncUser", "usuario","id","email","data_nasc","telefone","cpf","nome","nome_cargo"}; 
				String condicao = " cpf = '"+cpf+"' or email='"+o.getEmail()+"' or usuario = '"+o.getUsuario()+"'";
				lista = Banco.selecionar(condicao, campos, Funcionario.class.getName(),0);
				if(this.lista.size()>0){	
					for(MaeDevolve p :  this.lista){
						if(!((Funcionario) p).getCpf().equals(o.getCpf())){
							if(((Funcionario) p).getEmail().equals(o.getEmail())){
								msg="Alerta: Já possui um registro com este Email.";
							}else if(((Funcionario) p).getUsuario().equals(o.getUsuario())){
								msg="Alerta: Já possui um registro com este Usuario.";
							}
						}
					}
				}
			}
			break;
		}		
		return msg;
	}

}
