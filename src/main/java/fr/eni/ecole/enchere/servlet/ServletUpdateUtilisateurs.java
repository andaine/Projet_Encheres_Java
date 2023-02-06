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

import fr.eni.ecole.enchere.bll.UserManager;
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
		String motDePasseActuel = request.getParameter("mdpActuel");
		String motDePasseConfirmer = request.getParameter("mdpConfirmer");
		String motDePasseNouveau = request.getParameter("mdpNouveau");
		int id = Integer.parseInt(request.getParameter("id"));
		
		
//		Utilisateur userMdp = (Utilisateur) session.getAttribute("userConnecte");
//		String mdpBDD = userMdp.getMotDePasse();
		
		
		if (motDePasseConfirmer.equals(motDePasseNouveau) && !motDePasseConfirmer.isEmpty() && !motDePasseNouveau.isEmpty()) {	
			motDePasseActuel = request.getParameter("mdpNouveau");
		} else if(!motDePasseConfirmer.equals(motDePasseNouveau)) {
			BusinessException be = new BusinessException();
			be.addMessage("les 2 mots de passe ne sont pas identiques");
			request.setAttribute("listeErreur", be.getListeMessage());
		}
		
		
//		if (request.getParameter("mdpActuel").equals(mdpBDD)) {
			Utilisateur userConnecte = new Utilisateur(id, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, motDePasseActuel);
			UserManager em = UserManager.getInstance();
			try {
				em.updateUtilisateur(userConnecte);
				session.setAttribute("userConnecte", userConnecte);
			} catch (BusinessException e) {
				request.setAttribute("listeErreur", e.getListeMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modifierProfil.jsp");
				rd.forward(request, response);
			}
	
		RequestDispatcher rd = request.getRequestDispatcher("/ser");
		rd.forward(request, response);
	}

}
