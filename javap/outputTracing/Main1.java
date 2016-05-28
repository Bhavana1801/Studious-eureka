
 
class Base {
    public void Print() {
        System.out.println("Base");
    }         
}
 
class Derived extends Base {    
    public void Show() {
        System.out.println("Derived");
    }
}
 
class Main1{
    public static void DoPrint( Base o ) {
        o.Print();
        o.Show();   
    }
    public static void main(String[] args) {
        Base x = new Base();
        Base y = new Derived();
        Derived z = new Derived();
        DoPrint(x);
        DoPrint(y);
        DoPrint(z);
    }
}