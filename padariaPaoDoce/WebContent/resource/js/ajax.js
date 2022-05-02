
//Criando o sub-objeto ajax do objeto SENAI
SENAI.ajax = new Object();

/*
 * Processa o pedido, solicitação HTTP Ajax a ser recebido pelo rest.
 */
function ajaxRequestDefault(){
	var def = {
			url: null,
			dataType:'json',
			contentType:"application/json; charset=UTF-8",
			type:'POST',
			success: function(){},
			error: function(err){
				alert("error = "+err.responseText);
			}
	};
	return def;
}
/*
 * Verifica o estado do objeto cfg recebido, ou seja, se o identificador cfg
 * trata-se realmente de uma variável de objeto contendo suas respectivas
 * propriedades com valoes, se isto se confirmar retorna o objeto cfg.
 * Notem a chamada ao metodo stringify() que, por sua vez, converte o conteúdo
 * dos dados encontrados em cfg.data em JSON para que posteriormente possam
 * ser repassados pelo Ajax a partir de um pedido HTTP.
 * Notem também a chamada à função isObject(cfg.data) que repassa como parâmetro cfg.data
 * para saber se os dados apontados por cfg tratam-se de um array de dados,
 * ou se é um objeto simples, criado como {} ou new Object e por útimo
 * verifica se trata-se de uma função objeto javascript 
 */
function verifyObjectData(cfg){
	if(cfg.data){
		if(isObject(cfg.data)){
			cfg.data = JSON.stringify(cfg.data);
		}
		
	}
	if(cfg.type=="POST"||cfg.type=="PUT"){
		cfg.data=btoa(cfg.data);
	}
	return cfg;
};
/*
 * A função abaixo verifica os dados apontados por cfg tratam-se de um array
 * de dados, ou se é um simples, criado como {} ou new Object e por
 * último verifica se trata-se de uma função objeto javascript. Retorna o tipo
 * estrutura passado pelo objeto cfg.
 */
function isObject(o){
	return $.isArray(o) | $.isPlainObject(o) | $.isFunction(o);
};

SENAI.ajax.post = function(cfg){
	/*
	 * Iniciar o Ajax e processa um pedido de Ajax, a partir de
	 * ajaxRequestDefault().
	 * Esta inicialização e pedido são repassadas para o objeto def que,
	 * por sua vez, passará a ser um Ajax solicitante de uso geral.
	 */
	var def = new ajaxRequestDefault();
	/*
	 * Chama a função verifyObjectData() que, por sua vez, verifica o
	 * estado do objeto cfg recebido, ou seja, se o identificador cfg
	 * trata-se realmente de uma variável de objeto contendo suas
	 * respectivas propriedades com valores, se isto se confirmar retorna o
	 * objecto cfg e o armazena também em outro
	 * objecto cfg que será fundido no objecto config para que uma
	 * solicitação, pedido HTTP, Ajax seja enviada para um recurso Rest.
	 */
	cfg = verifyObjectData(cfg);
	
	/*
	 * Fundindo os objectos def e cfg e armazenando seus respectivos valores em config
	 */
	var config = $.extend(def,cfg);
	/*
	 * Realizando um HTTP pedido ajax. Em o parâmentro config segue o pedido
	 * Ajax.
	 */
	$.ajax(config);
};

SENAI.ajax.get = function(cfg){
	var def = new ajaxRequestDefault();
	cfg.type = "GET";
	cfg = verifyObjectData(cfg);
	var config = $.extend(def, cfg);
	$.ajax(config);
};
SENAI.ajax.del = function(cfg){
	var def = new ajaxRequestDefault();
	cfg.type = "DELETE";
	cfg = verifyObjectData(cfg);
	var config = $.extend(def, cfg);
	$.ajax(config);
};
SENAI.ajax.put = function(cfg){
	var def = new ajaxRequestDefault();
	cfg.type = "PUT";
	cfg = verifyObjectData(cfg);
	var config = $.extend(def, cfg);
	$.ajax(config);
};





