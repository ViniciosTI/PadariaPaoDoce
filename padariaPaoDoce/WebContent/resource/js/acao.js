/*Criação do objeto*/
SENAI.acao = new Object();
$(document).ready(function(){	
	SENAI.acao.cadastrar = function(o,url,f,callback){
		progress();
		var cfg = {
				/*
				 * Abaixo a url repassada como referência para o acesso a Rest que contém,
				 * dentre outros, o recurso que tratará de todo o processo para cadastramento
				 * dos dados do formulário
				 */
				type: "POST",
				url: url,
				data: o,
				success: function(msg){
					$(".progress").remove();
					
					if(msg.indexOf("#")==0||msg.indexOf("Alerta:")==0){
						mensagem(msg,"danger");
					}else{
						limparCampos();
						mensagem(msg,"success");
					}
					
					if(typeof callback=="function"){
						callback(msg,true);
					}	
					
					SENAI.acao.busca(f);
					
				},
				error: function(err) {
					if(typeof callback=="function"){
						callback(msg,false);
					}	
					$(".progress").remove();
					alert("Erro ao cadastrar!"+err.responseText);
				}
		}; 

		SENAI.ajax.post(cfg);
	};

	SENAI.acao.busca = function(f){
		var valorBusca = $("#pesquisar").val();
		switch(f){
		case "p":
			SENAI.produto.exibirProduto(undefined, valorBusca);
			break;
		case "c":
			SENAI.cargo.exibirCargo(undefined, valorBusca);
			break;
		case "f":
			SENAI.funcionario.exibirFuncionario(undefined, valorBusca);
			break;
		}
	};
	SENAI.acao.deletar = function(url,f){
		progress();
		if(confirm("Deseja deletar esse registro?")){
			var cfg = {
					type: "DELETE",
					url: url,
					success: function(msg){
						$(".progress").remove();
						if(msg.indexOf("#")==0||msg.indexOf("Alerta:")==0){
							mensagem(msg,"danger");
						}else{
							mensagem(msg,"success");
						}
						SENAI.acao.busca(f);
					},
					error: function (err){
						$(".progress").remove();
						alert("Erro ao deletar" + err.responseText);
					}
			};
			SENAI.ajax.del(cfg);
		}else{
			$(".progress").remove();			
		}
	
	};

	SENAI.acao.editar = function(url,get_url,c,f){
		$("#modal_edit").load(c, function(){	
			eventosEdit();
			var cfg = {
					type: "GET",
					url: get_url,
					success: function(o){
						$(".progress").remove();

						var b = new Object();
						b = setarDadosNoModal(o);

						SENAI.acao.exibirEdicao(b,url,f);
					},
					error: function(err){
						$(".progress").remove();
						alert("Erro ao editar!" + err.responseText);
					}
			};
			SENAI.ajax.get(cfg);
		});
	}
	SENAI.acao.exibirEdicao = function(b, url, f){
		var cfg = {
				title: "Editar",
				height: 420,
				width: 650,
				modal: true,
				buttons: {
					"Cancelar": function(){
						$(this).dialog("close");
					},
					"Salvar": function(){
						progress();
						var dialog = this;
						var b = new Object();

						var o = setarDados(b);

						var cfg = {
								type: "put",
								url: url,
								data: o,
								success: function(msg){
									$(".progress").remove();

									$(dialog).dialog("close");
									if(msg.indexOf("#")==0||msg.indexOf("Alerta:")==0){
										mensagem(msg,"danger");
									}else{
										mensagem(msg,"success");
									}
									SENAI.acao.busca(f);
								},
								error: function(err){
									$(".progress").remove();

									alert("Erro ao editar"+err.responseText);
								}
						};

						SENAI.ajax.put(cfg);
					},

				},
				close: function(){
					//caso o usuário simplesmente feche a caixa de edição não deve acontecer nada
				}
		};
		$("#modal_edit").dialog(cfg);
	}
});
function limparCampos(){
	$("input").val("");
	$("select").val("");
	$("textarea").val("");
}
function progress(){
	var html = "<div class='progress'  style='height:6px;'>"+ 
	"<div class='progress-bar progress-bar-warning progress-bar-striped active' role='progressbar' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100' style='width: 100%'>"+
	"<span class='sr-only'>Carregando</span>"+
	"</div>"+
	"</div>";
	$("#progress").html(html);
}