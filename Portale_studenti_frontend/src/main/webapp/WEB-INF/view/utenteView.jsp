<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Utente </title>
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
  <style>
    /* Stili per ingrandire il container di benvenuto */
    .container.mt-5 {
        margin-top: 4rem !important; /* Più margine superiore */
    }
    
    .alert-heading {
        font-size: 2rem !important; /* Titolo più grande */
        margin-bottom: 1.5rem;
    }
    
    .alert-heading i {
        font-size: 2.2rem;
        margin-right: 0.7rem;
        color: mediumvioletred;
        vertical-align: middle;
    }
    
    .container.mt-5 p {
        font-size: 1.3rem; /* Paragrafo più grande */
        line-height: 1.8;
        margin-bottom: 1rem;
    }
    
    .container.mt-5 strong {
        font-size: 1.4rem;
        color: mediumvioletred;
    }
    
    .container.mt-5 hr {
        border-width: 2px;
        border-color: mediumvioletred;
        opacity: 0.7;
        margin: 2rem 0;
    }
    
    /* Aggiungi un po' di stile alla card di benvenuto */
    .container.mt-5 .col-md-12 {
        background-color: #f8f9fa;
        border-radius: 15px;
        padding: 2.5rem !important;
        box-shadow: 0 4px 15px rgba(0,0,0,0.08);
        border-left: 5px solid mediumvioletred;
    }
    
    /* Stili per la tabella iscrizioni */
    .container h2 {
        font-size: 2.2rem;
        margin-bottom: 2rem !important;
    }
    
    .table {
        font-size: 1.1rem;
    }
    
    .table thead th {
        font-size: 1.2rem;
        padding: 1rem;
    }
    
    .table tbody td {
        padding: 0.9rem;
    }
    
</style>

</head>
<body>

<!-- INIZIO NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar_portale.jsp" />
<!-- FINE NAVBAR -->

<!-- Messaggio di benvenuto -->
<div class="container mt-5">
    <div class="row">
        <div class="col-md-12">
                  <h4 class="alert-heading">
                    <i class="bi bi-person-check"></i> Accesso effettuato con successo!
                </h4>
                <p>
                    Benvenuto/a <strong>${utente.nome} ${utente.cognome}</strong> nella tua area personale.
                    Da qui puoi gestire tutte le attività relative alla tua carriera universitaria.
                </p>
                <hr>
        </div>
    </div>
</div>

<br>

<c:if test="${not empty iscrizioni}">
<div class="container">

		<h2 class="text-center mb-4" style="color: mediumvioletred;">
			Dettagli Iscrizioni
		</h2>

		<!-- TABELLA PIANO DI STUDI -->
		<table class="table table-bordered table-hover text-center">
			<thead class="table-dark">
				<tr>
					<th>Corso di Laurea</th>
					<th>Livello</th>
					<th>Insegnamento</th>
					<th>Data iscrizione</th>
					<th>Stato</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${iscrizioni}" var="i">
					<tr>
						<td>${i.nomeCorso}</td>
						<td> ${i.livello} </td>
						<td>${i.nomeInsegnamento}</td>
						<td>${i.dataIscrizione}</td>
						<td>${i.stato}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
<br>

	</div>
<br>
</c:if>

<!-- FOOTER -->
<jsp:include page="/WEB-INF/components/footer.jsp" />
<!-- FINE FOOTER -->

</body>
</html>