package database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Database {
	public static Connection execute() throws NamingException, SQLException {
		Context context = new InitialContext();
		DataSource database = (DataSource) context.lookup("java:comp/env/jdbc/utenti");
		Connection connection = database.getConnection();
		return connection;
	}
}
