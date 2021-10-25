package flight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Admin {
	logIn lg = new logIn();
	
	@SuppressWarnings("resource")
	public boolean AdminLogIn(String us, String ps) {
		boolean userEqual=false;
		try {
			String str;
			File fl = new File("Admin.txt");
			BufferedReader myReader = new BufferedReader(new FileReader(fl));
			while ((str = myReader.readLine()) != null) {
				String [] arr1 = str.split(",");
				if(us.equals(arr1[0]) && ps.equals(arr1[1])) {
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
