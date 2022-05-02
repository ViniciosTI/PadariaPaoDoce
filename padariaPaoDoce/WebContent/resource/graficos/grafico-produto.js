var myChart;
$(document).ready(function(){
	var cfg = {
			type: "GET",
			url: "../../rest/PaoDoce/grafico-produto/"+dataAtual(),
			success: function(lista){
				var o = criaGrafico(lista, "produtos","R$", "nome","prec");
				myChart = new Chart(o.ctx, {
					type: 'line',
					data: o.data,
					options: o.options
				});
			},
			error: function(err){
				alert("Erro ao gerar gráfico: " + err.responseText);
			}
	};
	SENAI.ajax.get(cfg);
});
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
