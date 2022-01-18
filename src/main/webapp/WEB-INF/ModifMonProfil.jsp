<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier mon profil</title>
</head>
<body>
	<header>
		<h1 style="text-align: left;">ENI-Encheres</h1>
		<form action="ModifMonProfilServlet" method="POST" style="text-align: left;">
			<button type="submit" name="accueil" value="ENI-Encheres">ENI-Encheres</button> 
		</form>
	</header>
	<div style="text-align: center;">
	<h2>Modification de mon profil</h1><br><br>
	<form action="ModifMonProfilServlet" method="POST">
		Pseudo : <input type="text" name="pseudo" value="${model.utilisateur.pseudo}"> <br>
		Nom : <input type="text" name="nom" value="${model.utilisateur.nom}"> <br>
		Prenom : <input type="text" name="prenom" value="${model.utilisateur.prenom}"> <br>
		Email : <input type="text" name="email" value="${model.utilisateur.email}"> <br>
		Telephone : <input type="text" name="telephone" value="${model.utilisateur.telephone}"> <br>
		Rue : <input type="text" name="rue" value="${model.utilisateur.rue}"> <br>
		Ville : <input type="text" name="ville" value="${model.utilisateur.ville}"> <br>
		Code postal :<input type="text" name="codePostal" value="${model.utilisateur.codePostal}"> <br>
		Mot de passe actuel : <input type="password" name="motDePasse" value=""> <br>
		Nouveau mot de passe : <input type="password" name="nouveauMotDePasse" value=""> <br>
		confirmation : <input type="password" name="confirmationMotDePasse" value=""> <br>
		Credit : ${model.utilisateur.credit} <br><br>
		<h4>${model.message}</h3>
		<button type="submit" name="enregistrer" value="Enregistrer">Enregistrer</button>
	</form>
	<form action="ModifMonProfilServlet" method="POST" style="text-align: center;">
			<button type="submit" name="supprimer" value="Supprimer mon compte">Supprimer mon compte</button> 
	</form>
	</div>
</body>
</html>