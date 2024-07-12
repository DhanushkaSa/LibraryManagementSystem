package controller;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class BookController {

    @FXML
    private TableView<?> BookTable;

    @FXML
    private TableColumn<?, ?> Author;

    @FXML
    private TableColumn<?, ?> BookCount;

    @FXML
    private TableColumn<?, ?> BookId;

    @FXML
    private TableColumn<?, ?> BookName;

    @FXML
    private TableColumn<?, ?> CategoryId;

    @FXML
    private TableColumn<?, ?> Language;

    @FXML
    private TableColumn<?, ?> Status;

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

    @FXML
    void btnBackOnClick(ActionEvent event) throws IOException {
       this.root.getChildren().clear();
       URL resource = getClass().getResource("/view/Home.fxml");
       Parent root=FXMLLoader.load(resource);
       this.root.getChildren().add(root);
    }

    @FXML
    void btnDeleteOnClick(ActionEvent event) {

    }

    @FXML
    void btnSaveOnClick(ActionEvent event) {

    }

    @FXML
    void btnSearchOnClick(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnClick(ActionEvent event) {
    }

}
