<!DOCTYPE html >
<%@page import="beans.BeanProduto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Produto</title>

<script src="resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="resources/javascript/jquery.maskMoney.min.js"
	type="text/javascript"></script>

<link rel="stylesheet" href="resources/css/tabela.css">
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
	<section id="home" class="parallax-section">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-offset-1 col-md-10 col-sm-12">
					<div class="clearfix"></div>
					<div class="col-md-offset-1 col-md-10 col-sm-12">
						<!-- CONTACT FORM HERE -->

						<form action="salvarProduto" method="post" id="formProduto"
							class="material-form" id="quoteForm">
							<h2 style="color: white; font-weight: 200;">Dados de
								cadastro:</h2>

							<br />
							<div class="col-md-6 col-sm-6">
								<label for="id" class="label label-default">ID:</label> <input
									type="text" class="form-control" id="id" readonly="readonly"
									name="id" value="${prod.id} ">
							</div>
							<div class="col-md-6 col-sm-6">
								<label for="ativo" class="label label-default">ATIVO:</label> <br>
								<label class="btn btn-outline-primary; col-md-6 col-sm-6"
									for="ativo"
									style="background-color: window; font: bold; color: black;">
									<input type="checkbox" class="btn-check" id="ativo"
									name="ativo"
									<%if (request.getAttribute("prod") != null) {
	BeanProduto produtos = (BeanProduto) request.getAttribute("prod");
	if (produtos.isAtivo()) {
		out.print(" ");
		out.print("checked=\"checked\"");
		out.print(" ");
	}
}%>>
									CADASTRO ATIVO
								</label>
							</div>

							<br /> <br /> <br />
							<div class="col-md-6 col-sm-6">
								<label for="nome" class="label label-default">NOME:</label> <input
									type="text" class="form-control" id="nome" name="nome"
									maxlength="120" value="${prod.nome}" placeholder="Nome">
							</div>

							<div class="col-md-6 col-sm-6">
								<label for="barras" class="label label-default">CÓDIGO:</label>
								<input type="text" class="form-control" id="barras"
									name="barras" maxlength="15" value="${prod.barras} "
									required="required" placeholder="Código">
							</div>
							<br /> <br /> <br />
							<div class="col-md-6 col-sm-6">
								<label for="preco" class="label label-default">PREÇO:</label>
								<!--  -->
								<input type="text" id="preco" class="form-control" name="preco"
									maxlength="20" value="${prod.valorTexto}" required="required"
									placeholder="Preço">
							</div>

							<div class="col-md-6 col-sm-6">
								<label for="estoque" class="label label-default">ESTOQUE:</label>
								<input type="text" class="form-control" id="estoque"
									maxlength="10" name="estoque" data-precision="3"
									value="${prod.estoqueTexto}" required="required"
									placeholder="Estoque">
							</div>
							<br /> <br />
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
										onclick="document.getElementById('formProduto').action = 'salvarProduto?acao=reset'">
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
												<th style="text-align: center;">CÓDIGO</th>
												<th style="text-align: center;">PREÇO</th>
												<th style="text-align: center;">ESTOQUE</th>
												<th colspan="2" style="text-align: center;">OPÇÕES</th>
											</tr>
										</thead>
										<c:forEach items="${produtos}" var="prod">
											<tr align="center">
												<td style="width: 50px"><c:out value="${prod.id}"></c:out></td>
												<td style="width: 100px"><c:out value="${prod.nome}"></c:out></td>
												<td style="width: 100px"><c:out value="${prod.barras}"></c:out></td>
												<td style="width: 100px">R$ <fmt:formatNumber
														type="number" minFractionDigits="2" value="${prod.preco}" />
												</td>
												<td style="width: 100px"><fmt:formatNumber
														type="number" maxFractionDigits="3"
														value="${prod.estoque}" /></td>
												<td style="width: 50px"><a
													href="salvarProduto?acao=editar&prod=${prod.id}"><img
														width="20px" height="20px" alt="Editar" title="Editar"
														src="resources/img/editar.png"> </a></td>
												<td style="width: 50px"><a
													href="salvarProduto?acao=delete&prod=${prod.id}"><img
														width="20px" height="20px" alt="Excluir" title="Excluir"
														src="resources/img/excluir.png"
														onclick="return confirm('Confirmar a exclusão?');"></a></td>
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
				if (document.getElementById("nome").value == '') {
					alert("O campo \"NOME\" não pode ser vazio.\n");
					return false;
				} else if (document.getElementById("preco").value == '') {
					alert("O campo \"PREÇO\" não pode ser vazio.\n");
					return false;
				} else if (document.getElementById("estoque").value == '') {
					alert("O campo \"ESTOQUE\" não pode ser vazio.\n");
					return false;
				} else if (document.getElementById("barras").value == '') {
					alert("O campo \"CÓDIGO\" não pode ser vazio.\n");
					return false;
				}
				return true;
			}
			//---------------------------
		</script>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$('#preco').maskMoney({
			thousands : '.',
			decimal : ','
		});
		$('#estoque').maskMoney({
			thousands : '.',
			decimal : ',',
		});
	});

	$(document).ready(function() {
		$("#barras").keyup(function() {
			$("#barras").val(this.value.match(/[0-9]*/));
		});
	});
</script>
</html>