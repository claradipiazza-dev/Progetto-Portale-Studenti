package query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import database.Database;
import model.Corsi;


public class CorsoService {

      
    // METODO PER OTTENERE I CORSI 	

	public static Corsi ottieniCorsi() throws NamingException, SQLException {

		Corsi corso = null;
		Corsi corsi = new Corsi();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			connection = Database.execute();
			String sql = "SELECT * FROM corsi_laurea ;";
			statement = connection.prepareStatement(sql);
			result = statement.executeQuery();

			while (result.next()) {
				corso = new Corsi(result.getInt("id_corsi"), result.getString("nome_corso"),
						result.getString("livello"));
				corsi.setLista(corso);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Chiudo tutte le risorse in un blocco finally
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

		return corsi;

	}

	
	// METODO PER OTTENERE UN CORSO IN BASE ALL'ID
	public static Corsi ottieniCorsoPerId(int id) throws NamingException, SQLException {

	    Corsi corso = null;
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet result = null;

	    try {
	        connection = Database.execute();
	        String sql = "SELECT * FROM corsi WHERE id_corsi = ?;";
	        statement = connection.prepareStatement(sql);
	        statement.setInt(1, id);
	        result = statement.executeQuery();

	        if (result.next()) {
	            corso = new Corsi(result.getInt("id_corsi"), result.getString("nome_corso"),
	                    result.getString("livello"));
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

	    return corso;
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
