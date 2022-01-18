package fr.eni.encheres.ihm.creationcompte;

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
import fr.eni.encheres.dal.DALException;

/**
 * Servlet implementation class CreationCompteServlet
 */
@WebServlet("/CreationCompteServlet")
public class CreationCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager manager = UtilisateurManagerSing.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationCompteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CreationCompteModel model = new CreationCompteModel();
		String jsp ="WEB-INF/CreationCompte.jsp";
		
		if (request.getParameter("creer") != null) {
			Utilisateur utilisateur = new Utilisateur(); 
			if(request.getParameter("motDePasse").equals(request.getParameter("confirmationMotDePasse"))) {
				jsp = "AccueilServlet";
				utilisateur.setMotDePasse(request.getParameter("motDePasse"));
				utilisateur.setPseudo(request.getParameter("pseudo"));
				utilisateur.setNom(request.getParameter("nom"));
				utilisateur.setPrenom(request.getParameter("prenom"));
				utilisateur.setEmail(request.getParameter("email"));
				utilisateur.setTelephone(request.getParameter("telephone"));
				utilisateur.setRue(request.getParameter("rue"));
				utilisateur.setVille(request.getParameter("ville"));
				utilisateur.setCodePostal(request.getParameter("codePostal"));
				utilisateur.setCredit(100);
				try {
					manager.ajouterUtilisateur(utilisateur);
				} catch (BllException e) {
					e.printStackTrace();
					model.setMessage(e.getMessage());
					jsp ="WEB-INF/CreationCompte.jsp";
				}
			}
			else {
				System.out.println("Mauvais mdp");
				model.setMessage("Le mot de passe et la confirmation sont differents !");
			}
		}
		if (request.getParameter("annuler") != null) {
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
