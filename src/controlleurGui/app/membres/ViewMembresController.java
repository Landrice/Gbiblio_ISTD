/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui.app.membres;

import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXRadioButton;
import gettersSetters.ListMembres;
import gettersSetters.MembresGetWay;
import gettersSetters.userNameMedia;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import list.Membres;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class ViewMembresController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private TableView<ListMembres> tblEmoyeeList;
    @FXML
    private TableColumn<Object, Object> clmEmployeId;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfParcours;
    @FXML
    private TextField tfDate;
    @FXML
    private TextField tfCreatedBy;
    @FXML
    private Rectangle recUsrImage;
    @FXML
    private Button btnAttachImage;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField tfNum;
    @FXML
    private ToggleGroup groupt;
    
    Membres users = new Membres();
    MembresGetWay usersGetway = new MembresGetWay();
    //SQLSyntax sql = new SQLSyntax();
    bddConnection dbCon = new bddConnection();
    
    
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    private File file;
    private BufferedImage bufferedImage;
    private String imagePath;
    private Image image;
    private Blob blobImage;

    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    private String userId;
    private String name;
    private String id;
    private userNameMedia nameMedia;
    private String creatorId;
    private String creatorName;
    Image usrImg = new Image("/images/avatar.jpg");
    @FXML
    private TableColumn<Object, Object> clmnum;
    @FXML
    private TableColumn<Object, Object> clnom;
    @FXML
    private JFXRadioButton masc;
    @FXML
    private JFXRadioButton fem;
    @FXML
    private TextField tFidentifiant;
    @FXML
    private TextField tFmdp;

    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        name = nameMedia.getNom();
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
    private void tfSearchOnAction(ActionEvent event) {
    }

    @FXML
    private void tblEmloyeOnClick(MouseEvent event) {
        setselectedView();
    }

    @FXML
    private void tblViewOnClick(KeyEvent event) {
        if (event.getCode().equals(KeyCode.UP)) {
            setselectedView();
        } else if (event.getCode().equals(KeyCode.DOWN)) {
            setselectedView();
        }
    }

    @FXML
    private void btnAttachImageOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG (Joint Photographic Group)", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG (Portable Network Graphics)", "*.png")
        );

        fileChooser.setTitle("Choisir une image");

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            System.out.println(file);
            bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            recUsrImage.setFill(new ImagePattern(image));
            imagePath = file.getAbsolutePath();
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        
        String sexeA;
        if(masc.isSelected()==true){
            sexeA="Masculin";
        }else if(fem.isSelected()==true){
            sexeA="Feminin";
        }else sexeA="";
        
        users.nom = tfNom.getText();
        users.prenom = tfPrenom.getText();
        users.parcours = tfParcours.getText();
        users.datenaissance = tfDate.getText();
        users.sexe = sexeA;
        users.image = usrImg;
        users.imagePath = imagePath;
        users.identifiant=tFidentifiant.getText();
        users.mdp=tFmdp.getText();
        usersGetway.update(users);
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Etes vous sur ?");
        alert.setContentText("Pour comfirmer la suppression \n Cliquer sur ok");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            usersGetway.selectedView(users);
            usersGetway.delete(users);
        }
        
        tblEmoyeeList.getItems().clear();
        showDetails();
    }
    
    
    public void setselectedView() {
        clearAll();
        ListMembres employeeList = tblEmoyeeList.getSelectionModel().getSelectedItem();
        if (employeeList != null) {
            users.id = employeeList.getMembreid();
            usersGetway.selectedView(users);
            id = users.id;
            tfNum.setText(users.numero);
            tfNom.setText(users.nom);
            tfPrenom.setText(users.prenom);
            
            System.out.println("Prenom: "+users.prenom);
            System.out.println("Nom est: "+users.nom);
            
            tfParcours.setText(users.parcours);
            tfDate.setText(users.datenaissance);
            if(users.sexe.equals("Masculin")){
                masc.setSelected(true);
            }else if(users.sexe.equals("Feminin")){
                fem.setSelected(true);
            }else{}
            
            image = users.image;
            recUsrImage.setFill(new ImagePattern(image)); 
            tFidentifiant.setText(users.identifiant);
            tFmdp.setText(users.mdp);

        }
    }
    
    private void clearAll() {
        tfNom.clear();
        tfNum.clear();
        tfCreatedBy.clear();
        tfPrenom.clear();
        tfDate.clear();
        tfParcours.clear();
        groupt.selectToggle(null);
    }
    public void showDetails() {
        tblEmoyeeList.setItems(users.employeeLists);
        clmEmployeId.setCellValueFactory(new PropertyValueFactory<>("membreid"));
        clmnum.setCellValueFactory(new PropertyValueFactory<>("membrenumero"));
        clnom.setCellValueFactory(new PropertyValueFactory<>("mambrenom"));
        usersGetway.view(users);
        
    }
}
