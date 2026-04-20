<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profilo Utente</title>
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
.informazioni {
    width: 70%;
    margin: 0 auto; /* centra orizzontalmente senza usare left/position */
}


.div-group {
	border: 3px solid;
	border-radius: 8px;
	border-style: groove;
	text-align: center;
	font-size: 18px;
	border-color: lightsteelblue;
	font-family: 'Caveat', cursive; 
	font-size: 20px;
	margin-bottom: 20px;
}

.head {
	background-color: lightsteelblue;
	/*opacity: 0.8;*/
	color: darkblue;
	border-radius: 8px;
	padding-bottom: 12px;;
}

.linea-divisoria {
	border-top: 2px solid black;
	margin-top: 10px;
	margin-bottom: 10px;
}

#avanti {
	position: relative;
	left: 45%;
}

.modal-title {
	color: red;
	font-size: 30px;
}

.form-group {
	position: relative;
	font-size: 16px;
	text-align: center;
}

#imgcarta {
	width: 10%;
	height: 10%;
}

#imgcarte {
	width: 15%;
	height: 15%;
}

#imgacquisti {
	width: 10%;
	height: 10%;
}

#info {
	color: darkblue;
}

#divRadice h2 {
    margin-top: 30px;
    margin-bottom: 30px;
    text-align: center;
}

#divRadice h3 {
    margin-bottom: 20px;
    text-align: center;
}

.div-group {
    margin-bottom: 20px;
}

.button {
    text-align: center;
    margin-top: 30px;
    margin-bottom: 30px;
}

</style>

</head>
<body id="radice">

	<!-- NAVBAR -->
	<jsp:include page="/WEB-INF/components/navbar_portale.jsp" /><br><br>
	<!-- FINE NAVBAR -->
	<div id="divRadice">

	<h2
		style="color: mediumvioletred; text-align: center; font-size: 45px;  font-family: 'Caveat', cursive;">
		<b> Benvenuto nel tuo profilo! </b>
	</h2>
	<br>
	<br>

	<h3 style="text-align: center;  font-family: 'Caveat', cursive;" id="info">
		<b> <img id="imgcarta" src="img/iconautente.jpg"
			alt="Immagine utente">Informazioni personali
		</b>
	</h3>
	<br>

	<div class="informazioni">

		<div class="div-group">
			<p class="head">
				<b> Nome: </b>
			</p>
			<p>${utente.nome}</p>
		</div>
		<br>

		<div class="div-group">
			<p class="head">
				<b> Cognome: </b>
			</p>
			<p>${utente.cognome}</p>
		</div>
		<br>

		<div class="div-group">
			<p class="head">
				<b> Email: </b>
			</p>
			<p>${utente.email}</p>
		</div>
		<br>

		<div class="div-group">
			<p class="head">
				<b> Numero di telefono: </b>
			</p>
			<p>${utente.numero}</p>
		</div>
		<br>
		
		<div class="div-group">
			<p class="head">
				<b> Numero di matricola: </b>
			</p>
			<p>${utente.matricola}</p>
		</div>
		<br>

	</div>

	<div class="button">
	<a type="button" role="button" class="btn btn-primary btn-lg"
		href="Home">Torna indietro </a>
	</div>

</div>

<!-- FOOTER -->
<jsp:include page="/WEB-INF/components/footer.jsp" />
<!-- FINE FOOTER -->


</body>
</html>