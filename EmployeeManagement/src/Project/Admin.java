package Project;


public class Admin extends Methods {
	public void Admin(){
    System.out.print("ENTER YOUR USERNAME  : ");
    String u = sc.next();
    System.out.print("ENTER YOUR PASSWORD : ");
    String p = sc.next();
    adminlogin(u, p);
    if (adminlogin(u, p)) {
        while (true) {
            System.out.println("\n	WELCOME BACK " + u + ":)\n");
            System.out.println(" PRESS 1 : VIEW EMPLOYEE RECORDS\n PRESS 2 : INSERT RECORDS\n PRESS 3 : EDIT RECORDS\n PRESS 4 : DELETE RECORDS \n PRESS 5 : VIEW RECORDS BY FILTERS \n PRESS 6 : LEAVE APPLICATION \n PRESS 7 : VIEW ATTENDENCE\n PRESS 8 : Back \n PRESS 9 :EXIT");
            System.out.print("ENTER YOUR CHOICE : ");
            int c = sc.nextInt();
            if (c == 1) {
                System.out.println(" PRESS 1 : VIEW ENTIRE EMPLOYEE RECORDS \n PRESS 2 : VIEW SPECIFIC EMPLOYEE RECORDS \n PRESS 3 : BACK ");
                System.out.print("ENTER YOUR CHOICE : ");
                int view = sc.nextInt();
                if (view == 1) {
                    select();
                    System.out.println("--------- ALL DATA'S ARE SHOWED ABOVE ---------");
                } else if (view == 2) {
                    System.out.print("Enter Employee ID : ");
                    int id = sc.nextInt();
                    selectspecifc(id);
                } else if (view == 3) {

                } else {
                    System.out.println("Invalid entry");
                }
            } else if (c == 2) {
                System.out.println();
                System.out.println(" PRESS 1 : INSERT RECORDS  \n PRESS 2 : BACK ");
                int ch = sc.nextInt();
                System.out.print("ENTER YOUR CHOICE : ");
                if (ch == 1) {
                    newuser();
                } else if (c == 2) {

                } else {
                    System.out.println("Invalid entry");
                }
            } else if (c == 3) {
                System.out.println();
                System.out.println("PRESS 1 : EDIT DEPARTMENT \nPRESS 2 : EDIT SALARY  \nPRESS 3 : BACK");
                System.out.print("ENTER YOUR CHOICE : ");
                int ch = sc.nextInt();
                //department
                if (ch == 1) {
                    System.out.println("Editing Department...!");
                    System.out.print("Enter the Employee ID : ");
                    int id = sc.nextInt();
                    System.out.print("Enter the Employee Department to be Updated : ");
                    String dep = sc.next();
                    int res = (updatedep(id, dep));
                    System.out.println((res > 0) ? "Department updated" : "Department not updated");
                }
                //salary
                else if (ch == 2) {
                    System.out.println("Editing Salary...!");
                    System.out.print("Enter the Employee ID : ");
                    int id = sc.nextInt();
                    System.out.print("Enter the Employee Salary to be Updated : ");
                    int sal = sc.nextInt();
                    int res = (updatesal(id, sal));
                    System.out.println((res > 0) ? "Salary updated" : "Salary not updated");
                } else if (ch == 3) {

                } else {
                    System.out.println("Invalid entry");
                }
            } else if (c == 4) {
                System.out.println("PRESS 1 : DELETE RECORD \nPRESS 2 : BACK");
                System.out.print("ENTER YOUR CHOICE : ");
                int ch = sc.nextInt();
                if (ch == 1) {
                    System.out.print("Enter Employee ID to delete record : ");
                    int id = sc.nextInt();
                    int res = delete(id);
                    System.out.println((res > 0) ? "Record deleted" : "Record not deleted");
                } else if (ch == 2) {

                } else {
                    System.out.println("Invalid entry");
                }
            } else if (c == 5) {
                System.out.println();
                System.out.println("PRESS 1 : EMPLOYEE COUNT BY DEPARTMENT \nPRESS 2 : VIEW DETAILS BY SALARY \nPRESS 3 : VIEW DETAILS BY DATE OF JOINING \nPRESS 4 : BACK");
                System.out.print("ENTER YOUR CHOICE : ");
                int ch = sc.nextInt();
                if (ch == 1) {
                    empcount();
                } else if (ch == 2) {
                    System.out.println();
                    System.out.println("PRESS 1 : VIEW DETAILS BY SALARY ASCENDING \nPRESS 2 : VIEW DETAILS BY SALARY DESCENDING \nPRESS 3 : VIEW DETAILS BY SALARY RANGE \nPRESS 4 : BACK");
                    System.out.print("ENTER YOUR CHOICE : ");
                    int c1 = sc.nextInt();
                    if (c1 == 1) {
                        //employee record by salary asc
                        empsalasc();
                    } else if (c1 == 2) {
                        //employee record by salary desc
                        empsaldesc();
                    } else if (c1 == 3) {
                        System.out.println();
                        System.out.print("ENTER SALARY RANGE FIRST VALUE : ");
                        int r1 = sc.nextInt();
                        System.out.print("ENTER SALARY RANGE SECOND VALUE : ");
                        int r2 = sc.nextInt();
                        empsalrang(r1, r2);
                    } else if (c1 == 4) {

                    } else {
                        System.out.println("INVALID ENTRY");
                    }
                } else if (ch == 3) {
                    System.out.println();
                    System.out.println("PRESS 1 : VIEW DETAILS BY DOJ ASCENDING \nPRESS 2 : VIEW DETAILS BY DOJ DESCENDING \nPRESS 3 : BACK");
                    System.out.print("ENTER YOUR CHOICE : ");
                    int c1 = sc.nextInt();
                    if (c1 == 1) {
                        //employee record by doj asc
                        viewbydojasc();
                    } else if (c1 == 2) {
                        //employee record by doj desc
                        viewbydojdesc();
                    } else if (c1 == 3) {

                    } else {
                        System.out.println("INVALID ENTRY");
                    }
                } else if (ch == 4) {

                } else {
                    System.out.println("INALID ENTRY");
                }
            } else if (c == 6) {
                System.out.println();
                System.out.println("PRESS 1 : VIEW LEAVE APPLICATION \nPRESS 2 : UPDATE LEAVE STATUS \nPRESS 3 : BACK");
                System.out.print("ENTER YOUR CHOICE : ");
                int c1 = sc.nextInt();
                if (c1 == 1) {
                    viewleave();
                } else if (c1 == 2) {
                    System.out.println();
                    System.out.print("ENTER LEAVE APPLICATION NO. : ");
                    int aid = sc.nextInt();
                    System.out.print("ENTER APPROVAL Y/N : ");
                    String a = sc.next();
                    int res = (upleavestatus(a, aid));
                    System.out.println((res > 0) ? "LEAVE STATUS UPDATED" : "LEAVE NOT STATUS UPDATED");
                } else if (c1 == 3) {
                    
                } else {
                    System.out.println("INALID ENTRY");
                }
            } else if (c == 7) {
                viewattendence();
            } else if (c == 8) {
                MAIN.logmenu();
            } else if (c == 9) {
                System.out.println("THANK YOU...! EXITING.");
                break;
            } else {
                System.out.println("Invalid entry");
            }
            System.out.println("-------------------------------------------------------");
        }
    } else {
        System.out.println("Sorry " + u + " you're not an admin :(");
    }
}}

