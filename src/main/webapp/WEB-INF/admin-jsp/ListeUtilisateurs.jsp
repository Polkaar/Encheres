<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des utilisateurs</title>
</head>
<body>

	<header>
		<h1 style="text-align: left;"><a href="AccueilServlet">ENI-Encheres</a></h1>
	</header>
	
	<h2>Liste des utilisateurs</h2>
	
	<table>
		<tr>
	    	<td>PSEUDO</td>
	   		<td>NOM</td>
	   		<td>PRENOM</td>
	   		<td>EMAIL</td>
	   		<td>TELEPHONE</td>
	   		<td>RUE</td>
	   		<td>CODE POSTAL</td>
	   		<td>VILLE</td>
	   		<td>CREDITS</td>
	   		<td>ADMIN</td>
	   		<td>NB ARTICLES ENCHERIS</td>
	   		<td>NB VENTES EN COURS</td>
	   		<td>DETAILS</td>
		</tr>
		<c:forEach items="${model.lstUsersDonnees}" var="user">
			<tr>
		    	<td>${user.utilisateur.pseudo}</td>
		   		<td>${user.utilisateur.nom}</td>
		   		<td>${user.utilisateur.prenom}</td>
		   		<td>${user.utilisateur.email}</td>
		   		<td>${user.utilisateur.telephone}</td>
		   		<td>${user.utilisateur.rue}</td>
		   		<td>${user.utilisateur.codePostal}</td>
		   		<td>${user.utilisateur.ville}</td>
		   		<td>${user.utilisateur.credit}</td>
		   		<td>${user.utilisateur.administrateur}</td>
		   		<td>${user.nbEncheres}</td>
		   		<td>${user.nbVentes}</td>
		   		<td><a href="?detailUser=${user.utilisateur.noUtilisateur}">Détails</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>