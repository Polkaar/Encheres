package fr.eni.encheres.ihm.monprofil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.encheres.bll.utilisateur.UtilisateurManagerSing;

/**
 * Servlet implementation class MonProfil
 */
@WebServlet("/MonProfilServlet")
public class MonProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager utilisateurManager = UtilisateurManagerSing.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonProfilServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MonProfilModel model = new MonProfilModel();
		String servlet = "/WEB-INF/MonProfil.jsp";
		try {
			model.setUtilisateur(utilisateurManager.afficherUtilisateur(11));
		} catch (BllException e) {
			e.printStackTrace();
		}
		if(request.getParameter("modifier") != null) {
			servlet = "ModifMonProfilServlet";
		}
		
		if (request.getParameter("accueil") != null) {
			servlet = "AccueilServlet";
		}
		request.setAttribute("model", model);
		request.getRequestDispatcher(servlet).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
