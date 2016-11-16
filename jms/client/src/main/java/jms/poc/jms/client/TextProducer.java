/**
 * 
 */
package jms.poc.jms.client;

import java.util.Map;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author jsantuci
 *
 */
public class TextProducer {

	/**
	 * Default value for message.
	 */
	private static final String MESSAGE = "Hello, World!";

	/**
	 * Default value for connection factory.
	 */
	private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";

	/**
	 * Default value for destination name.
	 */
	private static final String DESTINATION = "queue/TextQueue";

	private static final String CONNECTION_FACTORY_PARAM_NAME = "cf";
	private static final String DESTINATION_PARAM_NAME = "d";
	private static final String MESSAGE_PARAM_NAME = "m";
	private static final String USER_PARAM_NAME = "u";
	private static final String PASSWORD_PARAM_NAME = "p";
	
    private String connectioFactoryName;
    private String destinationName;
    private String message;
    private String user;
    private String password;

	/**
	 * Default constructor.
	 */
	public TextProducer() {
		super();
		this.setConnectioFactoryName(CONNECTION_FACTORY);
		this.setDestinationName(DESTINATION);
		this.setMessage(MESSAGE);
	}

	/**
	 * Creates an instance using program arguments.
	 * 
	 * @param programArgs program arguments
	 */
	public TextProducer(String[] programArgs) {
		this();
		for (int i = 0; i < programArgs.length; i++) {
			String argument = programArgs[i];
			switch (argument) {
			case "-cf":
				argument = this.getArgumentValue(programArgs, ++i);
				this.setConnectioFactoryName(argument);
				break;
			case "-d":
				argument = this.getArgumentValue(programArgs, ++i);
				this.setDestinationName(argument);
				break;
			case "-m":
				argument = this.getArgumentValue(programArgs, ++i);
				this.setMessage(argument);
				break;
			case "-u":
				argument = this.getArgumentValue(programArgs, ++i);
				this.setUser(argument);
				break;
			case "-p":
				argument = this.getArgumentValue(programArgs, ++i);
				this.setPassword(argument);
				break;
			default:
				System.out.println("Unkown program argument");
			}
		}
	}

	/**
	 * Get the argument value. The rule is to get the next position of program arguments.
	 * 
	 * @param programArgs program arguments
	 * @param valueIndex array index of program argument value.
	 * @return argument value
	 */
	protected String getArgumentValue(String[] programArgs, int valueIndex) {
		String result = null;
		if (valueIndex < programArgs.length) {
			result = programArgs[valueIndex];
		}
		return result;
	}

	/**
	 * Get the first parameter of array if it is different of null and has length
	 * greather than 0.
	 * 
	 * @param parameters list of parameter values
	 * @return the first parameter value otherwise null
	 */
	protected String getParameterValue(String[] parameters) {
		String result = null;
		if (parameters != null && parameters.length > 0) {
			result = parameters[0];
		}
		return result;
	}
	
	/**
	 * Constructor using all arguments.
	 * 
	 * @param connectioFactoryName
	 * @param destinationName
	 * @param user
	 * @param password
	 * @param message
	 */
	public TextProducer(String connectioFactoryName,
			String destinationName, String user, String password, String message) {
		super();
		this.connectioFactoryName = connectioFactoryName;
		this.destinationName = destinationName;
		this.message = message;
		this.user = user;
		this.password = password;

	}

	public TextProducer(Map<String, String[]> parameters) {
		this.setConnectioFactoryName(this.getParameterValue(parameters.get(CONNECTION_FACTORY_PARAM_NAME)));
		this.setDestinationName(this.getParameterValue(parameters.get(DESTINATION_PARAM_NAME)));
		this.setMessage(this.getParameterValue(parameters.get(MESSAGE_PARAM_NAME)));
		this.setUser(this.getParameterValue(parameters.get(USER_PARAM_NAME)));
		this.setPassword(this.getParameterValue(parameters.get(PASSWORD_PARAM_NAME)));
	}
	
	/**
	 * @return the connectioFactoryName
	 */
	public String getConnectioFactoryName() {
		return this.connectioFactoryName;
	}

	/**
	 * @param connectioFactoryName the connectioFactoryName to set
	 */
	public void setConnectioFactoryName(String connectioFactoryName) {
		this.connectioFactoryName = connectioFactoryName;
	}

	/**
	 * @return the destinationName
	 */
	public String getDestinationName() {
		return this.destinationName;
	}

	/**
	 * @param destinationName the destinationName to set
	 */
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return this.user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean sendMessage() {
		boolean result = false;
		Context namingContext = null;
        JMSContext context = null;
 
        try {
 
            // Set up the namingContext for the JNDI lookup
            namingContext = new InitialContext();
 
            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(this.getConnectioFactoryName());
            Destination destination = (Destination) namingContext.lookup(this.getDestinationName());
 
            // Create the JMS context
            if (this.existUser()) {
            	context = connectionFactory.createContext(this.getUser(), this.getPassword());
            } else {
            	context = connectionFactory.createContext();
            }
 
            context.createProducer().send(destination, this.getMessage());
            result = true;
 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (namingContext != null) {
                try {
					namingContext.close();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
             // closing the context takes care of consumer too
            if (context != null) {
                context.close();
            }
        }
        return result;
	}

	/**
	 * Checks if user is a valid name.
	 * 
	 * @return true if user is valid otherwise false.
	 */
	public boolean existUser() {
		boolean existUser = this.getUser() != null && this.getUser().length() > 0;
		return existUser;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TextProducer textProducer = new TextProducer(args);
		if (textProducer.sendMessage()) {
			System.out.println("Message sent !!!");
		} else {
			System.out.println("Message didn't send !!!");
		}
	}

}
