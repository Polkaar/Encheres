package fr.eni.encheres.ihm.detailvente;

import java.io.IOException;
import java.time.LocalDate;

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
import fr.eni.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.encheres.bll.utilisateur.UtilisateurManagerSing;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;

/**
 * Servlet implementation class DetailVenteServlet
 */
@WebServlet("/DetailVenteServlet")
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleVenduManager articleManager = ArticleVenduManagerSing.getInstance();
	UtilisateurManager utilisateurManager = UtilisateurManagerSing.getInstance();
	EnchereManager enchereManager = EnchereManagerSing.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailVenteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DetailVenteModel model = new DetailVenteModel();
		String servlet = "/WEB-INF/DetailVente.jsp";
		Enchere enchere = new Enchere();
		ArticleVendu article = new ArticleVendu();
		Integer prixEnchere = null;

		try {
			article = articleManager.afficherArticleVendu(6);
			model.setArticleVendu(article);
		} catch (BllException e) {
			e.printStackTrace();
		} catch (DALException e) {
			e.printStackTrace();
		}
		System.out.println(model.getArticleVendu().getRetrait().getCodePostal());
		System.out.println(model.getArticleVendu().getRetrait().getRue());
		System.out.println(model.getArticleVendu().getRetrait().getVille());
		if (request.getParameter("encherir") != null) {
			
			if(request.getParameter("enchere").equals("")) {
				model.setMessage("Veuillez proposer une enchere");
			}
			
			else if(Integer.parseInt(request.getParameter("enchere")) > model.getArticleVendu().getPrixInitial() && Integer.parseInt(request.getParameter("enchere")) > model.getArticleVendu().getPrixVente()) {
				prixEnchere = Integer.parseInt(request.getParameter("enchere"));
				model.getArticleVendu().setPrixVente(prixEnchere);
				try {
					articleManager.modifierArticleVendu(article, prixEnchere);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (BllException e1) {
					e1.printStackTrace();
				} catch (DALException e1) {
					e1.printStackTrace();
				}
					enchere.setDateEnchere(LocalDate.now());
					enchere.setMontantEnchere(Integer.parseInt(request.getParameter("enchere")));
					try {
						enchere.setUtilisateur(utilisateurManager.afficherUtilisateur(3));
					} catch (BllException e) {
						e.printStackTrace();
					}
					enchere.setArticleVendu(article);
					try {
						enchereManager.ajouterEnchere(enchere);
					} catch (BllException e) {
						e.printStackTrace();
					} catch (DALException e) {
						e.printStackTrace();
					}	
					model.setMessage("Enchere validee !");
			}else {
				model.setMessage("Veuillez proposer une enchere superieur au prix initial et a la derniere enchere");
			}
		}
		
		request.setAttribute("model", model);
		request.getRequestDispatcher(servlet).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
