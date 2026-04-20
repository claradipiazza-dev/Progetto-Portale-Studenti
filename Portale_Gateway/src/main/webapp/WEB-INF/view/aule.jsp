<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Aule </title>
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

<div class="descrizione" style="margin-left: 1.5rem;">
        <h2 style="color: mediumvioletred; font-size: 2rem !important;"><b>Benvenuto nel tuo piano di studi</b></h2>
                <p style="font-size: 1.3rem;">
            Ora puoi vedere tutte le materie del tuo percorso con i relativi dettagli, 
            come CFU, aule e edifici.
        </p>
 </div>
 <br>
<c:if test="${not empty iscrizioni}">
<div class="container">

		<h2 class="text-center mb-4" style="color: mediumvioletred;">
			Piano di Studi – Ingegneria Informatica
		</h2>

		<!-- TABELLA PIANO DI STUDI -->
		<table class="table table-bordered table-hover text-center">
			<thead class="table-dark">
				<tr>
					<th>Anno Corso</th>
					<th>Codice</th>
					<th>Materia</th>
					<th>CFU</th>
					<th>Aule</th>
					<th>Edificio</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${materie}" var="m">
					<tr>
						<td>${m.anno}</td>
						<td>${m.codice}</td>
						<td>${m.nomeMateria}</td>
						<td>${m.CFU}</td>
						<!-- COLONNA AULE -->
        <td>
            <c:forEach items="${aule}" var="a">
                <c:if test="${a.mat_id == m.id}">
                    ${a.nomeAula}<br>
                </c:if>
            </c:forEach>
        </td>

        <!-- COLONNA EDIFICIO -->
        <td>
            <c:forEach items="${aule}" var="a">
                <c:if test="${a.mat_id == m.id}">
                    ${a.edificio}<br>
                </c:if>
            </c:forEach>
        </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
<br>
		<!-- BOTTONE ELIMINA ISCRIZIONE -->
		<div class="text-center mt-4">
			<form action="EliminazioneIscrizione" method="post">
			<!-- AZIONE -->
    			<input type="hidden" name="azione" value="elimina">
				<input type="hidden" name="ins_id" value="${iscrizioni[0].insegnamentoId}">
				<button type="submit" class="btn btn-danger btn-lg">
					Elimina Iscrizione
				</button>
			</form>
		</div>

	</div>
<br>
</c:if>


<c:if test="${empty iscrizioni}">
	<p style="font-size: 2rem !important; margin-left: 1.5rem;"><b> Nessuna iscrizione al momento </b></p>
</c:if>

	<div class="button" style="margin-left: 1.5rem;">
	<a type="button" role="button" class="btn btn-primary btn-lg"
		href="Home">Torna indietro </a>
	</div>

<!-- FOOTER -->
<jsp:include page="/WEB-INF/components/footer.jsp" />
<!-- FINE FOOTER -->

</body>
</html>