import java.util.*;

class Node implements Comparator<Node> {
	public int freq;
	public String data;
	public String bits;
	public Node left;
	public Node right;

	public Node() {

	}

	public Node(String data, int freq) {
		this.freq = freq;
		this.data = data;
		bits = "";
		left = null;
		right = null;
	}


	public void makeBits(String str) {
		this.bits = str;
	}

	public int getDecimal() {
		int sum = 0;
		int k = bits.length();
		for(int i = 0; i < k; i++) {
			sum = sum +((Integer.parseInt(Character.toString(bits.charAt(k-i-1)))) * (int)Math.pow(2,i));
		}
		return sum;
	}

	@Override
	public int compare(Node n1, Node n2) {
		return n1.freq-n2.freq;
	}
}

class Tree {
	Node root;
	ArrayList<Node> list;
	PriorityQueue<Node> pq;
	Tree(int n) {
		root = null;
		list = new ArrayList<Node>();
		pq = new PriorityQueue<Node>(n,new Node());
	}

	public void add(Node node) {
		pq.add(node);
	}

	public void algo() {
		Node node;
		while(pq.size() != 1) {
			Node n1 = pq.poll();
			Node n2 = pq.poll();
			int i = n1.freq;
			int j = n2.freq;
			node = new Node((n1.data.concat(n2.data)),i+j);
			node.left=n1;
			node.left=n2;
			pq.add(node);
		}
		root = pq.poll();	
	}
	
	public void inorder(Node node, String s) {
		if(node == null)
			return;
		String p = s + "0";
		inorder(node.left, p);
		node.makeBits(s);
		if(node.data.length() == 1) {
			list.add(node);
		}
		p = s+"1";
		inorder(node.right, p);
	}

	public void print() {
		inorder(root,"");
		sort();
		int sum = 0;
		for(Node node : list) {
			System.out.println(node.data + ": " + node.bits);
			sum = sum + (node.freq * node.bits.length());
		}
		System.out.println(sum);
	}
	
	private void sort() {
		Comparator<Node> comp = new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if(n1.getDecimal() != n2.getDecimal())
					return n1.getDecimal() - n2.getDecimal();
				return n1.bits.length()-n2.bits.length();
			}
		};
		Collections.sort(list,comp);
	}
}

public class phuffman {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		Tree tree = new Tree(n);
		for(int i = 0; i < n; i++) {
			String str = sc.nextLine();
			StringTokenizer st = new StringTokenizer(str," ");
			Node node = new Node(st.nextToken(), Integer.parseInt(st.nextToken()));
			tree.add(node);
		}
		tree.algo();
		tree.print();
	}
}