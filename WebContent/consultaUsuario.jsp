<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta usuários</title>
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
	<!-- PRE LOADER -->
	<div class="preloader">
		<div class="spinner">
			<span class="sk-inner-circle"></span>
		</div>
	</div>
	<!-- HOME -->
	<section id="home" class="parallax-section">
	<div class="overlay"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-1 col-md-10 col-sm-12">
				<div class="table-header-group" align="center">

					<form action="salvarUsuario" method="post"
						style="background-color: white; border-style: double;">
						<h2>Lista de usuários cadastrados:</h2>
						<p />
						<table class="dataTable">

							<thead>
								<tr>
									<th style="text-align: center;">ID</th>
									<th style="text-align: center;">NOME</th>
									<th style="text-align: center;">E-MAIL</th>
									<th style="text-align: center;">LOGIN</th>
									<th style="text-align: center;">CEP</th>
									<th style="text-align: center;">RUA</th>
									<th style="text-align: center;">BAIRRO</th>
									<th style="text-align: center;">CIDADE</th>
									<th style="text-align: center;">ESTADO</th>
									<th colspan="3" style="text-align: center;">OPÇÕES</th>
								</tr>
							</thead>
							<c:forEach items="${usuarios}" var="user">
								<tr>
									<td style="width: 50px; text-align: center;"><c:out
											value="${user.id}"></c:out></td>
									<td style="width: 200px; text-align: center;"><c:out
											value="${user.nome}"></c:out></td>
									<td style="width: 200px; text-align: center;"><c:out
											value="${user.email}"></c:out></td>
									<td style="width: 200px; text-align: center;"><c:out
											value="${user.login}"></c:out></td>
									<td style="width: 100px; text-align: center;"><c:out
											value="${user.cep}"></c:out></td>
									<td style="width: 300px; text-align: center;"><c:out
											value="${user.rua}"></c:out></td>
									<td style="width: 200px; text-align: center;"><c:out
											value="${user.bairro}"></c:out></td>
									<td style="width: 300px; text-align: center;"><c:out
											value="${user.cidade}"></c:out></td>
									<td style="width: 100px; text-align: center;"><c:out
											value="${user.uf}"></c:out></td>

									<td style="width: 50px"><a
										href="salvarTelefones?acao=addFone&user=${user.id}"><img width="20px"
											height="20px" alt="Telefones" title="Telefones"
											src="resources/img/phone.png"> </a></td>
									<td style="width: 50px; text-align: center;"><a
										href="salvarUsuario?acao=editar&user=${user.id}"><img
											width="20px" height="20px" alt="Editar" title="Editar"
											src="resources/img/editar.png"> </a></td>
									<td style="width: 50px; text-align: center;"><a
										href="salvarUsuario?acao=delete&user=${user.id}"><img
											width="20px" height="20px" alt="Excluir" title="Excluir"
											src="resources/img/excluir.png"></a></td>
								</tr>
							</c:forEach>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	</section>

	<!-- SCRIPTS -->
	<script src="resources/js/estilo/jquery.js"></script>
	<script src="resources/js/estilo/bootstrap.min.js"></script>
	<script src="resources/js/estilo/jquery.parallax.js"></script>
	<script src="resources/js/estilo/owl.carousel.min.js"></script>
	<script src="resources/js/estilo/jquery.magnific-popup.min.js"></script>
	<script src="resources/js/estilo/magnific-popup-options.js"></script>
	<script src="resources/js/estilo/modernizr.custom.js"></script>
	<script src="resources/js/estilo/smoothscroll.js"></script>
	<script src="resources/js/estilo/custom.js"></script>
</body>
</html>