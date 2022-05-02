package br.com.padariaPaoDoce.validar;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.padariaPaoDoce.crud.Banco;
import br.com.padariaPaoDoce.objetos.*;

public class VALProduto extends Operacao {

	private List<MaeDevolve> lista = new ArrayList<MaeDevolve>();

	public String acao(MaeDevolve obj,String f) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, SQLException{
		Produto o = (Produto) obj;

		if(o.getNome().equals("")||o.getNome()==null||o.getPreco().equals("")||o.getPreco()==null||o.getUnid_medida().equals("")||o.getUnid_medida()==null){
			msg="Alerta: Os campos obrigatórios devem ser preenchidos.";
		}
		switch(f){
		case "cad":
			String[] campos = {"produtos","nome", "id","unid_medida"}; 
			String condicao = " nome='"+o.getNome()+"' and status=1";
			lista = Banco.selecionar(condicao, campos, Produto.class.getName(),0);
			if(this.lista.size()>0){	
				for(MaeDevolve p :  this.lista){
					if(((Produto) p).getNome().equals(o.getNome())){
						if( ((Produto) p).getUnid_medida().equals(o.getUnid_medida()) ){
							msg="Alerta: Já possui um registro com este nome e unidade de medida.";
						}
					}
				}
			}
			break;
		case "upd" :
			String[] campos2 = {"produtos", "id","nome", "unid_medida"}; 
			String condicao2 = " nome='"+o.getNome()+"' and status=1";
			lista = Banco.selecionar(condicao2, campos2, Produto.class.getName(),0);
			if(this.lista.size()>0){		
				for(MaeDevolve p :  this.lista){
					if(((Produto) p).getId()!=o.getId()){	
						if(((Produto) p).getNome().equals(o.getNome())){
							if( ((Produto) p).getUnid_medida().equals(o.getUnid_medida()) ){
								msg="Alerta: Já possui um registro com este nome e unidade de medida.";
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