package fr.eni.encheres.ihm.accueil;

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
import fr.eni.encheres.bll.categorie.CategorieManager;
import fr.eni.encheres.bll.categorie.CategorieManagerSing;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("/AccueilServlet")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleVenduManager articleManager = ArticleVenduManagerSing.getInstance();
	private CategorieManager categorieManager = CategorieManagerSing.getInstance();

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

		// TODO : Changer d'un bouton � un lien hypertexte ?
		if (request.getParameter("accueilConnexion") != null) {
			request.getRequestDispatcher("ConnexionServlet").forward(request, response);
		}

		AccueilModel accueilModel = new AccueilModel();

		try {
			accueilModel.lstCategories = categorieManager.afficherTousCategories();
		} catch (BllException e1) {
			e1.printStackTrace();
		}
		// TODO : Remplacer par l'utilisateur connect� !
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNoUtilisateur(1);
		String nomArticle = request.getParameter("nomArticle");

		String noCategorie = request.getParameter("categorie");
		
		if (request.getParameter("rechercher") != null) {

			try {
				accueilModel.setLstArticles(articleManager.afficherArticleVenduNomEtCategorie(nomArticle, noCategorie));
			} catch (BllException e) {
				e.printStackTrace();
			}

		}

		request.setAttribute("accueilModel", accueilModel);
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
