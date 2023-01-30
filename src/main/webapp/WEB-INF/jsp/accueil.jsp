<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<!-- TODO integrer la liste Catégories dans le select -->
			</select>
			<input type="submit" id="boutonRechercher" value="Rechercher">
		</form>
	</section>

	<section id="afficherEncheres">
		<!-- TODO afficher la liste des encheres en cours -->
	</section>




</body>
</html>