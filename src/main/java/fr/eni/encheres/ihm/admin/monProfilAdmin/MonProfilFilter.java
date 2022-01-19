package fr.eni.encheres.ihm.admin.monProfilAdmin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.encheres.bll.utilisateur.UtilisateurManagerSing;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet Filter implementation class MonProfilFilter
 */
@WebFilter({ "/MonProfilServlet" })
public class MonProfilFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public MonProfilFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		UtilisateurManager manager = UtilisateurManagerSing.getInstance();

		Integer idConnecte = (Integer) ((HttpServletRequest) request).getSession().getAttribute("IdConnecte");
		Utilisateur utilisateur = new Utilisateur();
		try {
			utilisateur = manager.afficherUtilisateur(idConnecte);
		} catch (BllException e) {
			e.printStackTrace();
		}
		Boolean isAdmin = utilisateur.isAdministrateur();

		if (isAdmin) {
			request.getRequestDispatcher("MonProfilAdminServlet").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
