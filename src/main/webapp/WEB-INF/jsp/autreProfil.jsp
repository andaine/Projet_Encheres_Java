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
				<label for="pseudo" id="labelPseudo">Pseudo : ${infoVendeur.getPseudo()}</label>	
			</li>
			<li>
				<label for="nom" id="labelNom">Nom : ${infoVendeur.getNom()}</label>
			</li>
			<li>
				<label for="prenom" id="labelPrenom">Prenom : ${infoVendeur.getPrenom()}</label>
			</li>
			<li>
				<label for="email" id="labelEmail">Email : ${infoVendeur.getEmail()}</label>
			</li>
			<li>
				<label for="telephone" id="labelTelephone">Téléphone : ${infoVendeur.getTelephone()}</label>
			</li>
			<li>
				<label for="rue" id="labelRue">Rue : ${infoVendeur.getRue()}</label>
			</li>
			<li>
				<label for="codePostal" id="labelCodePostal">Code postal : ${infoVendeur.getCodePostal()}</label>	
			</li>
			<li>
				<label for="ville" id="labelVille">Ville : ${infoVendeur.getVille()}</label>
			</li>						
		</ul>

	</section>

	<form method="get" action="${pageContext.request.contextPath}/ServletConnecteAchats">
		<input type="submit" value="Accueil" id="connexion"/>
	</form>

</body>
</html>