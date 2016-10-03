/**
 * 
 */
package jms.cdijsf;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;

/**
 * @author jsantuci
 *
 */
@SessionScoped
@Named("cdiTestBean2")
@Default
public class CDITestBeanTwo implements Serializable {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
//	public CDITestBeanTwo() {
//		super();
//	}

	public void test() {
		System.out.println("CDITestBeanTwo.test");
	}
}
