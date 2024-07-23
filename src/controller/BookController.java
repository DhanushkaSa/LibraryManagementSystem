package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JOptionPane;

import dto.BookCategoryDto;
import dto.BookDto;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.ServiceFactory.ServiceType;
import service.custom.BookCategoryService;
import service.custom.BookService;

public class BookController {

    @FXML
    private TableView<BookDto> BookTable;

    @FXML
    private TableColumn<BookDto, String> Author;

    @FXML
    private TableColumn<BookDto, Integer> BookCount;

    @FXML
    private TableColumn<BookDto, String> BookId;

    @FXML
    private TableColumn<BookDto, String> BookName;

    @FXML
    private TableColumn<BookDto, String> CategoryId;

    @FXML
    private TableColumn<BookDto, String> Language;

    @FXML
    private Label lblCategoryData;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookCount;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtCategoryId;

    @FXML
    private TextField txtLanguage;

    private BookService service = (BookService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceType.BOOKS);

    private BookCategoryService serviceCategory = (BookCategoryService) ServiceFactory.getInstance()
            .getService(ServiceType.BOOK_CATEGORY);

    @FXML
    void btnBackOnClick(ActionEvent event) throws IOException {
        this.root.getChildren().clear();
        URL resource = getClass().getResource("/view/Home.fxml");
        Parent root = FXMLLoader.load(resource);
        this.root.getChildren().add(root);
    }

    @FXML
    void btnDeleteOnClick(ActionEvent event) {
        try {
            String BookId = txtBookId.getText();
            if (BookId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a book !!");
            } else {
                int value = JOptionPane.showConfirmDialog(null, "Do you want to delete this book ?", BookId, 0);
                if (value == 0) {
                    String resp = service.delete(BookId);
                }
                clearData();
                loadTable();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Delete Fail !!");
        }
    }

    @FXML
    void btnSaveOnClick(ActionEvent event) {

        try {
            BookDto Dto = new BookDto(txtBookId.getText(), txtBookName.getText(), txtLanguage.getText(),
                    txtAuthor.getText(), Integer.parseInt(txtBookCount.getText()), txtCategoryId.getText());
            String resp = service.save(Dto);
            JOptionPane.showMessageDialog(null, resp);
            clearData();
            loadTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Save Fail !!");
        }
    }

    @FXML
    void btnSearchOnClick(ActionEvent event) {
        try {

            String Category_Id = txtCategoryId.getText();
            BookCategoryDto dto = serviceCategory.get(Category_Id);
            lblCategoryData.setText(dto.getCategory_Id() + " | " + dto.getCategory_Name());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not Available");
        }

    }

    @FXML
    void btnUpdateOnClick(ActionEvent event) {
        try {
            BookDto dto = new BookDto(txtBookId.getText(), txtBookName.getText(), txtLanguage.getText(),
                    txtAuthor.getText(), Integer.parseInt(txtBookCount.getText()), txtCategoryId.getText());
            String resp = service.update(dto);
            JOptionPane.showMessageDialog(null, resp);
            clearData();
            loadTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    public void initialize() throws Exception {
        BookId.setCellValueFactory(new PropertyValueFactory<>("Book_Id"));
        BookName.setCellValueFactory(new PropertyValueFactory<>("Book_Name"));
        Language.setCellValueFactory(new PropertyValueFactory<>("Language"));
        Author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        BookCount.setCellValueFactory(new PropertyValueFactory<>("Book_Count"));
        CategoryId.setCellValueFactory(new PropertyValueFactory<>("Category_Id"));
        loadTable();

    }

    public void loadTable() throws Exception {
        ArrayList<BookDto> array = service.getAll();
        ObservableList<BookDto> data = FXCollections.observableArrayList(array);
        BookTable.setItems(data);
    }

    @FXML
    void mouseClickOnTable(MouseEvent event) throws Exception {
        BookDto Book = BookTable.getSelectionModel().getSelectedItem();
        String BookId = Book.getBook_Id();
        BookDto dto = service.get(BookId);
        txtBookId.setText(dto.getBook_Id());
        txtBookName.setText(dto.getBook_Name());
        txtLanguage.setText(dto.getLanguage());
        txtAuthor.setText(dto.getAuthor());
        txtBookCount.setText(Integer.toString(dto.getBook_Count()));
        txtCategoryId.setText(dto.getCategory_Id());
    }

    public void clearData() {
        txtBookId.setText("");
        txtBookName.setText("");
        txtLanguage.setText("");
        txtAuthor.setText("");
        txtBookCount.setText("");
        txtCategoryId.setText("");
    }

}
