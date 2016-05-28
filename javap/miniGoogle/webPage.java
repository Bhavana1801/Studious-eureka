import java.io.IOException;
import java.util.*;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;  
public class webPage {  
        public Document getContent(String url) throws IOException {  
            Document doc = Jsoup.connect(url).get();  
            
            return doc; 
      }  
      public ArrayList<String> getAllLinks(Document data) {
        Elements links = data.select("a[href]");
        ArrayList<String> allLinks = new ArrayList<String>();
        for(Element link : links) {
          allLinks.add(link.attr("abs:href"));
        }
        return allLinks;
      }
      public ArrayList<String> getStopWords() {
        BufferedReader br = null;
        ArrayList<String> stopWrds = new ArrayList<String>();
        try {
          String line;
          br = new BufferedReader(new FileReader("StopWords.txt"));
          while((line = br.readLine())!= null) {
            stopWrds.add(line);
          }
        } catch(IOException e) {
          e.printStackTrace();
        } 
        return stopWrds;
      }
      public ArrayList<String> tokenData(Document doc) {
        Elements data = doc.select("html");
        ArrayList<String> dataAsWords= new ArrayList<String>();
        for(Element d : data) {
          StringTokenizer st = new StringTokenizer(d.text()," .,~<>-_/??\'\"\t\n\b\r\0[]()*&^%$#@!{}|");
          while(st.hasMoreTokens()) {
            dataAsWords.add(st.nextToken());
          }
        }
        return dataAsWords;
       }
      public ArrayList<String> dataInWords;
       public ArrayList<String> getAllWords(String url) throws IOException {
        ArrayList<String> stopWordsList = getStopWords(); //stopwords
        dataInWords = new ArrayList<String>();
        ArrayList<Integer> freqCount = new ArrayList<Integer>();
        Document doc = getContent(url);
        ArrayList<String> dataInWords = tokenData(doc); //html words
        for(int i = 0; i < dataInWords.size(); i++) {
          for(int j = 0; j < stopWordsList.size(); j++) {
            if(dataInWords.get(i).equalsIgnoreCase(stopWordsList.get(j))) {
            dataInWords.remove(i);
            }
          }
        }
        return dataInWords;
       }
       // public int getKeywordFreq(String word) {
       //  int i;
       //  int count = 0;
       //  ArrayList<String> finalWords = new ArrayList<String>();
       //  //System.out.println("see");
       //  for(i = 0; i < dataInWords.size(); i++) {
       //    System.out.println(dataInWords.get(0)+"hai");
       //    if(dataInWords.get(i).equalsIgnoreCase(word)) {
       //      System.out.println(dataInWords.get(i)+"-----"+word);
       //      count++;
       //    }
       //  }
       //  return count;
       // }
}