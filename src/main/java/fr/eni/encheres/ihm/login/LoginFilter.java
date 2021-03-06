package fr.eni.encheres.ihm.login;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoginFilter
 */
//TODO : Rajouter au fur et ? mesure les pages dont l'acc?s n?cessite d'?tre connect?.
@WebFilter({"/AccueilConnecteServlet", "/NouvelleVenteServlet", "/ModifMonProfilServlet", "/MonProfilServlet", "/DetailVenteServlet",
	"/ProfilUtilisateur", "/AchatCreditServlet"})
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
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
		Integer idConnecte = (Integer) ((HttpServletRequest)request).getSession().getAttribute("IdConnecte");
		if(idConnecte == null) {
			request.getRequestDispatcher("ConnexionServlet").forward(request, response);
		}
		else {
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
