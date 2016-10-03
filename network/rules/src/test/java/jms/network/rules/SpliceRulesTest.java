/**
 * 
 */
package jms.network.rules;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import jms.network.domain.Cable;
import jms.network.domain.Splice;
import jms.network.domain.RootEntity;

import org.drools.runtime.rule.ConsequenceException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Ma
 *
 */
public class SpliceRulesTest {

	public static EntityManagerFactory entityManagerFactory;
	public static EntityManager entityManager;
	private static TypedQuery<Cable> cableQuery;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("network");
		entityManager = entityManagerFactory.createEntityManager();
		cableQuery = entityManager.createNamedQuery("findCableByName", Cable.class);

		SpliceRulesTest.persistCables();
		SpliceRulesTest.persistJoins();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		entityManager.close();
		entityManagerFactory.close();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		entityManager.getTransaction().begin();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		entityManager.getTransaction().commit();
	}

	public static Cable createCable(String code, String name, int type) {
		Cable cable1 = new Cable();
		cable1.setCode(code);
		cable1.setName(name);
		cable1.setType(type); // TODO use enum
		return cable1;
	}

	public static Splice createSplice(String code, String type, String zone) {
		Splice splice = new Splice();
		splice.setCode(code);
		splice.setType(type);
		splice.setZone(zone);
		
		return splice;
	}

	public static Splice createSplice(String code, String type, String zone, Cable cable) {
		Splice splice = createSplice(code, type, zone);
		cable.addSplice(splice);
		
		return splice;
	}

	public static Splice createSplice(String code, String type, String zone, String cableName) {
		
		Cable cable = cableQuery.setParameter("name", cableName).getSingleResult();
		
		return createSplice(code, type, zone, cable);
	}

	public static void persistCables() throws Exception {
		
		Cable cable1 = createCable("AA", "Cable AA", 1);
		Cable cable2 = createCable("AB", "Cable AB", 1);
		Cable cable3 = createCable("AF", "Cable AF", 2);
		Cable cable4 = createCable("AG", "Cable AG", 2);
		Cable cable5 = createCable("AH", "Cable AH", 2);
		
		entityManager.getTransaction().begin();
		entityManager.persist(cable1);
		entityManager.persist(cable2);
		entityManager.persist(cable3);
		entityManager.persist(cable4);
		entityManager.persist(cable5);
		entityManager.getTransaction().commit();
	}

	public static void persistJoins() throws Exception {
		entityManager.getTransaction().begin();

		Splice join1 = createSplice("E1", "MFP", "Z1", "Cable AA");
		Splice join2 = createSplice("E2", "MFP", "Z1", "Cable AB");
		Splice join3 = createSplice("E3", "MFS", "Z2", "Cable AF");
		Splice join4 = createSplice("E4", "MFS", "Z2", "Cable AF");
		Splice join5 = createSplice("E5", "MFS", "Z3", "Cable AH");
		
		entityManager.persist(join1);
		entityManager.persist(join2);
		entityManager.persist(join3);
		entityManager.persist(join4);
		entityManager.persist(join5);
		entityManager.getTransaction().commit();
	}

	public List<RuleMessage> callRules(Splice splice, String ruleName) throws Exception {
		List<RuleMessage> ruleMessages = new ArrayList<RuleMessage>();
		
		RuleExecuter ruleExecuter = RuleExecuterFactory.getInstance().createRuleExecuter(ruleName);
		ruleExecuter.setGlobal("messages", ruleMessages);
		ruleExecuter.setGlobal("entityManager", entityManager);
		ruleExecuter.execute(splice);
		
		return ruleMessages;
	}

	public List<RuleMessage> callRules(Cable cable, Splice splice, String ruleName) throws Exception {
		List<RootEntity> entities = new ArrayList<RootEntity>();
		List<RuleMessage> ruleMessages = new ArrayList<RuleMessage>();

		entities.add(cable);
		entities.add(splice);
		
		RuleExecuter ruleExecuter = RuleExecuterFactory.getInstance().createRuleExecuter(ruleName);
		
		ruleExecuter.setGlobal("messages", ruleMessages);
		ruleExecuter.setGlobal("entityManager", entityManager);
		ruleExecuter.execute(entities);
		return ruleMessages;
	}


	@Test
	public void onlyCode() throws Exception {
		Splice splice = createSplice("E1", null, null);
		List<RuleMessage> ruleMessages = callRules(splice, "SpliceValidation.drl");

		assertEquals("Join with only code is invalid !", 0, ruleMessages.size());
	}

	@Test
	public void typeAndEmptyCode() throws Exception {
		Splice splice = createSplice("", "T1", null);
		List<RuleMessage> ruleMessages = callRules(splice, "SpliceValidation.drl");

		assertEquals("Join with empty code is ok !", 1, ruleMessages.size());
	}

	@Test
	public void typeAndNullCode() throws Exception {
		Splice splice = createSplice(null, "T1", null);
		List<RuleMessage> ruleMessages = callRules(splice, "SpliceValidation.drl");

		assertEquals("Join with null code is ok !", 1, ruleMessages.size());
	}

	@Test
	public void typeMFPAndNullZone() throws Exception {
		Splice splice = createSplice("E111", "MFP", null, "Cable AA");
		List<RuleMessage> ruleMessages = callRules(splice, "SpliceValidation.drl");

		assertEquals("Join with null zone is ok !", 1, ruleMessages.size());
	}

	@Test
	public void typeMFPAndNullCable() throws Exception {
		Splice splice = createSplice("E112", "MFP", "Z1");
		List<RuleMessage> ruleMessages = callRules(splice, "SpliceValidation.drl");
		
		assertEquals("Join with null cable is ok !", 1, ruleMessages.size());
	}

	@Test
	public void typeMFPWithDuplicatedLogicKey() throws Exception {
		List<RootEntity> entities = new ArrayList<RootEntity>();

		Splice splice = createSplice("E1", "MFP", "Z1", "Cable AA");
		entities.add(splice);
		List<RuleMessage> ruleMessages = callRules(splice, "SpliceValidation.drl");
		
		assertEquals("Join logical key was validaded !", 1, ruleMessages.size());
	}

	@Test
	public void typeMFPWithLogicKeyOk() throws Exception {
		List<RootEntity> entities = new ArrayList<RootEntity>();

		Splice splice = createSplice("E2", "MFP", "Z1", "Cable AA");
		entities.add(splice);
		List<RuleMessage> ruleMessages = callRules(splice, "SpliceValidation.drl");

		assertEquals("Join logical key was not validaded !", 0, ruleMessages.size());
	}

	@Test
	public void typeMFPWithLogicKeyOk2() throws Exception {
		List<RootEntity> entities = new ArrayList<RootEntity>();

		Splice splice = createSplice("E3", "MFP", "Z1");
		Cable cable = cableQuery.setParameter("name", "Cable AA").getSingleResult();
		
		entities.add(cable);
		entities.add(splice);
		List<RuleMessage> ruleMessages = callRules(splice, "SpliceValidation.drl");

		assertEquals("Join logical key was not validaded !", 1, ruleMessages.size());
		assertEquals("Wrong Join logical key rule activation", "Informe a zona e o cabo", ruleMessages.get(0).getMessage());
	}

	@Test
	public void typeMFSAndNullZone() throws Exception {
		Splice splice = createSplice("E222", "MFS", null, "Cable AG");
		List<RuleMessage> ruleMessages = callRules(splice, "SpliceValidation.drl");

		assertEquals("Join with null zone is ok !", 1, ruleMessages.size());
	}

	@Test
	public void typeMFSAndNullCable() throws Exception {
		Splice splice = createSplice("E223", "MFP", "Z1");
		List<RuleMessage> ruleMessages = callRules(splice, "SpliceValidation.drl");
		
		assertEquals("Join with null cable is ok !", 1, ruleMessages.size());
	}

	@Test
	public void typeMFSWithDuplicatedLogicKey() throws Exception {
		List<RootEntity> entities = new ArrayList<RootEntity>();

		Splice splice = createSplice("E3", "MFS", "Z2", "Cable AG");
		entities.add(splice);
		List<RuleMessage> ruleMessages = callRules(splice, "SpliceValidation.drl");
		
		assertEquals("Join logical key was validaded !", 1, ruleMessages.size());
	}

	@Test
	public void typeMFSWithLogicKeyOk() throws Exception {
		List<RootEntity> entities = new ArrayList<RootEntity>();

		Splice splice = createSplice("E3", "MFS", "Z3", "Cable AH");
		entities.add(splice);
		List<RuleMessage> ruleMessages = callRules(splice, "SpliceValidation.drl");
		
		assertEquals("Join logical key was not validaded !", 0, ruleMessages.size());
	}
}
