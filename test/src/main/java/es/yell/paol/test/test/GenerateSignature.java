package es.yell.paol.test.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class GenerateSignature {
	public static void main(String[] args) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
		String domain = "http://maps.google.com";
		String roadmap = URLEncoder.encode("roadmap", "UTF-8");
		String markers = URLEncoder.encode("size:mid|color:0x888888|40.6284909,-4.01929679", "UTF-8");
		String size = URLEncoder.encode("300x300&style=feature:all|element:all|saturation:-100", "UTF-8");
		String encodedUrl="/maps/api/staticmap?maptype=roadmap&sensor=false&zoom=15&markers=size:mid|color:0x888888|40.6284909,-4.01929679&size=300x300&style=feature:all|element:all|saturation:-100&scale=4&center=40.6284909,-4.01929679&client=gme-yelllimited";

		
		String privateKey = "yetphYqo9O6-jBt3jqnLjEyEe44=";
		
		String decodedKey = new String(Base64.decodeBase64(privateKey));
		
		System.out.println("Decoded key:: "+decodedKey);
		
		Mac mac = Mac.getInstance("HmacSHA1");
		SecretKeySpec secret = new SecretKeySpec(decodedKey.getBytes(), mac.getAlgorithm());
		mac.init(secret);
		byte[] digest = mac.doFinal(encodedUrl.getBytes());
		String signature=Base64.encodeBase64String(digest);
		System.out.println("Signature:: "+signature);
		
		String sing = "M+H1qyIkhrV2Ryb5K/qWI57bMH0=";
		String resul = sing.replace("+", "-").replace("/", "_");
		System.out.println("Result: "+resul);
		System.out.print("complete url:: "+domain+encodedUrl+"&signature="+signature);
		
	}
}
