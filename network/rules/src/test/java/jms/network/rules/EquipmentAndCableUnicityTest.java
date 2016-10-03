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
import jms.network.domain.Counting;
import jms.network.domain.Equipment;
import jms.network.domain.RootEntity;

import org.drools.runtime.rule.ConsequenceException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author jsantuci
 *
 */
public class EquipmentAndCableUnicityTest {

	public static EntityManagerFactory entityManagerFactory;
	public static EntityManager entityManager;
	private static TypedQuery<Equipment> equipmentQuery;
	private static TypedQuery<Cable> cableQuery;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("network");
		entityManager = entityManagerFactory.createEntityManager();
		equipmentQuery = entityManager.createNamedQuery("findEquipmentByName", Equipment.class);
		cableQuery = entityManager.createNamedQuery("findCableByName", Cable.class);

		EquipmentAndCableUnicityTest.persistEquipment();
		EquipmentAndCableUnicityTest.persistCables();
		EquipmentAndCableUnicityTest.persistCountings();
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

	public static Equipment createEquipment(String code, String name, int type) {
		Equipment equipment1 = new Equipment();
		equipment1.setCode(code);
		equipment1.setName(name);
		equipment1.setType(type); // TODO use enum
		return equipment1;
	}

	public static Cable createCable(String code, String name, int type) {
		Cable cable1 = new Cable();
		cable1.setCode(code);
		cable1.setName(name);
		cable1.setType(type); // TODO use enum
		return cable1;
	}

	public static Counting createCounting(int initialValue, int finalValue, Equipment equipment, Cable cable) {
		Counting counting = new Counting();
		counting.setInitialValue(initialValue);
		counting.setFinalValue(finalValue);
		
		equipment.addCounting(counting);
		cable.addCounting(counting);
		
		return counting;
	}

	public static Counting createCounting(int initialValue, int finalValue,
			String equipmentName, String cableName) {
		
		Equipment equipment = equipmentQuery.setParameter("name", equipmentName).getSingleResult();
		Cable cable = cableQuery.setParameter("name", cableName).getSingleResult();
		
		return createCounting(initialValue, finalValue, equipment, cable);
	}

	public static void persistEquipment() throws Exception {
		
		Equipment cx1 = createEquipment("Cx-1", "Caixa 1 - Cable 1 & 2", 1);
		Equipment cx2 = createEquipment("Cx-2", "Caixa 2 - Cable 1", 1);
		Equipment cx3 = createEquipment("Cx-2", "Caixa 2 - Cable 2", 1);
		Equipment cx4 = createEquipment("Cx-3", "Caixa 3 - Cable 2", 1);
		Equipment eq1 = createEquipment("EQ-1", "Equipment 1 - Cable 1", 2);
		Equipment eq2 = createEquipment("EQ-2", "Equipment 2 - Cable 2", 2);
		
		entityManager.getTransaction().begin();
		entityManager.persist(cx1);
		entityManager.persist(cx2);
		entityManager.persist(cx3);
		entityManager.persist(cx4);
		entityManager.persist(eq1);
		entityManager.persist(eq2);
		entityManager.getTransaction().commit();
	}

	public static void persistCables() throws Exception {
		
		Cable cable1 = createCable("CB-1", "Cable 1", 1);
		Cable cable2 = createCable("CB-2", "Cable 2", 1);
		
		entityManager.getTransaction().begin();
		entityManager.persist(cable1);
		entityManager.persist(cable2);
		entityManager.getTransaction().commit();
	}

	public static void persistCountings() throws Exception {
		entityManager.getTransaction().begin();

		Counting counting1 = createCounting(1, 10, "Caixa 1 - Cable 1 & 2", "Cable 1");
		Counting counting2 = createCounting(11, 20, "Caixa 2 - Cable 1", "Cable 1");
		Counting counting3 = createCounting(1, 10, "Caixa 1 - Cable 1 & 2", "Cable 2");
		Counting counting4 = createCounting(11, 20, "Caixa 2 - Cable 2", "Cable 2");
		Counting counting5 = createCounting(21, 30, "Caixa 3 - Cable 2", "Cable 2");
		
		entityManager.persist(counting1);
		entityManager.persist(counting2);
		entityManager.persist(counting3);
		entityManager.persist(counting4);
		entityManager.persist(counting5);
		entityManager.getTransaction().commit();
	}

	public void callRules(Cable cable, Equipment equipment, String ruleName) throws Exception {
		List<RootEntity> entities = new ArrayList<RootEntity>();
		entities.add(cable);
		entities.add(equipment);
		
		RuleExecuter ruleExecuter = RuleExecuterFactory.getInstance().createRuleExecuter(ruleName);
		ruleExecuter.setGlobal("entityManager", entityManager);
		ruleExecuter.execute(entities);
	}

	@Test
	public void createUnicityViolation() throws Exception {
		Equipment equipment = createEquipment("Cx-1", "Caixa 1 - Cable 1 & 2", 1);
		Cable cable = cableQuery.setParameter("name", "Cable 1").getSingleResult();
		try {
			callRules(cable, equipment, "EquipmentCableUnicity.drl");
		} catch (ConsequenceException consequenceException) {
			assertTrue("Wrong Exception", consequenceException.getCause().getMessage().startsWith("Equipment"));
		}
	}

	@Test
	public void updateUnicityViolation() throws Exception {
		Equipment equipment = equipmentQuery.setParameter("name", "Caixa 3 - Cable 2").getSingleResult();
		equipment.setCode("Cx-1");
		Cable cable = cableQuery.setParameter("name", "Cable 1").getSingleResult();
		try {
			callRules(cable, equipment, "EquipmentCableUnicity.drl");
		} catch (ConsequenceException consequenceException) {
			assertTrue("Wrong Exception", consequenceException.getCause().getMessage().startsWith("Equipment"));
		}
	}

	@Test
	public void createOk() throws Exception {
		Equipment equipment = createEquipment("Cx-3", "Caixa 3 - Cable 1", 1);
		Cable cable = cableQuery.setParameter("name", "Cable 1").getSingleResult();
		
		callRules(cable, equipment, "EquipmentCableUnicity.drl");

		Counting counting = createCounting(21, 30, equipment, cable);
		entityManager.persist(equipment);
		entityManager.persist(counting);
		
	}

	@Test
	public void createUnicityViolationJPA() throws Exception {
		Equipment equipment = createEquipment("Cx-1", "Caixa 1 - Cable 1 & 2", 1);
		Cable cable = cableQuery.setParameter("name", "Cable 1").getSingleResult();
		try {
			callRules(cable, equipment, "EquipmentCableUnicityUsingJPA.drl");
		} catch (ConsequenceException consequenceException) {
			assertTrue("Wrong Exception", consequenceException.getCause().getMessage().startsWith("Equipment"));
		}
	}

	@Test
	public void updateUnicityViolationJPA() throws Exception {
		Equipment equipment = equipmentQuery.setParameter("name", "Caixa 3 - Cable 2").getSingleResult();
		equipment.setCode("Cx-1");
		Cable cable = cableQuery.setParameter("name", "Cable 1").getSingleResult();
		try {
			callRules(cable, equipment, "EquipmentCableUnicityUsingJPA.drl");
		} catch (ConsequenceException consequenceException) {
			assertTrue("Wrong Exception", consequenceException.getCause().getMessage().startsWith("Equipment"));
		}
	}

	@Test
	public void createOkJPA() throws Exception {
		Equipment equipment = createEquipment("Cx-4", "Caixa 4 - Cable 1", 1);
		Cable cable = cableQuery.setParameter("name", "Cable 1").getSingleResult();
		
		callRules(cable, equipment, "EquipmentCableUnicityUsingJPA.drl");

		Counting counting = createCounting(31, 40, equipment, cable);
		entityManager.persist(equipment);
		entityManager.persist(counting);
		
	}
}
