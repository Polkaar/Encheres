package fr.eni.encheres.ihm.accueil;

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
 * Servlet Filter implementation class AccueilFilter
 */
@WebFilter({"/", "/AccueilServlet"})
public class AccueilFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AccueilFilter() {
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
			request.getRequestDispatcher("AccueilServlet").forward(request, response);
		}
		else {
			request.getRequestDispatcher("AccueilConnecteServlet").forward(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
