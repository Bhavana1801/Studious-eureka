
import java.util.*;

public class Huffman {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int size = Integer.parseInt(sc.nextLine());
		String nodes[] = new String[size];
		int freq[] = new int[size];
		for(int i=0;i<size;i++) {
			String temp[] = sc.nextLine().split(" ");
			nodes[i] = temp[0];
			freq[i] = Integer.parseInt(temp[1]);
		}
		Background bg = new Background(nodes, freq, size);
		bg.initialise();
		bg.start();
		bg.traverse();
	}
}

class Background {
	String[] nodes;
	int[] freq;
	int size, answer;
	PriorityQueue<Node> que;
	String cruck;
	ArrayList<Node> reCheck;
	public Background(String[] nodes, int[] freq, int size) {
		this.nodes= nodes;
		this.freq = freq;
		this.size = size;
		que = new PriorityQueue<Node>(size, new Comparator<Node>() {
			public int compare(Node g1, Node g2) {
					return g1.frequency - g2.frequency;
				}
		});
		cruck = "";
		answer = 0;
		reCheck = new ArrayList<Node>();
	}

	public void initialise() {
		for(int i=0;i<size;i++) {
			que.add(new Node(freq[i], nodes[i]));
		}
	}

	public void start() {
		while(que.size() != 1) {
			Node n1 = que.poll();
			Node n2 = que.poll();
			Node ins = new Node(n1.frequency+n2.frequency, "test");
			n1.parent = ins;
			n2.parent = ins;
			n1.value = "0";
			n2.value = "1";
			ins.left = n1;
			ins.right = n2;
			que.add(ins);
		}
	}

	public void traverse() {
		for(int i=0;i<=size;i++) {
			levelOrder(que.peek(), i);
		}
		for(int i=1;i<reCheck.size();i++) {
			if(reCheck.get(i).parent.equals(reCheck.get(i-1).parent)) {
				if(reCheck.get(i).node.compareTo(reCheck.get(i-1).node) < 0) {
					Collections.swap(reCheck, i,i-1);
				}		
			}
		}
		for(int i=0;i<reCheck.size();i++) {
			System.out.println(reCheck.get(i).node+": "+reCheck.get(i).value);
		}
		System.out.println(answer);
	}

	public void levelOrder(Node node, int level) {
		if(node == null) return;
		if(level == 1) {
			if(node.node.equals("test")) {
					answer = answer + node.frequency;
				}
			Node parent = node.parent;
			if(parent != null) {
				String gg = parent.value+node.value;
				node.value = gg;
				if(!node.node.equals("test")) {
					reCheck.add(node);
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
	int frequency;
	Node left;
	Node right;
	String node;
	Node parent;
	String value;
	public Node(int frequency, String node) {
		this.frequency = frequency;
		this.node = node;
		left = null;
		right = null;
		parent = null;
		value = "";
	}
}