package fr.eni.ecole.enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import fr.eni.ecole.enchere.bll.EnchereManager;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

/**
 * Servlet implementation class ServletUpdateUtilisateurs
 */
@WebServlet("/ServletUpdateUtilisateurs")
public class ServletUpdateUtilisateurs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modifierProfil.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		System.out.println("dopost - servlet update");

		String pseudo = request.getParameter("pseudoModifier");
		String nom = request.getParameter("nomModifier");
		String prenom = request.getParameter("prenomModifier");
		String email = request.getParameter("emailModifier");
		String telephone = request.getParameter("telModifier");
		String rue = request.getParameter("rueModifier");
		String code_postal = request.getParameter("codePModifier");
		String ville = request.getParameter("villeModifier");
//		String mot_de_passe = request.getParameter("mdpConfirmer");

		if (request.getParameter("mdpConfirmer").equals(request.getParameter("mdpNouveau")) && request.getParameter("mdpActuel").equals("")) {
			
			String mdpNouveau = request.getParameter("mdpNouveau");
			Utilisateur userUpdate = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mdpNouveau);
			userUpdate.setNoUtilisateur(Integer.parseInt(request.getParameter("id")) );
		} 

		System.out.println(pseudo);
		EnchereManager em = new EnchereManager();
		Utilisateur userUpdate = new Utilisateur();

		try {
			em.updateUtilisateur(userUpdate);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
		rd.forward(request, response);
	}

}
