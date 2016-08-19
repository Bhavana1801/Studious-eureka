import java.util.*;
public class substring
{
   public static void main(String args[])
   {
      String string, sub;
      int i, c, length;
      ArrayList<String> list = new ArrayList<>();
      Scanner in = new Scanner(System.in);
      string = in.nextLine();
      String[] str = string.split(",");
      string="";
      for(i = 0; i < str.length;i++) {
         string = string+str[i];
      }
      length = string.length();   
      for( c = 0 ; c < length ; c++ )
      {
         for( i = 1 ; i <= length - c ; i++ )
         {
            sub = string.substring(c, c+i);
            // System.out.println(sub);
            list.add(sub);
         }
      }
      // System.out.println(list);
      // if(list.contains("-")) {
      //    list.remove("-");
      // }
      // System.out.println(list);
      findSum obj = new findSum(list);
      obj.getResult();
      //obj.print();
   }
}
class findSum {
   ArrayList<String> list;
   int max;
   String main="";
   findSum(ArrayList<String>list) {
      this.list = list;
      max= -1;
   } 
   public void getResult() {
      
      for(int i =0 ; i < list.size(); i++) {
         String str = list.get(i);
         //System.out.println(list);
         String[] numbers = str.split("");
         int sum = 0;
         //System.out.println(Arrays.toString(numbers));
         for(int j = 0; j < numbers.length; j++) {
            if(!numbers[j].equals("-"))
            sum = sum + Integer.parseInt(numbers[j]);
         }
         if(sum>max) {
            max = sum;
            main = str;
         }
         
      }
      print();
   }
   public void print() {
      // int sum=0;
      // if(main.contains("-")) {
      //    System.out.println(main);
      //    String[] arr = main.split(",");
      //    System.out.println(Arrays.toString(arr));
      //    for(int i = 0; i < arr.length;i++) {
      //       // sum = sum+Integer.parseInt(arr[i]);
      //       System.out.println(arr[i]);
      //    }
      //    max=sum;
      // }
      if(max<0) {
         System.out.println("0");
         System.out.println("Maximum sum is less than 0.");
         return;
      }
      System.out.println(max);
      for(int i = 0; i <main.length();i++) {
         if(i==main.length()-1) {
            System.out.print(main.charAt(i));
         }
         else
         System.out.print(main.charAt(i)+",");
      }
      // System.out.println(main);
   }
}