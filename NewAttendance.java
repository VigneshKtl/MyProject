package Interface;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class StaffDetails {
	private String Staffname;
	private String Department;

	public String getStaffname() {
		return Staffname;
	}

	public void setStaffname(String staffname) {
		Staffname = staffname;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public StaffDetails(String staffname, String department) {
		super();
		Staffname = staffname;
		Department = department;
	}
}

class StudentDetails {
	private String Studentname;
	private String Domain;

	public StudentDetails(String studentname, String domain) {
		Studentname = studentname;
		Domain = domain;
	}

	public String getStudentname() {
		return Studentname;
	}

	public void setStudentname(String studentname) {
		Studentname = studentname;
	}

	public String getDomain() {
		return Domain;
	}

	public void setDomain(String domain) {
		Domain = domain;
	}

	@Override
	public String toString() {
		return "Studentname:" + Studentname + ", Domain:" + Domain;
	}
}

class Attendance {
	private int Id;
	private String Studentname;
	private LocalDate Date;
	private String Attendance;
	private LocalTime ClockIn;
	private LocalTime ClockOut;
	private String TotalDuration;

	public Attendance(int id, String studentname, LocalDate date, String attendance, LocalTime clockIn,
			LocalTime clockOut, String totalDuration) {
		Id = id;
		Studentname = studentname;
		Attendance = attendance;
		Date = date;
		ClockIn = clockIn;
		ClockOut = clockOut;
		TotalDuration = totalDuration;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getStudentname() {
		return Studentname;
	}

	public void setStudentname(String studentname) {
		Studentname = studentname;
	}

	public String getAttendance() {
		return Attendance;
	}

	public void setAttendance(String attendance) {
		Attendance = attendance;
	}

	public LocalDate getDate() {
		return Date;
	}

	public void setDate(LocalDate date) {
		Date = date;
	}

	public LocalTime getClockIn() {
		return ClockIn;
	}

	public void setClockIn(LocalTime clockIn) {
		ClockIn = clockIn;
	}

	public LocalTime getClockOut() {
		return ClockOut;
	}

	public void setClockOut(LocalTime clockOut) {
		ClockOut = clockOut;
	}

	public String getTotalDuration() {
		return TotalDuration;
	}

	public void setTotalDuration(String totalDuration) {
		TotalDuration = totalDuration;
	}

}

class AttendanceSystem {
	Scanner sc = new Scanner(System.in);
	Map<Integer, StudentDetails> sr;
	Map<Integer, StaffDetails> st;
	List<Attendance> ad;

	// Constructor to initialize the student records map

	public AttendanceSystem() {
		sr = new HashMap<>();
		st = new HashMap<>();
		ad = new ArrayList<>();
		sr.put(514, new StudentDetails("Vignesh", "Java"));
		sr.put(515, new StudentDetails("Prasanth", "Python"));
		st.put(101, new StaffDetails("Mansoor", "Java"));
		st.put(102, new StaffDetails("Prabha", "Java"));
	}

	// Method to display current student records
	public void studentRecords() {
		Set<Map.Entry<Integer, StudentDetails>> srs = sr.entrySet();
		String header = String.format("%-15s |%-15s|%-15s", "Student_ID", "Student_Name", "Doamin");
		System.out.println(header);
		System.out.println("---------------------------------------------");
		for (Map.Entry<Integer, StudentDetails> entry : srs) {
			String content = String.format("%-15s |%-15s|%-15s", entry.getKey(), entry.getValue().getStudentname(),
					entry.getValue().getDomain());
			System.out.println(content);
		}
	}

	// Method to add new student details
	public void addStuDetails() {
		System.out.println("How many student details do you want to add?");
		int n = sc.nextInt();
		for (int i = 1; i <= n; i++) {
			System.out.println(" Enter " + i + " Student ID: ");
			int id = sc.nextInt();
			System.out.println(" Enter " + i + " Student Name: ");
			String name = sc.next();
			System.out.println(" Enter " + i + " Student Domain: ");
			String dom = sc.next();
			// Add the student details to the map
			sr.put(id, new StudentDetails(name, dom));
		}
	}

	// Placeholder method for future implementations
	public void morningAddentance() {
		Set<Map.Entry<Integer, StudentDetails>> srs = sr.entrySet();
		System.out.println("\n" + "Enter Today's Date(dd/mm/yyyy)" + "\n");
		System.out.print("Date:");
		int d = sc.nextInt();
		System.out.print("Month:");
		int m = sc.nextInt();
		System.out.print("Year:");
		int y = sc.nextInt();
		int no = 1;
		for (Map.Entry<Integer, StudentDetails> entry : srs) {
			String name = entry.getValue().getStudentname();
			int id = entry.getKey();
			System.out.println("If " + name + " was present press 1 press 0 if not");
			int p = sc.nextInt();
			if (p == 1) {
				String a = "Present";
				// Attendance(String studentname, LocalDate date, String attendance,LocalTime
				// clockIn, LocalTime clockOut,
				// String totalDuration)
				ad.add(new Attendance(id, name, LocalDate.of(y, m, d), a, LocalTime.now(), null, null));
			}
			if (p == 0) {
				String a = "Absent";
				ad.add(new Attendance(id, name, LocalDate.of(y, m, d), a, null, null, null));
			}
			no++;
		}

		System.err
				.println("\n" + "REMAINDER:DON'T FORGET TO ADD " + d + "/" + m + "/" + y + " EVENING ATTENDANCE" + "\n");
	}

	public void eveningAddentance() {
		System.out.println("\n" + "Enter Today's Date(dd/mm/yyyy)" + "\n");
		System.out.print("Date:");
		int d = sc.nextInt();
		System.out.print("Month:");
		int m = sc.nextInt();
		System.out.print("Year:");
		int y = sc.nextInt();
		LocalDate date = LocalDate.of(y, m, d);
		for (Attendance entry : ad) {
			int id = entry.getId();
			String name = entry.getStudentname();
			if (entry.getAttendance().equalsIgnoreCase("Present") && entry.getDate().equals(date)) {
				System.out.println("Enter " + name + " ClockOut Time");
				System.out.println("Hour (0-23): ");
				int h = sc.nextInt();
				System.out.println("Minutes (0-59): ");
				int mi = sc.nextInt();

				if (h < 0 || h > 23 || mi < 0 || mi > 59) {
					System.err.println("Invalid time input. Please enter valid hours and minutes.");
					continue;
				}
				entry.setClockOut(LocalTime.of(h, mi));

				Duration du = Duration.between(entry.getClockOut(), entry.getClockIn());
				int hr = du.toHoursPart();
				int mt = du.toMinutesPart();
				entry.setTotalDuration(hr + ":" + mt);
			}
		}
	}

	public void viewAttendance() {
		String header = String.format("%-15s | %-10s | %-15s | %-10s | %-10s | %-10s | %-10s |", "ID", "Name", "Date",
				"Attendance", "Clock In", "Clock Out", "Total Duration");
		System.out.println(header);
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------");

		for (Attendance entry : ad) {
			DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
			int id = entry.getId();
			String name = entry.getStudentname();
			LocalDate date = entry.getDate();
			String attendance = entry.getAttendance();
			LocalTime cin = entry.getClockIn();
			String c1 = (cin != null) ? f.format(cin) : "N/A";
			LocalTime cout = entry.getClockOut();
			String c2 = (cout != null) ? f.format(cout) : "N/A";
			String duration = (entry.getTotalDuration() != null) ? entry.getTotalDuration() : "N/A";
			String content = String.format("%-15s | %-10s | %-15s | %-10s | %-10s | %-10s | %-10s |", id, name, date,
					attendance, c1, c2, duration);
			System.out.println(content);
		}
	}

	public void dateVice() {

		Set<Map.Entry<Integer, StudentDetails>> s = sr.entrySet();
		System.out.println("Enter Which Date Records You Want (dd/mm/yyyy): ");
		System.out.print("Date: ");
		int d = sc.nextInt();
		System.out.print("Month: ");
		int m = sc.nextInt();
		System.out.print("Year: ");
		int y = sc.nextInt();
		LocalDate date = LocalDate.of(y, m, d);
		System.out.println("------------" + d + "/" + m + "/" + y
				+ "--------------");
		String header = String.format("%-15s | %-10s | %-15s | %-10s | %-10s | %-10s | %-10s", "Student_ID",
				"Student_Name", "Domain", "Attendance", "Clock_IN", "Clock_Out", "Total Duration");

		System.out.println(header);
		for (Attendance entry : ad) {
			for (Map.Entry<Integer, StudentDetails> en : s) {
				if (entry.getDate().equals(date)) {
					if (en.getKey() == entry.getId()) {
						String record = String.format("%-15s | %-10s | %-15s | %-10s | %-10s | %-10s | %-10s ",
								entry.getId(), entry.getStudentname(), en.getValue().getDomain(), entry.getAttendance(),
								entry.getClockIn(), entry.getClockOut(), entry.getTotalDuration());
						System.out.println(record);
					}
				}
			}
		}

	}

	public void nameVice() {

		Set<Map.Entry<Integer, StudentDetails>> s = sr.entrySet();
		System.out.print("Enter Which Student's Records You Want: ");
		String stu = sc.next();
		String header = String.format("%-15s | %-10s | %-15s | %-10s | %-10s | %-10s | %-10s | %-15s", "Date",
				"Student_ID", "Student_Name", "Domain", "Attendance", "Clock_IN", "Clock_Out", "Total Duration");
		System.out.println(header);
		System.out
				.println("-------------------------------------------------------------------------------------------");
		for (Attendance entry : ad) {
			for (Map.Entry<Integer, StudentDetails> en : s) {
				if (entry.getStudentname().equals(stu)) {
					if (en.getKey() == entry.getId()) {
						String record = String.format("%-15s | %-10s | %-15s | %-10s | %-10s | %-10s | %-10s | %-15s",
								entry.getDate(), entry.getId(), entry.getStudentname(), en.getValue().getDomain(),
								entry.getAttendance(), entry.getClockIn(), entry.getClockOut(),
								entry.getTotalDuration());
						System.out.println(record);
					}
				}
			}
		}

	}

}

class AttendanceManagment extends AttendanceSystem{
	public void Staff() {
		Scanner sc = new Scanner(System.in);
		AttendanceManagment as = new AttendanceManagment();

		System.out.println("---------------Attendance Managment System----------------------");
		System.out.println("\n" + "Current student details" + "\n");
		as.studentRecords();
		boolean pro = true;
		while (pro) {

			System.out.println("If You Want Add New Student Tap A" + "\n");
			System.out.println("To Add Morning Attendance Tab M" + "\n");
			System.out.println("To Add Evening Attendance Tab E" + "\n");
			System.out.println("To Check the Attendance Record Tab R" + "\n");
			System.out.println("To Close From This Tab X");
			String a = sc.next();
			switch (a.toUpperCase()) {
			case "A":
				addStuDetails();
				studentRecords();
				pro = true;
				break;
			case "M":
				System.out.println("\n" + "_______|MORNING ATTENDANCE|_______" + "\n");
				morningAddentance();
				System.out.println("\n" + "_______|MORNING ATTENDANCE RECORDS|_______" + "\n");
				viewAttendance();

				pro = true;
				break;
			case "R":
				
				System.out.println("\n"+"Press 1 for name vice records" + "\n" + "Press 2 for date vice records" + "\n"
						+ "Press 3 to view toal records"+"\n");
				int r = sc.nextInt();
				switch(r) {
				case 1:
					System.out.println("_______|NAME VICE ATTENDANCE RECORDS|_______" + "\n");
					nameVice();
					break;
				case 2:
					System.out.println("_______|DATE VICE ATTENDANCE RECORDS|_______" + "\n");
					dateVice();
					break;
				case 3:
					System.out.println("_______|TOTAL ATTENDANCE RECORDS|_______" + "\n");
					viewAttendance();
					break;
				}
				pro = true;
				break;
			case "E":
				System.out.println("\n" + "_______|EVENING ATTENDANCE|_______" + "\n");
				eveningAddentance();
				System.out.println("\n" + "_______|EVENING ATTENDANCE RECORDS|_______" + "\n");
				viewAttendance();
				pro = true;
				break;
			case "X":
				pro = false;
			}

		}
	}

	public void student() {
		Scanner sc=new Scanner(System.in);
		AttendanceSystem as = new AttendanceSystem();
		System.out.println("\n"+"Students Can Only Check the Previous Attendance Records"+"\n");
		boolean pro=true;
		while(pro) {
		System.out.println("Press 1 for name vice records" + "\n" + "Press 2 for date vice records" + "\n"+"Press 3 to close from this tab");
		int r = sc.nextInt();
		switch(r) {
		case 1:
			System.out.println("_______|NAME VICE ATTENDANCE RECORDS|_______" + "\n");
			nameVice();
			pro=true;
			break;
		case 2:
			System.out.println("_______|DATE VICE ATTENDANCE RECORDS|_______" + "\n");
			dateVice();
			pro=true;
			break;
		case 3:
			pro=false;
			break;
		}
		}
}
}
public class NewAttendance {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		NewAttendance na = new NewAttendance();
		AttendanceSystem as = new AttendanceSystem();
		AttendanceManagment am=new AttendanceManagment();
		System.out.println("---------------/Attendance Managment System/----------------------" + "\n");
		System.out.println("---SIGN IN PLEASE---:)" + "\n");
				boolean pro=true;
		while(pro) {
			System.out.print("Enter Your Username:");
			String name = sc.next();
			System.out.print("Enter Your Pin(Your ID is Your Pin):");
			int pin = sc.nextInt();
			Set<Map.Entry<Integer, StudentDetails>> std = as.sr.entrySet();
			Set<Map.Entry<Integer, StaffDetails>> sfd = as.st.entrySet();

		for (Map.Entry<Integer, StudentDetails> entry1 : std) {
			for (Map.Entry<Integer, StaffDetails> entry2 : sfd) {
				if (name.equals(entry1.getValue().getStudentname()) && pin == entry1.getKey()) {
					am.student();
					
				
				}
				if (name.equals(entry2.getValue().getStaffname()) && pin == entry2.getKey()) {
					am.Staff();
					
					
				} else {
					System.err.println("Your user or pin are incorrect plese enter correct one");
				pro=true;
				break;
				}
			}

		}
		
		System.out.println("\n"+"To Close the Application Enter E "+"\n");
		System.out.println("\n"+"To Continue Enter C"+"\n");
		String c=sc.next();
		if(c.equalsIgnoreCase("E")) {
			System.out.println("\n"+"-------------------Thank You-----------"+"\n");
			pro=false;
		}
		if(c.equalsIgnoreCase("C")) {
		pro=true;
		}
	}
	
	}
}


