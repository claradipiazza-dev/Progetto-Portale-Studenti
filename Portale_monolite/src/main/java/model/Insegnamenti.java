package model;

import java.util.LinkedList;
import java.util.List;

public class Insegnamenti {
	private int id_insegnamento;
	private String nome_insegnamento;
	private int corso_laurea_id; 
	private List<Insegnamenti> insegnamenti;

	public Insegnamenti() {
		this.insegnamenti = new LinkedList<Insegnamenti>();
	}
	
	//METODO PER L'ACCESSO
	public Insegnamenti (int id_insegnamento, String nome_insegnamento,int corso_laurea_id)
		{
		
		    this.id_insegnamento=id_insegnamento;
			this.nome_insegnamento=nome_insegnamento;
			this.corso_laurea_id=corso_laurea_id;
			this.insegnamenti= new LinkedList<Insegnamenti>(); 
		}
	
	public Insegnamenti(List<Insegnamenti> insegnamento) {
		this.insegnamenti = insegnamento;
	}
	
//------INIZIO METODI SET------
		
		public void setId(int id_insegnamento)
		{
			this.id_insegnamento=id_insegnamento;
		}
		
		public void setNomeInsegnamento(String nome_insegnamento)
		{
			this.nome_insegnamento=nome_insegnamento;
		}
		
		public void setCorso(int corso_laurea_id)
		{
			this.corso_laurea_id=corso_laurea_id;
		}
				
		public void setLista(Insegnamenti insegnamento) {
			insegnamenti.add(insegnamento);
		}
		
		
//------FINE METODI SET------
		
		
//------INIZIO METODI GET------				
		
	public int getId() {
		return this.id_insegnamento;
	}

	public String getNomeInsegnamento() {
		return this.nome_insegnamento;
	}

	public int getCorso() {
		return this.corso_laurea_id;
	}
	
	public List<Insegnamenti> getLista() {
		return this.insegnamenti;
	}
			
//------FINE METODI GET------
	
}