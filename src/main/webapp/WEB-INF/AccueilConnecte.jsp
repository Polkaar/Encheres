<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil Connecté</title>
</head>
<body>

<header >
		<h1>ENI-Encheres</h1>
		<form action="AccueilConnecteServlet" method="POST">
			<input type="submit" name="encheres" value="Enchères"/>
			<input type="submit" name="vente" value="Vendre un article"/>
			<input type="submit" name="monProfil" value="Mon Profil"/>
			<input type="submit" name="deconnexion" value="Déconnexion"/>
		</form>
	</header>
	
	<h2>Liste des enchères</h2>
	
	<h3>Filtres : </h3>
	
	<form action="AccueilConnecteServlet" method="POST">
	
		<input type="text" name="nomArticleConnecte" placeholder="Le nom de l'article contient"/>
		
		<label>Categorie : </label>
		<select name="categorieConnecte" id="id-categorie">
		    <option value="0">Toutes</option>
		    <c:forEach items="${accueilConnecteModel.lstCategories}" var="categorie">
				<option value="${categorie.noCategorie}">${categorie.libelle}</option>
			</c:forEach>
		</select>
		
		<fieldset>
		  	<fieldset>
				<input type="radio" name="achats" id="achats" onclick="onClickAchats()" > Achats<br>
		  			<input type="checkbox" name="encheresOuvertes" value="encheresOuvertes" id="encheresOuvertes">enchères ouvertes<br> 
		 			<input type="checkbox" name="mesEncheres" value="mesEncheres" id="mesEncheres">mes enchères<br> 
		   			<input type="checkbox" name="mesEncheresRemportees" value="mesEncheresRemportees" id="mesEncheresRemportees">mes enchères remportées<br>
		  	</fieldset>
			<fieldset>
				<input type="radio" name="mesVentes" id="mesVentes" onclick="onClickVentes()"> Mes ventes<br>
		  			<input type="checkbox" name="mesVentesEnCours" value="mesVentesEnCours" id="mesVentesEnCours">mes ventes en cours<br> 
		 			<input type="checkbox" name="ventesNonDebutees" value="ventesNonDebutees" id="ventesNonDebutees">ventes Non Debutées<br> 
		   			<input type="checkbox" name="ventesTerminees" value="ventesTerminees" id="ventesTerminees">ventes terminées<br>
		  	</fieldset>
		</fieldset>
		
		<input type="submit" name="rechercherConnecte" value="Rechercher"/>
	
	</form>
	
	<c:forEach items="${accueilConnecteModel.lstArticles}" var="article">
		<p>${article.nomArticle} ${article.description}</p>
		<p>Prix : ${article.prixInitial} points</p>
		<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
		<p>Vendeur : ${article.utilisateur.pseudo}</p>
		<br>
	</c:forEach>
	
</body>
</html>

<script type="text/javascript">
function onClickAchats(){
	var mesVentes = document.getElementById("mesVentes");
	mesVentes.checked = false;
	
    var encheresOuvertes = document.getElementById("encheresOuvertes");
    encheresOuvertes.checked = true;
    encheresOuvertes.disabled = false;
	var mesEncheres = document.getElementById("mesEncheres");
    mesEncheres.checked = false;
    mesEncheres.disabled = false;
	var mesEncheresRemportees = document.getElementById("mesEncheresRemportees");
    mesEncheresRemportees.checked = false;
    mesEncheresRemportees.disabled = false;
    
    var mesVentesEnCours = document.getElementById("mesVentesEnCours");
    mesVentesEnCours.checked = false;
    mesVentesEnCours.disabled = true;
	var ventesNonDebutees = document.getElementById("ventesNonDebutees");
    ventesNonDebutees.checked = false;
    ventesNonDebutees.disabled = true;
	var ventesTerminees = document.getElementById("ventesTerminees");
    ventesTerminees.checked = false;
    ventesTerminees.disabled = true;
}

function onClickVentes(){
	var achats = document.getElementById("achats");
	achats.checked = false;
	
	var encheresOuvertes = document.getElementById("encheresOuvertes");
    encheresOuvertes.checked = false;
    encheresOuvertes.disabled = true;
	var mesEncheres = document.getElementById("mesEncheres");
    mesEncheres.checked = false;
    mesEncheres.disabled = true;
	var mesEncheresRemportees = document.getElementById("mesEncheresRemportees");
    mesEncheresRemportees.checked = false;
    mesEncheresRemportees.disabled = true;
    
    var mesVentesEnCours = document.getElementById("mesVentesEnCours");
    mesVentesEnCours.checked = false;
    mesVentesEnCours.disabled = false;
	var ventesNonDebutees = document.getElementById("ventesNonDebutees");
    ventesNonDebutees.checked = true;
    ventesNonDebutees.disabled = false;
	var ventesTerminees = document.getElementById("ventesTerminees");
    ventesTerminees.checked = false;
    ventesTerminees.disabled = false;
}
</script>
