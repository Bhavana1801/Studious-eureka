import java.util.*;

class Node {
	private char node;
	private int cost;

	public Node() {
		cost = 0;
	}

	public Node(char node, int cost) {
		this.node = node;
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	public char getNode() {
		return node;
	}

	public int compareTo(Node node) {
		if (this.getCost() < node.getCost())
			return -1;
		if (this.getCost() > node.getCost())
			return 1;
		return 0;
	}
}

class MinHeap {
	Node[] heap;
	int size;
	
	public MinHeap(int n, Node ele) {
		heap = new Node[n];
		size = 0;
	}

	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}
	
	public void add(Node element){
		size++;
		heap[size] = element;
		bubbleUp();
	}
	
	public Node extractMin() {
		return heap[1];
	}
	
	public Node remove() {
		Node n = heap[1];
		heap[1] = heap[size];
		size--;
		bubbleDown();
		return n;
	}

	public void swap(int i , int j) {
		Node temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	public void bubbleUp() {
		int i = size;
		int j = i/2;
		Node temp;
		while(j > 0) {
			if(heap[i].compareTo(heap[j]) < 0) {
				swap(i,j);
				i = j;
				j = i/2;
			} else {
				break;
			}
		}
	}
	
	public void bubbleDown() {
		int i = 1;
		int j = 2*i;
		Node temp;
		while(j <= size) {
			if(j == size) {
				if(heap[i].compareTo(heap[j]) > 0)
					swap(i,j);
					i = j;
					j = 2*i;
			}	else if(heap[i].compareTo(heap[j]) > 0 || heap[i].compareTo(heap[j + 1]) > 0){
				if(heap[j].compareTo(heap[j+1]) > 0) {
					swap(i,j+1);
					i = j + 1;
				} else {
					swap(i,j);
					i = j;
				}
				j = 2 * i;
			} else {
				break;
			}
		}
	}

	public void modify(int i, Node element) {
		heap[i] = element;
		int j;
		if(i==1) {
			bubbleDown();
		} else if(heap[i].compareTo(heap[i/2]) < 0) {
			j = i/2;
			while(j > 0) {
				if(heap[i].compareTo(heap[j]) < 0) {
					swap(i,j);
					i = j;
					j = i/2;
				} else {
					break;
				}
			}
		} else if(heap[i].compareTo(heap[2*i]) > 0 || heap[i].compareTo(heap[2*i + 1]) > 0) {
			j = 2 * i;
			while(j <= size) {
				if(heap[i].compareTo(heap[j]) > 0 || heap[i].compareTo(heap[j + 1]) > 0){
					if(heap[j].compareTo(heap[j+1]) > 0) {
						swap(i,j+1);
					} else {
						swap(i,j);
					}
					i = j;
					j = 2 * i;
				} else {
					break;
				}
			}
		}
	}
}

class ShortestPath {
	private int[] distance;
	private int n;
	private MinHeap priorityQueue;
	private int[][] adjMatrix;
	private char[] parent;
	private char[] vertices;
	private ArrayList<Character> visited;


	ShortestPath(int n, char[] array) {
		distance = new int[n];
		this.n = n;
		priorityQueue = new MinHeap(n,new Node());
		adjMatrix = new int[n][n];
		parent = new char[n];
		visited = new ArrayList<Character>();
		vertices = new char[n];
		init(array);
	}

	private void init(char[] array) {
		for(int i = 0; i < n; i++) {
			vertices[i] = array[i];
		}
		for(int i = 0; i < n; i++) {
			parent[i] = '$';
		}
	}

	private int getIndex(char s) {
		for(int i = 0; i < n; i++) {
			if(vertices[i] == s)
				return i;
		}
		return -1;
	}

	public void dijkstra(int[][] matrix, char source) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				adjMatrix[i][j] = matrix[i][j];
			}
			distance[i] = 99999;
		}
		distance[getIndex(source)] = 0;
		priorityQueue.add(new Node(source,0));
		while(!priorityQueue.isEmpty()) {
			Node node = priorityQueue.remove();
			char p = node.getNode();
			int k = getIndex(p);
			visited.add(p);
			for(int i = 0; i < n; i++) {
				if(!visited.contains(vertices[i])) {
					if(adjMatrix[k][i] != 99999) {
						int alt = distance[k] + adjMatrix[k][i];
						if(alt < distance[i]) {
							distance[i] = alt;
							parent[i] = p;
						}
						priorityQueue.add(new Node(vertices[i],distance[i]));
					}
				}
			}
		}
	}

	public void display(char source){
		String str = "";
		for(int i = 0; i < n; i++) {
			str = "";
			if(vertices[i] == source) {
				System.out.println(source + ":0");
			} else {
				char c = vertices[i];
				int k = getIndex(vertices[i]);
				str = str + vertices[i] + ">-";
				while(k != -1 && parent[k] != source) {
					if(parent[k] == '$')
						break;
					str = str + parent[k] + ">-";
					k = getIndex(parent[k]);
				}
				str = str + source;
				String op = new StringBuffer(str).reverse().toString();
				System.out.println(op + ":" +distance[i]);
			}
		}
	}
}

public class Dijkstra{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		String source = sc.nextLine();
		String str = sc.nextLine();
		char[] vertices = new char[n];
		int inf = 99999;
		int i = 0;
		int[][] adjMatrix = new int[n][n];
		
		StringTokenizer st = new StringTokenizer(str,"(,)");
		while (st.hasMoreTokens()){
			vertices[i++] = st.nextToken().charAt(0);
		}

		for(i=0; i < n; i++){
			str = sc.nextLine();
			st = new StringTokenizer(str,",");
			int j = 0;
			while(st.hasMoreTokens()){
				adjMatrix[i][j++] = Integer.parseInt(st.nextToken());
			}
		}

		for(i=0;i<n;i++) {
			for(int j=0; j<n; j++){
				if(i == j) {
					adjMatrix[i][j] = 0;
					continue;
				}
				if(adjMatrix[i][j] == 0)
					adjMatrix[i][j] = inf;
			}
		}

		ShortestPath sp = new ShortestPath(n,vertices);
		sp.dijkstra(adjMatrix,source.charAt(0));
		sp.display(source.charAt(0));		
	}
}