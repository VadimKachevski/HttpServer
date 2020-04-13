package dbHandler;

public class course {
    int idcourse;
    String courseName;
    int degreeID;

    public course(int idcourse, String courseName, int degreeID) {
        this.idcourse = idcourse;
        this.courseName = courseName;
        this.degreeID = degreeID;
    }

    public course(String courseName, int degreeID) {
        this.courseName = courseName;
        this.degreeID = degreeID;
    }

    public int getIdcourse() {
        return idcourse;
    }

    public void setIdcourse(int idcourse) {
        this.idcourse = idcourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getDegreeID() {
        return degreeID;
    }

    public void setDegreeID(int degreeID) {
        this.degreeID = degreeID;
    }
}
