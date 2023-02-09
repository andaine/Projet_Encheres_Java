<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.eni.ecole.enchere.bo.Enchere"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>




<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Accueil</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

	<header>
		<div><%@ include file="/WEB-INF/fragments/header.html"%></div>
		
<p>

		<c:forEach var="be" items="${listeErreur}">
			<div style="color: red">${be}
				<br>
			</div>
		</c:forEach>
	</p>

		<div>
			<c:if test="${userConnecte!=null }">
				<nav id="navConnecte">

					<a href="${pageContext.request.contextPath}/ServletNouvelleVente"
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


	<section id="filtres">



		<form method="post"
			action="${pageContext.request.contextPath}/ServletAccueil"
			name="formAfficherEncheres" id="formAfficherEncheres">

			<h3 id="titreFiltres">Filtres :</h3>

			<div class="rechCat" id="filtreArticle">
				<!--  FILTRE CHAMP DE TEXTE -->
				<input type="text" name="textFiltreArticle" id="textFiltreArticle"
					placeholder="Le nom de l'article contient">
			</div>

			<div class="rechCat" id="categories">
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

			<!--   RADIO BUTTON ACHATS/VENTES  -->

			<c:if test="${userConnecte!=null }">
				<section id="sectionAchatVente">
					<div id="divAchat">
						<input type="radio" id="achats" name="radioButton"
							value="radioAchat" checked /> <label for="labelAchats">Achats</label>
						<ul>
							<li><input type="checkbox" id="encheresOuvertes"
								name="encheresOuvertes"> <label for="encheresOuvertes">enchères
									ouvertes</label></li>
							<li><input type="checkbox" id="mesEncheres"
								name="mesEncheres"> <label for="mesEncheres">mes
									enchères</label></li>
							<li><input type="checkbox" id="encheresRemportes"
								name="encheresRemportes"> <label for="encheresRemportes">mes
									enchères remportées</label></li>
						</ul>
					</div>
					<div id="divVente">
						<input type="radio" id="ventes" name="radioButton"
							value="radioVente" /> <label for="ventes">Mes ventes</label>
						<ul>
							<li><input type="checkbox" id="ventesEnCours"
								name="ventesEnCours"> <label for="ventesEnCours">mes
									ventes en cours</label></li>
							<li><input type="checkbox" id="ventesNonDebutees"
								name="ventesNonDebutees"> <label for="ventesNonDebutees">ventes
									non débutées</label></li>
							<li><input type="checkbox" id="ventesTerminees"
								name="ventesTerminees"> <label for="ventesTerminees">ventes
									terminées</label></li>
						</ul>
					</div>
				</section>



			</c:if>

			<div class="rech" id="rechercher">
				<!--  RECHERCHER -->
				<input type="submit" id="boutonRechercher" value="Rechercher">
				<br> <br>
			</div>



		</form>
	</section>

	<section id="afficherEncheres">
		<c:forEach var="e" items="${listeEncheres}">

			<div class="divEnchere">
				<fieldset class="fsEnchere">
					<c:if test="${userConnecte!=null }">
						<a
							href="${pageContext.request.contextPath}/ServletDetailVente?id=${e.getNoArticle() }"
							id="${e.getNoArticle() }"> ${e.getNomArticle() } </a>
						<br>
					</c:if>
					<c:if test="${userConnecte==null }">
						<p>${e.getNomArticle() }</p>
						<br>
					</c:if>

					<p>Prix : ${e.getMontantEnchere()}</p>
					<br>
					<p>Fin de l'enchère : ${e.getDateEnchere()}</p>
					<br>
					<c:if test="${userConnecte!=null }">
						<a
							href="${pageContext.request.contextPath}/ServletMonProfil?id=${e.getNoUser() }"
							id="${e.getNoUser() }">Vendeur : ${e.getPseudoUser() } </a>
						<br>
					</c:if>
					<c:if test="${userConnecte==null }">
						<p>Vendeur : ${e.getPseudoUser() }</p>
						<br>
					</c:if>
				</fieldset>
			</div>
		</c:forEach>

	</section>

</body>
</html>