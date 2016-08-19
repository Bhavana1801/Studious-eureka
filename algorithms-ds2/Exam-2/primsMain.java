/*
*
7
(A,B,C,D,E,F,G)
A
0 2 0 3 3 0 0
2 0 3 0 4 0 0
0 3 0 0 1 8 0
3 0 0 0 0 7 0
3 4 1 0 0 6 0
0 0 8 7 6 0 9
0 0 0 0 0 9 0
output=(A,A,B,A,C,E,F)
(A,B,C,E,D,F,G)
24
*/
import java.util.*;

public class primsMain {

	public static void main(String [] args) {

		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine());
		String vertices[] = new String[num];
		int matrix[][] = new int[num+1][num+1];
		String str = sc.nextLine();

		StringTokenizer st = new StringTokenizer(str,"(,)");
		int i = 0;
		while(st.hasMoreTokens()) {
			vertices[i] = st.nextToken();
			i++;
		}
		// System.out.println(Arrays.toString(vertices));
		
		String sourceNode = sc.nextLine();

		primsAlgorithm obj = new primsAlgorithm(num);

		for( i = 0; i < vertices.length; i++) {
			if (vertices[i].equals(sourceNode)) {
				obj.source = i + 1;
				
			}
			System.out.println("source= "+obj.source+"vertices "+vertices[i]);
			obj.addVertices(vertices[i], i+1);
		}
		for(i = 1; i <= num ;i++) {
			str = sc.nextLine();
			st = new StringTokenizer(str," ");
			// System.out.println(str);
			int j = 1;
			while(st.hasMoreTokens()) {
				
				int temp = Integer.parseInt(st.nextToken());
				matrix[i][j] = obj.insertIntoMatrix(temp,i,j);

				j++;
			}
			// System.out.println(Arrays.toString(matrix[i]));

		}
		
		obj.addMatrix(matrix);

		obj.algorithm();
		// obj.print(str);
	}

}
class primsAlgorithm {
	String[] vertices;
	int num;
	int[][] matrix;
	boolean[] visited;
	String[] parent;
	static int source;
	int sum = 0;
	int min = 9999;
	public primsAlgorithm(int num) {
		this.num = num;
		init();
	}
	public void init() {
		vertices = new String[num+1];
		matrix = new int[num+1] [num+1];
		visited = new boolean[num+1];
		parent = new String[num+1];
	}
	public int insertIntoMatrix(int value, int i, int j) {
			if (value == 0)
				return 99999;
			else
				return value;
			// System.out.println(Arrays.toString(matrix[i]));
		}
	public void addMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	public void addVertices (String vertex, int i) {
		vertices[i] = vertex;
		// System.out.println(Arrays.toString(vertices));
	}

	

	public void algorithm () {
		int v = -1;
		for ( int i = 0; i <= num; i++) {
			visited[i] = false;
			parent[i] = "";
		}
		visited [source] = true;
		parent[source] = vertices[source];
		String printStr = "("+vertices[source];
		for (int i = 0; i < num - 1; i++) {
			min = 99999;
			for ( int src = 1; src <= num; src++) {
				if (visited[src]) {
					for ( int dest = 1; dest <= num; dest++) {
						if (!visited[dest]) {
							if ( min > matrix[src][dest]) {
								min = matrix[src][dest];
								parent[dest] = vertices[src];
								// System.out.println(Arrays.toString(parent));
								v = dest;
							}
						}
						// System.out.println("*"+Arrays.toString(parent));
					}

				}
				
			}
			visited[v] = true;
			sum += min;
			printStr = printStr+ "," + vertices[v];
		}
		print(printStr);
	}

	public void print(String str) {
		// System.out.println("*"+Arrays.toString(parent));
		// System.out.println("*"+Arrays.toString(vertices));
		System.out.print("(");
		for ( int i = 1; i <= num; i++) {
			if (i == num)
				System.out.print(parent[i]);
			else
				System.out.print(parent[i]+",");
		}
		System.out.println(")");
		System.out.println(str+")");
		System.out.println(sum);
	}
}