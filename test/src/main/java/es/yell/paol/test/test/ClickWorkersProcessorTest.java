package es.yell.paol.test.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import es.yell.paol.business.client.PAOLBusinessSrvV3Stub.DoSimpleSearchResponse;

public class ClickWorkersProcessorTest {

	public static void main(String[] args) throws FactoryConfigurationError, Exception {
		String urlString = "http://paol.yelldes.intrayell.com:8085/paol-presentation-webapp/search/abogados/all-ma/madrid/all-is/madrid/all-ba/all-pu/all-nc/1?what=abogados&where=madrid&nb=false&ub=false&hl=MADRID&showModel=true";

		URL url = new URL(urlString);

		URLConnection urlConnection = url.openConnection();
		HttpURLConnection httpURLConnection = null;
		if (urlConnection instanceof HttpURLConnection) {
			httpURLConnection = (HttpURLConnection) urlConnection;
		}
		//urlConnection.setr
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				urlConnection.getInputStream()));
		int responseCode = httpURLConnection.getResponseCode();
		String type = httpURLConnection.getContentType();
		System.out.println("type:: "+type);
		StringBuilder builder = new StringBuilder();
		if (responseCode == 200) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				builder.append(line);
			}
		}
		//System.out.println(builder.toString());
		if (urlString.contains("aprob") && urlString.contains("nprob")) {
			XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().
            createXMLStreamReader(
                    new ByteArrayInputStream(builder.toString().getBytes()));
			/*DoSimpleSearchResponse response = DoSimpleSearchResponse.Factory.parse(xmlStreamReader);
			response.get_return().getAdvertisements();
			System.out.println("Adtz:: "+response.get_return().getAdvertisements());*/
		}else{
			XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().
            createXMLStreamReader(
                    new ByteArrayInputStream(builder.toString().getBytes()));
			/*es.yell.paol.business.client.PAOLBusinessSrvV2Stub.DoSimpleSearchResponse response = es.yell.paol.business.client.PAOLBusinessSrvV2Stub.DoSimpleSearchResponse.Factory.parse(xmlStreamReader);
			response.get_return().getAdvertisements();
			System.out.println("Adtz:: "+response.get_return().getAdvertisements());*/
		}
	}
}
