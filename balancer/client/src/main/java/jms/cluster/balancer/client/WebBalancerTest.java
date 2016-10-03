package jms.cluster.balancer.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

/**
 * Balancer Test Client
 *
 */
public class WebBalancerTest {
	private List<Node> nodes = new ArrayList<Node>();
	private Map<String, Node> nodeMap = new HashMap<String, Node>();
	private String host;
	private String context = "/cpqd-balancer-test";
	private String systemProperty;
	private HttpClient httpClient = new DefaultHttpClient();
	private HttpContext httpContext = new BasicHttpContext();
	private HttpGet httpGet;
	private int nodePosition;

	/**
	 * Default constructor.
	 */
	public WebBalancerTest() {
		this("http://localhost:80");
	}

	/**
	 * @param host
	 * @param systemProperty
	 */
	public WebBalancerTest(String host) {
		this(host, "tomcat.node.name");
	}

	/**
	 * @param host
	 * @param systemProperty
	 */
	public WebBalancerTest(String host, String systemProperty) {
		super();
		this.setHost(host);
		this.setSystemProperty(systemProperty);
		this.setHttpGet(new HttpGet(this.getBaseURL()));
	}

	/**
	 * @return the nodes
	 */
	public List<Node> getNodes() {
		return this.nodes;
	}

	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the nodeMap
	 */
	public Map<String, Node> getNodeMap() {
		return nodeMap;
	}

	/**
	 * @param nodeMap the nodeMap to set
	 */
	public void setNodeMap(Map<String, Node> nodeMap) {
		this.nodeMap = nodeMap;
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
	 * @return the context
	 */
	public String getContext() {
		return this.context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(String context) {
		this.context = context;
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

	/**
	 * @return the nodePosition
	 */
	public int getNodePosition() {
		return this.nodePosition;
	}

	/**
	 * @param nodePosition the nodePosition to set
	 */
	public void setNodePosition(int nodePosition) {
		this.nodePosition = nodePosition;
	}

	public String getBaseURL() {
		return this.getHost() + this.getContext() + "/BalancerTest?systemProperty=" + this.getSystemProperty();
	}

	public String getCreateSessionURL() {
		return this.getBaseURL() + "&createSession=true";
	}
	
	public String getInvalidateSessionURL() {
		return this.getBaseURL() + "&invalidateSession=true";
	}
	
	public String getNodeName(String[] properties) {
		String nodeName = null;
		if (this.getSystemProperty().equals(properties[0]) && properties[1].length() > 0) {
			nodeName = properties[1];
		}
		return nodeName;
	}

	public String getSessionId(String[] properties) {
		String sessionId = null;
		if ("sessionId".equals(properties[0]) && properties[1].length() > 0) {
			sessionId = properties[1];
		}
		return sessionId;
	}

	public String getJSessionId(String cookieValue) {
		String jsessionId = "";
		String[] cookies = cookieValue.split(";");
		for (String cookie : cookies) {
			if (cookie.startsWith("JSESSIONID=")) {
//				jsessionId = cookie.substring(cookieValue.indexOf("=") + 1);
				jsessionId = cookie;
			}
		}
		
		return jsessionId;
	}
	public void setCookie(HttpResponse response, Node node) {
		Header[] headers = response.getAllHeaders();
		for (Header header : headers) {
			if (header.getName().equals("Set-Cookie")) {
				node.setCookieName("Cookie");
				node.setCookieValue(this.getJSessionId(header.getValue()));
			}
		}
	}
	
	public Node parseNode(HttpResponse response) {
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		}
		Node node = new Node();
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output;

			while ((output = br.readLine()) != null) {
				String[] properties = output.split(":");

				if (properties.length > 1) {
					node.setName(this.getNodeName(properties));
					node.setSessionId(this.getSessionId(properties));
				}
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        this.setCookie(response, node);
		return node;
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private Node createSession() throws IOException, ClientProtocolException {
		this.setHttpGet(new HttpGet(this.getCreateSessionURL()));
		HttpResponse response = this.getHttpClient().execute(this.getHttpGet(), this.getHttpContext());
		return this.parseNode(response);
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private Node httpGet() throws IOException, ClientProtocolException {
		this.setHttpGet(new HttpGet(this.getBaseURL()));
		HttpResponse response = this.getHttpClient().execute(this.getHttpGet(), this.getHttpContext());
		return this.parseNode(response);
	}
	
	/**
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private Node httpGet(String cookieName, String cookieValue) throws IOException, ClientProtocolException {
		this.setHttpGet(new HttpGet(this.getBaseURL()));
		HttpResponse response = this.getHttpClient().execute(this.getHttpGet(), this.getHttpContext());
		return this.parseNode(response);
	}

	private Node invalidateSession(String cookieName, String cookieValue) throws IOException, ClientProtocolException {
		this.setHttpGet(new HttpGet(this.getInvalidateSessionURL()));
		HttpResponse response = this.getHttpClient().execute(this.getHttpGet(), this.getHttpContext());
		Node node = this.parseNode(response);
		this.setHttpClient(new DefaultHttpClient());
		return node;
	}

//	private void setHeader(Node node, HttpGet httpGet) {
//		CookieStore cookieStore = new BasicCookieStore();
//		BasicClientCookie cookie = new BasicClientCookie(node.getCookieName(), node.getCookieValue());
//
//		//cookie.setDomain("your domain");
//		cookie.setPath("/");

//		cookieStore.addCookie(cookie);
//		this.getHttpClient().setCookieStore(cookieStore);
		
		
		
//		httpGet.setHeader(node.getCookieName(), node.getCookieValue());
		
//	}

	public void discoveryNodes() {
		System.out.println("Discovering cluster nodes .... \n");
    	try {
    		boolean continueSearch = true;
    		do {
    			Node lastNodeFound = this.httpGet();
				if (lastNodeFound.getName() != null
						&& (this.getNodes().size() == 0
						       || !lastNodeFound.equals(this.getNodes().get(0)))) {
					System.out.println("Node found: " + lastNodeFound.getName());
					this.getNodes().add(lastNodeFound);
					this.getNodeMap().put(lastNodeFound.getName(), lastNodeFound);
				} else {
					continueSearch = false;
				}
			
			} while (continueSearch);
    	} catch (RuntimeException runtimeException) {
    		runtimeException.printStackTrace();
    	} catch (IOException ioException) {
			ioException.printStackTrace();
		}
    	// Quando o discovery termina o último acesso foi ao primeiro nó, então posiciona a lista no segundo
    	this.setNodePosition(1);
	}

	public Node testStickSession() {
		Node createSessionNode = null;
		try {
			createSessionNode = this.createSession();
			Node node = this.httpGet(createSessionNode.getCookieName(), createSessionNode.getCookieValue());
			
			if (createSessionNode.equals(node)) {
				System.out.println("Sticky session Ok for node: " + node.getName());
				node = this.getNodeMap().get(node.getName());
				if (node != null) {
					node.setStickySessionOk(true);
					try {
						this.invalidateSession(createSessionNode.getCookieName(), createSessionNode.getCookieValue());
					} catch (Exception exception) {
						
					}
				}
			}

		} catch (RuntimeException runtimeException) {
    		runtimeException.printStackTrace();
    	} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		return createSessionNode;
	}

	public void testStickSessionForAllNodes() {
		Node firstNode = testStickSession();
		if (firstNode != null) {
			Node currentNode = null;
			while (!firstNode.equals(currentNode)) {
				currentNode = testStickSession();
			}
		}
	}

	public static void main( String[] args ) {
    	if (args.length > 0) {
    		WebBalancerTest balancerClient = null;
    		String host = args[0];
    		System.out.println("Host URL: " + host);
	    	
    		if (args.length > 1) {
    			String systemProperty = args[1];
    			System.out.println("SystemProperty: " + systemProperty);
    			balancerClient = new WebBalancerTest(host, systemProperty);
    		} else {
    			balancerClient = new WebBalancerTest(host);
    		}
    		balancerClient.discoveryNodes();
    		balancerClient.testStickSessionForAllNodes();
    	}
    }
}
