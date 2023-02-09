package fr.eni.ecole.enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.enchere.bll.UserManager;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

/**
 * Servlet implementation class ServletMonProfil
 */
@WebServlet("/ServletMonProfil")
public class ServletMonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		int idProfil =  Integer.parseInt(request.getParameter("id")) ;
		UserManager user = UserManager.getInstance();
		Utilisateur infoVendeur;
		
		try {
			infoVendeur = user.afficherUtilisateur(idProfil);

			request.setAttribute("infoVendeur", infoVendeur);
			request.setAttribute("idVendeur", idProfil);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/monProfil.jsp");
		rd.forward(request, response);
	}

}
