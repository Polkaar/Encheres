<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modification vente</title>
</head>
<body style="text-align: center;">
	<header>
		<h1 style="text-align: left;">ENI-Encheres</h1>
		<form action="DetailVenteServlet" method="POST" style="text-align: left;">
			<button type="submit" name="accueil" value="ENI-Encheres">ENI-Encheres</button> 
		</form>
	</header>
	<h2>Modifier ma vente</h2> 
	
		Article : <input type="text" name="nomArticle" placeholder="${model.articleVendu.nomArticle}"> <br>
		Description : <input type="text" name="descriptionArticle" placeholder="${model.articleVendu.description}"> <br>
		Categorie : 
		<select name="categorie">
			<option value="1">Informatique</option>
			<option value="2">Ammeublement</option>
			<option value="3">Vetement</option>
			<option value="4">Sport&Loisirs</option>
		</select> <br>
		Photo de l'article : <input type="text" name="photo" placeholder="UPLOADER"><br>
		Prix initial : <input type"number" name="${model.articleVendu.prixInitial}"> pts<br>
		Debut de l'enchere : <input type="date" name="${model.articleVendu.dateDebutEncheres}"><br>
		Fin de l'enchere : <input type="date" name="${model.articleVendu.dateFinEncheres}"><br>
		
		<h3>Retrait</h3>
		Rue : <input type="text" name="rue" placeholder="${model.article.utilisateur.rue}"> <br>
		Ville : <input type="text" name="ville" placeholder="${model.article.utilisateur.ville}"> <br>
		Code postal : <input type="text" name="codePostal" placeholder="${model.article.utilisateur.codePostal}"> <br>
		
		<button type="submit" name="modifier" value="modifier">Modifier</button><br>
		<button type="submit" name="annuler" value="Annuler">Annuler</button><br>
		<form action="DetailVenteServlet" method="POST" style="text-align: center;">
			<button type="submit" name="supprimer" value="Supprimer ma vente">Supprimer ma vente</button> 
	</form><br>
	<h3>${model.message}</h3>
</body>
</html>