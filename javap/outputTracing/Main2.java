// filename Main.java
class Test {
    protected int x = 1, y;
    protected int method1() {
    	int a = 10;
    	return a;
    }
}
class Test2 extends Test{
	protected int method1() {
		int b = 20;
		return b;
	}
}
class Main2 {
    public static void main(String args[]) {
        //Test t1 = new Test();
        Test2 t = new Test2();
        Test t1 = new Test();
        Test t3= new Test2();
        //System.out.println(t.x + " " + t.y);
        int w  = t1.method1();
        System.out.println(w);
    }
}