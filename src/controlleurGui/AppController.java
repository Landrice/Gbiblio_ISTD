/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui;

import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import controlleurGui.app.DocumentController;
import controlleurGui.app.EmployeController;
import controlleurGui.app.MembresController;
import controlleurGui.app.SettingsController;
import gettersSetters.UsersGetWay;
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
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import list.Utilisateurs;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class AppController implements Initializable {

    @FXML
    private AnchorPane acMain;
    @FXML
    private BorderPane ancprincipal;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private ScrollPane leftSideBarScroolPan;
    @FXML
    private VBox vbox;
    @FXML
    public JFXButton btnHome;
    private JFXButton btnStore;
    @FXML
    public JFXButton btnEmplopye;
    @FXML
    public JFXButton btnSettings;
    @FXML
    private JFXButton btnlougout;
    @FXML
    private BorderPane appcontent;
    @FXML
    private AnchorPane acHead;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private StackPane acContent;
    @FXML
    public JFXButton btnEmprunt;
    @FXML
    public JFXButton btnMembre;
    
    bddConnection dbCon = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
     BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    String usrName;
    String id;
    
    HamburgerSlideCloseTransition hamb;
    private userNameMedia usrNameMedia;
    
    Utilisateurs users = new Utilisateurs();
    UsersGetWay usersGetway = new UsersGetWay();
    @FXML
    private JFXButton btnDocument;
    
    public userNameMedia getUsrNameMedia() {
        return usrNameMedia;
    }

    public void setUsrNameMedia(userNameMedia usrNameMedia) {
        //lblUserId.setText(usrNameMedia.getId());
     //   lblUsrName.setText(usrNameMedia.getNom());
        id = usrNameMedia.getId();
        usrName = usrNameMedia.getNom();

        this.usrNameMedia = usrNameMedia;
    }
    
    String defultStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:none";

    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:#FF4E3C";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hamb=new HamburgerSlideCloseTransition(hamburger);
       
       hamb.setRate(1);
    }    
    @FXML
    public void btnHomeOnClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/gui/home/home.fxml").openStream());
        } catch (IOException e) {
            
        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
    }



    @FXML
    private void btnEmplopyeOnClick(ActionEvent event) throws IOException {
        
        EmployeController ec = new EmployeController();
        userNameMedia nm = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/gui/app/Employe.fxml").openStream());
        nm.setId(id);
        EmployeController employeController = fXMLLoader.getController();
        employeController.bpContent.getStylesheets().add("/css/MainStyle.css");
        employeController.setNameMedia(usrNameMedia);
        employeController.btnViewEmployeeOnAction(event);

        AnchorPane acPane = fXMLLoader.getRoot();

        acContent.getChildren().clear();

        acContent.getChildren().add(acPane);
    }

    @FXML
    private void btnSettingsOnClick(ActionEvent event) throws IOException {
        //inithilize Setting Controller
        SettingsController settingController = new SettingsController();
        //inithilize UserNameMedia
        userNameMedia usrMedia = new userNameMedia();
        // Define a loader to inithilize Settings.fxml controller
        FXMLLoader settingLoader = new FXMLLoader();
        //set the location of Settings.fxml by fxmlloader
        settingLoader.load(getClass().getResource("/gui/app/Settings.fxml").openStream());

        //Send id to userMedia
        usrMedia.setId(id);
        //take control of settingController elements or node
        SettingsController settingControl = settingLoader.getController();
        settingControl.bpSettings.getStylesheets().add("/css/MainStyle.css");

        settingControl.setUsrMedia(usrMedia);
        settingControl.miMyASccountOnClick(event);
        //settingControl.settingPermission();

        AnchorPane acPane = settingLoader.getRoot();
        //acContent clear and make useul for add next node
        acContent.getChildren().clear();
        //add a node call "acPane" to acContent
        acContent.getChildren().add(acPane);
    }

    @FXML
    private void btnlougout_act(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/login.fxml"));
        loader.load();
        Parent parenthome = loader.getRoot();

        Stage stage = new Stage();
        //stage.getIcons().add(new Image("/image/lg.png"));
        Scene scene = new Scene(parenthome);
        stage.setScene(scene);
        stage.setTitle("Se Connecter");
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void hamburger_act(MouseEvent event) {
        hamb.setRate(hamb.getRate()*-1);
      TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), acDashBord);
      hamb.play();
      if(hamb.getRate()==-1){
            sideMenu.setByX(-200);
            sideMenu.play();
            acDashBord.getChildren().clear();

      }else{    
            sideMenu.setByX(200);
            sideMenu.play();
            acDashBord.getChildren().add(leftSideBarScroolPan);
      }
    }

    @FXML
    private void btnEmpruntOnClick(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/gui/app/emprunt/emprunt.fxml").openStream());
        AnchorPane acPane = fXMLLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(acPane);
    }

    @FXML
    private void btnMembreOnClick(ActionEvent event) throws IOException {
        
        userNameMedia nm = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/gui/app/Membres.fxml").openStream());
        nm.setId(id);
        MembresController employeController = fXMLLoader.getController();
        employeController.bpContent.getStylesheets().add("/css/MainStyle.css");
        employeController.setNameMedia(usrNameMedia);
        employeController.btnViewEmployeeOnAction(event);

        AnchorPane acPane = fXMLLoader.getRoot();

        acContent.getChildren().clear();

        acContent.getChildren().add(acPane);
    }
    
    public void permission() {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("select * from "+db+".droitutilisateur where idUtilisateur=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(12) == 0) {
                    btnEmplopye.setDisable(true);
                }
                 else {

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        Image image;
        public void viewDetails() {
        users.id = id;
        usersGetway.selectedView(users);
       // image = users.image;
      //  circleImgUsr.setFill(new ImagePattern(image));
       // imgUsrTop.setFill(new ImagePattern(image));
       // lblFullName.setText(users.fullName);
       // lblUsrNamePopOver.setText(users.userName);
    }

    @FXML
    public void btnDocumOnClick(ActionEvent event) {
        userNameMedia nm = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        try {
            fXMLLoader.load(getClass().getResource("/gui/app/document.fxml").openStream());
            nm.setId(id);
        DocumentController dc = fXMLLoader.getController();
        dc.bpStore.getStylesheets().add("/css/MainStyle.css");
        dc.setUserId(usrNameMedia);
        dc.btnDocOnAction(event);
        dc.settingPermission();
        AnchorPane acPane = fXMLLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(acPane);
        } catch (IOException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
        }
    }
}
