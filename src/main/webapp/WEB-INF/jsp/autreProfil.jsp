<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="fr.eni.ecole.enchere.bo.Utilisateur" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<!--  TODO recupérer le user dont on veut afficher le profil -->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP autre profil</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.html" %>

	<header><h2>ENI - enchères</h2></header>

	<section id="sectionUserID">
		<ul>
			<li>
				<label for="pseudo" id="labelPseudo">Pseudo :</label>
				<p id="pseudo" name="pseudo"> <!-- TODO autre user --></p>
			</li>
			<li>
				<label for="nom" id="labelNom">Nom :</label>
				<p id="nom" name="nom"><!-- TODO autre user --> </p>
			</li>
			<li>
				<label for="prenom" id="labelPrenom">Prenom :</label>
				<p id="prenom" name="prenom"><!-- TODO autre user -->  </p>
			</li>
			<li>
				<label for="email" id="labelEmail">Email :</label>
				<p id="email" name="email"><!-- TODO autre user --> </p>
			</li>
			<li>
				<label for="telephone" id="labelTelephone">Téléphone :</label>
				<p id="telephone" name="telephone"><!-- TODO autre user --> </p>
			</li>
			<li>
				<label for="rue" id="labelRue">Rue :</label>
				<p id="rue" name="rue"><!-- TODO autre user -->  </p>
			</li>
			<li>
				<label for="codePostal" id="labelCodePostal">Code postal :</label>
				<p id="codePostal" name="codePostal"><!-- TODO autre user --></p>
			</li>
			<li>
				<label for="ville" id="labelVille">Ville :</label>
				<p id="ville" name="ville"><!-- TODO autre user --> </p>
			</li>						
		</ul>

	
	
	
	
	</section>




</body>
</html>