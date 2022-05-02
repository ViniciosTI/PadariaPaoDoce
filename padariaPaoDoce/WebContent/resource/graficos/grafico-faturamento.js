/*
 * -- MELHORIAS --
 * colocar os botões de pesquisa no gráfico por
 *  formas de pagamento vinculádos com a pesquisa por data
 */
var c = 0;
var faturamento;
$(document).ready(function(){
	mudaGrafico("30");
});
function fazRequisicaoFaturamento(dataAtual,mg){
	var cfg = {
			type: "GET",
			url: "../../rest/PaoDoce/grafico-faturamento/"+dataAtual+"/"+mg,
			success: function(lista){
				var o = criaGrafico(lista, "faturamento","R$", "data_final","quantidade");
				if(c!=0){
					faturamento.destroy();
				}
				faturamento = new Chart(o.ctx, {
					type: 'line',
					data: o.data,
					options: o.options
				});
				c++;
			},
			error: function(err){
				alert("Erro ao gerar gráfico: " + err.responseText);
			}
	};
	SENAI.ajax.get(cfg);
}
function dataAtual(){
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
		return ano+"-"+mes+"-"+dia;
}

function mudaGrafico(tipo){
	$("#legendaFaturamento").html("Faturamento - "+tipo+" dias");
	var data = new Date();
	data.setDate(data.getDate()-tipo);
	var dia = data.getDate();
	var ano = data.getFullYear();
	var mes = data.getMonth()+1;
	fazRequisicaoFaturamento(dataAtual(),ano+"-"+mes+"-"+dia);
}

