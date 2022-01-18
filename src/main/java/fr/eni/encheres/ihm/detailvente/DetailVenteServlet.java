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
import fr.eni.encheres.bo.Utilisateur;
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
		Utilisateur newAcheteur = null;
		Integer noUtilisateur = (Integer)((HttpServletRequest)request).getSession().getAttribute("IdConnecte");
		try {
			//TODO: Article = article sur lequel on cliqué
			article = articleManager.afficherArticleVendu(7);
			model.setArticleVendu(article);
		} catch (BllException e) {
			e.printStackTrace();
		}
		try {
			newAcheteur = utilisateurManager.afficherUtilisateur(noUtilisateur);
			model.setNewAcheteur(newAcheteur);
			enchere = enchereManager.selectDerniereEnchere(article.getNoArticle());
			model.setOldEnchere(enchere);
		} catch (BllException e1) {
			e1.printStackTrace();
		}
		if (request.getParameter("encherir") != null) {
			
			if(request.getParameter("enchere").equals("")) {
				model.setMessage("Veuillez proposer une enchere");
			}
			else {
				prixEnchere = Integer.parseInt(request.getParameter("enchere"));
				if(prixEnchere > model.getArticleVendu().getPrixInitial() && prixEnchere > model.getArticleVendu().getPrixVente() && prixEnchere < model.getNewAcheteur().getCredit()) {
					model.getArticleVendu().setPrixVente(prixEnchere);
					try {
						articleManager.modifierArticleVendu(article, prixEnchere);
					} catch (BllException e) {
						e.printStackTrace();
					}
					enchere.setDateEnchere(LocalDate.now());
					enchere.setMontantEnchere(prixEnchere);
					enchere.setUtilisateur(newAcheteur);
					enchere.setArticleVendu(article);
					try {
						enchereManager.ajouterEnchere(enchere);
					} catch (BllException e) {
						e.printStackTrace();
					} catch (DALException e) {
						e.printStackTrace();
					}
					model.setMessage("Enchere validee !");
					newAcheteur.setCredit(newAcheteur.getCredit() - prixEnchere);
					try {
						utilisateurManager.modifierUtilisateur(newAcheteur);
					} catch (BllException e) {
						e.printStackTrace();
					} 
						
					
				}else {
					model.setMessage("Veuillez proposer une enchere superieur au prix initial et a la derniere enchere");
				}
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
