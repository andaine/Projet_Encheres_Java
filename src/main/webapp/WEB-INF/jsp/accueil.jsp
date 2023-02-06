<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.eni.ecole.enchere.bo.Enchere"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">


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
<<<<<<< HEAD
=======
		<c:if test="${userConnecte!=null }">
			<nav id="navConnecte">
			<ul>
				<li><a href="${pageContext.request.contextPath}/ServletConnecteAchats"
					name="lienEncheres" id="lienEncheres">Enchères</a></li>
				<li><a href="${pageContext.request.contextPath}/ServletNouvelleVente"
					name="lienNouvelleVente" id="lienNouvelleVente">Vendre un
						article</a> <!--  TODO ServletNouvelleVente --></li>
				<li><a href="${pageContext.request.contextPath}/ServletMonProfil?id=${userConnecte.getNoUtilisateur()}"
					name="lienMonProfil" id="lienMonProfil">Mon profil</a></li>
				<li><a href="${pageContext.request.contextPath}/ServletDeconnexion"
					name="lienDeconnexion" id="lienDeconnexion">Déconnexion</a></li>
			</ul>
		</nav>
		</c:if>
		<c:if test="${userConnecte==null}">
			<nav id="navDeconnecte"	>
		
		<a href="${pageContext.request.contextPath}/ServletCreateAccount" name="lienInscrire"
			id="lienLogin">S'inscrire</a>
			-
		<a href="${pageContext.request.contextPath}/ServletLogin" name="lienLogin"
			id="lienLogin">Se connecter</a>
		<h2 id="titreListe">Liste des enchères</h2>
			</nav>
		</c:if>
		
	</header>

	<section id="filtres">
		<h2 id="titreFiltres">Filtres :</h2>
		<form method="post" action="${pageContext.request.contextPath}/ServletAccueil" name="formAfficherEncheres" id="formAfficherEncheres">
		
		
			<!--  FILTRE CHAMP DE TEXTE -->
			<input type="text" name="textFiltreArticle" id="textFiltreArticle" placeholder="Le nom de l'article contient">
			
			
			
			
			
			<!--  RECHERCHER -->
			<input type="submit" id="boutonRechercher" value="Rechercher">
			<br>
			<br>
			
			
			
			<!-- SELECTEUR DE CATEGORIES  -->	
			<label for="selectCategorie" id="labelCategorie">Catégories :</label>			
			 <select name="selectCategorie" id="selectCategorie">
			 	<option selected="selected">Toutes</option>
				<c:forEach var="cat" items="${categorie}">
					<option value="${cat.libelle}" ${cat.libelle.equals(categorieChoisie) ? 'selected':''}>  ${cat.libelle} </option>
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