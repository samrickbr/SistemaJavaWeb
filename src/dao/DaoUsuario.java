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
			StringBuilder sql = new StringBuilder();

			sql.append(" insert into usuario(login, senha, nome, email, cep, rua, bairro, cidade, uf, ativo ");

			if (usuario.isAtualizarImagem()) {
				sql.append(", fotobase64, contenttype ");
			}
			if (usuario.isAtualizarPdf()) {
				sql.append(", curriculobase64, contenttypecurriculo ");
			}
			if (usuario.isAtualizarImagem()) {
				sql.append(", fotobase64Miniatura ");
			}
			sql.append(") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ");
			if (usuario.isAtualizarImagem()) {
				sql.append(", ?, ? ");
			}
			if (usuario.isAtualizarPdf()) {
				sql.append(", ?, ? ");
			}
			if (usuario.isAtualizarImagem()) {
				sql.append(", ? ");
			}
			sql.append(") ");

			PreparedStatement insert = connection.prepareStatement(sql.toString());
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getEmail());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getCidade());
			insert.setString(9, usuario.getUf());
			insert.setBoolean(10, usuario.isAtivo());

			if (usuario.isAtualizarImagem()) {
				insert.setString(11, usuario.getFotoBase64());
				insert.setString(12, usuario.getContentType());
			}

			if (usuario.isAtualizarPdf()) {
				if (usuario.isAtualizarPdf() && !usuario.isAtualizarImagem()) {
					insert.setString(11, usuario.getCurriculoBase64());
					insert.setString(12, usuario.getContentTypeCurriculo());
				} else {
					insert.setString(13, usuario.getCurriculoBase64());
					insert.setString(14, usuario.getContentTypeCurriculo());
				}
			} else {
				if (usuario.isAtualizarImagem()) {
					insert.setString(13, usuario.getFotoBase64Miniatura());
				}
			}
			if (usuario.isAtualizarImagem() && usuario.isAtualizarPdf()) {
				insert.setString(15, usuario.getFotoBase64Miniatura());
			}
			insert.execute();

			// ocorrendo tudo certo com a inser��o a opera��o � commitada
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				// caso ocorra erro na inser��o, a opera��o � cancelada
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	// =============================================================
	// m�todo para listar todos os usu�rios cadastrados
	public List<BeanUsuarioJsp> listar() throws Exception {
		// criar um novo objeto de lista
		List<BeanUsuarioJsp> listar = new ArrayList<BeanUsuarioJsp>();

		String sql = "select * from usuario where login <> 'admin'";

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
			// usuario.setFotoBase64(resultSet.getString("fotobase64"));
			usuario.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			usuario.setContentType(resultSet.getString("contenttype"));
			usuario.setCurriculoBase64(resultSet.getString("curriculobase64"));
			usuario.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			usuario.setAtivo(resultSet.getBoolean("ativo"));

			listar.add(usuario);
		}
		return listar;
	}

	// =============================================================
	public void delete(String id) {
		try {

			String sql = "delete from usuario where id = '" + id + "' and login <> 'admin'";
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
	public void delFoto(String id) {
		try {

			String sql = "update usuario set fotobase64miniatura = '', fotobase64 = '', contenttype = '' where id = '"
					+ id + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeUpdate();

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

	public void delPdf(String id) {
		try {

			String sql = "update usuario set curriculobase64 = '', contenttypecurriculo = '' where id = '" + id + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeUpdate();

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
			StringBuilder sql = new StringBuilder();

			sql.append(" update usuario set login = ?, senha = ?, nome = ?, email = ?, ");
			sql.append(" cep = ?, rua = ?, bairro = ?, cidade = ?, uf = ? , ativo = ?");

			if (usuario.isAtualizarImagem()) {
				sql.append(" , fotobase64 = ?, contenttype = ? ");
			}

			if (usuario.isAtualizarPdf()) {
				sql.append(" , curriculobase64 = ?, contenttypecurriculo = ? ");
			}

			if (usuario.isAtualizarImagem()) {
				sql.append(" , fotobase64Miniatura = ? ");
			}

			sql.append(" where id = " + usuario.getId());

			PreparedStatement update = connection.prepareStatement(sql.toString());

			update.setString(1, usuario.getLogin());
			update.setString(2, usuario.getSenha());
			update.setString(3, usuario.getNome());
			update.setString(4, usuario.getEmail());
			update.setString(5, usuario.getCep());
			update.setString(6, usuario.getRua());
			update.setString(7, usuario.getBairro());
			update.setString(8, usuario.getCidade());
			update.setString(9, usuario.getUf());
			update.setBoolean(10, usuario.isAtivo());

			if (usuario.isAtualizarImagem()) {
				update.setString(11, usuario.getFotoBase64());
				update.setString(12, usuario.getContentType());
			}

			if (usuario.isAtualizarPdf()) {
				if (usuario.isAtualizarPdf() && !usuario.isAtualizarImagem()) {
					update.setString(11, usuario.getCurriculoBase64());
					update.setString(12, usuario.getContentTypeCurriculo());
				} else {
					update.setString(13, usuario.getCurriculoBase64());
					update.setString(14, usuario.getContentTypeCurriculo());
				}
			} else {
				if (usuario.isAtualizarImagem()) {
					update.setString(13, usuario.getFotoBase64Miniatura());
				}
			}
			
			if (usuario.isAtualizarImagem() && usuario.isAtualizarPdf()) {
				update.setString(15, usuario.getFotoBase64Miniatura());
			}

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
		String sql = "select * from usuario where id = '" + id + "' and login <> 'admin'";
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
			usuario.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			usuario.setContentType(resultSet.getString("contenttype"));
			usuario.setCurriculoBase64(resultSet.getString("curriculobase64"));
			usuario.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			usuario.setAtivo(resultSet.getBoolean("ativo"));

			return usuario;

		} else {
			return null;
		}

	}

	// =============================================================
	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) as qtd from usuario where login = '" + login + "' ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;/*
												 * se n�o existir um login igual o count retorna '0' e o return tendo
												 * qtd <= 0 � true.
												 */

		} else {
			return false;
		}

	}

	// =============================================================
	public boolean validarLoginUpdate(String login, String id) throws Exception {
		String sql = "SELECT COUNT(1) as qtd FROM usuario WHERE login = '" + login + "' AND id <> " + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;/*
												 * se n�o existir um login igual o count retorna '0' e o return tendo
												 * qtd <= 0 � true.
												 */

		} else {
			return false;
		}

	}

	// =============================================================
	public boolean validarSenha(String senha) throws Exception {
		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "' ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;/*
												 * se n�o existir um login igual o count retorna '0' e o return tendo
												 * qtd <= 0 � true.
												 */

		} else {
			return false;
		}

	}
}
