package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	// ==========================================================================

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// pegar os parametros para executar a ação com o login selecionado

		String acao = request.getParameter("acao");
		String user = request.getParameter("user");

		// -----------------------------------------------------------------------
		// verificar se a ação é delete
		if (acao.equals("delete")) {
			daoUsuario.delete(user);

			try {
				// criar um dispatcher para setar a variável e listar os
				// usuários
				request.setAttribute("msg", "Usuário deletado com sucesso!");

				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				// atributos da requisição = usuarios (está setando os atributos que vem do daoUsuario
				//para a page cadastroUsuario no paranmetro "item" da tabela  
				request.setAttribute("usuarios", daoUsuario.listar());
				// redirecionamento:
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg",
						"Não foi possível deletar o usuário seleciondo.");
			}
			// -----------------------------------------------------------------------
		} else if (acao.equals("editar")) {
			BeanCursoJsp beanCursoJsp;
			try {
				beanCursoJsp = daoUsuario.consultar(user);

				// redirecionar para a página com a tabela dos cadastros

				// criar um dispatcher para setar a variável e listar os
				// usuários
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				// atributos da requisição = usuarios (está setando os atributos que vem do daoUsuario
				//para a page cadastroUsuario no paranmetro "item" da tabela  
				request.setAttribute("user", beanCursoJsp);
				// redirecionamento:
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
			// -----------------------------------------------------------------------
		} else if (acao.equalsIgnoreCase("listartodos")) {
			RequestDispatcher view = request
					.getRequestDispatcher("/cadastroUsuario.jsp");
			// atributos da requisição = usuarios (está setando os atributos que vem do daoUsuario
			//para a page cadastroUsuario no paranmetro "item" da tabela  
			try {
				request.setAttribute("usuarios", daoUsuario.listar());
			} catch (Exception e) {
				e.printStackTrace();
			}
			// redirecionamento:
			view.forward(request, response);
			// -----------------------------------------------------------------------
		} else if (acao.equalsIgnoreCase("consultartodos")) {
			RequestDispatcher view = request
					.getRequestDispatcher("/consultaUsuario.jsp");
			// atributos da requisição = usuarios que vem do daoUsuario
			try {
				request.setAttribute("usuarios", daoUsuario.listar());
			} catch (Exception e) {
				e.printStackTrace();
			}
			// redirecionamento:
			view.forward(request, response);
		}
	}

	// ==========================================================================
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");

		// -----------------------------------------------------------------------
		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				// criar um dispatcher para setar a variável e listar os
				// usuários
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				// atributos da requisição = usuarios (está setando os atributos que vem do daoUsuario
				//para a page cadastroUsuario no paranmetro "item" da tabela  
				request.setAttribute("usuarios", daoUsuario.listar());
				// redirecionamento:
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// -----------------------------------------------------------------------
		} else {
			// Salvar, alterar usuário

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String fone = request.getParameter("fone");
			
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
				BeanCursoJsp usuario = new BeanCursoJsp();
				
				
				if (podeValidar) {
					usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
					usuario.setLogin(login);
					usuario.setSenha(senha);
					usuario.setNome(nome);
					usuario.setFone(fone);
				}
				

				// passar o objeto usuario com login e senha para o método salvar do
				// dao
				// de cadastro

				// -----------------------------------------------------------------------

				// validação de usuário novo
				if (id == null || id.isEmpty() //
						&& !daoUsuario.validarLogin(login)) {
					msg.append("O login escolhido já existe. \n");
					podeValidar = false;
				} else if (id == null || id.isEmpty() //
						&& !daoUsuario.validarSenha(senha)) {
					podeValidar = false;
					msg.append("A senha escolhida não pode ser usada. \n");
				}

				

				// caso passe pela validação, cadastra o novo usuário

				if (id == null || id.isEmpty()
						&& daoUsuario.validarLogin(login) && podeValidar) {
					daoUsuario.salvar(usuario);
					msg.append("Usuário Cadastrado com sucesso! \n");
				} else if (id != null && !id.isEmpty() && podeValidar) {

					// caso passe pela validação,altera o usuário
					daoUsuario.edit(usuario);
					msg.append("Usuário alterado com sucesso! \n");
				}

				// mensagem de retorno
				if (msg != null) {
					request.setAttribute("msg", msg);
				}
				
				// retornar os dados para a tela acaso ocorra algum erro
				if (!podeValidar) {
					request.setAttribute("user", usuario);
				}

				// criar um dispatcher para setar a variável e listar os
				// usuários
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				// atributos da requisição = usuarios que vem do daoUsuario
				request.setAttribute("usuarios", daoUsuario.listar());
				// redirecionamento:
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}