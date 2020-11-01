<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BEM VINDO!</title>

<!-- MAIN CSS -->
<link rel="stylesheet" href="resources/css/estilo/tooplate-style.css">

</head>
<body>
	<!-- PRE LOADER -->
	<div class="preloader">
		<div class="spinner">
			<span class="sk-inner-circle"></span>
		</div>
	</div>
	<c:import url="menu.jsp"></c:import>

	<!-- HOME -->
	<section id="home" class="parallax-section">
	<div class="overlay"></div>
	<div class="container">
		<div class="row">

			<div class="col-md-8 col-sm-12">
				<div class="home-text">
					<h1>Bem vindo ao sistema com JSTL!</h1>
					<p>Escolha no menu acima o que deseja fazer.</p>
					<ul class="section-btn">
						<a href="index.jsp" class="smoothScroll"><span
							data-hover="SAIR?">FAZER LOGOUT.</span></a>
					</ul>
				</div>
			</div>

		</div>
	</div>

	<!-- Video --> <video controls autoplay loop muted> <source
		src="resources/videos/video.mp4" type="video/mp4"> Your browser does
	not support the video tag. </video> </section>

	<!-- 	<menu>-->
	<!-- 	<a href="salvarUsuario?acao=listartodos">Cadastro de Usuários</a>-->
	<!-- 	<p />-->
	<!-- 	<a href="salvarUsuario?acao=consultartodos">Consulta de Usuários</a>-->
	<!-- </menu>	 -->

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