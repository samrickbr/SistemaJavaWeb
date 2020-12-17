package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanTelefones;
import beans.BeanUsuarioJsp;
import dao.DaoTelefones;
import dao.DaoUsuario;

@WebServlet("/salvarTelefones")
public class TelefonesServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();
	private DaoTelefones daoTelefones = new DaoTelefones();

	public TelefonesServlets() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");

			if (acao.equalsIgnoreCase("addFone")) {

				String user = request.getParameter("user");

				BeanUsuarioJsp usuario = daoUsuario.consultar(user);

				/*
				 * setar uma sessão para não perder o usuário que está sendo inserido o telefone
				 */
				request.getSession().setAttribute("userEscolhido", usuario);
				request.setAttribute("userEscolhido", usuario);

				// direcionar o pedido para a tela de telefones
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");

				request.setAttribute("telefones", daoTelefones.listar(usuario.getId()));

				// ir para a tela de telefones
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("deleteFone")) {
				Long foneId = Long.parseLong(request.getParameter("foneId"));

				daoTelefones.deletar(foneId);

				BeanUsuarioJsp usuario = (BeanUsuarioJsp) request.getSession().getAttribute("userEscolhido");
				// direcionar o pedido para a tela de telefones
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daoTelefones.listar(usuario.getId()));
				request.setAttribute("msg", "Telefone deletado com sucesso!");

				// ir para a tela de telefones
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			BeanUsuarioJsp usuario = (BeanUsuarioJsp) request.getSession().getAttribute("userEscolhido");
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");
			String acao = request.getParameter("acao");

			if (acao == null || (acao != null && !acao.equalsIgnoreCase("voltar"))) {

				BeanTelefones telefones = new BeanTelefones();
				telefones.setNumero(numero);
				telefones.setTipo(tipo);
				telefones.setUsuario(usuario.getId());

				daoTelefones.salvar(telefones);
				request.getSession().setAttribute("userEscolhido", usuario);
				request.setAttribute("userEscolhido", usuario);

				// direcionar o pedido para a tela de telefones
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daoTelefones.listar(usuario.getId()));
				request.setAttribute("msg", "Telefone salvo com sucesso!");

				// ir para a tela de telefones
				view.forward(request, response);
			} else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
