package Library.example.ics324library;


import Library.example.DatabaseConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx. fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public static String Username = "";
    public String Password = "";
    public Label welcomeLabel;
    //LoginPage buttons
    public TextField LoginUsername;
    public TextField LoginPassword;
    public Label loginError;

    //Register Buttons
    public TextField registerID;
    public TextField registerFNAME;
    public TextField registerLNAME;
    public TextField registerAddress;
    public TextField registerPhone;
    public TextField registerEmail;
    public TextField registerAge;
    public TextField registerPassword;
    public Label registerError;
    public RadioButton registerPremium;
    public RadioButton registerBasic;

    //Cancel Subscription buttons
    @FXML
    private TableColumn<Memeber, String> emailColumn;
    @FXML
    private TableColumn<Memeber, String> fnameColumn;
    @FXML
    private TableColumn<Memeber, Integer> idColumn;
    @FXML
    private TableColumn<Memeber, String> lnameColumn;
    @FXML
    private TableColumn<Memeber, String>phoneColumn;
    @FXML
    private TableView<Memeber> tableview;
    ObservableList<Memeber> memberList = FXCollections.observableArrayList();
    int index = -1;


    //add book buttons
    public TextField book_ISBN;
    public TextField book_title;
    public TextField book_author_FNAME;
    public TextField book_author_LNAME;
    public TextField book_subject;
    public TextField book_publisher_FNAME;
    public TextField book_publisher_LNAME;
    public TextField book_publication_date;
    public TextField book_items;
    public TextField book_rackNumber;
    public Label bookError;

    //edit book buttons
    @FXML
    private TableColumn<Books, String> Author_editColumn;
    @FXML
    private TableView<Books> Edit_tableView;
    @FXML
    private TableColumn<Books, String> ISBN_editColumn;
    @FXML
    private TableColumn<Books, String> Subject_editColumn;
    @FXML
    private TableColumn<Books, String> Title_editColumn;
    ObservableList<Books> bookList = FXCollections.observableArrayList();

    public static String ISBNGlobal;
    public TextField editBook_ISBN;
    public TextField editBook_title;
    public TextField editBook_author_FNAME;
    public TextField editBook_author_LNAME;
    public TextField editBook_subject;
    public TextField editBook_publisher_FNAME;
    public TextField editBook_publisher_LNAME;
    public TextField editBook_publication_date;
    public TextField editBook_items;
    public TextField editBook_rackNumber;
    public Label editSuccess;

    //Book catalog buttons
    @FXML
    private TableColumn<bookCatalog, String> Author_catalogColumn;
    @FXML
    private TableColumn<bookCatalog, String> PublicationDate_catalogColumn;
    @FXML
    private TableColumn<bookCatalog, String> Subject_catalogColumn;
    @FXML
    private TableColumn<bookCatalog, String> Title_catalogColumn;
    @FXML
    private TableView<bookCatalog> catalog_tableView;
    ObservableList<bookCatalog> catalogList = FXCollections.observableArrayList();
    public Label catalogError;
    public Label catalogStatus;

    //book borrow buttons
    @FXML
    private TableColumn<Borrow, String> Title_borrowColumn;
    @FXML
    private TableColumn<Borrow, String> Subject_borrowColumn;
    @FXML
    private TableColumn<Borrow, String> DueDate_borrowColumn;
    @FXML
    private TableView<Borrow> Borrow_tableView;
    @FXML
    private TableColumn<Borrow, String> Author_borrowColumn;
    ObservableList<Borrow> borrowList = FXCollections.observableArrayList();
    public Label borrowLabel;


   //book reserve buttons
    @FXML
    private TableColumn<Reserve, String> Author_reserveColumn;
    @FXML
    private TableColumn<Reserve, String> PublishDate_reserveColumn;
    @FXML
    private TableView<Reserve> Reserve_tableView;
    @FXML
    private TableColumn<Reserve, String> Subject_reserveColumn;
    @FXML
    private TableColumn<Reserve, String> Title_reserveColumn;
    ObservableList<Reserve> reserveList = FXCollections.observableArrayList();
    public Label ReserveLabel;

    //remove book
    @FXML
    private TableColumn<RemoveBook, String> Author_removeColumn;
    @FXML
    private TableColumn<RemoveBook, String> ISBN_removeColumn;
    @FXML
    private TableView<RemoveBook> Remove_tableView;
    @FXML
    private TableColumn<RemoveBook, String> Subject_removeColumn;
    @FXML
    private TableColumn<RemoveBook, String> Title_removeColumn;
    ObservableList<RemoveBook> removeList = FXCollections.observableArrayList();
    public Label removeSuccess;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Members list
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectDB = connection.getConnection();
            String query = "SELECT memeber.ID, memeber.Fname, memeber.Lname, memeber.PhoneNum, memeber.Email FROM memeber";
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
              memberList.add(new Memeber(Integer.parseInt(rs.getString("ID")), rs.getString("Fname"), rs.getString("Lname"), rs.getString("PhoneNum"), rs.getString("Email")));
            }
            tableview.setItems(memberList);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            fnameColumn.setCellValueFactory(new PropertyValueFactory<>("Fname"));
            lnameColumn.setCellValueFactory(new PropertyValueFactory<>("Lname"));
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("PhoneNo"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        }catch (Exception e){
        }
        //Book list
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectDB = connection.getConnection();
            String query = "SELECT book.ISBN, book.Title, author.Fname, book.Subject FROM book INNER JOIN author ON author.AuthorID=book.AuthorID";
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                bookList.add(new Books(rs.getString("ISBN"), rs.getString("Title"), rs.getString("Fname"), rs.getString("Subject")));
            }
            Edit_tableView.setItems(bookList);
            ISBN_editColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            Title_editColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
            Author_editColumn.setCellValueFactory(new PropertyValueFactory<>("Author"));
            Subject_editColumn.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        }catch (Exception e){
        }

        //Catalog List
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectDB = connection.getConnection();
            String query = "SELECT book.Title, author.Fname, book.Subject, book.Date FROM book INNER JOIN author ON author.AuthorID=book.AuthorID";
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                catalogList.add(new bookCatalog(rs.getString("Title"), rs.getString("Fname"), rs.getString("Subject"), rs.getString("Date")));
            }
            catalog_tableView.setItems(catalogList);
            Title_catalogColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
            Author_catalogColumn.setCellValueFactory(new PropertyValueFactory<>("Author"));
            Subject_catalogColumn.setCellValueFactory(new PropertyValueFactory<>("Subject"));
            PublicationDate_catalogColumn.setCellValueFactory(new PropertyValueFactory<>("PublicationDate"));
        }catch (Exception e){
        }

        //borrow List
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectDB = connection.getConnection();
            String query = "SELECT book.Title, author.Fname, book.Subject, checkout.DueDate FROM book INNER JOIN checkout ON book.ISBN=checkout.ISBN AND checkout.BorrowerID = '" + Username + "' INNER JOIN author ON author.AuthorID=book.AuthorID";
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                borrowList.add(new Borrow(rs.getString("Title"), rs.getString("Fname"), rs.getString("Subject"), rs.getString("DueDate")));
            }
            Borrow_tableView.setItems(borrowList);
            Title_borrowColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
            Author_borrowColumn.setCellValueFactory(new PropertyValueFactory<>("Author"));
            Subject_borrowColumn.setCellValueFactory(new PropertyValueFactory<>("Subject"));
            DueDate_borrowColumn.setCellValueFactory(new PropertyValueFactory<>("DueDate"));
        }catch (Exception e){
        }

        //reserve List
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectDB = connection.getConnection();
            String query = "SELECT book.Title, author.Fname, book.Subject, book.Date FROM book INNER JOIN reserve ON book.ISBN=reserve.ISBN AND reserve.ReserverID = '" + Username + "' INNER JOIN author ON author.AuthorID=book.AuthorID";
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                reserveList.add(new Reserve(rs.getString("Title"), rs.getString("Fname"), rs.getString("Subject"), rs.getString("Date")));
            }
            Reserve_tableView.setItems(reserveList);
            Title_reserveColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
            Author_reserveColumn.setCellValueFactory(new PropertyValueFactory<>("Author"));
            Subject_reserveColumn.setCellValueFactory(new PropertyValueFactory<>("Subject"));
            PublishDate_reserveColumn.setCellValueFactory(new PropertyValueFactory<>("PublishDate"));
        }catch (Exception e){
        }

        //Remove book
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectDB = connection.getConnection();
            String query = "SELECT book.ISBN,  book.Title, author.Fname, book.Subject FROM book INNER JOIN author ON author.AuthorID=book.AuthorID";
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                removeList.add(new RemoveBook(rs.getString("ISBN"), rs.getString("Title"), rs.getString("Fname"), rs.getString("Subject")));
            }
            Remove_tableView.setItems(removeList);
            ISBN_removeColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            Title_removeColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
            Author_removeColumn.setCellValueFactory(new PropertyValueFactory<>("Author"));
            Subject_removeColumn.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        }catch (Exception e){
        }


    }

    public void login(ActionEvent event) throws IOException, InputMismatchException {
        if(LoginUsername.getText().isBlank() == false && LoginPassword.getText().isBlank() == false){
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectDB = connection.getConnection();
            String memberIDs = "SELECT * FROM memeber";
            String librarianIDs = "SELECT * FROM employee";
            String verifyLogin = "SELECT ID FROM memeber WHERE ID = '"+LoginUsername.getText()+ "' and memeber.Password = '"+ LoginPassword.getText() +"' UNION Select ID FROM employee WHERE ID = '"+ LoginUsername.getText()+ "' and employee.Password = '" + LoginPassword.getText()+"'";

            try{
                Statement statement1 = connectDB.createStatement();
                ResultSet loginResult = statement1.executeQuery(verifyLogin);

                Statement statement2 = connectDB.createStatement();
                ResultSet membersResult = statement2.executeQuery(memberIDs);

                Statement statement3 = connectDB.createStatement();
                ResultSet librarianResult = statement3.executeQuery(librarianIDs);

                while(loginResult.next()){
                    while(membersResult.next()){
                        if(loginResult.getString(1).equals(membersResult.getString(1))){
                            Username = membersResult.getString(1);
                            Password = membersResult.getString(13);
                            root = FXMLLoader.load(getClass().getResource("MemberHP.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        }
                    }
                    while(librarianResult.next()){
                        if(loginResult.getString(1).equals(librarianResult.getString(1))){
                            Username = librarianResult.getString(1);
                            Password = librarianResult.getString(7);
                            root = FXMLLoader.load(getClass().getResource("LibrarianHP.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        }
                    }
                }
                loginError.setText("Error: Incorrect username or password");
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        } else{
            loginError.setText("Please enter username and password");
        }
    }


    //LoginPage sub-pages back button
    public void backToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //MemberHP buttons
    public void backToMemberHP(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MemberHP.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toCatalog(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Catalog.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void checkout() throws SQLException {
        index = catalog_tableView.getSelectionModel().getFocusedIndex();
        if(index <= -1){
            return;
        }
        String title =  Title_catalogColumn.getCellData(index);
        String getItemQuery = "SELECT BookItems FROM book WHERE Title = '" + title + "'";
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        Statement statement1 = connectDB.createStatement();
        ResultSet rs = statement1.executeQuery(getItemQuery);
        rs.next();
        int Items = Integer.parseInt(rs.getString(1));
        if(Items == 0){
            catalogError.setText("Error: Book is not available, please reserve");
        }
        else{
            Items = Items - 1;
            String updateItems = "UPDATE book SET BookItems = '"+ Items + "' WHERE Title = '" + title + "'";
            Statement statement2 = connectDB.createStatement();
            statement2.executeUpdate(updateItems);
            String getISBNQuery = "SELECT ISBN FROM book WHERE Title = '" + title + "'";
            Statement statement3 = connectDB.createStatement();
            ResultSet rs2 = statement3.executeQuery(getISBNQuery);
            rs2.next();
            String ISBN = rs2.getString(1);
            String DueDate = "2022/01/21";
            String insertCheckout = "Insert INTO checkout(DueDate, ISBN, BorrowerID) VALUES ('" + DueDate + "','" + ISBN + "','" + Username + "')";
            Statement statement4 = connectDB.createStatement();
            statement4.executeUpdate(insertCheckout);
            catalogStatus.setText("Your book is now checked out!");
        }
    }
    public void reserve() throws SQLException {
        index = catalog_tableView.getSelectionModel().getFocusedIndex();
        if(index <= -1){
            return;
        }
        String title =  Title_catalogColumn.getCellData(index);
        String getItemQuery = "SELECT BookItems FROM book WHERE Title = '" + title + "'";
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        Statement statement1 = connectDB.createStatement();
        ResultSet rs = statement1.executeQuery(getItemQuery);
        rs.next();
        int Items = Integer.parseInt(rs.getString(1));
        if(Items == 0){
            String getISBNQuery = "SELECT ISBN FROM book WHERE Title = '" + title + "'";
            Statement statement2 = connectDB.createStatement();
            ResultSet rs2 = statement2.executeQuery(getISBNQuery);
            rs2.next();
            String ISBN = rs2.getString(1);
            String insertReserve = "Insert INTO reserve(ISBN, ReserverID) VALUES ('" + ISBN + "','" + Username + "')";
            Statement statement3 = connectDB.createStatement();
            statement3.executeUpdate(insertReserve);
            catalogStatus.setText("Your book is now reserved!");
        }
        else{
            catalogError.setText("Book is available for checkout, you can't reserve!");
        }
    }
    public void cancelReservation() throws SQLException {
        index = Reserve_tableView.getSelectionModel().getFocusedIndex();
        if(index <= -1){
            return;
        }
        String title =  Title_reserveColumn.getCellData(index);
        String getISBNQuery = "SELECT ISBN FROM book WHERE Title = '" + title + "'";
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        Statement statement1 = connectDB.createStatement();
        ResultSet rs = statement1.executeQuery(getISBNQuery);
        rs.next();
        String ISBN = rs.getString(1);
        String removeReserve = "DELETE FROM reserve WHERE ISBN = '" + ISBN + "' AND ReserverID = '" + Username + "'";
        Statement statement2 = connectDB.createStatement();
        statement2.executeUpdate(removeReserve);
        ReserveLabel.setText("Book reserve cancelled successfully!");

    }
    public void renew() throws SQLException {
        index = Borrow_tableView.getSelectionModel().getFocusedIndex();
        if(index <= -1){
            return;
        }
        String Title =  Title_borrowColumn.getCellData(index);
        String DueDate = DueDate_borrowColumn.getCellData(index);


        String getISBNQuery = "SELECT ISBN FROM book WHERE Title = '" + Title + "'";
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        Statement statement1 = connectDB.createStatement();
        ResultSet rs = statement1.executeQuery(getISBNQuery);
        rs.next();
        String ISBN = rs.getString(1);
        int cutMonth = Integer.parseInt(DueDate.substring(5,7));
        int cutYear = Integer.parseInt(DueDate.substring(0, 4));
        String newDueDate ="";
        cutMonth = cutMonth + 3;
        if(cutMonth <= 9) {
            newDueDate = cutYear + "/0"+cutMonth + "/" + DueDate.substring(8, 10);
        }
        else if(cutMonth>9 && cutMonth <= 12){
            newDueDate = cutYear + "/" + cutMonth + "/" + DueDate.substring(8, 10);
        }
        else {
            cutYear = cutYear + 1;
            cutMonth = cutMonth - 12;
            newDueDate = cutYear + "/0" + cutMonth + "/" + DueDate.substring(8, 10);
        }
        System.out.println(newDueDate);
        String updateDate = "UPDATE checkout SET DueDate = '" + newDueDate + "' Where ISBN = '" +ISBN + "'";
        Statement statement2 = connectDB.createStatement();
        statement2.executeUpdate(updateDate);
        borrowLabel.setText("Book renewed successfully!");
    }
    public void returnBorrow() throws SQLException {
        index = Borrow_tableView.getSelectionModel().getFocusedIndex();
        if(index <= -1){
            return;
        }
        String title =  Title_borrowColumn.getCellData(index);
        String getISBNQuery = "SELECT ISBN FROM book WHERE Title = '" + title + "'";
        String getItems = "SELECT BookItems FROM book WHERE Title = '" + title + "'";
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        Statement statement1 = connectDB.createStatement();
        ResultSet rs = statement1.executeQuery(getISBNQuery);
        rs.next();
        String ISBN = rs.getString(1);
        Statement statement2 = connectDB.createStatement();
        ResultSet rs2 = statement2.executeQuery(getItems);
        rs2.next();
        int Items = Integer.parseInt(rs2.getString(1));
        Items = Items + 1;
        String returnBorrowed = "DELETE FROM checkout WHERE ISBN = '" + ISBN + "' AND BorrowerID = '" + Username + "'";
        String updateItmes = "UPDATE book SET BookItems = '" + Items + "' Where ISBN = '" +ISBN + "'";
        Statement statement3 = connectDB.createStatement();
        statement3.executeUpdate(updateItmes);
        Statement statement4 = connectDB.createStatement();
        statement4.executeUpdate(returnBorrowed);
        borrowLabel.setText("Book returned successfully!");
    }



    public void toInventory(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MemberInventory.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //LibrarianHP buttons
    public void backToLibrarianHP(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LibrarianHP.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toAddBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LibrarianAddBook.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void addAbookQuery(ActionEvent event) throws IOException{
        if(book_ISBN.getText().isBlank() == false && book_title.getText().isBlank() == false && book_author_FNAME.getText().isBlank() == false && book_author_LNAME.getText().isBlank() == false && book_publisher_FNAME.getText().isBlank() == false && book_publisher_LNAME.getText().isBlank() == false && book_subject.getText().isBlank() == false && book_publication_date.getText().isBlank() == false && book_items.getText().isBlank() == false && book_rackNumber.getText().isBlank() == false) {
            String ISBN = book_ISBN.getText();
            String title = book_title.getText();
            String authorFirst = book_author_FNAME.getText();
            String authorLast = book_author_LNAME.getText();
            String publisherFirst = book_publisher_FNAME.getText();
            String publisherLast = book_publisher_LNAME.getText();
            String subject = book_subject.getText();
            String publication_date = book_publication_date.getText();
            int items = Integer.parseInt(book_items.getText());
            int rackNumber = Integer.parseInt(book_rackNumber.getText());
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectDB = connection.getConnection();
            String insertAuthor = "Insert INTO author(Fname, Lname) VALUES ('" + authorFirst + "','" + authorLast + "')";
            String insertPublisher = "Insert INTO publisher(Fname, Lname) VALUES ('" + publisherFirst + "','" + publisherLast + "')";
            try {
                Statement statement1 = connectDB.createStatement();
                statement1.executeUpdate(insertAuthor);
                Statement statement2 = connectDB.createStatement();
                statement2.executeUpdate(insertPublisher);

                String selectAuthorID = "SELECT AuthorID FROM author WHERE Fname = '" + authorFirst + "' AND Lname = '" + authorLast + "'";
                String selectPublisherID = "SELECT PublisherID FROM publisher WHERE Fname = '" + publisherFirst + "' AND Lname = '" + publisherLast + "'";
                Statement statement3 = connectDB.createStatement();
                ResultSet authorIDSet = statement3.executeQuery(selectAuthorID);
                Statement statement4 = connectDB.createStatement();
                ResultSet publisherIDSet = statement4.executeQuery(selectPublisherID);

                authorIDSet.next();
                String authorID = authorIDSet.getString(1);
                publisherIDSet.next();
                String publisherID = publisherIDSet.getString(1);

                String value = "no";
                String insertAttributes = "Insert INTO book(ISBN, Title, Subject, BookItems, RackNum, IsLoaned, Date, AuthorID, PublisherID) VALUES ('";
                String insertValues = ISBN + "','" + title + "','" + subject + "','" + items + "','" + rackNumber + "','"+ value + "','" + publication_date + "','" + authorID + "','" + publisherID + "')";
                String insertBook = insertAttributes + insertValues;
                Statement statement5 = connectDB.createStatement();
                statement5.executeUpdate(insertBook);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            root = FXMLLoader.load(getClass().getResource("LibrarianHP.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            bookError.setText("Error: Please fill all required inputs");
        }

    }

    public void toEditBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LibrarianEditBook.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toEditBookDetails(ActionEvent event) throws IOException {
        index = Edit_tableView.getSelectionModel().getFocusedIndex();
        if(index <= -1){
            return;
        }
        ISBNGlobal = ISBN_editColumn.getCellData(index);
        System.out.println(ISBNGlobal);
        root = FXMLLoader.load(getClass().getResource("LibrarianEditBookDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void editBookDatabase(){
        if(editBook_ISBN.getText().isBlank() == false && editBook_title.getText().isBlank() == false && editBook_author_FNAME.getText().isBlank() == false && editBook_author_LNAME.getText().isBlank() == false && editBook_publisher_FNAME.getText().isBlank() == false && editBook_publisher_LNAME.getText().isBlank() == false && editBook_subject.getText().isBlank() == false && editBook_publication_date.getText().isBlank() == false && editBook_items.getText().isBlank() == false && editBook_rackNumber.getText().isBlank() == false) {
            System.out.println(ISBNGlobal);
            String ISBN = editBook_ISBN.getText();
            String title = editBook_title.getText();
            String authorFirst = editBook_author_FNAME.getText();
            String authorLast = editBook_author_LNAME.getText();
            String publisherFirst = editBook_publisher_FNAME.getText();
            String publisherLast = editBook_publisher_LNAME.getText();
            String subject = editBook_subject.getText();
            String publication_date = editBook_publication_date.getText();
            int items = Integer.parseInt(editBook_items.getText());
            int rackNumber = Integer.parseInt(editBook_rackNumber.getText());

            DatabaseConnection connection = new DatabaseConnection();
            Connection connectDB = connection.getConnection();

            String authorIDQuery = "SELECT AuthorID FROM book WHERE ISBN = '" + ISBNGlobal + "'";
            String publisherIDQuery = "SELECT PublisherID FROM book WHERE ISBN = '" + ISBNGlobal + "'";

            try {

                Statement statement1 = connectDB.createStatement();
                ResultSet authorIDSet = statement1.executeQuery(authorIDQuery);
                Statement statement2 = connectDB.createStatement();
                ResultSet publisherIDSet = statement2.executeQuery(publisherIDQuery);
                authorIDSet.next();
                int authorID = Integer.parseInt(authorIDSet.getString(1));
                publisherIDSet.next();
                int publisherID = Integer.parseInt(publisherIDSet.getString(1));
                String updateBook = "UPDATE book SET ISBN = '" + ISBN + "', Title = '" +title+ "', Subject ='" +subject+ "', BookItems = '" +items+ "', RackNum = '" +rackNumber+ "', Date = '" +publication_date+ "', AuthorID = '" +authorID+ "', PublisherID = '" +publisherID + "' WHERE ISBN = '" + ISBNGlobal + "'";
                String updateAuthor = "UPDATE author SET Fname = '" +authorFirst +"', Lname = '" + authorLast + "' WHERE AuthorID = '" + authorID + "'";
                String updatePublisher = "UPDATE publisher SET Fname = '" +publisherFirst +"', Lname = '" +publisherLast+ "'WHERE PublisherID = '" + publisherID + "'";
                Statement statement3 = connectDB.createStatement();
                statement3.executeUpdate(updateBook);
                Statement statement4 = connectDB.createStatement();
                statement4.executeUpdate(updateAuthor);
                Statement statement5 = connectDB.createStatement();
                statement5.executeUpdate(updatePublisher);
                editSuccess.setText("Book Successfully updated!");

            }catch (Exception e){
            }
        }
        else{
            editSuccess.setStyle("-fx-text-fill: RED;");
            editSuccess.setText("Error: Please enter all details");
        }

    }
    public void toRemoveBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LibrarianRemoveBook.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void removeAbook() throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        index = Remove_tableView.getSelectionModel().getFocusedIndex();
        if(index <= -1){
            return;
        }
        String ISBN = ISBN_removeColumn.getCellData(index);
        System.out.println(ISBN);
        String removeBook = "DELETE FROM book WHERE ISBN = '" + ISBN + "'";
        Statement statement1 = connectDB.createStatement();
        statement1.execute(removeBook);
        Remove_tableView.getItems().remove(index);
    }

    public void toRequestBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LibrarianRequestBook.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toCancelSub(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LibrarianCancelSub.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void cancelSub() throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        index = tableview.getSelectionModel().getFocusedIndex();
        if(index <= -1){
            return;
        }
        int ID = Integer.parseInt(idColumn.getCellData(index).toString());
        System.out.println(ID);
        String removeMember = "DELETE FROM memeber WHERE ID = '" + ID + "'";
        Statement statement3 = connectDB.createStatement();
        statement3.execute(removeMember);
        tableview.getItems().remove(index);
    }

    public void toRegisterNew(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LibrarianRegisterNew.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void registerUser(ActionEvent event) throws IOException {
        if(registerID.getText().isBlank() == false && registerFNAME.getText().isBlank() == false && registerLNAME.getText().isBlank() == false && registerAddress.getText().isBlank() == false && registerPhone.getText().isBlank() == false  && registerAge.getText().isBlank() == false && registerPassword.getText().isBlank() == false &&(registerPremium.isSelected() == true || registerBasic.isSelected()==true)){
            int ID = Integer.parseInt(registerID.getText());
            String Fname = registerFNAME.getText();
            String Lname = registerLNAME.getText();
            String Address = registerAddress.getText();
            String Phone = registerPhone.getText();
            String Email = registerEmail.getText();
            String Password = registerPassword.getText();
            int Age = Integer.parseInt(registerAge.getText());
            String membershipStatus;
            if(registerPremium.isSelected()){
                membershipStatus = "yes";
            }else{
                membershipStatus = "no";
            }
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectDB = connection.getConnection();
            String insertMember = "Insert INTO memeber(ID, Fname, Lname, Address, PhoneNum, Email, Age, IsPremium, Password) VALUES ('";
            String insertValues = ID + "','" +Fname+ "','" +Lname+ "','" +Address+ "','" + Phone+ "','"+ Email+ "','" +Age+ "','" +membershipStatus+ "','" + Password + "')";
            String insertToRegister = insertMember + insertValues;

            try {
                Statement statement1 = connectDB.createStatement();
                statement1.executeUpdate(insertToRegister);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            root = FXMLLoader.load(getClass().getResource("LibrarianHP.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            registerError.setText("Please enter required values");
        }
    }


}