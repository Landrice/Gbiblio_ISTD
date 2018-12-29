/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui.home;

import controlleurGui.app.doc.AddDocController;
import gettersSetters.userNameMedia;
import getway.AuthGetway;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class HomeController implements Initializable {

    @FXML
    private Label lblOrgName1;
    @FXML
    private Text lblTotalDoc;
    @FXML
    private Text lblTotalEtudiant;
    @FXML
    private Text lblTotalUtilisateurs;
    @FXML
    private Text lblTotalEmprunt;
    private userNameMedia media;
    private String usrId;
    @FXML
    private FlowPane flowpane;
    @FXML
    private AnchorPane anc;
    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }
    AuthGetway auth = new AuthGetway();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //anc.setStyle("-fx-background-image: url(\"/images/Image3.png\");");
        flowpane.setStyle("-fx-background-image: url(\"/images/Image3.png\");");
        Platform.runLater(() -> {
            lblTotalUtilisateurs.setText(String.valueOf(auth.totalUser()));
            lblTotalDoc.setText(String.valueOf(auth.totalDoc()));
            lblTotalEmprunt.setText(String.valueOf(auth.totalEmprunt()));
            lblTotalEtudiant.setText(String.valueOf(auth.totalMembres()));
            
        });
    }    

    @FXML
    private void handleNewDoc(ActionEvent event) {
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/app/doc/AddDoc.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddDocController addProductController = fxmlLoader.getController();
            media.setId(usrId);
            addProductController.setNameMedia(media);
            addProductController.lblHeader.setText("Ajout Documents");
            addProductController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
//            addProductController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleNewEtu(ActionEvent event) {
        Alert a=new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Notifications");
        a.setHeaderText("Ajouter nouveau Etudiant?");
        a.setContentText("Veuillez consulter le Menu 'Etudiant Membre'");
        a.showAndWait();
    }

    @FXML
    private void handleNewUtil(ActionEvent event) {
    }

    @FXML
    private void handleNewEmprunt(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/app/emprunt/NewEmprunt.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            
            Stage nStage = new Stage();
            
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
