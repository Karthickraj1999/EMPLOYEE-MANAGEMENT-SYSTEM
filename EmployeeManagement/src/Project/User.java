package Project;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class User extends Methods {
	int id;
	public void user(){
    System.out.print("ENTER YOUR ID : ");
    int id = sc.nextInt();
    this.id=id;
    System.out.print("ENTER YOUR MOBILE NUMBER  : ");
    String mobilenumber = sc.next();
    userlogin(id, mobilenumber);
  
    if (userlogin(id, mobilenumber)) {
        while (true) {
            try {
                String query = "select name from  userinformation  where id=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(" WELCOME BACK  " +
                        rs.getString(1) + ":)");
                }
            } catch (SQLException e) {
                System.out.println("error" + e);
            }


            System.out.println("PRESS 1 : VIEW YOUR RECORDS \nPRESS 2 : EDIT RECORDS \nPRESS 3 : LEAVE APPLICATON \nPRESS 4 : ATTENDENCE \nPRESS 5 : BACK  \nPRESS 5 : EXIT");
            System.out.println();
            System.out.println("ENTER THE CHOICE ..");
            int opt = sc.nextInt();
            if (opt == 1) {
                int c = 0;
                try {
                    String query = "select * from  userinformation  where id=?";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setInt(1, id);
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
                } catch (SQLException e1) {
                    System.out.println("Error : " + e1);
                }
            } else if (opt == 2) {
                System.out.println(" 1.Mobile Number \n 2.Location \n 3.Email \n 4.Back  ");
                System.out.println("ENTER THE CHOICE ..");
                int o = sc.nextInt();
                if (o == 1) {
                    int c = 0;
                    try {
                        System.out.println("enter the mobile number to be updated  :");
                        String mn = sc.next();
                        String query = "update userinformation set mobilenumber=? where id=?";
                        PreparedStatement pst = con.prepareStatement(query);
                        pst.setString(1, mn);
                        pst.setInt(2, id);
                        c = pst.executeUpdate();
                    } catch (SQLException e1) {
                        System.out.println("error" + e1);
                    }
                    System.out.println((c > 0) ? "Updated data" : "Not Updated Data");
                } else if (o == 2) {
                    int c = 0;
                    try {
                        System.out.println("enter the location to be updated  :");
                        String l = sc.next();
                        String query = "update userinformation set location=? where id=?";
                        PreparedStatement pst = con.prepareStatement(query);
                        pst.setString(1, l);
                        pst.setInt(2, id);

                        c = pst.executeUpdate();
                    } catch (SQLException e1) {
                        System.out.println("error" + e1);
                    }
                    System.out.println((c > 0) ? "Updated data" : "Not Updated Data");
                } else if (o == 3) {
                    int c = 0;
                    try {
                        System.out.println("enter the email to be updated  :");
                        String e1 = sc.next();
                        String query = "update userinformation set email=? where id=?";
                        PreparedStatement pst = con.prepareStatement(query);
                        pst.setString(1, e1);
                        pst.setInt(2, id);

                        c = pst.executeUpdate();
                    } catch (SQLException e1) {
                        System.out.println(e1);
                    }
                    System.out.println((c > 0) ? "Updated data" : "Not Updated Data");
                } else if (o == 4) {

                } else {
                    System.out.println("  INVALID CHOICE  ");
                }
            } else if (opt == 3) {
                System.out.println();
                System.out.println("PRESS 1 : APPLY LEAVE \nPRESS 2 : CHECK LEAVE APPROVAL STATUS \nPRESS 3 : BACK");
                System.out.print("ENTER YOUR CHOICE : ");
                int c = sc.nextInt();
                if (c == 1) {
                    System.out.print("ENTER LEAVE STARTING DATE (YYYY-MM-DD) : ");
                    String d1 = sc.next();
                    System.out.print("ENTER LEAVE ENDING DATE (YYYY-MM-DD) : ");
                    String d2 = sc.next();
                    System.out.println("ENTER REASON FOR APPLYING LEAVE : ");
                    String r = sc.next();
                    int res = (leaveapply(d1, d2, r));
                    System.out.println((res > 0) ? "LEAVE APPLIED" : "LEAVE NOT APPLIED");
                } else if (c == 2) {
                    viewleave();
                } else if (c == 3) {

                } else {
                    System.out.println("INVALID ENTRY");
                }

            } else if (opt == 4) {
                System.out.println("FOR ATTENDENCE MARKING PRESS  1 ");
                int c = sc.nextInt();
                if (c == 1) {
                    attendence();
                } else {
                    System.out.println("INVALID");
                }
            } else if (opt == 5) {
                MAIN.logmenu();
            } else if (opt == 6) {
                System.out.println("EXITTING FROM EMP ");
                break;
            } else {
                System.out.println("INVALID CHOICE !! ENTER THE VALID KEY ");
            }

        }
    } else {
        System.out.println("INVALID NAME OR MOBILE NUMBER !!");
    }
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
}

