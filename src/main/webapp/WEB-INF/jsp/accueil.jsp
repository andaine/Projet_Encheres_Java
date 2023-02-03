<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.eni.ecole.enchere.bo.Enchere"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Accueil</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.html" %>

	<header>
		<h1 id="ENI">ENI-Encheres</h1>
		<a href="<%=request.getContextPath()%>/ServletCreateAccount" name="lienInscrire"
			id="lienLogin">S'inscrire</a>
			-
		<a href="<%=request.getContextPath()%>/ServletLogin" name="lienLogin"
			id="lienLogin">Se connecter</a>
		<h2 id="titreListe">Liste des enchères</h2>
	</header>

	<section id="filtres">
		<h2 id="titreFiltres">Filtres :</h2>
		<form method="post"
			action="<%=request.getContextPath()%>/ServletAccueil"
			name="formAfficherEncheres" id="formAfficherEncheres">
			<input type="text" name="textFiltreArticle" id="textFiltreArticle">
			<input type="submit" id="boutonRechercher" value="Rechercher">
			<br>
			<br>
			<label for="selectCategorie" id="labelCategorie">Catégories :</label>
			
			 <select name="selectCategorie" id="selectCategorie">
				<c:forEach var="cat" items="${categorie}">
					<option >  ${cat.libelle}</option>
				</c:forEach>
			</select>
		</form>
	</section>

	<section id="afficherEncheres">
		<c:forEach var="e" items="${listeEncheres}">
			<div id="divEnchere">			
				<p>${e.getNomArticle() }</p>
				<p>Prix : ${e.getMontantEnchere()}</p>
				<p>Fin de l'enchère : ${e.getDateEnchere()} </p>
				<br>
				<p>Vendeur : ${e.getPseudoUser() }</p>
			</div>
		</c:forEach>

	</section>

</body>
</html>