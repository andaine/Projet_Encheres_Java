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
	
	<div class="content">
		
		<div></div>
	<div>
		<form action="${pageContext.request.contextPath}/ServletUpdateUtilisateurs">
		
			<div class="labelProfil">
				<label for="pseudo" id="labelPseudo">Pseudo</label>
				${infoVendeur.getPseudo()}
			</div>
			<div class="labelProfil">
				<label for="nom" id="labelNom">Nom</label> 
				${infoVendeur.getNom()}
			</div>
			<div class="labelProfil">
				<label for="prenom" id="labelPrenom">Prenom</label>
				 ${infoVendeur.getPrenom()}
				
			</div>
			<div class="labelProfil">
				<label for="email" id="labelEmail">Email</label> 
				${infoVendeur.getEmail()}
				
			</div >
			<div class="labelProfil">
				<label for="telephone" id="labelTelephone">Téléphone</label> 
				${infoVendeur.getTelephone()}
			</div>
			<div class="labelProfil">
				<label for="rue" id="labelRue">Rue</label>
				${infoVendeur.getRue()} 
			
			</div>
			<div class="labelProfil">
				<label for="codePostal" id="labelCodePostal">Code postal</label> 
				${infoVendeur.getCodePostal()}
			</div>
			<div class="labelProfil">
				<label for="ville" id="labelVille">Ville</label> 
				${infoVendeur.getVille()}
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