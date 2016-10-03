/**
 * 
 */
package jms.network.domain;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author jsantuci
 *
 */
public class NetworkDomainTest {

	public static EntityManagerFactory entityManagerFactory;
	public static EntityManager entityManager;
	public static TypedQuery<Equipment> EQUIPMENT_BY_CODE_QUERY;
	public static TypedQuery<Cable> CABLE_BY_CODE_QUERY;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("network");
		entityManager = entityManagerFactory.createEntityManager();

		EQUIPMENT_BY_CODE_QUERY = entityManager.createNamedQuery("findEquipmentByCode", Equipment.class);
		CABLE_BY_CODE_QUERY = entityManager.createNamedQuery("findCableByCode", Cable.class);
		
		NetworkDomainTest.persistEquipment();
		NetworkDomainTest.persistCable();
		NetworkDomainTest.persistCounting();
		NetworkDomainTest.persistJoin();
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


	public static void persistEquipment() throws Exception {
		Equipment equipment1 = new Equipment();
		equipment1.setCode("EQ-1");
		equipment1.setName("Equipment 1");
		equipment1.setType(1); // TODO use enum

		Equipment equipment2 = new Equipment();
		equipment2.setCode("EQ-2");
		equipment2.setName("Equipment 2");
		equipment2.setType(1); // TODO use enum
		
		entityManager.getTransaction().begin();
		entityManager.persist(equipment1);
		entityManager.persist(equipment2);
		entityManager.getTransaction().commit();
	}

	public static void persistCable() throws Exception {
		Cable cable = new Cable();
		cable.setCode("CB-1");
		cable.setName("Cable 1");
		cable.setType(1); // TODO use enum
		
		entityManager.getTransaction().begin();
		entityManager.persist(cable);
		entityManager.getTransaction().commit();
	}

	public static void persistCounting() throws Exception {
		
		Counting counting1 = new Counting();
		counting1.setInitialValue(1);
		counting1.setFinalValue(10);
		
		Counting counting2 = new Counting();
		counting2.setInitialValue(11);
		counting2.setFinalValue(20);
		
		entityManager.getTransaction().begin();
		Equipment eq1 = EQUIPMENT_BY_CODE_QUERY.setParameter("code", "EQ-1").getSingleResult();
		Equipment eq2 = EQUIPMENT_BY_CODE_QUERY.setParameter("code", "EQ-2").getSingleResult();
		Cable cbl1 = CABLE_BY_CODE_QUERY.setParameter("code", "CB-1").getSingleResult();
		
		eq1.addCounting(counting1);
		eq2.addCounting(counting2);
		
		cbl1.addCounting(counting1);
		cbl1.addCounting(counting2);

		entityManager.persist(counting1);
		entityManager.persist(counting2);
		entityManager.getTransaction().commit();
	}

	public static void persistJoin() {
		Splice splice = new Splice();
		splice.setCode("EM-1");
		splice.setType("MFP");
		splice.setZone("Zone 1");

		entityManager.getTransaction().begin();
		Cable cbl1 = CABLE_BY_CODE_QUERY.setParameter("code", "CB-1").getSingleResult();
		cbl1.addSplice(splice);
		entityManager.persist(splice);
		entityManager.getTransaction().commit();
	}

	@Test
	public void findAllEquipments() {
		TypedQuery<Equipment> equipmentQuery = entityManager.createNamedQuery("findAllEquipments", Equipment.class);
		List<Equipment> equipments = equipmentQuery.getResultList();
		assertEquals("Find all equipments failed", 2, equipments.size());
	}

	@Test
	public void findAllCables() {
		TypedQuery<Cable> cableQuery = entityManager.createNamedQuery("findAllCables", Cable.class);
		List<Cable> cables = cableQuery.getResultList();
		assertEquals("Find all cables failed", 1, cables.size());
	}

	@Test
	public void findAllCounting() {
		TypedQuery<Counting> countingQuery = entityManager.createNamedQuery("findAllCountings", Counting.class);
		List<Counting> countings = countingQuery.getResultList();
		assertEquals("Find all countings failed", 2, countings.size());
	}

	@Test
	public void findEquipmentByCodeAndCable() {
		Cable cable = CABLE_BY_CODE_QUERY.setParameter("code", "CB-1").getSingleResult();
		TypedQuery<Equipment> equipmentQuery = entityManager.createNamedQuery("findEquipmentByCodeAndCable", Equipment.class);
		equipmentQuery.setParameter("code", "EQ-1");
		equipmentQuery.setParameter("cable", cable);
		List<Equipment> equipments = equipmentQuery.getResultList();
		assertEquals("Equipment not found", 1, equipments.size());
	}

	@Test
	public void findAllJoins() {
		TypedQuery<Splice> joinQuery = entityManager.createNamedQuery("findAllSplices", Splice.class);
		List<Splice> splices = joinQuery.getResultList();
		assertEquals("Find all joins failed", 1, splices.size());
	}
}
