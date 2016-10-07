/**
 * 
 */
package jms.http.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author jsantuci
 *
 */
public class HTTPClient {

	/**
	 * Default constructor
	 */
	public HTTPClient() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		URL url;
		try {
			url = new URL("http://cpqd036516:8808/index.html");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
//			urlConnection.setRequestProperty("", "");
			if (urlConnection.getResponseCode() == 200) {
				System.out.println("Server response:");
				BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				String response = null;
				while ((response = br.readLine()) != null) {
					System.out.println(response);
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
