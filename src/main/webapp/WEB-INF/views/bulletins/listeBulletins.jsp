<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<title>Liste des bulletins de salaire</title>
</head>
<body>
<li><a href ="/paie">Retour Accueil</a></li>
<h1>Liste des bulletins de salaire</h1>

<table class="table table-bordered table-striped">
		<tr>
			<th>Date/heure création</th>
			<th>Période</th>
			<th>Matricule</th>
		</tr>
		<c:forEach var="bulletin" items="${listeBulletins}">
			<tr>
				<td>${bulletin.dateCreation}</td>
				<td>${bulletin.periode.dateDebut} / ${bulletin.periode.dateFin}</td>
				<td>${bulletin.remunerationEmploye.matricule}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>