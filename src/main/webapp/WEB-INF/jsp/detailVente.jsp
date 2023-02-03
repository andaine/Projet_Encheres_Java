<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail vente</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.html" %>
	
	<h1>Détail vente</h1>

	<section id="sectionDetailVente">
		<ul>
			<li>${nomDuProduit}</li> 
			<li><label for="nom" id="labelNom">Description :</label>
				${descriptionDuProduit}</li>
			<li><label for="categorie" id="labelCategorie">Categorie :</label> 
				${categorieDuProduit}</li>
			<li><label for="meilOffre" id="labelMeilOffre">Meilleure offre :</label>
				${nb de points et enchereur}</li>
			<li><label for="miseAPrix" id="labelMiseAPrix">Mise à prix</label>
				${miseAPrix}</li>
			<li><label for="finEnchere" id="finEnchere">Fin de l'enchère :</label>
				${finEnchere(date)}</li>
			<li><label for="retrait" id="labelRetrait">Retrait :</label>
				${adresseREtrait}</li>
			<li><label for="vendeur" id="labelVendeur">Vendeur :</label>
				${vendeur}</li>
		</ul>


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