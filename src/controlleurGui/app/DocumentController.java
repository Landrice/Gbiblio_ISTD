/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui.app;

import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXToggleButton;
import controlleurGui.app.doc.CurrentDocController;
import controlleurGui.app.doc.ViewAuteurController;
import controlleurGui.app.doc.ViewCatagoryController;
import controlleurGui.app.doc.ViewEditeurController;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class DocumentController implements Initializable {

    @FXML
    public BorderPane bpStore;
    @FXML
    private AnchorPane acHeadStore;
    @FXML
    private JFXToggleButton btndoc;
    @FXML
    private ToggleGroup btnGup;
    @FXML
    private JFXToggleButton btnEditeur;
    @FXML
    private JFXToggleButton btnAuteur;
    @FXML
    private JFXToggleButton btnCat;
    @FXML
    private Label lblHeader;
    @FXML
    private StackPane spMainContent;
    private String usrId;

    private userNameMedia userId;
    
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    public userNameMedia getUserId() {
        return userId;
    }

    public void setUserId(userNameMedia userId) {
        usrId = userId.getId();
        this.userId = userId;
    }
    
    bddConnection dbCon = new bddConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void btnDocOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Documents, Fichier"); 
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/gui/app/doc/CurrentDoc.fxml").openStream()); 
        media.setId(usrId);
        CurrentDocController currentDocController = fXMLLoader.getController();
        currentDocController.setMedia(userId);
        currentDocController.viewDetails();
        currentDocController.apCombobox.getStylesheets().add("/css/StoreCombobox.css");
        currentDocController.settingPermission();
        StackPane acPane = fXMLLoader.getRoot();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnEditeurOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Editeur");
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/gui/app/doc/ViewEditeur.fxml").openStream());
        media.setId(usrId);
        ViewEditeurController viewSupplyerController = fXMLLoader.getController();
        viewSupplyerController.setMedia(userId);
        viewSupplyerController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
        
    }

    @FXML
    private void btnAuteurOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Auteur");
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/gui/app/doc/ViewAuteur.fxml").openStream());
        media.setId(usrId);
        ViewAuteurController viewBrandController = fXMLLoader.getController();
        viewBrandController.setMedia(userId);
        viewBrandController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnCatOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Categories");
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/gui/app/doc/ViewCatagory.fxml").openStream());
        media.setId(usrId);
        ViewCatagoryController viewCatagoryController = fXMLLoader.getController();
        viewCatagoryController.setMedia(userId);
        viewCatagoryController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }
    
    public void settingPermission(){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".droitutilisateur where idUtilisateur=?");
            pst.setString(1, usrId);
            rs = pst.executeQuery();
            while(rs.next()){
                if(rs.getInt(3)==0 && rs.getInt(9) == 0){
                    btnAuteur.setDisable(true);
                }if(rs.getInt(4) == 0 && rs.getInt(10) == 0){
                    btnCat.setDisable(true);
                }if(rs.getInt(5) == 0 && rs.getInt(11) == 0){
                    btnEditeur.setDisable(true);
                }
                else{
                    
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
