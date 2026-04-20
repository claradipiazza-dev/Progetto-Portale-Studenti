package query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

import database.Database;
import model.Materie;


public class Materie_service {

	// METODO PER OTTENETE LE ISCRIZIONI DELL'UTENTE

	public static Materie ottieniMaterie(int ins_id) {

		Materie materia = null;
		Materie materie = new Materie();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {

			connection = Database.execute();
			String sql = "SELECT * FROM materie WHERE ins_id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, ins_id);
			rs = statement.executeQuery();

			while (rs.next()) {	
				materia = new Materie(rs.getInt("id_materie"), rs.getInt("anno_corso"), 
						rs.getInt("codice"), rs.getString("nome_materia"), rs.getInt("cfu"), rs.getInt("ins_id"));
				materie.setLista(materia);
			}

		} catch (SQLException | NamingException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione, lo statement e il result set nel blocco finally per
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

		return materie;

	}




}
