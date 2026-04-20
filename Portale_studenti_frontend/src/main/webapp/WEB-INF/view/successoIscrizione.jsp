<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Successo Iscrizione </title>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>

<!-- INIZIO NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar_portale.jsp" />
<!-- FINE NAVBAR -->

<div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card text-center shadow">
                    <div class="card-header bg-success text-white">
                        <h4><i class="fas fa-check-circle"></i> Iscrizione Confermata!</h4>
                    </div>
                    <div class="card-body">
                        <p class="card-text">Ti sei iscritto correttamente al corso di laurea.</p>
                        <p class="card-text">Ora puoi visualizzare il tuo piano di studi.</p>
                        
                        <!-- Bottone per tornare alla home utente -->
                        <a href="Home" class="btn btn-primary mt-3">
                            <i class="fas fa-home me-2"></i>Torna alla Home Utente
                        </a>
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