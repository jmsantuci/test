/**
 * 
 */
package jms.jdt.call;

import java.io.IOException;
import java.lang.reflect.Constructor;

import org.eclipse.jdt.internal.compiler.env.AccessRestriction;
import org.eclipse.jdt.internal.compiler.env.IBinaryType;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;

/**
 * @author jsantuci
 *
 */
public class CallEclipseCompiler {

	/**
	 * 
	 */
	public CallEclipseCompiler() {
		super();
		
	}

	public String callJDTCompiler() {
       //"org.eclipse.jdt.internal.compiler.env.AccessRestriction"
		java.util.Enumeration<java.net.URL> e;
		try {
			e = Thread.currentThread().getContextClassLoader().getResources("org/eclipse/jdt/internal/compiler/env/AccessRestriction.class");
			while (e.hasMoreElements()) {
			    System.out.println(e.nextElement());
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Constructor constrNameEnvAnsBin2Args = null;
		Constructor constrNameEnvAnsCompUnit2Args = null;
//		AccessRestriction accessRestriction = new AccessRestriction(null, "JDT Compiler");

		Class classAccessRestriction;
		try {
			classAccessRestriction = Thread.currentThread().getContextClassLoader().loadClass("org.eclipse.jdt.internal.compiler.env.AccessRestriction");
			constrNameEnvAnsBin2Args = NameEnvironmentAnswer.class.getConstructor(new Class[]{IBinaryType.class, classAccessRestriction});
			constrNameEnvAnsCompUnit2Args = NameEnvironmentAnswer.class.getConstructor(new Class[]{ICompilationUnit.class, classAccessRestriction});
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
//		return accessRestriction.getMessageTemplate();
		return "";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
