package dto;


public class AulaDTO {

	private int id_aula;
	private String nome_aula;
	private int edificio; 
	private int mat_id;

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
		

}
