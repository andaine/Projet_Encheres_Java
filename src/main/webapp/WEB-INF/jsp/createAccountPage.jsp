<%@page import="fr.eni.ecole.enchere.exception.BusinessException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer mon compte</title>
</head>
<body>
	
	<header>
		<%@ include file="/WEB-INF/fragments/header.html" %>
	</header>
	
	<p style="color:red">
	<c:forEach var="be" items="${listeErreur}">
		${be}<br>
	</c:forEach></p>
	
	<div class="menu">
		<div></div>
		<div class="pageTitle"> <h1>Mon profil</h1>
		</div>
		<div></div>
	</div>

	<form method="post" action="<%=request.getContextPath()%>/ServletCreateAccount">
		<section>
			<label for="input_pseudo">Pseudo :</label>
			<input type="text" id="pseudo" name="pseudo" value="${pseudo}"/>
			<label for="input_nom">Nom :</label>
			<input type="text" id="nom" name="nom" value="${nom}"/>
		</section>
		
		<section>
			<label for="input_prenom">Prénom :</label>
			<input type="text" id="prenom" name="prenom" value="${prenom}"/>
			<label for="input_email">Email :</label>
			<input type="text" id="email" name="email" value="${email}"/>
		</section>
		
		<section>
			<label for="input_telephone">Téléphone :</label>
			<input type="text" id="telephone" name="telephone" value="${telephone}"/>
			<label for="input_rue">Rue :</label>
			<input type="text" id="rue" name="rue" value="${rue}"/>
		</section>
		
		<section>
			<label for="input_cp">Code Postal :</label>
			<input type="text" id="cp" name="cp" value="${cp}"/>
			<label for="input_ville">Ville :</label>
			<input type="text" id="ville" name="ville" value="${ville}"/>
		</section>
		
		<section>
			<label for="input_nom">Mot de passe :</label>
			<input type="password" id="motDePasse" name="motDePasse"/>
			<label for="input_nom">Confirmation :</label>
			<input type="password" id="confirmation" name="confirmation"/>
		</section>		
		
		<section>
		<input type="submit" value="Créer" id="creer" name="creer"/>
		</section>
		
	</form>
	
	<form method="post" action="<%=request.getContextPath()%>/ServletAccueil"> 
	
		<input type="submit" value="Annuler" id="annuler" name="annuler"/>
	
	</form>
---------------------------------------------------------

 <div class="contentModifier">
		<div id="colonneMilieu">

			<form method="post" action="<%=request.getContextPath()%>/ServletCreateAccount">
				<div id="separateurMilieu">
					<div>

						<div class="input">
							<label for="pseudoModifier" id="labelPseudoModifier">Pseudo
								: </label> <input id="pseudoModifier" name="pseudoModifier" type="text"
								value="${pseudo}">
						</div>
						<div class="input">
							<label for="prenomModifier" id="labelPrenomModifier">Prénom
								: </label> <input id="prenomModifier" name="prenomModifier" type="text"
								value="${nom}">
						</div>
						<div class="input">
							<label for="telModifier" id="labelTelModifier">Téléphone
								: </label> <input id="telModifier" name="telModifier" type="text"
								value="${prenom}">
						</div>
						<div class="input">
							<label for="codePModifier" id="labelCodePModifier">Code
								postal : </label> <input id="codePModifier" name="codePModifier"
								type="text" value="${cp}">
						</div>
						<div class="input">
							<label for="mdpActuel" id="labelMdpActuel">Mot de passe
								 : </label> <input id="motDePasse" name="motDePasse" type="text">
						</div>
						

					</div>

					<div>


						<div class="input">
							<label for="nomModifier" id="labelNomModifier">Nom </label> <input
								id="nomModifier" name="nomModifier" type="text"
								value="${nom}">
						</div>

						<div class="input">
							<label for="emailModifier" id="labelEmailModifier">Email
								: </label> <input id="emailModifier" name="emailModifier" type="text"
								value="${email}">
						</div>
						<div class="input">
							<label for="rueModifier" id="labelRueModifier">Rue : </label> <input
								id="rueModifier" name="rueModifier" type="text"
								 value="${rue}">
						</div>
						<div class="input">
							<label for="villeModifier" id="labelVilleModifier">Ville
								: </label> <input id="villeModifier" name="villeModifier" type="text"
								value="${ville}">
						</div>
						<div class="input">
							<label for="mdpConfirmer" id="labelMdpConfirmer">Confirmation
								: </label> <input id="mdpConfirmer" name="mdpConfirmer" type="text">
						</div>

					</div>
					<!-- TODO servlet modifier profil -->
				</div>
				<div id="lotBtnModifier">
					<input type="submit" id="creer" name="creer" value="Créer"> 
						<a href="${pageContext.request.contextPath}/ServletAccueil" class="button"><input type="button" value="Accueil" /></a>
				</div>
			</form>
		</div>
	</div>

	<div></div> 

</body>
</html>