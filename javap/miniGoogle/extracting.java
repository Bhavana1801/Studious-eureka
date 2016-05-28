import java.net.*;
import java.io.*;
import java.util.*;
class extracting {
	public static void main(String[] args) throws Exception{
		String line;
		ArrayList<String> data = new ArrayList<String>();
		URL oracle = new URL("http://www.google.com");
		BufferedReader input = new BufferedReader(new InputStreamReader(oracle.openStream()));
		while((line = input.readLine())!= null)
			data.add(line);
		input.close();
		System.out.println(data);

	}
}