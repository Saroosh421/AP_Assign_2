package flight;

import java.io.*;
import java.util.Scanner;

public class flight {
	
	@SuppressWarnings("resource")
	void searchFlight() {
		boolean userEqual=false;
		Scanner in = new Scanner(System.in);
		String origin="",dest="",date="";
		System.out.println("Enter Origin");
		origin=in.next();
		System.out.println("Enter Destination");
		dest=in.next();
		System.out.println("Enter Date (i.e. format 1/1/2001)");
		date=in.next();
		try {
			String str;
			File fl = new File("searchFlight.txt");
			BufferedReader myReader = new BufferedReader(new FileReader(fl));
			while ((str = myReader.readLine()) != null) {
				String [] arr1 = str.split(",");
				if(origin.equals(arr1[3]) && dest.equals(arr1[4]) && date.equals(arr1[5])) {
					System.out.println("Flight Details");
					System.out.println("Serial: "+arr1[0]);
					System.out.println("Air Craft: "+arr1[1]);
					System.out.println("Airline: "+arr1[2]);
					System.out.println("From: "+arr1[3]);
					System.out.println("To: "+arr1[4]); 
					System.out.println("Date: "+arr1[5]);
					System.out.println("Arrival Time: "+arr1[6]);
					System.out.println("Destination Time: "+arr1[7]);
					System.out.println("Economy Seats: "+arr1[9]+" Price: "+arr1[8]);
					System.out.println("Business Seats: "+arr1[11]+" Price: "+arr1[10]);
					System.out.println("First Class Seats: "+arr1[13]+" Price: "+arr1[12]);
					System.out.println("Type: "+arr1[14]+"\n\n");
					userEqual=true;
				}
			}
		if(!userEqual)
			System.out.println("Flight Not Found!");
		}
		catch (IOException ex) {
		      System.out.println("File Reading Error!!!");
		      ex.printStackTrace();
		}
	}
	
	public String countInd() {
		try {
			File readF = new File("flightReserved.txt");
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
	
	@SuppressWarnings({ "resource", "unused" })
	void bookFlight(String User) {
		boolean userEqual=false;
		int op=0;
		int op1=0;
		Scanner in = new Scanner(System.in);
		try {
			String str;
			File fl = new File("searchFlight.txt");
			BufferedReader myReader = new BufferedReader(new FileReader(fl));
			System.out.println("Enter serial number");
			op=in.nextInt();
			String opS = Integer.toString(op);
			while ((str = myReader.readLine()) != null) {
				String [] arr1 = str.split(",");
				if(opS.equals(arr1[0])) {
					System.out.println("Follwing seats are available");
					System.out.println("Economy Seats: "+arr1[9]+" Price: "+arr1[8]);
					System.out.println("Business Seats: "+arr1[11]+" Price: "+arr1[10]);
					System.out.println("First Class Seats: "+arr1[13]+" Price: "+arr1[12]+"\n\n");
					System.out.println("Which seat would you like you like to book");
					System.out.println("1. Economy");
					System.out.println("2. Business");
					System.out.println("3. First Class");
					System.out.println("Enter 1,2 or 3");
					op1=in.nextInt();
					if(op1==1) {
						int ec=0;
						try {
						   ec = Integer.parseInt(arr1[9]);
						}
						catch (NumberFormatException e)
						{
							System.out.println("String to Int Conversion Error!");
							ec = 0;
						}
						if(ec > 0) {
							fl = new File("searchFlight.txt");
							myReader = new BufferedReader(new FileReader(fl));
							FileWriter flw = new FileWriter("tempy.txt");
							while ((str = myReader.readLine()) != null) {
								String [] arr2 = str.split(",");
								try {
									if(opS.equals(arr2[0]))
										flw.write(arr2[0]+","+arr2[1]+","+arr2[2]+","+arr2[3]+","+arr2[4]+","+arr2[5]+","+arr2[6]+","+arr2[7]+","+arr2[8]+","+--ec+","+arr2[10]+","+arr2[11]+","+arr2[12]+","+arr2[13]+","+arr2[14]+"\n");
									else
										flw.write(arr2[0]+","+arr2[1]+","+arr2[2]+","+arr2[3]+","+arr2[4]+","+arr2[5]+","+arr2[6]+","+arr2[7]+","+arr2[8]+","+arr2[9]+","+arr2[10]+","+arr2[11]+","+arr2[12]+","+arr2[13]+","+arr2[14]+"\n");
							    } 
								catch (IOException e) {
							      System.out.println("File Write Error!!!");
							      e.printStackTrace();
							    }
							}
							flw.close(); 
							
							File flw2 = new File("tempy.txt");
							BufferedReader myR2 = new BufferedReader(new FileReader(flw2));
							FileWriter fl2 = new FileWriter("searchFlight.txt");
							while ((str = myR2.readLine()) != null) {
								String [] arr2 = str.split(",");
								try {
									fl2.write(arr2[0]+","+arr2[1]+","+arr2[2]+","+arr2[3]+","+arr2[4]+","+arr2[5]+","+arr2[6]+","+arr2[7]+","+arr2[8]+","+arr2[9]+","+arr2[10]+","+arr2[11]+","+arr2[12]+","+arr2[13]+","+arr2[14]+"\n");
							    } 
								catch (IOException e) {
							      System.out.println("File Write Error!!!");
							      e.printStackTrace();
							    }
							}
							fl2.close();
							
							String ind2=countInd();
							File flc = new File("searchFlight.txt");
							BufferedReader myR3 = new BufferedReader(new FileReader(flc));
							FileWriter flr = new FileWriter("flightReserved.txt",true);
							while ((str = myR3.readLine()) != null) {
								String [] arr3 = str.split(",");
								try {
									if(opS.equals(arr3[0])) {
										flr.write(ind2+","+User+","+arr3[1]+","+arr3[2]+","+arr3[3]+","+arr3[4]+","+arr3[5]+","+arr3[6]+","+arr3[7]+","+arr3[8]+","+arr3[14]+"\n");
										break;
									}
								}
								catch (IOException ex) {
									System.out.println("File Reading Error!!!");
									ex.printStackTrace();
								}
							}
							flr.close();
							userEqual=true;
							break;
						}
					}
					else if(op1==2) {
						int bs=0;
						try {
						   bs = Integer.parseInt(arr1[11]);
						}
						catch (NumberFormatException e)
						{
							System.out.println("String to Int Conversion Error!");
							bs = 0;
						}
						if(bs > 0) {
							fl = new File("searchFlight.txt");
							myReader = new BufferedReader(new FileReader(fl));
							FileWriter flw = new FileWriter("tempy.txt");
							while ((str = myReader.readLine()) != null) {
								String [] arr2 = str.split(",");
								try {
									if(opS.equals(arr2[0]))
										flw.write(arr2[0]+","+arr2[1]+","+arr2[2]+","+arr2[3]+","+arr2[4]+","+arr2[5]+","+arr2[6]+","+arr2[7]+","+arr2[8]+","+arr2[9]+","+arr2[10]+","+--bs+","+arr2[12]+","+arr2[13]+","+arr2[14]+"\n");
									else
										flw.write(arr2[0]+","+arr2[1]+","+arr2[2]+","+arr2[3]+","+arr2[4]+","+arr2[5]+","+arr2[6]+","+arr2[7]+","+arr2[8]+","+arr2[9]+","+arr2[10]+","+arr2[11]+","+arr2[12]+","+arr2[13]+","+arr2[14]+"\n");
							    } 
								catch (IOException e) {
							      System.out.println("File Write Error!!!");
							      e.printStackTrace();
							    }
							}
							flw.close(); 
							
							File flw2 = new File("tempy.txt");
							BufferedReader myR2 = new BufferedReader(new FileReader(flw2));
							FileWriter fl2 = new FileWriter("searchFlight.txt");
							while ((str = myR2.readLine()) != null) {
								String [] arr2 = str.split(",");
								try {
									fl2.write(arr2[0]+","+arr2[1]+","+arr2[2]+","+arr2[3]+","+arr2[4]+","+arr2[5]+","+arr2[6]+","+arr2[7]+","+arr2[8]+","+arr2[9]+","+arr2[10]+","+arr2[11]+","+arr2[12]+","+arr2[13]+","+arr2[14]+"\n");
							    } 
								catch (IOException e) {
							      System.out.println("File Write Error!!!");
							      e.printStackTrace();
							    }
							}
							fl2.close();
							
							String ind2=countInd();
							File flc = new File("searchFlight.txt");
							BufferedReader myR3 = new BufferedReader(new FileReader(flc));
							FileWriter flr = new FileWriter("flightReserved.txt",true);
							while ((str = myR3.readLine()) != null) {
								String [] arr3 = str.split(",");
								try {
									if(opS.equals(arr3[0])) {
										flr.write(ind2+","+User+","+arr3[1]+","+arr3[2]+","+arr3[3]+","+arr3[4]+","+arr3[5]+","+arr3[6]+","+arr3[7]+","+arr3[8]+","+arr3[14]+"\n");
										break;
									}
								}
								catch (IOException ex) {
									System.out.println("File Reading Error!!!");
									ex.printStackTrace();
								}
							}
							flr.close();
							userEqual=true;
							break;
						}
					}
					else if(op1==3) {
						int fc=0;
						try {
						   fc = Integer.parseInt(arr1[13]);
						}
						catch (NumberFormatException e)
						{
							System.out.println("String to Int Conversion Error!");
							fc = 0;
						}
						if(fc > 0) {
							fl = new File("searchFlight.txt");
							myReader = new BufferedReader(new FileReader(fl));
							FileWriter flw = new FileWriter("tempy.txt");
							while ((str = myReader.readLine()) != null) {
								String [] arr2 = str.split(",");
								try {
									if(opS.equals(arr2[0]))
										flw.write(arr2[0]+","+arr2[1]+","+arr2[2]+","+arr2[3]+","+arr2[4]+","+arr2[5]+","+arr2[6]+","+arr2[7]+","+arr2[8]+","+arr2[9]+","+arr2[10]+","+arr2[11]+","+arr2[12]+","+--fc+","+arr2[14]+"\n");
									else
										flw.write(arr2[0]+","+arr2[1]+","+arr2[2]+","+arr2[3]+","+arr2[4]+","+arr2[5]+","+arr2[6]+","+arr2[7]+","+arr2[8]+","+arr2[9]+","+arr2[10]+","+arr2[11]+","+arr2[12]+","+arr2[13]+","+arr2[14]+"\n");
							    } 
								catch (IOException e) {
							      System.out.println("File Write Error!!!");
							      e.printStackTrace();
							    }
							}
							flw.close(); 
							
							File flw2 = new File("tempy.txt");
							BufferedReader myR2 = new BufferedReader(new FileReader(flw2));
							FileWriter fl2 = new FileWriter("searchFlight.txt");
							while ((str = myR2.readLine()) != null) {
								String [] arr2 = str.split(",");
								try {
									fl2.write(arr2[0]+","+arr2[1]+","+arr2[2]+","+arr2[3]+","+arr2[4]+","+arr2[5]+","+arr2[6]+","+arr2[7]+","+arr2[8]+","+arr2[9]+","+arr2[10]+","+arr2[11]+","+arr2[12]+","+arr2[13]+","+arr2[14]+"\n");
							    } 
								catch (IOException e) {
							      System.out.println("File Write Error!!!");
							      e.printStackTrace();
							    }
							}
							fl2.close();
							
							String ind2=countInd();
							File flc = new File("searchFlight.txt");
							BufferedReader myR3 = new BufferedReader(new FileReader(flc));
							FileWriter flr = new FileWriter("flightReserved.txt",true);
							while ((str = myR3.readLine()) != null) {
								String [] arr3 = str.split(",");
								try {
									if(opS.equals(arr3[0])) {
										flr.write(ind2+","+User+","+arr3[1]+","+arr3[2]+","+arr3[3]+","+arr3[4]+","+arr3[5]+","+arr3[6]+","+arr3[7]+","+arr3[8]+","+arr3[14]+"\n");
										break;
									}
								}
								catch (IOException ex) {
									System.out.println("File Reading Error!!!");
									ex.printStackTrace();
								}
							}
							flr.close();
							userEqual=true;
							break;
						}
					}
				}
			}
		if(!userEqual)
			System.out.println("Flight Not Found!");
		}
		catch (IOException ex) {
		      System.out.println("File Reading Error!!!");
		      ex.printStackTrace();
		}
	}
}
