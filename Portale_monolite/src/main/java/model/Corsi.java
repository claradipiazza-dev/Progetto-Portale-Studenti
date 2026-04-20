package model;

import java.util.LinkedList;
import java.util.List;

public class Corsi {
	private int id_corso_laurea;
	private String nome_corso;
	private String livello; 
	private List<Corsi> corsi;

	public Corsi() {
		this.corsi = new LinkedList<Corsi>();
	}
	
	//METODO PER L'ACCESSO
	public Corsi (int id_corso_laurea, String nome_corso, String livello)
		{
		
		    this.id_corso_laurea=id_corso_laurea;
			this.nome_corso=nome_corso;
			this.livello=livello;
			this.corsi= new LinkedList<Corsi>(); 
		}
	
	public Corsi(List<Corsi> corso) {
		this.corsi = corso;
	}
	
//------INIZIO METODI SET------
		
		public void setId(int id_corso_laurea)
		{
			this.id_corso_laurea=id_corso_laurea;
		}
		
		public void setNomeCorso(String nome_corso)
		{
			this.nome_corso=nome_corso;
		}
		
		public void setLivello(String livello)
		{
			this.livello=livello;
		}
				
		public void setLista(Corsi corso) {
			corsi.add(corso);
		}
		
		
//------FINE METODI SET------
		
		
//------INIZIO METODI GET------				
		
	public int getId() {
		return this.id_corso_laurea;
	}

	public String getNomeCorso() {
		return this.nome_corso;
	}

	public String getLivello() {
		return this.livello;
	}
	
	public List<Corsi> getLista() {
		return this.corsi;
	}
			
//------FINE METODI GET------
	
}