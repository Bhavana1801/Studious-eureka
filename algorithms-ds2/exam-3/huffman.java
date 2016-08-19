/*
6
a 10
b 5
c 100
d 50
e 20
f 15
output :
c: 0

d: 10

e: 110

f: 1110

b: 11110

a: 11111

395
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
		sort();
	}
	private void sort() {
		Comparator<Node> comp = new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if(getDecimal(n1) != getDecimal(n2))
					return getDecimal(n1) - getDecimal(n2);
				return n1.getValue().length()-n2.getValue().length();
			}
		};
		Collections.sort(list,comp);
	}
	public int getDecimal(Node n1) {
		int sum = 0;
		int k = n1.value.length();
		for(int i = 0; i < k; i++) {
			sum = sum +((Integer.parseInt(Character.toString(n1.value.charAt(k-i-1)))) * (int)Math.pow(2,i));
		}
		return sum;
	}

	public void traverse() {

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