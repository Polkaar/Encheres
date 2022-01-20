package fr.eni.encheres.ihm.profilutilisateur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.encheres.bll.utilisateur.UtilisateurManagerSing;
import fr.eni.encheres.ihm.monprofil.MonProfilModel;

/**
 * Servlet implementation class ProfilUtilisateurServlet
 */
@WebServlet("/ProfilUtilisateurServlet")
public class ProfilUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager utilisateurManager = UtilisateurManagerSing.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfilUtilisateurServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProfilUtilisateurModel model = new ProfilUtilisateurModel();
		Integer noUtilisateur = null;
		String servlet = "/WEB-INF/ProfilUtilisateur.jsp";

	
			try {
				noUtilisateur = (Integer) ((HttpServletRequest)request).getSession().getAttribute("vendeurId");
				model.setUtilisateur(utilisateurManager.afficherUtilisateur(noUtilisateur));
			} catch (BllException e) {
				e.printStackTrace();
			}
		
		if (request.getParameter("accueil") != null) {
			servlet = "AccueilServlet";

		}
		if (request.getParameter("accueilViaProfilUtilisateur") != null) {
			request.getRequestDispatcher("AccueilServlet").forward(request, response);
		}

		try {
			model.setUtilisateur(utilisateurManager.afficherUtilisateur(noUtilisateur));
		} catch (BllException e) {
			e.printStackTrace();

		}
		

		request.setAttribute("model", model);
		request.getRequestDispatcher(servlet).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
