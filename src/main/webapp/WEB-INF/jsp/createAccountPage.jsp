<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	<h2>Mon profil</h2>

	<form method="post" action="<%=request.getContextPath()%>/ServletConnexion">
		<section>
			<label for="input_nom">Pseudo :</label>
			<input type="text" id="pseudo" name="pseudo" />
			<label for="input_nom">Nom :</label>
			<input type="text" id="nom" name="nom" />
		</section>
		
		<section>
			<label for="input_nom">Prénom :</label>
			<input type="text" id="prenom" name="prenom" />
			<label for="input_nom">Email :</label>
			<input type="text" id="email" name="email" />
		</section>
		
		<section>
			<label for="input_nom">Téléphone :</label>
			<input type="text" id="telephone" name="telephone" />
			<label for="input_nom">Rue :</label>
			<input type="text" id="rue" name="rue" />
		</section>
		
		<section>
			<label for="input_nom">Code Postal :</label>
			<input type="text" id="cp" name="cp" />
			<label for="input_nom">Email :</label>
			<input type="text" id="email" name="email" />
		</section>
		
		<section>
			<label for="input_nom">Mot de passe :</label>
			<input type="password" id="mdp" name="mdp" />
			<label for="input_nom">Confirmation :</label>
			<input type="password" id="confirmation" name="confirmation" />
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