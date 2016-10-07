package jms.jaas.client.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jms.jaas.Hello;
import jms.jaas.security.PoCAuthenticator;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Print Servlet", name="Print", urlPatterns = { "/Print" })
public class PrintServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Authenticator reference
     */
    @EJB(mappedName="PoCAuthenticatorBean/remote")
    private PoCAuthenticator pocAuthenticator;

    /**
     * Hello reference
     */
    @EJB(mappedName="HelloBean/remote")
    private Hello hello;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sessionId = (String) request.getSession().getAttribute("sessionId");
		if (sessionId != null) {
			PrintWriter printWriter = response.getWriter();
			printWriter.print("Info: " + this.pocAuthenticator.getInfo());
			printWriter.print("Hello: " + this.hello.sayHello());
			printWriter.print("Hello: " + this.hello.sayHelloByPrincipalValues());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
