/**
 * 
 */
package jms.network.rules;

import java.util.HashMap;
import java.util.Map;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;

/**
 * @author jsantuci
 *
 */
public class DroolsRuleExecuterFactory extends RuleExecuterFactory {

	private static Map<String, KnowledgeBase> knowledgeBaseMap = new HashMap<String, KnowledgeBase>();
	
	/**
	 * Default constructor.
	 */
	public DroolsRuleExecuterFactory() {
		super();
	}

	private static KnowledgeBase readKnowledgeBase(String ruleName) throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource(ruleName), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error); // TODO
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }

	/**
	 * @throws Exception 
	 * @see jms.network.rules.RuleExecuterFactory#createRuleExecuter(String)
	 */
	@Override
	public RuleExecuter createRuleExecuter(String ruleName) throws Exception {
		KnowledgeBase knowledgeBase = knowledgeBaseMap.get(ruleName);
		if (knowledgeBase == null) {
			knowledgeBase = DroolsRuleExecuterFactory.readKnowledgeBase(ruleName);
			knowledgeBaseMap.put(ruleName, knowledgeBase);
		}
		return new DroolsRuleExecuter(knowledgeBase);
	}

}
