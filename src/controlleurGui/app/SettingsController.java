/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui.app;

import bdd.BddPropreties;
import bdd.bddConnection;
import controlleurGui.app.settings.MyAccountController;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SettingsController implements Initializable {

    @FXML
    public BorderPane bpSettings;
    @FXML
    private MenuItem miMyASccount;
    @FXML
    private MenuItem miBackup;
    @FXML
    private Label lblCurrentStatus;
    @FXML
    private StackPane spSettingContent;
    
    bddConnection dbCon = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    
    private String userID;
    
    userNameMedia usrMedia;
    public userNameMedia getUsrMedia() {
        return usrMedia;
    }

    public void setUsrMedia(userNameMedia usrMedia) {
        userID = usrMedia.getId();
        this.usrMedia = usrMedia;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void miMyASccountOnClick(ActionEvent event) throws IOException {
        System.out.println(userID);
        lblCurrentStatus.setText("Mon Compte");
        
        MyAccountController myAccount = new MyAccountController();
        userNameMedia usrIdMedia = new userNameMedia();
        
        FXMLLoader fxmlload = new FXMLLoader();
        fxmlload.load(getClass().getResource("/gui/app/settings/MyAccount.fxml").openStream());
        
        usrIdMedia.setId(userID);
        
        MyAccountController mAccount = fxmlload.getController();
        
        mAccount.setUsrMediaID(usrMedia);
        mAccount.showDetails();
        AnchorPane acPane = fxmlload.getRoot();
        
        spSettingContent.getChildren().clear();
        spSettingContent.getChildren().add(acPane);
    }

    @FXML
    private void miBackupOnAction(ActionEvent event) {
    }
}
