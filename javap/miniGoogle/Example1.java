import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;  
import java.io.File;
public class Example1 {  
     public static void main( String[] args ) throws IOException{  

            Document doc = Jsoup.connect("http://minigoogle.msitprogram.net").get();  
            System.out.println(doc);
              
            Document htmlwords = null;
            try {
              htmlwords = Jsoup.parse(doc = Jsoup.connect("http://minigoogle.msitprogram.net").get(),"ISO-8859-1");
              } catch(IOException e) {
                e.printStackTrace();
              }
              System.out.println(htmlwords);
 }
}