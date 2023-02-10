<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="fr.eni.ecole.enchere.bo.Utilisateur"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
<meta charset="UTF-8">
<title>JSP mon profil</title>
</head>
<body id="bodyProfil">
	<%@ include file="/WEB-INF/fragments/header.html"%>

	<p>

		<c:forEach var="be" items="${listeErreur}">
			<div style="color: red">${be}
				<br>
			</div>
		</c:forEach>
	</p>

	<div id="titre">
		<h1 class="pageTitle">Profil</h1>
	</div>


	
		<div class="infoUser">

			<form id="formInfos"
				action="${pageContext.request.contextPath}/ServletUpdateUtilisateurs">


				<label for="pseudo" id="labelPseudo">Pseudo : </label>
				<div class="boxInfo" id="pseudo">${infoVendeur.getPseudo()}</div>


				<label for="nom" id="labelNom">Nom :</label>
				<div class="boxInfo" id="nom">${infoVendeur.getNom()}</div>



				<label for="prenom" id="labelPrenom">Prenom : </label>
				<div class="boxInfo" id="prenom">${infoVendeur.getPrenom()}</div>



				<label for="email" id="labelEmail">Email :</label>
				<div class="boxInfo" id="email">${infoVendeur.getEmail()}</div>



				<label for="telephone" id="labelTelephone">Téléphone :</label>
				<div class="boxInfo" id="telephone">${infoVendeur.getTelephone()}</div>



				<label for="rue" id="labelRue">Rue :</label>
				<div class="boxInfo" id="rue">${infoVendeur.getRue()}</div>



				<label for="codePostal" id="labelCodePostal">Code postal :</label>
				<div class="boxInfo" id="codePostal">${infoVendeur.getCodePostal()}</div>



				<label for="ville" id="labelVille">Ville :</label>
				<div class="boxInfo" id="ville">${infoVendeur.getVille()}</div>


				<c:if test="${userConnecte != null }">
					<label for="credit" id="labelCredit">Crédit :</label>
					<div class="boxInfo" id="credit">${userConnecte.getCredit()}</div>
				</c:if>

				<div class="actions">
					<c:if test="${idVendeur == userConnecte.getNoUtilisateur()}">
						<input type="submit" name="Modifier" value="Modifier"
							id="boutonModif" />
					</c:if>
				</div>

			</form>


		</div>


</body>
</html>