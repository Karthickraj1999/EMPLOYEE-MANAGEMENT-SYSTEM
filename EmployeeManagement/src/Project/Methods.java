package Project;



import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.time.LocalDate;

public class Methods {
    int id;
    Connection con;
    Scanner sc = new Scanner(System.in);
    //Connectivity
    public Methods() {
        String dburl = "jdbc:mysql://localhost:3306/project";
        String dbuser = "root";
        String dbpass = "";
        try {
            con = DriverManager.getConnection(dburl, dbuser, dbpass);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //adminlogin
    boolean adminlogin(String u, String p) {

        boolean r = false;
        try {
            String query = "select *from adminlog where username='" + u + "'and password='" + p + "'";
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery(query);
            while (rs.next()) {
                r = true;
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return r;
    }
    //user login 
    boolean userlogin(int id, String mobilenumber) {
        boolean r = false;
        try {
            String query = "select id,mobilenumber from userinformation where id='" + id + "'and mobilenumber='" + mobilenumber + "'";
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery(query);
            while (rs.next()) {
                r = true;
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return r;
    }
    //new user 
    public void newuser() {
        System.out.println();
        System.out.print("ENTER THE NAME OF THE EMPLOYEE : ");
        String n = sc.next();
        System.out.print("ENTER THE DATE OF JOINING (YYYY-MM-DD): ");
        String doj = sc.next();
        System.out.print("ENTER MOBILE NUMBER  : ");
        String m = sc.next();
        System.out.print("ENTER LOCATION : ");
        String l = sc.next();
        System.out.println("ENTER EMAIL ID : ");
        String e = sc.next();
        System.out.print("ENTER DEPARTMENT : ");
        String d = sc.next();
        System.out.print("ENTER EXPERIENCE(IN NUMBERS) : ");
        int exp = sc.nextInt();
        System.out.print("ENTER SALARY (IN NUMBERS) : ");
        float s = sc.nextFloat();
        int c1 = 0;
        try {
            String query = "insert into userinformation(name,doj,mobilenumber,location,email,department,experience,salary) values(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, n);
            pst.setString(2, doj);
            pst.setString(3, m);
            pst.setString(4, l);
            pst.setString(5, e);
            pst.setString(6, d);
            pst.setInt(7, exp);
            pst.setFloat(8, s);
            c1 = pst.executeUpdate();
            String query1="select id from userinformation where name=? and mobilenumber=?";
			PreparedStatement pst1=con.prepareStatement(query1);
			pst1.setString(1, n);
			pst1.setString(2, m);
			ResultSet rs=pst1.executeQuery();
			while(rs.next()) {
				System.out.println("USER ID NUMBER IS "+rs.getInt(1));
				System.out.println((c1>0)?"USER  DETAILS HAS BEEN ADDED "+n:"USER DETAILS ARE NOT ADDED "+n);
				System.out.println("EXITTING FROM EMP ");
			}
			}catch(SQLException ex) {	
				System.out.println("Error : "+ex);	}
    }
   

    //Insertion
    public int insert(String name, String doj, String mobilenumber, String location, String email, String department, int experience, float salary) {

        System.out.print("Enter the Name : ");
        String n = sc.next();
        System.out.print("Enter the DOJ : ");
        String dj = sc.next();
        System.out.print("Enter the mobile number : ");
        String m = sc.next();
        System.out.print("Enter the location : ");
        String l = sc.next();
        System.out.println("Enter the email : ");
        String e = sc.next();
        System.out.print("Enter the Department : ");
        String d = sc.next();
        System.out.print("Enter the Experience : ");
        int exp = sc.nextInt();
        System.out.print("Enter the Salary : ");
        float s = sc.nextFloat();

        int c = 0;
        try {
            String query = "insert into userinformation(name,doj,mobilenumber,location,email,department,experience,salary) values(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, n);
            pst.setString(2, dj);
            pst.setString(3, m);
            pst.setString(4, l);
            pst.setString(5, e);
            pst.setString(6, d);
            pst.setInt(7, exp);
            pst.setFloat(8, s);
            c = pst.executeUpdate();
            System.out.println((c > 0) ? "Updated data" : "Not Updated Data");
        } catch (SQLException ex) {
            System.out.println("Error : " + ex);
        }
        return c;
    }



    //Selection
    public void select() {
        try {
            String query = "select *from userinformation ";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                System.out.println(
                    rs.getInt(1) + "\t" +
                    rs.getString(2) + "\t" +
                    rs.getString(3) + "\t" +
                    rs.getString(4) + "\t" +
                    rs.getString(5) + "\t" +
                    rs.getString(6) + "\t" +
                    rs.getString(7) + "\t" +
                    rs.getInt(8) + "\t" +
                    rs.getFloat(9));
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
    }
    //selecting  by id
    public void selectspecifc(int id) {
        try {
            String q2 = "select * from userinformation where id=?";
            PreparedStatement pst = con.prepareStatement(q2);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            System.out.println("RESULT : ");
            while (rs.next()) {
                System.out.println(
                    rs.getInt(1) + "\t" +
                    rs.getString(2) + "\t" +
                    rs.getString(3) + "\t" +
                    rs.getString(4) + "\t" +
                    rs.getString(5) + "\t" +
                    rs.getString(6) + "\t" +
                    rs.getString(7) + "\t" +
                    rs.getInt(8) + "\t" +
                    rs.getFloat(9));
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
    }


    //Updation
    public int update(int id, float sal) {
        int c = 0;
        try {
            String query = "update userinformation set salary=? where id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setFloat(1, sal);
            pst.setInt(2, id);
            c = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return c;
    }
    //updating department
    public int updatedep(int id, String dept) {
        int i = 0;
        try {
            String q4 = "update userinformation set department=? where id=?";
            PreparedStatement pst = con.prepareStatement(q4);
            pst.setString(1, dept);
            pst.setInt(2, id);
            i = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return i;
    }
    //updating salary
    public int updatesal(int id, int sal) {
        int i = 0;
        try {
            String q5 = "update userinformation set salary=? where id=?";
            PreparedStatement pst = con.prepareStatement(q5);
            pst.setInt(1, sal);
            pst.setInt(2, id);
            i = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return i;
    }
    //Employee by dept
    public void empbydept(String dep) {
        try {
            String q7 = "select * from userinformation where department=?";
            PreparedStatement pst = con.prepareStatement(q7);
            pst.setString(1, dep);
            ResultSet rs = pst.executeQuery();
            System.out.println("FILTER APPLIED : VIEWING BY SPECIFIC DEPARTMENT");
            System.out.println("RESULT : ");
            while (rs.next()) {
                System.out.println(
                    rs.getInt(1) + "\t" +
                    rs.getString(2) + "\t" +
                    rs.getString(3) + "\t" +
                    rs.getString(4) + "\t" +
                    rs.getString(5) + "\t" +
                    rs.getString(6) + "\t" +
                    rs.getString(7) + "\t" +
                    rs.getInt(8) + "\t" +
                    rs.getFloat(9));
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
    }

    //Employee count by dept
    public void empcount() {
        try {
            String q8 = "select department,count(*) from userinformation group by department";
            PreparedStatement pst = con.prepareStatement(q8);
            ResultSet rs = pst.executeQuery();
            System.out.println("FILTER APPLIED : EMPLOYEE COUNT BY DEPARTMENT");
            System.out.println("RESULT : ");
            System.out.println("department\tcount");
            while (rs.next()) {
                String dept = rs.getString(1);
                int count = rs.getInt(2);
                System.out.println(dept + "\t\t" + count);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    //Employee details by latest doj descending
    public void viewbydojasc() {
        try {
            String q9 = "select * from userinformation order by doj";
            PreparedStatement pst = con.prepareStatement(q9);
            ResultSet rs = pst.executeQuery();
            System.out.println("FILTER APPLIED : VIEWING BY EARLYIEST DATE OF JOINING");
            System.out.println("RESULT : ");
            while (rs.next()) {
                System.out.println(
                    rs.getInt(1) + "\t" +
                    rs.getString(2) + "\t" +
                    rs.getString(3) + "\t" +
                    rs.getString(4) + "\t" +
                    rs.getString(5) + "\t" +
                    rs.getString(6) + "\t" +
                    rs.getString(7) + "\t" +
                    rs.getInt(8) + "\t" +
                    rs.getFloat(9));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    //Employee details by latest doj descending
    public void viewbydojdesc() {
        try {
            String q9 = "select * from userinformation order by doj desc";
            PreparedStatement pst = con.prepareStatement(q9);
            ResultSet rs = pst.executeQuery();
            System.out.println("FILTER APPLIED : VIEWING BY LATEST DATE OF JOINING");
            System.out.println("RESULT : ");
            while (rs.next()) {
                System.out.println(
                    rs.getInt(1) + "\t" +
                    rs.getString(2) + "\t" +
                    rs.getString(3) + "\t" +
                    rs.getString(4) + "\t" +
                    rs.getString(5) + "\t" +
                    rs.getString(6) + "\t" +
                    rs.getString(7) + "\t" +
                    rs.getInt(8) + "\t" +
                    rs.getFloat(9));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    //Employee details by salary ascending
    public void empsalasc() {
        try {
            String q10 = "select * from userinformation order by salary asc";
            PreparedStatement pst = con.prepareStatement(q10);
            System.out.println("FILTER APPLIED : VIEWING BY LATEST DATE OF JOINING");
            ResultSet rs = pst.executeQuery();
            System.out.println("RESULT : ");
            while (rs.next()) {
                System.out.println(
                    rs.getInt(1) + "\t" +
                    rs.getString(2) + "\t" +
                    rs.getString(3) + "\t" +
                    rs.getString(4) + "\t" +
                    rs.getString(5) + "\t" +
                    rs.getString(6) + "\t" +
                    rs.getString(7) + "\t" +
                    rs.getInt(8) + "\t" +
                    rs.getFloat(9));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    //Employee details by salary descending
    public void empsaldesc() {
        try {
            String q11 = "select * from userinformation order by salary desc";
            PreparedStatement pst = con.prepareStatement(q11);
            System.out.println("FILTER APPLIED : VIEWING BY LATEST DATE OF JOINING");
            ResultSet rs = pst.executeQuery();
            System.out.println("RESULT : ");
            while (rs.next()) {
                System.out.println(
                    rs.getInt(1) + "\t" +
                    rs.getString(2) + "\t" +
                    rs.getString(3) + "\t" +
                    rs.getString(4) + "\t" +
                    rs.getString(5) + "\t" +
                    rs.getString(6) + "\t" +
                    rs.getString(7) + "\t" +
                    rs.getInt(8) + "\t" +
                    rs.getFloat(9));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    //Employee details by salary range
    public void empsalrang(int s1, int s2) {
        try {
            String q12 = "select * from userinformation where salary between ? and ?";
            PreparedStatement pst = con.prepareStatement(q12);
            pst.setInt(1, s1);
            pst.setInt(2, s2);
            System.out.println("FILTER APPLIED : VIEWING BY SALARY RANGE");
            System.out.println("RESULT : ");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(
                    rs.getInt(1) + "\t" +
                    rs.getString(2) + "\t" +
                    rs.getString(3) + "\t" +
                    rs.getString(4) + "\t" +
                    rs.getString(5) + "\t" +
                    rs.getString(6) + "\t" +
                    rs.getString(7) + "\t" +
                    rs.getInt(8) + "\t" +
                    rs.getFloat(9));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }


    //viewing employee leave application
    public void viewleave() {
        try {
            String q = "select * from leaveinformation";
            PreparedStatement pst = con.prepareStatement(q);
            ResultSet rs = pst.executeQuery();
            System.out.println("LEAVE APPLICATION");
            System.out.println("NO.\tID\tFROM\tTO\tREASON\tSTATUS");
            while (rs.next()) {
                int an = rs.getInt(1);
                int i = rs.getInt(2);
                String d1 = rs.getString(3);
                String d2 = rs.getString(4);
                String r = rs.getString(5);
                String s = rs.getString(6);
                System.out.println(an + "\t" + i + "\t" + d1 + "\t" + d2 + "\t" + r + "\t" + s);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    //Update employee leave status
    public int upleavestatus(String res, int aid) {
        int i = 0;
        try {
            String q = "update leaveinformation set status=? where Applicationno=?";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setString(1, res);
            pst.setInt(2, aid);

            i = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
        return i;
    }


    //Employee leave applying
    public int leaveapply(String d1, String d2, String r) {
        int i = 0;
        try {
            String q = "insert into leaveinformation(id,From_Date,To_Date,reason) values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setInt(1,id );
            pst.setString(2, d1);
            pst.setString(3, d2);
            pst.setString(4, r);

            i = pst.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return i;
    }
    //deletion
    public int delete(int id) {
        int c = 0;
        try {
            String query = "delete from userinformation where id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            c = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return c;
    }
    public void attendence() {
        String status = "yes";
        LocalDate localDate = java.time.LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);
        int c=0;
        try {
            String q = "insert attendence(id,attendence_Date,Status) values(?,?,?)";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setInt(1,id);
            pst.setDate(2, date);
            pst.setString(3, status);
            c = pst.executeUpdate();
            System.out.println((c > 0) ? "attendence marked " : "attendence not marked ");
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }
    
    public void viewattendence() {
    	 try {
             String query = "select *from attendence ";
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery();

             while (rs.next()) {

                 System.out.println(
                     rs.getInt(1) + "\t" +
                     rs.getInt(2) + "\t" +
                     rs.getString(3) + "\t" +
                     rs.getString(4)  
                     );
             }
         } catch (SQLException e) {
             System.out.println("Error : " + e);
         }
    }
}



