/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui;

import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXToggleButton;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class LoginController implements Initializable {

    private JFXToggleButton etudianttg;
    @FXML
    private TextField idTf;
    @FXML
    private PasswordField mdpTf;
    private Connection con;
    private PreparedStatement pst;
    ResultSet rs;
    
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    @FXML
    private Label lblnotmatch;
    @FXML
    private JFXToggleButton etu;
    @FXML
    private ToggleGroup group;
    @FXML
    private JFXToggleButton admin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void ConnecterOnAction(ActionEvent event) throws IOException {
        bddConnection dbCon = new bddConnection();
        con = dbCon.geConnection();
        
        
        if (con != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/gui/App.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
            Scene adminPanelScene = new Scene(parent);
            Stage adminPanelStage = new Stage();
            adminPanelStage.setMaximized(true);
            if(admin.isSelected()==true){
            if (isValidCondition()) {
                try {
                    pst = con.prepareStatement("select * from " + db + ".utilisateur where nom=? and mdp=? ");
                    pst.setString(1, idTf.getText());
                    pst.setString(2, mdpTf.getText());
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        userNameMedia usrNameMedia = new userNameMedia(rs.getString(1), rs.getString(2));
                        AppController apControl = loader.getController();
                        apControl.setUsrNameMedia(usrNameMedia);
                        apControl.permission();
                        apControl.viewDetails();
                        adminPanelStage.setScene(adminPanelScene);
                        adminPanelStage.setTitle("Utilisateur "+rs.getString(3));
                        
                        adminPanelStage.show();
                        apControl.btnHomeOnClick(event);

                        Stage stage = (Stage) mdpTf.getScene().getWindow();
                        stage.close();
                    } else {
                        lblnotmatch.setText("Identifiant ou mot de passe incorrect !");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                           Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Erreur");
                        alert.setContentText(ex.getMessage());
                        alert.showAndWait();
                }

            }
        }else if(etu.isSelected()==true){
            if (isValidCondition()) {
                try {
                    pst = con.prepareStatement("select * from " + db + ".membres where identifiant=? and mdp=? ");
                    pst.setString(1, idTf.getText());
                    pst.setString(2, mdpTf.getText());
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        userNameMedia usrNameMedia = new userNameMedia(rs.getString(1), rs.getString(2));
                        AppController apControl = loader.getController();
                        apControl.setUsrNameMedia(usrNameMedia);
                        adminPanelStage.setScene(adminPanelScene);
                        adminPanelStage.setTitle("Membres "+rs.getString(3));
                        
                        adminPanelStage.show();
                        apControl.btnEmplopye.setDisable(true);
                        apControl.btnEmprunt.setDisable(true);
                        apControl.btnHome.setDisable(true);
                        apControl.btnSettings.setDisable(true);
                        apControl.btnMembre.setDisable(true);
                        apControl.btnDocumOnClick(event);

                        Stage stage = (Stage) mdpTf.getScene().getWindow();
                        stage.close();
                    } else {
                        lblnotmatch.setText("Identifiant ou mot de passe incorrect !");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                           Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Erreur");
                        alert.setContentText(ex.getMessage());
                        alert.showAndWait();
                }

            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur : Groupe ontrouvable");
            alert.setContentText("Selectionner etudiant ou employé, \n");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }
        
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur : Serveur Introuvable");
            alert.setContentText("Assurer que le serveur est en marche, \n");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }
    }

    
    private boolean isValidCondition() {
        boolean validCondition = false;
        if (idTf.getText().trim().isEmpty()
                || mdpTf.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement :");
            alert.setHeaderText("Null !!");
            alert.setContentText("Veuiller entrer votre nom et le mot de passe");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

            validCondition = false;
        } else {
            validCondition = true;
        }
        return validCondition;
    }
}
