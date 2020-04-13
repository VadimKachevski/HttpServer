package dbHandler;

public class questions {
    String imgPath;
    String txt;
    String type;
    int masterQ;
    int score;
    int idCourse;

    public questions(String imgPath, String txt, String type, int masterQ, int score, int idCourse) {
        this.imgPath = imgPath;
        this.txt = txt;
        this.type = type;
        this.masterQ = masterQ;
        this.score = score;
        this.idCourse = idCourse;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMasterQ() {
        return masterQ;
    }

    public void setMasterQ(int masterQ) {
        this.masterQ = masterQ;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }
}
