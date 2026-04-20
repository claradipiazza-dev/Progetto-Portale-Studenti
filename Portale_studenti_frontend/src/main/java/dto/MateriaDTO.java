package dto;

public class MateriaDTO {

    private int id;
    private int anno;
    private int codice;
    private String nomeMateria;
    private int CFU;

    // Costruttore vuoto (OBBLIGATORIO per JSP / JSTL)
    public MateriaDTO() {
    }

    // Metodi Getter e Setter

    public int getId() {
        return id;
    }

    public int getAnno() {
        return anno;
    }
    
    public int getCodice() {
        return codice;
    }
    
    public String getNomeMateria() {
        return nomeMateria;
    }
    
    public int getCFU() {
        return CFU;
    }

    // Metodi Setter
    
    public void setId(int id) {
        this.id = id;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }

    public void setCFU(int cFU) {
        CFU = cFU;
    }
}
