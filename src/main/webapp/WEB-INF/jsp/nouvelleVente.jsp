<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<meta charset="UTF-8">
<title>Enchères ENI - Nouvelle Vente</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.html"%>

	<p style="color:red"><c:forEach var="be" items="${listeErreur}">
		${be} <br>
	</c:forEach></p>
	
	<div id=titreNouvelleVente>
		<h1>Nouvelle vente</h1>
	</div>
	<div class="content">
		<div>
			<img src="">
		</div>
		<div>
			<form method ="post" action="${pageContext.request.contextPath}/ServletNouvelleVente" name="formNouvelleVente" id="formNouvelleVente">
				<div class="input">
					<label for="article">Article</label> <input type="text"
						id="article" name="article" placeholder="Nom de l'article"
						value="${article}" required />
				</div>
				<div class="input">
					<label for="description">Description</label>
					<textarea id="description" name="description"
						placeholder="Rentrez votre description" rows="4" cols="30">${description}</textarea>
				</div>
				<div class="input">
					<label for="categorie">Catégorie</label> <select id="categorie"
						name="categorie" required>
						<c:forEach var="cat" items="${categorie}">
							<option value="${cat.noCategorie}" ${cat.noCategorie.equals(categorieChoisie) ? 'selected':''}> ${cat.libelle}</option>
						</c:forEach>
					</select>
				</div>
				<div class="input">
					<label for="photo">Photo de l'article</label> 
					<input type="file" accept="image/*" id="photo" name="photo"
						placeholder="Envoyer une image">
				</div>
				<div class="input">
					<label for="prix">Mise à prix</label> <input type="number" min="0"
						id="prix" name="prix" step="1"
						placeholder="prix de vente initial" value="${prix}" required />
				</div>

				<div class="input">
					<label for="debut">Début de l'enchère</label> <input type="datetime-local"
						id="debut" name="debut" value="${debut}" required />
				</div>
				<div class="input">
					<label for="fin">Fin de l'enchère</label> <input type="datetime-local"
						id="fin" name="fin" value="${fin}"  required/>
				</div>
			<fieldset id="retrait">
                <legend class="title">Retrait</legend>
                <div>
                    <div class="input">
                        <label for="rue">Rue</label>
                        <input type="text" id="rue" name="rue" placeholder="Nom de la rue du retrait" 
                        value="${rue}" required/>
                    </div>
                    <div class="input">
                        <label for="postal">Code postal</label>
                        <input type="text" pattern="\d{5}" id="postal" name="postal"
                               placeholder="Code postal de la ville" value="${postal}" required/>
                    </div>
                    <div class="input">
                        <label for="ville">Ville</label>
                        <input type="text" id="ville" name="ville" placeholder="Ville" value="${ville}" required/>
                    </div>
                </div>
            </fieldset>	
				
				<div class="actions">
						<input type="submit" name="enregistrer" value="Enregistrer" /> 
						<a href="${pageContext.request.contextPath}/ServletAccueil" class="button"><input type="button" value="Annuler" /></a>
				</div>
			</form>
		</div>
		<div></div>
	</div>
</body>
</html>