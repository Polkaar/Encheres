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

		LoginModel loginModel = LoginModel.getInstance();

		Utilisateur utilisateur = null;

		if (request.getParameter("connexion") != null) {
			String pseudo = request.getParameter("pseudo");
			String motDePasse = request.getParameter("motDePasse");

			try {
				utilisateur = utilisateurManager.afficherUtilisateurParPseudo(pseudo);
			} catch (BllException e) {
				e.printStackTrace();
			}

			if (utilisateur != null) {
				if (motDePasse.equals(utilisateur.getMotDePasse())) {
					if (request.getParameter("seSouvenirDeMoi") != null) {
						loginModel.setPseudo(pseudo);
						loginModel.setMotDePasse(motDePasse);
					}
					request.getSession().setAttribute("IdConnecte", utilisateur.getNoUtilisateur());
					request.getRequestDispatcher("AccueilConnecteServlet").forward(request, response);
				}
			}

		}

		// TODO : Changer motDePasseOublie d'un bouton à un lien hypertexte ?
		if (request.getParameter("motDePasseOublie") != null) {
			// TODO : Proposer de se connecter autrement ?
		}

		if (request.getParameter("creerUnCompte") != null) {
			request.getRequestDispatcher("CreationCompteServlet").forward(request, response);
		}

		request.setAttribute("model", loginModel);
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
