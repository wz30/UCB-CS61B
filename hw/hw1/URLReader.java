package hw1;
//http://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
/*After you've successfully created a URL, you can call the URL's openStream() method to get a stream from which you can read the contents of the URL. The openStream() method returns a java.io.InputStream object, so reading from a URL is as easy as reading from an input stream.

The following small Java program uses openStream() to get an input stream on the URL http://www.oracle.com/. It then opens a BufferedReader on the input stream and reads from the BufferedReader thereby reading from the URL. Everything read is copied to the standard output stream:*/
import java.net.*;
import java.io.*;

public class URLReader {

	public static void main(String[] args) throws IOException {
		
		URL oracle  = new URL("http://www.google.com");
		
		BufferedReader in = new BufferedReader(
				new InputStreamReader(oracle.openStream()));
		
		String inputLine;
		while((inputLine=in.readLine())!=null)
			System.out.println(inputLine);
		in.close();
	}

}
