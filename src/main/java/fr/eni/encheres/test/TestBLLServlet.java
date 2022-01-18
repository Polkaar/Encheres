package fr.eni.encheres.test;

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
import fr.eni.encheres.bll.categorie.CategorieManager;
import fr.eni.encheres.bll.categorie.CategorieManagerSing;
import fr.eni.encheres.bll.enchere.EnchereManager;
import fr.eni.encheres.bll.enchere.EnchereManagerSing;
import fr.eni.encheres.bll.retrait.RetraitManager;
import fr.eni.encheres.bll.retrait.RetraitManagerSing;
import fr.eni.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.encheres.bll.utilisateur.UtilisateurManagerSing;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;

/**
 * Servlet implementation class TestBLLServlet
 */
@WebServlet("/TestBLLServlet")
public class TestBLLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategorieManager categorieManager = CategorieManagerSing.getInstance();
	private EnchereManager enchereManager = EnchereManagerSing.getInstance();
	private RetraitManager retraitManager = RetraitManagerSing.getInstance();
	private UtilisateurManager utilisateurManager = UtilisateurManagerSing.getInstance();
	private ArticleVenduManager articleManager = ArticleVenduManagerSing.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestBLLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur u1 = new Utilisateur("Seb", "Polo", "Polux", "polo.polo@poplo.polo", "010203040506", "rue saint malo", "35000", "rennes",
				"pololo", 100, false);
		Categorie c1 = new Categorie("Informatique");
		Categorie c2 = new Categorie("Ameublement");
		Categorie c3 = new Categorie("Vêtement");
		Categorie c4 = new Categorie("Sport&Loisirs");
		ArticleVendu a1 = new ArticleVendu();
		Retrait r1 = new Retrait("rue saint malo", "35000", "rennes");
		
		Categorie c5 = new Categorie("TestConnexionBDD");
		
		
		List<ArticleVendu> lstArticles = new ArrayList<ArticleVendu>();
		
		try {
			lstArticles = articleManager.afficherArticleVenduNomEtCategorie("pc", "1");
		} catch (BllException e) {
			e.printStackTrace();
		}
		System.out.println("Ici !");
		for (ArticleVendu articleVendu : lstArticles) {
			System.out.println("Là !");
			System.out.println(articleVendu);
		}
		
		
//		try {
//			categorieManager.ajouterCategorie(c5);
//		} catch (BllException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		//Test BLL Retrait
		
//		try {
//			retraitManager.ajouterRetrait(r1);
//		} catch (BllException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			System.out.println(retraitManager.afficherRetrait(a1));
//		} catch (BllException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		retraitManager.supprimerRetrait(r1);
		
		
		//Test BLL Utilisateur
		
//		try {
//			u1 = utilisateurManager.ajouterUtilisateur(u1);
//		} catch (BllException | DALException e) {
//			e.printStackTrace();
//		}
//		try {
//			System.out.println(utilisateurManager.afficherUtilisateur(1));
//		} catch (BllException e) {
//			e.printStackTrace();
//		}
//		u1.setPseudo("Sebounet");
//		try {
//			utilisateurManager.modifierUtilisateur(u1);
//		} catch (BllException | DALException e) {
//			e.printStackTrace();
//		}
//		try {
//			System.out.println(utilisateurManager.afficherTousUtilisateurs());
//		} catch (BllException e) {
//			e.printStackTrace();
//		}
//		try {
//			utilisateurManager.supprimerUtilisateur(u1);
//		} catch (BllException e) {
//			e.printStackTrace();
//		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
