/*
6
(A,B,C,D,E,F)
A
0 2 3 3 1 3
2 0 3 0 0 0
3 3 0 3 3 1
3 0 3 0 3 0
1 0 3 3 0 0
3 0 1 0 0 0
output=(A,A,A,A,A,C)
(A,E,B,C,F,D)
10
*/
import java.util.*;
class primsAlg {
	static int source=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine());
		String vertices[] = new String[num+1];
		int matrix[][] = new int[num+1][num+1];
		String str = sc.nextLine();
		
		StringTokenizer st = new StringTokenizer(str,"(,)");
		int i = 0;
		while(st.hasMoreTokens()) {
			vertices[i] = st.nextToken();
			i++;
		}
		String sourceNode = sc.nextLine();
		// System.out.println(num);
		// System.out.println(Arrays.toString(vertices));
		// System.out.println(sourceNode);
		for(i = 1; i <= num ;i++) {
			str = sc.nextLine();
			st = new StringTokenizer(str," ");
			// System.out.println(str);
			int j = 0;
			while(st.hasMoreTokens()) {
				
				matrix[i][j] = Integer.parseInt(st.nextToken());

				j++;
			}
			// System.out.println(Arrays.toString(matrix[i]));
		}
		for(i = 1; i <= num; i++) {
			for(int j = 1; j <= num; j++) {
				if(matrix[i][j] == 0) {
					matrix[i][j] = 999;
				}
			}
			System.out.println(Arrays.toString(matrix[i]));
		}
		primsAlgorithm obj = new primsAlgorithm(num,source);
		obj.addMatrix(matrix);
		for(i = 0; i <num;i++) {
			if(vertices.equals(sourceNode)) {
				source = i+1;
			}
			obj.addVertices(vertices[i],i+1);
		}
		// str = obj.algorithm();
	}
}
class primsAlgorithm {
	int num;
	int source;
	String[] verticesArray;
	int[][] matrix;
	boolean[] visited;
	String[] parent;
	int sum = 0;
	primsAlgorithm(int num,int source) {
		this.num = num;
		this.source = source;
		init();
	}
	public void addMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	public void init() {
		verticesArray = new String[num+1];
		matrix = new int[num+1][num+1];
		visited = new boolean[num+1];
		parent  = new String[num+1];
	}
	public void addVertices(String str, int i) {
		verticesArray[i] = str;
	}
}