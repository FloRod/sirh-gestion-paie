<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<title>Liste des employés</title>
</head>
<body>
	<a href="/paie">Retour Accueil</a>
	<h1>Liste des employés</h1>

	<table class="table table-bordered table-striped">
		<tr>
			<th>Matricule</th>
			<th>Grade</th>
			<th>Entreprise</th>
		</tr>
		<c:forEach var="employe" items="${listeEmployes}">
			<tr>
				<td>${employe.matricule}</td>
				<td>${employe.grade.code}</td>
				<td>${employe.entreprise.denomination}</td>
			</tr>
		</c:forEach>
	</table>
	<p><a href="creer">Ajout d'un employé</a></p>
	<p><a href="liste">Liste des bulletins de salaire</a></p>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"</script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</html>