/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gbiblio_istd;

import bdd.BddPropreties;
import bdd.bddConnection;
import bdd.bddModel;
import getway.AuthGetway;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ralande
 */
public class Gbiblio_ISTD extends Application {

    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    public Gbiblio_ISTD() {  
        System.out.println("Commencement");
        bddModel model = new bddModel();
        model.createDataBase();
        System.out.println("Fin");
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        
        bddConnection dbCon = new bddConnection();
        con = dbCon.geConnection();
        
        
         if (con != null) {
              try {
                  System.out.println("Commencement----test");
                pst = con.prepareStatement("SELECT Id FROM " + db + ".utilisateur ORDER BY Id ASC LIMIT 1");
                rs = pst.executeQuery();
              if (rs.next()) {
                  
                  Parent root = FXMLLoader.load(getClass().getResource("/gui/login.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Authentification requise");
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(false);
            stage.setFullScreen(false);
            stage.getIcons().add(new Image("/images/Image1.png"));
            stage.setScene(scene);
            stage.show();           

        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/NewUser.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Gestion du Bibiothèque du IST-D");
            stage.getIcons().add(new Image("/images/Image1.png"));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
              } catch (SQLException ex) {
                Logger.getLogger(Gbiblio_ISTD.class.getName()).log(Level.SEVERE, null, ex);
            }
         } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error : Serveur Introuvable");
            alert.setContentText("Assurer que la connection au serveur est bien etablie, \n");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }

}
