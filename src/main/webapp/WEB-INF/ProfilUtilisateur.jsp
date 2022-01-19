<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profil</title>
</head>
<body>
	<header>
		<h1 style="text-align: left;"><a href="AccueilServlet?accueilViaProfilUtilisateur=accueil">ENI-Encheres</a></h1>
	</header>
	<div style="text-align: center;">
	<h2>Profil de ${model.utilisateur.pseudo}</h2><br><br>
	Pseudo : ${model.utilisateur.pseudo}<br>
	Nom : ${model.utilisateur.nom}<br>
	Prenom : ${model.utilisateur.prenom}<br>
	Email : ${model.utilisateur.email}<br>
	Telephone : ${model.utilisateur.telephone}<br>
	Rue : ${model.utilisateur.rue}<br>
	Code postal : ${model.utilisateur.codePostal}<br>
	Ville : ${model.utilisateur.ville}<br><br>
	</div>
</body>
</html>