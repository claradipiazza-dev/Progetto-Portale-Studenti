package query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import database.Database;
import model.Aule;


public class Aule_service {

    // METODO PER OTTENERE LE AULE DI PIÙ MATERIE
    public static Aule ottieni_Aule(List<Integer> matIds) {

        Aule aule = new Aule();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        if (matIds == null || matIds.isEmpty()) {
            return aule;
        }

        try {
            connection = Database.execute();

            // Costruzione dinamica della IN (?, ?, ?)
            String placeholders = String.join(
                    ",",
                    matIds.stream().map(id -> "?").toArray(String[]::new)
            );

            String sql = "SELECT * FROM aula WHERE mat_id IN (" + placeholders + ")";
            statement = connection.prepareStatement(sql);

            // Binding parametri
            for (int i = 0; i < matIds.size(); i++) {
                statement.setInt(i + 1, matIds.get(i));
            }

            rs = statement.executeQuery();

            while (rs.next()) {
                Aule aula = new Aule(
                        rs.getInt("id_aula"),
                        rs.getString("nome_aula"),
                        rs.getInt("edificio"),
                        rs.getInt("mat_id")
                );
                aule.setLista(aula);
            }

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (statement != null) statement.close(); } catch (SQLException ignored) {}
            try { if (connection != null) connection.close(); } catch (SQLException ignored) {}
        }

        return aule;
    }
    
    // METODO PER OTTENERE LE AULE DI PIÙ MATERIE

 	public static Aule ottieniAule(int mat_id) {

 		Aule aula = null;
 		Aule aule = new Aule();
 		Connection connection = null;
 		PreparedStatement statement = null;
 		ResultSet rs = null;

 		try {

 			connection = Database.execute();
 			String sql = "SELECT * FROM aula WHERE mat_id=?";
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, mat_id);
 			rs = statement.executeQuery();

 			while (rs.next()) {			
 				aula = new Aule(rs.getInt("id_aula"), rs.getString("nome_aula"),
 						rs.getInt("edificio"), rs.getInt("mat_id"));
 				aule.setLista(aula);
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

 		return aule;

 	}
}
