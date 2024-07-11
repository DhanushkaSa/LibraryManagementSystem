package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.custom.BookCategoryDao;
import dto.BookCategoryDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.custom.BookCategoryService;

public class BookCategoryController {

    BookCategoryService service = (BookCategoryService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceType.BOOK_CATEGORY);

    @FXML
    private TableView<BookCategoryDto> CategoryTable;

    @FXML
    private TableColumn<BookCategoryDto, String> CategoryId;

    @FXML
    private TableColumn<BookCategoryDto, String> CategoryName;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtCategoryId;

    @FXML
    private TextField txtCategoryName;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {

        this.root.getChildren().clear();
        URL resource = getClass().getResource("/view/Home.fxml");
        Parent root = FXMLLoader.load(resource);
        this.root.getChildren().add(root);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {

            if (txtCategoryId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Select a Book Category !!!");
            } else {
                String BookCategory_Id = txtCategoryId.getText();

                int value = JOptionPane.showConfirmDialog(null, "Do you want to delete this book category ?",
                        BookCategory_Id, 0);
                if (value == 0) {
                    String resp = service.Delete(BookCategory_Id);

                }
                clearData();
                loadTable();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Delete Fail !!");
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {
            BookCategoryDto dto = new BookCategoryDto(txtCategoryId.getText(), txtCategoryName.getText());
            if (dto.getCategory_Id().trim().isEmpty() & dto.getCategory_Name().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please insert details !!");
            }
            else{
                String resp = service.Save(dto);
                JOptionPane.showMessageDialog(null, resp);
                clearData();
                loadTable();
            }
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Save Fail !!");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            BookCategoryDto dto = new BookCategoryDto(txtCategoryId.getText(), txtCategoryName.getText());
            if(dto.getCategory_Id().trim().isEmpty() & dto.getCategory_Name().trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please Select a Book Category !!!");
            }else{
                String resp = service.Update(dto);
                JOptionPane.showMessageDialog(null, resp);
                clearData();
                loadTable();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @FXML
    void tableOnClick(MouseEvent event) {
        get();
    }

    public void clearData() {
        txtCategoryId.setText("");
        txtCategoryName.setText("");
    }

    @FXML
    public void initialize() throws Exception {
        CategoryId.setCellValueFactory(new PropertyValueFactory<>("Category_Id"));
        CategoryName.setCellValueFactory(new PropertyValueFactory<>("Category_Name"));
        loadTable();
    }

    public void loadTable() throws Exception {
        ArrayList<BookCategoryDto> arrayDto = service.getAll();
        ObservableList<BookCategoryDto> data = FXCollections.observableArrayList(arrayDto);
        CategoryTable.setItems(data);
    }

    public void get() {
        try {
            BookCategoryDto BookCategory = CategoryTable.getSelectionModel().getSelectedItem();
            String BookCategory_Id = BookCategory.getCategory_Id();
            BookCategoryDto dto = service.get(BookCategory_Id);
            txtCategoryId.setText(dto.getCategory_Id());
            txtCategoryName.setText(dto.getCategory_Name());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
}
