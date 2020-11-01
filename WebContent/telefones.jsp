<!DOCTYPE html >

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Telefones</title>
<link rel="stylesheet" href="resources/css/tabela.css">
<!-- MAIN CSS -->

<link rel="stylesheet" href="resources/css/estilo/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/estilo/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/estilo/magnific-popup.css">
<link rel="stylesheet" href="resources/css/estilo/owl.theme.css">
<link rel="stylesheet" href="resources/css/estilo/owl.carousel.css">

<link rel="stylesheet" href="resources/css/estilo/tooplate-style.css">

<link rel="stylesheet" href="resources/css/tabela.css">

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

					<form action="salvarTelefones" method="POST" id="formTelefone"
						class="material-form" id="quoteForm"
						onsubmit="return validarCampos()? true:false;">
						<h2 style="color: white; font-weight: 200;">Telefones
							cadastrados para o usuario:</h2>

						<br />
						<div class="col-md-6 col-sm-6">
							<label for="id" class="label label-default">ID:</label> <input
								type="text" class="form-control" id="id" readonly="readonly"
								name="id" value="${userEscolhido.id}" class="field">
						</div>

						<div class="col-md-6 col-sm-6">
							<label for="nome" class="label label-default">NOME:</label> <input
								type="text" class="form-control" id="nome" readonly="readonly"
								name="nome" value="${userEscolhido.nome}" class="field">
						</div>
						<br /> <br /> <br />
						<div class="col-md-6 col-sm-6">
							<label for="numero" class="label label-default">TELEFONE:</label>
							<input type="text" class="form-control" id="numero" name="numero"
								class="field" onblur="validarfone();"
								placeholder="TELEFONE (Insira somente números)">
						</div>

						<div class="col-md-6 col-sm-6">
							<label for="tipo" class="label label-default">TIPO:</label> <select
								name="tipo" id="tipo" class="form-control">
								<option>CELULAR</option>
								<option>CASA</option>
								<option>TRABALHO</option>
								<option>CONTATO</option>
							</select>
						</div>
						<br /> <br /> <br />

						<pre
							style="color: orange; background-color: transparent; border: thin; font-size: medium;">${msg}</pre>

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
								<button type="submit" class="form-control" id="voltar"
									name="voltar"
									onclick="document.getElementById('formTelefone').action='salvarUsuario?acao=reset'">
									<span data-hover="Voltar" style="width: 100%">Voltar</span>
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
											<th style="text-align: center;">TELEFONE</th>
											<th style="text-align: center;">TIPO</th>
											<th colspan="3" style="text-align: center;">OPÇÕES</th>
										</tr>
									</thead>
									<c:forEach items="${telefones}" var="fone">
										<tr align="center">
											<td style="width: 50px"><c:out value="${fone.id}"></c:out></td>
											<td style="width: 100px"><c:out value="${fone.numero}"></c:out></td>
											<td style="width: 100px"><c:out value="${fone.tipo}"></c:out></td>

											<td style="width: 50px"><a
												href="salvarTelefones?acao=deleteFone&foneId=${fone.id}"><img
													width="20px" height="20px" alt="Excluir" title="Excluir"
													src="resources/img/excluir.png"></a></td>

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

				if (document.getElementById("numero").value == '') {
					alert("O campo \"TELEFONE\" não pode ser vazio.\n");
					return false;
				}
				return true;
			}

			//------------------------------------------------------------

			//Verifica se campo número possui valor informado.
			function validarfone() {
				var fone = $("#numero").val();
				if (fone != "") {

					//Expressão regular para validar o número.
					var validafone = /^[0-9]{11}$/;

					//Valida o formato do número.
					if (validafone.test(fone)) {
						if (("erro" in dados)) {
							msg = "Erro no número inserido.";
							//fone é inválido.
							limpa_formulário_fone();
							alert("Formato de Telefone inválido.");
							$("#numero").val("");
						}
					} else {
						//número sem valor, limpa formulário.
						limpa_formulário_fone();
					}
				}
			}
		</script>
	</div>

</body>
</html>