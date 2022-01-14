package fr.eni.encheres.ihm;

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
		LoginModel model = new LoginModel("", "");
		Utilisateur utilisateur = null;

		if (request.getParameter("connexion") != null) {
			String pseudo = request.getParameter("pseudo");
			String motDePasse = request.getParameter("motDePasse");
//BLABLA test github
			try {
				utilisateur = utilisateurManager.afficherUtilisateurParPseudo(pseudo);
			} catch (BllException e) {
				e.printStackTrace();
			}

			if (utilisateur != null) {
				model.setPseudo(pseudo);
				if (motDePasse.equals(utilisateur.getMotDePasse())) {
					//TODO : Faire que le navigateur enregistre pseudo et motDePasse ?
					if (request.getParameter("seSouvenirDeMoi") != null) {
						request.getSession().setAttribute("motDePasse", motDePasse);
					}
					request.getSession().setAttribute("pseudo", pseudo);
					model.setPseudo(request.getSession().getAttribute("pseudo").toString());
					model.setMotDePasse(request.getSession().getAttribute("motDePasse").toString());
					// TODO : Vérifier le nom de la Servlet
					nextScreen = "ListeEncheresConnecteServlet";
				}
			}

		}

		// TODO : Changer motDePasseOublie d'un bouton à un lien hypertexte ?
		if (request.getParameter("motDePasseOublie") != null) {
			// TODO : envoyer un mail avec le mot de passe ?
		}

		if (request.getParameter("creerUnCompte") != null) {
			// TODO : Vérifier le nom de la Servlet
			nextScreen = "CreerCompteServlet";
		}

		request.setAttribute("model", model);
		request.getRequestDispatcher(nextScreen).forward(request, response);

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
