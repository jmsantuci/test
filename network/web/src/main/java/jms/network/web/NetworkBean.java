/**
 * 
 */
package jms.network.web;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import jms.company.domain.Department;

/**
 * @author jsantuci
 *
 */
@ManagedBean(name = "departmentBean")
@SessionScoped
public class NetworkBean {

	private Department department;
	private DataModel<Department> departments;

	@PersistenceContext
	private EntityManager entityManager;

	/**
     * User transaction.
     */
    @Resource
    private UserTransaction userTransaction;

    /**
     * Non-argument constructor.
     */
    public NetworkBean() {
        super();
    }

    /**
	 * @return the department
	 */
	public Department getDepartment() {
		return this.department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getMessage() {
        return "Hello World!";
    }

	public String prepareCreate() {
		this.setDepartment(new Department());
		return "departmentRegister";
	}

	public void create() {
		try {
			this.userTransaction.begin();
			this.entityManager.persist(this.getDepartment());
			this.userTransaction.commit();
		} catch (Exception exception) {
			// TODO return error
			exception.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public DataModel<Department> getDepartments() {
		try {
			this.userTransaction.begin();
			this.departments = new ListDataModel<Department>(this.entityManager.createNamedQuery("findAllDepartments").getResultList());
			this.userTransaction.commit();
		} catch (Exception exception) {
			// TODO return error
			exception.printStackTrace();
			this.departments = new ListDataModel<Department>();
		}
		return this.departments;
	}

	public String prepareUpdate() {
		this.setDepartment(this.departments.getRowData());
		return "departmentRegister";
	}

	public String update() {
		try {
			this.userTransaction.begin();
			this.entityManager.merge(this.getDepartment());
			this.userTransaction.commit();
		} catch (Exception exception) {
			// TODO return error
			exception.printStackTrace();
		}
		return "departmentRegister";
	}

	public void remove() {
		this.setDepartment(this.departments.getRowData());
		try {
			this.userTransaction.begin();
			this.entityManager.remove(this.entityManager.find(Department.class, this.getDepartment().getCode()));
			this.userTransaction.commit();
		} catch (Exception exception) {
			// TODO return error
			exception.printStackTrace();
		}
		this.setDepartment(null);
	}

}
