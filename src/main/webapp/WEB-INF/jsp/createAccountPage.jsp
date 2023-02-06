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
	
	<h2>Mon profil</h2>

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

</body>
</html>