var precoTotal;
var preco;
var quantidade;
var produtoLista;
var i = "";
var produtos ={};
var unid = "";
var idvenda=0;
var c=0;
SENAI.venda = new Object();
function limpaTabela(){
	$("tbody").find("tr").remove();
	$("#b").attr("style","");
	$("#valorTotal").val("");
	$("#quantidade").val("");
	$("#formaPag").val("");
	$('#seleciona_produto').val(null).trigger('change');
	$("#quantidade").prop("disabled", true);
	pegaIdVenda();
	pegaDataInicial();
	produtos ={};
}
function removeItem(linha,el){
	var tr = el;
	while (tr.nodeName != "TR" && tr.parentNode) {
		tr = tr.parentNode;
	}
	delete produtos[linha];
	tr.parentNode.removeChild(tr);
	if(document.getElementById("table-produtos").rows.length==3){
		$("#b").attr("style","");
	}
	valorPago();
}
function itemNovo(){

	var novoProduto = {
			idprodutos:produtoLista["id"],
			nome:produtoLista["nome"],
			quantidade:quantidade,
			preco:produtoLista.preco,
			unid_medida:produtoLista["unid_medida"]
	};
	if($("#valorTotal").val()==""){
		$("#valorTotal").val("0,00");
	}
	parseFloat(preco);
	var linhaNova="";
	produtos[c] = novoProduto;
	linhaNova =
		"<tr>" +
		"<td>"+produtoLista["id"]+"</td>" +
		"<td>"+produtoLista["nome"]+"</td>" +
		"<td id='"+produtoLista["id"]+"'>"+quantidade+"</td>" +
		"<td>"+unid+"</td>" +
		"<td>"+produtoLista.preco.replace(".",",")+"</td>" +
		"<td>"+preco.toFixed(2).replace(".",",")+"</td>" +
		"<td><a class='link' onclick='removeItem(\""+c+"\",this)'><span class='glyphicon glyphicon-remove'> </span></a></td>" +
		"</tr>";	
	c++;
	valorPago();
	return linhaNova;
}
$(document).ready(function(){
	SENAI.venda.listaProdutos = function(){
		if(arrumaQuantidade($("#quantidade").val())!=""){
			quantidade = arrumaQuantidade($("#quantidade").val());
			preco = valorProdLista(quantidade);
			switch(produtoLista["unid_medida"]){
			case "0":
				unid = "Ud";
				break;
			case "1":
				unid = "Kg";
				break;
			}
			SENAI.venda.colocaLinha();
		}else{
			mensagem("Coloque um produto e a quantidade.","info");
		}
	};

	SENAI.venda.colocaLinha = function(){
		document.getElementById('b').style.display = 'none';
		$("tbody").html($("tbody").html()+itemNovo());
	};

	$('#seleciona_produto').select2({
		language: 'pt-BR',
		placeholder: "Digite o código ou nome do produto...",
		minimumInputLength: 1,
		ajax: {  
			type:"GET",
			url: function(params){
				return "../../rest/PaoDoce/buscarProduto/"+params.term+"/"+"0";
			},
			delay: 250,
			quietMillis: 250,//intervalo entre os botoes, 100ms que é o default fica pouco
			dataType: 'json',
			data:null,
			processResults: function (data) {
				return {
					results: data
				};
			},
		},
		templateResult:format,
		templateSelection: format,
		escapeMarkup: function (m) { return m; },
	});
	$('#seleciona_produto').on('select2:select', function (e) {
		var data = e.params.data;
	});

	$(function(){
		$('#dataEmissao').datepicker({
			changeYear: true,
			changeMonth: true,
			yearRange: "-5:+5",
			defaultDate: new Date()
		});
	})
	$(function(){
		$('#dataFinal').datepicker({
			changeYear: true,
			changeMonth: true,
			yearRange: "-5:+5",
			defaultDate: new Date()
		});
	})
	SENAI.venda.realizarVenda = function(){
		var cfg = {
				title: "Editar",
				height: 180,
				width: 420,
				modal: true,
				buttons: {
					"Cancelar": function(){
						resposta = false;
						$(this).dialog("close");
					},
					"Finalizar": function(){

						if(validacao("v")){
							var o = new Object();
							o.idvendas = idvenda;
							o.data_emissao = $("#dataEmissao").val();
							o.data_final = $("#dataFinal").val();
							o.forma_pag = $("#formaPag").val();
							var aux = [];
							var repitidos = produtoRepitido();
							
							for (var p in repitidos) {
								aux.push(repitidos[p]);
							}
							o.produtos = aux;

							var url="../../rest/PaoDoce/realizaVenda";
							SENAI.acao.cadastrar(o,url,'',function(msg,p){
								if(p){
									if(!msg.substring(0, msg.indexOf("#"))){
										limpaTabela();
									}
								}else{
									pegaIdVenda();
									pegaDataInicial();
								}
							});
						}
						$(this).dialog("close");
					},

				},
				close: function(){
					resposta = false;
				}
		};
		$("#modal").dialog(cfg);

	}
	$("#quantidade").prop("disabled", true);
	mensagem("Comece realizando uma venda! ▲","info");
	pegaDataInicial();
	pegaIdVenda();
});
function produtoRepitido(){
	var ctrl = false;
	var novaLista = {};
	for(var p in produtos){
		ctrl = false;
		for(var g in novaLista){
			if(novaLista[g] && novaLista[g].idprodutos==produtos[p].idprodutos){
				var calc = parseFloat(novaLista[g].quantidade) + parseFloat(produtos[p].quantidade);
				novaLista[g].quantidade = calc;
				ctrl = true;
//				idprodutos:produtoLista["id"],
//				nome:produtoLista["nome"],
//				quantidade:quantidade,
//				preco:produtoLista.preco,
//				unid_medida:produtoLista["unid_medida"]
			}
		}
		if(ctrl==false){
			novaLista[p]=produtos[p];
		}
	}
	return novaLista;
}

function valorPago(){
	var valorAtual=0;
	var valorp=0;
	for (var p in produtos) {
		valorp = parseFloat(produtos[p].preco*produtos[p].quantidade).toFixed(2);
		valorAtual+=parseFloat(valorp);
	}
	if(valorAtual!=0){
		$("#valorTotal").val(valorAtual.toFixed(2).replace(".",","));		
	}else{
		$("#valorTotal").val("");		
	}
}
function pegaDataInicial(){
	var data = new Date();
	var dia = data.getDate();
	var ano = data.getFullYear();
	var mes = data.getMonth()+1;
	if(dia.toString().length == 1){
		dia = "0"+dia;
	}
	if(mes.toString().length == 1){
		mes = "0"+mes;
	}
	$("#dataEmissao").val(dia+"/"+mes+"/"+ano);
	$("#dataFinal").val(dia+"/"+mes+"/"+ano);
}
function pegaIdVenda(){
	var cfg = {
			type: "POST",
			url: "../../rest/PaoDoce/numeroVenda",
			success: function(id){
				$("#numeroVenda").val(id);
				idvenda=id;
			},
			error: function(err){
				alert("Erro ao buscar o numero da venda: " + err.responseText);
			}
	};
	SENAI.ajax.get(cfg);
}

function format(texto){
	if(texto.text){
		return texto.text;
	}
	switch(texto.unid_medida){
	case "0":
		i = "Ud";
		break;
	case "1":
		i = "Kg";
		break;
	}
	produtoLista = {
			id:texto.id,
			nome:texto.nome,
			unid_medida:texto.unid_medida,
			preco:texto.preco
	};

	preparaCampoQuantidade();
	return texto.id+" - "+texto.nome;
}
function arrumaQuantidade(valor){
	return valor.substring(0, valor.indexOf(" "));
}

function preparaCampoQuantidade(){
	$("#quantidade").prop("disabled", false);
	$("#quantidade").val("");
	if(i=="Ud"){
		$("#quantidade").maskMoney({suffix: " "+i,thousands: '', precision: 0 });
		$("#quantidade").attr('maxlength','6');
	}else if("Kg"){
		$("#quantidade").maskMoney({suffix: " "+i,thousands: '', decimal: '.', precision: 2 });
		$("#quantidade").attr('maxlength','8');	
	}

}

function valorProdLista(quantidade){
	return produtoLista.preco*quantidade;
}
