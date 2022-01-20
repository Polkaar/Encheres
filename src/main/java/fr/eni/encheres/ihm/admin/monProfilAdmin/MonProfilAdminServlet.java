package fr.eni.encheres.ihm.admin.monProfilAdmin;

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
 * Servlet implementation class MonProfilAdmin
 */
@WebServlet("/MonProfilAdminServlet")
public class MonProfilAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager utilisateurManager = UtilisateurManagerSing.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonProfilAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MonProfilAdminModel model = new MonProfilAdminModel();
		String servlet = "/WEB-INF/admin-jsp/MonProfilAdmin.jsp";

		Integer noUtilisateur = (Integer) ((HttpServletRequest) request).getSession().getAttribute("IdConnecte");
		try {
			model.setUtilisateur(utilisateurManager.afficherUtilisateur(noUtilisateur));
		} catch (BllException e) {
			e.printStackTrace();
		}

		if (request.getParameter("accueilViaMonProfil") != null) {
			request.getRequestDispatcher("AccueilServlet").forward(request, response);
		}

		if (request.getParameter("modifier") != null) {
			servlet = "ModifMonProfilServlet";
		}
		
		if(request.getParameter("achatDeCredit") != null) {
			servlet = "AchatCreditServlet";
		}

		request.setAttribute("model", model);
		request.getRequestDispatcher(servlet).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
