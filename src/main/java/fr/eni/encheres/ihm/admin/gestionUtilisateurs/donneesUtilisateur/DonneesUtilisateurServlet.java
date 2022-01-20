package fr.eni.encheres.ihm.admin.gestionUtilisateurs.donneesUtilisateur;

import java.io.IOException;
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
 * Servlet implementation class DonneesUtilisateurServlet
 */
@WebServlet("/DonneesUtilisateurServlet")
public class DonneesUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleVenduManager articleManager = ArticleVenduManagerSing.getInstance();
	private UtilisateurManager utilisateurManager = UtilisateurManagerSing.getInstance();
	private DonneesUtilisateurModel donneesModel = new DonneesUtilisateurModel();
	Utilisateur utilisateur = new Utilisateur();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonneesUtilisateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer noUserDetail = (Integer) ((HttpServletRequest) request).getSession().getAttribute("noUserDetail");
		try {
			utilisateur = utilisateurManager.afficherUtilisateur(noUserDetail);
		} catch (BllException e1) {
			e1.printStackTrace();
		}
		donneesModel.setUtilisateur(utilisateur);
		try {
			donneesModel.setNbEncheres(articleManager.afficherNbEncheresAcheteur(utilisateur)) ;
		} catch (BllException e1) {
			e1.printStackTrace();
		}
		try {
			donneesModel.setNbVentes(articleManager.afficherNbVentesVendeur(utilisateur));
		} catch (BllException e1) {
			e1.printStackTrace();
		}
		
		
		//TODO : désactivation et suppression d'un utilisateur. Voir avec Paul pour le delete on cascade.
		
		
		
		if(request.getParameter("listerDonnees") != null) {
			
			if (request.getParameter("userEncheres") != null) {
				try {
					donneesModel.setLstArticles(articleManager.afficherEncheresAcheteur(utilisateur, "", "0"));
				} catch (BllException e) {
					e.printStackTrace();
				}
			}
			
			if (request.getParameter("userEncheresRemportees") != null) {
				try {
					donneesModel.setLstArticles(articleManager.afficherEncheresRemportees(utilisateur, "", "0"));
				} catch (BllException e) {
					e.printStackTrace();
				}
			}
			
			if (request.getParameter("userVentesEnCours") != null) {
				try {
					donneesModel.setLstArticles(articleManager.afficherVentesEnCours(utilisateur, "", "0"));
				} catch (BllException e) {
					e.printStackTrace();
				}
			}
			
			if (request.getParameter("userVentesNonDebutees") != null) {
				try {
					donneesModel.setLstArticles(articleManager.afficherVentesNonDebutees(utilisateur, "", "0"));
				} catch (BllException e) {
					e.printStackTrace();
				}
			}
			
			if (request.getParameter("userVentesTerminees") != null) {
				try {
					donneesModel.setLstArticles(articleManager.afficherVentesTerminees(utilisateur, "", "0"));
				} catch (BllException e) {
					e.printStackTrace();
				}
			}
			
		}
		
//		DetailVenteServlet
//		if (request.getParameter("detailVente") != null) {
//			Integer detailArticle = Integer.parseInt(request.getParameter("detailVente"));
//			for (Integer noArticle : accueilConnecteModel.getLstNoArticle()) {
//				if (noArticle == detailArticle) {
//					request.getSession().setAttribute("noArticleDetail", noArticle);
//					request.getRequestDispatcher("DetailVenteServlet").forward(request, response);
//				}
//			}
//		}

		request.setAttribute("donneesModel", donneesModel);
		request.getRequestDispatcher("WEB-INF/admin-jsp/DonneesUtilisateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
