package jms.poc.cluster.domain;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Session Bean implementation class UserBean
 */
@Singleton
@LocalBean
public class UserBean {

	private Map<String, User> users = new HashMap<String, User>();

	/**
     * Default constructor. 
     */
    public UserBean() {
        super();
    }

	/**
	 * @return the users
	 */
	private Map<String, User> getUsers() {
		return this.users;
	}

//	@PermitAll
	public User getUser(String name) {
		return this.getUsers().get(name);
	}

	@PostConstruct
	private void addUsers() {
		this.getUsers().put("jms", new User("jms", "123", false));
		this.getUsers().put("blk", new User("blk", "321", true));
	}
}
