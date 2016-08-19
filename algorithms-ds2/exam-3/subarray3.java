import java.util.*;
public class subarray3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		int num=0;
		int[] numberArray = new int[100];
		int tmp = 0;
		StringTokenizer st = new StringTokenizer(str,",");
		// st = new StringTokenizer(str,",");
		
		while(st.hasMoreTokens()) {
			numberArray[tmp] = Integer.parseInt(st.nextToken());
			tmp++;
			num++;
		}
		findSum obj = new findSum(num,numberArray);
		obj.getSum();
		obj.print();
	}
}
class findSum {
	int num;
	int[] numberArray;
	int max;
	int startNum;
	int endNum;
	findSum(int num,int[] numberArray) {
		this.num = num;
		this.numberArray = numberArray;
	}
	public void getSum() {
		max = 0;
		max = getInitialMaxSum();
		startNum = 0;
		endNum = num;
		for(int i = 0; i < num; i++) {
			for(int j = 0; j <= i; j++) {
				int temp = 0;
				int k;
				for(k = j; k < num-i+j; k++) {
					temp = temp + numberArray[k];
				}
				if(temp >= max){
					max = temp;
					startNum = j;
					endNum = k;
				}
				if(temp<=max) {
					// System.out.println(max);
				}
			}
		}
		
	}
	public int getInitialMaxSum() {
		int max = 0;
		for(int i = 0; i < num; i++) {
			max = max + numberArray[i];
		}
		return max;
	}
	public void print() {
		if(max>=0) {
			System.out.println(max);
			int i;
			for(i = startNum; i < endNum-1; i++)
				System.out.print(numberArray[i]+",");
			System.out.println(numberArray[i]);
			return;
		}
			System.out.println("0");
			System.out.println("Maximum sum is less than 0.");
	}
}