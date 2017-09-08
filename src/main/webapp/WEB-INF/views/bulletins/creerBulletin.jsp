<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=UTF-8">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
<<a href ="/paie">Retour Accueil</a>
<h1>Créer un bulletin de salaire</h1>

<form action = <%=request.getContextPath()%>/mvc/bulletins/valider method="post">
		<p>Période : <select name = "periodeId">
			<c:forEach var="periode" items="${listePeriodes}">
				<option value="${periode.id}">${periode.dateDebut} - ${periode.dateFin}</option>
			</c:forEach>
		</select></p>
		
		<p>Matricule : <select name = "employeId">
			<c:forEach var="employe" items="${listeEmployes}">
				<option value="${employe.id}">${employe.matricule}</option>
			</c:forEach>
		</select></p>
		
		<p>Prime Exceptionnelle : <input type="text" name="prime"></p>
		
		<button class="btn-primary" type="submit">Valider</button>
	</form>

</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"</script>
<script src ="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</html>