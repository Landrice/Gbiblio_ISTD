/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui.app.doc;

import bdd.BddPropreties;
import bdd.bddConnection;
import gettersSetters.AuteurBLL;
import gettersSetters.Auteur;
import gettersSetters.AuteurGetway;
import gettersSetters.userNameMedia;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class AddAuteurController implements Initializable {

    @FXML
    private AnchorPane apContent;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;
    @FXML
    public Label lblCaption;
    @FXML
    private Button btnClose;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPName;
    
    private userNameMedia media;

    Auteur brands = new Auteur();
    AuteurGetway brandsGetway = new AuteurGetway();
    AuteurBLL brandBLL = new AuteurBLL();

    public String brandId;
    private String usrId;
    private String supplyerName;
    private String supplyerId;

    bddConnection dbCon = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
     public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        System.out.println(usrId);
        if (isNotNull()) {
            brands.creatorId = usrId;
            brands.auteurnom = tfName.getText();
            brands.auteurprenom = tfPName.getText();
            brandBLL.save(brands);
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (isNotNull()) {
            brands.creatorId = usrId;
            brands.auteurnom = tfName.getText();
            brands.auteurprenom = tfPName.getText();
            brandBLL.update(brands);
        }
    }

    @FXML
    private void apOnMouseDragged(MouseEvent event) {
    }

    @FXML
    private void apOnMousePressed(MouseEvent event) {
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage=(Stage)btnClose.getScene().getWindow();
        stage.close();
    }
    
    public boolean isNotNull() {
        boolean isNotNull;
        if (tfName.getText().trim().isEmpty()
                || tfPName.getText().trim().isEmpty()) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("erreur");
            alert.setHeaderText("Champ vide");
            alert.setContentText("Veuillez remplir les champs");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            
            isNotNull = false;

        } else {
            isNotNull = true;
        }
        return isNotNull;
    }

    public void showDetails() {
        brands.id = brandId;
        brandsGetway.selectedView(brands);
        tfName.setText(brands.auteurnom);
        tfPName.setText(brands.auteurprenom);
    }
    
}
