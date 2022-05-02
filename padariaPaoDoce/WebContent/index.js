var id = "";
var o='';
function cript(){
	var senha = btoa(document.getElementById("senha").value);
	document.getElementById("crip").value = senha;

}
function esqueciSenha(){
	var cfg = {
			title: "Mensagem",
			height: 400,
			width: 525,
			modal: true,
			buttons: {
				"Cancelar": function(){
					$( this ).dialog("close");
				},
				"Enviar": function(){
					o = "usuario="+$("#usuarioEsq").val()+
					"&email="+$("#emailEsq").val()
					;
					requisicao(o);
				}
			}
	};
	$("#esqueci").dialog(cfg);
	$("#usuarioEsq").val("");
	$("#emailEsq").val("");
}
function requisicao(o){
	$.ajax({
		type: "POST",
		url: "EsqueciSenha",
		data: o,
		success: function (msg){
			if(msg.msg!="b"){
				alert(msg.msg);
			}else{
				id=msg.id;
				senhaNova();
				$("#esqueci").dialog("close");
			}
		},

		error: function (msg){
			alert("Erro ao reaver a senha.");
		}
	});
}

function senhaNova(){

		var cfg = {
				title: "Mensagem",
				height: 400,
				width: 525,
				modal: true,
				buttons: {
					"Cancelar": function(){
						$( this ).dialog("close");
					},
					"Enviar": function(){
						if(validacao()){
						o = "senha="+$("#senhaNova").val()+
						"&id="+id;
						requisicao(o);
						$( this ).dialog("close");
						}
					}
				}
		};
		$("#novaSenha").dialog(cfg);
		$("#senhaNova").val("");
		$("#confirmar").val("");
	}

function validacao(){
var resposta = true;
 if(document.getElementById("senhaNova").value.length <5 || document.getElementById("senhaNova").value.length>10){
	alert("A senha deve ter no mínimo 5 e no máximo 10 letras","info");
	resposta = false;
}else if($("#senhaNova").val() != $("#confirmar").val()){
	alert("A senha deve ser igual ao confirmar senha.","info");
	resposta = false;
	}
 return resposta;
}