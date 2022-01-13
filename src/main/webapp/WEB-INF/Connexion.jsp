<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
</head>
<body>

	<header>
		<h1>ENI-Encheres</h1>
	</header>
	
	<form action="ConnexionServlet" method="POST">
	
		<label>Identifiant : </label>
		<input type="text" name="pseudo" value="${model.pseudo}"/>
		<br>
		<label>Mot de passe : </label>
		<input type="text" name="motDePasse" value="${model.motDePasse}"/>
		<br>
		
		<input type="submit" name="connexion" value="Connexion"/>
		
		<input type="checkbox" name="seSouvenirDeMoi">
  		<label> Se souvenir de moi</label>
  		
  		<input type="submit" name="motDePasseOublie" value="Mot de passe oublié"/>
  		
  		<br>
  		<input type="submit" name="creerUnCompte" value="Créer un compte"/>
	
	</form>
</body>
</html>