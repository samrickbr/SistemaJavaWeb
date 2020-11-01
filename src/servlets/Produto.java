package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoProduto;

/**
 * Servlet implementation class Produto
 */
@WebServlet("/salvarProduto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public Produto() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String prod = request.getParameter("prod");

		if (acao.equalsIgnoreCase("delete")) {
			try {
				daoProduto.deletar(Long.parseLong(prod));
				request.setAttribute("msg", "Produto deletado com sucesso!");

				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");

				request.setAttribute("produtos", daoProduto.listar());

				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg",
						"N�o foi poss�vel deletar o produto selecionado.");
			}

		} else if (acao.equalsIgnoreCase("editar")) {
			try {
				// criar o objeto a ser editado(beanProduto),
				// atribuindo os dados do produto pelo dao
				// (daoProduto.consultar)
				BeanProduto produto = daoProduto.consultar(prod);

				// chamar a p�gina para fazer a edi��o
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");

				// injetar os dados do obj na p�g pelo sufixo (prod.id...)
				request.setAttribute("prod", produto);

				// carregar a pagina com as requisi��es
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (acao.equalsIgnoreCase("listartodos")) {
			RequestDispatcher view = request
					.getRequestDispatcher("/cadastrarProduto.jsp");

			try {
				request.setAttribute("produtos", daoProduto.listar());
			} catch (Exception e) {
				e.printStackTrace();
			}

			view.forward(request, response);

		} else if (acao.equalsIgnoreCase("consultartodos")) {
			RequestDispatcher view = request
					.getRequestDispatcher("/cadastroProduto.jsp");

			try {
				request.setAttribute("produtos", daoProduto.listar());
			} catch (Exception e) {
				e.printStackTrace();
			}

			view.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {

				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");

				request.setAttribute("produtos", daoProduto.listar());

				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			// montar o objeto com os dados da tela

			String id = request.getParameter("id").trim();
			String nome = request.getParameter("nome");
			String codigo = request.getParameter("codigo");
			String preco = request.getParameter("preco");
			String estoque = request.getParameter("estoque");

			try {
				StringBuilder msg = new StringBuilder();
				boolean podeValidar = true;

				if (nome == null || nome.isEmpty()) {
					msg.append("O nome do produto deve ser informado.\n ");
					podeValidar = false;
				}
				if (codigo == null || codigo.isEmpty()) {
					msg.append("O c�digo do produto deve ser informado.\n ");
					podeValidar = false;
				}
				if (preco == null || preco.isEmpty()) {
					msg.append("O pre�o do produto deve ser informado.\n ");
					podeValidar = false;
				}
				if (estoque == null || estoque.isEmpty()) {
					msg.append("O estoque do produto deve ser informado.\n ");
					podeValidar = false;
				}
				BeanProduto produto = new BeanProduto();

				if (podeValidar) {
					produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
					produto.setNome(nome);
					produto.setCodigo(Integer.parseInt(codigo));
					produto.setPreco(Float.parseFloat(preco));
					produto.setEstoque(Float.parseFloat(estoque));
				}
				if (id == null || id.isEmpty() // ok
						&& !daoProduto.validarCodigo(codigo)) {
					msg.append("J� existe um produto cadastrado com este c�digo.\n"
							+ "N�o � possivel cadastrar o novo produto. ");
					podeValidar = false;

				} else if (id != null
						&& !id.isEmpty()
						&& !daoProduto.validarCodigoUpdate(
								Integer.parseInt(codigo), Long.parseLong(id))) {
					msg.append("J� existe um produto cadastrado com este c�digo.\n"
							+ "N�o � possivel atualizar o produto atual. ");
					podeValidar = false;
				}

				if (id == null || id.isEmpty() && podeValidar // ok
						&& daoProduto.validarCodigo(codigo)) {
					daoProduto.salvar(produto);
					msg.append("Produto cadastrado com sucesso!.\n ");
				} else if (id != null && !id.isEmpty() // ok
						&& podeValidar) {
					daoProduto.editar(produto);
					msg.append("Produto alterado com sucesso.\n ");

				}
				if (msg != null) {
					request.setAttribute("msg", msg);
				}

				if (!podeValidar) {
					request.setAttribute("prod", produto);
				}

				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");

				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
