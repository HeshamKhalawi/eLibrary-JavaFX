package Library.example.ics324library;

public class RemoveBook {
    String Author, ISBN, Subject, Title;

    public RemoveBook(String ISBN, String title, String author, String subject) {
        Author = author;
        this.ISBN = ISBN;
        Subject = subject;
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
