import java.io.*;
import java.util.*;
public class JhonsonSra {
public static void main(String args[] ) throws Exception {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    Scanner in=new Scanner(System.in);
    int no=Integer.parseInt(in.nextLine());
    String[] v=(in.nextLine()).split(",");
    int[][] c=new int[no][no];
    for(int i=0;i<no;i++){
        String x=in.nextLine();//System.out.println(x);
        String str[]=(x).split(",");
        for(int j=0;j<no;j++){
            c[i][j]=Integer.parseInt(str[j]);//System.out.println("c["+i+"]["+j+"] "+c[i][j]);
        }
    }
    int[][] nc=new int[no+1][no+1];
    for(int i=0;i<nc.length;i++){
        for(int j=0;j<nc.length;j++){
            if(i==0){
                nc[i][j]=0;
            }
            else if(j==0){
                nc[i][j]=999;
            }
            else{
                nc[i][j]=c[i-1][j-1];
            }
           // System.out.print(nc[i][j]+" ");
        }
       // System.out.println();
    }
    String nv[]=new String[v.length+1];
    nv[0]="@";
    for(int i=1;i<nv.length;i++){
        nv[i]=v[i-1];  
    }
    String s="@";
    int[] d=bf(s,nc,no+1,nv);
   /* for(int i=0;i<d.length;i++){
        System.out.println(d[i]);
    }*/
   int[][] rc=reweight(c,d);
   int[][] dc=new int[no][no];
   for(int i=0;i<no;i++){
     int[] dis=dijk(rc,i,no);
     for(int j=0;j<no;j++){
        dc[i][j]=dis[j];//System.out.print(dc[i][j]+" ");
     }
     //System.out.println();
   }
   for(int i=0;i<no;i++){
    String str="";
    for(int j=0;j<no;j++){
        int x=dc[i][j];
        if(x!=999) x=x-d[i+1]+d[j+1];
        str=str+x+",";
    }
    System.out.println(str.substring(0,str.length()-1));
   }

}
    public static int[] bf(String s,int[][] c,int no,String[] v){
        int[] d=new int[no];
        String[] p=new String[no];
        p[getIndex(s,v)]=s;
        for(int i=0;i<d.length;i++){
            d[i]=999;
        }
        d[getIndex(s,v)]=0;
        for(int i=1;i<no-1;i++){
            for(int j=0;j<no;j++){
                for(int k=0;k<no;k++){
                    if(c[j][k]!=999){
                        if(c[j][k]+d[j]<d[k]){
                            d[k]=c[j][k]+d[j];
                            p[k]=v[j];
                         }
                    }
                    
                }
            }
        }
        for(int j=0;j<no;j++){
                for(int k=0;k<no;k++){
                    if(c[j][k]!=0){
                        if(c[j][k]+d[j]<d[k]){
                            System.out.println("Graph contains a negative weight cycles. Can't able to find the shortest path distances between every pair of vertices for the given graph.");
                            System.exit(0);
                         }
                    }
                    
                }
            }
      return d;
        
        
    }
    public static int getIndex(String x,String[] v){
        for(int i=0;i<v.length;i++){
            if(v[i].equals(x)){
                return i;
            }
        }
        return -1;
    }
    public static int[][] reweight(int[][] c,int[] d){
        int[][] nc=new int[c.length][c.length];
        for(int i=0;i<c.length;i++){
            for(int j=0;j<c[i].length;j++){
                int x=c[i][j];
                if(c[i][j]!=999){
                    x=x+d[i+1]-d[j+1];
                }
                nc[i][j]=x;//System.out.print(nc[i][j]+" ");
            }
           // System.out.println();
        }
       return nc;
    }
    public static int[] dijk(int[][] c,int s,int no){
      int[] d=new int[no];
      boolean[] visited=new boolean[no];
      for(int i=0;i<no;i++){
        d[i]=999;
      }
      d[s]=0;
      ArrayList<Integer> q=new ArrayList<Integer>();
      q.add(s);visited[s]=true;
       while(q.size()!=0){
        //System.out.println("hai"+q);
            int u=deleteMin(q,d,no);
            for(int v=0;v<no;v++){
        
                    if(c[u][v]!=999){
                        if(d[u]+c[u][v]<d[v]){
                            d[v]=d[u]+c[u][v];
                            //System.out.println(d[u]+"  "+c[u][v]);
                        }
                        if(!visited[v]){
                            q.add(v);
                            visited[v]=true;
                        }
                        

                    }
                
            }
            //System.out.println();
       }
      
      
     return d; 
    }
    public static int deleteMin(ArrayList<Integer> q,int[] d,int no){

        for(int i=0;i<q.size()-1;i++){
            for(int j=i+1;j<q.size();j++){
                if(d[q.get(i)]>d[q.get(j)]){
                    int temp=q.get(j);
                    q.set(j,q.get(i));
                     q.set(i,temp);
                     

                }
            }
        }
        //System.out.println("...."+q);
      return q.remove(0);
    }
}