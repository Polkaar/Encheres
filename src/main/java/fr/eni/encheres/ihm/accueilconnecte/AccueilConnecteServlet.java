package fr.eni.encheres.ihm.accueilconnecte;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bll.articlevendu.ArticleVenduManager;
import fr.eni.encheres.bll.articlevendu.ArticleVenduManagerSing;
import fr.eni.encheres.bll.enchere.EnchereManager;
import fr.eni.encheres.bll.enchere.EnchereManagerSing;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.ihm.accueil.AccueilModel;

/**
 * Servlet implementation class AccueilConnecteServlet
 */
@WebServlet("/AccueilConnecteServlet")
public class AccueilConnecteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleVenduManager articleManager = ArticleVenduManagerSing.getInstance();

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
		
		String jsp ="WEB-INF/AccueilConnecte.jsp";
		
		// TODO : Changer de boutons à des liens hypertextes ?
		if (request.getParameter("encheres") != null) {
			request.getRequestDispatcher(jsp).forward(request, response);
		}
		if (request.getParameter("vente") != null) {
			request.getRequestDispatcher("NouvelleVenteServlet").forward(request, response);
		}
		if (request.getParameter("monProfil") != null) {
			//TODO : Vérifier le nom de la Servlet
			jsp ="MonProfilServlet";
		}
		if (request.getParameter("deconnexion") != null) {
			request.getSession().setAttribute("pseudo", null);
			jsp ="AccueilServlet";
		}

		AccueilConnecteModel accueilConnecteModel = new AccueilConnecteModel();

		// TODO : Factoriser la méthode dans la BLL ? Deux autres pages utilisent des listes d'enchères.
		if (request.getParameter("rechercher") != null) {
			String nomArticle = request.getParameter("nomArticle");
			Integer noCategorie;

			// TODO : Utiliser plutôt une request SQL pour le tri ?
			if ("toutes".equals(request.getParameter("categorie"))) {
				try {
					for (ArticleVendu article : articleManager.afficherTousArticleVendu()) {
						if (article.getNomArticle().contains(nomArticle)) {
							accueilConnecteModel.addLstArticles(article);
						}
					}
				} catch (BllException | DALException e) {
					e.printStackTrace();
				}
			} else {
				noCategorie = Integer.parseInt(request.getParameter("categorie"));
				try {
					for (ArticleVendu article : articleManager.afficherArticleVenduCategorie(noCategorie)) {
						if (article.getNomArticle().contains(nomArticle)) {
							accueilConnecteModel.addLstArticles(article);
						}
					}
				} catch (BllException | DALException e) {
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
