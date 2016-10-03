package jms.cluster.balancer.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BalancerTest
 */
public class BalancerTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public BalancerTest() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean createSession = Boolean.parseBoolean(request.getParameter("createSession"));
		boolean invalidateSession = Boolean.parseBoolean(request.getParameter("invalidateSession"));
		if (createSession) {
			HttpSession httpSession = request.getSession();
			response.getOutputStream().println("sessionId: " + httpSession.getId());
		} else if (invalidateSession) {
			request.getSession().invalidate();
			response.getOutputStream().println("Session is invalid");
		}
		String systemProperty = request.getParameter("systemProperty");
		if (systemProperty != null && systemProperty.length() > 0) {
			response.getOutputStream().println(systemProperty + ": " + System.getProperty(systemProperty));
		}
		
		response.getOutputStream().println("\nOk!\n\n");
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.process(request, response);
	}

}
