package Library.example.ics324library;

public class Books {
    String ISBN, Title, Author, Subject;

    public Books(String ISBN, String title, String author, String subject) {
        this.ISBN = ISBN;
        Title = title;
        Author = author;
        Subject = subject;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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
}
