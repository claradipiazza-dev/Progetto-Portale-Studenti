package model;

import java.util.LinkedList;
import java.util.List;

public class Utenti {
	private int id_utenti;
	private String nome;
	private String cognome;
	private String numero;
	private String email;
	private String matricola;
	private String password; 
	private List<Utenti> utenti;

	public Utenti() {
		this.utenti = new LinkedList<Utenti>();
	}
	
	//METODO PER L'ACCESSO
	public Utenti (int id_utenti, String nome, String cognome, String numero, String email, String matricola, String password)
		{
		
		    this.id_utenti=id_utenti;
			this.nome=nome;
			this.cognome=cognome;
			this.numero=numero;
			this.email=email;
			this.matricola=matricola;
			this.password= password;
		}
	
			
	
//------INIZIO METODI SET------
		
		public void setId(int id_utenti)
		{
			this.id_utenti=id_utenti;
		}
		
		public void setNome(String nome)
		{
			this.nome=nome;
		}
		
		public void setCognome(String cognome)
		{
			this.cognome=cognome;
		}
		
		public void setNumero(String numero)
		{
			this.numero=numero;
		}
		
		public void setEmail(String email)
		{
			this.email=email;
		}
		
		public void setMatricola(String matricola) {
			this.matricola = matricola;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		
		public void setLista(Utenti utente) {
			utenti.add(utente);
		}
		
		
//------FINE METODI SET------
		
		
//------INIZIO METODI GET------				
		
	public int getId() {
		return this.id_utenti;
	}

	public String getNome() {
		return this.nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public String getNumero() {
		return this.numero;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getMatricola() {
		return this.matricola;
	}
	
	public String getPassword() {
		return this.password;
	}
	

	public List<Utenti> getLista() {
		return this.utenti;
	}
			
//------FINE METODI GET------
	
}