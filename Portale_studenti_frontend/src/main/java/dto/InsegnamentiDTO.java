package dto;

import java.util.LinkedList;
import java.util.List;

public class InsegnamentiDTO {
	private int id_insegnamento;
	private String nome_insegnamento;
	private int corso_laurea_id; 
	private List<InsegnamentiDTO> insegnamenti;

	public InsegnamentiDTO() {
		this.insegnamenti = new LinkedList<InsegnamentiDTO>();
	}
	
	//METODO PER L'ACCESSO
	public InsegnamentiDTO (int id_insegnamento, String nome_insegnamento,int corso_laurea_id)
		{
		
		    this.id_insegnamento=id_insegnamento;
			this.nome_insegnamento=nome_insegnamento;
			this.corso_laurea_id=corso_laurea_id;
			this.insegnamenti= new LinkedList<InsegnamentiDTO>(); 
		}
	
	public InsegnamentiDTO (List<InsegnamentiDTO> insegnamento) {
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
				
		public void setLista(InsegnamentiDTO insegnamento) {
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
	
	public List<InsegnamentiDTO> getLista() {
		return this.insegnamenti;
	}
			
//------FINE METODI GET------
	
}