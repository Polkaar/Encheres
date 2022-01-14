<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil Connect�</title>
</head>
<body>

<header >
		<h1>ENI-Encheres</h1>
		<form action="AccueilConnecteServlet" method="POST">
			<input type="submit" name="encheres" value="Ench�res"/>
			<input type="submit" name="vente" value="Vendre un article"/>
			<input type="submit" name="monProfil" value="Mon Profil"/>
			<input type="submit" name="deconnexion" value="D�connexion"/>
		</form>
	</header>
	
	<h2>Liste des ench�res</h2>
	
	<h3>Filtres : </h3>
	
	<form action="AccueilConnecteServlet" method="POST">
	
		<input type="text" name="nomArticleConnecte" placeholder="Le nom de l'article contient"/>
		
		<label>Categorie : </label>
		<select name="categorieConnecte" id="id-categorie">
		    <option value="toutes">Toutes</option>
		    <option value="1">Informatique</option>
		    <option value="2">Ameublement</option>
		    <option value="3">V�tements</option>
		    <option value="4">Sport&Loisirs</option>
		</select>
		
		<fieldset>
		  	<fieldset>
				<input type="radio" name="achats"> Achats<br>
		  			<input type="checkbox" name="encheresOuvertes" value="encheresOuvertes">ench�res ouvertes<br> 
		 			<input type="checkbox" name="mesEncheres" value="mesEncheres">mes ench�res<br> 
		   			<input type="checkbox" name="mesEncheresRemportees" value="mesEncheresRemportees">mes ench�res remport�es<br>
		  	</fieldset>
			<fieldset>
				<input type="radio" name="mesVentes"> Mes ventes<br>
		  			<input type="checkbox" name="mesVentesEnCours" value="mesVentesEnCours">mes ventes en cours<br> 
		 			<input type="checkbox" name="ventesNonDebutees" value="ventesNonDebutees">ventes Non Debut�es<br> 
		   			<input type="checkbox" name="ventesTerminees" value="ventesTerminees">ventes termin�es<br>
		  	</fieldset>
		</fieldset>
		
		<input type="submit" name="rechercherConnecte" value="Rechercher"/>
	
	</form>
	
	<c:forEach items="${accueilConnecteModel.lstArticles}" var="article">
		<p>${article.nomArticle} ${article.description}</p>
		<p>Prix : ${article.prixInitial} points</p>
		<p>Fin de l'ench�re : ${article.dateFinEncheres}</p>
		<p>Vendeur : ${article.utilisateur.pseudo}</p>
		<br>
	</c:forEach>
	
</body>
</html>