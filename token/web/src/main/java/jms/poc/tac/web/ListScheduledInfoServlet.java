package jms.poc.tac.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jms.poc.cluster.domain.SchedulerInfo;
import jms.poc.cluster.scheduler.ScheduledInfoBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class ListScheduledInfoServlet
 */
@WebServlet("/ListScheduledInfoServlet")
public class ListScheduledInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = LoggerFactory.getLogger(ListScheduledInfoServlet.class);
	
	/**
	 * EJB with scheduler info
	 */
	@EJB
	private ScheduledInfoBean scheduledInfoBean;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListScheduledInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.debug("Listing schedulers");
		if (request.getUserPrincipal() != null) {
			response.getOutputStream().println("Generating schedulerInfoList");
			for (SchedulerInfo schedulerInfo : this.scheduledInfoBean.getScheduledInfoList()) {
				response.getOutputStream().println(schedulerInfo.toString());
			}
			response.getOutputStream().println("SchedulerInfoList generated");
		} else {
			response.getOutputStream().println("User not authenticated");
		}
	}

}
