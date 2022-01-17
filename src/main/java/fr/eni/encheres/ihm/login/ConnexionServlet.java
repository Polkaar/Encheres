package fr.eni.encheres.ihm.login;

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
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/ConnexionServlet")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager utilisateurManager = UtilisateurManagerSing.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnexionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextScreen = "/WEB-INF/Connexion.jsp";
		LoginModel model = new LoginModel();

		//LoginModel model = new LoginModel("", "");

		Utilisateur utilisateur = null;

		if (request.getParameter("connexion") != null) {
			String pseudo = request.getParameter("pseudo");
			String motDePasse = request.getParameter("motDePasse");

			System.out.println(pseudo);
			System.out.println(motDePasse);
			
			try {
				utilisateur = utilisateurManager.afficherUtilisateurParPseudo(pseudo);
				System.out.println("Je passe ici UN");
			} catch (BllException e) {
				e.printStackTrace();
			}
			
			System.out.println(utilisateur);

			if (utilisateur != null) {
				System.out.println("Je passe ici DEUX");
				model.setPseudo(pseudo);
				if (motDePasse.equals(utilisateur.getMotDePasse())) {
					System.out.println("Je passe ici TROIS");
					request.getSession().setAttribute("pseudo", pseudo);
					//TODO : Faire que le navigateur enregistre pseudo et motDePasse ?
					if (request.getParameter("seSouvenirDeMoi") != null) {
						System.out.println("Je passe ici QUATRE");
						request.getSession().setAttribute("motDePasse", motDePasse);
						model.setPseudo(request.getSession().getAttribute("pseudo").toString());
						model.setMotDePasse(request.getSession().getAttribute("motDePasse").toString());
					}
					request.getSession().setAttribute("utilisateurConnecte", utilisateur);
					request.getRequestDispatcher("AccueilConnecteServlet").forward(request, response);
				}
			}

		}

		// TODO : Changer motDePasseOublie d'un bouton à un lien hypertexte ?
		if (request.getParameter("motDePasseOublie") != null) {
			// TODO : envoyer un mail avec le mot de passe ?
		}

		if (request.getParameter("creerUnCompte") != null) {
			request.getRequestDispatcher("CreationCompteServlet").forward(request, response);
		}

		request.setAttribute("model", model);
		request.getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
