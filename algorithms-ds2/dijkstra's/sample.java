import java.util.*;
public class mainD {
	public static void main(String[] args) {
		int num, max = 99999;
		String source,str_vertices = "";
		Scanner sc = new Scanner(System.in);
		//number of vertices
		num = Integer.parseInt(sc.nextLine());
		//source node
		source = sc.nextLine();
		char[] vertices = new char[num];
		int[][] matrix = new int[num][num];
		str_vertices = sc.nextLine();
		StringTokenizer st = new StringTokenizer(str_vertices,"(,) ");
		int i = 0;
		//3rd line of input, reading(s,a,b,c,d)
		while(st.hasMoreTokens()) {
			vertices[i] = st.nextToken().charAt(0);
			i++;
		}
		// System.out.println(num);
		// System.out.println(source);
		// System.out.println(Arrays.toString(vertices));
		int j;
		//values from matrix
		boolean flag=false;
		for(i = 0;i < num;) {
			String str = sc.nextLine();
			st = new StringTokenizer(str,",");
			j = 0;
			while(st.hasMoreTokens()) {
				// System.out.print(st.nextToken()+" ");
				
				matrix[i][j] = Integer.parseInt(st.nextToken());
				// System.out.println(matrix[i][j]);
				if(matrix[i][j] <0) {
					// System.out.println("came");
					flag = true;
				}

				j++;
			}
			// System.out.println(Arrays.toString(matrix[i]));
			i++;
		}
		if(flag==true) {
			System.out.println("the graph contains negative costs, dijkstras cannot be applied");
			return;
		}
		//fill the matrix with 0's and max values i.e., non-self will be infinity
		for(i = 0; i < num ;i++) {
			for(j = 0; j <num;j++) {
				if(i==j) {
					matrix[i][j] = 0;
					continue;
				}
				if(matrix[i][j] == 0) {
					//there is no path between the source and this vertex
					matrix[i][j] = max;
				}
			}
			// System.out.println(Arrays.toString(matrix[i]));
		}
		// System.out.println(Arrays.toString(matrix[0]));
		findShortestPath obj= new findShortestPath(num,vertices);
		obj.dijkstra(matrix,source.charAt(0));
		obj.print(source.charAt(0));

	}
}
class Node {
	public char element;
	public int cost;
	public Node() {
		cost = 0;
	}
	public Node(char element,int cost) {
		this.element = element;
		this.cost = cost;
	}
	public int compareTo(Node node) {
		if(this.cost < node.cost) {
			return -1;
		}
		else if(this.cost > node.cost) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
class minHeap {
	Node[] heap;
	int size;
	public minHeap(int n,Node element) {
		heap = new Node[n];
		size = 0;
	}
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}
	public void insert(Node element) {
		size++;
		heap[size] = element;
		heapifyUp();
	}
	public Node delete() {
		//root element will be min element in minheap
		Node node = heap[1];
		heap[1] = heap[size];
		size--;
		heapifyDown();
		return node;
	}
	// public Node extractMinElement() {
	// 	return heap[1];
	// 	//heap[1] is the root element.
	// }
	public void swap(int i,int j) {
		Node temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;

	}
	public void heapifyUp() {
		int i = size, j = i/2;
		Node tempNode;
		while(j > 0) {
			if(heap[i].compareTo(heap[j]) < 0) {
				swap(i,j);
				i = j;
				j = i/2;
			}
			else {
				break;
			}
		}
	}
	public void heapifyDown() {
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
	public void modify(int i,Node element) {
		int j;
		heap[i] = element;
		if(i==1) {
			heapifyDown();
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
class findShortestPath {
	public int num;
	public int[] dist;
	public char[] parent;
	public char[] vertices;
	public int[][] matrix;
	public minHeap myheap;
	public ArrayList<Character> visited;
	findShortestPath(int num,char[] vertices_array) {
		this.num = num;
		dist = new int[num];
		parent = new char[num];
		vertices = new char[num];
		matrix = new int[num][num];
		myheap = new minHeap(num,new Node());
		visited = new ArrayList<>();
		// initializing the vertices array
		for(int i = 0; i < num; i++) {
			vertices[i] = vertices_array[i];
		}
	}
	
	public int getIndex(char s) {
		for(int i = 0; i <num; i++) {
			if(vertices[i] == s) {
				return i;
			}
		}
		//since -1 cant be index of any element.
		return -1;
	}
	public void dijkstra(int[][] matrix1, char source) {
		for(int i = 0; i < num; i++) {
			for(int j = 0; j < num; j++) {
				matrix[i][j] = matrix1[i][j];
			}
			dist[i] = 99999;
		}
		dist[getIndex(source)] = 0;
		Node node = new Node(source,0);
		myheap.insert(node);
		while(!myheap.isEmpty()) {
			node = myheap.delete();
			String str = node.element+"";
			int k = getIndex(str.charAt(0));
			// System.out.println("*******"+str);
			visited.add(str.charAt(0));
			for(int i = 0; i < num; i++) {
				if(!visited.contains(vertices[i])) {
					if(matrix[k][i] != 99999) {
						//dst will store new distance
						int dst = dist[k] + matrix[k][i];
						if(dst < dist[i]) {
							//uodating the distance array with shortest path
							dist[i] = dst;
							parent[i] = str.charAt(0);
						}
						node = new Node(vertices[i],dist[i]);
						myheap.insert(node);
					}
				}
			}
		}
	}
	public void print(char source){
		String str="";
		for(int i = 0; i < num; i++) {
			
			if(vertices[i] == source) {
				System.out.println(source + ":0");
			} else {
				char vertex = vertices[i];
				int k = getIndex(vertex);
				str = str + vertex + ">-";
				while(k != -1 && parent[k] != source) {
					str = str + parent[k] + ">-";
					k = getIndex(parent[k]);
				}
				str = str + source;
				String finalPath="";
				for(int x = str.length()-1; x >=0;x--) {
					finalPath = finalPath+str.charAt(x)+"";
				}
				System.out.println(finalPath + ":" +dist[i]);
			}
			str="";
		}
	}

}