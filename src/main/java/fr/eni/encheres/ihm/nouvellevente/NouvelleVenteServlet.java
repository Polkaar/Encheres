package fr.eni.encheres.ihm.nouvellevente;

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
import fr.eni.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.encheres.bll.utilisateur.UtilisateurManagerSing;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;

/**
 * Servlet implementation class NouvelleVenteServlet
 */
@WebServlet("/NouvelleVenteServlet")
public class NouvelleVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager managerUtilisateur = UtilisateurManagerSing.getInstance();
	ArticleVenduManager managerArticleVendu = ArticleVenduManagerSing.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouvelleVenteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NouvelleVenteModel model = new NouvelleVenteModel();
		Utilisateur utilisateur = new Utilisateur();
		try {
			utilisateur = managerUtilisateur.afficherUtilisateur(1);
		} catch (BllException e1) {
			e1.printStackTrace();
		}
		model.setUtilisateur(utilisateur);
		
		if (request.getParameter("enregistrer") != null) {
			
			Categorie categorie = new Categorie();
			categorie.setLibelle(request.getParameter("categorie"));
			
			Retrait retrait = new Retrait();
			if(request.getParameter("rue") == null) {
				retrait.setRue(utilisateur.getRue());
			}
			if(request.getParameter("ville") == null) {
				retrait.setRue(utilisateur.getVille());
			}
			if(request.getParameter("codePostal") == null) {
				retrait.setRue(utilisateur.getCodePostal());
			}
			else{
				retrait.setRue(request.getParameter("rue"));
				retrait.setVille(request.getParameter("ville"));
				retrait.setCodePostal(request.getParameter("codePostal"));
			}
			ArticleVendu articleVendu = new ArticleVendu();
			articleVendu.setNomArticle(request.getParameter("nomArticle"));
			articleVendu.setDescription(request.getParameter("descriptionArticle"));
			articleVendu.setCategorie(categorie);
			articleVendu.setPrixInitial(Integer.parseInt(request.getParameter("prixInitial")));
			articleVendu.setDateDebutEncheres(LocalDate.parse(request.getParameter("dateDebutEnchere")));
			articleVendu.setDateFinEncheres(LocalDate.parse(request.getParameter("dateFinEnchere")));
			articleVendu.setRetrait(retrait);
			articleVendu.setUtilisateur(utilisateur);
			articleVendu.setEtatVente(true);
			
			try {
				managerArticleVendu.ajouterArticleVendu(articleVendu);
			} catch (DALException e) {
				e.printStackTrace();
			} catch (BllException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("model", model);
		request.getRequestDispatcher("WEB-INF/NouvelleVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
