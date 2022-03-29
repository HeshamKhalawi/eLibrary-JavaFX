package Library.example.ics324library;

public class Memeber {
    int ID;
    String Fname, Lname, PhoneNo, Email;

    public Memeber(int ID, String Fname, String Lname, String PhoneNo, String Email) {
        this.ID = ID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.PhoneNo = PhoneNo;
        this.Email = Email;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getID() {
        return ID;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public String getEmail() {
        return Email;
    }
}
