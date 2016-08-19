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

(A,D)

(A,E)

(E,F)

(F,G)

24
*/
import java.util.ArrayList;
import java.util.Scanner;
class union_find1{
    int n;
    Node[] parents;
    Node[] nodes;
    int[] ranks;
    int matrix[][];
    int count = 1;
    int cost = 0;
    public union_find1(String[] array,int[][] matrix1){
        n = matrix1.length;
        parents = new Node[n];
        nodes = new Node[n];
        ranks = new int[n];
        matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(array[i]);
            parents[i] = nodes[i];
            ranks[i] = 0;
            nodes[i].weight = 0;
            for (int j = 0; j < n; j++) {
                matrix[i][j] = matrix1[i][j];
            }
        }
        
        while(count != n){
            int minimum = 9999;
            int s = -1;
            int e = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(matrix[i][j] != 0 && minimum > matrix[i][j]){
                        minimum = matrix[i][j];
                        s = i;
                        e = j;
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
            System.out.println("("+nodes[s].value+","+nodes[e].value+")");
            cost = cost+minimum;
            count++;
            link(find(s),find(e));
        }
    }
    private void link(Node n1, Node n2) {
        if(ranks[getIndex(n1, nodes)] < ranks[getIndex(n2, nodes)])
            parents[getIndex(n1, nodes)] = n2;
        else{
            parents[getIndex(n2, nodes)] = n1;
            if(ranks[getIndex(n1, nodes)] == ranks[getIndex(n2, nodes)]){
                ranks[getIndex(n1, nodes)]++;
            }
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
        int n = sc.nextInt();       //number of nodes
        String s = sc.next();        //names of nodes
        String[] array = s.substring(1, s.indexOf(")")).split(",");
        int[][] matrix = new int[n][n];                //Enter the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        union_find1 u = new union_find1(array,matrix);
    }
}

class Node{
    String value = "";
    int weight ;
    public Node(String a){
        value = a;
        weight= 0;
    }
}