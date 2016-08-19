import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;
public class DijkstraAlgorithm {
    static int length;
    static String[] vertices;
    static ArrayList<Edge> edges = new ArrayList<Edge>();
    public static void main(String... arg) {
        int adjacency_matrix[][];
        int number_of_vertices;
        String source = "";
        String set_vertices = "";  
        Scanner scan = new Scanner(System.in);
        try {
            number_of_vertices = Integer.parseInt(scan.nextLine());
            adjacency_matrix = new int[number_of_vertices + 1][number_of_vertices + 1];
            source = scan.nextLine();
            set_vertices = scan.nextLine();
            vertices = set_vertices.substring(1,set_vertices.length()-1).split(",");
            length= vertices.length;
            for (int i = 0; i < vertices.length; i++) {
                String[] temp = scan.nextLine().split(",");
                for (int j = 0; j < vertices.length ; j++) {
                    adjacency_matrix[i][j] = Integer.parseInt(temp[j]);
                    if(adjacency_matrix[i][j] == 0) {
                    }else {
                        edges.add(new Edge(vertices[i], vertices[j], adjacency_matrix[i][j]));
                    }
                } 
            }
            
            Dijkstra(vertices[0]);
            
        } catch (InputMismatchException inputMismatch) {
            System.out.println("Wrong Input Format");
        }
    }
    static int[] distance_array;
    static String[] parent_array;
    public static void Dijkstra(String sourceId) {
        distance_array = new int[length];
        Arrays.fill(distance_array, 99999);
        parent_array = new String[length];
        Comparator<Node> compare = new NodeCompare();
        PriorityQueue<Node> node_queue = new PriorityQueue<Node>(10,compare);
        distance_array[index_finder(sourceId)] = 0;
        parent_array[index_finder(sourceId)] = sourceId;
        Node x = new Node(parent_array[index_finder(sourceId)], distance_array[index_finder(sourceId)]);
        node_queue.add(x);
        while(!node_queue.isEmpty()){
            Node u_node = node_queue.poll();
            for(int i=0;i<edges.size();i++) {
                if(edges.get(i).getSource().equals(u_node.getNode())) {
                    if(distance_array[index_finder(u_node.getNode())] + edges.get(i).getWeight() < distance_array[index_finder(edges.get(i).getDestination())]) {
                        distance_array[index_finder(edges.get(i).getDestination())] = distance_array[index_finder(u_node.getNode())] + edges.get(i).getWeight();
                        parent_array[index_finder(edges.get(i).getDestination())] = u_node.getNode();
                        Node x1 = new Node(edges.get(i).getDestination(), distance_array[index_finder(edges.get(i).getDestination())]);
                        node_queue.add(x1);
                    }
                }
            }
        }
        for(int i = 0; i < length; i++){
            int p = 0;
            p = i;
            String reversepath = "";
            String path = "";
            while(parent_array[p]!=null && !parent_array[p].equals(sourceId)) {
                path = path + parent_array[p];
                p = index_finder(parent_array[p]);
            }
            for(int j = path.length() - 1; j >= 0 ; j--){
                reversepath = reversepath +"->"+ path.charAt(j);
            }
            if(i!=0)
                reversepath = sourceId  + reversepath + "->" + vertices[i] + ":" + distance_array[i];
            else
                reversepath = sourceId + reversepath + ":" + distance_array[i];
            if(i!=length)
                System.out.println(reversepath);
            else 
                System.out.print(reversepath);
        }
    }
    private static int index_finder(String ele){
        int temp = 0;
        for(int i = 0; i < vertices.length; i++){
            if(vertices[i].equals(ele)){
                temp = i;
            }
        }
        return temp;
    }
}
class Edge{
    String source = "";
    String destination = "";
    int weight = 0;
    public Edge(String source, String destination, int weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    public String getSource(){
        return this.source;
    } 
    public String getDestination(){
        return this.destination;
    }
    public int getWeight(){
        return this.weight;
    }
    public String toString(){
        return this.source + " " + this.destination + " " + this.weight;
    }
}
class Node{
    String node = "";
    int distance = 0;
    public Node(String node, int distance){
        this.node = node;
        this.distance = distance;
    }
    public String getNode(){
        return this.node;
    }
    public int getDistance(){
        return this.distance;
    }
    public String toString(){
        return this.node + " " + this.distance;
    }
}
class NodeCompare implements Comparator<Node>{
    public int compare(Node node1, Node node2){
        if(node1.getDistance() < node2.getDistance()){
            return -1;
        } else if(node1.getDistance() > node2.getDistance()){
            return 1;
        } else {
            return 0;
        }
    }
}