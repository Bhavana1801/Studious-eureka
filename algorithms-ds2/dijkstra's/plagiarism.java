import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;
class plagiarism {
	public static void main(String[] args) throws FileNotFoundException {
		String filename1 = "mainD1.java";
		String filename2 = "pdijk.java";
		String frstFileData;
		String secFileData;
		int count = 0;
		float percentage = 0.0f;
		extractData e = new extractData();
		frstFileData = e.getData(filename1);
		secFileData = e.getData(filename2);
		//System.out.println(e.getData(filename2));
		ArrayList<String> firstFile = new ArrayList<>();
		ArrayList<String> secondFile = new ArrayList<>();
		firstFile = e.getWords(frstFileData);
		secondFile = e.getWords(secFileData);
		//System.out.println(firstFile);
		for(int i = 0; i < firstFile.size(); i ++) {
			for(int j = 0; j < secondFile.size(); j ++) {
				if(firstFile.get(i).equals(secondFile.get(j))) {
					count ++;
					break;
				}
			}
		}
		percentage = (count*100)/firstFile.size();
		//System.out.println(count +"1..."+ firstFile.size()+"2..."+secondFile.size());
		System.out.println(percentage+"%");
	}
}
class extractData {
	String data;
	ArrayList<String> words;
	public String getData(String filename) throws FileNotFoundException {
		data = "";
		File file = new File(filename);
		Scanner in = new Scanner(file);
		while(in.hasNextLine()) {
			data = data + in.nextLine();
		}
		return data;
	}
	public ArrayList<String> getWords(String data) {
		StringTokenizer st = new StringTokenizer(data," 1234567890 !@//()&^%*;.:");
		words = new ArrayList<>();
		while(st.hasMoreTokens()) {
			words.add(st.nextToken());
		}
		return words;
	}
}