package br.com.padariaPaoDoce.validar;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.padariaPaoDoce.crud.Banco;
import br.com.padariaPaoDoce.objetos.Cargo;
import br.com.padariaPaoDoce.objetos.MaeDevolve;

public class VALCargo extends Operacao {

	private List<MaeDevolve> lista = new ArrayList<MaeDevolve>();

	public String acao(MaeDevolve obj,String f) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, SQLException{
		Cargo o = (Cargo) obj;
		if(o.getNome().equals("")|| o.getNome()==null||o.getSalario().equals("")||o.getSalario()==null||o.getPermissao().equals("")||o.getPermissao()==null){
			msg="Alerta: Os campos obrigatórios devem ser preenchidos.";
		}
		switch(f){
		case "cad":
			String[] campos = {"cargos", "id"}; 
			String condicao = " nome='"+o.getNome()+"' and status=1";
			lista = Banco.selecionar(condicao, campos, Cargo.class.getName(),0);
			if(this.lista.size()>0){					
				msg="Alerta: Já possui um registro com este nome de Cargo.";
			}
			break;
		case "upd":
			String[] campos2 = {"cargos", "id","nome"}; 
			String condicao2 = " nome='"+o.getNome()+"' and status=1";
			lista = Banco.selecionar(condicao2, campos2, Cargo.class.getName(),0);
			if(this.lista.size()>0){		
				for(MaeDevolve p :  this.lista){
					if(((Cargo) p).getId()!=o.getId()){							
						if(o.getNome().equals(((Cargo) p).getNome())){
							msg="Alerta: Já possui um registro com este nome de Cargo.";
						}
					}
				}
			}
			break;
		}		
		return msg;
	}
}
