package fr.eni.encheres.ihm;

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

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("/AccueilServlet")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnchereManager enchereManager = EnchereManagerSing.getInstance();
	private ArticleVenduManager articleManager = ArticleVenduManagerSing.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//TODO : Changer d'un bouton à un lien hypertexte ?
		if(request.getParameter("connexion") != null) {
			request.getRequestDispatcher("ConnexionServlet").forward(request, response);
		}
		
		ArticleModel articleModel = new ArticleModel();

		//TODO : Factoriser la méthode dans la BLL ? Deux autres pages utilisent des listes d'enchères.
		if (request.getParameter("rechercher") != null) {
			String categorie = request.getParameter("categorie");
			String nomArticle = request.getParameter("nomArticle");

			//TODO : Utiliser plutôt une request SQL pour le tri ?
			switch (categorie) {
			case "toutes":
				try {
					for (ArticleVendu article : articleManager.afficherTousArticleVendu()) {
						if (article.getNomArticle().contains(nomArticle)) {
							articleModel.addLstArticles(article);
						}
					}
				} catch (BllException | DALException e) {
					e.printStackTrace();
				}
			case "informatique":
				try {
					for (ArticleVendu article : articleManager.afficherArticleVenduCategorie(1)) {
						if (article.getNomArticle().contains(nomArticle)) {
							articleModel.addLstArticles(article);
						}
					}
				} catch (BllException | DALException e) {
					e.printStackTrace();
				}
			case "ameublement":
				try {
					for (ArticleVendu article : articleManager.afficherArticleVenduCategorie(2)) {
						if (article.getNomArticle().contains(nomArticle)) {
							articleModel.addLstArticles(article);
						}
					}
				} catch (BllException | DALException e) {
					e.printStackTrace();
				}
			case "vetement":
				try {
					for (ArticleVendu article : articleManager.afficherArticleVenduCategorie(3)) {
						if (article.getNomArticle().contains(nomArticle)) {
							articleModel.addLstArticles(article);
						}
					}
				} catch (BllException | DALException e) {
					e.printStackTrace();
				}
			case "sport&loisir":
				try {
					for (ArticleVendu article : articleManager.afficherArticleVenduCategorie(4)) {
						if (article.getNomArticle().contains(nomArticle)) {
							articleModel.addLstArticles(article);
						}
					}
				} catch (BllException | DALException e) {
					e.printStackTrace();
				}
			}

			for (ArticleVendu article : articleModel.getLstArticles()) {
				try {
					article.setLstEncheres(enchereManager.afficherEnchereArticle(article));
				} catch (BllException e) {
					e.printStackTrace();
				}
			}

		}

		request.setAttribute("articleModel", articleModel);
		request.setAttribute("locale", Locale.ENGLISH);
		request.getRequestDispatcher("WEB-INF/Accueil.jsp").forward(request, response);
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
