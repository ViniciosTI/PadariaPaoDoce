/*
 * MELHORIAS
 * colocar um select no filtro para agrupar o
 * resultado da pesquisa em blocos de meses e dias
 */
var numitens=0;
var pagina=1;
var data = "";
var ate = "";
var total = "";
SENAI.faturamento = new Object();

$(document).ready(function(){

	SENAI.faturamento.exibirFaturamento = function(lista, valor){
		var html = "<div class='row'><div class='col-md-12'><table class='faturamento table'>";
		html += "<thead>" +
				"<tr>" +
				"<th scope='col'>Data</th>" +
				"<th scope='col'>Valor</th>" +
				"</tr>" +
				"</thead>" +
				"<tbody>";
		if(lista != undefined && lista.length > 0){
			var unid = "";
			for(var i=0; i<lista.length; i++){		
				html += "<tr>" +
				"<td scope='row'>"+lista[i].data_final+"</td>"+
				"<td>"+lista[i].quantidade+"</td>"+
				"</tr>";
			}
			if(total!=""){
				html+="<tr>" +
				"<td scope='row' id='total_consulta'>Total:</td>"+
				"<td>"+total+"</td>"+
				"</tr>";
			}

		}else{
			if(lista == undefined){
				if(valor == ""){
					valor = null;
				}
				validaFiltro();
				getitens(pagina);
			}else{
				html += "<tr>" +
				"<td scope='row' colspan='5' align='center'> Nenhum registro encontrado</td>" +
				"</tr>";
			}
		}
		html += "</tbody></table></div></div>";
		$("#resultados").html(html);
	}
	
	SENAI.faturamento.exibirFaturamento(undefined,"");
	
	$(function(){
		$('#data').datepicker({
			changeYear: true,
			changeMonth: true,
			yearRange: "-5:",
			defaultDate: new Date()
		});
	})
	$(function(){
		$('#ate').datepicker({
			changeYear: true,
			changeMonth: true,
			yearRange: "-5:",
			defaultDate: new Date()
		});
	})
});

function getitens(novaPag){
	pagina = novaPag;
	var url= "../../rest/PaoDoce/faturamento/"+data+"/"+ate+"/";
	var cfg = {
			type: "GET",
			url: url+pagina+"/"+"b",
			success: function(lista){
				var valor = 0;
				lista.map(function(item) {
					valor = item["max"];
					total = item["resp"];
				});
				SENAI.faturamento.exibirFaturamento(lista);
				
				numitens = valor;
				contador(url,pagina);
			},
			error: function(err){
				alert("Erro ao consultar os produtos: " + err.responseText);
			}
	};
	SENAI.ajax.get(cfg);
}

function pesquisar(){
	itensFiltro();
	getitens(1);
}
function itensFiltro(){
	data = $("#data").val();
	ate = $("#ate").val();
	validaFiltro();
}
function validaFiltro(){
	if($("#data").val()==''){
		data = null;
	}else{
		data = data.split("/").join("-");
	}
	if($("#ate").val()==''){
		ate = null;
	}else{
		ate = ate.split("/").join("-");
	}
}
