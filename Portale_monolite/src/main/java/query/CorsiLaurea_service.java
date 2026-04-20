package query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import database.Database;
import model.Corsi;


public class CorsiLaurea_service {

	
	// METODO PER OTTENERE I CORSI

	public static Corsi ottieniCorsi() throws NamingException, SQLException {

		Corsi corso = null;
		Corsi corsi = new Corsi();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM corso_laurea ;";
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();

			while (rs.next()) {			
				corso = new Corsi(rs.getInt("id_corso_laurea"), rs.getString("nome_corso"),
						rs.getString("livello"));
				corsi.setLista(corso);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione, lo statement e il ResultSet nel blocco finally per
			// garantire il rilascio delle risorse
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return corsi;

	}
	
	// METODO PER OTTENERE IL LIVELLO DEL CORSO DI LAUREA

		public static Corsi ottieniLivello(int id_corso) throws NamingException, SQLException {

			Corsi corso = null;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet rs = null;

			try {
				connection = Database.execute();
				String sql = "SELECT * FROM corso_laurea WHERE id_corso_laurea=? ;";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, id_corso);
				rs = statement.executeQuery();

				while (rs.next()) {			
					corso = new Corsi(rs.getInt("id_corso_laurea"), rs.getString("nome_corso"),
							rs.getString("livello"));
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				// Chiudo la connessione, lo statement e il ResultSet nel blocco finally per
				// garantire il rilascio delle risorse
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			return corso;

		}

}
