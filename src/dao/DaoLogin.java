package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DaoLogin {

	private Connection connection;

	public DaoLogin() { // construtor padrão
		connection = SingleConnection.getConnection(); // recebe o objeto de
														// conexão com o BD

	}

	/**
	 * Método para validar o usuario
	 * 
	 * @param login
	 * @param senha
	 * @return true caso o usuario exista no DB
	 * @throws Exception
	 *             caso ocorra exceção joga pra cima
	 */
	public boolean validarLogin(String login, String senha) throws Exception {
		String sql = "select * from usuario where login = '" + login
				+ "' and senha = '" + senha + "'";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return true;// existe o usuário
		} else {
			return false; // não existe o usuario
		}
	}
}
