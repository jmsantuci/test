package jms.poc.tac.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jms.poc.cluster.scheduler.SchedulerBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class SchedulerServlet
 */
@WebServlet("/SchedulerServlet")
public class SchedulerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = LoggerFactory.getLogger(SchedulerServlet.class);
	
	@EJB
	private SchedulerBean schedulerBean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SchedulerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.debug("Scheduling a task by web application");

		if (request.getUserPrincipal() != null) {
			HttpSession httpSession = request.getSession(false);
			String user = (String) httpSession.getAttribute("user");
			String timer = request.getParameter("timer");
	
			if (timer != null && timer.length() > 0 ) {
				schedulerBean.schedule(user, timer);
	
				response.getOutputStream().println("Scheduler ok");
			}
			response.getOutputStream().println("\n");
		} else {
			response.getOutputStream().println("User not authenticated");
		}
	}

}
