package fr.eni.ecole.enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.enchere.bll.UserManager;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

/**
 * Servlet implementation class ServletCreateAccount
 */
@WebServlet("/ServletCreateAccount")
public class ServletCreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/createAccountPage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("cp");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("motDePasse");
		int credit = 100;
		try {

			Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit);

			UserManager em = UserManager.getInstance();

			
			em.insererUtilisateur(user);
			HttpSession session = request.getSession();
			session.setAttribute("userConnecte", user);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
			rd.forward(request, response);
			
			
		} catch (BusinessException e) {
			request.setAttribute("pseudo",pseudo);
			request.setAttribute("nom",nom);
			request.setAttribute("prenom",prenom);
			request.setAttribute("email",email);
			request.setAttribute("telephone",telephone);
			request.setAttribute("rue",rue);
			request.setAttribute("cp",codePostal);
			request.setAttribute("ville",ville);
		
			request.setAttribute("listeErreur", e.getListeMessage());
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/createAccountPage.jsp");
			rd.forward(request, response);
		}
	}
}
