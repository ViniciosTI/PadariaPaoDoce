var myChart;
function criaGrafico(data, local, ylabel, campo1,campo2){
	
	var gradientChartOptionsConfigurationWithTooltipPurple = {
			maintainAspectRatio: false,
			legend: {
				display: false
			},

			tooltips: {
				backgroundColor: '#f5f5f5',
				titleFontColor: '#333',
				bodyFontColor: '#666',
			},
			responsive: true,
			scales: {
				yAxes: [{
					gridLines: {
						drawBorder: false,
						color: 'rgba(255,255,255,0.5)',
						zeroLineColor: "rgba(255,255,255,0.5)",
					},
					ticks: {
						fontColor: "rgba(255,255,255,1)",
					}
				}],

				xAxes: [{
					gridLines: {
						drawBorder: true,
						color: 'rgba(255,255,255,1)',
						zeroLineColor: "rgba(255,255,255,0.5)",
					},
					ticks: {
						fontColor: "rgba(255,255,255,0.9)",
			              fontFamily: "Helvetica"
					}
				}]
			},

	};

	var ctx = document.getElementById(local).getContext("2d");
	var width = window.innerWidth || document.body.clientWidth;
	 var labels = data.map(function(item) {
		 return item[campo1];
	 });
	 var valor = data.map(function(item) {
		 return item[campo2];
	 });

	// 'rgba(183, 28, 28, 0.5)' VERMELHO
	var data = {
			labels: labels,
			datasets: [{
				label:ylabel,
				backgroundColor: "rgba(183, 28, 28, 0.5)", 
				borderWidth: 2,
				borderColor: 'rgba(109, 53, 26, 1)',
				pointBackgroundColor: 'rgba(183, 28, 28, 1)',
				pointHoverBackgroundColor: 'rgba(183, 28, 28, 1)',
				pointRadius: 4,
				data: valor,
			}]
	};
	var o = new Object();
	o.ctx = ctx;
	o.data = data;
	o.options = gradientChartOptionsConfigurationWithTooltipPurple;
	return o;
}