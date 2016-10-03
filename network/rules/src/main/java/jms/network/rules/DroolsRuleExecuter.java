/**
 * 
 */
package jms.network.rules;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.drools.KnowledgeBase;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatelessKnowledgeSession;

/**
 * @author jsantuci
 *
 */
public class DroolsRuleExecuter implements RuleExecuter {

	private KnowledgeBase knowledgeBase;
	private Map<String, Object> globals = new HashMap<String, Object>();

	/**
	 * Default constructor.
	 * @param knowledgeBase 
	 */
	public DroolsRuleExecuter(KnowledgeBase knowledgeBase) {
		super();
		this.setKnowledgeBase(knowledgeBase);
	}

	/**
	 * @return the knowledgeBase
	 */
	public KnowledgeBase getKnowledgeBase() {
		return this.knowledgeBase;
	}

	/**
	 * @param knowledgeBase the knowledgeBase to set
	 */
	public void setKnowledgeBase(KnowledgeBase knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}

	public Map<String, Object> getGlobals() {
		return this.globals;
	}

	public void setGlobals(Map<String, Object> globals) {
		this.globals = globals;
	}

	/**
	 * @see jms.network.rules.RuleExecuter#execute(java.lang.Object)
	 */
	@Override
	public Object execute(Object object) {
		StatelessKnowledgeSession knowledgeSession = this.getKnowledgeBase().newStatelessKnowledgeSession();
		KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newConsoleLogger(knowledgeSession); // TODO change to file
		this.setGlobalsToSession(knowledgeSession);
		knowledgeSession.execute(object);
		logger.close();
		return object; // TODO needs to return a value
	}

	/**
	 * @see jms.company.rules.RuleExecuter#execute(List<T>)
	 */
	@Override
	public <T> List<T> execute(List<T> objects) {
		StatelessKnowledgeSession knowledgeSession = this.getKnowledgeBase().newStatelessKnowledgeSession();
		KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newConsoleLogger(knowledgeSession); // TODO change to file
		this.setGlobalsToSession(knowledgeSession);
		knowledgeSession.execute(objects);
		logger.close();
		return objects; // TODO needs to return a value
	}

	private void setGlobalsToSession(StatelessKnowledgeSession knowledgeSession) {
		for (Entry<String, Object> entry : this.getGlobals().entrySet()) {
			knowledgeSession.setGlobal(entry.getKey(), entry.getValue());
		}
		
	}

	@Override
	public void setGlobal(String name, Object value) {
		this.getGlobals().put(name, value);
		
	}

}
