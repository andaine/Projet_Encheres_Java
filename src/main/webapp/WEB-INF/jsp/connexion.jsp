<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>

	<header>
		<h1 id="ENI">ENI-Encheres</h1>
	</header>

	<form method="post" action="<%=request.getContextPath()%>/Login">
		<section>
			<label for="input_nom">Identifiant :</label>
			<input type="text" name="identifiant" />
		</section>
		
		<section>
			<label for="input_nom">Mot de passe :</label>
			<input type="text" name="mdp" />
		</section>
		
		<section>
		<input type="submit" value="Connexion" name="connexion"/>
	
		<label for="input_nom">Se souvenir de moi :</label>
		<input type="checkbox" name="seSouvenir" unchecked>
		<a href="<%=request.getContextPath()%>/Login"><input type="button" value="Mot de passe oublié"/></a>
		</section>
		
	</form>
	
	<form method="post" action="<%=request.getContextPath()%>/CreateAccount"> 
	
		<input type="submit" value="Créer un compte" />
	
	</form>

</body>
</html>