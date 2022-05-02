biblioteca.dashboard = function(){

	var initDashboardPageCharts = function() {

	    gradientChartOptionsConfigurationWithTooltipPurple = {
	      maintainAspectRatio: false,
	      legend: {
	        display: false
	      },

	      tooltips: {
	        backgroundColor: '#f5f5f5',
	        titleFontColor: '#333',
	        bodyFontColor: '#666',
	        bodySpacing: 4,
	        xPadding: 12,
	        mode: "nearest",
	        intersect: 0,
	        position: "nearest"
	      },
	      responsive: true,
	      scales: {
	        yAxes: [{
	          barPercentage: 1.6,
	          gridLines: {
	            drawBorder: false,
	            color: 'rgba(29,140,248,0.0)',
	            zeroLineColor: "transparent",
	          },
	          ticks: {
	            suggestedMin: 60,
	            suggestedMax: 125,
	            padding: 20,
	            fontColor: "#9a9a9a"
	          }
	        }],

	        xAxes: [{
	          barPercentage: 1.6,
	          gridLines: {
	            drawBorder: false,
	            color: 'rgba(225,78,202,0.1)',
	            zeroLineColor: "transparent",
	          },
	          ticks: {
	            padding: 20,
	            fontColor: "#9a9a9a"
	          }
	        }]
	      }
	    };

	    gradientChartOptionsConfigurationWithTooltipGreen = {
	      maintainAspectRatio: false,
	      legend: {
	        display: false
	      },

	      tooltips: {
	        backgroundColor: '#f5f5f5',
	        titleFontColor: '#333',
	        bodyFontColor: '#666',
	        bodySpacing: 4,
	        xPadding: 12,
	        mode: "nearest",
	        intersect: 0,
	        position: "nearest"
	      },
	      responsive: true,
	      scales: {
	        yAxes: [{
	          barPercentage: 1.6,
	          gridLines: {
	            drawBorder: false,
	            color: 'rgba(29,140,248,0.0)',
	            zeroLineColor: "transparent",
	          },
	          ticks: {
	            suggestedMin: 50,
	            suggestedMax: 125,
	            padding: 20,
	            fontColor: "#9e9e9e"
	          }
	        }],

	        xAxes: [{
	          barPercentage: 1.6,
	          gridLines: {
	            drawBorder: false,
	            color: 'rgba(0,242,195,0.1)',
	            zeroLineColor: "transparent",
	          },
	          ticks: {
	            padding: 20,
	            fontColor: "#9e9e9e"
	          }
	        }]
	      }
	    };



	    var ctx = document.getElementById("chartLinePurple").getContext("2d");

	    var gradientStroke = ctx.createLinearGradient(0, 230, 0, 50);

	    gradientStroke.addColorStop(1, 'rgba(72,72,176,0.2)');
	    gradientStroke.addColorStop(0.2, 'rgba(72,72,176,0.0)');
	    gradientStroke.addColorStop(0, 'rgba(119,52,169,0)'); //purple colors

	    var data = {
	      labels: ['JUL', 'AGO', 'SET', 'OUT', 'NOV', 'DEZ'],
	      datasets: [{
	        label: "Data",
	        fill: true,
	        backgroundColor: gradientStroke,
	        borderColor: '#d048b6',
	        borderWidth: 2,
	        borderDash: [],
	        borderDashOffset: 0.0,
	        pointBackgroundColor: '#d048b6',
	        pointBorderColor: 'rgba(255,255,255,0)',
	        pointHoverBackgroundColor: '#d048b6',
	        pointBorderWidth: 20,
	        pointHoverRadius: 4,
	        pointHoverBorderWidth: 15,
	        pointRadius: 4,
	        data: [80, 100, 70, 80, 120, 80],
	      }]
	    };

	    var myChart = new Chart(ctx, {
	      type: 'line',
	      data: data,
	      options: gradientChartOptionsConfigurationWithTooltipPurple
	    });


	    var ctxGreen = document.getElementById("chartLineGreen").getContext("2d");

	    var gradientStroke = ctx.createLinearGradient(0, 230, 0, 50);

	    gradientStroke.addColorStop(1, 'rgba(66,134,121,0.15)');
	    gradientStroke.addColorStop(0.4, 'rgba(66,134,121,0.0)'); //green colors
	    gradientStroke.addColorStop(0, 'rgba(66,134,121,0)'); //green colors

	    var data = {
	      labels: ['JUL', 'AGO', 'SET', 'OUT', 'NOV'],
	      datasets: [{
	        label: "Dados",
	        fill: true,
	        backgroundColor: gradientStroke,
	        borderColor: '#00d6b4',
	        borderWidth: 2,
	        borderDash: [],
	        borderDashOffset: 0.0,
	        pointBackgroundColor: '#00d6b4',
	        pointBorderColor: 'rgba(255,255,255,0)',
	        pointHoverBackgroundColor: '#00d6b4',
	        pointBorderWidth: 20,
	        pointHoverRadius: 4,
	        pointHoverBorderWidth: 15,
	        pointRadius: 4,
	        data: [90, 27, 60, 12, 80],
	      }]
	    };

	    var myChart = new Chart(ctxGreen, {
	      type: 'line',
	      data: data,
	      options: gradientChartOptionsConfigurationWithTooltipGreen

	    });


	  };
	  
	
	  return {
		  initCharts: initDashboardPageCharts
	  }
	  
	
}();
