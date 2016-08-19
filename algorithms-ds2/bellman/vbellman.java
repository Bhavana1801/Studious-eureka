import java.io.*;
import java.util.*;
class BellmanFord {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int no_of_vertices = Integer.parseInt(sc.nextLine());
		ArrayList<String> vertices_array = new ArrayList<String>();
		String starting_vertex = sc.nextLine(); 
		String vertices = sc.nextLine();
		StringTokenizer st = new StringTokenizer(vertices,",");
		int i = 0;
		while(st.hasMoreTokens()){
			vertices_array.add(st.nextToken());
			i++;
		}
		i = 0;
		int adj_matrix[][] = new int[no_of_vertices][no_of_vertices];
		Hashtable<String,Integer> vertices_path = new Hashtable<String,Integer>();;
		Hashtable<String,Integer> weight = new Hashtable<String,Integer>();
		Hashtable<String,String> paths = new Hashtable<String,String>();
		while(i<no_of_vertices) {
			String edge_value = sc.nextLine();
			st = new StringTokenizer(edge_value,",");
			int j = 0;
			while(st.hasMoreTokens()){
					adj_matrix[i][j] = Integer.parseInt(st.nextToken());
					if(adj_matrix[i][j]!=0){
						vertices_path.put(vertices_array.get(i)+"->"+vertices_array.get(j),adj_matrix[i][j]);
					}
					j++;
			}
			if(vertices_array.get(i).equals(starting_vertex))
				weight.put(vertices_array.get(i),0);
				else
				weight.put(vertices_array.get(i),Integer.MAX_VALUE);
			i++;
		}
		i = 0;
		boolean flg = false;
		while(i<=no_of_vertices){
			Set<String> keys = vertices_path.keySet();
    	Iterator<String> itr = keys.iterator();
    	while (itr.hasNext()) {
    		String u="",v="";
    		String str = itr.next();
    		int val = vertices_path.get(str);
    		st = new StringTokenizer(str, "->");
    		while(st.hasMoreTokens()){
    			u = st.nextToken();
    			v = st.nextToken();
    		}

    		if(weight.get(u)!=Integer.MAX_VALUE){
	    		if(weight.get(v) > weight.get(u) + adj_matrix[vertices_array.indexOf(u)][vertices_array.indexOf(v)] && i<=no_of_vertices-1){
	    			weight.put(v, weight.get(u) + adj_matrix[vertices_array.indexOf(u)][vertices_array.indexOf(v)]);
	    			paths.put(v,u);
	    		}
	    		if(i== no_of_vertices){
	    			if(weight.get(v) > weight.get(u) + adj_matrix[vertices_array.indexOf(u)][vertices_array.indexOf(v)]){
	    				flg = true;
	    			}
	    		}
	    	}
    	}
    	i++;
		}
		for(int d = 0; d<vertices_array.size(); d++){
			if(flg == true){
				System.out.println("Graph contains a negitive weight cycles.");
				break;
			}
			String p = vertices_array.get(d);
			int vall = weight.get(p);
			if(p.equals(starting_vertex)){
				System.out.println(starting_vertex+"->"+starting_vertex+":"+vall);
			} else if(!paths.containsKey(p)){
				System.out.println(starting_vertex+"->"+p+":"+"99999");
			} else {
				String pt = p;
				String pt1 = p;
				while(!p.equals(starting_vertex)){
					pt = paths.get(p);
					pt1 = pt+"->"+pt1;
					p = pt;
				}
				System.out.println(pt1+":"+vall);
			}
		}
	}
}