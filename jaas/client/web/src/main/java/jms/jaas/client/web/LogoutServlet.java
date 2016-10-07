package jms.jaas.client.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jms.jaas.security.PoCAuthenticator;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Logout Servlet", name="Logout", urlPatterns = { "/Logout" })
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Authenticator reference
     */
    @EJB(mappedName="PoCAuthenticatorBean/remote")
    private PoCAuthenticator pocAuthenticator;

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

		String sessionId = (String) request.getSession().getAttribute("sessionId");
		if (sessionId != null) {
			pocAuthenticator.logout();
			request.logout();
			request.getSession().invalidate();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
