package dbHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class users {
    int idUsers=-1;
    String firstName;
    String lastName;
    int score;
    String mail;

    public users(String firstName, String lastName, int score, String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
        this.mail = mail;
    }

    public users(int id, String firstName, String lastName, int score, String mail) {
        this.idUsers = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
        this.mail = mail;
    }

    public int getId() {
        return idUsers;
    }

    public void setId(int id) {
        this.idUsers = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

//    @Override
//    public String toString() {
////        return "users{" +
////                "firstName='" + firstName + '\'' +
////                ", lastName='" + lastName + '\'' +
////                ", score=" + score +
////                ", mail='" + mail + '\'' +
////                '}';
//    }

    public String Json()
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
           return mapper.writeValueAsString(this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String toString() {
        if(idUsers != -1) {
            return "users{" +
                    "idUsers=" + idUsers +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", score=" + score +
                    ", mail='" + mail + '\'' +
                    '}';
        }
        else
        {
            return "users{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", score=" + score +
                ", mail='" + mail + '\'' +
                '}';
        }
    }
}
