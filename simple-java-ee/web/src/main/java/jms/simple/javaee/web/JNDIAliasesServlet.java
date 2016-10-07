package jms.simple.javaee.web;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jms.simple.javaee.java.SimpleHello;
import jms.test.jndi.Alias;
import jms.test.jndi.ShowTree;

/**
 * 
 */
@WebServlet(name="JNDIAliasesServlet", value="/JNDIAliasesServlet")
public class JNDIAliasesServlet extends HttpServlet {

    /**
     * Serial version.
     */
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

    	String result = "";
    	String op = request.getParameter("op");
    	if (op.equals("create")) {
    		String target = request.getParameter("target");
    		String link = request.getParameter("link");
    		try {
				InitialContext initialContext = new InitialContext();
				Alias alias = new Alias(initialContext, link, target);
				alias.createAlias();
				result = "Alias created";
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	} else if (op.equals("show")) {
    		try {
				InitialContext initialContext = new InitialContext();
				ShowTree showTree = new ShowTree(initialContext);
				showTree.printJNDITree("");
				result = "Tree showed";
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	} else if (op.equals("lookup")) {
    		String target = request.getParameter("target");
    		try {
				InitialContext initialContext = new InitialContext();
				Object object = initialContext.lookup(target);
				if (object != null) {
					result = "Object found";
				}
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}

    	response.getWriter().print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
    	response.getWriter().print("<html>");
    	response.getWriter().print("<head>");
		response.getWriter().print("<title>Sample Web Page</title>");
		response.getWriter().print("<META http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
		response.getWriter().print("</head>");
		response.getWriter().print("<body bgcolor=\"#ffffff\" text=\"#000000\">");
		response.getWriter().print("<h1> op = " + op + "</h1>");
		response.getWriter().print("<h1>---------------------------------------------------------------------------</h1>");
		response.getWriter().print("<h1>" + result + "</h1>");
		response.getWriter().print("</body>");
		response.getWriter().print("</html>");

    }
}
