var cont = 0;
var venda;
$(document).ready(function(){
	mudaGraficoVenda("30");
});
function fazRequisicaoVenda(dataAtual,mg){
	var cfg = {
			type: "GET",
			url: "../../rest/PaoDoce/grafico-venda/"+dataAtual+"/"+mg,
			success: function(lista){
				var o = criaGrafico(lista, "venda","Número de vendas", "forma_pag","resp");
				if(cont!=0){
					venda.destroy();
				}
				venda = new Chart(o.ctx, {
					type: 'bar',
					data: o.data,
					options: o.options
				});
				cont++;
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

function mudaGraficoVenda(tipo){
	$("#legendaVenda").html("Venda - "+tipo+" dias");
	var data = new Date();
	data.setDate(data.getDate()-tipo);
	var dia = data.getDate();
	var ano = data.getFullYear();
	var mes = data.getMonth()+1;
	fazRequisicaoVenda(dataAtual(),ano+"-"+mes+"-"+dia);
}

