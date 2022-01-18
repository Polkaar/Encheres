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
			} catch (BllException e) {
				e.printStackTrace();
			}
			
			System.out.println(utilisateur);

			if (utilisateur != null) {
				model.setPseudo(pseudo);
				if (motDePasse.equals(utilisateur.getMotDePasse())) {
					request.getSession().setAttribute("pseudo", pseudo);
					if (request.getParameter("seSouvenirDeMoi") != null) {
						request.getSession().setAttribute("motDePasse", motDePasse);
						
						//Gérer ça dans un if à part, pour que le mot de passe et le pseudo s'affichent quand on revient sur la page ?
						model.setPseudo(request.getSession().getAttribute("pseudo").toString());
						model.setMotDePasse(request.getSession().getAttribute("motDePasse").toString());
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
