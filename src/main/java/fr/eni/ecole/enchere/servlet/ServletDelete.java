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
 * Servlet implementation class ServletDelete
 */
@WebServlet("/ServletDelete")
public class ServletDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

			String idStr = request.getParameter("id");
			System.out.println("test servlet delete " + idStr);
			int id = Integer.parseInt(idStr);
			UserManager mgr = UserManager.getInstance();
			try {
				HttpSession session = request.getSession();
				mgr.supprimerUtilisateur(id);
				session.setAttribute("userConnecte", null);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
			rd.forward(request, response);
		}

}
