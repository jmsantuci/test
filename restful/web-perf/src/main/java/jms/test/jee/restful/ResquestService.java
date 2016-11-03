/**
 * 
 */
package jms.test.jee.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jsantuci
 *
 */
@Path("/request")
@Produces("application/json")
public class ResquestService {

	/**
	 * Logger factory.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(RequestOutput.class);

	/**
	 * Error on waiting request to finish.
	 */
	private static int ERROR_ON_WAITING = 1;

	/**
	 * Default constructor.
	 */
	public ResquestService() {
		super();
	}

	private char randomChar () {
	    int rnd = (int) (Math.random() * 52);
	    char base = (rnd < 26) ? 'A' : 'a';
	    return (char) (base + rnd % 26);

	}

	private long calculateElapsedTime(long initialTime) {
		return System.currentTimeMillis() - initialTime;
	}
	
	@GET
	@Path("/process/{time}/{length}")
	public RequestOutput process(@PathParam("time") long time,@PathParam("length") int length) {

		LOGGER.info("Request output length and time: " + length + ", " + time);

		RequestOutput requestOutput = new RequestOutput();
		requestOutput.setErrorCode(0);
		long initialTime = System.currentTimeMillis();
		StringBuilder data = new StringBuilder(length);

		LOGGER.debug("Generating data ....");
		for (int i = 0; i <= length; i++) {
			data.append(this.randomChar());
		}

		long elapsedTime = this.calculateElapsedTime(initialTime);
		long timeToWait = time - elapsedTime;
		LOGGER.debug("Real execution time(ms): " + elapsedTime);

		if (timeToWait > 0) {
			try {
				LOGGER.debug("Waiting for " + timeToWait + "(ms) to finish request");
				Thread.sleep(timeToWait);
			} catch (InterruptedException interruptedException) {
				LOGGER.error("Error on waiting request to finish", interruptedException);
				requestOutput.setErrorCode(ERROR_ON_WAITING);
			}
		}
		
		elapsedTime = this.calculateElapsedTime(initialTime);
		LOGGER.info("Total execution time(ms): " + elapsedTime);

		requestOutput.setData(data.toString());
		return requestOutput;
	}

}
