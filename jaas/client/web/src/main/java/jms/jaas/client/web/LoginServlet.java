package jms.jaas.client.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jms.jaas.security.PoCAuthenticator;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Login Servlet", name="Login", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Authenticator reference
     */
    @EJB(mappedName="PoCAuthenticatorBean/remote")
    private PoCAuthenticator pocAuthenticator;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession httpSession = request.getSession();
		String sessionId = pocAuthenticator.login();
		response.getOutputStream().print("Login session id: " + sessionId);
		if (sessionId != null && sessionId.length() > 0) {
			httpSession.setAttribute("sessionId", sessionId);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
