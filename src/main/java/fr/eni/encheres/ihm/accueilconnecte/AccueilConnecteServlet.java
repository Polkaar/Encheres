package fr.eni.encheres.ihm.accueilconnecte;

import java.io.IOException;

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

		AccueilConnecteModel accueilConnecteModel = new AccueilConnecteModel();
		
		Integer noUtilisateur = (Integer)((HttpServletRequest)request).getSession().getAttribute("IdConnecte");

		try {
			Utilisateur utilisateur = utilisateurManager.afficherUtilisateur(noUtilisateur);
		} catch (BllException e2) {
			e2.printStackTrace();
		}

		try {
			accueilConnecteModel.lstCategories = categorieManager.afficherTousCategories();
		} catch (BllException e1) {
			e1.printStackTrace();
		}

		// TODO : Changer de boutons à des liens hypertextes ?
		if (request.getParameter("encheres") != null) {
			request.getRequestDispatcher(jsp).forward(request, response);
		}
		if (request.getParameter("vente") != null) {
			request.getRequestDispatcher("NouvelleVenteServlet").forward(request, response);
		}
		if (request.getParameter("monProfil") != null) {
			jsp = "MonProfilServlet";
		}
		if (request.getParameter("deconnexion") != null) {
			request.getSession().setAttribute("IdConnecte", null);
			jsp = "AccueilServlet";
		}

		// TODO : Remplacer par l'utilisateur connecté !
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNoUtilisateur(1);
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
		}

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
