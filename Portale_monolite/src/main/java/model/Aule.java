package model;

import java.util.LinkedList;
import java.util.List;

public class Aule {
	private int id_aula;
	private String nome_aula;
	private int edificio; 
	private int mat_id;
	private List<Aule> aule;

	public Aule() {
		this.aule = new LinkedList<Aule>();
	}
	
	//METODO PER L'ACCESSO
	public Aule (int id_aula, String nome_aula, int edificio, int mat_id)
		{
		
		    this.id_aula=id_aula;
			this.nome_aula=nome_aula;
			this.edificio=edificio;
			this.mat_id = mat_id;
			this.aule= new LinkedList<Aule>(); 
		}
	
	public Aule(List<Aule> aula) {
		this.aule = aula;
	}
	
//------INIZIO METODI SET------
		
		public void setId(int id_aula)
		{
			this.id_aula=id_aula;
		}
		
		public void setNomeAula(String nome_aula)
		{
			this.nome_aula=nome_aula;
		}
		
		public void setEdificio(int edificio)
		{
			this.edificio=edificio;
		}
		
		public void setMat_id(int mat_id)
		{
			this.mat_id=mat_id;
		}
				
		public void setLista(Aule aula) {
			aule.add(aula);
		}
		
		
//------FINE METODI SET------
		
		
//------INIZIO METODI GET------				
		
	public int getId() {
		return this.id_aula;
	}

	public String getNomeAula() {
		return this.nome_aula;
	}
	
	public int getEdificio() {
		return this.edificio;
	}
	
	public int getMat_id() {
		return this.mat_id;
	}
	
	public List<Aule> getLista() {
		return this.aule;
	}
			
//------FINE METODI GET------
	
}