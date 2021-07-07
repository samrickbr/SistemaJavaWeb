package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	// ===========================================================
	public void salvar(BeanProduto produto) {

		String sql = "INSERT INTO produto (nome, barras, preco, estoque, ativo) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, produto.getNome());
			insert.setInt(2, produto.getBarras());
			insert.setFloat(3, produto.getPreco());
			insert.setFloat(4, produto.getEstoque());
			insert.setBoolean(5, produto.isAtivo());
			insert.execute();

			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// ===========================================================
	public List<BeanProduto> listar() throws Exception {

		List<BeanProduto> listar = new ArrayList<BeanProduto>();

		String sql = "select * from produto";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanProduto produto = new BeanProduto();
			produto.setId(resultSet.getLong("id"));
			produto.setNome(resultSet.getString("nome"));
			produto.setBarras(resultSet.getInt("barras"));
			produto.setPreco(resultSet.getFloat("preco"));
			produto.setEstoque(resultSet.getFloat("estoque"));
			produto.setAtivo(resultSet.getBoolean("ativo"));

			listar.add(produto);

		}

		return listar;
	}

	// ===========================================================
	public BeanProduto consultar(String id) throws Exception {

		String sql = "select * from produto where id = '" + id + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			BeanProduto produto = new BeanProduto();
			produto.setId(resultSet.getLong("id"));
			produto.setNome(resultSet.getString("nome"));
			produto.setBarras(resultSet.getInt("barras"));
			produto.setPreco(resultSet.getFloat("preco"));
			produto.setEstoque(resultSet.getFloat("estoque"));
			produto.setAtivo(resultSet.getBoolean("ativo"));

			return produto;

		} else {
			return null;
		}
	}

	// ===========================================================
	public void deletar(Long id) {
		try {
			String sql = "delete from produto where id = '" + id + "'";
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();

			connection.commit();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			connection.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ===========================================================
	public boolean validarCodigo(String barras) throws Exception {
		String sql = "select count(1) as qtd from produto where barras = '"
				+ barras + " '";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		return false;
	}

	// ===========================================================
	public boolean validarCodigoUpdate(Integer barras, Long id)
			throws Exception {
		String sql = "select count(1) as qtd from produto where barras = "
				+ barras + " and id <> " + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		return false;
	}

	// ===========================================================
	public void editar(BeanProduto produto) {
		try {
			String sql = "update produto set nome = ?, barras = ?, preco = ?, estoque = ?, ativo = ? where id = "
					+ produto.getId();
			PreparedStatement update = connection.prepareStatement(sql);

			update.setString(1, produto.getNome());
			update.setInt(2, produto.getBarras());
			update.setFloat(3, produto.getPreco());
			update.setFloat(4, produto.getEstoque());
			update.setBoolean(5, produto.isAtivo());

			update.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
}
