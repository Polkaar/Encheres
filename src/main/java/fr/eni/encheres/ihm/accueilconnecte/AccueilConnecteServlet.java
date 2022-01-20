package fr.eni.encheres.ihm.accueilconnecte;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bll.articlevendu.ArticleVenduManager;
import fr.eni.encheres.bll.articlevendu.ArticleVenduManagerSing;
import fr.eni.encheres.bll.categorie.CategorieManager;
import fr.eni.encheres.bll.categorie.CategorieManagerSing;
import fr.eni.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.encheres.bll.utilisateur.UtilisateurManagerSing;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class AccueilConnecteServlet
 */
@WebServlet("/AccueilConnecteServlet")
public class AccueilConnecteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleVenduManager articleManager = ArticleVenduManagerSing.getInstance();
	private CategorieManager categorieManager = CategorieManagerSing.getInstance();
	private UtilisateurManager utilisateurManager = UtilisateurManagerSing.getInstance();
	AccueilConnecteModel accueilConnecteModel = new AccueilConnecteModel();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilConnecteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String jsp = "WEB-INF/AccueilConnecte.jsp";

		Integer noUtilisateur = (Integer) ((HttpServletRequest) request).getSession().getAttribute("IdConnecte");
		Utilisateur utilisateur = new Utilisateur();
		try {
			utilisateur = utilisateurManager.afficherUtilisateur(noUtilisateur);
		} catch (BllException e2) {
			e2.printStackTrace();
		}

		try {
			accueilConnecteModel.lstCategories = categorieManager.afficherTousCategories();
		} catch (BllException e1) {
			e1.printStackTrace();
		}

		String lienProfil = new String();
		
		if (utilisateur.isAdministrateur()) {
			System.out.println("Je suis bien admin !");
			lienProfil = "MonProfilAdminServlet";
		} else {
			System.out.println("Euh... Je ne suis pas admin ???");
			lienProfil = "MonProfilServlet";
		}

		if (request.getParameter("deconnexion") != null) {
			request.getSession().setAttribute("IdConnecte", null);
			jsp = "AccueilServlet";
		}

		String nomArticle = request.getParameter("nomArticleConnecte");

		String noCategorie = request.getParameter("categorieConnecte");

		if (request.getParameter("rechercherConnecte") != null) {

			if (request.getParameter("achats") != null) {

				if (request.getParameter("encheresOuvertes") != null) {
					try {
						accueilConnecteModel
								.addLstListesArticles(articleManager.afficherEncheresOuvertes(nomArticle, noCategorie));
					} catch (BllException e) {
						e.printStackTrace();
					}
				}

				if (request.getParameter("mesEncheres") != null) {
					try {
						accueilConnecteModel.addLstListesArticles(
								articleManager.afficherEncheresAcheteur(utilisateur, nomArticle, noCategorie));
					} catch (BllException e) {
						e.printStackTrace();
					}
				}

				if (request.getParameter("mesEncheresRemportees") != null) {
					try {
						accueilConnecteModel.addLstListesArticles(
								articleManager.afficherEncheresRemportees(utilisateur, nomArticle, noCategorie));
					} catch (BllException e) {
						e.printStackTrace();
					}
				}

			} else if (request.getParameter("mesVentes") != null) {

				if (request.getParameter("mesVentesEnCours") != null) {
					try {
						accueilConnecteModel.addLstListesArticles(
								articleManager.afficherVentesEnCours(utilisateur, nomArticle, noCategorie));
					} catch (BllException e) {
						e.printStackTrace();
					}
				}

				if (request.getParameter("ventesNonDebutees") != null) {
					try {
						accueilConnecteModel.addLstListesArticles(
								articleManager.afficherVentesNonDebutees(utilisateur, nomArticle, noCategorie));
					} catch (BllException e) {
						e.printStackTrace();
					}
				}

				if (request.getParameter("ventesTerminees") != null) {
					try {
						accueilConnecteModel.addLstListesArticles(
								articleManager.afficherVentesTerminees(utilisateur, nomArticle, noCategorie));
					} catch (BllException e) {
						e.printStackTrace();
					}
				}

			} else {
				try {
					accueilConnecteModel.addLstListesArticles(
							articleManager.afficherArticleVenduNomEtCategorie(nomArticle, noCategorie));
				} catch (BllException e) {
					e.printStackTrace();
				}
			}

			for (List<ArticleVendu> lstArticles : accueilConnecteModel.getLstListesArticles()) {
				for (ArticleVendu articleVendu : lstArticles) {
					accueilConnecteModel.addLstNoArticle(articleVendu.getNoArticle());
				}
			}

		}

		if (request.getParameter("profilVendeur") != null) {
			Integer vendeurId = Integer.parseInt(request.getParameter("profilVendeur"));
			request.getSession().setAttribute("vendeurId", vendeurId);
			request.getRequestDispatcher("ProfilUtilisateurServlet").forward(request, response);
		}

		if (request.getParameter("detailVente") != null) {
			Integer detailArticle = Integer.parseInt(request.getParameter("detailVente"));
			for (Integer noArticle : accueilConnecteModel.getLstNoArticle()) {
				if (noArticle == detailArticle) {
					request.getSession().setAttribute("noArticleDetail", noArticle);
					request.getRequestDispatcher("DetailVenteServlet").forward(request, response);
				}
			}
		}

		
		request.setAttribute("lienProfil", lienProfil);
		request.setAttribute("accueilConnecteModel", accueilConnecteModel);
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
