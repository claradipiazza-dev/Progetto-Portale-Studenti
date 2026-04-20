<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Navbar</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="css/navbar.css">
   

</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg" id="nav1">
    <div class="container-fluid">

        <a class="navbar-brand">
            <img src="img/logo.png" alt="Logo" class="rounded-circle" height="40">
        </a>
        
            <a class="nav-link" href="Home" style="margin-left: 10px;">
        <i class="fa fa-home"></i> <b>Home</b>
    </a>

        <div class="collapse navbar-collapse show">
            <ul class="navbar-nav ms-auto align-items-center">
            
<c:choose>
    <c:when test="${not empty sessionScope.utente and sessionScope.isIscritto}">
        <li class="nav-item">
            <a class="nav-link" href="Aula">
                <i class="fa fa-university"></i> <b>Piano di studi</b>
            </a>
        </li>
    </c:when>
</c:choose>

                <c:choose>
                    <c:when test="${not empty sessionScope.utente}">

                        <li class="nav-item">
                            <a class="nav-link" href="ProfiloUtente">
                                <i class="fa fa-user me-1"></i><b>Profilo</b>
                            </a>
                        </li>
						
						<li class="nav-item">
                            <a class="nav-link" href="CorsiLaurea?corso_laurea_id=1">
                                <i class="fas fa-graduation-cap me-1"></i><b>Corsi di Laurea</b>
                            </a>
                        </li>
						
                        <!-- DROPDOWN UTENTE -->
                        <div class="dropdown">
                            <button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown" id="logout">
      							<i class="fas fa-user me-1"></i>
                                <b>Ciao, <c:out value="${sessionScope.utente.nome}" /></b>
    						</button>
                            
                            <ul class="dropdown-menu">
                                <li>
                                    <a class="dropdown-item" href="Logout">
                                        <i class="fas fa-sign-out-alt me-2"></i>Logout
                                    </a>
                                </li>
                            </ul>
                       </div>
                    </c:when>

                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="#"
                               data-bs-toggle="modal"
                               data-bs-target="#myModalAccesso">
                                <i class="fas fa-user me-1"></i><b>Accedi</b>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </div>
</nav>



<!-- INIZIO Modal ACCESSO -->
<div class="modal fade" id="myModalAccesso" tabindex="-1" aria-labelledby="accessoModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-md">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">
                    <i class="fas fa-sign-in-alt me-2"></i><b>Accedi al tuo account</b>
                </h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <!--INIZIO FORM ACCESSO-->
                <div id="contenitoreAccesso">
                    <form name="formAccesso" id="Accesso" action="Accesso" method="post">
                        <div class="form-group">
                            <label for="email"><i class="fas fa-envelope me-1"></i> <b>Email</b></label>
                            <div class="input-group">
                                <input type="email" class="form-control" id="email" name="email"
                                    placeholder="nome@esempio.com" required="required"
                                    autocomplete="on" />
                                <div class="input-group-append">
                                    <span class="input-group-text bg-transparent border-0">
                                        <i class="fas fa-at text-muted"></i>
                                    </span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="password"><i class="fas fa-lock me-1"></i> <b>Password</b></label>
                            <div class="input-group">
                                <input type="password" class="form-control" id="password"
                                    name="password" placeholder="La tua password"
                                    required="required" autocomplete="on" />
                                <div class="input-group-append">
                                    <button type="button" class="btn" id="togglePassword">
                                        <i class="fas fa-eye text-muted"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                                             
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="rememberMe">
                                <label class="form-check-label" for="rememberMe">
                                    Ricordami
                                </label>
                            </div>
                         </div>
                        
                        <p id="messaggi"></p>
                        <!-- EVENTUALI MESSAGGI -->
                        
                        <button type="submit" id="login" class="btn btn-lg">
                            <i class="fas fa-sign-in-alt me-2"></i>Accedi
                        </button>            
                                         
                   </form>
                </div>
                <!-- FINE FORM ACCESSO  -->
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">
                    <i class="fas fa-times me-2"></i>Chiudi
                </button>
            </div>
        </div>
    </div>
</div>
<!-- FINE Modal ACCESSO -->

<div class="immagine"> 
    <img src="img/sfondo2.jpg" alt="Ateneo" style="height: 120px; width: 120px; object-fit: cover;" class="rounded-circle mb-3 shadow">
    <h4 style="color: #0b408d;"><b>Università degli studi di Palermo</b></h4>
    <hr class="mx-auto" style="width: 50px; height: 3px; background-color: #0b408d; opacity: 1;"> 
</div>
<hr>

<script>
// Funzione base per mostrare/nascondere password
document.getElementById('togglePassword').addEventListener('click', function() {
    const passwordInput = document.getElementById('password');
    const icon = this.querySelector('i');
    
    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        icon.classList.remove('fa-eye');
        icon.classList.add('fa-eye-slash');
    } else {
        passwordInput.type = 'password';
        icon.classList.remove('fa-eye-slash');
        icon.classList.add('fa-eye');
    }
});

document.querySelectorAll('.dropdown-submenu > a').forEach(function (element) {
    element.addEventListener('click', function (e) {
        e.preventDefault();
        e.stopPropagation();

        let submenu = this.nextElementSibling;
        if (submenu) {
            submenu.classList.toggle('show');
        }
    });
});

</script>

</body>
</html>