package br.com.padariaPaoDoce.validar;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import br.com.padariaPaoDoce.objetos.*;

public abstract class Operacao {
	public String msg = "";
	public abstract String acao(MaeDevolve o,String f) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, SQLException;
}
