
function paginador(cont){
	if(cont<=numitens){ //Verificando se há mais de uma página
		$('.pagination').html('<li><a href="#paginacao">1</a></li>');
	}else{
		$('.pagination').html('<li></li>');
		if(pagina!=1){ 
			$('.pagination li').append('<a href="#paginacao" aria-label="Previous" onclick="getitens('+(pagina-1)+')"><span aria-hidden="true">&laquo;</span></a>');
		}
		var qtdpaginas=Math.ceil(cont/numitens); //arredondando divisão fracionada para cima
		
		var ctrl = 2;
		var inicio = (((pagina - 2) > 1) ? pagina - 2 : 1);
		var fim = (((pagina + 2) > 1) ? pagina + 2 : 1);
		if(qtdpaginas>10){
			ctrl = 5;
			inicio = (((pagina - 5) > 1) ? pagina - 5 : 1);
			fim = (((pagina + 5) > 1) ? pagina + 5 : 1);
		}
		
		if(ctrl == 2){
			if(pagina>3 && qtdpaginas>5){
				$('.pagination li').append('<span aria-hidden="true">...</span>');
			}
		}else if(ctrl==5){
			if(pagina>6 && qtdpaginas>10){
				$('.pagination li').append('<span id="1" aria-hidden="true">...</span>');
			}
		}
		/*
		 * qtdpaginas <-- quantidade de páginas
		 * pagina <-- posição atual de página
		 * nt=5 <-- numero de páginas na paginação
		 */
		var html="";
			
			for(var i=inicio;i<=fim;i++){
				if(i<=qtdpaginas){
					if(pagina==i){
						html += '<span style="background-color:#b5b5b5;"><a href="#paginacao" onclick="getitens('+i+')">'+i+'</a></span>';
					}else{
						html += '<a href="#paginacao" onclick="getitens('+i+')">'+i+'</a>';
						}
				}
			}
		$('.pagination li').append(html);
		if(ctrl == 2){
			if(pagina<(qtdpaginas-2)){
				$('.pagination li').append('<span id="2" aria-hidden="true">...</span>');
			}
		}else if(ctrl==5){
			if(pagina<(qtdpaginas-5)){
				$('.pagination li').append('<span id="3" aria-hidden="true">...</span>');
			}
		}
		if(pagina!=qtdpaginas){
			$('.pagination li').append('<a href="#paginacao" aria-label="Next" onclick="getitens('+(pagina+1)+')"><span aria-hidden="true">&raquo;</span> </a>');
		}
	}
}

function contador(url,pagina){
	var cfg = {
      	type: 'GET',
      	url:url+pagina+"/"+"c",
   		success: function(retorno){
        	paginador(retorno);
      	}
    };
	SENAI.ajax.get(cfg);

}
