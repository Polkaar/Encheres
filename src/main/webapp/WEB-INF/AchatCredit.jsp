<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Achat de crédit</title>
</head>
<body>

	<header>
		<h1 style="text-align: left;"><a href="AccueilServlet?accueilViaAchatCredit=accueil">ENI-Encheres</a></h1>
	</header>
	
	Pseudo : ${model.utilisateur.pseudo}<br>
	Crédits actuels : ${model.utilisateur.credit}<br>
	
	<h2>Acheter des crédits</h2>
	<form action="AchatCreditServlet" method="POST">
		
		<fieldset>
			<input type="radio" name="achat10credits" id="achat10credits" onclick="onClickAchatDix()"> 10 credits pour 20 euros<br>
			<input type="radio" name="achat50credits" id="achat50credits" onclick="onClickAchatCinquante()"> 50 credits pour 95 euros<br>
			<input type="radio" name="achat200credits" id="achat200credits" onclick="onClickAchatDeuxCents()" checked="checked"> 200 crédits pour 360 euros (conseillé) (mdr)<br>
		</fieldset>
		
		<input type="submit" name="acheterCredit" value="Valider l'achat"/>
	
	</form>
	
</body>
</html>


<script type="text/javascript">

function onClickAchatDix(){
	var cinquante = document.getElementById("achat50credits");
	cinquante.checked = false;
	
    var deuxCents = document.getElementById("achat200credits");
    deuxCents.checked = false;
}

function onClickAchatCinquante(){
	var dix = document.getElementById("achat10credits");
	dix.checked = false;
	
    var deuxCents = document.getElementById("achat200credits");
    deuxCents.checked = false;
}

function onClickAchatDeuxCents(){
	var dix = document.getElementById("achat10credits");
	dix.checked = false;
	
	var cinquante = document.getElementById("achat50credits");
	cinquante.checked = false;
}

</script>