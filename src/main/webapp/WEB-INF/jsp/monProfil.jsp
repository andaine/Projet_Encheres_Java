<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="fr.eni.ecole.enchere.bo.Utilisateur" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   


<%Utilisateur userConnecte =(Utilisateur) session.getAttribute("userConnecte") ;%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP mon profil</title>
</head>
<body>
	<header><h2>ENI - enchères</h2></header>

	<section id="sectionUserID">
		<ul>
			<li>
				<label for="pseudo" id="labelPseudo">Pseudo :</label>
				<p id="pseudo" name="pseudo"><%=userConnecte.getPseudo() %>  </p>
			</li>
			<li>
				<label for="nom" id="labelNom">Nom :</label>
				<p id="nom" name="nom"><%=userConnecte.getNom() %>  </p>
			</li>
			<li>
				<label for="prenom" id="labelPrenom">Prenom :</label>
				<p id="prenom" name="prenom"><%=userConnecte.getPrenom() %>  </p>
			</li>
			<li>
				<label for="email" id="labelEmail">Email :</label>
				<p id="email" name="email"><%=userConnecte.getEmail() %>  </p>
			</li>
			<li>
				<label for="telephone" id="labelTelephone">Téléphone :</label>
				<p id="telephone" name="telephone"><%=userConnecte.getTelephone() %>  </p>
			</li>
			<li>
				<label for="rue" id="labelRue">Rue :</label>
				<p id="rue" name="rue"><%=userConnecte.getRue() %>  </p>
			</li>
			<li>
				<label for="codePostal" id="labelCodePostal">Code postal :</label>
				<p id="codePostal" name="codePostal"><%=userConnecte.getCodePostal() %>  </p>
			</li>
			<li>
				<label for="ville" id="labelVille">Ville :</label>
				<p id="ville" name="ville"><%=userConnecte.getVille() %>  </p>
			</li>						
		</ul>
	
	<a href="<%=request.getContextPath()%>/ServletModifierProfil">
		<!-- TODO servlet modifier profil -->
	
		<input name="modifierProfil" id="modifierProfil" type="submit" value="Modifier">
	</a>
	
	
	
	
	</section>




</body>
</html>