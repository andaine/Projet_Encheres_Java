<%@page import="javax.swing.text.Document"%>
<%@page import="javax.swing.JRadioButton"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.eni.ecole.enchere.bo.Utilisateur"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP UserConnecte Achats</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.html" %>
	
	<p style="color: red">
		<c:forEach var="be" items="${listeErreur}">
		${be}
	</c:forEach>
	</p>

	<header>
		<h1 id="ENI">ENI-Encheres</h1>
		<nav id="menu">
			<ul>
				<li><a href="<%=request.getContextPath()%>/ServletConnecteAchats"
					name="lienEncheres" id="lienEncheres">Enchères</a></li>
				<li><a href="<%=request.getContextPath()%>/ServletNouvelleVente"
					name="lienNouvelleVente" id="lienNouvelleVente">Vendre un
						article</a> <!--  TODO ServletNouvelleVente --></li>
				<li><a href="<%=request.getContextPath()%>/ServletMonProfil"
					name="lienMonProfil" id="lienMonProfil">Mon profil</a></li>
				<li><a href="<%=request.getContextPath()%>/ServletDeconnexion"
					name="lienDeconnexion" id="lienDeconnexion">Déconnexion</a></li>
			</ul>
		</nav>
		<h2 id="titreListe">Liste des enchères</h2>
	</header>

	<!-- SECTION RECHERCHER DES ENCHERES -->
	<section id="sectionFiltres">
		<h2 id="titreFiltres">Filtres :</h2>
		<form method="post"
			action="<%=request.getContextPath()%>/ServletConnecteAchats"
			name="formAfficherEncheres" id="formAfficherEncheres">
			<input type="text" name="textFiltreArticle" id="textFiltreArticle" placeholder="le nom de l'article contient">
			<label for="selectCategorie" id="labelCategorie">Catégories</label>
			<select name="categorie" id="categorie">
				<c:forEach var="cat" items="${categorie}">
					<option value="${cat.noCategorie}">${cat.libelle}</option>
				</c:forEach>
			</select>
			<!--  SECTION MES ACHATS/MES VENTES -->
			<section id="sectionAchatVente">
				<div id="divAchat">
					<input type="radio" id="achats" name="radioButton" value="Achats" checked/>
					<label for="achats">Achats</label>
					<ul>
						<li><input type="checkbox" id="encheresOuvertes"
							name="encheresOuvertes"> <label for="encheresOuvertes">enchères
								ouvertes</label></li>
						<li><input type="checkbox" id="mesEncheres"
							name="mesEncheres"> <label for="mesEncheres">mes
								enchères</label></li>
						<li><input type="checkbox" id="encheresRemportes"
							name="encheresRemportes"> <label for="encheresRemportes">mes
								enchères remportées</label></li>
					</ul>
				</div>
				<div id="divVente">
					
						<input type="radio" id="ventes" name="radioButton" />
							 <label for="ventes">Mes ventes</label>
					
					<ul>
						<li><input type="checkbox" id="ventesEnCours"
							name="ventesEnCours"> <label for="ventesEnCours">mes
								ventes en cours</label></li>
						<li><input type="checkbox" id="ventesNonDebutees"
							name="ventesNonDebutees"> <label for="ventesNonDebutees">ventes
								non débutées</label></li>
						<li><input type="checkbox" id="ventesTerminees"
							name="ventesTerminees"> <label for="ventesTerminees">ventes
								terminées</label></li>
					</ul>
				</div>
			</section>
			<input type="submit" id="boutonRechercher" value="Rechercher">
		</form>
	</section>

	 <section id="sectionAfficherEncheres">
	

		<c:forEach var="e" items="${listeEncheres}">
			<div id="divEnchere">
				<p>${e.getNomArticle() }</p>
				<p>Prix : ${e.getMontantEnchere()}</p>
				<p>Fin de l'enchère : ${e.getDateEnchere()}</p>
				<br>
				<p>Vendeur : <a href="${pageContext.request.contextPath }/ServletAutreProfil?nom=${e.getPseudoUser()}"  id="nomAutreUser">${e.getPseudoUser() }</a>
					
				</p>
			</div>
		</c:forEach>




	</section>





</body>
</html>