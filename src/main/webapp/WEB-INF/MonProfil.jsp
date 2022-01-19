<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mon profil</title>
</head>
<body>
	<header>
		<h1 style="text-align: left;"><a href="AccueilServlet?accueilViaMonProfil=accueil">ENI-Encheres</a></h1>
	</header>
	<div style="text-align: center;">
	<h2>Mon profil</h2><br><br>
	Pseudo : ${model.utilisateur.pseudo}<br>
	Nom : ${model.utilisateur.nom}<br>
	Prenom : ${model.utilisateur.prenom}<br>
	Email : ${model.utilisateur.email}<br>
	Telephone : ${model.utilisateur.telephone}<br>
	Rue : ${model.utilisateur.rue}<br>
	Code postal : ${model.utilisateur.codePostal}<br>
	Ville : ${model.utilisateur.ville}<br><br>
	<form action="MonProfilServlet" method="POST" style="text-align: center;">
			<button type="submit" name="modifier" value="Modifier">Modifier</button> 
	</form>
	</div>
</body>
</html>