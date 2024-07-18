package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dto.BookDto;
import dto.BorrowDto;
import dto.MemberDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.ServiceFactory.ServiceType;
import service.custom.BookService;
import service.custom.BorrowService;
import service.custom.MemberService;

public class BorrowController {
    private ArrayList<BookDto> bookArray = new ArrayList<>();
    private BookService bookService = (BookService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceType.BOOKS);

    private MemberService memberService = (MemberService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceType.MEMBERS);

    private BorrowService borrowService = (BorrowService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceType.BORROW);

    @FXML
    private Label lblBookDetails;

    @FXML
    private Label lblMemberDetails;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBorrowId;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtDueDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BorrowDto> tblBorrow;

    @FXML
    private TableColumn<BorrowDto, String> colBookId;

    @FXML
    private TableColumn<BorrowDto, String> colBorrowDate;

    @FXML
    private TableColumn<BorrowDto, String> colBorrowId;

    @FXML
    private TableColumn<BorrowDto, String> colDueDate;

    @FXML
    private TableColumn<BorrowDto, String> colMemberId;

    @FXML
    public void initialize() throws Exception {
        colBorrowId.setCellValueFactory(new PropertyValueFactory<>("Borrow_Id"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("Member_Id"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("Book_Id"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("Borrow_Date"));
        loadTable();
    }

    public void loadTable() throws Exception {
        ArrayList<BorrowDto> array = borrowService.getAll();
        ObservableList<BorrowDto> observableList = FXCollections.observableArrayList(array);
        tblBorrow.setItems(observableList);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {

        this.root.getChildren().clear();
        URL resource = getClass().getResource("/view/Home.fxml");
        Parent root = FXMLLoader.load(resource);
        this.root.getChildren().add(root);

    }

    @FXML
    void btnBookSearchOnAction(ActionEvent event) {

        try {
            String BookId = txtBookId.getText();
            BookDto bookDto = bookService.get(BookId);
            lblBookDetails.setText(bookDto.getBook_Id() + " | " + bookDto.getBook_Name() + " | " + bookDto.getAuthor()
                    + " | " + bookDto.getLanguage() + " | " + bookDto.getBook_Count());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter valid Book Id !!");

        }
    }

    @FXML
    void btnMemberSearchOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {

        try {
            String memberId = txtMemberId.getText();
            MemberDto memberDto = memberService.get(memberId);
            lblMemberDetails.setText(memberDto.getMember_Id() + " | " + memberDto.getFirstName() + " "
                    + memberDto.getLastName() + " | " + memberDto.getTelephone());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter valid Member Id !!");
        }

    }

    @FXML
    void btnPlaceBorrowDataAction(ActionEvent event) {

        try {
            BookDto bookDto = bookService.get(txtBookId.getText());
            bookArray.add(bookDto);

            BorrowDto borrowDto = new BorrowDto();
            borrowDto.setBorrow_Id(txtBorrowId.getText());
            borrowDto.setMember_Id(txtMemberId.getText());
            borrowDto.setBook_Id(bookDto.getBook_Id());
            borrowDto.setDueDate(txtDueDate.getText());

            LocalDate currenDate = LocalDate.now();
            borrowDto.setBorrow_Date(currenDate.toString());
            borrowDto.setBookDtos(bookArray);

            String resp = borrowService.placeBorrow(borrowDto);
            JOptionPane.showMessageDialog(null, resp);
            loadTable();

            refreshPage();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }

    }

    public void refreshPage() {
        try {
            URL resource = getClass().getResource("/view/Borrow.fxml");
            Parent newroot = FXMLLoader.load(resource);
            root.getChildren().setAll(newroot);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Page reload error !!");
        }

    }
}
