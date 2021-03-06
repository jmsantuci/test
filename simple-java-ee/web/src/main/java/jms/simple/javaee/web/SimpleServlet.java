package jms.simple.javaee.web;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jms.simple.javaee.java.SimpleHello;

/**
 * 
 */
@WebServlet(name="SimpleServlet", value="/SimpleServlet")
public class SimpleServlet extends HttpServlet {

    /**
     * Serial version.
     */
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

    	response.getWriter().print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
    	response.getWriter().print("<html>");
    	response.getWriter().print("<head>");
		response.getWriter().print("<title>Sample Web Page</title>");
		response.getWriter().print("<META http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
		response.getWriter().print("</head>");
		response.getWriter().print("<body bgcolor=\"#ffffff\" text=\"#000000\">");
		response.getWriter().print("<h1>" + SimpleHello.hello() + "</h1>");
		response.getWriter().print("</body>");
		response.getWriter().print("</html>");

    }
}
