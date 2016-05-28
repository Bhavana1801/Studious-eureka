import java.net.*;
import java.io.*;
import java.util.*;
class webPage {
	static ArrayList<String> getContent(String link) throws Exception{
		String line;
		ArrayList<String> data = new ArrayList<String>();
		URL oracle = new URL(link);
		BufferedReader input = new BufferedReader(new InputStreamReader(oracle.openStream()));
		while((line = input.readLine())!= null)
			data.add(line);
		input.close();
		//System.out.println(data);
		return data;
	}
	public ArrayList<String> getAllLinks(ArrayList<String> page) {
		ArrayList

	}
}