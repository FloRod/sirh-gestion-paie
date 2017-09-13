<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<title>Ajouter un employé</title>
</head>
<body>

	<a href="/paie">Retour Accueil</a>
	<h1>Ajouter un employé</h1>

	<form action = <%=request.getContextPath()%>/mvc/employes/validerForm method="post">
		<p>Matricule : <input type="text" name="matricule"></p>
		
		<p>Entreprise : <select name = "entrepriseId">
			<c:forEach var="entreprise" items="${listeEntreprises}">
				<option value="${entreprise.id}">${entreprise.denomination}</option>
			</c:forEach>
		</select></p>
		
		<p>Profil : <select name = "profilId">
			<c:forEach var="profil" items="${listeProfils}">
				<option value="${profil.id}">${profil.code}</option>
			</c:forEach>
		</select></p>
		
		<p>Grade : <select name = "gradeId">
			<c:forEach var="grade" items="${listeGrades}">
				<option value="${grade.id}">${grade.code}</option>
			</c:forEach>
		</select></p>
		<button class="btn-primary" type="submit">Valider</button>
	</form>

</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src ="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</html>