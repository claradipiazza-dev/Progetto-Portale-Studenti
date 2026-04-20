<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Corsi di Laurea</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
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

<jsp:include page="/WEB-INF/components/navbar_portale.jsp" />
<br>

<div class="container">

	<h2 class="text-center mb-4" style="color: mediumvioletred;"><b>Corsi di Laurea</b></h2>

	<table class="table table-bordered table-hover text-center">
		<thead class="table-dark">
			<tr>
				<th class="fs-5">Corso di Laurea</th> 
				<th class="fs-5">Livello</th>
				<th class="fs-5">Azioni</th> 
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${insegnamenti.getLista()}" var="i">
				<tr>
					<td class="fs-5">${i.nomeInsegnamento}</td>
					<td class="fs-5">${livello.getLivello()}</td>
					<td>
						<c:if test="${i.nomeInsegnamento eq 'Ingegneria Informatica'}">
							<form action="IscrizioneCorso" method="post">
								<input type="hidden" name="InsId" value="${i.id}">
								<button class="btn btn-success btn-lg">
									Iscriviti
								</button>
							</form>
						</c:if>
						<c:if test="${i.nomeInsegnamento ne 'Ingegneria Informatica'}">
							<span class="text-muted fs-5">Non disponibile</span> 
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>

<jsp:include page="/WEB-INF/components/footer.jsp" />

</body>
</html>