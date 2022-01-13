<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
</head>
<body>

<c:if test="${!empty locale}">
	<fmt:setLocale value="${locale}"/>
</c:if>

<fmt:setBundle basename="fr.eni.encheres.message.mes_messages" var="r"/>

	<header >
		<h1>ENI-Encheres</h1>
		<form action="AccueilServlet" method="POST">
			<input type="submit" name="connexion" value="<fmt:message key="lien_connexion" bundle="${r}"/>"/>
		</form>
	</header>
	
	<h2><fmt:message key="h2_accueil" bundle="${r}"/></h2>
	
	<h3><fmt:message key="h3_accueil" bundle="${r}"/></h3>
	
	<form action="AccueilServlet" method="POST">
	
		<input type="text" name="nomArticle" value="<fmt:message key="input_article_contient" bundle="${r}"/>"/>
		
		<label><fmt:message key="label_categorie" bundle="${r}"/></label>
		<select name="categorie" id="id-categorie">
		    <option value="toutes"><fmt:message key="select_toutes" bundle="${r}"/></option>
		    <option value="informatique"><fmt:message key="select_informatique" bundle="${r}"/></option>
		    <option value="ameublement"><fmt:message key="select_ameublement" bundle="${r}"/></option>
		    <option value="vetement"><fmt:message key="select_vetement" bundle="${r}"/></option>
		    <option value="sport&loisir"><fmt:message key="select_sport&loisir" bundle="${r}"/></option>
		</select>
		
		<input type="submit" name="rechercher" value="<fmt:message key="bouton_rechercher" bundle="${r}"/>"/>
	
	</form>
	
	<c:forEach items="${articleModel.lstArticles}" var="article">
		<p>${article.nomArticle} ${article.description}</p>
		<p>Prix : ${article.prixInitial} points</p>
		<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
		<p>Vendeur : ${article.utilisateur.pseudo}</p>
		
		<c:forEach items="${article.lstEncheres}" var="enchere">
			<p>${enchere.montantEnchere} points, Faîtes par ${enchere.utilisateur.pseudo} le ${enchere.dateEnchere}</p>
		</c:forEach>
		<br>
		<br>
	</c:forEach>

</body>
</html>