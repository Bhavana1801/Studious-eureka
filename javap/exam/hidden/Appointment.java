class Appointment {
	String date;
	int duration;
	public boolean available = true;
	Appointment(String date,int duration) {
		this.date = date;
		this.duration = duration;
	}
	 public String toString() {
	 	return date + " - "+duration+" mins ";
	 }

}