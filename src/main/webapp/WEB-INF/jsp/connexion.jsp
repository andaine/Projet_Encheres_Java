<%@page import="java.util.List"%>
<%@page import="fr.eni.ecole.enchere.exception.BusinessException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.html" %>
	
	<header>
		<h1 id="ENI">ENI-Encheres</h1>
	</header>


	<p style="color:red"><c:forEach var="be" items="${listeErreur}">
		${be}
	</c:forEach></p>
	
	
	<form method="post" action="<%=request.getContextPath()%>/ServletLogin">
		<section>
			<label for="input_nom">Identifiant :</label>
			<input type="text" id="identifiant" name="identifiant" />
		</section>
		
		<section>
			<label for="input_nom">Mot de passe :</label>
			<input type="password" id="motDePasse" name="motDePasse" />
		</section>
		
		<section>
		<input type="submit" value="Connexion" id="connexion" name="ServletConnexion"/>
	
		<label for="input_nom">Se souvenir de moi :</label>
		<input type="checkbox" id="seSouvenir" name="seSouvenir" unchecked>
		<a href="<%=request.getContextPath()%>/Login"><input type="button" value="Mot de passe oublié"/></a>
		</section>
		
	</form>
	
	<form method="get" action="<%=request.getContextPath()%>/ServletCreateAccount"> 
	
		<input type="submit" id= "creerCompte" value="Créer un compte" />
	
	</form>

</body>
</html>