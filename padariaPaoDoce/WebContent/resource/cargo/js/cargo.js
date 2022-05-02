/*Criação do objeto*/
var pagina = 1;
SENAI.cargo = new Object();
var f="c";
var pagina=1;
var numitens=0;
$(document).ready(function(){
	var url="";
	var o = new Object();	
	SENAI.cargo.validacao = function(){
		
		if(validacao(f)){
			// objeto que levará os dados para o lado back-end
			
			o.nome = document.getElementById("funcao").value;
			o.salario = document.getElementById("valor").value;
			o.permissao = document.getElementById("permissao").value;
			o.descricao = document.getElementById("descricao").value;
			o.status = "1";
			url = "../../rest/PaoDoce/addCargo"; 
			// caminho do arquivo back-end 
			console.dir(o);
			SENAI.acao.cadastrar(o,url,f);
		}
	}
	
	SENAI.cargo.exibirCargo = function(lista, valor){
		var html = "<div class='row'><div class='col-md-12'><table class='table'>";
		html += "<thead>" +
					"<tr>" +
						"<th scope='col' width='5%'>ID</th>" +
						"<th scope='col' width='15%'>Nome</th>" +
						"<th scope='col' width='15%'>Salário</th>" +
						"<th scope='col' width='15%'>Permissão</th>" +
						"<th scope='col' width='10%'>Descriçao</th>" +
						"<th scope='col' width='10%'>Ações</th>"
					"</tr>" +
				"</thead>" +
				"<tbody>";
		if(lista != undefined && lista.length > 0){
			var permissao = "";
			for(var i=0; i<lista.length; i++){		
				switch(lista[i].permissao){
				case "0":
					permissao = "Restrição";
				break;
				case "1":
					permissao = "Acesso de caixa";
				break;
				case "2":
					permissao = "Acesso total";
				break;
				default:
					permissao = "Erro";
				break;
				}
				html += "<tr>" +
							"<td scope='row'>"+lista[i].id+"</td>"+
							"<td>"+lista[i].nome+"</td>"+
							"<td>"+lista[i].salario+"</td>"+
							"<td>"+permissao+"</td>"+
							"<td>"+lista[i].descricao+"</td>" +
							"<td align='center'>" +
								"<a class='link' title='Editar' onclick='SENAI.cargo.edit("+lista[i].id+")'>"+
									"<span class='glyphicon glyphicon-edit'> </span>" +
								"</a>"+
								"<a class='link' title='Deletar' onclick='SENAI.cargo.del("+lista[i].id+")'>"+
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
							"<td scope='row' colspan='7' align='center'> Nenhum registro encontrado</td>" +
						"</tr>";
			}
		}
		html += "</tbody></table></div></div>";
		$("#resultados").html(html);
	}
	
	SENAI.cargo.exibirCargo(undefined,"");
	
	SENAI.cargo.del = function(i){
		url = "../../rest/PaoDoce/delCargo/"+i;
		SENAI.acao.deletar(url,f)
	};
	
	SENAI.cargo.edit = function(i){
		var c = "../cargo/edit_cargo.html";
		var get_url="../../rest/PaoDoce/buscarCargoPorId/"+i;
		url = "../../rest/PaoDoce/editCargo";
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
	var url = "../../rest/PaoDoce/buscarCargoPorNome/"+valor+"/"+pagina;
	var cfg = {
			type: "GET",
			url: url+"/"+"b",
			success: function(lista){
				SENAI.cargo.exibirCargo(lista);
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
	$("#funcaoEdit").val(o[0].nome);
	$("#valorEdit").val(o[0].salario);
	$("#permissaoEdit").val(o[0].permissao);
	$("#descricaoEdit").val(o[0].descricao);
	$("#idCargo").val(o[0].id);
	return o;
}

function setarDados(o){
	o.id = $("#idCargo").val();
	o.nome = $("#funcaoEdit").val();
	o.salario = $("#valorEdit").val();
	o.permissao = $("#permissaoEdit").val();
	o.descricao = $("#descricaoEdit").val();
	return o;
}
	