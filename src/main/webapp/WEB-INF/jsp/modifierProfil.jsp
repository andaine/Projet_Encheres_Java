<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.ecole.enchere.bo.Utilisateur" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>JSP modifier profil</title>
</head>
<body>
	<c:forEach var="be" items="${listeErreur}">
		<div><p style="color:red">${be}</p> </div>
	</c:forEach>
	
	
	
	<header>
		<h1 id="ENI">ENI-Encheres</h1>
		<h1>Mon Profil</h1>
		
	</header>

	<section id="sectionInfos">
	<form method ="post" action="<%=request.getContextPath()%>/ServletUpdateUtilisateurs?id=${userConnecte.getNoUtilisateur()}">
		<div id="divL">
		
			<ul>
				<li>
					<label for="pseudoModifier" id="labelPseudoModifier">Pseudo : </label>
					<input id="pseudoModifier" name="pseudoModifier" type="text" value="${userConnecte.getPseudo()}">
				</li>
				<li>
					<label for="prenomModifier" id="labelPrenomModifier">Prénom : </label>
					<input id="prenomModifier" name="prenomModifier" type="text" value="${userConnecte.getPrenom()}">
				</li>
				<li>
					<label for="telModifier" id="labelTelModifier">Téléphone : </label>
					<input id="telModifier" name="telModifier" type="text" value="${userConnecte.getTelephone()}">
				</li>
				<li>
					<label for="codePModifier" id="labelCodePModifier">Code postal : </label>
					<input id="codePModifier" name="codePModifier" type="text" value="${userConnecte.getCodePostal()}">
				</li>
				<li>
					<label for="mdpActuel" id="labelMdpActuel">Mot de passe actuel : </label>
					<input id="mdpActuel" name="mdpActuel" type="text">
				</li>
				<li>
					<label for="mdpNouveau" id="labelMdpNouveau">Nouveau mot de passe : </label>
					<input id="mdpNouveau" name="mdpNouveau" type="text">
				</li>
			</ul>
			
	
		</div>
	
		<div id="divR">
			<ul>
				<li>
					<label for="nomModifier" id="labelNomModifier">Nom : </label>
					<input id="nomModifier" name="nomModifier" type="text" value="${userConnecte.getNom()}">
				</li>
				<li>
					<label for="emailModifier" id="labelEmailModifier">Email : </label>
					<input id="emailModifier" name="emailModifier" type="text" value="${userConnecte.getEmail()}">
				</li>
				<li>
					<label for="rueModifier" id="labelRueModifier">Rue : </label>
					<input id="rueModifier" name="rueModifier" type="text" value="${userConnecte.getRue()}">
				</li>
				<li>
					<label for="villeModifier" id="labelVilleModifier">Ville : </label>
					<input id="villeModifier" name="villeModifier" type="text" value="${userConnecte.getVille()}">
				</li>
				<li>
					<label for="mdpConfirmer" id="labelMdpConfirmer">Confirmation : </label>
					<input id="mdpConfirmer" name="mdpConfirmer" type="text">
				</li>
			</ul>
		</div>
	
		<div id="divCredit">
			<label for="credit" id="labelCredit ">Crédit</label>
			<p id="credit">
				${userConnecte.getCredit()}		
			</p> 
		</div>
		<!-- TODO servlet modifier profil -->
		<input type="submit" id="enregistrerProfil" name="enregistrerProfil" value="Enregistrer" >
	</form>
	</section>

	
		
			
		
	<section id="sectionBoutons">	
		<a href="<%=request.getContextPath()%>/ServletDelete?id=${userConnecte.getNoUtilisateur()}">
			<input type="submit" id="supprimerProfil" name="supprimerProfil" value="Supprimer mon compte">	
		</a>	
	</section>

</body>
</html>