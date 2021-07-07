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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String prod = request.getParameter("prod");

		if (acao.equalsIgnoreCase("delete")) {
			try {
				daoProduto.deletar(Long.parseLong(prod));
				request.setAttribute("msg", "Produto deletado com sucesso!");

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");

				request.setAttribute("produtos", daoProduto.listar());

				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "Não foi possível deletar o produto selecionado.");
			}

		} else if (acao.equalsIgnoreCase("editar")) {
			try {
				// criar o objeto a ser editado(beanProduto),
				// atribuindo os dados do produto pelo dao
				// (daoProduto.consultar)
				BeanProduto produto = daoProduto.consultar(prod);

				// chamar a p�gina para fazer a edi��o
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");

				// injetar os dados do obj na pag pelo sufixo (prod.id...)
				request.setAttribute("prod", produto);

				// carregar a pagina com as requisi��es
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (acao.equalsIgnoreCase("listartodos")) {
			RequestDispatcher view = request.getRequestDispatcher("/cadastrarProduto.jsp");

			try {
				request.setAttribute("produtos", daoProduto.listar());
			} catch (Exception e) {
				e.printStackTrace();
			}

			view.forward(request, response);

		} else if (acao.equalsIgnoreCase("consultartodos")) {
			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");

			try {
				request.setAttribute("produtos", daoProduto.listar());
			} catch (Exception e) {
				e.printStackTrace();
			}

			view.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");

				request.setAttribute("produtos", daoProduto.listar());

				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			// montar o objeto com os dados da tela

			String id = request.getParameter("id").trim();
			String nome = request.getParameter("nome");
			String barras = request.getParameter("barras").trim();
			String preco = request.getParameter("preco");
			String estoque = request.getParameter("estoque");

			try {
				StringBuilder msg = new StringBuilder();
				boolean podeValidar = true;

				if (nome == null || nome.isEmpty()) {
					msg.append("O nome do produto deve ser informado.\n ");
					podeValidar = false;
				}
				if (barras == null || barras.isEmpty()) {
					msg.append("O código do produto deve ser informado.\n ");
					podeValidar = false;
				}
				if (preco == null || preco.isEmpty()) {
					msg.append("O preço do produto deve ser informado.\n ");
					podeValidar = false;
				}
				if (barras == null || estoque.isEmpty()) {
					msg.append("O estoque do produto deve ser informado.\n ");
					podeValidar = false;
				}
				BeanProduto produto = new BeanProduto();

				if (podeValidar) {
					produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
					System.out.println(request.getParameter("ativo"));

					if (request.getParameter("ativo") != null && request.getParameter("ativo").equalsIgnoreCase("on")) {
						produto.setAtivo(true);
					} else {
						produto.setAtivo(false);
					}
					
					produto.setNome(nome);
					//produto.setBarras(Integer.parseInt(barras));
					produto.setBarras(Integer.parseInt(String.valueOf(barras)));

					String valorParse = preco.replace("R$ ", "");
					valorParse = valorParse.replace(".", "");
					valorParse = valorParse.replace(",", ".");
					produto.setPreco(Float.parseFloat(valorParse));

					String estoqueParse = estoque.replace(".", "");
					estoqueParse = estoqueParse.replace(",", ".");
					produto.setEstoque(Float.parseFloat(estoqueParse));
					
				}
				if (id == null || id.isEmpty() // ok
						&& !daoProduto.validarCodigo(barras)) {
					msg.append("Já existe um produto cadastrado com este código.\n"
							+ "Não é possivel cadastrar o novo produto. ");
					podeValidar = false;

				} else if (id != null && !id.isEmpty()
						&& !daoProduto.validarCodigoUpdate(Integer.parseInt(barras), Long.parseLong(id))) {
					msg.append("Ja existe um produto cadastrado com este código.\n"
							+ "Não é possivel atualizar o produto atual. ");
					podeValidar = false;
				}

				if (id == null || id.isEmpty() && podeValidar // ok
						&& daoProduto.validarCodigo(barras)) {
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

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");

				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
