<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style><%@includefile="/WEB-INF/Connexion.css"%></style>	
<meta charset="ISO-8859-1">
<title>Connexion</title>
</head>
<body>

	<header>
		<h1><a href="AccueilServlet?accueilViaConnexion=accueil">ENI-Encheres</a></h1>
	</header>
	
	<form action="ConnexionServlet" method="POST">
	
		<label>Identifiant : </label>
		<input type="text" name="pseudo" value="${model.pseudo}"/>
		<br>
		<label>Mot de passe : </label>
		<input type="password" name="motDePasse" value="${model.motDePasse}"/>
		<br>
		
		<input type="submit" name="connexion" value="Connexion"/>
		
		<input type="checkbox" name="seSouvenirDeMoi">
  		<label> Se souvenir de moi</label>
  		
  		<input type="submit" name="motDePasseOublie" value="Mot de passe oubli?"/>
  		
  		<br>
  		<input type="submit" name="creerUnCompte" value="Cr?er un compte"/>
	
	</form>
</body>
</html>