package dbHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class dbConnector {


   private static final String CONPARAM = "jdbc:mysql://db.cxofjfyslq43.us-east-2.rds.amazonaws.com:3306/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
  private static final   String USER = "root";
   private static final String PASS = "AllDbes1";


    public static ArrayList<users> getUsers()
    {
        ArrayList<users> usersAr = new ArrayList<users>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(CONPARAM,USER,PASS);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from users");
            users us=null;
            while(rs.next()) {
                us = new users(rs.getInt("idUsers"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("score"), rs.getString("mail"));
                usersAr.add(us);
              //  System.out.println(rs.getInt("idUsers") + "  " + rs.getString("firstName") + "  " + rs.getString("lastName") + " " + rs.getInt("score") + rs.getString("mail"));
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return usersAr;
    }
    public static ArrayList<degree> getDegree()
    {
        ArrayList<degree> degreeAr = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(CONPARAM,USER,PASS);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from degree");
            degree us=null;
            while(rs.next()) {
                us = new degree(rs.getInt("iddegree"), rs.getString("degreeName"), rs.getString("imgsrc"));
                degreeAr.add(us);
                //  System.out.println(rs.getInt("idUsers") + "  " + rs.getString("firstName") + "  " + rs.getString("lastName") + " " + rs.getInt("score") + rs.getString("mail"));
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return degreeAr;
    }
    public static ArrayList<course> getCourses(String courseName)
    {
        ArrayList<course> courseAR = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(CONPARAM,USER,PASS);
            Statement stmt=con.createStatement();

            ResultSet rs1 = stmt.executeQuery("select * from degree WHERE degreeName="+courseName);
            int degreeID=1;
            while (rs1.next())
            {
                degreeID= rs1.getInt("iddegree");
            }
            ResultSet rs=stmt.executeQuery("select * from course WHERE degreeID="+degreeID);
            course us=null;
            while(rs.next()) {
                us = new course(rs.getInt("idcourse"), rs.getString("courseName"), rs.getInt("degreeID"));
                courseAR.add(us);
                //  System.out.println(rs.getInt("idUsers") + "  " + rs.getString("firstName") + "  " + rs.getString("lastName") + " " + rs.getInt("score") + rs.getString("mail"));
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return courseAR;
    }
}
