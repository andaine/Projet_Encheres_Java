<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nouvelle Vente</title>
</head>
<body id="testBody">
	<h1>nouvelle vente</h1>
	
	<div id="image">
	</div>


	<section id="sectionNouvelleVente">
		<ul>
			<li><label for="nomArticle" id="labelPseudo">Article :</label>
				<input type="text" id="nomArticle" name="nomArticle"></li>
			<li><label for="nom" id="Description">Description :</label>
				<textarea id="nomDescription" name="nomDescription" rows="5" cols="33"> </textarea></li>
			<li><label for="Categorie" id="Categorie">Categorie :</label> <select
				name="categorie" id="categorie">
					<option></option>
					<c:forEach var="cat" items="${categorie}">
						<option value="${cat.noCategorie}">${cat.libelle}</option>
					</c:forEach>
			</select></li>

			<li><label for="email" id="prix">Mise à prix :</label>
			 <input type="number" id="prix" name="prix" step="50" min="0"></li>
			<li><label for="debutEnchere" id="debutEnchere">Début des enchère :</label>
				 <input type="date" id="debutEnchere" name="debutEnchere"></li>
			<li><label for="finEnchere">Fin des enchères :</label>
				 <input type="date" id="finEnchere" name="finEnchere" ></li>
			<li>
		</ul>

	</section>
		<form>
			<fieldset class="fieldset">
				<legend>Retrait</legend>

				<label for="rue">Rue : </label>
				<input type="text" id="rue" name="rueRetrait" value="${userConnecte.getRue()}"> <br> 
				<label for="sasquatch">Code Postal :</label>
				<input type="text" id="codePostal" name="codePostal" value="${userConnecte.getCodePostal()}"> <br> 
				 <label for="mothman">Ville : </label>
				<input type="text" id="mothman" name="monster" value="${userConnecte.getVille()}" />
			</fieldset>
		</form>

	
</body>
</html>