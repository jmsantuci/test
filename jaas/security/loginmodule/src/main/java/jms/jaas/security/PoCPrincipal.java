/**
 * 
 */
package jms.jaas.security;

import java.io.Serializable;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author jsantuci
 *
 */
public class PoCPrincipal implements Principal, Serializable {

	/**
	 * Serial version universal identifier.
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
//	private String domain;
//
//	private String userName;

//	private String sessionId;

	private boolean logged;

	private boolean logout;

	/**
	 * Values to change between client and server.
	 */
	private Map<String, String> values = new HashMap<String, String>();

	/**
	 * Defualt constructor.
	 */
	public PoCPrincipal() {
		super();
	}

	public PoCPrincipal(String name) {
		this.name = name;
	}

	/**
	 * @see java.security.Principal#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the domain
	 */
	public String getDomain() {
		return this.getValues().get(Constants.DOMAIN_KEY);
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.getValues().put(Constants.DOMAIN_KEY, domain);
	}

	/**
	 * @return the identity
	 */
	public String getIdentity() {
		return this.getValues().get(Constants.IDENTITY_KEY);
	}

	/**
	 * @param userName the userName to set
	 */
	public void setIdentity(String userName) {
		this.getValues().put(Constants.IDENTITY_KEY, userName);
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return this.getValues().get(Constants.SESSION_ID_KEY);
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.getValues().put(Constants.SESSION_ID_KEY, sessionId);
	}

	/**
	 * Gera um sessionId e atualiza o atributo sessionId.
	 */
	public void setSessionId() {
		this.setSessionId(PoCPrincipal.generateSessionId());
	}
	
	/**
	 * @return the loginOk
	 */
	public boolean isLogged() {
		return logged;
	}

	/**
	 * @param loginOk the loginOk to set
	 */
	public void setLogged(boolean loginOk) {
		this.logged = loginOk;
	}

	/**
	 * @return the logout
	 */
	public boolean isLogout() {
		return this.logout;
	}

	/**
	 * @param logout the logout to set
	 */
	public void setLogout(boolean logout) {
		this.logout = logout;
	}

	/**
	 * @return the values
	 */
	public Map<String, String> getValues() {
		return this.values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(Map<String, String> values) {
		this.values = values;
	}

	/**
	 * Transforma os atributos em uma string seguindo o padrão:
	 * domain@name@sessionId
	 */
	public void encode() {
		StringBuilder encode = new StringBuilder();
		for (Entry<String, String> entry : this.getValues().entrySet()) {
			encode.append(entry.getKey());
			encode.append('=');
			encode.append(entry.getValue());
			encode.append('\n');
		}
		this.name = encode.toString(); 
	}

	/**
	 * Transforma os atributos em uma string seguindo o padrão:
	 * domain@name@sessionId
	 */
	public void decode() {
		String[] keyAndValues = this.getName().split("\n");
		for (String keyValue : keyAndValues) {
			int index = keyValue.indexOf('=');
			if (index > -1) {
				String key = keyValue.substring(0, index);
				String value = keyValue.substring(index + 1);
				this.getValues().put(key, value);
			}
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("PoCPrincipal");
		result.append("\tdomain: " + this.getValues().get(Constants.DOMAIN_KEY));
		result.append("\tidentity: " + this.getValues().get(Constants.IDENTITY_KEY));
		result.append("\tvalues: ");
		for (Entry<String, String> value : this.getValues().entrySet()) {
			result.append(value);
			result.append(", ");
		}
		return result.toString();
	}

	/**
	 * Considera somente o atributo name, assim como a maioria dos Principals.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = 0;
		if (this.getName() != null) {
			result = this.getName().hashCode();
		}
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		boolean result = false;

		if (this == other) {
			result = true;
		} else if (other != null) {
            if (other instanceof Principal) {
            	Principal otherPrincipal = (Principal) other;
            	if (this.getName() != null) {
            		result = this.getName().equals(otherPrincipal.getName());
            	}
            }
		}
		return result;
	}

	/**
	 * Generate the session ID through the current time.
	 * @return
	 */
	public static String generateSessionId() {
	    return "" + System.currentTimeMillis();
	}

}
