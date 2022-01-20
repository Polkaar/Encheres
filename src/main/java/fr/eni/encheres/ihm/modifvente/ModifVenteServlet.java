package fr.eni.encheres.ihm.modifvente;

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
import fr.eni.encheres.bll.categorie.CategorieManager;
import fr.eni.encheres.bll.categorie.CategorieManagerSing;
import fr.eni.encheres.bll.retrait.RetraitManager;
import fr.eni.encheres.bll.retrait.RetraitManagerSing;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;

/**
 * Servlet implementation class ModifVenteServlet
 */
@WebServlet("/ModifVenteServlet")
public class ModifVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleVenduManager ArticleVenduManager = ArticleVenduManagerSing.getInstance();
	RetraitManager managerRetrait = RetraitManagerSing.getInstance();
	CategorieManager managerCategorie = CategorieManagerSing.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifVenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModifVenteModel model = new ModifVenteModel();
		String servlet = "/WEB-INF/ModifMonProfil.jsp";

		Integer noArticle = (Integer) ((HttpServletRequest)request).getSession().getAttribute("noArticleDetail");
		try {
			model.setArticleVendu(ArticleVenduManager.afficherArticleVendu(noArticle));
	
		} catch (BllException e) {
			e.printStackTrace();
		}

		if(request.getParameter("accueilViaModifProfil") != null) {
			request.getRequestDispatcher("AccueilServlet").forward(request, response);
		}
		
		if (request.getParameter("modifier") != null) {
			
				ArticleVendu articleVendu = new ArticleVendu();
				articleVendu = model.getArticleVendu();
				articleVendu.setNomArticle(request.getParameter("nomArticle"));
				articleVendu.setDescription(request.getParameter("descriptionArticle"));
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(Integer.parseInt(request.getParameter("categorie")));
				Retrait retrait = new Retrait();
				retrait.setRue(request.getParameter("rue"));
				retrait.setVille(request.getParameter("ville"));
				retrait.setCodePostal(request.getParameter("codePostal"));
				try {
					articleVendu.setCategorie(
							managerCategorie.afficherCategorieById(Integer.parseInt(request.getParameter("categorie"))));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (BllException e1) {
					e1.printStackTrace();
				}
				articleVendu.setPrixInitial(Integer.parseInt(request.getParameter("prixInitial")));
				articleVendu.setDateDebutEncheres(LocalDate.parse(request.getParameter("dateDebutEnchere")));
				articleVendu.setDateFinEncheres(LocalDate.parse(request.getParameter("dateFinEnchere")));
				articleVendu.setRetrait(retrait);
				
				try {
					ArticleVenduManager.modifierArticleVendu(articleVendu);
				} catch (BllException e) {
					e.printStackTrace();
				}
			} 

		if (request.getParameter("supprimer") != null) {
			try {
				ArticleVendu articleVendu = new ArticleVendu();
				articleVendu = model.getArticleVendu();
				ArticleVenduManager.supprimerArticleVendu(articleVendu);
				servlet = "AccueilServlet";
			} catch (BllException e) {
				e.printStackTrace();
			}
		}
		if (request.getParameter("annuler") != null) {
		}

		request.setAttribute("model", model);
		request.getRequestDispatcher(servlet).forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
