package Library.example.ics324library;

public class Reserve {
    String Title, Author, Subject, PublishDate;

    public Reserve(String title, String author, String subject, String publishDate) {
        Title = title;
        Author = author;
        Subject = subject;
        PublishDate = publishDate;
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

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String publishDate) {
        PublishDate = publishDate;
    }
}
