import java.util.*;
import java.text.*;
class MyCalendar {
	 String title;
	 int count;
	 //ArrayList<Appointment> appnList;
	 Appointment[] appnList;
	MyCalendar(String title) {
		this.title = title;
		count = 0;
		appnList = new Appointment[10];
		//appnList = new ArrayList<Appointment>();
	}

	boolean createAppointmentSlot(Appointment ap) {
		if(count == 0) {
			appnList[count] = ap;
			//appnList.add(0,ap);
			count++;
			return true;
		}
		else {
			Date newDate1,newDate2;
			for(int i = 0; appnList[i]!=null; i++) {
				String date1 = appnList[i].date;
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
							if(newDate1.getHours() == newDate2.getHours()) {
								if(newDate1.getMinutes() ==newDate2.getMinutes()) {
									if(newDate1.getSeconds() == newDate2.getMinutes())
										return false;
								}
							}
							
						}
					}
				}
				else
				{
					appnList[count] = ap;
					//appnList.add(count,ap);
					//System.out.println(appnList.get(i));
					count++;
				 	return true;
				 }
			}
		}
		return true;
	}
	
	boolean bookAppointment(String name, String date, int duration, String place) {
		Date newDate1,newDate2;
		//System.out.println("ArrayList"+appnList);
		for(int i = 0; appnList[i]!=null; i++) {
			String date1 = appnList[i].date;
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
							if(appnList[i].duration >= duration) {
								//System.out.println("see"+appnList.get(0).available);
								if(appnList[i].available) {
									//System.out.println("yes");
									appnList[i].available = false;
									return true;
								}
								else
								{
									return false;
								}
							}
						}
						else {
							if(appnList[i].available) {
								appnList[i].available = false;
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
		for(int i = 0; appnList[i]!=null; i++) {

			if(appnList[i].date.equals(date)) {
				if(appnList[i].available == false) {
					appnList[i].available = true;
					return true;
				}
				else
					return false;
			}
		
		}
		return false;
	}
}