package es.yell.paol.test.test;

import java.io.CharArrayWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.BitSet;

import org.apache.commons.lang.StringUtils;

public class SimplTest {

	static BitSet dontNeedEncoding;
	static final int caseDiff = ('a' - 'A');
	static String dfltEncName = null;
	static {
		dontNeedEncoding = new BitSet(256);
		int i;
		for (i = 'a'; i <= 'z'; i++) {
			dontNeedEncoding.set(i);
		}
		for (i = 'A'; i <= 'Z'; i++) {
			dontNeedEncoding.set(i);
		}
		for (i = '0'; i <= '9'; i++) {
			dontNeedEncoding.set(i);
		}
		dontNeedEncoding.set('-');
		dontNeedEncoding.set('_');
		dontNeedEncoding.set('.');
		dontNeedEncoding.set('*');
		dfltEncName = "UTF-8";
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String testUrl = "http://moxqum.yelldes.intrayell.com/paol/images/logo.gif?id_maq=paoldesa.yelldes.intrayell.com&serv=192.168.122.45&urlf=www.paginasamarillas.es/resultados.jsp&urlv=cod_ret%3D0%26tx_busq%3D%26nomb%3D%26activ%3DColegios+privados%26lo%3D%26prov%3DMadrid%26pgpv%3D1%26mode%3Dstatic%26site%3Dpaol%26id_busq%3Dpaol2012674715X38ac15b5c0a87a20%26codpost%3D";
		System.out.println("Test URL:: "+URLDecoder.decode(testUrl, "UTF-8"));
		System.out.println("a-coruña - e"+URLEncoder.encode("a-coruña", "UTF-8"));
		System.out.println("a-coruña - d"+URLDecoder.decode("a-coruña", "UTF-8"));
		String value = "A Coruña";
		String oupt = URLEncoder.encode(value, "UTF-8");
		System.out.println("E Value1: : "+oupt);
		System.out.println("Decode V1: : "+URLDecoder.decode(oupt, "UTF-8"));
		value = "Málaga";
		System.out.println("E Value1: : "+URLEncoder.encode(value, "UTF-8"));
		System.out.println("Decode Value1: : "+URLDecoder.decode(value, "UTF-8"));
		String url = "<link>http://www.paginasamarillas.es/search/constructoras/all-ma/all-pr/all-is/all-ci/all-ba/all-pu/all-nc/1?cu=constructoras&amp;ub=false</link>";
		String secondUrl = "<link>http://www.paginasamarillas.es/search/constructoras/all-ma/all-pr/all-is/all-ci/all-ba/all-pu/all-nc/1?cu=constructoras&amp;ub=false</link>";
		//url
		if(url.contains("all-pr")){
			String output = StringUtils.replace(url, "all-pr", "acouna");
			System.out.println("Output1: "+output);
		}
		if(secondUrl.contains("all-pr")){
			String output = StringUtils.replace(secondUrl, "all-pr", "madrid");
			System.out.println("Output2: "+output);
		}
		
		String text = "abogados : las naciones L’abraccio";
		/*text = "uñas";*/
		text = "abogados & cía";
		String output = encode(text, "UTF-8");
		String output1 = encodeEEE(text);
		System.out.println("Value1: "+output);
		System.out.println("Value2: "+output1);
		String s1 = "";
		String s2 = "";
		boolean result = false;
		if(!"".equals(s1) || !"".equals(s2)){
			result = true;
		}
		System.out.println("Result:: "+result);
	}
	
	public static String encode(String s, String enc)
			throws UnsupportedEncodingException {

		boolean needToChange = false;
		StringBuffer out = new StringBuffer(s.length());
		Charset charset;
		CharArrayWriter charArrayWriter = new CharArrayWriter();

		if (enc == null)
			throw new NullPointerException("charsetName");

		try {
			charset = Charset.forName(enc);
		} catch (IllegalCharsetNameException e) {
			throw new UnsupportedEncodingException(enc);
		} catch (UnsupportedCharsetException e) {
			throw new UnsupportedEncodingException(enc);
		}
		for (int i = 0; i < s.length();) {
			int c = (int) s.charAt(i);
			if (dontNeedEncoding.get(c)) {
				out.append((char) c);
				i++;
			} else {
				do {
					charArrayWriter.write(c);
					if (c >= 0xD800 && c <= 0xDBFF) {
						if ((i + 1) < s.length()) {
							int d = (int) s.charAt(i + 1);
							if (d >= 0xDC00 && d <= 0xDFFF) {
								charArrayWriter.write(d);
								i++;
							}
						}
					}
					i++;
				} while (i < s.length()
						&& !dontNeedEncoding.get((c = (int) s.charAt(i))));

				charArrayWriter.flush();
				String str = new String(charArrayWriter.toCharArray());
				byte[] ba = str.getBytes(charset.name());
				for (int j = 0; j < ba.length; j++) {
					out.append('%');
					char ch = Character.forDigit((ba[j] >> 4) & 0xF, 16);
					if (Character.isLetter(ch)) {
						ch -= caseDiff;
					}
					out.append(ch);
					ch = Character.forDigit(ba[j] & 0xF, 16);
					if (Character.isLetter(ch)) {
						ch -= caseDiff;
					}
					out.append(ch);
				}
				charArrayWriter.reset();
				needToChange = true;
			}
		}
		return (needToChange ? out.toString() : s);
	}
	
	/**
     * @param input
     * @return
     */
    public static String encodeEEE(String input) {
        StringBuilder resultStr = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (isUnsafe(ch)) {
                resultStr.append('%');
                resultStr.append(toHex(ch / 16));
                resultStr.append(toHex(ch % 16));
            } else {
                resultStr.append(ch);
            }
        }
        return resultStr.toString();
    }

    /**
     * @param ch
     * @return
     */
    private static char toHex(int ch) {
        return (char) (ch < 10 ? '0' + ch : 'A' + ch - 10);
    }

    /**
     * @param ch
     * @return
     */
    private static boolean isUnsafe(char ch) {
        if (ch > 128 || ch < 0)
            return true;
        return " %$&+,/:;=?@<>#%".indexOf(ch) >= 0;
    }

}
