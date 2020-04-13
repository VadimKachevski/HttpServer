package dbHandler;

public class degree
{
    int iddegree;
    String degreeName;
    String imgsrc;

    public degree(int iddegree, String degreeName, String imgsrc) {
        this.iddegree = iddegree;
        this.degreeName = degreeName;
        this.imgsrc = imgsrc;
    }

    public degree(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public int getIddegree() {
        return iddegree;
    }

    public void setIddegree(int iddegree) {
        this.iddegree = iddegree;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }
}
