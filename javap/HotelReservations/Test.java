import java.util.*;
import java.text.SimpleDateFormat;
public class Test {
	public static void main(String[] args) {
		Date dt = new Date();
		String date = "11/08/15 11:00:00";
		//tasks = list.getTask("20/08/15 11:00:00");
		SimpleDateFormat sd = new SimpleDateFormat("mm/dd/yyyy hh:mm:ss");
		System.out.println(sd.format(dt));
		System.out.println(dt);
		Date dat;
		if () {
			
		}
		try {
		System.out.println("see"+sd.parse(date));
	     }
	     catch(Exception e) {
	     	System.out.println(e);
	     }
	}
}