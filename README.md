Portale Studenti - Analisi Monolite vs Microservizi
Questo progetto rappresenta il lavoro svolto per l'analisi e la comparazione di due architetture software differenti applicate a un sistema di gestione universitaria.

## Descrizione del Progetto
Il sistema simula un portale universitario per la gestione di aule, corsi, iscrizioni e carriere degli utenti. La particolarità del progetto risiede nella sua doppia implementazione, che permette di osservare come lo stesso dominio applicativo si comporti in scenari architetturali differenti.

### 1. Approccio Monolitico
Implementato come un'unica unità logica, dove tutte le componenti (autenticazione, logica di business e accesso ai dati) risiedono nello stesso processo.
* **Pattern:** MVC (Model-View-Controller) puro.
* **Tecnologia:** Java Web (Servlet/JSP) senza l'ausilio di framework come Spring Boot, per un controllo a basso livello del ciclo di vita delle richieste.

### 2. Approccio a Microservizi
Il monolite è stato separato in servizi indipendenti e autonomi (Aule, Corsi, Iscrizioni, Materie, Utenti).
* **Comunicazione:** Basata su protocollo **HTTP/REST** con scambio dati in formato **JSON**.
* **Struttura:** Ogni microservizio è isolato, favorendo la scalabilità orizzontale delle singole componenti.
* **Gateway:** Un front-end dedicato che funge da punto di ingresso unico per le chiamate API verso i microservizi.

---

## Stack Tecnologico
* **Backend:** Java Core, Java Servlet API.
* **Pattern Architetturale:** MVC (Model-View-Controller).
* **Data Exchange:** JSON (per l'interazione tra microservizi e API REST).
* **Database:** MySQL (gestito tramite MySQL Workbench).
* **Frontend:** HTML5, CSS3, JavaScript, jQuery, Bootstrap.

---

## Performance Testing & Analisi
Per valutare l'efficacia delle due architetture, è stato utilizzato **Apache JMeter** con l'obiettivo di misurare il comportamento del sistema sotto stress e identificare colli di bottiglia strutturali.

### Metriche di Valutazione:
* **TTFB (Time To First Byte):** Per misurare la reattività del server all'invio del primo pacchetto.
* **Durata complessiva della richiesta:** Tempo totale dall'invio della richiesta alla ricezione completa della risposta.
* **Throughput:** Numero di transazioni al secondo gestite dal sistema.
* **Payload:** Analisi della dimensione dei dati scambiati (particolarmente rilevante nel formato JSON dei microservizi).

Questo approccio analitico ha permesso di confrontare oggettivamente l'overhead di rete introdotto dalla comunicazione inter-servizio rispetto alla chiamata locale del monolite.
