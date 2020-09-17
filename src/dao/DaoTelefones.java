package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;

import connection.SingleConnection;
import beans.BeanProduto;
import beans.BeanTelefones;

public class DaoTelefones {

	private Connection connection;

	public DaoTelefones() {
		connection = SingleConnection.getConnection();
	}

	// ===========================================================
	public void salvar(BeanTelefones telefone) {

		String sql = "INSERT INTO telefone (numero, tipo, usuario) VALUES (?, ?, ?)";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
			insert.execute();

			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public List<BeanTelefones> listar(Long usuario) throws Exception {

		List<BeanTelefones> listar = new ArrayList<BeanTelefones>();

		String sql = "select * from telefone where usuario = " + usuario;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanTelefones telefone = new BeanTelefones();
			telefone.setId(resultSet.getLong("id"));
			telefone.setNumero(resultSet.getString("numero"));
			telefone.setTipo(resultSet.getString("tipo"));
			telefone.setUsuario(resultSet.getLong("usuario"));

			listar.add(telefone);

		}

		return listar;
	}

	public void deletar(Long id) {
		try {
			String sql = "delete from telefone where id = '" + id + "'";
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
}
