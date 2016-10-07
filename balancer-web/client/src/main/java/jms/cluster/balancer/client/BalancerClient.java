package jms.cluster.balancer.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

/**
 * Balancer Test Client
 *
 */
public class BalancerClient {
	private List<String> nodes = new ArrayList<String>();
	private String host;
	private String systemProperty;
	private HttpClient httpClient = new DefaultHttpClient();
	private HttpContext httpContext = new BasicHttpContext();
	private HttpGet httpGet;

	/**
	 * Default constructor.
	 */
	public BalancerClient() {
		this("http://localhost:80");
	}

	/**
	 * @param host
	 * @param systemProperty
	 */
	public BalancerClient(String host) {
		this(host, "tomcat.node.name");
	}

	/**
	 * @param host
	 * @param systemProperty
	 */
	public BalancerClient(String host, String systemProperty) {
		super();
		this.setHost(host);
		this.setSystemProperty(systemProperty);
		this.setHttpGet(new HttpGet(this.getHost() + "/cpqd-balancer-test/BalancerTest?systemProperty=" + this.getSystemProperty()));
	}

	/**
	 * @return the nodes
	 */
	public List<String> getNodes() {
		return this.nodes;
	}

	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the systemProperty
	 */
	public String getSystemProperty() {
		return this.systemProperty;
	}

	/**
	 * @param systemProperty the systemProperty to set
	 */
	public void setSystemProperty(String systemProperty) {
		this.systemProperty = systemProperty;
	}

	/**
	 * @return the httpClient
	 */
	public HttpClient getHttpClient() {
		return httpClient;
	}

	/**
	 * @param httpClient the httpClient to set
	 */
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	/**
	 * @return the httpContext
	 */
	public HttpContext getHttpContext() {
		return httpContext;
	}

	/**
	 * @param httpContext the httpContext to set
	 */
	public void setHttpContext(HttpContext httpContext) {
		this.httpContext = httpContext;
	}

	/**
	 * @return the httpGet
	 */
	public HttpGet getHttpGet() {
		return httpGet;
	}

	/**
	 * @param httpGet the httpGet to set
	 */
	public void setHttpGet(HttpGet httpGet) {
		this.httpGet = httpGet;
	}

	public String getNodeName(String[] properties) {
		String nodeName = null;
		if (this.getSystemProperty().equals(properties[0]) && properties[1].length() > 0) {
			nodeName = properties[1];
		}
		return nodeName;
	}

	public void discoveryNodes() {
		System.out.println("Discovering cluster nodes .... \n");
    	try {
    		boolean continueSearch = true;
    		do {
    			String lastNodeFound = null;
		    	HttpResponse response = this.getHttpClient().execute(this.getHttpGet(), this.getHttpContext());
		    	 
				if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
				}
				BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
		 
				String output;

				while ((output = br.readLine()) != null) {
					String[] properties = output.split(":");

					if (properties.length > 1) {
						lastNodeFound = this.getNodeName(properties);
					}
				}
				if (lastNodeFound != null
						&& (this.getNodes().size() == 0
						       || !lastNodeFound.equals(this.getNodes().get(0)))) {
					System.out.println("Node found: " + lastNodeFound);
					this.getNodes().add(lastNodeFound);
				} else {
					continueSearch = false;
				}
			
			} while (continueSearch);
    	} catch (RuntimeException runtimeException) {
    		runtimeException.printStackTrace();
    	} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public void testStickSession() {
		this.setHttpGet(new HttpGet(this.getHost() + "/cpqd-balancer-test/BalancerTest?systemProperty=" + this.getSystemProperty() + "&createSession=true"));

		try {
			String lastNodeName;
			String nodeName = null;
			do {
				lastNodeName = nodeName;
				nodeName = null;
				HttpResponse response = this.getHttpClient().execute(this.getHttpGet(), this.getHttpContext());
		   	 
				if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
				}
				BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
		 
				String output;
		
				while ((output = br.readLine()) != null) {
					String[] properties = output.split(":");
					String nodeNameAux = this.getNodeName(properties);
					if (nodeNameAux != null) {
						nodeName = nodeNameAux;
					}
				}
				this.setHttpGet(new HttpGet(this.getHost() + "/cpqd-balancer-test/BalancerTest?systemProperty=" + this.getSystemProperty()));
			} while (nodeName != null && !nodeName.equals(lastNodeName));
			if (nodeName != null && !nodeName.equals(lastNodeName)) {
				System.out.println("Sticky session Ok for node: " + nodeName);
			}
			
		} catch (RuntimeException runtimeException) {
    		runtimeException.printStackTrace();
    	} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void main( String[] args ) {
//		Header[] headers = response.getAllHeaders();
//		CookieOrigin cookieOrigin = (CookieOrigin) httpContext.getAttribute(ClientContext.COOKIE_ORIGIN);
//		CookieStore cookieStore = (CookieStore) httpContext.getAttribute(ClientContext.COOKIE_STORE);
//		System.out.println("Cookie Origin: " + cookieOrigin);
//		System.out.println("Cookie Store: " + cookieStore.getCookies());
    	if (args.length > 0) {
    		BalancerClient balancerClient = null;
    		String host = args[0];
    		System.out.println("Host URL: " + host);
	    	
    		if (args.length > 1) {
    			String systemProperty = args[1];
    			System.out.println("SystemProperty: " + systemProperty);
    			balancerClient = new BalancerClient(host, systemProperty);
    		} else {
    			balancerClient = new BalancerClient(host);
    		}
    		balancerClient.discoveryNodes();
    		balancerClient.testStickSession();
    	}
    }
}
