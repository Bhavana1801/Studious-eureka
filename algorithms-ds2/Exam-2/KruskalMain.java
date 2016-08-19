/*
7
(A,B,C,D,E,F,G)
0 2 0 3 3 0 0
2 0 3 0 4 0 0
0 3 0 0 1 8 0
3 0 0 0 0 7 0
3 4 1 0 0 6 0
0 0 8 7 6 6 9
0 0 0 0 0 9 0

Output #1:
(C,E)
(A,B)
(A,E)
(A,D)
(E,F)
(F,G)
24
*/
import java.util.*;

class Node implements Comparable<Node> {
	private char parent;
	private char vertex;
	private int cost;

	public Node() {

	}

	public Node(char parent, char vertex, int cost) {
		this.parent = parent;
		this.vertex = vertex;
		this.cost = cost;
	}

	public char getParent() {
		return parent;
	}

	public char getVertex() {
		return vertex;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	@Override
	public int compareTo(Node node) {
		if (this.getCost() < node.getCost())
			return -1;
		if (this.getCost() > node.getCost())
			return 1;
		if(this.getParent() < node.getParent())
			return 1;
		if(this.getParent() > node.getParent())
			return -1;
		if(this.getVertex() < node.getVertex())
			return 1;
		if(this.getVertex() > node.getVertex())
			return -1;
		return 0;
	}
}

class Algorithm {
	ArrayList<Node> list;
	static int count = 0;
	int[][] adjMatrix;
	char[] vertices;
	int n;
	ArrayList<Node> visited;
	int[] parent;
	int[] rankArray;

	Algorithm(int n, char[] vertex, int[][] matrix) {
		init(n);
		int cnt = initMatrix();
		for(int i = 0; i < n; i++) {
			vertices[i] = vertex[i];
			parent[i] = i;
			rankArray[i] = 0;
			for(int j = 0; j < n; j++) {
				if(i == j)
					adjMatrix[i][j] = 0;
				else
					adjMatrix[i][j] = matrix[i][j];
			}
		}
	}
	public int initMatrix() {
		return adjMatrix.length;
	}
	public void init(int n) {
		this.n = n;
		vertices = new char[n];
		adjMatrix = new int[n][n];
		list = new ArrayList<Node>();
		visited = new ArrayList<Node>();
		parent = new int[n];
		rankArray = new int[n];
	}

	public void kruskal() {
		int flag;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(adjMatrix[i][j] != 0) {
					flag = 0;
					Node temp = new Node(vertices[i],vertices[j],adjMatrix[i][j]);
					for(int k = 0; k < list.size(); k++) {
						if(temp.getParent() == list.get(k).getVertex() && temp.getVertex() == list.get(k).getParent())
							flag = 1;
					}
					if(flag == 0)
						list.add(temp);
					if(flag>10) {
						count++;
					}
				}
			}
		}
		Collections.sort(list);
		for(int i = 0; i < list.size(); i++) {
			Node temp = list.get(i);

			int p = getIndex(temp.getParent());
			count = getAllData();
			int q = getIndex(temp.getVertex());
			if(union(p,q))
				visited.add(temp);
		}
		print();
	}
	public int getAllData() {
		count++;
		return count;
	}

	private int getIndex(char s) {
		for(int i = 0; i < n; i++) {
			if(vertices[i] == s)
				return i;
		}
		return -1;
	}

	private int find(int i) {
		if(parent[i] == i)
			return i;
		int k = find(parent[i]);
		parent[i] = k;
		return k;
	}

	private boolean union(int i, int j) {
		if(find(i) != find(j)) {
			makeLink(find(i),find(j));
			return true;
		}
		else
			return false;
	}

	public void makeLink(int i, int j) {
		if(rankArray[i] < rankArray[j])
			parent[i] = j;
		else {
			parent[j] = i;
			if(rankArray[i] == rankArray[j]) {
				rankArray[i]++;
			}
			if(rankArray[i] != rankArray[j]) {

			}
				
		}
		if(i==j) {

		}
	}

	public void print() {
		int sum = 0;
		for(int i = 0; i < visited.size(); i++) {
			Node node = visited.get(i);
			finalPrint(node);
			sum = sum + node.getCost();
		}
		System.out.println(sum);
	}
	public void finalPrint(Node node) {
		System.out.println("(" + node.getParent() + "," + node.getVertex() + ")");
	}

}

public class KruskalMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine());
		String str = sc.nextLine();
		char[] vertices = new char[num];
		
		int[][] adjMatrix = new int[num][num];
		int i = 0;
		StringTokenizer st = new StringTokenizer(str,"(,)");
		while (st.hasMoreTokens()){
			vertices[i++] = st.nextToken().charAt(0);
		}

		for(i = 0; i < num; i++){
			str = sc.nextLine();
			st = new StringTokenizer(str," ");
			int j = 0;
			while(st.hasMoreTokens()){
				adjMatrix[i][j++] = Integer.parseInt(st.nextToken());
			}
		}

		Algorithm obj = new Algorithm(num,vertices,adjMatrix);
		int count = obj.getAllData();
		obj.kruskal();
		// obj.print();
	}
}