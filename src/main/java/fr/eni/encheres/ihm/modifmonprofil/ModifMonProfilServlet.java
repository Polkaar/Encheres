package fr.eni.encheres.ihm.modifmonprofil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.encheres.bll.utilisateur.UtilisateurManagerSing;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ModifMonProfrilServlet
 */
@WebServlet("/ModifMonProfilServlet")
public class ModifMonProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager utilisateurManager = UtilisateurManagerSing.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifMonProfilServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModifMonProfilModel model = new ModifMonProfilModel();
		String servlet = "/WEB-INF/ModifMonProfil.jsp";
		
		Integer noUtilisateur = (Integer)((HttpServletRequest)request).getSession().getAttribute("IdConnecte");
		try {
			model.setUtilisateur(utilisateurManager.afficherUtilisateur(noUtilisateur));
		} catch (BllException e) {
			e.printStackTrace();
		}
		
		if (request.getParameter("enregistrer") != null) {
			if(request.getParameter("nouveauMotDePasse").equals(request.getParameter("confirmationMotDePasse"))) {
				Utilisateur utilisateur = new Utilisateur();
			utilisateur = model.getUtilisateur();
			utilisateur.setPseudo(request.getParameter("pseudo"));
			utilisateur.setNom(request.getParameter("nom"));
			utilisateur.setPrenom(request.getParameter("prenom"));
			utilisateur.setEmail(request.getParameter("email"));
			utilisateur.setTelephone(request.getParameter("telephone"));
			utilisateur.setRue(request.getParameter("rue"));
			utilisateur.setVille(request.getParameter("ville"));
			utilisateur.setCodePostal(request.getParameter("codePostal"));
			utilisateur.setCredit(model.getUtilisateur().getCredit());
			utilisateur.setMotDePasse(request.getParameter("nouveauMotDePasse"));
			try {
				utilisateurManager.modifierUtilisateur(utilisateur);
			} catch (BllException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Mauvais mdp");
			model.setMessage("Le mot de passe et la confirmation sont differents !");
		}
	}
		
	if (request.getParameter("supprimer") != null) {
		try {
			utilisateurManager.supprimerUtilisateur(model.getUtilisateur());
			servlet = "AccueilServlet";
		} catch (BllException e) {
			e.printStackTrace();
		}
	}

	if (request.getParameter("accueil") != null) {
		servlet = "AccueilServlet";
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
