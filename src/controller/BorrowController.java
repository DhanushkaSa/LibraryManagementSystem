package controller;

import java.io.IOException;
import java.net.URL;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class BorrowController {

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBorrowId;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private Label lblBookDetails;

    @FXML
    private Label lblMemberDetails;

    @FXML
    private TableView<?> tblBorrow;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBorrowId;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtReturnDate;

    @FXML
    private AnchorPane root;

    @FXML
    void btnAddOnAction(ActionEvent event) {

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

    }

    @FXML
    void btnMemberSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceBorrowDataAction(ActionEvent event) {

    }
}
