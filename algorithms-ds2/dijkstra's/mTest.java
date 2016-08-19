import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Test {
    public static int adjacency[][];
    String parent[];
    String vertices[];
    int distance[];
    static int num;
    String source;
    Queue<String> que=new LinkedList<String>();
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        Test dj=new Test();
        dj.num=Integer.parseInt(scan.nextLine());
        dj.source=scan.nextLine();
        String verticesInput = scan.nextLine();//vertices names
       String verti = verticesInput.substring(1,verticesInput.length()-1);//removing () from (s,a,b,c,d,e,f) 
//        String vertices[] = verti.split(",");
        dj.vertices=new String[dj.num];
        dj.vertices=verti.split(",");
        adjacency = new int[dj.num][dj.num];
        ;
//        System.out.println(Arrays.toString(dj.vertices));
          for(int i = 0; i < dj.num; i++) {
                String[] cov =  scan.nextLine().split(",");    
//                System.out.println(Arrays.toString(cov));
                //System.out.println(Integer.parseInt(cov[0]));
                for(int j= 0; j<dj.num; j++) {
                    adjacency[i][j] = Integer.parseInt(cov[j]);
                    //System.out.println((j ));
            }
        }
          
       dj.Dijkstra();
//        dj.display();
    }
//    public void display(){
//        for(int i=0;i<num;i++){
////            for(int j=0;j<num;j++){
////                System.out.println();
////            }
////            System.out.println();
//            System.out.println(distance[i]);
//        }
//    }
    public String deleteMin(){
        int min=9999;
        String temp="";
        for(String str: que){
            int i=getIndex(str);
            if(distance[i]<min){
                min=distance[i];
                temp=str;
            }
        }
        return temp;
    }
    public int getIndex(String str){
        int index=0;
        for(int i=0;i<adjacency.length;i++){
            if(vertices[i].equals(str)){
                index=i;
            }
        }
        return index;
    }
    ArrayList<String> arrayList=new ArrayList<String>();
    public void Dijkstra(){
        String[] pathMatrix = new String[num];
        parent=new String[num];
        distance=new int[num];
        for(int i=0;i<num;i++){
            if(vertices[i].equals(source)){
                parent[i]=source;
                distance[i]=0;
                que.add(source);
                pathMatrix[i] = source;
            }
            else {
                parent[i]="";
                distance[i]=9999;
            }    
        }
        while(que.size()!=0){
            String str=deleteMin();
            que.remove(str);
            arrayList.add(str);
            int i=getIndex(str);
            for(int j=0;j<adjacency.length;j++){
                if(adjacency[i][j]!=0){
                    if(distance[i]+adjacency[i][j]<distance[j]){
                        distance[j]=distance[i]+adjacency[i][j];
                        parent[j]=str;
                        if(!que.contains(vertices[j])){
                            que.add(vertices[j]);
                            //System.out.println(parent[j]);
                            pathMatrix[i] = pathMatrix[i] + "->" +vertices[i];
                        }
                        
                    }
                }
            }
            
    }
        for(int k = 0;k < distance.length; k++) {
           if(distance[k] == 9999){
               System.out.println(source + "->" + vertices[k] + ":99999");
           }else{
               System.out.println(pathMatrix[k] + ":" +distance[k] );
           }

    }
    }
}