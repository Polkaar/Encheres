package fr.eni.encheres.test;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.ArticleVenduDAOFact;
import fr.eni.encheres.dal.DALException;

/**
 * Servlet implementation class TestDAOArticleVendu
 */
@WebServlet("/TestDAOArticleVendu")
public class TestDAOArticleVendu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleVenduDAO dao = ArticleVenduDAOFact.getInstance();
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
		ArticleVendu a1 = new ArticleVendu("Pc gaming", "Pc puissant pour le jeu", LocalDate.now(), LocalDate.now(), 1000, false);
		try {
			dao.insert(a1);
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
