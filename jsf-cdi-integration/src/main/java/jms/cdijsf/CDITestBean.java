/**
 * 
 */
package jms.cdijsf;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author jsantuci
 *
 */
@RequestScoped
@Named("cdiTestBean")
public class CDITestBean {

	@Inject
	private Instance<CDITestBeanTwo> cdiTestBeanTwoInstance;

	@Inject
	private CDITestBeanTwo cdiTestBeanTwo;

	/**
	 * Default Constructor
	 */
//	public CDITestBean() {
//		super();
//	}

	public void test() {
		System.out.println("CDITestBean.test");
		
		this.cdiTestBeanTwo.test();
		CDITestBeanTwo cdiTestBeanTwo = this.cdiTestBeanTwoInstance.get();
		cdiTestBeanTwo.test();
	}
}
