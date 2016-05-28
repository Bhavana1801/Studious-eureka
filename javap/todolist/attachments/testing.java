import java.util.*;
import java.text.*;
import java.io.*;
class testing {
	public static void main(String[] args)throws Exception {
		Date dt = new Date();
		String date = "28/08/15 09:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YY hh:mm:ss");
		dt = sdf.parse(date);
		System.out.println(dt);
	}
}