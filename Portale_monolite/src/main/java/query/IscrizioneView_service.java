package query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import database.Database;
import model.IscrizioneView;


public class IscrizioneView_service {

    // METODO PER OTTENERE LE ISCRIZIONI DELL'UTENTE
	public static List<IscrizioneView> ottieniIscrizioniConDettagli(int utenteId) 
	        throws NamingException, SQLException {
	    
	    List<IscrizioneView> lista = new ArrayList<>();
	    String sql = """
	        SELECT i.id_iscrizione, i.data_iscrizione, i.stato,
	               ins.nome_insegnamento,
	               cl.nome_corso, cl.livello
	        FROM iscrizione i
	        JOIN insegnamento ins ON i.insegnamento_id = ins.id_insegnamento
	        JOIN corso_laurea cl ON ins.corso_laurea_id = cl.id_corso_laurea
	        WHERE i.utenti_id = ?
	    """;

	    // Try-with-resources: le risorse tra parentesi tonde vengono chiuse AUTOMATICAMENTE
	    try (Connection conn = Database.execute(); 
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        ps.setInt(1, utenteId);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                IscrizioneView iv = new IscrizioneView();
	                iv.setIdIscrizione(rs.getInt("id_iscrizione"));
	                iv.setDataIscrizione(rs.getDate("data_iscrizione").toLocalDate());
	                iv.setStato(rs.getString("stato"));
	                iv.setNomeInsegnamento(rs.getString("nome_insegnamento"));
	                iv.setNomeCorso(rs.getString("nome_corso"));
	                iv.setLivello(rs.getString("livello"));
	                
	                lista.add(iv);
	            }
	        }
	    } catch (SQLException | NamingException e) {
	        e.printStackTrace();
	        throw e; 
	    }

	    return lista;
	}

}
