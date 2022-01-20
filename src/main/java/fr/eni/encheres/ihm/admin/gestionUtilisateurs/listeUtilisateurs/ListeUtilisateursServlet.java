package fr.eni.encheres.ihm.admin.gestionUtilisateurs.listeUtilisateurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bll.articlevendu.ArticleVenduManager;
import fr.eni.encheres.bll.articlevendu.ArticleVenduManagerSing;
import fr.eni.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.encheres.bll.utilisateur.UtilisateurManagerSing;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ListeUtilisateursServlet
 */
@WebServlet("/ListeUtilisateursServlet")
public class ListeUtilisateursServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager utilisateurManager = UtilisateurManagerSing.getInstance();
	ArticleVenduManager articleManager = ArticleVenduManagerSing.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeUtilisateursServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListeUtilisateursModel model = new ListeUtilisateursModel();

		List<Utilisateur> lstUtilisateurs = new ArrayList<>();
		List<ListeUtilisateursModel> lstModel = new ArrayList<ListeUtilisateursModel>();
		try {
			lstUtilisateurs = utilisateurManager.afficherTousUtilisateurs();
		} catch (BllException e) {
			e.printStackTrace();
		}
		
		for (Utilisateur utilisateur : lstUtilisateurs) {
			Integer nbEncheres = null;
			Integer nbVentes = null;
			try {
				nbEncheres = articleManager.afficherNbEncheresAcheteur(utilisateur);
			} catch (BllException e) {
				e.printStackTrace();
			}
			try {
				nbVentes = articleManager.afficherNbVentesVendeur(utilisateur);
			} catch (BllException e) {
				e.printStackTrace();
			}
			ListeUtilisateursModel modelAjoute = new ListeUtilisateursModel(utilisateur, nbEncheres, nbVentes);
			model.addlstUsersDonnees(modelAjoute);
		}
		
		if(request.getParameter("detailUser") != null) {
			Integer noUserDetail = Integer.parseInt(request.getParameter("detailUser"));
			request.getSession().setAttribute("noUserDetail", noUserDetail);
			request.getRequestDispatcher("DonneesUtilisateurServlet").forward(request, response);
		}
		
		request.setAttribute("model", model);
		request.getRequestDispatcher("/WEB-INF/admin-jsp/ListeUtilisateurs.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
