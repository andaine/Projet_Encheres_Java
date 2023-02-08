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

	<div id="titreDetailVente">
		<h1>Détail vente</h1>
	</div>

	<section id="detailVente">

		<div id="divDetailVente">

			<p id="nomArticle">${infoArt.getNomArticle()}</p>
			<p id="truc" >fzefiazefbezf</p>


			<label for="nomArticle" id="labelDesciption">Description :</label>
			<p id="description">${infoArt.getDescription()}</p>



			<label for="categorie" id="labelCategorie">Categorie :</label>
			<p id="categorieVente">${infoArt.getCategorie().getLibelle()}</p>



			<label for="meilOffre" id="labelMeilOffre">Meilleure offre :</label>
			<p id="meilleureOffre">${infoArt.getPrixVente()}pts par
				${infoArt.getUserA().getPseudo()}</p>



			<label for="miseAPrix" id="labelMiseAPrix">Mise à prix :</label>
			<p id="miseAPrixVente">${infoArt.getPrixInitial()}points</p>


			<label for="finEnchere" id="finEnchere">Fin de l'enchère :</label>
			<p id="dateFinEnchere">${infoArt.getDateFinEncheres()}</p>



			<label for="retrait" id="labelRetrait">Retrait :</label>
			<p id="adresseRetrait">${infoArt.getRetraitVendeur().getRue()}<br />
				${infoArt.getRetraitVendeur().getCodePostal()}
				${infoArt.getRetraitVendeur().getVille()}
			</p>


			<label for="vendeur" id="labelVendeur">Vendeur :</label>
			<p id="vendeurRetrait">${infoArt.getUserV().getPseudo()}</p>


			<form method="post"
				action="${pageContext.request.contextPath}/ServletDetailVente">
				<section>
					<label for="input_proposition" id="labelFormProposition">Ma proposition :</label> <input
						type="number" id="prixPropose" name="prixPropose" step="1"
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