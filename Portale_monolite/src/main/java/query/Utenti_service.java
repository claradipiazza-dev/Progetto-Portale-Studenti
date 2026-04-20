package query;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import database.Database;
import model.Utenti;

public class Utenti_service {

	// METODO PER IL LOGIN

	public static Utenti accesso(String email, String pass)
			throws SQLException, NamingException, NoSuchAlgorithmException {

		Utenti utente = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM utenti WHERE email=? and password=?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, pass);
			rs = statement.executeQuery();

			if (rs.next()) {
				// Recupera l'ID dell'utente dal ResultSet
				int idUtente = rs.getInt("id_utenti");
				// Setta l'ID nell'oggetto Utenti solo se non è zero
				if (idUtente != 0) {
					utente = new Utenti(idUtente, rs.getString("nome"), rs.getString("cognome"),
							rs.getString("telefono"), rs.getString("email"), rs.getString("matricola"),
							rs.getString("password"));
				}
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

		return utente;
	}

	

	// METODO PER VERIFICARE L'ESISTENZA DELL'EMAIL

	public static boolean checkMailExistence(String email) throws NamingException, SQLException {

		boolean esiste = false; // se l'email è nel DB, allora verrà settato a true
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM utenti WHERE email=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			rs = statement.executeQuery();

			while (rs.next()) // SE TROVA UN RECORD, QUESTO SARA' UNICO
			{
				esiste = true;
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

		return esiste; // falso se non esiste, vero se esiste
	}

	// METODO PER L'HASH DELLA PASSWORD

	public static String passwordHash(String password) throws NoSuchAlgorithmException {

		String passwordHash;

		// Istanza di MD5
		MessageDigest md = MessageDigest.getInstance("MD5");

		// Aggiunta di bytes alla password
		md.update(password.getBytes());

		// Ottenimento dei bytes dell'hash
		byte[] bytes = md.digest();

		// Questo bytes[] ha i bytes in formato decimale
		// vengono convertiti in formato esadecimale
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		// Ottenimento dell'hash della password
		passwordHash = sb.toString();
		return passwordHash;
	}


	

	public static void aggiornaUtente(Utenti utente) throws SQLException, NamingException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = Database.execute();
			String sql = "UPDATE utenti SET password = ? WHERE email = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, utente.getPassword());
			statement.setString(2, utente.getEmail());
			statement.executeUpdate();
			
		} finally {
			// Chiudo le risorse
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	// METODO PER VERIFICARE L'ESISTENZA DELLA PASSWORD

	public static boolean checkPassword(String password)
			throws NamingException, SQLException, NoSuchAlgorithmException {

		boolean esiste = false; // se la password è nel DB, allora verrà settato a true
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM utenti WHERE password=?";
			statement = connection.prepareStatement(sql);
			String passwordHash = passwordHash(password);
			statement.setString(1, passwordHash);
			rs = statement.executeQuery();

			if (rs.next()) // SE TROVA UN RECORD, QUESTO SARA' UNICO
			{
				esiste = true;
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

		return esiste; // falso se non esiste, vero se esiste
	}

	// METODO PER VERIFICARE L'ESISTENZA DI UN NUMERO DI TELEFONO

	public static boolean checkTelefono(String numero) throws NamingException, SQLException, NoSuchAlgorithmException {

		boolean esiste = false; // se il telefono è nel DB, allora verrà settato a true
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM utenti WHERE telefono=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, numero);
			rs = statement.executeQuery();

			if (rs.next()) // SE TROVA UN RECORD, QUESTO SARA' UNICO
			{
				esiste = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudi la connessione, lo statement e il ResultSet nel blocco finally per
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

		return esiste; // falso se non esiste, vero se esiste
	}

}
