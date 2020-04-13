package dbHandler;

import java.sql.*;
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

    public static ArrayList<questions> getQuestions(String courseName)
    {
        ArrayList<questions> questionAr = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(CONPARAM,USER,PASS);
            Statement stmt=con.createStatement();

            ResultSet rs1 = stmt.executeQuery("select * from course WHERE courseName="+"'" + courseName + "'");
            int idCourse=1;
            while (rs1.next())
            {
                idCourse= rs1.getInt("idcourse");
            }


            ResultSet rs=stmt.executeQuery("select * from questions WHERE masterQ=-1 AND idCourse="+idCourse);
            questions us=null;
            while(rs.next()) {
                us = new questions(rs.getInt("idquestions"),rs.getString("imgPath"),rs.getString("txt"),rs.getString("type"),rs.getInt("masterQ"),rs.getInt("score"),rs.getInt("idCourse"),rs.getInt("comment"),rs.getString("name"));
                //us = new questions(rs.getInt("iddegree"), rs.getString("degreeName"), rs.getString("imgsrc"));
                questionAr.add(us);
                //  System.out.println(rs.getInt("idUsers") + "  " + rs.getString("firstName") + "  " + rs.getString("lastName") + " " + rs.getInt("score") + rs.getString("mail"));
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return questionAr;
    }


    public static ArrayList<course> getCourses(String courseName)
    {
        ArrayList<course> courseAR = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(CONPARAM,USER,PASS);
            Statement stmt=con.createStatement();
            //courseName = "'" + courseName + "'";
            ResultSet rs1 = stmt.executeQuery("select * from degree WHERE degreeName="+"'" + courseName + "'");
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


    public static ArrayList<questions> getComments(String questionID)
    {
        ArrayList<questions> questionAr = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(CONPARAM,USER,PASS);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from questions WHERE idquestions="+questionID+" OR masterQ="+questionID);
            questions us=null;
            while(rs.next()) {
                us = new questions(rs.getInt("idquestions"),rs.getString("imgPath"),rs.getString("txt"),rs.getString("type"),rs.getInt("masterQ"),rs.getInt("score"),rs.getInt("idCourse"),rs.getInt("comment"),rs.getString("name"));
                //us = new questions(rs.getInt("iddegree"), rs.getString("degreeName"), rs.getString("imgsrc"));
                questionAr.add(us);
                //  System.out.println(rs.getInt("idUsers") + "  " + rs.getString("firstName") + "  " + rs.getString("lastName") + " " + rs.getInt("score") + rs.getString("mail"));
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return questionAr;
    }
    public static ArrayList<questions> setComment(String questionID,String txt,String idCourse,String name,String file)
    {
        ArrayList<questions> questionAr = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(CONPARAM,USER,PASS);
            Statement stmt=con.createStatement();
            // INSERT INTO `db`.`questions` (`txt`, `type`, `masterQ`, `score`, `idCourse`, `comment`, `name`) VALUES ('teeeeeeeeet', 'נוה מה', '1', '2', '1', '1', 'da');
            String query = "INSERT INTO `db`.`questions` (`txt`, `type`, `masterQ`, `score`, `idCourse`, `comment`, `name`, `imgPath`) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, txt);
            preparedStmt.setString (2, "תגובה");
            preparedStmt.setInt (3, Integer.parseInt(questionID));
            preparedStmt.setInt (4, 0);
            preparedStmt.setInt (5, Integer.parseInt(idCourse));
            preparedStmt.setInt (6, 1);
            preparedStmt.setString (7, name);
            preparedStmt.setString (8, file);

            preparedStmt.execute();
            ResultSet rs=stmt.executeQuery("select * from questions WHERE idquestions="+questionID+" OR masterQ="+questionID);
            questions us=null;
            while(rs.next()) {
                us = new questions(rs.getInt("idquestions"),rs.getString("imgPath"),rs.getString("txt"),rs.getString("type"),rs.getInt("masterQ"),rs.getInt("score"),rs.getInt("idCourse"),rs.getInt("comment"),rs.getString("name"));
                //us = new questions(rs.getInt("iddegree"), rs.getString("degreeName"), rs.getString("imgsrc"));
                questionAr.add(us);
                //  System.out.println(rs.getInt("idUsers") + "  " + rs.getString("firstName") + "  " + rs.getString("lastName") + " " + rs.getInt("score") + rs.getString("mail"));
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return questionAr;
    }
}
