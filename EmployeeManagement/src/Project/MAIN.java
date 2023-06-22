package Project;

import java.util.Scanner;

public class MAIN {
	Admin a=new Admin();
	User u=new User();
	Methods m=new Methods();

	 public static void logmenu() {
		 Admin a=new Admin();
		 User u=new User();
		 Methods m=new Methods();
		 Scanner sc=new Scanner(System.in);
		 System.out.println("------------------------");
		 System.out.println("|    WELCOME TO EMP     |");
		 System.out.println("------------------------");
		 System.out.println(" PRESS 1 :  FOR ADMIN LOGIN ");
		 System.out.println(" PRESS 2 :  FOR USER LOGIN ");
		 System.out.println(" PRESS 3 :  FOR NEW USER REGISTRATION ");
		 System.out.println("ENTER YOUR CHOICE :");
		 int ch=sc.nextInt();
	        if(ch==1) {
	        	a.Admin();
	        }
	        else if(ch==2) {
	        	u.user();	        }
	        else if(ch==3) {
	        	m.newuser();
	        }
	        else {
	        	System.out.println("INVALID OPTION !!");
	        	MAIN.logmenu();	
	        }
	 }
	public static void main(String[] args) {	
		MAIN.logmenu();		
	}
}
