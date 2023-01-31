<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.ecole.enchere.bo.Utilisateur" %>
<%Utilisateur userConnecte =(Utilisateur) session.getAttribute("userConnecte") ;%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>JSP modifier profil</title>
</head>
<body>

	<header>
		<h1 id="ENI">ENI-Encheres</h1>
		<h1>Mon Profil</h1>
		
	</header>

	<section id="sectionInfos">
	
		<div id="divL">
			<ul>
				<li>
					<label for="pseudoModifier" id="labelPseudoModifier">Pseudo : </label>
					<input id="pseudoModifier" name="pseudoModifier" type="text">
				</li>
				<li>
					<label for="prenomModifier" id="labelPrenomModifier">Prénom : </label>
					<input id="prenomModifier" name="prenomModifier" type="text">
				</li>
				<li>
					<label for="telModifier" id="labelTelModifier">Téléphone : </label>
					<input id="telModifier" name="telModifier" type="text">
				</li>
				<li>
					<label for="codePModifier" id="labelCodePModifier">Code postal : </label>
					<input id="codePModifier" name="codePModifier" type="text">
				</li>
				<li>
					<label for="mdpActuel" id="labelMdpActuel">Mot de passe actuel : </label>
					<input id="mdpActuel" name="mdpActuel" type="text" value="<%=userConnecte.getMotDePasse()%>">
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
					<input id="nomModifier" name="nomModifier" type="text">
				</li>
				<li>
					<label for="emailModifier" id="labelEmailModifier">Email : </label>
					<input id="emailModifier" name="emailModifier" type="text">
				</li>
				<li>
					<label for="rueModifier" id="labelRueModifier">Rue : </label>
					<input id="rueModifier" name="rueModifier" type="text">
				</li>
				<li>
					<label for="villeModifier" id="labelVilleModifier">Ville : </label>
					<input id="villeModifier" name="villeModifier" type="text">
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
				<%=userConnecte.getCredit() %>			
			</p> 
		</div>

	</section>

	<section id="sectionBoutons">
		<form method ="post" action="<%=request.getContextPath()%>/ServletModifierProfil">
			<!-- TODO servlet modifier profil -->
			<input type="submit" id="enregistrerProfil" name="enregistrerProfil" value="Enregistrer" >
		</form>
		<form method="get" action="<%=request.getContextPath()%>/ServletDelete">
			<!--  TODO servlet delete -->
			<input type="submit" id="supprimerProfil" name="supprimerProfil" value="Supprimer mon compte">	
		</form>	
	</section>

</body>
</html>