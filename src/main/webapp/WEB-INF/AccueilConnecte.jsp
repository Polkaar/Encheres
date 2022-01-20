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
	<div>
		<a href="AccueilConnecteServlet">Enchères</a>
		<a href="NouvelleVenteServlet">Vendre un article</a>
		<a href="${lienProfil}?monProfilViaAccueilConnecte=monProfil">Mon profil</a>
		<a href="AccueilServlet?deconnexion=deconnecte">Déconnexion</a>
	</div>
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
				<input type="radio" name="achats" id="achats" onclick="onClickAchats()"> Achats<br>
		  			<input type="checkbox" name="encheresOuvertes" value="encheresOuvertes" id="encheresOuvertes" onclick="onClickMesAchats()">enchères ouvertes<br> 
		 			<input type="checkbox" name="mesEncheres" value="mesEncheres" id="mesEncheres" onclick="onClickMesAchats()">mes enchères<br> 
		   			<input type="checkbox" name="mesEncheresRemportees" value="mesEncheresRemportees" id="mesEncheresRemportees" onclick="onClickMesAchats()">mes enchères remportées<br>
		  	</fieldset>
			<fieldset>
				<input type="radio" name="mesVentes" id="mesVentes" onclick="onClickVentes()"> Mes ventes<br>
		  			<input type="checkbox" name="mesVentesEnCours" value="mesVentesEnCours" id="mesVentesEnCours" onclick="onClickMesVentes()">mes ventes en cours<br> 
		 			<input type="checkbox" name="ventesNonDebutees" value="ventesNonDebutees" id="ventesNonDebutees" onclick="onClickMesVentes()">ventes Non Debutées<br> 
		   			<input type="checkbox" name="ventesTerminees" value="ventesTerminees" id="ventesTerminees" onclick="onClickMesVentes()">ventes terminées<br>
		  	</fieldset>
		</fieldset>
		
		<input type="submit" name="rechercherConnecte" value="Rechercher"/>
	
	</form>
	
	
	<c:forEach items="${accueilConnecteModel.lstListesArticles}" var="lstArticles">
	<!-- Rajouter des labelles aux listes -->
		<c:forEach items="${lstArticles}" var="article">
			<p>${article.nomArticle} ${article.description}</p>
			<p>Prix : ${article.prixVente} points</p>
			<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
			<p><a href="?profilVendeur=${article.utilisateur.noUtilisateur}">Vendeur : ${article.utilisateur.pseudo}</a></p>
			<p><a href="?detailVente=${article.noArticle}">Détail Vente</a></p>
			<br>
		</c:forEach>
		<br>
		<br>
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

function onClickMesAchats(){
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

function onClickMesVentes(){
	var encheresOuvertes = document.getElementById("encheresOuvertes");
    encheresOuvertes.checked = false;
    encheresOuvertes.disabled = true;
	var mesEncheres = document.getElementById("mesEncheres");
    mesEncheres.checked = false;
    mesEncheres.disabled = true;
	var mesEncheresRemportees = document.getElementById("mesEncheresRemportees");
    mesEncheresRemportees.checked = false;
    mesEncheresRemportees.disabled = true;
}

</script>
