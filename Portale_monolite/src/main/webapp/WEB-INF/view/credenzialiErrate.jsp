<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Credenziali Errate - Portale Studenti</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">
<style>
    body {
        background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
        min-height: 100vh;
        display: flex;
        flex-direction: column;
    }
    
    .error-container {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 20px;
    }
    
    .error-card {
        background: white;
        border-radius: 20px;
        box-shadow: 0 10px 30px rgba(0,0,0,0.1);
        padding: 40px;
        max-width: 500px;
        width: 100%;
        text-align: center;
        border-top: 5px solid #dc3545;
        animation: fadeIn 0.5s ease;
    }
    
    .error-icon {
        font-size: 80px;
        color: #dc3545;
        margin-bottom: 20px;
        animation: bounce 1s infinite alternate;
    }
    
    .error-title {
        color: #dc3545;
        font-weight: 700;
        margin-bottom: 15px;
    }
    
    .error-message {
        color: #6c757d;
        margin-bottom: 25px;
        font-size: 1.1rem;
    }
    
    .btn-retry {
        background: linear-gradient(135deg, #dc3545, #bb2d3b);
        color: white;
        border: none;
        padding: 12px 30px;
        border-radius: 8px;
        font-weight: 600;
        transition: all 0.3s ease;
        margin: 5px;
    }
    
    .btn-retry:hover {
        transform: translateY(-2px);
        box-shadow: 0 5px 15px rgba(220, 53, 69, 0.3);
        color: white;
    }
    
    .btn-secondary-custom {
        background: linear-gradient(135deg, #6c757d, #545b62);
        color: white;
        border: none;
        padding: 12px 30px;
        border-radius: 8px;
        font-weight: 600;
        transition: all 0.3s ease;
        margin: 5px;
    }
    
    .btn-secondary-custom:hover {
        transform: translateY(-2px);
        box-shadow: 0 5px 15px rgba(108, 117, 125, 0.3);
        color: white;
    }
    
    .tip-box {
        background-color: #f8f9fa;
        border-left: 4px solid #ffc107;
        padding: 15px;
        border-radius: 8px;
        margin-top: 25px;
        text-align: left;
    }
    
    .tip-title {
        color: #856404;
        font-weight: 600;
        margin-bottom: 8px;
    }
    
    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(20px); }
        to { opacity: 1; transform: translateY(0); }
    }
    
    @keyframes bounce {
        from { transform: translateY(0); }
        to { transform: translateY(-10px); }
    }
    
    .support-info {
        margin-top: 20px;
        color: #6c757d;
        font-size: 0.9rem;
    }
</style>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar_portale.jsp" />

<!-- CONTENUTO PRINCIPALE -->
<div class="error-container">
    <div class="error-card">
        <!-- ICONA ERRORE -->
        <div class="error-icon">
            <i class="bi bi-shield-exclamation"></i>
        </div>
        
        <!-- TITOLO -->
        <h1 class="error-title">
            Accesso Non Riuscito
        </h1>
        
        <!-- MESSAGGIO -->
        <p class="error-message">
            Le credenziali inserite non sono corrette. <br>
            Verifica email e password e riprova.
        </p>
        
         
        <!-- CONSIGLI UTILI -->
        <div class="tip-box">
            <div class="tip-title">
                <i class="bi bi-lightbulb"></i> Suggerimenti:
            </div>
            <ul class="mb-0" style="padding-left: 20px; color: #6c757d;">
                <li>Controlla che l'email sia quella istituzionale</li>
                <li>Verifica che CAPS LOCK non sia attivo</li>
                <li>Assicurati di aver completato la registrazione</li>
                <li>Prova a reimpostare la password se necessario</li>
            </ul>
        </div>
        
        <!-- INFORMAZIONI SUPPORTO -->
        <div class="support-info">
            <i class="bi bi-envelope"></i> Per assistenza: supporto@universita.it
        </div>
    </div>
</div>

<!-- MODAL PER AIUTO -->
<div class="modal fade" id="helpModal" tabindex="-1" aria-labelledby="helpModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="helpModalLabel" style="color: #dc3545;">
                    <i class="bi bi-life-preserver"></i> Assistenza Accesso
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p><strong>Problemi di accesso comuni:</strong></p>
                <ul>
                    <li><strong>Email sbagliata:</strong> Usa l'email istituzionale fornita dall'Università</li>
                    <li><strong>Password dimenticata:</strong> Contatta la segreteria per reimpostarla</li>
                    <li><strong>Account non attivo:</strong> Completa la procedura di registrazione</li>
                    <li><strong>Problemi tecnici:</strong> Prova con un browser diverso o cancella la cache</li>
                </ul>
                <hr>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
            </div>
        </div>
    </div>
</div>

<!-- FOOTER -->
<jsp:include page="/WEB-INF/components/footer.jsp" />


</body>
</html>