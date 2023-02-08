<%@page import="fr.eni.ecole.enchere.exception.BusinessException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer mon compte</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

	<header>
		<%@ include file="/WEB-INF/fragments/header.html"%>
	</header>

	<p style="color: red">
		<c:forEach var="be" items="${listeErreur}">
		${be}<br>
		</c:forEach>
	</p>

	<div class="menu">
		<div></div>
		<div class="pageTitle">
			<h1>Création du profil</h1>
		</div>
		<div></div>
	</div>

	<div class="contentModifier">
		<div id="colonneMilieu">

			<form method="post"
				action="${pageContext.request.contextPath}/ServletCreateAccount">
				<div id="separateurMilieu">
					<div>

						<div class="input">
							<label for="input_pseudo">Pseudo : </label> <input id="pseudo"
								name="pseudo" type="text" value="${pseudo}">
						</div>
						<div class="input">
							<label for="input_prenom">Prénom : </label> <input id="prenom"
								name="prenom" type="text" value="${prenom}">
						</div>
						<div class="input">
							<label for="input_telephone">Téléphone : </label> <input
								id="telephone" name="telephone" type="text" value="${telephone}">
						</div>
						<div class="input">
							<label for="input_cp">Code postal : </label> <input id="cp"
								name="cp" type="text" value="${cp}">
						</div>
						<div class="input">
							<label for="input_motDePasse">Mot de passe : </label> <input
								id="motDePasse" name="motDePasse" type="password">
						</div>


					</div>

					<div>


						<div class="input">
							<label for="input_nom">Nom </label> <input id="nom" name="nom"
								type="text" value="${nom}">
						</div>

						<div class="input">
							<label for="input_email">Email : </label> <input id="email"
								name="email" type="text" value="${email}">
						</div>
						<div class="input">
							<label for="input_rue">Rue : </label> <input id="rue" name="rue"
								type="text" value="${rue}">
						</div>
						<div class="input">
							<label for="input_ville">Ville : </label> <input id="ville"
								name="ville" type="text" value="${ville}">
						</div>
						<div class="input">
							<label for="input_mdpConfirmation">Confirmation : </label> <input
								id="confirmation" name="confirmation" type="password">
						</div>

					</div>
					<!-- TODO servlet modifier profil -->
				</div>
				<div id="lotBtnModifier">
					<div class="boutonsCreateAccount">
						<input type="submit" id="creer" name="creer" value="Créer">
					</div>
					<div class="boutonsCreateAccount">
						<a href="${pageContext.request.contextPath}/ServletAccueil"
							class="button"> <input type="button" value="Annuler" />
						</a>
					</div>

				</div>
			</form>
		</div>
	</div>

	<div></div>

</body>
</html>