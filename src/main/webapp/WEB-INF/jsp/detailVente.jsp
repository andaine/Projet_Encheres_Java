<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail vente</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.html"%>

	<p>

		<c:forEach var="be" items="${listeErreur}">
			<div style="color: red">${be}
				<br>
			</div>
		</c:forEach>
	</p>

	<div id="titreDetailVente">
		<h1>Détail vente</h1>
	</div>

	<section id="detailVente">

		<div id="divDetailVente">




			<div class="border" id="nomArticle">
				<h3>${infoArt.getNomArticle()}</h3>
			</div>

			<div class="border" id="labelDesciption">
				<label for="nomArticle">Description :</label>
			</div>

			<div class="border" id="description">
				<p>${infoArt.getDescription()}</p>
			</div>

			<div class="border" id="labelCategorie">
				<label for="categorie">Catégorie :</label>
			</div>

			<div class="border" id="categorieVente">
				<p>${infoArt.getCategorie().getLibelle()}</p>
			</div>

			<div class="border" id="labelMeilOffre">
				<label for="meilOffre">Meilleure offre :</label>
			</div>

			<div class="border" id="meilleureOffre">
				<p>${infoArt.getPrixVente()} pts par
					${infoArt.getUserA().getPseudo()}</p>
			</div>

			<div class="border" id="labelMiseAPrix">
				<label for="miseAPrix">Mise à prix :</label>
			</div>

			<div class="border" id="miseAPrixVente">
				<p>${infoArt.getPrixInitial()} points</p>
			</div>

			<div class="border" id="finEnchere">
				<label for="finEnchere">Fin de l'enchère :</label>
			</div>

			<div class="border" id="dateFinEnchere">
				<p>${infoArt.getDateFinEncheres()}</p>
			</div>

			<div class="border" id="labelRetrait">
				<label for="retrait">Retrait :</label>
			</div>

			<div class="border" id="adresseRetrait">
				<p>${infoArt.getRetraitVendeur().getRue()}<br />
					${infoArt.getRetraitVendeur().getCodePostal()}
					${infoArt.getRetraitVendeur().getVille()}
				</p>
				<%-- 	<c:if test= "${infoArt.getRetraitVendeur().getArticle().getNoArticle()!=null}">
				${infoArt.getRetraitVendeur().getRue()}<br />
				${infoArt.getRetraitVendeur().getCodePostal()}
				${infoArt.getRetraitVendeur().getVille()}
			</c:if>
			<c:if test= "${infoArt.getRetraitVendeur().getArticle().getNoArticle()==null}">
				${infoArt.getUserV().getRue()}<br />
				${infoArt.getUserV().getCodePostal()}
				${infoArt.getUserV().getVille()}
			</c:if> --%>
			</div>

			<div class="border" id="labelVendeur">
				<label for="vendeur">Vendeur :</label>
			</div>
			<div class="border" id="vendeurRetrait">
				<p>${infoArt.getUserV().getPseudo()}</p>
			</div>

			<form method="post"
				action="${pageContext.request.contextPath}/ServletDetailVente">
				<section class="border" id="formulaire">

					<p id="labelFormProposition">Ma proposition :</p>


					<input type="number" id="prixPropose" name="prixPropose" step="1"
						min="${infoArt.getPrixVente()}"
						max="${infoArt.getUserA().getCredit()}"
						value="${infoArt.getPrixVente()}"> <input type="submit"
						id="encherir" name="encherir" value="Enchérir" /> <input
						type="hidden" value="${infoArt.getNoArticle()}" id="noArticle"
						name="noArticle">
				</section>
			</form>

		</div>
	</section>
</body>
</html>