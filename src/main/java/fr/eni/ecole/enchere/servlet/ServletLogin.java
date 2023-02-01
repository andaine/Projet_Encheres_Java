package fr.eni.ecole.enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.enchere.bll.EnchereManager;
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

		System.out.println("dopost - servlet login");

		EnchereManager em = new EnchereManager();
		Utilisateur userConnecte;

		try {

			if (!request.getParameter("identifiant").isEmpty() && !request.getParameter("motDePasse").isEmpty()) {

				String pseudo = request.getParameter("identifiant");
				String mdp = request.getParameter("motDePasse");

					userConnecte = em.validerUtilisateur(pseudo, mdp);
					HttpSession session = request.getSession();
					session.setAttribute("userConnecte", userConnecte);

					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/userConnecte.jsp");
					rd.forward(request, response);

			} else {
				BusinessException be = new BusinessException();
				be.addMessage("Servlet : veuillez remplir les 2 champs");
				be.printStackTrace();
				request.setAttribute("listeErreur", be.getListeMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp");
				rd.forward(request, response);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeErreur", e.getListeMessage());
			doGet(request, response);
		}
	}
}
