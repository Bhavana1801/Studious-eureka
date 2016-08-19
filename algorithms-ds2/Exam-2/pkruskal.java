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
	int[][] adjMatrix;
	char[] vertices;
	int n;
	ArrayList<Node> visited;
	int[] parent;
	int[] rank;

	Algorithm(int n, char[] vertex, int[][] matrix) {
		this.n = n;
		vertices = new char[n];
		adjMatrix = new int[n][n];
		list = new ArrayList<Node>();
		visited = new ArrayList<Node>();
		parent = new int[n];
		rank = new int[n];
		for(int i = 0; i < n; i++) {
			vertices[i] = vertex[i];
			parent[i] = i;
			rank[i] = 0;
			for(int j = 0; j < n; j++) {
				if(i == j)
					adjMatrix[i][j] = 0;
				else
					adjMatrix[i][j] = matrix[i][j];
			}
		}
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
				}
			}
		}
		Collections.sort(list);
		for(int i = 0; i < list.size(); i++) {
			Node temp = list.get(i);
			int p = getIndex(temp.getParent());
			int q = getIndex(temp.getVertex());
			if(union(p,q))
				visited.add(temp);
		}
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
			link(find(i),find(j));
			return true;
		}
		else
			return false;
	}

	public void link(int i, int j) {
		if(rank[i] < rank[j])
			parent[i] = j;
		else {
			parent[j] = i;
			if(rank[i] == rank[j])
				rank[i]++;
		}
	}

	public void print() {
		int sum = 0;
		for(int i = 0; i < visited.size(); i++) {
			Node node = visited.get(i);
			System.out.println("(" + node.getParent() + "," + node.getVertex() + ")");
			sum = sum + node.getCost();
		}
		System.out.println(sum);
	}

}

public class KruskalMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		String str = sc.nextLine();
		char[] vertices = new char[n];
		int i = 0;
		int[][] adjMatrix = new int[n][n];
		
		StringTokenizer st = new StringTokenizer(str,"(,)");
		while (st.hasMoreTokens()){
			vertices[i++] = st.nextToken().charAt(0);
		}

		for(i=0; i < n; i++){
			str = sc.nextLine();
			st = new StringTokenizer(str," ");
			int j = 0;
			while(st.hasMoreTokens()){
				adjMatrix[i][j++] = Integer.parseInt(st.nextToken());
			}
		}

		Algorithm alg = new Algorithm(n,vertices,adjMatrix);
		alg.kruskal();
		alg.print();
	}
}