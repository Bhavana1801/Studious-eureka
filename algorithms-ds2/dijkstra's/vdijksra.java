import java.io.*;
import java.util.*;
class Dijk {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int no_of_vertices = Integer.parseInt(sc.nextLine());
		ArrayList<String> vertices_array = new ArrayList<String>();
		String starting_vertex = sc.nextLine(); 
		String vertices = sc.nextLine();
		StringTokenizer st = new StringTokenizer(vertices,",,(,)");
		int i = 0;
		while(st.hasMoreTokens()){
			vertices_array.add(st.nextToken());
			i++;
		}
		i = 0;
		int adj_matrix[][] = new int[no_of_vertices][no_of_vertices];
		while(i<no_of_vertices){
			String edge_value = sc.nextLine();
			st = new StringTokenizer(edge_value,",");
			int j = 0;
			while(st.hasMoreTokens()){
					adj_matrix[i][j] = Integer.parseInt(st.nextToken());
					j++;
			}
			i++;
		}
		Hashtable<String,Integer> vertices_list = new Hashtable<String,Integer>();;
		Hashtable<String,Integer> weight = new Hashtable<String,Integer>();
		Hashtable<String,String> paths = new Hashtable<String,String>();
		for(int x=0;x<vertices_array.size(); x++){
			vertices_list.put(vertices_array.get(x),100);
		}
		String source = starting_vertex;
		String current = starting_vertex; 
		weight.put(starting_vertex,0);
		paths.put(starting_vertex,"null");
		// System.out.println(weight.contains(sta) + "888888");
		int y=0;
		while(!vertices_list.isEmpty()){
			for(int a = vertices_array.indexOf(current), b=0; b<no_of_vertices; b++){
				if(adj_matrix[a][b]!=0 && vertices_list.containsKey(vertices_array.get(b))==true){
					if(adj_matrix[a][b]<=vertices_list.get(vertices_array.get(b))){
						if(vertices_list.get(vertices_array.get(b)) == 100)
							vertices_list.put(vertices_array.get(b),0);
						int sum = adj_matrix[a][b];
						if(weight.get(current)!=null){
							sum += weight.get(current);
						}
						vertices_list.put(vertices_array.get(b),sum);
						paths.put(vertices_array.get(b),current);
					}
				}
			}
			vertices_list.remove(current);
			Set<String> keys = vertices_list.keySet();
    	Iterator<String> itr = keys.iterator();
			int count = 0,temp =0; String next_cur = "";
    	while (itr.hasNext()) {
    		// System.out.println("inside");
    		String str = itr.next();
    		int val = vertices_list.get(str);
         if(count > 0){
         	if(val < temp){
         		temp = val;
         		next_cur = str;
         	}
         }else {
       		temp = val;
       		next_cur = str;
       	}
       	count++;
    	}
			current = next_cur;
			if(!vertices_list.isEmpty())
				weight.put(current,vertices_list.get(current));
		}
		// System.out.println(weight);
		// System.out.println(paths);
		for(int d = 0; d<vertices_array.size();d++){
			String p = vertices_array.get(d);
			int val = weight.get(vertices_array.get(d));
			if(p.equals(starting_vertex)){
				System.out.println(starting_vertex+":"+val);
			} else if(!paths.containsKey(p)){
				System.out.println(starting_vertex+"->"+p+":"+"99999");
			}else {
				String pt = p;
				String pt1 =  p;;
				while(!paths.get(p).equals("null")){
					pt = paths.get(p);
					if(!p.equals("null"))
						pt1 = pt+"->"+pt1;
					p = pt;
				}			
				System.out.println(pt1+":"+val);
			}
		}
	}
}