package fr.eni.encheres.ihm.achatCredit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.encheres.bll.utilisateur.UtilisateurManagerSing;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class AchatCreditServlet
 */
@WebServlet("/AchatCreditServlet")
public class AchatCreditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager utilisateurManager = UtilisateurManagerSing.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AchatCreditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AchatCreditModel model = new AchatCreditModel();

		Integer noUtilisateur = (Integer) ((HttpServletRequest) request).getSession().getAttribute("IdConnecte");
		Utilisateur utilisateur = new Utilisateur();
		try {
			utilisateur = utilisateurManager.afficherUtilisateur(noUtilisateur);
		} catch (BllException e) {
			e.printStackTrace();
		}
		model.setUtilisateur(utilisateur);

		if (request.getParameter("accueilViaAchatCredit") != null) {
			request.getRequestDispatcher("AccueilServlet").forward(request, response);
		}

		if (request.getParameter("acheterCredit") != null) {

			if (request.getParameter("achat10credits") != null) {
				utilisateur.setCredit(utilisateur.getCredit() + 10);
			}
			else if (request.getParameter("achat50credits") != null) {
				utilisateur.setCredit(utilisateur.getCredit() + 50);
			}
			else if (request.getParameter("achat200credits") != null) {
				utilisateur.setCredit(utilisateur.getCredit() + 200);
			}
			
			try {
				utilisateurManager.modifierUtilisateur(utilisateur);
			} catch (BllException e) {
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("MonProfilServlet").forward(request, response);
			
		}

		request.setAttribute("model", model);
		request.getRequestDispatcher("/WEB-INF/AchatCredit.jsp").forward(request, response);
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
