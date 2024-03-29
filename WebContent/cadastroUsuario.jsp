<!DOCTYPE html>

<%@page import="javax.swing.text.Document"%>
<%@page import="beans.BeanUsuarioJsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usu�rio</title>
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
	crossorigin="anonymous" type="text/javascript">
	
</script>

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

						<form action="salvarUsuario" method="POST" id="formUser"
							class="material-form" id="quoteForm"
							enctype="multipart/form-data">
							
							<h2 style="color: white; font-weight: 200; margin-top: 50%">Dados de cadastro:</h2>

							<br />
							<div class="col-md-6 col-sm-6">
								<label for="id" class="label label-default"> ID: </label> <input
									type="text" class="form-control" id="id" readonly="readonly"
									name="id" value="${user.id}">
							</div>
							<div class="col-md-6 col-sm-6" align="left">
								<label for="ativo" class="label label-default">ATIVO:</label> <br>
								<label class="btn btn-outline-primary; col-md-6 col-sm-6"
									for="ativo"
									style="background-color: window; font: bold; color: black;">
									<input alt="ATIVO" type="checkbox" class="btn-check" id="ativo"
									name="ativo" checked="checked"
									<%if (request.getAttribute("user") != null) {
										BeanUsuarioJsp produtos = (BeanUsuarioJsp) request.getAttribute("user");
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
								<label for="nome" class="label label-default"> NOME: </label> <input
									type="text" class="form-control" id="nome" name="nome"
									maxlength="120" value="${user.nome}" required="required"
									placeholder="Nome">
							</div>

							<div class="col-md-6 col-sm-6">
								<label for="email" class="label label-default"> E-MAIL:
								</label> <input type="text" class="form-control" id="email" name="email"
									maxlength="150" value="${user.email}" placeholder="E-mail">
							</div>
							<br /> <br /> <br />
							<div class="col-md-6 col-sm-6">
								<label for="login" class="label label-default"> LOGIN: </label>
								<input type="text" class="form-control" id="login" name="login"
									maxlength="100" value="${user.login}" required="required"
									placeholder="Login">
							</div>

							<div class="col-md-6 col-sm-6">
								<label for="senha" class="label label-default"> SENHA: </label>
								<input type="password" class="form-control" id="senha"
									name="senha" maxlength="20" value="${user.senha}"
									required="required" placeholder="Senha">
							</div>
							<br /> <br /> <br />
							<div class="col-md-6 col-sm-6">
								<label for="cep" class="label label-default"> CEP: </label> <input
									type="number" class="form-control" id="cep" name="cep"
									maxlength="8" value="${user.cep}"
									placeholder="Cep Ex. 12345678" onblur="consultaCep();">
							</div>

							<div class="col-md-6 col-sm-6">
								<label for="rua" class="label label-default"> RUA: </label> <input
									title="text" class="form-control" id="rua" name="rua"
									maxlength="100" value="${user.rua}" placeholder="Rua">
							</div>
							<br /> <br /> <br />
							<div class="col-md-6 col-sm-6">
								<label for="bairro" class="label label-default"> Bairro:
								</label> <input type="text" class="form-control" id="bairro"
									maxlength="50" name="bairro" value="${user.bairro}"
									placeholder="Bairro">
							</div>
							<div class="col-md-6 col-sm-6">
								<label for="cidade" class="label label-default"> CIDADE:
								</label> <input type="text" class="form-control" id="cidade"
									maxlength="100" name="cidade" value="${user.cidade}"
									placeholder="Cidade">
							</div>
							<br /> <br /> <br />
							<div class="col-md-6 col-sm-6">
								<label for="uf" class="label label-default"> ESTADO: </label> <input
									type="text" class="form-control" id="uf" name="uf"
									maxlength="2" value="${user.uf}" placeholder="Estado">
							</div>

							<div class="col-md-6 col-sm-6">
								<label for="sexo" class="label label-default">SEXO:</label> <br>
								
								<div style="background-color: window; font: bold; color: black; font-size: medium; border-radius: 3px; padding: 1%;">
									<input type="radio" name="sexo"
									<%
										if(request.getAttribute("user") != null ){
											BeanUsuarioJsp user = (BeanUsuarioJsp) request.getAttribute("user");
											if(user.getSexo().equalsIgnoreCase("masculino")){
												out.print(" ");
												out.print("checked=\"checked\"");
												out.print(" ");
											}
										}
									%>
									 value="masculino"> MASCULINO
									<input type="radio" name="sexo"
									<%
										if(request.getAttribute("user") != null ){
											BeanUsuarioJsp user = (BeanUsuarioJsp) request.getAttribute("user");
											if(user.getSexo().equalsIgnoreCase("feminino")){
												out.print(" ");
												out.print("checked=\"checked\"");
												out.print(" ");
											}
										}
									%>
									 value="feminino" > FEMININO
									 <input type="radio" name="sexo" checked="checked"
									<%
										if(request.getAttribute("user") != null ){
											BeanUsuarioJsp user = (BeanUsuarioJsp) request.getAttribute("user");
										if(user.getSexo().equalsIgnoreCase("na")){
												out.print(" ");
												out.print("checked=\"checked\"");
												out.print(" ");
											}
										}
									%>
									 value="na"> N/A
								</div>
							</div>
							
							<br /> <br /> <br />
							
							<div class="col-md-6 col-sm-6">
							<label for="perfil" class="label label-default">PERFIL:</label><br>
							<select id="perfil" name="perfil" style="width: 100%; color: black; font: bold; font-size: large;">
								<option value="na"
								<%
									if(request.getAttribute("user") != null ){
										BeanUsuarioJsp user = (BeanUsuarioJsp) request.getAttribute("user");
									if(user.getPerfil().equalsIgnoreCase("na")){
											out.print(" ");
											out.print("selected=\"selected\"");
											out.print(" ");
										}
									}
								%>>--SELECIONE--</option>
								
								<option value="administrador"
								<%
									if(request.getAttribute("user") != null ){
										BeanUsuarioJsp user = (BeanUsuarioJsp) request.getAttribute("user");
									if(user.getPerfil().equalsIgnoreCase("administrador")){
											out.print(" ");
											out.print("selected=\"selected\"");
											out.print(" ");
										}
									}
								%>>ADMINISTRADOR</option>
								
								<option value="faturamento"
								<%
									if(request.getAttribute("user") != null ){
										BeanUsuarioJsp user = (BeanUsuarioJsp) request.getAttribute("user");
									if(user.getPerfil().equalsIgnoreCase("faturamento")){
											out.print(" ");
											out.print("selected=\"selected\"");
											out.print(" ");
										}
									}
								%>>FATURAMENTO</option>
								
								<option value="gerente"
								<%
									if(request.getAttribute("user") != null ){
										BeanUsuarioJsp user = (BeanUsuarioJsp) request.getAttribute("user");
									if(user.getPerfil().equalsIgnoreCase("gerente")){
											out.print(" ");
											out.print("selected=\"selected\"");
											out.print(" ");
										}
									}
								%>>GERENTE</option>
							</select>
							
							</div>
							
							<br /> <br /> <br />
							<div class="col-md-6 col-sm-6">
								<label for="foto" class="label label-default"> FOTO: </label> <input
									type="file" class="form-control" id="foto" name="foto">
							</div>

							<div class="col-md-6 col-sm-6">
								<label for="curriculo" class="label label-default">
									Curriculo: </label> <input type="file" class="form-control"
									id="curriculo" name="curriculo">
							</div>
							<div align="center" class="col-md-6 col-sm-6">
								<c:if test="${user.fotoBase64.isEmpty() == false}">
									<button style="width: 180px"
										onclick="return apagarFoto()? true:false;">
										<img width="20px" height="20px" alt="Apagar Foto"
											title="Apagar Foto" src="resources/img/excluir.png">
										'Apagar Foto'
									</button>
								</c:if>

								<c:if test="${user.fotoBase64.isEmpty() == true}">
									<button style="width: 180px" disabled="disabled">
										<img width="20px" height="20px" alt="Apagar Foto"
											title="Apagar Foto" src="resources/img/excluir.png">
										'Apagar Foto'
									</button>
								</c:if>

							</div>
							<div align="center" class="col-md-6 col-sm-6">
								<c:if test="${user.curriculoBase64.isEmpty() == false}">
									<button style="width: 180px"
										onclick="return apagarPdf()? true:false;">
										<img width="20px" height="20px" alt="Apagar Curriculo"
											title="Apagar Curriculo" src="resources/img/excluir.png">
										'Apagar Curriculo'
									</button>
								</c:if>

								<c:if test="${user.curriculoBase64.isEmpty() == true}">
									<button style="width: 180px" disabled="disabled">
										<img width="20px" height="20px" alt="Apagar Curriculo"
											title="Apagar Curriculo" src="resources/img/excluir.png">
										'Apagar Curriculo'
									</button>
								</c:if>
							</div>
							<br>
							<hr>
							<h3 align="center" style="color: orange;">${msg}</h3>

							<div class="col-md-6 col-sm-6">
								<div class="section-btn">
									<button type="submit" class="form-control" id="salvar"
										name="salvar" onclick="return validarCampos()? true:false;">
										<span data-hover="Salvar" style="width: 100%"> Salvar </span>
									</button>
								</div>
							</div>
							<div class="col-md-6 col-sm-6">
								<div class="section-btn">
									<button type="submit" class="form-control" id="cancelar"
										name="cancelar"
										onclick="document.getElementById('formUser').action='salvarUsuario?acao=reset'">
										<span data-hover="Cancelar" style="width: 100%;">
											Cancelar </span>
									</button>
								<br>
								</div> 
							</div>
							<div class="col-md-12 col-sm-12">
								<hr>
							</div>
						</form>				
				
						<form method="post" action="servletPesquisa" id="formPesquisa">	
							<div class="col-md-6 col-sm-6">
							<br style="width: 80%">
								<label for="descricaoconsulta" class="label label-default">Descri��o: </label> 
								<input type="text" class="form-control" id="descricaoconsulta" name="descricaoconsulta">
							</div>
							<div class="col-md-6 col-sm-6">
								<div class="section-btn">
									<button type="submit" class="form-control" id="pesquisar" name="pesquisar">
									<span data-hover="Pesquisar" style="width: 100%">Pesquisar</span> </button>
								</div>
							</div>	
						</form>
						
						<div class="col-md-12 col-sm-12">
							<hr>
							<br>
						</div>
	
						<c:if test="${usuarios.isEmpty() == false}">
							<div class=" col-md-12 col-sm-12">
								<div class="table-responsive">
									<table class="table table-striped">
										<thead>
											<tr>
												<th style="text-align: center;">ID</th>
												<th style="text-align: center;">LOGIN</th>
												<th style="text-align: center;">NOME</th>
												<th style="text-align: center;">FOTO</th>
												<th style="text-align: center;">CURRICULO</th>
												<th colspan="3" style="text-align: center;">OP��ES</th>
											</tr>
										</thead>
										<c:forEach items="${usuarios}" var="user">
											<tr align="center">
												<td style="width: 50px"><c:out value="${user.id}">
													</c:out></td>
												<td style="width: 100px"><c:out value="${user.login}">
													</c:out></td>
												<td style="width: 100px"><c:out value="${user.nome}">
													</c:out></td>

												<c:if test="${user.fotoBase64Miniatura.isEmpty() == false }">
													<td style="width: 100px"><a
														href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}">
															<img width="32px" height="32px"
															src='<c:out value="${user.fotoBase64Miniatura}"></c:out>'>
													</a></td>
												</c:if>
												<c:if test="${user.fotoBase64Miniatura == null}">
													<td><img width="32px" height="32px" alt="Imagem User"
														src="resources/img/user.jpg"></td>
												</c:if>

												<c:if test="${user.curriculoBase64.isEmpty() == false }">
													<td style="width: 100px"><a
														href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}">
															<img height="32px" width="32px" alt="Curriculo"
															src="resources/img/pdf.png">
													</a></td>
												</c:if>
												<c:if test="${user.curriculoBase64 == null}">
													<td><img width="32px" height="32px" alt="Curriculo"
														src="resources/img/semPdf.png"></td>
												</c:if>

												<td style="width: 50px"><a
													href="salvarTelefones?acao=addFone&user=${user.id}"> <img
														width="20px" height="20px" alt="Telefones"
														title="Telefones" src="resources/img/phone.png">
												</a></td>

												<td style="width: 50px"><a
													href="salvarUsuario?acao=editar&user=${user.id}"> <img
														width="20px" height="20px" alt="Editar" title="Editar"
														src="resources/img/editar.png">
												</a></td>

												<td style="width: 50px"><a
													href="salvarUsuario?acao=delete&user=${user.id}"> <img
														width="20px" height="20px" alt="Excluir" title="Excluir"
														src="resources/img/excluir.png"
														onclick="return confirm('Confirmar a exclus�o? ');"></a></td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<hr>
		</div>
	</section>
	<div>
		<script type="text/javascript">
			function validarCampos() {
				if (document.getElementById("login").value == '') {
					alert("O campo \"LOGIN\" n�o pode ser vazio.\n");
					return false;
				} else if (document.getElementById("senha").value == '') {
					alert("O campo \"SENHA\" n�o pode ser vazio.\n");
					return false;
				} else if (document.getElementById("nome").value == '') {
					alert("O campo \"NOME\" n�o pode ser vazio.\n");
					return false;
				}
				return true;
			}
			//------------------------------------------------------------

			/* Fun��o para confirmar apagar foto do cadastro*/
			function apagarFoto() {
				deletarFoto = confirm('Deseja apagar a Foto do cadastro?');
				if (deletarFoto) {
					document.getElementById('formUser').action = 'salvarUsuario?acao=delFoto&user=${user.id}';
					alert("A foto foi deletada!");
					return true;
				}
				return false;
			}
			//------------------------------------------------------------
			/* Fun��o para confirmar apagar pdf do cadastro*/
			function apagarPdf() {
				deletarPdf = confirm('Deseja apagar o Curriculo do cadastro?');
				if (deletarPdf) {
					document.getElementById('formUser').action = 'salvarUsuario?acao=delPdf&user=${user.id}';
					alert("O curriculo foi deletado!");
					return true;
				}
				return false;
			}

			//------------------------------------------------------------
			/* *Consulta de CEP atrav�s do webservice
			viacep.com.br */
			function limpa_formul�rio_cep() {
				/* Limpa valores do formul�rio de cep. */
				$("#cep").val("");
				$("#rua").val("");
				$("#bairro").val("");
				$("#cidade").val("");
				$("#uf").val("");
				/* $("#ibge").val(""); 
				 */
			}

			//------------------------------------------------------------
			function consultaCep() {
				var cep = $("#cep").val();

				/* Verifica se campo cep possui valor informado. */
				if (cep != "") {

					/* Express�o regular para validar o CEP. */
					var validacep = /^[0-9]{8}$/;

					/* Valida o formato do CEP. */
					if (validacep.test(cep)) {
						/* Preenche os campos com "..." enquanto consultawebservice. */
						$("#rua").val("...");
						$("#bairro").val("...");
						$("#cidade").val("...");
						$("#uf").val("...");
						/* $("#ibge").val("..."); */

						/* Consulta o webservice viacep.com.br/ */
						$.getJSON("https://viacep.com.br/ws/" + cep
								+ "/json/?callback=?",

						function(dados) {
							if (!("erro" in dados)) {
								/* Atualiza os campos com os valores da consulta. */
								$("#rua").val(dados.logradouro);
								$("#bairro").val(dados.bairro);
								$("#cidade").val(dados.localidade);
								$("#uf").val(dados.uf);
								/* $("#ibge").val(dados.ibge); */
								/* end if erro. */
							} else {
								/* CEP pesquisado n�o foi encontrado. */
								limpa_formul�rio_cep();
								alert("CEP n�o encontrado.");
								$("#cep").focus();
							}
						});
						/* fim json */
					} else {
						/* cep � inv�lido. */
						limpa_formul�rio_cep();
						alert("Formato de CEP inv�lido.");
						$("#cep").val("");
						$("#cep").focus();
					}
				} else {
					/* cep sem valor, limpa formul�rio. */
					limpa_formul�rio_cep();
				}
			}
		</script>
	</div>
</body>
</html>