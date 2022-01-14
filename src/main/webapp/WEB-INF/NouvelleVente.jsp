<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nouvelle vente</title>
</head>
<body>
	<header>
		<h1 style="text-align: left;">ENI-Encheres</h1>
		<form action="NouvelleVenteServlet" method="POST" style="text-align: left;">
			<button type="submit" name="accueil" value="ENI-Encheres">ENI-Encheres</button> 
		</form>
	</header>
	<h2 style="text-align: center;">Nouvelle vente</h2> 
	<form action="NouvelleVenteServlet" method="POST" style="text-align: center;">
		Article : <input type="text" name="nomArticle" placeholder="Article"> <br>
		Description : <input type="text" name="descriptionArticle" placeholder="Description"> <br>
		Categorie : 
		<select name="categorie">
			<option value="1">Informatique</option>
			<option value="2">Ammeublement</option>
			<option value="3">Vetement</option>
			<option value="4">Sport&Loisirs</option>
		</select> <br>
		Photo de l'article : <input type="text" name="photo" placeholder="UPLOADER"><br>
		Prix initial : <input type"number" name="prixInitial"><br>
		Debut de l'enchere : <input type="date" name="dateDebutEnchere"><br>
		Fin de l'enchere : <input type="date" name="dateFinEnchere"><br>
		
		<h3>Retrait</h3>
		Rue : <input type="text" name="rue" placeholder="${model.utilisateur.rue}"> <br>
		Ville : <input type="text" name="ville" placeholder="${model.utilisateur.ville}"> <br>
		Code postal : <input type="text" name="codePostal" placeholder="${model.utilisateur.codePostal}"> <br>
		
		<button type="submit" name="enregistrer" value="Enregistrer">Enregistrer</button>
		<button type="submit" name="annuler" value="Annuler">Annuler</button>
	</form><br><br>
</body>
</html>