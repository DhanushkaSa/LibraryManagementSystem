package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dto.BookDto;
import dto.BookReturnDto;
import dto.BorrowDto;
import dto.MemberDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.custom.BookReturnService;
import service.custom.BookService;
import service.custom.BorrowService;
import service.custom.MemberService;

public class BookReturnController {

    private BorrowService borrowService = (BorrowService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceType.BORROW);
    private MemberService memberService = (MemberService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceType.MEMBERS);
    private BookService bookService = (BookService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceType.BOOKS);
    private BookReturnService bookReturnService = (BookReturnService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceType.BOOK_RETURN);

    @FXML
    private TableColumn<BookReturnDto, Double> colFine;

    @FXML
    private TableColumn<BookReturnDto, String> colReturnDate;

    @FXML
    private TableColumn<BookReturnDto, String> colReturnId;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblBorrowDate;

    @FXML
    private Label lblDueDate;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLanguage;

    @FXML
    private Label lblLastName;

    @FXML
    private Label lblTelephone;

    @FXML
    private Label lblBorrowId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BookReturnDto> tblReturnBook;

    @FXML
    private TextField txtBorrowId;

    @FXML
    private TextField txtReturnId;

    @FXML
    private Label lblBookId;

    @FXML
    private Label lblMemberId;

    @FXML
    private Label lblFine;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        this.root.getChildren().clear();
        URL resource = getClass().getResource("/view/Home.fxml");
        Parent node = FXMLLoader.load(resource);
        this.root.getChildren().add(node);

    }

    @FXML
    void btnProcessFineOnAction(ActionEvent event) throws Exception {

        try {
            if (!txtReturnId.getText().isEmpty()) {
                BookReturnDto dto = new BookReturnDto();
                dto.setReturn_Id(txtReturnId.getText());
                BookDto bookDto = bookService.get(lblBookId.getText());
                ArrayList<BookDto> bookDtoArray = new ArrayList<>();
                bookDtoArray.add(bookDto);
                dto.setBookDtos(bookDtoArray);

                LocalDate currentDate = LocalDate.now();
                dto.setReturn_Date(currentDate.toString());

                String dateString = lblDueDate.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(dateString, formatter);
                Long dateRange = ChronoUnit.DAYS.between(date, currentDate);
                Double perDayFine = 10.00;
                Double fine = 0.00;

                if (dateRange > 0) {
                    fine = perDayFine * dateRange;

                    lblFine.setText(Double.toString(fine));

                }
                dto.setFine(fine);
                String response = bookReturnService.save(dto, txtBorrowId.getText());
                new Alert(AlertType.CONFIRMATION, response).show();
                loadTable();

            } else {
                new Alert(Alert.AlertType.ERROR, "Please enter return id !!!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        try {
            String BorrowId = txtBorrowId.getText();
            BorrowDto borrowDto = borrowService.get(BorrowId);

            String MemberId = borrowDto.getMember_Id();
            MemberDto memberDto = memberService.get(MemberId);

            String BookId = borrowDto.getBook_Id();
            BookDto bookDto = bookService.get(BookId);

            lblMemberId.setText(memberDto.getMember_Id());
            lblFirstName.setText(memberDto.getFirstName());
            lblLastName.setText(memberDto.getLastName());
            lblTelephone.setText(memberDto.getTelephone());

            lblDueDate.setText(borrowDto.getDueDate());
            lblBorrowDate.setText(borrowDto.getBorrow_Date());

            lblBookId.setText(bookDto.getBook_Id());
            lblBookName.setText(bookDto.getBook_Name());
            lblLanguage.setText(bookDto.getLanguage());
            lblAuthor.setText(bookDto.getAuthor());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter valid borrow Id !!!");
        }
    }

    @FXML
    public void initialize() throws Exception {

        colReturnId.setCellValueFactory(new PropertyValueFactory<>("Return_Id"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("Return_Date"));
        colFine.setCellValueFactory(new PropertyValueFactory<>("Fine"));

        loadTable();
    }

    public void loadTable() throws Exception {

        ArrayList<BookReturnDto> array = bookReturnService.getAll();
        ObservableList<BookReturnDto> observeArray = FXCollections.observableArrayList(array);
        tblReturnBook.setItems(observeArray);
    }

}
