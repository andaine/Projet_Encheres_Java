<%@page import="java.util.List"%>
<%@page import="fr.eni.ecole.enchere.exception.BusinessException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>
	
	<header>
		<%@ include file="/WEB-INF/fragments/header.html" %>
	</header>


	<div id="titreConnexion">
		<h1>Connexion</h1>
	</div>
	
	<p style="color:red"><c:forEach var="be" items="${listeErreur}">
		${be}
	</c:forEach></p>

<div class="content">
	<div></div>
	
	<div>
	
	<form method="post" action="${pageContext.request.contextPath}/ServletLogin">
		<div class="input">
			<label for="input_nom">Identifiant</label>
			<input type="text" id="identifiant" name="identifiant" required/>
		</div>
		
		<div class="input">
			<label for="input_nom">Mot de passe</label>
			<input type="password" id="motDePasse" name="motDePasse" required/>
		</div>
		
		<section>
		<input type="submit" value="Connexion" id="connexion" name="ServletConnexion"/>
	
		<label for="input_nom">Se souvenir de moi</label>
		<input type="checkbox" id="seSouvenir" name="seSouvenir">
		<a href="${pageContext.request.contextPath}/ServletLogin">Mot de passe oublié</a>
		</section>
		
	</form>
	<form method="get" action="${pageContext.request.contextPath}/ServletCreateAccount"> 
			<input type="submit" id= "creerCompte" value="Créer un compte" />
	</form>
	</div>
	<div></div>
</div>
</body>
</html>