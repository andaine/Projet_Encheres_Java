<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="fr.eni.ecole.enchere.bo.Utilisateur" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   




<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<meta charset="UTF-8">
<title>JSP mon profil</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.html" %>
	<div class="menu">
	<div></div>
	
		<div><h2 class="pageTitle">profil</h2></div> 
	<div></div>	

	
	</div>
	
	<div class="infoUser">
		
		<div></div>
	<div>
		<form action="${pageContext.request.contextPath}/ServletUpdateUtilisateurs">
		
			<div class="labelProfil">
				<label for="pseudo" id="labelPseudo">Pseudo</label>
				<span class="info"> ${infoVendeur.getPseudo()} </span>
			</div>
			<div class="labelProfil">
				<label for="nom" id="labelNom">Nom</label> 
				<span class="info">${infoVendeur.getNom()}</span>
			</div>
			<div class="labelProfil">
				<label for="prenom" id="labelPrenom">Prenom</label>
				<span class="info">${infoVendeur.getPrenom()}</span>
				
			</div>
			<div class="labelProfil">
				<label for="email" id="labelEmail">Email</label> 
				<span class="info">${infoVendeur.getEmail()}</span>
				
			</div >
			<div class="labelProfil">
				<label for="telephone" id="labelTelephone">Téléphone</label> 
				<span class="info">${infoVendeur.getTelephone()}</span>
			</div>
			<div class="labelProfil">
				<label for="rue" id="labelRue">Rue</label>
				<span class="info">${infoVendeur.getRue()} </span>
			
			</div>
			<div class="labelProfil">
				<label for="codePostal" id="labelCodePostal">Code postal</label> 
				<span class="info">${infoVendeur.getCodePostal()}</span>
			</div>
			<div class="labelProfil">
				<label for="ville" id="labelVille">Ville</label> 
				<span class="info">${infoVendeur.getVille()}</span>
			</div>						
	
	<div class="actions">
	<c:if test="">
						<input type="submit" name="Modifier" value="Modifier" /> 			
		</c:if>
	</div>
	
	
		
			
			
	
	
	
	
		</form>
	</div>
	<div></div>
	
	
	</div>



</body>
</html>