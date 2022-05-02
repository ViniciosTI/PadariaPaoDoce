/*Criação do objeto*/
SENAI.funcionario = new Object();

var f = "f";
var cargo;
var pagina=1;
var numitens=0;
$(document).ready(function(){
	var url="";
	var o = new Object();
	SENAI.funcionario.validacao = function(){
		
		if(validacao(f)){
			// objeto que levará os dados para o lado back-end
			
			o.nome = document.getElementById("nome").value;
			o.idcargos = document.getElementById("cargo").value;
			o.cpf = document.getElementById("cpf").value;
			o.telefone = document.getElementById("telefone").value;
			o.data_nasc = document.getElementById("data").value;
			o.email = document.getElementById("email").value;
			o.usuario = document.getElementById("usuario").value;
			o.senha = document.getElementById("senha").value;
			
			url = "../../rest/PaoDoce/addFuncionario"; 
			// caminho do arquivo back-end 
			
			SENAI.acao.cadastrar(o,url,f);
		}
	}
	
	
	SENAI.funcionario.exibirFuncionario = function(lista, valor){
		var html = "<div class='row'><div class='col-md-12'><table class='table'>";
		html += "<thead>" +
					"<tr>" +
						"<th scope='col' width='10%'>Nome</th>" +
						"<th scope='col' width='10%'>E-mail</th>" +
						"<th scope='col' width='5%'>Telefone</th>" +
						"<th scope='col' width='5%'>CPF</th>" +
						"<th scope='col' width='1%'>Data de nascimento</th>" +
						"<th scope='col' width='1%'>Usuário</th>" +
						"<th scope='col' width='1%'>Cargo</th>" +
						"<th scope='col' width='5%'>Ações</th>" +
					"</tr>" +
				"</thead>" +
				"<tbody>";
		if(lista != undefined && lista.length > 0){
			for(var i=0; i<lista.length; i++){
				html += "<tr style='font-size:12px;'>" +
							"<td scope='row'>"+lista[i].nome+"</td>"+
							"<td>"+lista[i].email+"</td>"+
							"<td>"+lista[i].telefone+"</td>"+
							"<td>"+lista[i].cpf+"</td>"+
							"<td>"+lista[i].data_nasc+"</td>"+
							"<td>"+lista[i].usuario+"</td>"+
							"<td>"+lista[i].nome_cargo+"</td>"+
							"<td align='center' class='f'>" +
							"<a class='link' title='Editar' onclick='SENAI.funcionario.edit("+formataCpf(lista[i].cpf)+")'>"+
								"<span class='glyphicon glyphicon-edit'> </span>" +
							"</a>"+
							"<a class='link' title='Deletar' onclick='SENAI.funcionario.del("+formataCpf(lista[i].cpf)+")'>"+
								"<span class='glyphicon glyphicon-remove'> </span>" +
							"</a>" +
						"</td>" +
						"</tr>";
			}
			
		}else{
			if(lista == undefined){
				if(valor == ""){
					valor = null;
				}
				
				getitens(pagina,valor);
			}else{
				html += "<tr>" +
							"<td scope='row' colspan='10' align='center'> Nenhum registro encontrado</td>" +
						"</tr>";
			}
		}
		html += "</tbody></table></div></div>";
		$("#resultados").html(html);
	}
	
	SENAI.funcionario.exibirFuncionario(undefined,"");
	
	SENAI.funcionario.del = function(i){
		url = "../../rest/PaoDoce/delFuncionario/"+i;
		SENAI.acao.deletar(url,f);
	};
	
	SENAI.funcionario.edit = function(i){
		var c = "../funcionario/edit_funcionario.html";
		var get_url="../../rest/PaoDoce/buscarfuncPorId/"+i;
		url = "../../rest/PaoDoce/editFuncionario";
		SENAI.acao.editar(url,get_url,c,f);
	}

		var cfg = {
				type: "GET",
				url: "../../rest/PaoDoce/campoCargo",
				success: function(lista){
					cargo = "<option value=''>Selectione...</option>"
					for(var i=0; i<lista.length; i++){		
						cargo += "<option value='"+lista[i].id+"'>"+lista[i].nome+"</option>";
					}
					$("#cargo").html(cargo);
				},
				error: function(err){
					alert("Erro ao buscar os cargos: " + err.responseText);
				}
		};
		SENAI.ajax.get(cfg);
		 $(function(){
		        $('#data').datepicker({
		          changeYear: true,
		          changeMonth: true,
		          yearRange: "-100:-18",
		  		  defaultDate:"01/01/2000"

		        });
		      })
		   
});

function getitens(novaPag,valor){
	pagina = novaPag;
	if(valor==undefined){
		valor = null;
	}
	var url="../../rest/PaoDoce/buscarFuncPorNome/"+valor+"/"+pagina;
	var cfg = {
			type: "GET",
			url: url+"/"+"b",
			success: function(lista){
				SENAI.funcionario.exibirFuncionario(lista);
				var valor = 0;
				 lista.map(function(item) {
					 valor = item["max"];
				 });

				numitens = valor;
				contador(url,pagina);
			},
			error: function(err){
				alert("Erro ao consultar os produtos: " + err.responseText);
			}
	};
	SENAI.ajax.get(cfg);
}
function eventosEdit(){
	$("#cargoEdit").html(cargo);
	$("input[name='cpfEdit']").mask('000.000.000-00');
	$("input[name='telefoneEdit']").mask("(00) 0000-00000");
	$("#dataEdit").mask("99/99/9999", {
		completed: function () {
			console.log('complete')
			var value = $(this).val().split('/');
			var maximos = [31, 12, 9999];
			var novoValor = value.map(function (parcela, i) {
				if (parseInt(parcela, 10) > maximos[i]) return maximos[i];
				return parcela;
			});
			if (novoValor.toString() != value.toString()) $(this).val(novoValor.join('/')).focus();
		}
	});
	$(function(){
		$('#dataEdit').datepicker({
			beforeShow:function(input) {
		        $(input).css({
		            "position": "relative",
		            "z-index": 999999
		        });
		    },
			changeYear: true,
			changeMonth: true,
			yearRange: "-100:-18",
			defaultDate:"01/01/2000"
		});			
	})

}
function pesquisar(){
	pagina=1;
	SENAI.acao.busca(f);
}
function formataCpf(cpf){
	var bloco1 = cpf.substring(0, 3);
	var bloco2 = cpf.substring(4, 7);
	var bloco3 = cpf.substring(8, 11);
	var bloco4 = cpf.substring(12, 14);
	cpf = bloco1 + bloco2 + bloco3 + bloco4;
	return cpf;
}
function setarDadosNoModal(o){
	$("#nomeEdit").val(o[0].nome);
	$("#cargoEdit").val(o[0].idcargos);		
	$("#cpfEdit").val(o[0].cpf);
	$("#telefoneEdit").val(o[0].telefone);
	$("#dataEdit").val(o[0].data_nasc);
	$("#emailEdit").val(o[0].email);
	$("#usuarioEdit").val(o[0].usuario);
	$("#idfuncionario").val(o[0].cpf);
	return o;
}
function setarDados(o){
	o.nome = $("#nomeEdit").val();
	o.idcargos = $("#cargoEdit").val();
	o.cpf = $("#cpfEdit").val();
	o.telefone = $("#telefoneEdit").val();
	o.data_nasc = $("#dataEdit").val();
	o.email = $("#emailEdit").val();
	o.usuario = $("#usuarioEdit").val();
	o.funcionarios_cpf = $("#idfuncionario").val();
	return o;
}
