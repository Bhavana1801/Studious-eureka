import java.lang.*;
import java.util.*;
class Edge{
    String source = "";
    String destination = "";
    int weight = 0;
    public Edge(String source, String destination, int weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
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
   
    public String toString(){
        return this.node + " " + this.distance;
    }
}
class PathFinder{
    ArrayList<Edge> edges = new ArrayList<Edge>();
    String[] vertices ;
    PathFinder(ArrayList<Edge> ed,String[] ver ){
        System.out.println("came");
         Collections.copy(ed,edges);
         vertices  = new String[ver.length];
         System.arraycopy(ver, 0, vertices, 0, ver.length);
    }
     int[] distance_array;
     String[] parent_array;
    public void Dijkstra(String sourceId,int length) {
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
                if(edges.get(i).source.equals(u_node.node)) {
                    if(distance_array[index_finder(u_node.node)] + edges.get(i).weight < distance_array[index_finder(edges.get(i).destination)]) {
                        distance_array[index_finder(edges.get(i).destination)] = distance_array[index_finder(u_node.node)] + edges.get(i).weight;
                        parent_array[index_finder(edges.get(i).destination)] = u_node.node;
                        Node x1 = new Node(edges.get(i).destination, distance_array[index_finder(edges.get(i).destination)]);
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
    private int index_finder(String ele){
        int temp = 0;
        for(int i = 0; i < vertices.length; i++){
            if(vertices[i].equals(ele)){
                temp = i;
            }
        }
        return temp;
    }

}
class NodeCompare implements Comparator<Node>{
    public int compare(Node node1, Node node2){
        if(node1.distance < node2.distance){
            return -1;
        } else if(node1.distance > node2.distance){
            return 1;
        } else {
            return 0;
        }

    }
}
class ppp {
     static int length;
     static String[] vertices;
     static ArrayList<Edge> edges = new ArrayList<Edge>();
    public static void main(String... arg) {
        
        int adjacency_matrix[][];
        int number_of_vertices;
        String source = "";
        String set_vertices = "";  
        Scanner scan = new Scanner(System.in);
            number_of_vertices = Integer.parseInt(scan.nextLine());
            adjacency_matrix = new int[number_of_vertices + 1][number_of_vertices + 1];
            source = scan.nextLine();
            set_vertices = scan.nextLine();
            vertices = set_vertices.substring(1,set_vertices.length()-1).split(",");
            length= vertices.length;
            // for(int i =0;i<length;i++){
            //      temp_input[i] = scan.nextLine();
            // }
            for (int i = 0; i < vertices.length; i++) {
                String[] temp = scan.nextLine().split(",");
                for (int j = 0; j < vertices.length ; j++) {
                    adjacency_matrix[i][j] = Integer.parseInt(temp[j]);
                    if(adjacency_matrix[i][j] == 0) {

                    }else {
                        Edge obj = new Edge(vertices[i], vertices[j], adjacency_matrix[i][j]);
                        edges.add(obj);
                    }
                } 
            }
            
            PathFinder pf = new PathFinder(edges,vertices);
            pf.Dijkstra(vertices[0],length);
            
        
    }
}