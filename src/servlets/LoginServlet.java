package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoLogin;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoLogin daoLogin = new DaoLogin();

	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
		try {

			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			/* validaï¿½ï¿½o do login e senha */
			// if (beanCursoJsp.validaLoginSenha(login, senha)) { // este mï¿½todo utiliza o
			// bean pra validar o login

			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {

				if (daoLogin.validarLogin(login, senha)) { // este método utiliza o DAO para conectar no DB e validar
															// o login
					/*
					 * mátodo para direcionar para a página do sistema caso login e senha sejam
					 * verdadeiros
					 */
					RequestDispatcher dispatcher = request.getRequestDispatcher("acessopermitido.jsp"); // setar uma
																										// página
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("acessonegado.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}