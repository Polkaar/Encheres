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
import fr.eni.encheres.bll.categorie.CategorieManager;
import fr.eni.encheres.bll.categorie.CategorieManagerSing;
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
 * Servlet implementation class NouvelleVenteServlet
 */
@WebServlet("/NouvelleVenteServlet")
public class NouvelleVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager managerUtilisateur = UtilisateurManagerSing.getInstance();
	ArticleVenduManager managerArticleVendu = ArticleVenduManagerSing.getInstance();
	RetraitManager managerRetrait = RetraitManagerSing.getInstance();
	CategorieManager managerCategorie = CategorieManagerSing.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouvelleVenteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp ="WEB-INF/NouvelleVente.jsp";
		NouvelleVenteModel model = new NouvelleVenteModel();
		Utilisateur utilisateur = new Utilisateur();
		
		try {
			utilisateur = managerUtilisateur.afficherUtilisateur(11);
		} catch (BllException e1) {
			e1.printStackTrace();
		}
		model.setUtilisateur(utilisateur);
		
		if (request.getParameter("enregistrer") != null) {
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(Integer.parseInt(request.getParameter("categorie")));
			Retrait retrait = new Retrait();
			retrait.setRue(request.getParameter("rue"));
			retrait.setVille(request.getParameter("ville"));
			retrait.setCodePostal(request.getParameter("codePostal"));
			if(request.getParameter("rue").isBlank() && request.getParameter("ville").isBlank() && request.getParameter("codePostal").isBlank() ) {
				try {
					System.out.println("je passe là");
					retrait.setRue(utilisateur.getRue());
					retrait.setVille(utilisateur.getVille());
					retrait.setCodePostal(utilisateur.getCodePostal());
					managerRetrait.ajouterRetrait(retrait);
				} catch (BllException e) {
					e.printStackTrace();
				}
			}else{
				
				try {
					System.out.println("je passe ici");
					managerRetrait.ajouterRetrait(retrait);
				} catch (BllException e) {
					e.printStackTrace();
				}
			}
			ArticleVendu articleVendu = new ArticleVendu();
			articleVendu.setNomArticle(request.getParameter("nomArticle"));
			articleVendu.setDescription(request.getParameter("descriptionArticle"));
			try {
				articleVendu.setCategorie(managerCategorie.afficherCategorieById(Integer.parseInt(request.getParameter("categorie"))));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (BllException e1) {
				e1.printStackTrace();
			}
			articleVendu.setPrixInitial(Integer.parseInt(request.getParameter("prixInitial")));
			articleVendu.setDateDebutEncheres(LocalDate.parse(request.getParameter("dateDebutEnchere")));
			articleVendu.setDateFinEncheres(LocalDate.parse(request.getParameter("dateFinEnchere")));
			articleVendu.setRetrait(retrait);
			articleVendu.setUtilisateur(utilisateur);
			articleVendu.setEtatVente(true);
			
			try {
				managerArticleVendu.ajouterArticleVendu(articleVendu);
			} 
			catch (BllException e) {
				e.printStackTrace();
			}
		}

		if (request.getParameter("annuler") != null) {	
		}
		
		if (request.getParameter("accueil") != null) {
			jsp = "AccueilServlet";
		}

		request.setAttribute("model", model);
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
