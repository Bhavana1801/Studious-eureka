import java.util.*;
import java.lang.*;
class BookYourShow {
	Show[] showArray;
	int i;
	int count;
	Show[] temp= new Show[1];
	Patron temp1;
	BookYourShow() {
		showArray=new Show[10];
		i=0;
		count=0;
	}
	public void addAShow(Show oneShow) {
		showArray[i]=oneShow;
		i++;
		count++;
	}
	public void getAShow(String showName) {
		System.out.println("Results matching your search are:");
		for(int x=0;x<count;x++) {
			if(showArray[x].name.compareTo(showName)==0) {
		//int x=0;
				System.out.println((x+1)+")"+showArray[x].name);
				System.out.println("   "+showArray[x].date);
				System.out.println("   "+showArray[x].time);
				System.out.println("   "+showArray[x].seats+"\n");
			}
		}
	}
	public void bookAShow(String showName,String date, String time,int seats,Patron ptn) {
		for(int x=0;x<count;x++) {
			if(showArray[x].name.compareTo(showName)==0) {
				if(showArray[x].date.compareTo(date)==0) {
					if(showArray[x].time.compareTo(time)==0) {
						if(showArray[x].seats>=seats) {
							showArray[x].seats=showArray[x].seats-seats;
							temp[0]=showArray[x];
							temp1=ptn;
							System.out.println("Your tickets are booked.Thank you!!!");
						}
					} 
				} 
			} 
		}
	}
	public void printTickets() {
		System.out.println("Show name:"+temp[0].name);
		System.out.println("Show time:"+temp[0].time);
		System.out.println("Show date:"+temp[0].date);
		System.out.println("Cust Name:"+temp1.pname);
		System.out.println("Cust number:"+temp1.pnumber);
	}
	 public void removAMovie(String rmovie) {
      for (int x = 0; x < count; x++) {
         if (showArray[x].name.compareTo(rmovie) == 0) {
            System.out.println("movie found and Deleted");
            count--;
            for (int k = x; k < count; k++) {
              showArray[k] = showArray[k + 1];
            }
          }
      }
   }
}