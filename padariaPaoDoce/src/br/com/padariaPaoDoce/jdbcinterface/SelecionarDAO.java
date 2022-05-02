package br.com.padariaPaoDoce.jdbcinterface;

import java.util.List;

import br.com.padariaPaoDoce.objetos.Venda;

public interface SelecionarDAO {
	abstract List<Venda> graficoVenda(String data,String mg);
	abstract String permissaoUsuario(String string);
	abstract String pegaTotalPrecoVenda(String string);
	abstract String pegaNumeroRegistros(String tipo,String condicao);
	
}
