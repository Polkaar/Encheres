<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Données d'un utilisateur</title>
</head>
<body>

	<header>
		<h1 style="text-align: left;"><a href="AccueilServlet">ENI-Encheres</a></h1>
	</header>
	
	<h2>Gestion d'un utilisateur</h2>
	
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
		</tr>
		<tr>
		    <td>${donneesModel.utilisateur.pseudo}</td>
		   	<td>${donneesModel.utilisateur.nom}</td>
		   	<td>${donneesModel.utilisateur.prenom}</td>
		   	<td>${donneesModel.utilisateur.email}</td>
		   	<td>${donneesModel.utilisateur.telephone}</td>
		   	<td>${donneesModel.utilisateur.rue}</td>
		   	<td>${donneesModel.utilisateur.codePostal}</td>
		   	<td>${donneesModel.utilisateur.ville}</td>
		   	<td>${donneesModel.utilisateur.credit}</td>
		   	<td>${donneesModel.utilisateur.administrateur}</td>
		   	<td>${donneesModel.nbEncheres}</td>
		   	<td>${donneesModel.nbVentes}</td>
		</tr>
	</table>
	
	<form action="DonneesUtilisateurServlet" method="post">
		<input type="submit" name="desactiverUser" value="Désactiver le compte"/>
		<input type="submit" name="supprimerUser" value="Supprimer le compte"/>
	</form>
	
	<form action="DonneesUtilisateurServlet" method="POST">
		<fieldset>
			<input type="checkbox" name="userEncheres" value="a" id="userEncheres" onclick="onClickUE()">ses enchères<br> 
			<input type="checkbox" name="userEncheresRemportees" value="a" id="userEncheresRemportees" onclick="onClickUER()">ses enchères remportées<br>
			<input type="checkbox" name="userVentesEnCours" value="a" id="userVentesEnCours" onclick="onClickUVEC()">ses ventes en cours<br> 
			<input type="checkbox" name="userVentesNonDebutees" value="a" id="userVentesNonDebutees" onclick="onClickUVND()">ses ventes non debutées<br> 
			<input type="checkbox" name="userVentesTerminees" value="a" id="userVentesTerminees" onclick="onClickUVT()">ses ventes terminées<br>
		</fieldset>
		<input type="submit" name="listerDonnees" value="Lister"/>
	</form>
	
	<c:forEach items="${donneesModel.lstArticles}" var="article">
		<p>${article.nomArticle} ${article.description}</p>
		<p>Prix : ${article.prixVente} points</p>
		<p>Début de l'enchère : ${article.dateDebutEncheres}</p>
		<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
		<p><a href="?detailVente=${article.noArticle}">Détail Vente</a></p>
		<br>
	</c:forEach>
	
</body>
</html>

<script type="text/javascript">
var ue = document.getElementById("userEncheres");
var uer = document.getElementById("userEncheresRemportees");
var uvec = document.getElementById("userVentesEnCours");
var uvnd = document.getElementById("userVentesNonDebutees");
var uvt = document.getElementById("userVentesTerminees");

function onClickUE(){
	uer.checked = false;
	uvec.checked = false;
	uvnd.checked = false;
	uvt.checked = false;
}
function onClickUER(){
	ue.checked = false;
	uvec.checked = false;
	uvnd.checked = false;
	uvt.checked = false;
}
function onClickUVEC(){
	ue.checked = false;
	uer.checked = false;
	uvnd.checked = false;
	uvt.checked = false;
}
function onClickUVND(){
	ue.checked = false;
	uer.checked = false;
	uvec.checked = false;
	uvt.checked = false;
}
function onClickUVT(){
	ue.checked = false;
	uer.checked = false;
	uvec.checked = false;
	uvnd.checked = false;
}

</script>
