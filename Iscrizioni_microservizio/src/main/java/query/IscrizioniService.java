package query;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import java.time.LocalDate;

import database.Database;
import model.Iscrizioni;


public class IscrizioniService {

	// METODO PER OTTENETE LE ISCRIZIONI DELL'UTENTE

	public static Iscrizioni ottieniIscrizioni(int utente_id) {

		Iscrizioni iscrizione = null;
		Iscrizioni iscrizioni = new Iscrizioni();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {

			connection = Database.execute();
			String sql = "SELECT * FROM iscrizione WHERE utente_id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, utente_id);
			rs = statement.executeQuery();

			while (rs.next()) {
				LocalDate data_iscrizione = rs.getObject("data", LocalDate.class);
				
				iscrizione = new Iscrizioni(rs.getInt("id_iscrizione"), data_iscrizione,
						rs.getInt("utente_id"), rs.getInt("insegnamento_id"), rs.getString("stato"));
				iscrizioni.setLista(iscrizione);
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

		return iscrizioni;

	}

	

	// METODO PER OTTENERE LO STATO DELL'ISCRIZIONE

	public static String stato(int utente_id) {

		String stato = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT stato FROM iscrizione WHERE utente_id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, utente_id);
			rs = statement.executeQuery();
			if (rs.next()) {
				stato = rs.getString("stato");
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

		return stato;
	}


	// METODO PER VERIFICARE L'ESISTENZA DELLA ISCRIZIONE

		public static boolean checkIscrizione(int utente_id, int ins_id) throws NamingException, SQLException {

			boolean esiste = false;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;

			try {

				connection = Database.execute();
				String sql = "SELECT * FROM iscrizione WHERE utente_id=? AND insegnamento_id=?;";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, utente_id);
				statement.setInt(2, ins_id);
				result = statement.executeQuery();

				while (result.next()) {

					esiste = true;
				}

			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				// Chiudo la connessione, lo statement e il result set nel blocco finally per
				// garantire il rilascio delle risorse
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

			return esiste; // falso se non esiste, vero se esiste
		}

	
	
	// METODO PER ELIMINARE UNA ISCRIZIONE

	public static void eliminaIscrizione(int utente_id, int insegnamento_id) throws NamingException, SQLException, NoSuchAlgorithmException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = Database.execute();
			String sql = "DELETE FROM iscrizione WHERE utente_id=? AND insegnamento_id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, utente_id);
			statement.setInt(2, insegnamento_id);
			statement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione e lo statement nel blocco finally per
			// garantire il rilascio delle risorse
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

	}

	// METODO PER OTTENERE LE ISCRIZIONI

	public static Iscrizioni ottieniIscrizioni() throws NamingException, SQLException {

		Iscrizioni iscrizione = null;
		Iscrizioni iscrizioni = new Iscrizioni();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM iscrizione ;";
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();

			while (rs.next()) {
				LocalDate data_iscrizione = rs.getObject("data", LocalDate.class);
				//Timestamp dataTimestamp = rs.getTimestamp("data_iscrizione");
				//LocalDateTime data_iscrizione = dataTimestamp.toLocalDateTime();
				
				iscrizione = new Iscrizioni(rs.getInt("id_iscrizione"), data_iscrizione,
						rs.getInt("utente_id"), rs.getInt("insegnamento_id"), rs.getString("stato"));
				iscrizioni.setLista(iscrizione);
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

		return iscrizioni;

	}
	
	// METODO PER AGGIUNGERE UNA ISCRIZIONE

		public static void inserisciIscrizione(LocalDate data_iscrizione, int utenti_id, int ins_id, String stato)
				throws NamingException, SQLException, NoSuchAlgorithmException {

			Connection connection = null;
			PreparedStatement statement = null;

			try {
				connection = Database.execute();
				String sql = "INSERT INTO iscrizione (`data`, `utente_id`, `insegnamento_id`, `stato` ) VALUES (?, ?, ?, ?);";
				statement = connection.prepareStatement(sql);
				statement.setObject(1, data_iscrizione);
				statement.setInt(2, utenti_id);
				statement.setInt(3, ins_id);
				statement.setString(4, stato);
				statement.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				// Chiudo la connessione e lo statement nel blocco finally per garantire il
				// rilascio delle risorse
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

		}


}
