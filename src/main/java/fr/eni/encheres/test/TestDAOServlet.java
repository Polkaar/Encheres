package fr.eni.encheres.test;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.articlevendu.ArticleVenduDAO;
import fr.eni.encheres.dal.articlevendu.ArticleVenduDAOFact;
import fr.eni.encheres.dal.categorie.CategorieDAO;
import fr.eni.encheres.dal.categorie.CategorieDAOFact;
import fr.eni.encheres.dal.enchere.EnchereDAO;
import fr.eni.encheres.dal.enchere.EnchereDAOFact;
import fr.eni.encheres.dal.retrait.RetraitDAO;
import fr.eni.encheres.dal.retrait.RetraitDAOFact;
import fr.eni.encheres.dal.utilisateur.UtilisateurDAO;
import fr.eni.encheres.dal.utilisateur.UtilisateurDAOFact;

//Test gihub Sébastien

/**
 * Servlet implementation class TestDAOArticleVendu
 */
@WebServlet("/TestDAOArticleVendu")
public class TestDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleVenduDAO daoArticle = ArticleVenduDAOFact.getArticleVenduDAO();
	private CategorieDAO daoCat = CategorieDAOFact.getCategorieDAO();
	private UtilisateurDAO daoUser = UtilisateurDAOFact.getUtilisateurDAO();
	private RetraitDAO daoRetrait = RetraitDAOFact.getRetraitDAO();
	private EnchereDAO daoEnchere = EnchereDAOFact.getEnchereDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestDAOServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur u2 = new Utilisateur("Sebounet", "Seb", "Polux", "polo.polo@poplo.polo", "010203040506", "rue saint malo", "35000", "rennes city", "pololo", 100, false);
		Categorie c1 = new Categorie("Informatique");
		Categorie c3 = new Categorie("Jardinnage");
		ArticleVendu a2 = new ArticleVendu();
		Retrait r1 = new Retrait("rue saint malo", "35000", "rennes");
		
		
		try {
//			daoUser.update(daoUser.selectById(1));
//			daoUser.insert(u2);
//			daoCat.insert(c3);
			daoUser.insert(u2);
//			a2 = new ArticleVendu("voiture", "gamer", LocalDate.now(), LocalDate.now(),100 ,200, true, daoCat.selectById(1), daoRetrait.selectById(1), daoUser.selectById(1));
//			daoArticle.insert(a2);
//			Enchere e1 = new Enchere(LocalDate.now(), 500, daoArticle.selectById(1), daoUser.selectById(1));
//			daoRetrait.insert(r1);
//			daoCat.insert(c1);
//			daoCat.insert(c2);
//			daoUser.insert(u1);
//			a1 = new ArticleVendu("pc", "gamer", LocalDate.now(), LocalDate.now(),100 ,200, true, daoCat.selectById(1), daoRetrait.selectById(1), daoUser.selectById(1));
//			daoArticle.insert(a1);
//			daoEnchere.insert(e1);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
