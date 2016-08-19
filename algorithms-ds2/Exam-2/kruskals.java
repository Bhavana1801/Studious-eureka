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
class algorithm{
    int n;
    Node[] parents;
    String[] array;
    Node[] nodes;
    int[] ranks;
    int matrix[][];
    int count = 1;
    int cost = 0;
    public algorithm(String[] array,int[][] matrix1,int n){
        this.n = n;
        this.array = array;
        init(matrix1);
        init2();
        
    }
    public void init(int[][] matrix1) {
        parents = new Node[n];
        nodes = new Node[n];
        ranks = new int[n];
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(array[i]);
            parents[i] = nodes[i];
            ranks[i] = 0;
            nodes[i].setWeight(0);
           this.matrix = matrix1;
        }

    }
    public void init2() {
        while(count != n){
            int minimum = 9999;
            int s = -1;
            int e = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // System.out.println("in for min= "+minimum);
                    if(matrix[i][j] != 0 && minimum > matrix[i][j]){
                        // System.out.println("i= "+i+"j= "+j+"min= "+minimum+"mat "+matrix[i][j]);
                        minimum = matrix[i][j];
                        
                        s = i;
                        e = j;
                        // System.out.println("s= "+s+"e= "+e);
                    }
                  
                }
            }
            matrix[s][e] = 0;
            matrix[e][s] = 0;
            union(s,e,minimum);
        }
        System.out.println(cost);
    }
    private void union(int s, int e,int minimum) {
        if(!find(s).equals(find(e))){
           print(s,e);
            cost = cost+minimum;
            count++;
            makeLink(find(s),find(e));
        }
    }
    public void print(int s,int e) {
         System.out.println("("+nodes[s].getValue()+","+nodes[e].getValue()+")");
    }
    private void makeLink(Node n1, Node n2) {
        if(ranks[getIndex(n1, nodes)] < ranks[getIndex(n2, nodes)]) {
             parents[getIndex(n1, nodes)] = n2;
             // System.out.println("*"+n1.getValue());
        }
           
        else{
            parents[getIndex(n2, nodes)] = n1;
            if(ranks[getIndex(n1, nodes)] == ranks[getIndex(n2, nodes)]){
                ranks[getIndex(n1, nodes)]++;
            }
             // System.out.println("**"+n1.getValue());
        }
    }
    private Node find(int i) {
        if(parents[i].equals(nodes[i]))
            return nodes[i];
        else{
            Node temp = find(getIndex(parents[i],nodes));
            parents[i] = temp;
            return temp;
        }
    }
    private int getIndex(Node node,Node[] array) {
        for (int j = 0; j < n; j++) {
            if(array[j].equals(node))
                return j;
        }
        return -1;
    }

}

public class kruskals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());       
        String s = sc.nextLine();   
        // System.out.println(num+s);     
        String[] array = s.substring(1, s.indexOf(")")).split(",");
        int[][] matrix = new int[num][num]; 
        StringTokenizer st;              
        for(int i = 0; i < num ;i++) {
            String str = sc.nextLine();
            st = new StringTokenizer(str," ");
            // System.out.println(str);
            int j = 0;
            while(st.hasMoreTokens()) {
                
                int temp = Integer.parseInt(st.nextToken());
                matrix[i][j] = temp;

                j++;
            }
            // System.out.println(Arrays.toString(matrix[i]));

        }
        algorithm obj = new algorithm(array,matrix,num);
 
    }
}

class Node{
    String value = "";
    int weight ;
    public Node(String a){
        value = a;
        weight= 0;
    }
    public int getWeight() {
        return weight;
    }
    public String getValue() {
        return value;
    }
    public void setWeight(int a) {
        weight = a;
    }
}