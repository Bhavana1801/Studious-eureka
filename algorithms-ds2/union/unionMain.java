/*
6
Union(1,2)
Union(3,4)
Check(1,3)
Union(1,5)
Check(2,5)
Union(1,5)
end
Output #1:
(1,2),(3),(4),(5),(6)
(1,2),(3,4),(5),(6)
No
(1,2,5),(3,4),(6)
Yes
There is a Cycle
*/
import java.util.*;

class UnionFindDS {

	int[] parent;
	int[] rank;
	int num;
	public UnionFindDS(int num) {
		this.num = num;
		parent = new int[num+1];
		rank = new int[num+1];
		init();
	}
	public void init() {
		for(int i = 1; i <= num; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	public int check(int i) {
		if (parent[i] ==  i) return i;
		int temp = check(parent[i]);
		parent[i] = temp;
		return temp;
	}
	
	public void link(int i, int j) {
		if (parent[i] != parent[j]) {
			if (rank[i] >= rank[j]) {
				
				parent[j] = i;
				if (rank[i] == rank[j]) {
					rank[i]++;
				}
			}
			else if(rank[i]<rank[j]){
				parent[i] = j;
			}
		}
	}

	public boolean union(int i, int j) {
		int p1 = check(i);
		int p2 = check(j);
		if (p1 != p2) {
			link(p1,p2);
		}
		else {
			System.out.println("There is a Cycle");
			return false;
		}
		return true;
	}

	public void print () {
		for (int i = 1; i < parent.length; i++) {
			String str = "";
			for (int j = 1; j < parent.length; j++) {
				if (parent[j] == i)
					if (str.length() == 0)
						str = str + j;
					else
						str = str+","+j;
			}
			if (str.length() > 0) {
				System.out.print("("+str+"),");
			}
		}
		System.out.println();
	}
}
class unionMain {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine());
		UnionFindDS obj = new UnionFindDS(num);
		while(sc.hasNext()) {
			String str = sc.nextLine();
			if(str.equals("end"))break;
			StringTokenizer st = new StringTokenizer(str,"(),");
			while (st.hasMoreTokens()) {
				str = st.nextToken();
				 {
					if(str.equals("Union")) {
						boolean flag = obj.union(Integer.parseInt(st.nextToken()),
														Integer.parseInt(st.nextToken()));
								if (flag)obj.print(); break;
					}
								
					else if(str.equals("Check")) {
						if (obj.check(Integer.parseInt(st.nextToken())) == obj.check(Integer.parseInt(st.nextToken()))) {
							System.out.println("Yes");
						}
						else {
							System.out.println("No");
						}
					 	break;
					}
						
					else break;
				}
			}
		}
	}	
}