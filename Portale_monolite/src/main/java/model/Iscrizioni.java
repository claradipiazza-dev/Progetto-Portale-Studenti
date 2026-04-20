package model;

import java.util.LinkedList;
import java.util.List;
import java.time.LocalDate;

public class Iscrizioni {
	
	private int id_iscrizione;
	private LocalDate data;
	private int utenti_id; 
	private int insegnamento_id;
	private String stato = "non attiva" ; //nessuna iscrizione
	private List<Iscrizioni> iscrizioni;

	public Iscrizioni() {
		this.iscrizioni = new LinkedList<Iscrizioni>();
	}
	
	//METODO PER L'ACCESSO
	public Iscrizioni (int id_iscrizione, LocalDate data, int utenti_id, int insegnamento_id, String stato)
		{
		
		    this.id_iscrizione=id_iscrizione;
			this.data=data;
			this.utenti_id=utenti_id;
			this.insegnamento_id=insegnamento_id;
			this.stato=stato;
			this.iscrizioni= new LinkedList<Iscrizioni>(); 
		}
	
	public Iscrizioni(List<Iscrizioni> iscrizione) {
		this.iscrizioni = iscrizione;
	}
	
//------INIZIO METODI SET------
		
		public void setId(int id_iscrizione)
		{
			this.id_iscrizione=id_iscrizione;
		}
		
		public void setData(LocalDate data)
		{
			this.data=data;
		}
		
		public void setUtentiId(int utenti_id)
		{
			this.utenti_id=utenti_id;
		}
		
		public void setInsegnamentoId(int insegnamento_id)
		{
			this.insegnamento_id=insegnamento_id;
		}
				
		public void setStato(String stato) {
		    this.stato = stato;
		}
		
		public void setLista(Iscrizioni iscrizione) {
			iscrizioni.add(iscrizione);
		}
		
		
//------FINE METODI SET------
		
		
//------INIZIO METODI GET------				
		
	public int getId() {
		return this.id_iscrizione;
	}
	
	public LocalDate getData() {
		return this.data;
	}
	
	public int getUtentiId() {
		return this.utenti_id;
	}


	public int getInsegnamentoId() {
		return this.insegnamento_id;
	}

	public String getStato() {
	    return stato;
	}
	
	public List<Iscrizioni> getLista() {
		return this.iscrizioni;
	}
			
//------FINE METODI GET------
	
}