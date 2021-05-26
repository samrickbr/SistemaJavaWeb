package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Responsável por fazer a conexão com o banco de dados
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
			//fazendo a conexão com o DB; carregando o driver do jdbc
			Class.forName("org.postgresql.Driver");
			//parametro do drive para o conector
			connection = DriverManager.getConnection(banco, user, password);
			//deixo sem auto commit para ter controle sobre esta função
			connection.setAutoCommit(false); 
			
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}
	
	/**
	 * Método para retornar a conexão
	 */
	public static Connection getConnection(){
		return connection;
	}

}
