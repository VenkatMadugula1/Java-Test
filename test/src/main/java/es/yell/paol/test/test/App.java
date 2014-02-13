package es.yell.paol.test.test;

import org.apache.commons.lang.StringUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String value1 = "hello";
    	System.out.println(value1.equalsIgnoreCase("hello"));
    	
        System.out.println( "Hello World!" );
        
        String name = "3 estrellas";
        System.out.println(StringUtils.capitalize(name));
    }
}
