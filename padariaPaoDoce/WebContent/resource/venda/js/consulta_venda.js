
var numitens=0;
var pagina=1;
var data = "";
var ate = "";
var numVenda="";
var formaPag="";
SENAI.consulta_venda = new Object();
$(document).ready(function(){
	SENAI.consulta_venda.exibirVenda = function(lista, valor){
		var html = "<div class='row'><div class='col-md-12'><table class='faturamento table'>";
		html += "<thead>" +
		"<tr>" +
		"<th scope='col' width='4%'>ID</th>" +
		"<th scope='col' width='8%'>Data de emissão</th>" +
		"<th scope='col' width='8%'>Data final</th>" +
		"<th scope='col' width='8%'>Forma de pag.</th>" +
		"<th scope='col' width='8%'>Valor pago</th>" +
		"<th scope='col' width='15%'>Caixa</th>" +
		"<th scope='col' width='4%'>Lista de produtos</th>" +
		"</tr>" +
		"</thead>" +
		"<tbody>";
		/*
		 * "id","forma_pag","nome", "data_final"
		 */
		if(lista != undefined && lista.length > 0){
			var forma = "";
			for(var i=0; i<lista.length; i++){		
				switch(lista[i].forma_pag){
				case "0":
					forma= "Dinheiro";
					break;
				case "1":
					forma = "Débito";
					break;
				case "2":
					forma = "Crédito";
					break;
				default:
					forma = "Erro";
				break;
				}
				html += "<tr>" +
				"<td scope='row'>"+lista[i].id+"</td>"+
				"<td>"+lista[i].data_emissao+"</td>"+
				"<td>"+lista[i].data_final+"</td>"+
				"<td>"+forma+"</td>"+
				"<td>"+lista[i].resp+"</td>"+
				"<td>"+lista[i].nome+"</td>"+
				"<td align='center'>" +
				"<a class='link' onclick='SENAI.consulta_venda.listaDeItens("+lista[i].id+")'>"+
				"<span class='glyphicon glyphicon-eye-open'> </span>" +
				"</a>" +
				"</td>"+
				"</tr>";
			}
		}else{
			if(lista == undefined){
				if(valor == ""){
					valor = null;
				}
				validaFiltro();
				getitens(pagina,'b');
			}else{
				html += "<tr>" +
				"<td scope='row' colspan='7' align='center'> Nenhum registro encontrado</td>" +
				"</tr>";
			}
		}
		html += "</tbody></table></div></div>";
		$("#resultados").html(html);
	}

	SENAI.consulta_venda.exibirVenda(undefined,"");

	SENAI.consulta_venda.listaDeItens = function(id){
		var cfg = {
				type: "GET",
				url: "../../rest/PaoDoce/listaDeItens/"+id,
				success: function(o){

					SENAI.consulta_venda.abreListaVendas(o);
				},
				error: function(err){
					alert("Erro ao visualizar lista de produtos vendidos!" + err.responseText);
				}
		};
		SENAI.ajax.get(cfg);
	}
	SENAI.consulta_venda.abreListaVendas = function(lista){
		constroiLista(lista);
		var cfg = {
				title: "Lista de Produtos",
				height: 600,
				width: 800,
				modal: true,
				buttons: {
					"Fechar": function(){
						$(this).dialog("close");
					}
				},
				close: function(){
					//caso o usuário simplesmente feche a caixa de edição não deve acontecer nada
				}
		};
		$("#modal_visual").dialog(cfg);
	}
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
	$("#numero_venda").maskMoney({thousands: '', precision: 0 });
});

function getitens(novaPag, t){
	pagina = novaPag;
	if(t==undefined){t="b"}
	var url= "../../rest/PaoDoce/buscaVenda/"+numVenda+"/"+formaPag+"/"+data+"/"+ate+"/";
	var cfg = {
			type: "GET",
			url: url+pagina+"/"+t,
			success: function(lista){
				var valor = 0;
					
				lista.map(function(item) {
					valor = item["max"];
				});
				if(t=="g"){
					gerarPDF(lista);
				}else{
					
				SENAI.consulta_venda.exibirVenda(lista);

				numitens = valor;
				contador(url,pagina);
				}
			},
			error: function(err){
				alert("Erro ao consultar os produtos: " + err.responseText);
			}
	};
	SENAI.ajax.get(cfg);
}
function gerarPDF(lista){
	var cfg = {
			title: "Mensagem",
			height: 175,
			width: 400,
			modal: true,
			buttons: {
				"Cancelar": function(){
					$(this).dialog("close");
				},
				"Gerar": function(){
					var columns = [
					               {title: "Data de emissao", dataKey: "data_emissao"}, 
					               {title: "Data final", dataKey: "data_final"}, 
					               {title: "Forma de pagamento", dataKey: "forma_pag"}, 
					               {title: "Valor pago", dataKey: "resp"}, 
					               {title: "Caixa", dataKey: "nome"}, 
					           ];
					var ultimaLinha = {
							id:"",
							data_emissao:"",
							data_final:"",
							forma_pag:"Total: ",
							resp:lista[0].quantidade,
							nome:""
					};	               
					           lista.push(ultimaLinha);
					           var doc = new jsPDF('p', 'pt');
					           doc.text("Relatório de vendas", 225, 30);
					           doc.autoTable(columns, lista, {
					               styles: {
					            	   theme: 'striped',
					            	   fillColor:[221,221,221]
					               },
					               headerStyles: {fillColor:[183,28,28]},
					           });
					           doc.save('Relatorio de Vendas.pdf');

					$(this).dialog("close");
				},
			},
			close: function(){
				//caso o usuário simplesmente feche a caixa de edição não deve acontecer nada
			}
	};
	$("#modal_msg").dialog(cfg);
}
function constroiLista(lista){
	var html = "<div class='row'><div class='col-md-12'><table class='faturamento table'>";
	html += "<thead>" +
	"<tr>" +
	"<th scope='col'>ID</th>" +
	"<th scope='col'>Nome do produto</th>" +
	"<th scope='col'>Quantidade</th>" +
	"<th scope='col'>Unid. de Medida</th>" +
	"<th scope='col'>Preco</th>" +
	"</tr>" +
	"</thead>" +
	"<tbody>";
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
		"<td>"+lista[i].quantidade+"</td>"+
		"<td>"+unid+"</td>"+
		"<td>"+lista[i].preco+"</td>"+
		"</tr>";
	}
	html += "</tbody></table></div></div>";
	$("#modal_visual").html(html);
}
function pesquisar(){
	itensFiltro();
	getitens(1,'b');
}
function itensFiltro(){
	numVenda = $("#numVenda").val();
	formaPag = $("#formaPag").val();
	data = $("#data").val();
	ate = $("#ate").val();
	validaFiltro();
}
function validaFiltro(){
	if($("#numVenda").val()==''){
		numVenda = null;
	}
	if($("#formaPag").val()==''){
		formaPag = null;
	}
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

