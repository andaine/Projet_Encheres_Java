<%@page import="fr.eni.ecole.enchere.exception.BusinessException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer mon compte</title>
</head>
<body>

	<header>
		<h1 id="ENI">ENI-Encheres</h1>
	</header>
	
	<p style="color:red"><c:forEach var="be" items="${listeErreur}">
		${be}
	</c:forEach></p>
	
	<h2>Mon profil</h2>

	<form method="post" action="<%=request.getContextPath()%>/ServletCreateAccount">
		<section>
			<label for="input_pseudo">Pseudo :</label>
			<input type="text" id="pseudo" name="pseudo"/>
			<label for="input_nom">Nom :</label>
			<input type="text" id="nom" name="nom" required/>
		</section>
		
		<section>
			<label for="input_prenom">Prénom :</label>
			<input type="text" id="prenom" name="prenom" required/>
			<label for="input_email">Email :</label>
			<input type="text" id="email" name="email" required/>
		</section>
		
		<section>
			<label for="input_telephone">Téléphone :</label>
			<input type="text" id="telephone" name="telephone" />
			<label for="input_rue">Rue :</label>
			<input type="text" id="rue" name="rue" required/>
		</section>
		
		<section>
			<label for="input_cp">Code Postal :</label>
			<input type="text" id="cp" name="cp" required/>
			<label for="input_ville">Ville :</label>
			<input type="text" id="ville" name="ville" required/>
		</section>
		
		<section>
			<label for="input_nom">Mot de passe :</label>
			<input type="password" id="motDePasse" name="motDePasse" required/>
			<label for="input_nom">Confirmation :</label>
			<input type="password" id="confirmation" name="confirmation" required/>
		</section>		
		
		<section>
		<input type="submit" value="Créer" id="creer" name="creer"/>
		</section>
		
	</form>
	
	<form method="post" action="<%=request.getContextPath()%>/ServletAccueil"> 
	
		<input type="submit" value="Annuler" id="annuler" name="annuler"/>
	
	</form>

</body>
</html>