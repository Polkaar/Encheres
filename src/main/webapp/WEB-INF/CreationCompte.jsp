<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Creation compte</title>
</head>

<body>
	<header>
		<h1 style="text-align: left;">ENI-Encheres</h1> 
	</header>
	<h2 style="text-align: center;">Mon profil</h2> 
	<form action="CreationCompteServlet" method="POST" style="text-align: center;">
			Pseudo : <input type="text" name="pseudo" placeholder="Votre pseudo"> <br>
			Nom : <input type="text" name="nom" placeholder="Votre nom"> <br>
			Prenom : <input type="text" name="prenom" placeholder="Votre prenom"> <br>
			Email : <input type="text" name="email" placeholder="Votre mail"> <br>
			Telephone : <input type="text" name="telephone" placeholder="Votre telephone"> <br>
			Rue : <input type="text" name="rue" placeholder="Votre rue"> <br>
			Ville : <input type="text" name="ville" placeholder="Votre ville"> <br>
			Code postal :<input type="text" name="codePostal" placeholder="Votre code postal"> <br>
			Mot de passe : <input type="password" name="motDePasse" placeholder="Votre mot de passe"> <br>
			Confirmation : <input type="password" name="confirmationMotDePasse" placeholder="Confirmation de votre mot de passe"> <br>
			<h4>${model.message}</h3>
		<button type="submit" name="creer" value="Creer">Creer</button>
		<button type="submit" name="annuler" value="Annuler">Annuler</button>
	</form><br><br>
</body>
</html>