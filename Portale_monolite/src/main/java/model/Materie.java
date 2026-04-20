package model;

import java.util.LinkedList;
import java.util.List;

public class Materie {
	private int id_materia;
	private int anno_corso;
	private int codice;
	private String nome_materia;
	private int cfu; 
	private int ins_id; 
	private List<Materie> materie;

	public Materie() {
		this.materie = new LinkedList<Materie>();
	}
	
	//METODO PER L'ACCESSO
	public Materie (int id_materia, int anno_corso, int codice, String nome_materia, int cfu, int ins_id)
		{
		
		    this.id_materia=id_materia;
		    this.anno_corso=anno_corso;
		    this.codice=codice;
			this.nome_materia=nome_materia;
			this.cfu=cfu;
			this.ins_id=ins_id;
			this.materie= new LinkedList<Materie>(); 
		}
	
	public Materie(List<Materie> materia) {
		this.materie = materia;
	}
	
//------INIZIO METODI SET------
		
		public void setId(int id_materia)
		{
			this.id_materia=id_materia;
		}
		
		public void setAnno(int anno_corso)
		{
			this.anno_corso=anno_corso;
		}
		
		public void setCodice(int codice)
		{
			this.codice=codice;
		}
		
		public void setNomeMateria(String nome_materia)
		{
			this.nome_materia=nome_materia;
		}
		
		public void setCFU(int cfu)
		{
			this.cfu=cfu;
		}
		
		public void setInsId(int ins_id)
		{
			this.ins_id=ins_id;
		}
		
		public void setLista(Materie materia) {
			materie.add(materia);
		}
		
		
//------FINE METODI SET------
		
		
//------INIZIO METODI GET------				
		
	public int getId() {
		return this.id_materia;
	}
	
	public int getAnno() {
		return this.anno_corso;
	}
	
	public int getCodice() {
		return this.codice;
	}

	public String getNomeMateria() {
		return this.nome_materia;
	}

	public int getCFU() {
		return this.cfu;
	}
	
	public int getInsId() {
		return this.ins_id;
	}
	
	public List<Materie> getLista() {
		return this.materie;
	}
			
//------FINE METODI GET------
	
}