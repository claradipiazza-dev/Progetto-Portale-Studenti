<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
 #foot {
        opacity: 0.9;
        margin-top: 3rem !important;
        color: white;
        background-color: #0b408d;
        width: 100%;
        overflow-x: hidden; /* Previene lo scroll orizzontale */
    }
    
    /* RIMUOVI O MODIFICA QUESTO BLOCCO PROBLEMATICO */
    #foot .col-lg-3:first-child {
        padding-left: 1px !important; /* Usa padding normale di Bootstrap */
        margin-left: 0 !important;     /* RIMUOVI il margine negativo */
    }
    
    /* Aggiungi questo per garantire che non ci siano overflow */
    body {
        overflow-x: hidden;
        margin: 0;
        padding: 0;
    }
	
</style>   
<footer class="footer mt-auto py-4" id="foot">
<div class="container">
        <div class="row">
            <!-- Dove Siamo -->
            <div class="col-lg-3 col-md-6 mb-4">
                <h5 class="text-white mb-4">Dove Siamo</h5>
               <p class="text-white">
					Piazza Marina, 61
                </p>
                <p class="text-white">
					90133 - PALERMO
                </p>
               <p class="text-white">
					Codice Fiscale 80023730825, Partita IVA 00605880822
                </p>
            </div>

            <!-- Contatti -->
            <div class="col-lg-6 col-md-6 mb-4">
                <h5 class="text-white mb-4"><i class="far fa-envelope me-2"></i>Contatti</h5>
                <ul class="list-unstyled">
                       <li class="mb-2">
                        Call center studenti <i class="fas fa-mobile-alt me-2"></i> 091 238 86472
                    </li>
                    <li class="mb-2">
                        Telefono Amm. C.le di P.zza Marina, 61 <i class="fas fa-mobile-alt me-2"></i> 091 238 93011
                    </li>
                    <li class="mb-2">
                        URP <i class="far fa-envelope me-2"></i>urp@unipa.it <i class="fas fa-mobile-alt me-2"></i> 091 238 93666
                    </li>
                    <li class="mb-2">
                        PEC <i class="far fa-envelope me-2"></i>pec@cert.unipa.it 
                    </li>
                     <li class="mb-2">
                        Webmaster <i class="far fa-envelope me-2"></i>webmaster@unipa.it
                </ul>
            </div>
	</div>
</div>

<!-- Back to top button -->
<button type="button" class="btn btn-danger btn-floating btn-lg" id="btn-back-to-top" style="position: fixed; bottom: 20px; right: 20px; display: none; opacity: 0.7; z-index: 1000;">
    <i class="fas fa-arrow-up"></i>
</button>
</footer>
<!-- Script per il pulsante "Torna su" -->
<script>
    document.addEventListener('DOMContentLoaded', function() {
        var backToTopButton = document.getElementById("btn-back-to-top");
        
        // Quando l'utente scorre verso il basso di 20px dalla cima del documento, mostra il pulsante
        window.onscroll = function() {
            if (document.body.scrollTop > 300 || document.documentElement.scrollTop > 300) {
                backToTopButton.style.display = "block";
                // Animazione di fade-in
                setTimeout(function() {
                    backToTopButton.style.opacity = "0.7";
                }, 50);
            } else {
                backToTopButton.style.opacity = "0";
                // Attendi che l'animazione di fade-out sia completa prima di nascondere
                setTimeout(function() {
                    if (document.body.scrollTop <= 300 && document.documentElement.scrollTop <= 300) {
                        backToTopButton.style.display = "none";
                    }
                }, 300);
            }
        };
        
        // Quando l'utente clicca sul pulsante, torna in cima al documento
        backToTopButton.addEventListener("click", function() {
            // Per Safari
            document.body.scrollTop = 0;
            // Per Chrome, Firefox, IE e Opera
            document.documentElement.scrollTop = 0;
        });
    });
</script>