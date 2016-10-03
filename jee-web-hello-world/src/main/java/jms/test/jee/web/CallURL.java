package jms.test.jee.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet(description = "Call URL servlet", name="CallURL", urlPatterns = { "/CallURL" })
public class CallURL extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String USER_AGENT = "Mozilla/5.0";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CallURL() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String urlParam = request.getParameter("url");
		PrintWriter out = response.getWriter();

		System.out.println("urlParam: " + urlParam);

		if (urlParam != null && urlParam.length() > 0) {
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>Call URL</h1>");
			out.println("<h1>URL: " + urlParam + "</h1>");
			out.println("<h1>Content below:</h1>");
			out.println("<h1>---------------------------------------------------</h1>");
			out.println(this.getContentContent(urlParam));
			out.println("</body>");
			out.println("</html>");
		} else {
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>URL parameter is null or empty</h1>");
			out.println(urlParam);
			out.println("</body>");
			out.println("</html>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected String getContentContent(String url) {
		StringBuffer response = new StringBuffer();
		BufferedReader in = null;
		try {
			URL obj = new URL(url);
		
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");
	
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
	
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return response.toString();
	}
}
