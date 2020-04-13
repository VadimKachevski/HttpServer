package dbHandler;

public class questions {
    int idquestions;
    String imgPath;
    String txt;
    String type;
    int masterQ;
    int score;
    int idCourse;
    int comment;

    public questions(int idquestions, String imgPath, String txt, String type, int masterQ, int score, int idCourse, int comment) {
        this.idquestions = idquestions;
        this.imgPath = imgPath;
        this.txt = txt;
        this.type = type;
        this.masterQ = masterQ;
        this.score = score;
        this.idCourse = idCourse;
        this.comment = comment;
    }

    public questions(String imgPath, String txt, String type, int masterQ, int score, int idCourse) {
        this.imgPath = imgPath;
        this.txt = txt;
        this.type = type;
        this.masterQ = masterQ;
        this.score = score;
        this.idCourse = idCourse;
    }

    public questions(int idquestions, String imgPath, String txt, String type, int masterQ, int score, int idCourse) {
        this.idquestions = idquestions;
        this.imgPath = imgPath;
        this.txt = txt;
        this.type = type;
        this.masterQ = masterQ;
        this.score = score;
        this.idCourse = idCourse;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getIdquestions() {
        return idquestions;
    }

    public void setIdquestions(int idquestions) {
        this.idquestions = idquestions;
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
