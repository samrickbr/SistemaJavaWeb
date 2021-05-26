<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ACESSO NEGADO</title>
<!-- MAIN CSS -->
<link rel="stylesheet" href="resources/css/estilo/tooplate-style.css">
<link rel="stylesheet" href="resources/css/estilo/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/estilo/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/estilo/magnific-popup.css">
<link rel="stylesheet" href="resources/css/estilo/owl.carousel.css">
<link rel="stylesheet" href="resources/css/estilo/owl.theme.css">
<link rel="stylesheet" href="resources/css/estilo/tooplate-style.css">
</head>
<body>

	<!-- MENU -->
	<div class="navbar custom-navbar navbar-fixed-top" role="navigation">
		<div class="container">

			<!-- NAVBAR HEADER -->
			<div class="navbar-header">
				<button class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon icon-bar"></span> <span class="icon icon-bar"></span>
					<span class="icon icon-bar"></span>
				</button>
				<!-- lOGO -->
				<a href="#" class="navbar-brand"><img src="resources/img/logo.png" height="128px" width="128px"></a>
			</div>

			<!-- MENU LINKS -->
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
				<li><a href="index.jsp" class="smoothScroll">Login</a></li>
				</ul>
			</div>
		</div>
	</div>

	<!-- HOME -->
	<section id="home" class="parallax-section">
	<div class="overlay"></div>
	<div class="container">
		<div class="row">

			<div class="col-md-8 col-sm-12">
				<div class="home-text">
					<h1>Olá desconhecido!</h1>
					<p>Infelizmente seu usuário não foi autenticado corretamente.</p>
					<ul class="section-btn">
						<a href="index.jsp" class="smoothScroll"><span
							data-hover="VOLTAR?">Tentar Novamente?.</span></a>
					</ul>
				</div>
			</div>

		</div>
	</div>

	<!-- Video --> 
	<video controls autoplay loop muted> 
	<source src="resources/videos/video.mp4" type="video/mp4">Your browser does
	not support the video tag.</video>
	
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