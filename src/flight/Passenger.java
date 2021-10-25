package flight;

import java.io.*;
import java.util.Scanner;

public class Passenger {
	int age;
	String name;
	String gender;
	String address;
	String passportNumber;
	logIn lg = new logIn(); 
	
	public Passenger(){
		age=0;
		name="";
		gender="";
		address="";
		passportNumber="";
	}
	
	void setAge(int a) {
		age=a;
	}
	void setName(String n) {
		name=n;
	}
	void setGender(String g) {
		gender=g;
	}
	void setAddress(String add) {
		address=add;
	}
	void setPassNumber(String pn) {
		passportNumber=pn;
	}
	
	int getAge() {
		return age;
	}
	String getName() {
		return name;
	}
	String getGender() {
		return gender;
	}
	String getAddress() {
		return address;
	}
	String getPassNumber() {
		return passportNumber;
	}
	
	@SuppressWarnings("resource")
	public String countInd() {
		try {
			File readF = new File("Customer.txt");
			Scanner readIn = new Scanner(readF);
			int co = 0;
			while (readIn.hasNextLine()) {
		        String read_Line = readIn.nextLine();
		        String[] arr1 = read_Line.split(",");
		        co = Integer.valueOf(arr1[0]);
		      }
			co++;
			String indS = Integer.toString(co);
			return indS;
		} catch (FileNotFoundException error) {
			System.out.println("File Read Error");
			return "1";
		}
	}
	
	@SuppressWarnings("resource")
	public void createAccount() {
		boolean checky=true;
		boolean checky1=true;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your Name");
		name=in.nextLine();
		System.out.println("Enter your Age");
		age=in.nextInt();
		in.nextLine();// Due to integer input
		System.out.println("Enter your Gender");
		gender=in.nextLine();
		System.out.println("Enter your Address");
		address=in.nextLine();
		System.out.println("Enter your Passport Number");
		passportNumber=in.nextLine();
		while(checky!=false) {
			try {
				System.out.println("Enter Username");
				lg.userName=in.nextLine();
				checky=customerLog1(lg.userName);
			}
			catch(except1 ex){
				System.out.println(ex);
			}
		}
		System.out.println("Enter Password");
		lg.password=in.nextLine();
		/*while(checky1!=false) {
			try {
				System.out.println("Enter Password");
				lg.password=in.nextLine();
				checky=customerLog1(lg.password);
			}
			catch(except1 ex){
				System.out.println(ex);
			}
		}*/
		String ind = countInd();
		try {
			FileWriter flw = new FileWriter("Customer.txt",true);
			flw.write(ind+","+name+","+age+","+gender+","+address+","+passportNumber+","+lg.userName+","+lg.password+"\n");
			flw.close();
			System.out.println("Successfully wrote to the file.");
	    } 
		catch (IOException e) {
	      System.out.println("File Write Error!!!");
	      e.printStackTrace();
	    }
	}
	
	@SuppressWarnings("resource")
	public boolean customerLog1(String us) {
		try {
			String str;
			File fl = new File("Customer.txt");
			BufferedReader myReader = new BufferedReader(new FileReader(fl));
			while ((str = myReader.readLine()) != null) {
				String [] arr1 = str.split(",");
				if(us.equals(arr1[6])) {
					//lg.userName=us;
					throw new except1("username already exist!");
				}
			}
			lg.userName=us;
			return false;
		}
		catch (IOException ex) {
		      System.out.println("File Reading Error!!!");
		      ex.printStackTrace();
		      return false;
		}
	}
	
	@SuppressWarnings("resource")
	public boolean customerLog2(String ps) {
		try {
			String str;
			File fl = new File("Customer.txt");
			BufferedReader myReader = new BufferedReader(new FileReader(fl));
			while ((str = myReader.readLine()) != null) {
				String [] arr1 = str.split(",");
				if(ps.equals(arr1[7])) {
					//lg.userName=us;
					throw new except1("username already exist!");
				}
			}
			lg.userName=ps;
			return false;
		}
		catch (IOException ex) {
		      System.out.println("File Reading Error!!!");
		      ex.printStackTrace();
		      return false;
		}
	}
	
	@SuppressWarnings("resource")
	public boolean customerLogIn(String us, String ps) {
		boolean userEqual=false;
		try {
			String str;
			File fl = new File("Customer.txt");
			BufferedReader myReader = new BufferedReader(new FileReader(fl));
			while ((str = myReader.readLine()) != null) {
				String [] arr1 = str.split(",");
				if(us.equals(arr1[6]) && ps.equals(arr1[7])) {
					System.out.println("Access Granted");
					lg.userName=us;
					lg.password=ps;
					userEqual=true;
					return true;
				}
			}
		if(!userEqual)
			System.out.println("Access Denied");
			return false;
		}
		catch (IOException ex) {
		      System.out.println("File Reading Error!!!");
		      ex.printStackTrace();
		      return false;
		}
	}
}
