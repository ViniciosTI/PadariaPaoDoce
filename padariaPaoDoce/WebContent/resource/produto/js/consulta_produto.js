//var NI = 10;
//var pag = 1;
var numitens=0;
var pagina=1;
var f="p";
var filtro=0;
var pesq = "";
var unid = "";
var preco = "";
var ate = "";
SENAI.consulta_produto = new Object();
$(document).ready(function(){
	
	SENAI.consulta_produto.exibirProduto = function(lista, valor){
		var html = "<div class='row'><div class='col-md-12'><table class='faturamento table'>";
		html += "<thead>" +
				"<tr>" +
				"<th scope='col' width='5%'>ID</th>" +
				"<th scope='col' width='15%'>Nome</th>" +
				"<th scope='col' width='15%'>Unid. Medida</th>" +
				"<th scope='col' width='15%'>Preço</th>" +
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
	
	SENAI.consulta_produto.exibirProduto(undefined,"");
	
});
function getitens(novaPag){
	pagina = novaPag;
	var url= "../../rest/PaoDoce/buscarProdfiltrado/"+pesq+"/"+unid+"/"+preco+"/"+ate+"/";
	var cfg = {
			type: "GET",
			url: url+pagina+"/"+"b",
			success: function(lista){
				SENAI.consulta_produto.exibirProduto(lista);
				var valor = 0;
				lista.map(function(item) {
					valor = item["max"];
				});
				
				numitens = valor;
				contador(url,pagina,"c");
			},
			error: function(err){
				alert("Erro ao consultar os produtos: " + err.responseText);
			}
	};
	SENAI.ajax.get(cfg);
	
}

function pesquisar(){
	if(filtro==0){
		pesq = $("#pesquisa").val();
		
		if($("#pesquisa").val()==""||filtro==1){
			pesq=null;
			getitens(pagina);
		}else{
			getitens(1);
		}
	}else if(filtro==1){
		itensFiltro();
		getitens(1);
	}
	
}
function itensFiltro(){
	pesq = $("#pesq-filtro").val();
	unid = $("#unid_medida").val();
	preco = $("#preco").val();
	ate = $("#ate").val();
	validaFiltro();
}
function validaFiltro(){
	if($("#pesq-filtro").val()==''||filtro==0){
		pesq = null;
	}
	if($("#unid_medida").val()==''||filtro==0){
		unid = null;
	}
	if($("#preco").val()==''||filtro==0){
		preco = null;
	}
	if($("#ate").val()==''||filtro==0){
		ate = null;
	}

}
function mudaFiltro(){
	if(filtro==0){
		filtro++;
	}else if(filtro==1){
		filtro--;
	}
}