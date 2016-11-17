package jms.poc.tac.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = LoggerFactory.getLogger(LogoutServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.debug("Loging out of web application");
		if (request.getUserPrincipal() != null) {
			request.logout();
			LOGGER.debug("Logged out of web applicaiton");
		}

		HttpSession httpSession = request.getSession(false);
		if (httpSession != null) {
			httpSession.invalidate();
			LOGGER.debug("Session of web applicaiton was invalidated");
		}
		response.getOutputStream().print("User logout !");
	}

}
