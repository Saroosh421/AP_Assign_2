package flight;

import java.util.Scanner;

public class driverClass{
	static Passenger p = new Passenger();
	static Admin a = new Admin();
	static flight f = new flight();
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int op=0;
		while(op!=4) {
			System.out.println("Following are the operations you can perform");
			System.out.println("1. Create Customer Account");
			System.out.println("2. Login Customer Account");
			System.out.println("3. Login Admin Account");
			System.out.println("4. Exit");
			System.out.println("Enter 1,2,3 or 4");
			op = in.nextInt();
			if(op==1) {
				p.createAccount();
			}
			else if(op==2) {
				int op1=0;
				String user="",pass="";
				System.out.println("Enter Username");
				user=in.next();
				System.out.println("Enter Password");
				pass=in.next();
				if(p.customerLogIn(user,pass)){
					System.out.println("Enter 1 to Start Flight Reservation");
					System.out.println("Enter 2 to Search Flight");
					System.out.println("Enter 0 to exit");
					op1 = in.nextInt();
					if(op1==1) {
						f.searchFlight();
						System.out.println("Enter 1 to Book Flight");
						op1 = in.nextInt();
						if(op1==1) {
							f.bookFlight(user);
						}
					}
					else if(op1==2) {
						f.searchFlight();
					}
					else if(op1==3) {
						continue;
					}
				}
			}
			else if(op==3) {
				int op1=0;
				String user="",pass="";
				System.out.println("Enter Username");
				user=in.next();
				System.out.println("Enter Password");
				pass=in.next();
				if(a.AdminLogIn(user, pass)){
					System.out.println("1. Search Flight");
					System.out.println("Enter 1");
					op1 = in.nextInt();
					if(op1==1) {
						f.searchFlight();
					}
				}
			}
			else if(op==4) {
				continue;
			}
		}
	}
}
