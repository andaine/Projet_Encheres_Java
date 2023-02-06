<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.eni.ecole.enchere.bo.Enchere"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Accueil</title>
</head>
<body>

	<header>
		<div><%@ include file="/WEB-INF/fragments/header.html"%></div>

		<div>
			<c:if test="${userConnecte!=null }">
				<nav id="navConnecte">

					<a href="${pageContext.request.contextPath}/ServletConnecteAchats"
						name="lienEncheres" id="lienEncheres">Enchères</a> <a
						href="${pageContext.request.contextPath}/ServletNouvelleVente"
						name="lienNouvelleVente" id="lienNouvelleVente">Vendre un
						article</a>
					<!--  TODO ServletNouvelleVente -->
					<a
						href="${pageContext.request.contextPath}/ServletMonProfil?id=${userConnecte.getNoUtilisateur()}"
						name="lienMonProfil" id="lienMonProfil">Mon profil</a> <a
						href="${pageContext.request.contextPath}/ServletDeconnexion"
						name="lienDeconnexion" id="lienDeconnexion">Déconnexion</a>

				</nav>
			</c:if>
			<c:if test="${userConnecte==null}">
				<nav id="navDeconnecte">

					<a href="${pageContext.request.contextPath}/ServletCreateAccount"
						name="lienInscrire" id="lienLogin">S'inscrire</a> - <a
						href="${pageContext.request.contextPath}/ServletLogin"
						name="lienLogin" id="lienLogin">Se connecter</a>

				</nav>
			</c:if>
		</div>

	</header>

	<h2 id="titreListe">Liste des enchères</h2>

	<h3 id="titreFiltres">Filtres :</h3>
	<section id="filtres">
		<form method="post"
			action="${pageContext.request.contextPath}/ServletAccueil"
			name="formAfficherEncheres" id="formAfficherEncheres">

			<div class="rechCat">
				<!--  FILTRE CHAMP DE TEXTE -->
				<input type="text" name="textFiltreArticle" id="textFiltreArticle"
					placeholder="Le nom de l'article contient">
			</div>

			<div class="rechCat">
				<!-- SELECTEUR DE CATEGORIES  -->
				<label for="selectCategorie" id="labelCategorie">Catégories
					:</label> <select name="selectCategorie" id="selectCategorie">
					<option selected="selected">Toutes</option>
					<c:forEach var="cat" items="${categorie}">
						<option value="${cat.libelle}"
							${cat.libelle.equals(categorieChoisie) ? 'selected':''}>
							${cat.libelle}</option>
					</c:forEach>
				</select>
			</div>


			<div class="rech">
				<!--  RECHERCHER -->
				<input type="submit" id="boutonRechercher" value="Rechercher">
				<br> <br>
			</div>



		</form>
	</section>

	<section id="afficherEncheres">
		<c:forEach var="e" items="${listeEncheres}">
			<div id="divEnchere">
				<p>${e.getNomArticle() }</p>
				<p>Prix : ${e.getMontantEnchere()}</p>
				<p>Fin de l'enchère : ${e.getDateEnchere()}</p>
				<br>
				<p>Vendeur : ${e.getPseudoUser() }</p>
			</div>
		</c:forEach>

	</section>

</body>
</html>