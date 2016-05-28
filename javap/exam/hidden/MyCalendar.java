import java.util.*;
import java.text.*;
class MyCalendar {
	 String title;
	 int count;
	 ArrayList<Appointment> appnList;
	MyCalendar(String title) {
		this.title = title;
		count = 0;
		appnList = new ArrayList<Appointment>();
	}

	boolean createAppointmentSlot(Appointment ap) {
		if(count == 0) {
			appnList.add(0,ap);
			count++;
			return true;
		}
		else {
			Date newDate1,newDate2;
			for(int i = 0; i < appnList.size(); i++) {
				String date1 = appnList.get(i).date;
				String date2 = ap.date;
				SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
				newDate1 =new Date();
				newDate2 = new Date();
				try {
					newDate1 = ft.parse(date1);
					newDate2 = ft.parse(date2);
				} catch(Exception e) {
					System.out.println("Exception");
				}
				if(newDate1.getDate() == newDate2.getDate()) {
					if(newDate1.getMonth() == newDate2.getMonth()) {
						if(newDate1.getYear() == newDate2.getYear()) {
							if(newDate1.getHours() == newDate2.getHours())
							return false;
						}
					}
				}
				else
				{
					appnList.add(count,ap);
					//System.out.println(appnList.get(i));
					count++;
				 	return true;
				 }
			}
		}
		return true;
	}
	void print() {
		for(int x= 0; x<appnList.size();x++) {
			//System.out.println(appnList.get(1));
		}
	}
	boolean bookAppointment(String name, String date, int duration, String place) {
		Date newDate1,newDate2;
		//System.out.println("ArrayList"+appnList);
		for(int i = 0; i < appnList.size(); i++) {
			String date1 = appnList.get(i).date;
			String date2 = date;
			SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
			//System.out.println("see"+appnList.get(i).available); 
			newDate1 =new Date();
			newDate2 = new Date();
			try {
				newDate1 = ft.parse(date1);
				newDate2 = ft.parse(date2);
			} catch(Exception e) {
				System.out.println("Exception");
				}
			if(newDate1.getDate() == newDate2.getDate()) {
				if(newDate1.getMonth() == newDate2.getMonth()) {
					if(newDate1.getYear() == newDate2.getYear()) {
						if(newDate1.getHours() == newDate2.getHours()) {
							if(appnList.get(i).duration >= duration) {
								//System.out.println("see"+appnList.get(0).available);
								if(appnList.get(i).available) {
									//System.out.println("yes");
									appnList.get(i).available = false;
									return true;
								}
								else
								{
									return false;
								}
							}
						}
						else {
							if(appnList.get(i).available) {
								appnList.get(i).available = false;
								return true;
							}
							else return true;
						}
					}
				}
			}
		}
		return true;
	}
	boolean cancelAppointment(String date) {
		for(int i = 0; i < appnList.size(); i++) {

			if(appnList.get(i).date.equals(date)) {
				if(appnList.get(i).available == false) {
					appnList.get(i).available = true;
					return true;
				}
				else
					return false;
			}
		
		}
		return false;
	}
}