<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<%@ page import="fr.eni.ecole.enchere.bo.Enchere" %> 
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
       

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Accueil</title>
</head>
<body>
	

	<header>
		<h1 id="ENI">ENI-Encheres</h1>
		<a href="<%=request.getContextPath()%>/ServletLogin" name="lienLogin" id="lienLogin">S'inscrire - Se connecter</a>
		<h2 id="titreListe">Liste des enchères</h2>
	</header>

	<section id="filtres">
		<h2 id="titreFiltres">Filtres :</h2>
		<form method="post" action="<%=request.getContextPath()%>/ServletAfficherEncheres" name="formAfficherEncheres" id="formAfficherEncheres">
			<input type="text" name="textFiltreArticle" id="textFiltreArticle">

			<label for="selectCategorie" id="labelCategorie">Catégories</label>
			<select name="categorie" id="categorie">
				<p><c:forEach var="cat" items="${categorie}">
					${cat}
				</c:forEach></p>   
			
			
				<!-- TODO integrer la liste Catégories dans le select -->
			</select>
			<input type="submit" id="boutonRechercher" value="Rechercher">
		</form>
	</section>

	<section id="afficherEncheres">
		<!-- TODO afficher la liste des encheres en cours -->
		<c:forEach var="e" items="${listeEncheres}">
			<p>
				COUCOU
				${e.getMontantEnchere()}	
			</p>
		
		
		
		
		</c:forEach>
		
		
		
		
		
		
	</section>





</body>
</html>