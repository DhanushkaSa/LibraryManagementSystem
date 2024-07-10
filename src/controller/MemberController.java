package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dto.MemberDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.custom.MemberService;

public class MemberController {

    private MemberService service = (MemberService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceType.MEMBERS);

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtTelephone;

    @FXML
    private AnchorPane root;

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
    void btnDeleteOnAction(ActionEvent event) {
        delete();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {

        save();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        update();
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        this.root.getChildren().clear();
        URL resource = getClass().getResource("/view/Home.fxml");
        Parent root = FXMLLoader.load(resource);
        this.root.getChildren().add(root);
    }

    @FXML
    void get(MouseEvent event) {
        get();
    }

    public void save() {
        try {
            MemberDto dto = new MemberDto(txtMemberId.getText(), txtFirstName.getText(), txtLastName.getText(),
                    txtAddress.getText(), Integer.parseInt(txtAge.getText()), txtTelephone.getText());
            String resp = service.save(dto);
            JOptionPane.showMessageDialog(null, resp);
            clearData();
            loadTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void delete() {
        try {
            String Member_Id = txtMemberId.getText();
            String resp = service.Delete(Member_Id);
            JOptionPane.showMessageDialog(null, resp);
            clearData();
            loadTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    public void update() {

        try {
            MemberDto dto = new MemberDto(txtMemberId.getText(), txtFirstName.getText(), txtLastName.getText(),
                    txtAddress.getText(), Integer.parseInt(txtAge.getText()), txtTelephone.getText());
            String resp = service.update(dto);
            JOptionPane.showMessageDialog(null, resp);
            clearData();
            loadTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void get() {
        try {
            MemberDto Member = tblMember.getSelectionModel().getSelectedItem();
            String Member_Id = Member.getMember_Id();

            MemberDto dto = service.get(Member_Id);
            txtMemberId.setText(dto.getMember_Id());
            txtFirstName.setText(dto.getFirstName());
            txtLastName.setText(dto.getLastName());
            txtAddress.setText(dto.getAddress());
            txtAge.setText(Integer.toString(dto.getAge()));
            txtTelephone.setText(dto.getTelephone());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void clearData() {
        txtMemberId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtAddress.setText("");
        txtAge.setText("");
        txtTelephone.setText("");
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
