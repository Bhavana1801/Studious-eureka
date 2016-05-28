import org.jsoup.nodes.Document;
import java.util.ArrayList;
public class searchIndex {
  ArrayList<String> finalWords = new ArrayList<String>(); //words from all urls
  ArrayList<String> urlList = new ArrayList<String>(); //links of all urls
  public void indexing(String seedPage) {
    webCrawler wbc = new webCrawler();
    webPage wbp = new webPage();
    urlList = wbc.crawl(seedPage);
    for(int i = 0; i < urlList.size(); i++) {
      String url = urlList.get(i);
      try {
        Document doc = wbp.getContent(url);
        finalWords = wbp.getAllWords(url);
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
  }
  public String toString() {
    String temp="";
    for(int i = 0; i < urlList.size(); i++) {
      temp = temp + "\n" +urlList.get(i);
    }
    return temp;
  }
}