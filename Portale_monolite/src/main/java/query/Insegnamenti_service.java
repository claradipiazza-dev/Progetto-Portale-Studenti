package query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import database.Database;
import model.Insegnamenti;


public class Insegnamenti_service {

	
	// METODO PER OTTENERE I CORSI

	public static Insegnamenti ottieniInsegnamenti(int corso_laurea_id) throws NamingException, SQLException {

		Insegnamenti insegnamento = null;
		Insegnamenti insegnamenti = new Insegnamenti();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM insegnamento WHERE corso_laurea_id=? ;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, corso_laurea_id);
			rs = statement.executeQuery();

			while (rs.next()) {			
				insegnamento = new Insegnamenti(rs.getInt("id_insegnamento"), rs.getString("nome_insegnamento"),
						rs.getInt("corso_laurea_id"));
				insegnamenti.setLista(insegnamento);
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

		return insegnamenti;

	}

}
