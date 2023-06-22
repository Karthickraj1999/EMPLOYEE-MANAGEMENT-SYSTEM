package Project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class DBcode {	
	int id;
	private Connection con;
	Scanner sc=new Scanner(System.in);
	//Connectivity
	public DBcode() {
		String dburl="jdbc:mysql://localhost:3306/project";
		String dbuser="root";
		String dbpass="";		
		try {
		con=DriverManager.getConnection(dburl,dbuser,dbpass);	
		}
		catch(SQLException e) {	
			System.out.println("Error : "+e);	
		}	
	}	
	//Menu for login 
	public void logmenu() {
	
		System.out.println("------------------------");
		System.out.println("|    WELCOME TO EMP     |");
		System.out.println("------------------------");
		System.out.println("PRESS 1 :  FOR ADMIN LOGIN ");
		System.out.println("PRESS 2 :  FOR USER LOGIN ");
		System.out.println("PRESS 3 :  FOR NEW USER REGISTRATION ");
		System.out.println("ENTER YOUR CHOICE :");
		int ch=sc.nextInt();

	// admin	
		if(ch==1) {
			System.out.println();
			System.out.print("ENTER YOUR USERNAME  : ");
			String u=sc.next();
			System.out.print("ENTER YOUR PASSWORD : ");
			String p=sc.next();
			
			adminlogin( u, p);
			
			if(adminlogin(u, p)) {
				
				while(true) {
					System.out.println();
					System.out.println("	WELCOME BACK "+u+":)");
				System.out.println();
				System.out.print(" 1.Add Employee Details\n 2.View Employee Details\n 3.Edit Employee Salary\n 4.Remove Employee Details\n 5.Back \n 6.Exit");
				System.out.println();
				System.out.println();
				System.out.println("ENTER YOUR CHOICE : ");
				int c=sc.nextInt();
				
				if(c==1) {
					System.out.println();
					System.out.print("ENTER THE NAME OF THE EMPLOYEE : ");
					String n=sc.next();
					System.out.print("ENTER THE DATE OF JOINING (YYYY-MM-DD): ");
					String doj=sc.next();
					System.out.print("ENTER MOBILE NUMBER  : ");
					String m=sc.next();
					System.out.print("ENTER LOCATION : ");
					String l=sc.next();
					System.out.println("ENTER EMAIL ID : ");
					String e=sc.next();
					System.out.print("ENTER DEPARTMENT : ");
					String d=sc.next();
					System.out.print("ENTER EXPERIENCE(IN NUMBERS) : ");
					int exp=sc.nextInt();
					System.out.print("ENTER SALARY (IN NUMBERS) : ");
					float s=sc.nextFloat();
					int c1=0;
					try {
					String query="insert into userinformation(name,doj,mobilenumber,location,email,department,experience,salary) values(?,?,?,?,?,?,?,?)";	
					PreparedStatement pst=con.prepareStatement(query);
					pst.setString(1, n);
					pst.setString(2, doj);
					pst.setString(3, m);
					pst.setString(4, l);
					pst.setString(5, e);
					pst.setString(6, d);
					pst.setInt(7, exp);
					pst.setFloat(8, s);			
					c1=pst.executeUpdate();
					System.out.println((c1>0)?n+"'s DETAILS ARE UPDATED":"'s DETAILS ARE NOT UPDATED");
					}catch(SQLException ex) {	
						System.out.println("Error : "+ex);	}
				}
				else if(c==2) {
					select();
					System.out.println("--------- ALL DATA'S ARE SHOWED ABOVE ---------");
				}
				else if(c==3) {
					System.out.print("Enter the id : ");
					int id=sc.nextInt();

					System.out.print("Enter the Salary : ");
					float s=sc.nextFloat();
					
					int res=update(id,s);
					System.out.println((res>0)?"Updated data":"Not Updated Data");
				}
				else if(c==4) {
					System.out.print("Enter the id : ");
					int id=sc.nextInt();
					
					int res=delete(id);
					System.out.println((res>0)?"Deleted data":"Not Deleted Data");
				}
				else if(c==5) {
					logmenu();
					
				}
				else if(c==6) {
					System.out.println("EXITTING FROM EMP...!");
					break;
				}
				else {
					System.out.println("INVALID KEY !! ENTER THE VALID OPTION "+u);		
				}
				System.out.println("-------------------------------------------------------");
			  }
			  
			}else {
				System.out.println("INVALID USERNAME OR PASSWORD !!");
			}
		}
		// user
		else if(ch==2) {
			System.out.print("ENTER YOUR ID : ");
			int id=sc.nextInt();
			this.id=id;
			System.out.print("ENTER YOUR MOBILE NUMBER  : ");
			String mobilenumber=sc.next();
			
			userlogin(id,mobilenumber);
			if(userlogin(id,mobilenumber)) {
				while(true) {
					try {
					String query="select name from  userinformation  where id=?";	
					PreparedStatement pst=con.prepareStatement(query);
					pst.setInt(1, id);	
						ResultSet rs=pst.executeQuery();
					while(rs.next()) {
					System.out.println(" WELCOME BACK  "+
							rs.getString(1)+":)");}
					}catch (SQLException e) {
						System.out.println("error"+e);
					}
					
			
					System.out.println(" 1.Edit information \n 2.view information \n 3.back \n 4.EXIT ");
					System.out.println();
					System.out.println("ENTER THE CHOICE ..");
					int opt=sc.nextInt();
					if(opt==1) {
						System.out.println(" 1.Mobile Number \n 2.Location \n 3.Email  ");
						System.out.println("ENTER THE CHOICE ..");
						int o=sc.nextInt();
						if(o==1) {
							int c=0;
							try {
								System.out.println("enter the mobile number to be updated  :");
								String mn=sc.next();
								String query="update userinformation set mobilenumber=? where id=?";	
								PreparedStatement pst=con.prepareStatement(query);
								pst.setString(1, mn);
								pst.setInt(2, id);
								c=pst.executeUpdate();
								} catch (SQLException e1) {
									System.out.println("error"+e1);
									
								}
								System.out.println((c>0)?"Updated data":"Not Updated Data");
						}
						else if(o==2) {
							int c=0;
							try {
								System.out.println("enter the location to be updated  :");
								String l=sc.next();
								String query="update userinformation set location=? where id=?";	
								PreparedStatement pst=con.prepareStatement(query);
								pst.setString(1,l);
								pst.setInt(2, id);
								
									c=pst.executeUpdate();
								} catch (SQLException e1) {
									System.out.println("error"+e1);
								}
								System.out.println((c>0)?"Updated data":"Not Updated Data");
						}
						else if(o==3) {
							int c=0;
							try {
								//System.out.println("enter the name :");
								//String name3=sc.next();
								System.out.println("enter the email to be updated  :");
								String e1=sc.next();
								String query="update userinformation set email=? where id=?";	
								PreparedStatement pst=con.prepareStatement(query);
								pst.setString(1,e1);
								pst.setInt(2, id);
								
									c=pst.executeUpdate();
								} catch (SQLException e1) {
									
								}
								System.out.println((c>0)?"Updated data":"Not Updated Data");
						}
						else {
							System.out.println("  INVALID CHOICE  ");
						}
					}
					else if(opt==2) {
						int c=0;
						try {String query="select * from  userinformation  where id=?";	
							PreparedStatement pst=con.prepareStatement(query);
							pst.setInt(1, id);	
								ResultSet rs=pst.executeQuery();
							while(rs.next()) {
							System.out.println(
									rs.getInt(1)+"\t"+
									rs.getString(2)+"\t"+
									rs.getString(3)+"\t"+
									rs.getString(4)+"\t"+
									rs.getString(5)+"\t"+
									rs.getString(6)+"\t"+
									rs.getString(7)+"\t"+
									rs.getInt(8)+"\t"+
									rs.getFloat(9) );		
							}				
						}catch(SQLException e1) {
							System.out.println("Error : "+e1);
						}	
					}
					else if(opt==3) {
						logmenu();
					}
					else if(opt==4) {
						System.out.println("EXITTING FROM EMP ");
						break;
					}
					else {
						System.out.println("INVALID CHOICE !! ENTER THE VALID KEY ");
					}
					
				}
			}
			else{
				System.out.println("INVALID NAME OR MOBILE NUMBER !!");
			}
			}
		
		
		
		
		// new user 	
       else if(ch==3) {
    	   System.out.println();
			System.out.print("ENTER YOUR NAME  : ");
			String n=sc.next();
			System.out.print("ENTER THE DATE OF JOINING (YYYY-MM-DD): ");
			String doj=sc.next();
			System.out.print("ENTER MOBILE NUMBER  : ");
			String m=sc.next();
			System.out.print("ENTER LOCATION : ");
			String l=sc.next();
			System.out.println("ENTER EMAIL ID : ");
			String e=sc.next();
			System.out.print("ENTER DEPARTMENT : ");
			String d=sc.next();
			System.out.print("ENTER EXPERIENCE(IN NUMBERS) : ");
			int exp=sc.nextInt();
			System.out.print("ENTER SALARY (IN NUMBERS) : ");
			float s=sc.nextFloat();
				
			int c=0;
			try {
			String query="insert into userinformation(name,doj,mobilenumber,location,email,department,experience,salary) values(?,?,?,?,?,?,?,?)";	
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, n);
			pst.setString(2, doj);
			pst.setString(3, m);
			pst.setString(4, l);
			pst.setString(5, e);
			pst.setString(6, d);
			pst.setInt(7, exp);
			pst.setFloat(8, s);			
			c=pst.executeUpdate();
			String query1="select id from userinformation where name=? and mobilenumber=?";
			PreparedStatement pst1=con.prepareStatement(query1);
			pst1.setString(1, n);
			pst1.setString(2, m);
			ResultSet rs=pst1.executeQuery();
			while(rs.next()) {
				System.out.println("YOUR ID NUMBER IS "+rs.getInt(1));
				System.out.println((c>0)?"YOUR DETAILS HAS BEEN ADDED "+n:"YOUR DETAILS ARE NOT ADDED "+n);
				System.out.println("EXITTING FROM EMP ");
			}
			}catch(SQLException ex) {	
				System.out.println("Error : "+ex);	}
			
			}else {
			System.out.println("INVALID OPTION");}
		}
	
// adminlogin 
	boolean adminlogin(String u,String p) {
		
		boolean r=false;
		try {
		String query="select *from adminlog where username='"+u+"'and password='"+p+"'";
		Statement smt=con.createStatement();
		ResultSet rs=smt.executeQuery(query);
		while(rs.next()) {
			r=true;
		}	
		}catch(SQLException e) {
			System.out.println("Error : "+e);	
		}			
		return r;
	}
	// user login 
	boolean userlogin(int id,String mobilenumber) {
		
		boolean r=false;
		try {
		String query="select id,mobilenumber from userinformation where id='"+id+"'and mobilenumber='"+mobilenumber+"'";
		Statement smt=con.createStatement();
		ResultSet rs=smt.executeQuery(query);
		while(rs.next()) {
			r=true;
		}	
		}catch(SQLException e) {
			System.out.println("Error : "+e);	
		}			
		return r;
	}


	//Insertion
	public int insert( String name,String doj,String mobilenumber,String location,String email,String department,int experience,float salary) {

		System.out.print("Enter the Name : ");
		String n=sc.next();
		System.out.print("Enter the DOJ : ");
		String dj=sc.next();
		System.out.print("Enter the mobile number : ");
		String m=sc.next();
		System.out.print("Enter the location : ");
		String l=sc.next();
		System.out.println("Enter the email : ");
		String e=sc.next();
		System.out.print("Enter the Department : ");
		String d=sc.next();
		System.out.print("Enter the Experience : ");
		int exp=sc.nextInt();
		System.out.print("Enter the Salary : ");
		float s=sc.nextFloat();
		
		int c=0;
		try {
		String query="insert into userinformation(name,doj,mobilenumber,location,email,department,experience,salary) values(?,?,?,?,?,?,?,?)";	
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, n);
		pst.setString(2, dj);
		pst.setString(3, m);
		pst.setString(4, l);
		pst.setString(5, e);
		pst.setString(6, d);
		pst.setInt(7, exp);
		pst.setFloat(8, s);			
		c=pst.executeUpdate();
		System.out.println((c>0)?"Updated data":"Not Updated Data");
		}catch(SQLException ex) {	
			System.out.println("Error : "+ex);	}
		return c;				
	}	
		
	
	
	//Selection
	public void select() {
		try {	
			String query="select *from userinformation ";	
			PreparedStatement pst=con.prepareStatement(query);
			ResultSet rs=pst.executeQuery();	
			
			while(rs.next()) {
			
				System.out.println(
						rs.getInt(1)+"\t"+
						rs.getString(2)+"\t"+
						rs.getString(3)+"\t"+
						rs.getString(4)+"\t"+
						rs.getString(5)+"\t"+
						rs.getString(6)+"\t"+
						rs.getString(7)+"\t"+
						rs.getInt(8)+"\t"+
						rs.getFloat(9) );	
			}				
		}catch(SQLException e) {
			System.out.println("Error : "+e);
		}	
	}
	
	//Updation
		public int update(int id,float sal) {
			int c=0;
			try {
			String query="update userinformation set salary=? where id=?";	
			PreparedStatement pst=con.prepareStatement(query);
			pst.setFloat(1, sal);
			pst.setInt(2, id);
			c=pst.executeUpdate();
			}catch(SQLException e) {	System.out.println("Error : "+e);	}	
			return c;
		}
		//deletion
		public int delete(int id) {
			int c=0;
			try {
			String query="delete from userinformation where id=?";	
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, id);
			c=pst.executeUpdate();
			}catch(SQLException e) {	System.out.println("Error : "+e);	}	
			return c;
		}	
		}




