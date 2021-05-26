package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Respons�vel por fazer a conex�o com o banco de dados
 * @author Samrick
 *
 */

public class SingleConnection {

	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "postgres";
	private static String user = "postgres";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	public SingleConnection(){
		conectar();
	}
	
	private static void conectar(){
		try {
			//fazendo a conex�o com o DB; carregando o driver do jdbc
			Class.forName("org.postgresql.Driver");
			//parametro do drive para o conector
			connection = DriverManager.getConnection(banco, user, password);
			//deixo sem auto commit para ter controle sobre esta fun��o
			connection.setAutoCommit(false); 
			
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}
	
	/**
	 * M�todo para retornar a conex�o
	 */
	public static Connection getConnection(){
		return connection;
	}

}
