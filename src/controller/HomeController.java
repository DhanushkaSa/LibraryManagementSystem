package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.net.URL;

import java.util.ArrayList;

import dto.BookDto;
import dto.MemberDto;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.ServiceFactory;
import service.ServiceFactory.ServiceType;
import service.custom.BookService;
import service.custom.MemberService;

public class HomeController {

    private MemberService service = (MemberService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceType.MEMBERS);

    private BookService bookService = (BookService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceType.BOOKS);

    public HomeController() throws ClassNotFoundException, SQLException {

    }

    @FXML
    private TableView<MemberDto> tblMember;

    @FXML
    private TableColumn<MemberDto, String> Address;

    @FXML
    private TableColumn<MemberDto, Integer> Age;

    @FXML
    private TableColumn<MemberDto, String> FirstName;

    @FXML
    private TableColumn<MemberDto, String> LastName;

    @FXML
    private TableColumn<MemberDto, String> MemberId;

    @FXML
    private TableColumn<MemberDto, String> Telephone;
    // Book Table
    @FXML
    private TableView<BookDto> tblBook;

    @FXML
    private TableColumn<BookDto, String> colAuthor;

    @FXML
    private TableColumn<BookDto, Integer> colBookCount;

    @FXML
    private TableColumn<BookDto, String> colBookId;

    @FXML
    private TableColumn<BookDto, String> colBookName;

    @FXML
    private TableColumn<BookDto, String> colCategoryId;

    @FXML
    private TableColumn<BookDto, String> colLanguage;

    @FXML
    private Label lblDifferentBookCount;

    @FXML
    private TextField searchBooks;

    @FXML
    private AnchorPane root;

    @FXML
    private Label dateTimeLabel;

    @FXML
    private TextField searchMember;

    @FXML
    void btnBookCategoryOnAction(ActionEvent event) throws IOException {
        this.root.getChildren().clear();
        URL resource = getClass().getResource("/view/BookCategory.fxml");
        Parent root = FXMLLoader.load(resource);
        this.root.getChildren().add(root);

    }

    @FXML
    void btnBooksOnAction(ActionEvent event) throws IOException {
        this.root.getChildren().clear();
        URL resource = getClass().getResource("/view/Book.fxml");
        Parent root = FXMLLoader.load(resource);
        this.root.getChildren().add(root);
    }

    @FXML
    void btnBorrowOnAction(ActionEvent event) throws IOException {
        this.root.getChildren().clear();
        URL resource = getClass().getResource("/view/Borrow.fxml");
        Parent root = FXMLLoader.load(resource);
        this.root.getChildren().add(root);
    }

    @FXML
    private Label lblMemberCount;

    @FXML
    void btnMembersOnAction(ActionEvent event) throws IOException {

        this.root.getChildren().clear();
        URL resource = getClass().getResource("/view/Member.fxml");
        Parent root = FXMLLoader.load(resource);
        this.root.getChildren().add(root);

    }

    private ObservableList<MemberDto> masterData = FXCollections.observableArrayList();
    private ObservableList<BookDto> BookData = FXCollections.observableArrayList();

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");

    @FXML
    public void initialize() throws Exception {
        // Member Table

        MemberId.setCellValueFactory(new PropertyValueFactory<>("member_Id"));
        FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        Age.setCellValueFactory(new PropertyValueFactory<>("age"));
        Telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        loadTable();

        FilteredList<MemberDto> filteredData = new FilteredList<>(masterData, p -> true);
        searchMember.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(member -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (member.getMember_Id().toLowerCase().contains(lowerCaseFilter) ||
                        member.getFirstName().toLowerCase().contains(lowerCaseFilter) ||
                        member.getLastName().toLowerCase().contains(lowerCaseFilter) ||
                        member.getAddress().toLowerCase().contains(lowerCaseFilter) ||
                        member.getTelephone().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<MemberDto> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblMember.comparatorProperty());
        tblMember.setItems(sortedData);

        // Book Table
        colBookId.setCellValueFactory(new PropertyValueFactory<>("Book_Id"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("Book_Name"));
        colLanguage.setCellValueFactory(new PropertyValueFactory<>("Language"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        colBookCount.setCellValueFactory(new PropertyValueFactory<>("Book_Count"));
        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("Category_Id"));
        loadBooks();

        FilteredList<BookDto> filteredBook = new FilteredList<>(BookData, p -> true);
        searchBooks.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredBook.setPredicate(book -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (book.getBook_Id().toLowerCase().contains(lowerCaseFilter) ||
                        book.getBook_Name().toLowerCase().contains(lowerCaseFilter) ||
                        book.getLanguage().toLowerCase().contains(lowerCaseFilter) ||
                        book.getAuthor().toLowerCase().contains(lowerCaseFilter) ||
                        book.getCategory_Id().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<BookDto> sortedBooks = new SortedList<>(filteredBook);
        sortedBooks.comparatorProperty().bind(tblBook.comparatorProperty());
        tblBook.setItems(sortedBooks);

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDateTime now = LocalDateTime.now();
            dateTimeLabel.setText(now.format(dateTimeFormatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    public void loadTable() throws ClassNotFoundException, SQLException {
        ArrayList<MemberDto> dtoArray = service.getAll();

        ObservableList<MemberDto> data = FXCollections.observableArrayList(dtoArray);
        this.tblMember.setItems(data);
        int MemberCount = tblMember.getItems().size();
        lblMemberCount.setText(Integer.toString(MemberCount));
        masterData.setAll(dtoArray);
    }

    public void loadBooks() throws Exception {
        ArrayList<BookDto> dtoArray = bookService.getAll();

        ObservableList<BookDto> data = FXCollections.observableArrayList(dtoArray);
        this.tblBook.setItems(data);
        int BookCount = tblBook.getItems().size();
        lblDifferentBookCount.setText(Integer.toString(BookCount));
        BookData.setAll(data);
    }

}
