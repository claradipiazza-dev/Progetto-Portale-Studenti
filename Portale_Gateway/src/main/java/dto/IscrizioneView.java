package dto;

import java.time.LocalDate;

public class IscrizioneView {

    private int idIscrizione;
    private LocalDate dataIscrizione;
    private String stato;

    private String nomeInsegnamento;
    private String nomeCorso;
    private String livello;

    // ===== COSTRUTTORE VUOTO =====
    public IscrizioneView() {
    }

    // ===== COSTRUTTORE COMPLETO =====
    public IscrizioneView(int idIscrizione, LocalDate dataIscrizione, String stato, String nomeInsegnamento, String nomeCorso, String livello) {
        
    	this.idIscrizione = idIscrizione;
        this.dataIscrizione = dataIscrizione;
        this.stato = stato;
        this.nomeInsegnamento = nomeInsegnamento;
        this.nomeCorso = nomeCorso;
        this.livello = livello;
    }

    // ===== SET =====
    public void setIdIscrizione(int idIscrizione) {
        this.idIscrizione = idIscrizione;
    }

    public void setDataIscrizione(LocalDate dataIscrizione) {
        this.dataIscrizione = dataIscrizione;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public void setNomeInsegnamento(String nomeInsegnamento) {
        this.nomeInsegnamento = nomeInsegnamento;
    }

    public void setNomeCorso(String nomeCorso) {
        this.nomeCorso = nomeCorso;
    }

    public void setLivello(String livello) {
        this.livello = livello;
    }

    // ===== GET =====
    public int getIdIscrizione() {
        return idIscrizione;
    }

    public LocalDate getDataIscrizione() {
        return dataIscrizione;
    }

    public String getStato() {
        return stato;
    }

    public String getNomeInsegnamento() {
        return nomeInsegnamento;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public String getLivello() {
        return livello;
    }
}
