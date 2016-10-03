package jms.socket.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WaitServlet
 */
@WebServlet("/WaitServlet")
public class WaitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public WaitServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("WaitServlet");
		long start = System.currentTimeMillis();

		String time = request.getParameter("time");
		if (time != null && time.length() > 0) {
			try {
				long millis = Long.parseLong(time);
				
				System.out.println("Waiting for " + millis);
				Thread.sleep(millis);
				System.out.println("End of waiting");

				response.getOutputStream().println("Total waiting time: " + (System.currentTimeMillis() - start) + " ms");
			} catch (InterruptedException interruptedException) {
				interruptedException.printStackTrace();
				response.getOutputStream().println("Error on thread sleep method: " + interruptedException.getMessage());
			} catch (NumberFormatException numberFormatException) {
				numberFormatException.printStackTrace();
				response.getOutputStream().println("The 'waitTime' parameter is not a number: " + numberFormatException.getMessage());
			}
		}
		response.getOutputStream().println("\n");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
