<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nouvelle Vente</title>
</head>
<body>
	<h1>nouvelle vente</h1>


	<section id="sectionNouvelleVente">
		<ul>
			<li><label for="pseudo" id="labelPseudo">Article :</label>
				<p id="pseudo" name="pseudo"></p></li>
			<li><label for="nom" id="labelNom">Description :</label>
				<p id="description" name="nom"></p></li>
			<li><label for="Categorie" id="Categorie">Categorie :</label> <select
				name="categorie" id="categorie">
					<option></option>
					<c:forEach var="cat" items="${categorie}">
						<option value="${cat.noCategorie}">${cat.libelle}</option>
					</c:forEach>
			</select></li>

			<li><label for="email" id="prix">Mise à prix :</label> <input
				type="number" id="prix" name="prix" step="50"></li>
			<li><label for="telephone" id="labelTelephone">Début des
					enchère :</label>
				<p id="telephone" name="telephone"></p></li>
			<li><label for="rue" id="labelRue">Fin des enchères :</label>
				<p id="rue" name="rue"></p></li>
			<li>
		</ul>


		<form>
			<fieldset>
				<legend>Retrait</legend>

				<label for="rue">Rue : </label>
				<input type="text" id="rue" name="rueRetrait" value=""> <br> 
				<label for="sasquatch">Code Postal :</label>
				<input type="text" id="sasquatch" name="monster" value=""> <br> 
				 <label for="mothman">Mothman : </label>
				<input type="text" id="mothman" name="monster" value="" />
			</fieldset>
		</form>

	</section>
</body>
</html>