package controller;

import java.io.IOException;
import java.net.URL;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUser;

    @FXML
    private AnchorPane root;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtUser.setText("");
        txtPassword.setText("");
    }

    

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        
        String userName = txtUser.getText();
        String password = txtPassword.getText();
        if (userName.equals("admin") & password.equals("0286")) {
            this.root.getChildren().clear();
            URL resource = getClass().getResource("/view/Home.fxml");
            Parent root = FXMLLoader.load(resource);
            this.root.getChildren().add(root);
            
        }
        else{
            JOptionPane.showMessageDialog(null, userName+" username or password incorrect !!", password, 0);
        }
    }

}
