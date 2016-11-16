package jms.poc.jms.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jms.poc.jms.client.TextProducer;

/**
 * Servlet implementation class Producer
 */
@WebServlet(description = "Producer servlet", name="Producer", urlPatterns = { "/Producer" })
public class Producer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Producer() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> parameters = request.getParameterMap();
		TextProducer textProducer = new TextProducer(parameters);
		
		String result = "<p>Message didn't send!</p>";
		if (textProducer.sendMessage()) {
			result = "<p>Message sent!</p>";
		}
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>JMS Producer</h1>");
		out.println(result);
		out.println("<p></p>");
		out.println("<p>Current time: ");
		out.println(SIMPLE_DATE_FORMAT.format(new Date()));
		out.println("</p>");
		out.println("</body>");
		out.println("</html>");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
