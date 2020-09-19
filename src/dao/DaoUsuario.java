package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanUsuarioJsp;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	// =============================================================
	public void salvar(BeanUsuarioJsp usuario) {
		// insert dos dados no DB

		try {
			String sql = "insert into usuario(login, senha, nome, email, cep, rua, bairro, cidade, "
					+ "uf, fotobase64, contenttype,curriculobase64, contenttypecurriculo) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getEmail());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getCidade());
			insert.setString(9, usuario.getUf());
			insert.setString(10, usuario.getFotoBase64());
			insert.setString(11, usuario.getContentType());
			insert.setString(13, usuario.getCurriculoBase64());
			insert.setString(14, usuario.getContentTypeCurriculo());
			insert.execute();

			// ocorrendo tudo certo com a inserção a operação é commitada
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				// caso ocorra erro na inserção, a operação é cancelada
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	// =============================================================
	// método para listar todos os usuários cadastrados
	public List<BeanUsuarioJsp> listar() throws Exception {
		// criar um novo objeto de lista
		List<BeanUsuarioJsp> listar = new ArrayList<BeanUsuarioJsp>();

		String sql = "select * from usuario";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		// criar a listagem com while enquanto houver usuarios
		while (resultSet.next()) {

			BeanUsuarioJsp usuario = new BeanUsuarioJsp();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setCep(resultSet.getString("cep"));
			usuario.setRua(resultSet.getString("rua"));
			usuario.setBairro(resultSet.getString("bairro"));
			usuario.setCidade(resultSet.getString("cidade"));
			usuario.setUf(resultSet.getString("uf"));
			usuario.setFotoBase64(resultSet.getString("fotobase64"));
			usuario.setContentType(resultSet.getString("contenttype"));
			usuario.setCurriculoBase64(resultSet.getString("curriculobase64"));
			usuario.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));

			listar.add(usuario);
		}
		return listar;
	}

	// =============================================================
	public void delete(String id) {
		try {

			String sql = "delete from usuario where id = '" + id + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();

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

	// =============================================================
	public void edit(BeanUsuarioJsp usuario) {
		try {

			String sql = "update usuario set login = ?, senha = ?, nome = ?, email = ?, "
					+ "cep = ?, rua = ?, bairro = ?, cidade = ?, uf = ?, fotobase64 = ?, contenttype = ?, curriculobase64 = ?, contenttypecurriculo = ? where id = "
					+ usuario.getId();
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, usuario.getLogin());
			update.setString(2, usuario.getSenha());
			update.setString(3, usuario.getNome());
			update.setString(4, usuario.getEmail());
			update.setString(5, usuario.getCep());
			update.setString(6, usuario.getRua());
			update.setString(7, usuario.getBairro());
			update.setString(8, usuario.getCidade());
			update.setString(9, usuario.getUf());
			update.setString(10, usuario.getFotoBase64());
			update.setString(11, usuario.getContentType());
			update.setString(12, usuario.getCurriculoBase64());
			update.setString(13, usuario.getContentTypeCurriculo());

			update.executeUpdate();

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

	// =============================================================
	public BeanUsuarioJsp consultar(String id) throws Exception {
		String sql = "select * from usuario where id = '" + id + "' ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			BeanUsuarioJsp usuario = new BeanUsuarioJsp();

			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setCep(resultSet.getString("cep"));
			usuario.setRua(resultSet.getString("rua"));
			usuario.setBairro(resultSet.getString("bairro"));
			usuario.setCidade(resultSet.getString("cidade"));
			usuario.setUf(resultSet.getString("uf"));
			usuario.setFotoBase64(resultSet.getString("fotobase64"));
			usuario.setContentType(resultSet.getString("contenttype"));
			usuario.setCurriculoBase64(resultSet.getString("curriculobase64"));
			usuario.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));

			return usuario;

		} else {
			return null;
		}

	}

	// =============================================================
	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) as qtd from usuario where login = '"
				+ login + "' ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;/*
												 * se não existir um login igual
												 * o count retorna '0' e o
												 * return tendo qtd <= 0 é true.
												 */

		} else {
			return false;
		}

	}

	// =============================================================
	public boolean validarLoginUpdate(String login, String id) throws Exception {
		String sql = "SELECT COUNT(1) as qtd FROM usuario WHERE login = '"
				+ login + "' AND id <> " + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;/*
												 * se não existir um login igual
												 * o count retorna '0' e o
												 * return tendo qtd <= 0 é true.
												 */

		} else {
			return false;
		}

	}

	// =============================================================
	public boolean validarSenha(String senha) throws Exception {
		String sql = "select count(1) as qtd from usuario where senha = '"
				+ senha + "' ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;/*
												 * se não existir um login igual
												 * o count retorna '0' e o
												 * return tendo qtd <= 0 é true.
												 */

		} else {
			return false;
		}

	}
}
