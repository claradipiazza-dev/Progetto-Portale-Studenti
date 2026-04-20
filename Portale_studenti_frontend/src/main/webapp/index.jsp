<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">
    <title>Portale Studenti</title>
<style>
        /* Stili per ingrandire il contenuto della descrizione */
        .descrizione {
            padding: 2rem 1rem;
        }
        
        .descrizione .testo h1 {
            font-size: 2.8rem; /* Titolo più grande */
            margin-bottom: 2rem !important;
        }
        
        .descrizione .card {
            border-radius: 15px;
            border: none;
        }
        
        .descrizione .lead {
            font-size: 1.5rem; /* Testo lead più grande */
            margin-bottom: 2rem !important;
        }
        
        .descrizione .card-body p {
            font-size: 1.2rem; /* Paragrafo più grande */
            line-height: 1.7;
            margin-bottom: 1.5rem;
        }
        
        .descrizione .alert {
            border-radius: 12px;
            border-left: 5px solid #0dcaf0;
        }
        
        .descrizione .alert-heading {
            font-size: 1.4rem; /* Titolo alert più grande */
            margin-bottom: 1rem;
        }
        
        .descrizione .alert ul {
            font-size: 1.15rem; /* Lista più grande */
        }
        
        .descrizione .alert li {
            margin-bottom: 0.7rem;
            line-height: 1.6;
        }
        
        /* Icona più grande */
        .descrizione .bi-mortarboard-fill {
            font-size: 2rem;
            margin-right: 0.5rem;
            vertical-align: middle;
        }
        
        /* Icona info più grande */
        .descrizione .bi-info-circle {
            font-size: 1.3rem;
            margin-right: 0.5rem;
        }
        
        /* Spaziatura aumentata */
        .descrizione .card-body {
            padding: 3rem !important;
        }
        
</style>

</head>
<body>
    <!-- NAVBAR -->
    <jsp:include page="/WEB-INF/components/navbar_portale.jsp" />
    <!-- FINE NAVBAR -->


<div class="descrizione">
    <div class="testo">
        <h1 class="text-center mb-4" style="color: mediumvioletred;">
            <b>Portale Studenti</b>
        </h1>
        
                <div class="row justify-content-center">
            <div class="col-md-10 col-lg-9"> <!-- Colonna leggermente più larga -->
                <div class="card shadow-lg"> <!-- Ombra più pronunciata -->
                    <div class="card-body p-5">
                        <p class="lead text-center mb-4">
                            <i class="bi bi-mortarboard-fill" style="color: mediumvioletred;"></i>
                            <strong>Benvenuto nel Portale Studenti dell'Università</strong>
                        </p>
                        
                        <p class="mb-4">
                            Questo portale è lo strumento ufficiale per la gestione della tua carriera universitaria. 
                            Qui potrai consultare il piano di studi, iscriverti ai corsi, visualizzare le tue iscrizioni 
                            e rimanere aggiornato sulle attività didattiche.
                        </p>
                        
                        <div class="alert alert-info mt-5">
                            <h5 class="alert-heading">
                                <i class="bi bi-info-circle"></i> Cosa puoi fare sul portale:
                            </h5>
                            <ul class="mb-0">
                                <li><strong>Piano di Studi:</strong> Consulta l'offerta formativa completa</li>
                                <li><strong>Iscrizione Corsi:</strong> Iscriviti ai corsi del tuo percorso</li>
                                <li><strong>Gestione Iscrizioni:</strong> Visualizza e gestisci le tue iscrizioni attive</li>
                                <li><strong>Area Personale:</strong> Accedi ai tuoi dati e al tuo profilo studente</li>
                            </ul>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
  <br>
<!-- FOOTER -->
<jsp:include page="/WEB-INF/components/footer.jsp" />
<!-- FINE FOOTER -->

</body>
</html>
