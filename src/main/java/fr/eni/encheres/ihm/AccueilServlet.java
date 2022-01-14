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

import fr.eni.encheres.ihm.creationcompte.CreationCompteModel;

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

		//TODO : Changer d'un bouton � un lien hypertexte ?
		if(request.getParameter("accueilConnexion") != null) {
			request.getRequestDispatcher("ConnexionServlet").forward(request, response);
		}
		
		ArticleModel articleModel = new ArticleModel();

		//TODO : Factoriser la m�thode dans la BLL ? Deux autres pages utilisent des listes d'ench�res.
		if (request.getParameter("rechercher") != null) {
			String nomArticle = request.getParameter("nomArticle");
			Integer noCategorie;

			//TODO : Utiliser plut�t une request SQL pour le tri ?
			if("toutes".equals(request.getParameter("categorie"))) {
				try {
					for (ArticleVendu article : articleManager.afficherTousArticleVendu()) {
						if (article.getNomArticle().contains(nomArticle)) {
							articleModel.addLstArticles(article);
						}
					}
				} catch (BllException | DALException e) {
					e.printStackTrace();
				}
			}else {
				noCategorie = Integer.parseInt(request.getParameter("categorie"));
				try {
					for (ArticleVendu article : articleManager.afficherArticleVenduCategorie(noCategorie)) {
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
