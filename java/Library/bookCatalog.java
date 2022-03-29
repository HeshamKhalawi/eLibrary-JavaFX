package Library.example.ics324library;

public class bookCatalog {
    String Title, Author, Subject, PublicationDate;

    public bookCatalog(String title, String author, String subject, String publicationDate) {
        Title = title;
        Author = author;
        Subject = subject;
        PublicationDate = publicationDate;
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

    public String getPublicationDate() {
        return PublicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        PublicationDate = publicationDate;
    }
}
