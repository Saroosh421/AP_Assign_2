package flight;

public class logIn {
	String userName;
	String password;
	void lonIn() {
		userName="";
		password="";
	}
	void setUserName(String n) {
		userName=n;
	}
	void setPassword(String p) {
		password=p;
	}
	String getName() {
		return userName;
	}
	String getPassword() {
		return password;
	}
	boolean checkUser(String user, String pass) {
		if(userName.equals(user) && password.equals(pass)) {
			return true;
		}
		else {
			return false;
		}
	}
}
