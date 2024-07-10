package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.net.URL;

import java.util.ArrayList;




import dto.MemberDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.ServiceFactory.ServiceType;
import service.custom.MemberService;

public class HomeController {

    private MemberService service=(MemberService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.MEMBERS);

    public HomeController() throws ClassNotFoundException, SQLException {
       


    }

    @FXML
    private AnchorPane home;

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

    @FXML
    void btnBookCategoryOnAction(ActionEvent event) {

    }

    @FXML
    void btnBooksOnAction(ActionEvent event) {

    }

    @FXML
    void btnBorrowOnAction(ActionEvent event) {

    }

    @FXML
    private AnchorPane root;

    @FXML
    void btnMembersOnAction(ActionEvent event) throws IOException {
          
         this.root.getChildren().clear();
          URL resource = getClass().getResource("/view/Member.fxml");
          Parent root = FXMLLoader.load(resource);
          this.root.getChildren().add(root);
        
          

    }

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException {
        MemberId.setCellValueFactory(new PropertyValueFactory<>("member_Id"));
        FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        Age.setCellValueFactory(new PropertyValueFactory<>("age"));
        Telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        loadTable();
    }

    public void loadTable() throws ClassNotFoundException, SQLException {
        ArrayList<MemberDto> dtoArray = service.getAll();

        ObservableList<MemberDto> data = FXCollections.observableArrayList(dtoArray);
        this.tblMember.setItems(data);
    }

}
