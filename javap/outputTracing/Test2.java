// filename: Test2.java
class Test1 {   
    Test1(int x) {
    	System.out.println("5");
        System.out.println("Constructor called " + x);
        System.out.println("6");
    }
}
  
// This class contains an instance of Test1 
class Test21 {    
    Test1 t1 = new Test1(10);   
  //System.out.println("1");
    Test21(int i) { 
    	System.out.println("3");
    	t1 = new Test1(i);
    	System.out.println("4"); } 
    	
  //System.out.println("2");
    public static void main(String[] args) { 
    System.out.println("1");   
         Test21 t21 = new Test21(500);
         System.out.println("2");
  }
 }
 // filename: Test2.java
// class Test1 {   
//     Test1(int x) {
//         System.out.println("Constructor called " + x);
//     }
// }
  
// // This class contains an instance of Test1 
// class Test2 {    
//     Test1 t1 = new Test1(10);   
  
//     Test2(int i) { t1 = new Test1(i); } 
  
//     public static void main(String[] args) {    
//          Test2 t2 = new Test2(5);
//     }
// }