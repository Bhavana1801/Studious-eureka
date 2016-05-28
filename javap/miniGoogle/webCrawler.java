import java.util.*;
import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements; 
class webCrawler {
  ArrayList<String> urlList = new ArrayList<String>();
  ArrayList<String> urls = new ArrayList<String>();
  public ArrayList<String> crawl(String seedPage) {
    webPage wbp = new webPage();
    urlList.add(0,seedPage);
    int i;
    try
    {
      for(i = 0; i<urlList.size(); i++) {
        try {
          Document doc = wbp.getContent(urlList.get(i));
          urls = wbp.getAllLinks(doc);
        } catch(Exception e) {
          e.printStackTrace();
        }
        if(urls == null) {
          continue;
        }
        for(int j = 0; j < urls.size(); j++) {
          if((urlList.contains(urls.get(j)) == false)) {
            urlList.add(urls.get(j));
          }
        }
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    return urlList;
  }
}