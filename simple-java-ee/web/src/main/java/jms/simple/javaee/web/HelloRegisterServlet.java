package jms.simple.javaee.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jms.simple.javaee.domain.Hello;
import jms.simple.javaee.services.HelloServiceLocal;

/**
 * 
 */
@WebServlet(name="HelloRegisterServlet", value="/HelloRegisterServlet")
public class HelloRegisterServlet extends HttpServlet {

    /**
     * Serial version.
     */
    private static final long serialVersionUID = 1L;

    @EJB
    private HelloServiceLocal helloService;

    /**
	 * @return the helloService
	 */
	public HelloServiceLocal getHelloService() {
		return this.helloService;
	}

	/**
	 * @param helloService the helloService to set
	 */
	public void setHelloService(HelloServiceLocal helloService) {
		this.helloService = helloService;
	}

	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

		Hello hello = null;
		String message = null;
    	String op = request.getParameter("op");
    	if (op.equals("find")) {
    		String code = request.getParameter("code");
    		hello = this.getHelloService().findMessage(Long.parseLong(code));
    		if (hello != null) {
    			message = "<h1>Code: " + code + "</h1>" + "<h1>Message: " + hello.getMessage() + "</h1>" ;
    		} else {
    			message = "<h1>Message not found</h1>";
    		}
    	} else if (op.equals("create")) {
    		hello = new Hello();
    		hello.setMessage(request.getParameter("message"));
    		this.getHelloService().createMessage(hello);
    		message = "<h1>Message created</h1>";
    	}
    	response.getWriter().print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
    	response.getWriter().print("<html>");
    	response.getWriter().print("<head>");
		response.getWriter().print("<title>Sample Web Page</title>");
		response.getWriter().print("<META http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
		response.getWriter().print("</head>");
		response.getWriter().print("<body bgcolor=\"#ffffff\" text=\"#000000\">");
		response.getWriter().print("<h1>Operation: " + op  + "</h1>");
		response.getWriter().print(message);
		response.getWriter().print("</body>");
		response.getWriter().print("</html>");

    }
}
