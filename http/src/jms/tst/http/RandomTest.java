/**
 * 
 */
package jms.tst.http;

import java.util.Random;

/**
 * @author jsantuci
 *
 */
public class RandomTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random randomGenerator = new Random();
		for (int idx = 1; idx <= 10; ++idx){
	      int randomInt = randomGenerator.nextInt(1000000);
	      System.out.println("Generated : " + randomInt);
	    }
	}

}
