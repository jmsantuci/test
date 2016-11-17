/**
 * 
 */
package jms.poc.tac.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

/**
 * @author jsantuci
 *
 */
public class Token {

	private String user;
	private static final Key key = MacProvider.generateKey(); // Usar a mesma do security-glue
	
	/**
	 * Default constructor.
	 */
	public Token() {
		super();
	}

	
	/**
	 * @param user
	 */
	public Token(String user) {
		super();
		this.setUser(user);
	}


	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the key
	 */
	public static Key getKey() {
		return key;
	}


	public String generateToken() {

		String compactJws = Jwts.builder()
		  .setSubject(this.getUser())
		  .signWith(SignatureAlgorithm.HS512, key)
//		  .setExpiration(new Date()) // It's possible to set a expiration date
		  .compact();
		return compactJws;
	}

	public boolean checkToken(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject().equals(this.getUser());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Token token = new Token("jms");
		String jwts = token.generateToken();
		System.out.println("JWTS: " + jwts);
		System.out.println("Token ok ? " + token.checkToken(jwts));
	}

}
