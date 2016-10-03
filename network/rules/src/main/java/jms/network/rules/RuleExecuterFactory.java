/**
 * 
 */
package jms.network.rules;

/**
 * @author jsantuci
 *
 */
public abstract class RuleExecuterFactory {

	private static RuleExecuterFactory ruleExecuterFactory;
	
	static {
		RuleExecuterFactory.ruleExecuterFactory = RuleExecuterFactory.createFactory();
	}

	/**
	 * Default contructor
	 */
	protected RuleExecuterFactory() {
		super();
	}

	/**
	 * Get the singleton instance.
	 * @return
	 */
	
	public static RuleExecuterFactory getInstance() {
		return RuleExecuterFactory.ruleExecuterFactory; 
	}

	protected static RuleExecuterFactory createFactory() {
		// TODO Read from property file
		return new DroolsRuleExecuterFactory();
	}

	/**
	 * 
	 * @param ruleName TODO Define a better name
	 * @return
	 * @throws Exception 
	 */
	public abstract RuleExecuter createRuleExecuter(String ruleName) throws Exception;

}
