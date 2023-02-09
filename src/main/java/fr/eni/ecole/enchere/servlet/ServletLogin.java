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
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		UserManager em = UserManager.getInstance();
		Utilisateur userConnecte;

		try {

			if (!request.getParameter("identifiant").isEmpty() && !request.getParameter("motDePasse").isEmpty()) {

				String pseudo = request.getParameter("identifiant");
				String mdp = request.getParameter("motDePasse");

					userConnecte = em.connecterUtilisateur(pseudo, mdp); 
					HttpSession session = request.getSession();
					session.setAttribute("userConnecte", userConnecte);

					RequestDispatcher rd = request.getRequestDispatcher("/ServletAccueil");
					rd.forward(request, response);

			} else {
				BusinessException be = new BusinessException();
				be.addMessage("Servlet : veuillez remplir les 2 champs");
				be.printStackTrace();
				request.setAttribute("listeErreur", be.getListeMessage());

				doGet(request, response);

			}
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeErreur", e.getListeMessage());
			doGet(request, response);
		}
	}
}
