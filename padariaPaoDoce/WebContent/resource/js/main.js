SENAI = new Object();
$(document).ready(function(){
	var i = 1; 
	$("#menu-container").load("../home/menu.html", function(){
		var cfg = {
				type: "GET",
				url: "../../rest/PaoDoce/checagem",
				async: false,
				success: function(menu){
					if(menu=='1'){
						$("#menu-caixa").css("display","block !important");
						$("#menu-adm").remove();
						$("#home_caminho").attr('href','#');
					}else if(menu=='2'){
						$("#check-grafico").css('display','block');
						$("#menu-caixa").remove();
						$("#menu-adm").css("display","block !important");
					}else if(menu=="0"){
						window.location.assign("../home/bloqueio.html");
					}else if(menu==""){
						$("#menu-container").remove();
					}
				},
				error: function(err){
					alert("Erro ao checar usuário: " + err.responseText);
				}
		};
		SENAI.ajax.get(cfg);
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
		
	});		     

	$("#campo_pesquisa").load("../home/campo_pesquisa.html");

	$("#cadastro-toggle").click(function(){ 
		if (i==1) { 
			$(".registro").css("display","none");
			i=0;         
			$("#aberto").css("display","none");
			$("#fechado").css("display","inline-block");
		} else {  
			$(".registro").css("display","block");
			i=1;
			$("#aberto").css("display","inline-block");
			$("#fechado").css("display","none");
		}
	});

	$("#filtro").load("../produto/consulta_filtro.html", function(){
		$("#hidden").css("display","none");
		$("#avancado").click(function(){    
			limparCampos();
			mudaFiltro();
			if (i==1) { 
				i=0;
				$("#hidden").css("display","block");
				$("#view").css("display","none");
			} else {  
				$("#view").css("display","block");
				$("#hidden").css("display","none");
				i=1;
			}
			mascara();
		});
		
	});
	
}) ;
function mascara(){
	$(function() {
  		$('#preco').maskMoney({ 
  			decimal: ',',
  			thousands: '',
  			precision: 2 
  			});
  	});
	$(function() {
		$('#ate').maskMoney({ 
			decimal: ',',
			thousands: '',
			precision: 2 
		});
	});
}

function mensagem(mensagem, tipo){
	$.notifyClose();
	$.notify({
		// options
		message: mensagem 
	},{
		// settings
		type: tipo,
		position: null,
		allow_dismiss: true,
		offset: 2,
		placement: {
			from:"bottom",
			align:"center" 
		},
		z_index: 1031,
		delay: 3000,
	});
}
