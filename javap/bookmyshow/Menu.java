import java.util.*;
class Menu {
	public static void main(String[] args) {
	  int choice;
		Scanner in=new Scanner(System.in);
		Scanner in1=new Scanner(System.in);
		BookYourShow bshow=new BookYourShow();
		Show sh;
		Patron p;
		String showName,showDate,showTime,custNumb;
		String custName;
		int showSeats;
		do
		{
			System.out.println("1. Add a  show");
			System.out.println("2. get A show");
			System.out.println("3. book a show");
			System.out.println("4. print tickets");
			System.out.println("5. Remove a movie");
			System.out.println("6. Exit");
			System.out.println("Enter a choice:");
			choice=in.nextInt();
			switch(choice)
			{
				case 1: System.out.println("Enter a show name:");
						showName=in1.nextLine();
						System.out.println("Enter a date:");
						showDate=in1.nextLine();
						System.out.println("Enter show time:");
						showTime=in1.nextLine();
						System.out.println("Enter no of seats:");
						showSeats=in.nextInt();
						sh=new Show(showName, showDate, showTime,showSeats);
						bshow.addAShow(sh);
						break;
				case 2: System.out.println("Enter a movie name to search:");
						showName=in1.nextLine();
						bshow.getAShow(showName);
						break;
				case 3: System.out.println("Enter movie name to search");
						showName=in1.nextLine();
						System.out.println("Enter a date:");
						showDate=in1.nextLine();
						System.out.println("Enter show time:");
						showTime=in1.nextLine();
						System.out.println("Enter no of seats:");
						showSeats=in.nextInt();
						System.out.println("Enter your name:");
						custName=in1.nextLine();
						System.out.println("Enter mobile number:");
						custNumb=in1.nextLine();
						System.out.println("ahh");
						p=new Patron(custName,custNumb);
						bshow.bookAShow(showName,showDate,showTime,showSeats,p);
						break;
				case 4: bshow.printTickets();
						break;
				case 5: System.out.println("Enter a movie to remove");
						showName=in1.nextLine();
						bshow.removAMovie(showName);
						break;

 			}
		}while(choice!=6);	
	}
}