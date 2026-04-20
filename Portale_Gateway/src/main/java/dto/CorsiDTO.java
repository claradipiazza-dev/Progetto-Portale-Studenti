package dto;

import java.util.LinkedList;
import java.util.List;

public class CorsiDTO {
	private int id_corso_laurea;
	private String nome_corso;
	private String livello; 
	private List<CorsiDTO> corsi;

	public CorsiDTO() {
		this.corsi = new LinkedList<CorsiDTO>();
	}
	
	//METODO PER L'ACCESSO
	public CorsiDTO (int id_corso_laurea, String nome_corso, String livello)
		{
		
		    this.id_corso_laurea=id_corso_laurea;
			this.nome_corso=nome_corso;
			this.livello=livello;
			this.corsi= new LinkedList<CorsiDTO>(); 
		}
	
	public CorsiDTO (List<CorsiDTO> corso) {
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
				
		public void setLista(CorsiDTO corso) {
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
	
	public List<CorsiDTO> getLista() {
		return this.corsi;
	}
			
//------FINE METODI GET------
	
}