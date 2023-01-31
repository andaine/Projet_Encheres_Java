package fr.eni.ecole.enchere.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("dopost - servlet update");

		if (request.getParameter("mdpConfirmer").equals(request.getParameter("mdpNouveau"))) {
			
			String pseudo = request.getParameter("pseudoModifier");
			String nom = request.getParameter("nomModifier");
			String prenom = request.getParameter("prenomModifier");
			String email = request.getParameter("emailModifier");
			String telephone = request.getParameter("telModifier");
			String rue = request.getParameter("rueModifier");
			String code_postal = request.getParameter("codePModifier");
			String ville = request.getParameter("villeModifier");
			String mot_de_passe = request.getParameter("mdpConfirmer");

			EnchereManager em = new EnchereManager();
			Utilisateur userUpdate = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, code_postal, ville,
					mot_de_passe);

			try {
				em.updateUtilisateur(userUpdate);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println();
		}
		;
	}

}
