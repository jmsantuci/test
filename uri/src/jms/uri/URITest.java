/**
 * 
 */
package jms.uri;

import java.awt.print.Printable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;

/**
 * @author jsantuci
 *
 */
public class URITest {

	/**
	 * Default constructor.
	 */
	public URITest() {
		super();
	}

	public static void print(URI uri) throws MalformedURLException {
		System.out.println("ASCII: " + uri.toASCIIString());
		System.out.println("toURL: " + uri.toURL());
		System.out.println("path: " + uri.getPath());
		System.out.println("query: " + uri.getQuery());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://test.com/test?test=1&t2=2&t3=/";
        try {
			URI uri = new URI(url);
			print(uri);
			uri = new URI("http", "test.com", "/test", "test=1&t2=2&t3=/&t5=1 2", "");
			print(uri);
			System.out.println("------------------------");
			System.out.println(URIUtil.encodeAll("http://test.com/test?test=1&t2=2&t3=/&t5=1 2"));
			System.out.println(URIUtil.encodeQuery("test=1&t2=2&t3=/&t5=1 2"));
			System.out.println("------------------------");
			System.out.println(URLEncoder.encode("test=1&t2=2&t3=/&t5=1 2"));
			System.out.println(URLEncoder.encode("test=WithoutSpace&t2=Forward///Slash&t3=Space Space Space"));
			System.out.println(URLEncoder.encode("CPqD-ETICS/ISP-Inv", "UTF-8"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
