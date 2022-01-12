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
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.articlevendu.ArticleVenduDAO;
import fr.eni.encheres.dal.articlevendu.ArticleVenduDAOFact;
import fr.eni.encheres.dal.categorie.CategorieDAO;
import fr.eni.encheres.dal.categorie.CategorieDAOFact;
import fr.eni.encheres.dal.retrait.RetraitDAO;
import fr.eni.encheres.dal.retrait.RetraitDAOFact;
import fr.eni.encheres.dal.utilisateur.UtilisateurDAO;
import fr.eni.encheres.dal.utilisateur.UtilisateurDAOFact;

//Test gihub Sébastien

/**
 * Servlet implementation class TestDAOArticleVendu
 */
@WebServlet("/TestDAOArticleVendu")
public class TestDAOArticleVendu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleVenduDAO dao = ArticleVenduDAOFact.getInstance();
	private CategorieDAO daoCat = CategorieDAOFact.getInstance();
	private UtilisateurDAO daoUser = UtilisateurDAOFact.getUtilisateurDAO();
	private RetraitDAO daoRetrait = RetraitDAOFact.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestDAOArticleVendu() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur u1 = new Utilisateur("Paul", "Polo", "Polux", "polo.polo@poplo.polo", "010203040506", "rue saint malo", "35000", "rennes", "pololo", 100, false);
		Categorie c1 = new Categorie("Informatique");
		Categorie c2 = new Categorie("Bricolage");
		ArticleVendu a1 = new ArticleVendu();
		ArticleVendu a2 = new ArticleVendu();
		ArticleVendu a3 = new ArticleVendu();
		try {
			Retrait r1 = new Retrait("rue saint malo", "35000", "rennes");
			daoRetrait.insert(r1);
		} catch (DALException e2) {

			e2.printStackTrace();
		}
		try {
			a1 = new ArticleVendu("pc", "gamer", LocalDate.now(), LocalDate.now(),100 ,200, true, daoCat.selectById(1), daoUser.selectById(1));
			a2 = new ArticleVendu("Marteau", "Pour clouter", LocalDate.now(), LocalDate.now(),100 ,200, true, daoCat.selectById(3), daoUser.selectById(1));
			a3 = new ArticleVendu("Tournevis", "Pour visser", LocalDate.now(), LocalDate.now(),100 ,200, true, daoCat.selectById(3), daoUser.selectById(1));
		} catch (DALException e1) {
		
			e1.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
