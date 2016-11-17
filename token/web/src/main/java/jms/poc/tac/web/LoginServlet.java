package jms.poc.tac.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jms.poc.tac.security.AccessController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
	
	@EJB
	private AccessController accessController;

	/**
     * Default constructor. 
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("user");
		String password = request.getParameter("password");
		LOGGER.debug("Login to web application. User: " + userName);
		if (userName != null && userName.length() > 0 && password != null && password.length() > 0) {
			request.login(userName, password);
			request.getSession(true);
			if (accessController.isUserBlocked(userName)) {
				request.getSession().invalidate();
				request.logout();
				response.getOutputStream().println("User blocked.");
				LOGGER.debug("User blocked");
			} else {
				request.getSession().setAttribute("user", userName);
				response.getOutputStream().println("User ok");
				LOGGER.debug("User logged to web application");
			}
		}
		response.getOutputStream().println("\n");

	}

}
