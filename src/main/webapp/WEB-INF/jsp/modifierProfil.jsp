<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.ecole.enchere.bo.Utilisateur"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>JSP modifier profil</title>
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



	<div class="menu">
		<div></div>
		<div class="pageTitle">
			<h1>Mon profil</h1>
		</div>
		<div></div>
	</div>


	<div class="contentModifier">
		<div id="colonneMilieu">

			<form method="post"
				action="${pageContext.request.contextPath}/ServletUpdateUtilisateurs?id=${userConnecte.getNoUtilisateur()}">
				<div id="separateurMilieu">
					<div>

						<div class="input">
							<label for="pseudoModifier" id="labelPseudoModifier">Pseudo
								: </label> <input id="pseudoModifier" name="pseudoModifier" type="text"
								value="${userConnecte.getPseudo()}">
						</div>
						<div class="input">
							<label for="prenomModifier" id="labelPrenomModifier">Prénom
								: </label> <input id="prenomModifier" name="prenomModifier" type="text"
								value="${userConnecte.getPrenom()}">
						</div>
						<div class="input">
							<label for="telModifier" id="labelTelModifier">Téléphone
								: </label> <input id="telModifier" name="telModifier" type="text"
								value="${userConnecte.getTelephone()}">
						</div>
						<div class="input">
							<label for="codePModifier" id="labelCodePModifier">Code
								postal : </label> <input id="codePModifier" name="codePModifier"
								type="text" value="${userConnecte.getCodePostal()}">
						</div>
						<div class="input">
							<label for="mdpActuel" id="labelMdpActuel">Mot de passe
								actuel : </label> <input id="mdpActuel" name="mdpActuel" type="password">
						</div>
						<div class="input">
							<label for="mdpNouveau" id="labelMdpNouveau">Nouveau mot
								de passe : </label> <input id="mdpNouveau" name="mdpNouveau" type="password">
						</div>

					</div>

					<div>


						<div class="input">
							<label for="nomModifier" id="labelNomModifier">Nom </label> <input
								id="nomModifier" name="nomModifier" type="text"
								value="${userConnecte.getNom()}">
						</div>

						<div class="input">
							<label for="emailModifier" id="labelEmailModifier">Email
								: </label> <input id="emailModifier" name="emailModifier" type="text"
								value="${userConnecte.getEmail()}">
						</div>
						<div class="input">
							<label for="rueModifier" id="labelRueModifier">Rue : </label> <input
								id="rueModifier" name="rueModifier" type="text"
								value="${userConnecte.getRue()}">
						</div>
						<div class="input">
							<label for="villeModifier" id="labelVilleModifier">Ville
								: </label> <input id="villeModifier" name="villeModifier" type="text"
								value="${userConnecte.getVille()}">
						</div>
						<div class="input">
							<label for="mdpConfirmer" id="labelMdpConfirmer">Confirmation
								: </label> <input id="mdpConfirmer" name="mdpConfirmer" type="password">
						</div>

						<div id="divCredit">
							<label for="credit" id="labelCredit ">Crédit :</label>
							<p id="credit">${userConnecte.getCredit()}</p>
						</div>
					</div>
					<!-- TODO servlet modifier profil -->
				</div>
				<div id="lotBtnModifier">
					<input type="submit" id="enregistrerProfil"
						name="enregistrerProfil" value="Enregistrer"> 
						<a href="${pageContext.request.contextPath}/ServletDelete?id=${userConnecte.getNoUtilisateur()}"
						class="button"><input type="button" value="Supprimer mon compte" /></a>
				</div>
			</form>
		</div>
	</div>

	<div></div>


</body>
</html>