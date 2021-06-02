package servlets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanUsuarioJsp;
import dao.DaoUsuario;

/**
 * Servlet implementation class Usuario
 * 
 */
@WebServlet("/salvarUsuario")
@MultipartConfig
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	// ==========================================================================

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// pegar os parametros para executar a a��o com o login selecionado

		String acao = request.getParameter("acao");
		String user = request.getParameter("user");

		// -----------------------------------------------------------------------
		// verificar se a a��o � delete
		if (acao.equals("delete")) {
			daoUsuario.delete(user);

			try {
				// criar um dispatcher para setar a vari�vel e listar os
				// usu�rios
				request.setAttribute("msg", "Usu�rio deletado com sucesso!");

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				// atributos da requisi��o = usuarios (est� setando os atributos
				// que vem do daoUsuario
				// para a page cadastroUsuario no paranmetro "item" da tabela
				request.setAttribute("usuarios", daoUsuario.listar());
				// redirecionamento:
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "N�o foi poss�vel deletar o usu�rio selecionado.");
			}
			// -----------------------------------------------------------------------
		} else if (acao.equals("editar")) {
			BeanUsuarioJsp beanUsuarioJsp;
			try {
				beanUsuarioJsp = daoUsuario.consultar(user);

				request.setAttribute("msg", "Cadastro em edi��o!");
				// redirecionar para a p�gina com a tabela dos cadastros

				// criar um dispatcher para setar a vari�vel e listar os
				// usu�rios
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				// atributos da requisi��o = usuarios (est� setando os atributos
				// que vem do daoUsuario
				// para a page cadastroUsuario no paranmetro "item" da tabela
				request.setAttribute("user", beanUsuarioJsp);
				// redirecionamento:
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
			// -----------------------------------------------------------------------
		} else if (acao.equals("telefone")) {
			BeanUsuarioJsp beanUsuarioJsp;
			try {
				beanUsuarioJsp = daoUsuario.consultar(user);
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("user", beanUsuarioJsp);
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// -----------------------------------------------------------------------
		} else if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				// criar um dispatcher para setar a vari�vel e listar os
				// usu�rios
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				// atributos da requisi��o = usuarios (est� setando os atributos
				// que vem do daoUsuario
				// para a page cadastroUsuario no parametro "item" da tabela
				request.setAttribute("usuarios", daoUsuario.listar());
				// redirecionamento:
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// -----------------------------------------------------------------------
		} else if (acao.equalsIgnoreCase("listartodos")) {
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			// atributos da requisi��o = usuarios (est� setando os atributos que
			// vem do daoUsuario
			// para a page cadastroUsuario no parametro "item" da tabela
			try {
				request.setAttribute("usuarios", daoUsuario.listar());
			} catch (Exception e) {
				e.printStackTrace();
			}
			// redirecionamento:
			view.forward(request, response);
			// -----------------------------------------------------------------------
		} else if (acao.equalsIgnoreCase("consultartodos")) {
			RequestDispatcher view = request.getRequestDispatcher("/consultaUsuario.jsp");
			// atributos da requisi��o = usuarios que vem do daoUsuario
			try {
				request.setAttribute("usuarios", daoUsuario.listar());
			} catch (Exception e) {
				e.printStackTrace();
			}
			// redirecionamento:
			view.forward(request, response);
		} else if (acao.equalsIgnoreCase("download")) {
			BeanUsuarioJsp usuario;
			try {
				usuario = daoUsuario.consultar(user);
				if (usuario != null) {
					String contentType = "";
					byte[] fileBytes = null;
					String tipo = request.getParameter("tipo");

					if (tipo.equalsIgnoreCase("imagem")) {
						/** Converte a base64 da imagem do banco para byte[] */
						contentType = usuario.getContentType();
						new Base64();
						fileBytes = Base64.decodeBase64(usuario.getFotoBase64());
					} else if (tipo.equalsIgnoreCase("curriculo")) {
						contentType = usuario.getContentTypeCurriculo();
						new Base64();
						fileBytes = Base64.decodeBase64(usuario.getCurriculoBase64());
					}

					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + contentType.split("\\/")[1]);

					/** Coloca os bytes em um objeto de entrada para processar */
					InputStream is = new ByteArrayInputStream(fileBytes);

					/** In�cio da resposta para o navegador */
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();

					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					os.flush();
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	// ==========================================================================
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String user = request.getParameter("user");

		// -----------------------------------------------------------------------
		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				// criar um dispatcher para setar a vari�vel e listar os
				// usu�rios
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				// atributos da requisi��o = usuarios (est� setando os atributos
				// que vem do daoUsuario
				// para a page cadastroUsuario no parametro "item" da tabela
				request.setAttribute("usuarios", daoUsuario.listar());
				// redirecionamento:
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// -----------------------------------------------------------------------
		} else if (acao != null && acao.equals("delFoto")) {

			daoUsuario.delFoto(user);

			try {
				request.setAttribute("msg", "Foto do usu�rio apagada!");

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "N�o foi poss�vel apagar a foto do usu�rio selecionado.");
			}
		} else if (acao != null && acao.equals("delPdf")) {

			daoUsuario.delPdf(user);

			try {
				request.setAttribute("msg", "Curriculo do usu�rio apagado!");

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "N�o foi poss�vel apagar o curriculo do usu�rio selecionado.");
			}
		} else {
			// Salvar, alterar usu�rio

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String uf = request.getParameter("uf");

			try {

				StringBuilder msg = new StringBuilder();
				boolean podeValidar = true;

				if (login == null || login.isEmpty()) {
					msg.append("O Login deve ser preenchido. \n");
					podeValidar = false;
				}
				if (senha == null || senha.isEmpty()) {
					msg.append("A senha deve ser preenchido. \n");
					podeValidar = false;
				}
				if (nome == null || nome.isEmpty()) {
					msg.append("O Nome deve ser preenchido. \n");
					podeValidar = false;
				}

				// criar o objeto usuario com login e senha
				BeanUsuarioJsp usuario = new BeanUsuarioJsp();

				if (podeValidar) {
					usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
					usuario.setLogin(login);
					usuario.setSenha(senha);
					usuario.setNome(nome);
					usuario.setEmail(email);
					usuario.setCep(cep);
					usuario.setRua(rua);
					usuario.setBairro(bairro);
					usuario.setCidade(cidade);
					usuario.setUf(uf);
				}

				/** Inicio "File Upload" de imagens e PDF */

				// verifica se � um formul�rio de upload
				if (ServletFileUpload.isMultipartContent(request)) {

					Part imageFoto = request.getPart("foto");
					if (imageFoto != null && imageFoto.getInputStream().available() > 0) {

						byte[] bytesImagem = converteStreamParaByte(imageFoto.getInputStream());
						new Base64();
						String fotoBase64 = Base64.encodeBase64String(bytesImagem);
						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(imageFoto.getContentType());

						/* Fazer miniatura da imagem para n�o pesar o DB */
						/* transformar em um bufferedImage */
						byte[] imageByteDecode = new Base64().decodeBase64(fotoBase64);
						BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));
						/* pega o tipo da imgem */
						int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
						/* cria imagem em miniatura */
						BufferedImage resizedImage = new BufferedImage(100, 100, type);
						Graphics2D g = resizedImage.createGraphics();
						g.drawImage(resizedImage, 0, 0, 100, 100, null);

						/* Escrever imagem novamente */
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(bufferedImage, "png", baos);
						g.dispose();

						String miniaturaBase64 = "data:image/png;base64,"
								+ DatatypeConverter.printBase64Binary(baos.toByteArray());

						System.out.println(miniaturaBase64);
						/* Salva no DB */
						usuario.setFotoBase64Miniatura(miniaturaBase64);
						/* Fim c�digo miniaturiza��o de imagem */
					} else {
						usuario.setFotoBase64(request.getParameter("fotoTemp"));
						usuario.setContentType(request.getParameter("contentTypeTemp"));
					}
					Part curriculoPdf = request.getPart("curriculo");
					if (curriculoPdf != null && curriculoPdf.getInputStream().available() > 0) {
						new Base64();
						String curriculoBase64 = Base64
								.encodeBase64String(converteStreamParaByte(curriculoPdf.getInputStream()));
						usuario.setCurriculoBase64(curriculoBase64);
						usuario.setContentTypeCurriculo(curriculoPdf.getContentType());
					} else {
						usuario.setCurriculoBase64(request.getParameter("curriculoTemp"));
						usuario.setContentTypeCurriculo(request.getParameter("contentTypeTempCurriculo"));
					}

				}

				/** Fim "File Upload" de imagens e PDF */

				// -----------------------------------------------------------------------

				/**
				 * passar o objeto usuario com login e senha para o m�todo salvar do dao de
				 * cadastro
				 */

				// -----------------------------------------------------------------------

				// valida��o de usu�rio novo
				if (id == null || id.isEmpty() //
						&& !daoUsuario.validarLogin(login)) {
					msg.append("O login escolhido j� existe. \n");
					podeValidar = false;
				} else if (id == null || id.isEmpty() //
						&& !daoUsuario.validarSenha(senha)) {
					podeValidar = false;
					msg.append("A senha escolhida n�o pode ser usada. \n");
				}

				// caso passe pela valida��o, cadastra o novo usu�rio

				if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeValidar) {
					daoUsuario.salvar(usuario);
					msg.append("Usu�rio Cadastrado com sucesso! \n");
				} else if (id != null && !id.isEmpty() && podeValidar) {

					// caso passe pela valida��o,altera o usu�rio
					daoUsuario.edit(usuario);
					msg.append("Usu�rio alterado com sucesso! \n");
				}

				// mensagem de retorno
				if (msg != null) {
					request.setAttribute("msg", msg);
				}

				// retornar os dados para a tela acaso ocorra algum erro
				if (!podeValidar) {
					request.setAttribute("user", usuario);
				}

				// criar um dispatcher para setar a vari�vel e listar os
				// usu�rios
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				// atributos da requisi��o = usuarios que vem do daoUsuario
				request.setAttribute("usuarios", daoUsuario.listar());
				// redirecionamento:
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ------------------------------------------------------------------
	/** Converte a entrada de fluxo de dados da imagem para byte[] */
	private byte[] converteStreamParaByte(InputStream imagem) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();
		while (reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}
		return baos.toByteArray();
	}
}