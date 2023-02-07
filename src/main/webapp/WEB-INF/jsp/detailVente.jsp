<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail vente</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.html" %>
	
	<h2>Détail vente</h2>

	<section id="sectionDetailVente">
		<div>${infoArt.getNomArticle()}</div>
		<div>
			<label for="nom" id="labelNom">Description :</label>
			${infoArt.getDescription()}
		</div>	
		<div>
			<label for="categorie" id="labelCategorie">Categorie :</label>
			${infoArt.getCategorie().getLibelle()}
		</div> 
		<div>
			<label for="meilOffre" id="labelMeilOffre">Meilleure offre :</label>
			${infoArt.getPrixVente()} pts par ${infoArt.getUserA().getPseudo()}
		</div>
		<div>
			<label for="miseAPrix" id="labelMiseAPrix">Mise à prix :</label>
			${infoArt.getPrixInitial()} points
		</div>
		<div>
			<label for="finEnchere" id="finEnchere">Fin de l'enchère :</label>
			${infoArt.getDateFinEncheres()}
		</div>
		<div>
			<label for="retrait" id="labelRetrait">Retrait :</label>
			${infoArt.getRetraitVendeur().getRue()}<br>
			${infoArt.getRetraitVendeur().getCodePostal()} ${infoArt.getRetraitVendeur().getVille()}
		</div>
		<div>
			<label for="vendeur" id="labelVendeur">Vendeur :</label>
			${infoArt.getUserV().getPseudo()}
		</div>
			
		<%-- <form method="post" action="<%=request.getContextPath()%>/ServletDetailVente">
			<section>
			<label for="input_proposition">Ma proposition :</label>
			<input type="number" id="prixPropose" name="prixPropose" step="50" min="${infoArt.getPrixInitial()}" max="${infoArt.getUserA().getCredit()}" value="${infoArt.getPrixInitial()}">
			<input type="submit" id="encherir" name="encherir" value="Enchérir"/>			
			</section>	
		</form> --%>

	</section>
</body>
</html>