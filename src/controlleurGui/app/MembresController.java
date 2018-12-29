/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui.app;

import controlleurGui.app.membres.AddMembresController;
import controlleurGui.app.membres.ViewMembresController;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class MembresController implements Initializable {

    @FXML
    public BorderPane bpContent;
    @FXML
    private Label lblView;
    @FXML
    private MenuItem btnAddEmployee;
    @FXML
    private MenuItem btnViewEmployee;
    @FXML
    private ImageView ivEmployeIcon;
    @FXML
    private StackPane spEmployeContent;
     private String userId;
    
    private userNameMedia nameMedia;
    
    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAddEmployeeOnClick(ActionEvent event) throws IOException {
        lblView.setText("Nouveau Membre");
        userNameMedia media = new userNameMedia();        
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/gui/app/membres/AddMembres.fxml").openStream());
        media.setId(userId);       
        AddMembresController addEmployeController = fXMLLoader.getController();
        addEmployeController.setNameMedia(nameMedia);       
        AnchorPane acPane = fXMLLoader.getRoot();       
        spEmployeContent.getChildren().clear();        
        spEmployeContent.getChildren().add(acPane);
    }

    @FXML
    public void btnViewEmployeeOnAction(ActionEvent event) throws IOException {
        lblView.setText("Membres");
        userNameMedia media = new userNameMedia();        
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/gui/app/membres/ViewMembres.fxml").openStream());
        media.setId(userId);        
        ViewMembresController viewEmployeController = fXMLLoader.getController();
        viewEmployeController.setNameMedia(nameMedia);
        viewEmployeController.showDetails();        
        AnchorPane acPane = fXMLLoader.getRoot();       
        spEmployeContent.getChildren().clear();       
        spEmployeContent.getChildren().add(acPane);
    }
    
}
