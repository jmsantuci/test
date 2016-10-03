/**
 * 
 */
package jms.cluster.balancer.client;

/**
 * @author jsantuci
 *
 */
public class Node {

	String name;
	boolean stickySessionOk;
	String cookieName;
	String cookieValue;
	String sessionId;

	/**
	 * Default constructor.
	 */
	public Node() {
		super();
		this.setStickySessionOk(false);
	}

	public Node(String lastNodeFound) {
		super();
		this.setName(lastNodeFound);
		this.setStickySessionOk(false);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the stickySessionOk
	 */
	public boolean isStickySessionOk() {
		return this.stickySessionOk;
	}

	/**
	 * @param stickySessionOk the stickySessionOk to set
	 */
	public void setStickySessionOk(boolean stickySessionOk) {
		this.stickySessionOk = stickySessionOk;
	}

	/**
	 * @return the cookieName
	 */
	public String getCookieName() {
		return cookieName;
	}

	/**
	 * @param cookieName the cookieName to set
	 */
	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

	/**
	 * @return the cookieValue
	 */
	public String getCookieValue() {
		return this.cookieValue;
	}

	/**
	 * @param cookieValue the cookieValue to set
	 */
	public void setCookieValue(String cookieValue) {
		this.cookieValue = cookieValue;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return this.sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [name=" + name + ", stickySessionOk=" + stickySessionOk
				+ ", cookieName=" + cookieName + ", cookieValue=" + cookieValue
				+ ", sessionId=" + sessionId + "]";
	}

}
