package hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Nuke2 {

	public static void main(String[] args) throws IOException {
		BufferedReader keyboard;
	    String inputLine;
	    
	    keyboard = new BufferedReader(new InputStreamReader(System.in));

	    System.out.print("Please enter the name of a company (without spaces): ");
	    //System.out.println();
	    System.out.flush();        /* Make sure the line is printed immediately. */
	    inputLine = keyboard.readLine();
	    System.out.println(inputLine);
	    if(inputLine.length()<2) System.out.println("Error"); 
	    else{
	    	inputLine=inputLine.substring(0, 1)+inputLine.substring(2);
	    	System.out.println(inputLine);
	    }
	    
	    /*
	    StringBuilder out = new StringBuilder(inputLine);
	    out.delete(1, 2);
	    System.out.println(out.toString());//out.toString();
	    */
	}

}
