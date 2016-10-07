package jms.jaas.client.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jms.jaas.client.core.BaseClient;
import jms.jaas.security.Constants;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "WebClient Servlet", name="WebClient", urlPatterns = { "/WebClientServlet", "/WebClient" })
public class WebClientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebClientServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String domain = request.getParameter("domain");
		String identity = request.getParameter("identity");
		String pass = request.getParameter("pass");

		if (domain != null && domain.length() > 0 && identity != null && identity.length() > 0 && pass != null && pass.length() > 0) {
			BaseClient client = new BaseClient(domain, identity, Constants.CREDENTIAL_KEY, pass);
			if (client.login()) {
				// Print information about authentication
				client.printInfo();

				// Call hello bean
				client.callHelloBean();

				// Logout from application
				client.logout();

				// Failure: user is not authenticated
				client.printInfo();

				// Ok: HelloBean does not have security associated
				client.callHelloBean();
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
