package es.yell.paol.test.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ClickWorkersProcessHttpClientTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {

		Logger.getRootLogger().setLevel(Level.OFF);
		
		/*HttpClient client = new HttpClient();
		client.getParams().setParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT,Integer.valueOf("10000"));
		client.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT,Integer.valueOf("10000"));
		// Create a method instance.
		HttpMethod method = new GetMethod("http://paol.yelldes.intrayell.com:8085/paol-presentation-webapp/search/abogados/all-ma/madrid/all-is/madrid/all-ba/all-pu/all-nc/1?what=abogados&where=madrid&nb=false&ub=false&hl=MADRID&showModel=true");
		method.get
		client.set
		CookieStore cookieStore = client.getCookieStore();
		BasicClientCookie cookie = new BasicClientCookie("abc", "123");
		//Provide custom retry handler is necessary
		//method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler(3, false));
		//method.setQueryString(EncodingUtil.formUrlEncode(new NameValuePair[]{new NameValuePair("query", "abogados")},"ISO-8859-1"));

		try {
			long currentTimeMillis = System.currentTimeMillis();
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			//byte[] responseBody = method.getResponseBody();
			String xmlResponse = new String(method.getResponseBody(), "UTF-8");

			System.out.println("Total time:: "+(System.currentTimeMillis()-currentTimeMillis));
			//System.out.println("Response body: "+new String(responseBody));
			System.out.println("Response body: "+xmlResponse);
			
		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	*/

		/*DefaultHttpClient httpclient = new DefaultHttpClient();
	    CookieStore cookieStore = httpclient.getCookieStore();
	    BasicClientCookie cookie = new BasicClientCookie("abc", "123");

	    // Prepare a request object
	    HttpGet httpget = new HttpGet("http://abc.net/restofurl");

	    cookieStore.addCookie(cookie);
	    httpclient.setCookieStore(cookieStore);

	    // Execute the request
	    HttpResponse response = httpclient.execute(httpget);

	    // Examine the response status
	    log.info("Http request response is: " + response.getStatusLine());

	    List<Cookie> cookies = cookieStore.getCookies();

	    for (int i=0; i<cookies.size();i++) {

	        if (cookies.get(i).getName().toString().equals("abc")) {
	            log.info("cookie is: " + cookies.get(0).getValue().toString());
	            }
	    }*/
		
		DefaultHttpClient client = new DefaultHttpClient();
		//HttpPost httppost = new HttpPost("http://paol.yelldes.intrayell.com:8085/paol-presentation-webapp/search/abogados/all-ma/madrid/all-is/madrid/all-ba/all-pu/all-nc/1?what=abogados&where=madrid&nb=false&ub=false&hl=MADRID&showModel=true");
		//HttpGet httpget = new HttpGet("http://paol.yelldes.intrayell.com:8085/paol-presentation-webapp/search/abogados/all-ma/madrid/all-is/madrid/all-ba/all-pu/all-nc/1?what=abogados&where=madrid&nb=false&ub=false&hl=MADRID&showModel=true");
		HttpGet httpget = new HttpGet("http://paol.yelldes.intrayell.com:8085/paol-presentation-webapp/search/sanidad/all-ma/madrid/all-is/madrid/all-ba/all-pu/all-nc/1?what=Sanidad&where=Madrid&nb=false&ub=false&showModel=true");
		
		CookieStore cookieStore = new BasicCookieStore();
		//client.get
		BasicClientCookie cookie = new BasicClientCookie("seedCookie", "3991");

		cookie.setDomain("yelldes.intrayell.com");
		cookie.setPath("/");

		cookieStore.addCookie(cookie); 
		client.setCookieStore(cookieStore); 
		HttpResponse response = client.execute(httpget); 
		InputStream stream = response.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		int responseCode = response.getStatusLine().getStatusCode();
		/*Header[] types= response.getHeaders("content-type");
		System.out.println("type:: "+types[0]);*/
		StringBuilder builder = new StringBuilder();
		if (responseCode == 200) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				
				builder.append(line);
			}
		}
		System.out.println(builder.toString());
	}

}
