package dto;

import java.time.LocalDate;

public class IscrizioneDTO {

	private int id_iscrizione;
	private LocalDate data;
	private int utenti_id; 
	private int insegnamento_id;
	private String stato = "non attiva" ; //nessuna iscrizione


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

}
