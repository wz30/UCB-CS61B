package hw1;

import java.net.*;
import java.io.*;

/**  A class that provides a main function to read five lines of a commercial
 *   Web page and print them in reverse order, given the name of a company.
 */

class OpenCommerical {
	/* OpenCommercial.java */
  /** Prompts the user for the name X of a company (a single string), opens
   *  the Web site corresponding to www.X.com, and prints the first five lines
   *  of the Web page in reverse order.
   *  @param arg is not used.
   *  @exception Exception thrown if there are any problems parsing the 
   *             user's input or opening the connection.
   */
  public static void main(String[] arg) throws Exception {

    BufferedReader keyboard;
    String inputLine;
    
    keyboard = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("Please enter the name of a company (without spaces): ");
    //System.out.println();
    System.out.flush();        /* Make sure the line is printed immediately. */
    inputLine = keyboard.readLine();
    
    /* Replace this comment with your solution.  */
    inputLine = "http://www." + inputLine +".com"; 
    System.out.println(inputLine);
    
    URL newURL = new URL(inputLine);
    BufferedReader in = new BufferedReader(
    						new InputStreamReader(newURL.openStream()));
    //String show = in.readLine();
    String[] content = new String[5];
    content[0] = in.readLine();
    content[1] = in.readLine();
    content[2] = in.readLine();
    content[3] = in.readLine();
    content[4] = in.readLine();
    System.out.println(content[4]);
    System.out.println(content[3]);
    System.out.println(content[2]);
    System.out.println(content[1]);
    System.out.println(content[0]);
  }
}