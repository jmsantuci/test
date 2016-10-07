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
import jms.jaas.security.PoCPrincipal;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Authenticate Servlet", name="Authenticate", urlPatterns = { "/Authenticate" })
public class AuthenticateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Authenticator reference
     */
    @EJB(mappedName="PoCAuthenticatorBean/remote")
    private PoCAuthenticator pocAuthenticator;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String domain = request.getParameter("domain");
		String identity = request.getParameter("identity");
		String pass = request.getParameter("pass");
		String sessionId = request.getParameter("sessionId");

		if (domain != null && domain.length() > 0 && identity != null && identity.length() > 0 && pass != null && pass.length() > 0) {
			HttpSession httpSession = request.getSession();

			PoCPrincipal pocPrincipal = new PoCPrincipal();
			pocPrincipal.setDomain(domain);
			pocPrincipal.setIdentity(identity);
			if (sessionId != null && sessionId.length() > 0) {
				pocPrincipal.setSessionId(sessionId);
			} else {
				pocPrincipal.setSessionId();
			}
			pocPrincipal.encode();

			request.login(pocPrincipal.getName(), pass);
			sessionId = pocAuthenticator.login();
			response.getOutputStream().print("Authenticate session id: " + sessionId);
			if (sessionId != null && sessionId.length() > 0) {
				httpSession.setAttribute("sessionId", sessionId);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
