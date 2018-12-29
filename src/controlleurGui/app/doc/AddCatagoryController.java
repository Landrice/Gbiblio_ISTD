/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui.app.doc;

import bdd.BddPropreties;
import bdd.SQLSyntax;
import bdd.bddConnection;
import gettersSetters.Catagory;
import gettersSetters.CatagoryBLL;
import gettersSetters.CatagoryGetway;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class AddCatagoryController implements Initializable {

    @FXML
    private TextField tfCatagoryName;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;
    @FXML
    public Label lblHeaderContent;
    @FXML
    private Button btnClose;
    private String userId;
    Catagory catagory = new Catagory();
    CatagoryGetway catagoryGetway = new CatagoryGetway();
    CatagoryBLL catagoryBLL = new CatagoryBLL();
    SQLSyntax sql = new SQLSyntax();
    
     BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    bddConnection dbCon = new bddConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    private userNameMedia media;

    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        userId = media.getId();
        this.media = media;
    }
     public String catagoryId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSaveCatagory(ActionEvent event) {
        if (isNotNull()) {
            catagory.catagoryName = tfCatagoryName.getText().trim();
            catagory.creatorId = userId;
            catagoryBLL.save(catagory);

        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (isNotNull()) {
            catagory.catagoryName = tfCatagoryName.getText().trim();
            catagory.creatorId = userId;
            catagoryBLL.update(catagory);

        }
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage=(Stage)btnClose.getScene().getWindow();
        stage.close();
    }
    public boolean isNotNull() {
        boolean isNotNull;
        if (tfCatagoryName.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("erreur");
            alert.setHeaderText("ERREUR: champ vide ");
            alert.setContentText("veuiller remplir les champs necessaires");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            

            isNotNull = false;
        } else {
            isNotNull = true;
        }
        return isNotNull;
    }
    
    public void showDetails() {
        catagory.id = catagoryId;
        catagoryGetway.selectedView(catagory);
        tfCatagoryName.setText(catagory.catagoryName);
    }
}
