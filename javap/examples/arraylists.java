import java.util.ArrayList;
import java.util.Iterator;
class arraylists {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("world");
		list.add("hai");
		list.add("hw r u");
		System.out.println(list); //prints all elements
		list.add(2,"new");
		System.out.println(list);
		System.out.println("hai is in "+ list.indexOf("hai"));
		System.out.println("size"+list.size());
		list.remove(1);
		System.out.println("size"+list.size());
		//get method
		System.out.println("element at 4th position "+list.get(3));
		//adding two lists
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("bhavana");
		list2.add("Manisha");
		list.addAll(list2);
		//after adding
		//using iterator
		 Iterator<String> itr = list.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
	}
}