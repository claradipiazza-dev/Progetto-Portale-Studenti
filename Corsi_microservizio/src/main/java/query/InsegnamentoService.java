package query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import database.Database;
import model.Insegnamenti;


public class InsegnamentoService {


	// METODO PER OTTENERE UN INSEGNAMENTO IN BASE ALL'ID
	public static Insegnamenti ottieniInsegnamentoPerId(int id) throws NamingException, SQLException {

	    Insegnamenti insegnamento = null;
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet result = null;

	    try {
	        connection = Database.execute();
	        String sql = "SELECT * FROM insegnamento WHERE id_insegnamento = ?;";
	        statement = connection.prepareStatement(sql);
	        statement.setInt(1, id);
	        result = statement.executeQuery();

	        if (result.next()) {
	            insegnamento = new Insegnamenti(
	                result.getInt("id_insegnamento"),
	                result.getString("nome_ins"),   
	                result.getInt("corso_id")       // foreign key verso corsi_laurea
	            );
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Chiudo tutte le risorse
	        if (result != null) {
	            try {
	                result.close();
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

	    return insegnamento;
	}


	// METODO PER OTTENERE I CORSI

		public static Insegnamenti ottieniInsegnamentiCorso(int corso_laurea_id) throws NamingException, SQLException {

			Insegnamenti insegnamento = null;
			Insegnamenti insegnamenti = new Insegnamenti();
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet rs = null;

			try {
				connection = Database.execute();
				String sql = "SELECT * FROM insegnamento WHERE corso_id=? ;";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, corso_laurea_id);
				rs = statement.executeQuery();

				while (rs.next()) {			
					insegnamento = new Insegnamenti(rs.getInt("id_insegnamento"), rs.getString("nome_ins"),
							rs.getInt("corso_id"));
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
