<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Piano di Studi</title>
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

<style type="text/css">
.button {
    text-align: center;
    margin-top: 30px;
    margin-bottom: 30px;
}

</style>

</head>

<body>

	<!-- NAVBAR -->
	<jsp:include page="/WEB-INF/components/navbar_portale.jsp" />
	<br><br>

	
<c:if test="${not empty errore}">
    <div class="alert alert-danger text-center" role="alert">
        ${errore}
    </div>
</c:if>


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
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${materie}" var="m">
					<tr>
						<td>${m.anno}</td>
						<td>${m.codice}</td>
						<td>${m.nomeMateria}</td>
						<td>${m.CFU}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
<br>
		<!-- BOTTONE ISCRIZIONE -->
		<div class="text-center mt-4">
			<form action="IscrizioneCorso" method="post">
			    <!-- AZIONE -->
    			<input type="hidden" name="azione" value="crea">
				<input type="hidden" name="InsId" value="${ins_id}">
				 <!-- Passa SOLO gli ID delle materie come array -->
        	<c:forEach items="${materie}" var="m">
            <input type="hidden" name="mat_id" value="${m.id}">
        	</c:forEach>
				<button type="submit" class="btn btn-success btn-lg">
					Iscriviti al corso
				</button>
			</form>
		</div>

	</div>
<br>

	<div class="button">
	<a type="button" role="button" class="btn btn-primary btn-lg"
		href="Home">Torna indietro </a>
	</div>


<!-- FOOTER -->
<jsp:include page="/WEB-INF/components/footer.jsp" />
<!-- FINE FOOTER -->

</body>
</html>
