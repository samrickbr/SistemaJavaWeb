package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import connection.SingleConnection;

/**
 * Filter - intercepta as requisições e levanta a conexão
 * @author Samrick
 *
 */

//mapear o Filter
@WebFilter(urlPatterns={"/*"})
public class Filter implements javax.servlet.Filter{
	
	private static Connection connection; //iniciar a conexão para o filter receber a conexão iniciada

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		try {
			arg2.doFilter(arg0, arg1); //intercepatar as request e retornar as response
			
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

		connection = SingleConnection.getConnection(); //sobe a conexão
		
	}
	
	 

}
