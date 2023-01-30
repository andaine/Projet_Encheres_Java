<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP UserConnecte</title>
</head>
<body>
	<header>
		<h1 id="ENI">ENI-Encheres</h1>
		<nav id="menu">
			<ul>
				<li>
					<a href="<%=request.getContextPath()%>/..." name="lienEncheres" id="lienEncheres">Enchères</a>
						<!-- TODO Servlet userConnecte -->
				</li>
				<li>
					<a href="<%=request.getContextPath() %>/..." name="lienNouvelleVente" id="lienNouvelleVente">Vendre un article</a>
						<!--  TODO ServletNouvelleVente -->
				</li>
				<li>
					<a href="<%=request.getContextPath() %>/..." name="lienMonProfil" id="lienMonProfil">Mon profil</a>
						<!--  TODO Servlet monProfil -->
				</li>
				<li>
					<a href="<%=request.getContextPath() %>/ServletAccueil" name="lienDeconnexion" id="lienDeconnexion">Déconnexion</a>
				</li>			
			</ul>	
		</nav>		
		<h2 id="titreListe">Liste des enchères</h2>
	</header>

		<!-- SECTION RECHERCHER DES ENCHERES -->
	<section id="sectionFiltres">
		<h2 id="titreFiltres">Filtres :</h2>
		<form method="post" action="<%=request.getContextPath()%>/ServletAfficherEncheres" name="formAfficherEncheres" id="formAfficherEncheres">
			<input type="text" name="textFiltreArticle" id="textFiltreArticle">
			<label for="selectCategorie" id="labelCategorie">Catégories</label>
			<select name="categorie" id="categorie">
				<!-- TODO integrer la liste Catégories dans le select -->
			</select>		
					<!--  SECTION MES ACHATS/MES VENTES -->
					<section id="sectionAchatVente">
						<div id="divAchat">
							<input type="radio" id="achats" name="achats" value="Achats"/>
								<ul>
									<li><input type="checkbox" id="encheresOuvertes" name="encheresOuvertes" value="enchères ouvertes"></li>
									<li><input type="checkbox" id="mesEncheres" name="mesEncheres" value="mes enchères"></li>
									<li><input type="checkbox" id="encheresRemportes" name="encheresRemportes" value="mes enchères remportées"></li>
								</ul>
						</div>
						<div id="divVente">
							<input type="radio" id="achats" name="achats" value="Mes ventes"/>
								<ul>
									<li><input type="checkbox" id="ventesEnCours" name="ventesEnCours" value="mes ventes en cours"></li>
									<li><input type="checkbox" id="ventesNonDebutees" name="ventesNonDebutees" value="ventes non débutées"></li>
									<li><input type="checkbox" id="ventesTerminees" name="ventesTerminees" value="ventes terminées"></li>
								</ul>
						</div>				
					</section>
			<input type="submit" id="boutonRechercher" value="Rechercher">
		</form>
	</section>

	<section id="sectionAfficherEncheres">
		<!-- TODO afficher la liste des encheres en cours -->
	</section>





</body>
</html>