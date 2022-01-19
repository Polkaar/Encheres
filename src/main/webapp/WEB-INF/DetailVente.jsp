<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detail vente</title>
</head>
<body style="text-align: center;">
	<header>
		<h1 style="text-align: left;"><a href="AccueilServlet?accueilViaDetailVente=accueil">ENI-Encheres</a></h1>
		<form action="DetailVenteServlet" method="POST" style="text-align: left;">
			<button type="submit" name="accueil" value="ENI-Encheres">ENI-Encheres</button> 
		</form>
	</header>
	<h2>Detail vente</h2> 
	<h4>${model.messageVente}</h4><br>
	${model.articleVendu.nomArticle}<br>
	Description : ${model.articleVendu.description}<br>
	Categorie : ${model.articleVendu.categorie.libelle}<br>
	Meilleure offre : ${model.articleVendu.prixVente} pts par ${model.oldEnchere.utilisateur.pseudo}<br>
	Mise a prix : ${model.articleVendu.prixInitial} pts<br>
	Fin de l'enchere : ${model.articleVendu.dateFinEncheres}<br>
	Retrait : ${model.articleVendu.retrait.rue}
	${model.articleVendu.retrait.codePostal}
	${model.articleVendu.retrait.ville}<br>
	Vendeur : ${model.articleVendu.utilisateur.pseudo}<br>
	<form action="DetailVenteServlet" method="POST" style="text-align: center;">
		Ma proposition : <input type="number" name="enchere"> <br>
		<button type="submit" name="encherir" value="Encherir">Encherir</button>
	</form><br>
	<h3>${model.message}</h3>
</body>
</html>