<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Sitema com JSP e JSTL</title>
<link rel="stylesheet" href="resources/css/login.css">
</head>
<body>

	<!-- tags de JSTL para desenvolvimento com JSP  -->
	<div class="login-wrap">
		<div class="login-html">

			<div class="login-form">


				<c:out value="${'Bem vindo ao JSTL!' }" />
				<hr>
				<br />

				<form action="LoginServlet" method="post" class="login-form">
					<div class="group">
						<label for="login" class="label">Login:</label> <input id="login"
							type="text" class="input" name="login">
					</div>
					<div class="group">
						<label for="senha" class="label">Senha:</label> <input id="senha"
							type="password" class="input" name="senha">
					</div>
					<br />
					<div class="group">
						<input type="submit" class="button" value="Logar">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>