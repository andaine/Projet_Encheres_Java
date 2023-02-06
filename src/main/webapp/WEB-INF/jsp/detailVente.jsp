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
		<div>${nomDuProduit}
			<label for="nom" id="labelNom">Description :</label></div>
		<div>${descriptionDuProduit}
			<label for="categorie" id="labelCategorie">Categorie :</label></div> 
		<div>${categorieDuProduit}
			<label for="meilOffre" id="labelMeilOffre">Meilleure offre :</label></div>
		<div>${nb de points et enchereur}
			<label for="miseAPrix" id="labelMiseAPrix">Mise à prix</label></div>
		<div>${miseAPrix}
			<label for="finEnchere" id="finEnchere">Fin de l'enchère :</label></div>
		<div>${finEnchere(date)}
			<label for="retrait" id="labelRetrait">Retrait :</label></div>
		<div>${adresseREtrait}
			<label for="vendeur" id="labelVendeur">Vendeur :</label></div>
				${vendeur}


		<form method="post" action="<%=request.getContextPath()%>/ServletDetailVente">>
			<section>
			<label for="input_proposition">Ma proposition :</label>
			<input type="number" id="proposition" name="proposition" step="50" min="${meilleureEnchère+1}" max="${creditsRestants}">
			<input type="text" id="encherir" name="encherir" value="Enchérir"/>			
			</section>	
		</form>

	</section>
</body>
</html>