/*
6
a 12
b 2
c 7
d 13
e 14
f 85
output :
f 1
a: 001
d: 010
e: 011
b: 0000
c: 0001
238
*/
import java.util.*;
;
class huffman {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine());
		MyQueue obj = new MyQueue(num);
		for(int i = 0; i < num; i++) {
			// String str = sc.nextLine();
			// StringTokenizer st = new StringTokenizer(str," ");
			int j = 0;
			String str[] = sc.nextLine().split(" ");
			obj.addNodes(str[0],Integer.parseInt(str[1]));
		}
		obj.start();
		obj.levelOrder();
		obj.traverse();

	}
}
class MyQueue {
	int size;
	String[] nodes;
	int[] freq;
	int booleanValue;
	PriorityQueue<Node> queue;
	ArrayList<Node> list;
	MyQueue(int size) {
		this.size = size;
		nodes = new String[size];
		freq = new int[size];
		booleanValue = 0;
		list = new ArrayList<Node>();
		init();
	}
	public void getAllData(String[] nodes, int[] freq) {
		this.nodes = nodes;
		this.freq = freq;
	}
	public void init() {
		queue = new PriorityQueue<Node>(size,new Comparator<Node>() {
			public int compare(Node node1, Node node2) {
				return node1.getfreq() - node2.getfreq();
			}
		});
	}
	public void addNodes(String node,int freq) {
		queue.add(new Node(node,freq));
	}
	// public void printQueue() {
	// 	for(int i = 0; i < size;i++) {
	// 		System.out.println(queue[i].getValue()+" "+queue[i].getfreq());
	// 	}
	// }
	public void start() {
		Node firstMin = new Node();
		Node secondMin = new Node();
		while(queue.size() > 1) {
			firstMin = queue.poll();
			secondMin = queue.poll();
			Node ins = new Node("mixedNode",firstMin.freq+secondMin.freq);
			firstMin.parent = ins;
			secondMin.parent = ins;
			firstMin.value = "0";
			secondMin.value = "1";
			ins.left = firstMin;
			ins.right = secondMin;
			queue.add(ins);
		}
	}
	public void levelOrder() {
		for(int i=0;i<=size;i++) {
			levelOrder(queue.peek(), i);
		}
	}
	public void traverse() {
		
		for(int i=1;i<list.size();i++) {
			if(list.get(i).parent.equals(list.get(i-1).parent)) {
				if(list.get(i).node.compareTo(list.get(i-1).node) < 0) {
					Collections.swap(list, i,i-1);
				}		
			}
		}
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).node+": "+list.get(i).getValue());
		}
		System.out.println(booleanValue);
	}
	public void levelOrder(Node node, int level) {
		if(node == null) {
			return;
		} 
		if(level == 1) {
			if(node.node.equals("mixedNode")) {
					booleanValue = booleanValue + node.freq;
				}
			Node parent = node.parent;
			String str;
			if(parent != null) {
				str = parent.getValue()+node.getValue();
				node.value = str;
				if(!node.node.equals("mixedNode")) {
					list.add(node);
				}
			}
		}
		else if(level>1) {
			levelOrder(node.left, level-1);
			levelOrder(node.right, level-1);
		}
	}


}
class Node {
	String value;
	int freq;
	Node left;
	Node right;
	Node parent;
	String node;
	Node() {
		left = null;
		right = null;
		parent = null;
		value = "";
	}
	Node(String node,int freq) {
		this.node = node;
		this.freq = freq;
		init();
	}
	public void init() {
		left = null;
		right = null;
		parent = null;
		value = "";
	}
	public int getfreq() {
		return freq;
	}
	public String getValue() {
		return value;
	}
	public Node getLeftChild() {
		return left;
	}
	public Node getRightChild() {
		return right;
	}
	public Node getParent() {
		return parent;
	}
}