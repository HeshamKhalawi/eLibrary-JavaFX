package Library.example.ics324library;

public class Borrow {
    String Title, Author, Subject, DueDate;

    public Borrow(String title, String author, String subject, String dueDate) {
        Title = title;
        Author = author;
        Subject = subject;
        DueDate = dueDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }
}
