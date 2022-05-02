/*Criação do objeto*/
SENAI.produto = new Object();

var f = "p";
var pagina=1;
var numitens=0;
$(document).ready(function(){
	var url="";
	var o = new Object();
	SENAI.produto.validacao = function(){
		if(validacao(f)){
		
			o.nome = document.getElementById("nome").value;
			o.unid_medida = document.getElementById("unidade").value;
			o.preco = document.getElementById("valor").value;
			o.status = "1";
			url = "../../rest/PaoDoce/addProduto"; 
			// caminho do arquivo back-end 
			
			SENAI.acao.cadastrar(o,url,f);
		}
	}
	
	SENAI.produto.exibirProduto = function(lista, valor){
		var html = "<div class='row'><div class='col-md-12'><table class='faturamento table'>";
		html += "<thead>" +
					"<tr>" +
						"<th scope='col' width='5%'>ID</th>" +
						"<th scope='col' width='15%'>Nome</th>" +
						"<th scope='col' width='15%'>Unid. Medida</th>" +
						"<th scope='col' width='15%'>Preço</th>" +
						"<th scope='col' width='10%'>Ações</th>" +
					"</tr>" +
				"</thead>" +
				"<tbody>";
		if(lista != undefined && lista.length > 0){
			var unid = "";
			for(var i=0; i<lista.length; i++){		
				switch(lista[i].unid_medida){
				case "0":
					unid = "Unidade";
					break;
				case "1":
					unid = "Kilo";
					break;
				default:
					unid = "Erro";
					break;
				}
				html += "<tr>" +
							"<td scope='row'>"+lista[i].id+"</td>"+
							"<td>"+lista[i].nome+"</td>"+
							"<td>"+unid+"</td>"+
							"<td>"+lista[i].preco+"</td>"+
							"<td align='center'>" +
								"<a class='link' onclick='SENAI.produto.edit("+lista[i].id+")'>"+
									"<span class='glyphicon glyphicon-edit'> </span>" +
								"</a>"+
								"<a class='link' onclick='SENAI.produto.del("+lista[i].id+")'>"+
									"<span class='glyphicon glyphicon-remove'> </span>" +
								"</a>" +
							"</td>" +
						"</tr>";
			}
			
		}else{
			if(lista == undefined){
				if(valor == ""){
					valor = null;
				}
				
				getitens(pagina,valor);
			}else{
				html += "<tr>" +
							"<td scope='row' colspan='5' align='center'> Nenhum registro encontrado</td>" +
						"</tr>";
			}
		}
		html += "</tbody></table></div></div>";
		$("#resultados").html(html);
	}
	
	SENAI.produto.exibirProduto(undefined,"");
	
	SENAI.produto.del = function(i){
		url = "../../rest/PaoDoce/delProduto/"+i;
		SENAI.acao.deletar(url,f)
	};
		
	SENAI.produto.edit = function(i){
		var c = "../produto/edit_produto.html";
		var get_url="../../rest/PaoDoce/buscarProdPorId/"+i;
		url = "../../rest/PaoDoce/editProduto";
		SENAI.acao.editar(url,get_url,c,f);
	}
	$(function() {
  		$('#valor').maskMoney({ 
  			decimal: ',',
  			thousands: '',
  			precision: 2 
  			});
  	});

		
});

function getitens(novaPag,valor){
	pagina = novaPag;
	if(valor==undefined){
		valor = null;
	}
	var url = "../../rest/PaoDoce/buscarProdPorNome/"+valor+"/"+pagina
	var cfg = {
			type: "GET",
			url: url+"/"+"b",
			success: function(lista){
				SENAI.produto.exibirProduto(lista);
				var valor = 0;
				 lista.map(function(item) {
					 valor = item["max"];
				 });

				numitens = valor;
				contador(url,pagina);
			},
			error: function(err){
				alert("Erro ao consultar os produtos: " + err.responseText);
			}
	};
	SENAI.ajax.get(cfg);
}

function eventosEdit(){
	$(function() {
		$('#valorEdit').maskMoney({ 
			decimal: ',',
			thousands: '',
			precision: 2 
		});
	});	
}
function pesquisar(){
	pagina=1;
	SENAI.acao.busca(f);
}
function setarDadosNoModal(o){
	$("#nomeEdit").val(o[0].nome);
	$("#unidadeEdit").val(o[0].unid_medida);
	$("#valorEdit").val(o[0].preco);
	$("#idProduto").val(o[0].id);
	return o;
}
function setarDados(o){
	o.id = $("#idProduto").val();
	o.nome = $("#nomeEdit").val();
	o.unid_medida = $("#unidadeEdit").val();
	o.preco = $("#valorEdit").val();
	return o;
}