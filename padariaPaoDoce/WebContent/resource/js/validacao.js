var resposta = true;
function validacao(f){
	switch(f){
	case "p":
		produto();
		break;
	case "c":
		cargo();
		break;
	case "f":
		funcionario();
		break;
	case "v":
		vendas();
		break;
	}
	return resposta;
}
function produto(){
	resposta = true;
	var nome = document.getElementById("nome").value;
	var unidade = document.getElementById("unidade").value;
	var valor = document.getElementById("valor").value;
	
	if(nome == "" || unidade == "" || valor == ""){
		mensagem("Todos os campos são de preenchimento obrigatório","info");
		resposta = false;
	}
}
function cargo(){
	resposta = true;
	var funcao = document.getElementById("funcao").value;
	var valor = document.getElementById("valor").value;
	var permissao = document.getElementById("permissao").value;
	var descricao = document.getElementById("descricao").value;
	if(funcao == "" || valor == "" || permissao == ""){
		mensagem("Preencha os campos obrigatórios","info");
		resposta = false;
	}
}
function funcionario(){
	resposta = true;
	var nome = document.getElementById("nome").value;
	var email = document.getElementById("email").value;
	var cargo = document.getElementById("cargo").value;
	var telefone = document.getElementById("telefone").value;
	var data = document.getElementById("data").value;
	var cpf = document.getElementById("cpf").value;
	var usuario = document.getElementById("usuario").value;
	var senha = document.getElementById("senha").value;
	var confirmar = document.getElementById("confirmar").value;
    var reg = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
	if(email.indexOf("@")==-1||
			email.indexOf(".")==-1||
			email.indexOf("@")==0||
			email.lastIndexOf(".") + 1 == email.length||
			(email.indexOf("@") + 1 == email.indexOf("."))){
		mensagem("email incorreto","danger");
		$("email").focus();
		resposta = false;
	}else if(nome == "" || email == "" || cargo == "" || telefone == "" || usuario == "" || senha == ""){
	   
		mensagem("Todos os campos são de preenchimento obrigatório.","info");
		resposta = false;
			}else if(!reg.test(data)){
				mensagem("Digite uma data válida","info");
				resposta = false;
			}else if(document.getElementById("senha").value.length <5 || document.getElementById("senha").value.length>10){
				mensagem("A senha deve ter no mínimo 5 e no máximo 10 letras","info");
				resposta = false;
			}else if(senha != confirmar){
				mensagem("A senha deve ser igual ao confirmar senha.","info");
				resposta = false;
				}
}
function vendas(){
	resposta = true;
	var data2 = new Date(corrigeData($("#dataFinal").val()));
	var data1 = new Date(corrigeData($("#dataEmissao").val()));
	if(document.getElementById("table-produtos").rows.length==3){
		mensagem("Coloque pelo menos 1 item na lista.","danger");
		resposta = false;
	}else if($("#formaPag").val()==""){
		mensagem("Não esqueça da forma de pagamento.","info");
		resposta = false;
	}else if(data1 > data2){
		mensagem("A data de Emissão não pode ser menor que a data final.","danger");
		resposta = false;
	}
}
function corrigeData(data){
	var dia = data.substring(0,2);
	var mes = data.substring(3,5);
	var ano = data.substring(6,10);
	return mes+"-"+dia+"-"+ano;
}