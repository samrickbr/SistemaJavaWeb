<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"/>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuário</title>
<link rel="stylesheet" href="resources/css/tabela.css">
<!-- MAIN CSS -->

<link rel="stylesheet" href="resources/css/estilo/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/estilo/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/estilo/magnific-popup.css">
<link rel="stylesheet" href="resources/css/estilo/owl.theme.css">
<link rel="stylesheet" href="resources/css/estilo/owl.carousel.css">

<link rel="stylesheet" href="resources/css/estilo/tooplate-style.css">

<link rel="stylesheet" href="resources/css/tabela.css">

<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

</head>

<body>
	<c:import url="menu.jsp"></c:import>

	<!-- HOME -->
	<section id="home" class="parallax-section">
	<div class="overlay"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-1 col-md-10 col-sm-12">
				<div class="clearfix"></div>
				<div class="col-md-offset-1 col-md-10 col-sm-12">
					<!-- CONTACT FORM HERE -->

					<form action="salvarUsuario" method="POST" id="formUser"
						class="material-form" id="quoteForm"
						onsubmit="return validarCampos()? true:false;">
						<h2 style="color: white; font-weight: 200;">Dados de
							cadastro:</h2>

						<br />
						<div class="col-md-6 col-sm-6">
							<label for="id" class="label label-default">ID:</label> <input
								type="text" class="form-control" id="id" readonly="readonly"
								name="id" value="${user.id}">
						</div>
						<br /> <br /> <br />
						<div class="col-md-6 col-sm-6">
							<label for="nome" class="label label-default">NOME:</label> <input
								type="text" class="form-control" id="nome" name="nome"
								value="${user.nome}" required="required" placeholder="Nome">
						</div>

						<div class="col-md-6 col-sm-6">
							<label for="fone" class="label label-default">TELEFONE:</label> <input
								type="text" class="form-control" id="fone" name="fone"
								value="${user.fone}" placeholder="Telefone">
						</div>
						<br /> <br /> <br />
						<div class="col-md-6 col-sm-6">
							<label for="login" class="label label-default">LOGIN:</label> <input
								type="text" class="form-control" id="login" name="login"
								value="${user.login}" required="required" placeholder="Login">
						</div>

						<div class="col-md-6 col-sm-6">
							<label for="senha" class="label label-default">SENHA:</label> <input
								type="password" class="form-control" id="senha" name="senha"
								value="${user.senha}" required="required" placeholder="Senha">
						</div>
						<br /> <br /> <br />
						<div class="col-md-6 col-sm-6">
							<label for="cep" class="label label-default">CEP:</label> <input
								type="text" class="form-control" id="cep" name="cep"
								value="${user.cep}" placeholder="Cep" onblur="consultaCep();">
						</div>
						<div class="col-md-6 col-sm-6">
							<label for="rua" class="label label-default">RUA:</label> <input
								title="text" class="form-control" id="rua" name="rua"
								value="${user.rua}" placeholder="Rua">
						</div>
						<br /> <br /> <br />
						<div class="col-md-6 col-sm-6">
							<label for="bairro" class="label label-default">Bairro:</label> <input
								type="text" class="form-control" id="bairro" name="bairro"
								value="${user.bairro}" placeholder="Bairro">
						</div>
						<div class="col-md-6 col-sm-6">
							<label for="cidade" class="label label-default">CIDADE:</label> <input
								title="text" class="form-control" id="cidade" name="cidade"
								value="${user.cidade}" placeholder="Cidade">
						</div>
						<br /> <br /> <br />
						<div class="col-md-6 col-sm-6">
							<label for="uf" class="label label-default">ESTADO:</label> <input
								title="text" class="form-control" id="uf" name="uf"
								value="${user.uf}" placeholder="Estado">
						</div>
						<br /> <br /> <br />

						<pre
							style="color: orange; background-color: transparent; border: thin; font-size: medium;">${msg }</pre>

						<div class="col-md-6 col-sm-6">
							<div class="section-btn">
								<button type="submit" class="form-control" id="salvar"
									name="salvar">
									<span data-hover="Salvar" style="width: 100%">Salvar</span>
								</button>
							</div>
						</div>
						<div class="col-md-6 col-sm-6">
							<div class="section-btn">
								<button type="submit" class="form-control" id="cancelar"
									name="cancelar"
									onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'">
									<span data-hover="Cancelar" style="width: 100%">Cancelar</span>
								</button>
							</div>
						</div>
						<br /> <br /> <br /> <br /> <br />
						<hr>

						<div class=" col-md-12 col-sm-12">
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
										<tr>
											<th style="text-align: center;">ID</th>
											<th style="text-align: center;">NOME</th>
											<th style="text-align: center;">TELEFONE</th>
											<th style="text-align: center;">LOGIN</th>
											<th colspan="2" style="text-align: center;">OPÇÕES</th>
										</tr>
									</thead>
									<c:forEach items="${usuarios}" var="user">
										<tr align="center">
											<td style="width: 50px"><c:out value="${user.id}"></c:out></td>
											<td style="width: 100px"><c:out value="${user.nome}"></c:out></td>
											<td style="width: 100px"><c:out value="${user.fone}"></c:out></td>
											<td style="width: 100px"><c:out value="${user.login}"></c:out></td>

											<td style="width: 50px"><a
												href="salvarUsuario?acao=delete&user=${user.id}"><img
													width="20px" height="20px" alt="Excluir" title="Excluir"
													src="resources/img/excluir.png"></a></td>
											<td style="width: 50px"><a
												href="salvarUsuario?acao=editar&user=${user.id}"><img
													width="20px" height="20px" alt="Editar" title="Editar"
													src="resources/img/editar.png"> </a></td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</section>

	<div>
		<script type="text/javascript">
			function validarCampos() {
				if (document.getElementById("login").value == '') {
					alert("O campo \"LOGIN\" não pode ser vazio.\n");
					return false;
				} else if (document.getElementById("senha").value == '') {
					alert("O campo \"SENHA\" não pode ser vazio.\n");
					return false;
				} else if (document.getElementById("nome").value == '') {
					alert("O campo \"NOME\" não pode ser vazio.\n");
					return false;
				}
				return true;
			}

			//------------------------------------------------------------
			
			/*
			*Consulta de CEP através do webservice viacep.com.br
			*/
			function limpa_formulário_cep() {
				// Limpa valores do formulário de cep.
				$("#cep").val("");
				$("#rua").val("");
				$("#bairro").val("");
				$("#cidade").val("");
				$("#uf").val("");
				//$("#ibge").val("");
			}

			//------------------------------------------------------------
			function consultaCep() {
				var cep = $("#cep").val();

				//Verifica se campo cep possui valor informado.
				if (cep != "") {

					//Expressão regular para validar o CEP.
					var validacep = /^[0-9]{8}$/;

					//Valida o formato do CEP.
					if (validacep.test(cep)) {
						//Preenche os campos com "..." enquanto consulta webservice.
						$("#rua").val("...");
						$("#bairro").val("...");
						$("#cidade").val("...");
						$("#uf").val("...");
						//$("#ibge").val("...");

						//Consulta o webservice viacep.com.br/
						$.getJSON("https://viacep.com.br/ws/" + cep
								+ "/json/?callback=?", function(dados) {

							if (!("erro" in dados)) {
								//Atualiza os campos com os valores da consulta.
								$("#rua").val(dados.logradouro);
								$("#bairro").val(dados.bairro);
								$("#cidade").val(dados.localidade);
								$("#uf").val(dados.uf);
								// $("#ibge").val(dados.ibge);
							} //end if.
							else {
								//CEP pesquisado não foi encontrado.
								limpa_formulário_cep();
								alert("CEP não encontrado.");
							}
						});
					} else {
						//cep é inválido.
						limpa_formulário_cep();
						alert("Formato de CEP inválido.");
						$("#cep").val("");
					}
				} else {
					//cep sem valor, limpa formulário.
					limpa_formulário_cep();
				}
			}
		</script>
	</div>

</body>
</html>