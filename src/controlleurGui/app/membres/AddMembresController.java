/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui.app.membres;

import bdd.BddPropreties;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import gettersSetters.MembresGetWay;
import gettersSetters.userNameMedia;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import list.Membres;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class AddMembresController implements Initializable {

    @FXML
    private Button btnAttachImage;
    @FXML
    private ImageView imvUsrImg;
    @FXML
    private TextField tfNum;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPName;
    @FXML
    private TextField tfParcours;
    @FXML
    private JFXDatePicker dpdate;
    @FXML
    private JFXRadioButton masc;
    @FXML
    private ToggleGroup sexe;
    @FXML
    private JFXRadioButton fem;
    @FXML
    private JFXButton btnSave;
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    Membres users = new Membres();
    MembresGetWay usersGetway = new MembresGetWay();
    
    private File file;
    private BufferedImage bufferedImage;
    private Image image;
    
    private String imagePath;
    private String usrId;
    private userNameMedia nameMedia;
    @FXML
    private TextField tFidentifiant;
    @FXML
    private TextField tFmdp;

    

    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        usrId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        BooleanBinding binding = tfNum.textProperty().isEmpty()
                .or(tfName.textProperty().isEmpty()
                .or(tfParcours.textProperty().isEmpty())
                .or(tFidentifiant.textProperty().isEmpty())
                .or(tFmdp.textProperty().isEmpty())
                );
        btnSave.disableProperty().bind(binding);
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
            imvUsrImg.setImage(image);
            imagePath = file.getAbsolutePath();
        }
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        
        String sexeA;
        if(masc.isSelected()==true){
            sexeA="Masculin";
        }else if(fem.isSelected()==true){
            sexeA="Feminin";
        }else sexeA="";
        
        users.nom = tfName.getText();
        users.prenom = tfPName.getText();
        users.numero = tfNum.getText();
        users.parcours = tfParcours.getText();
        users.sexe = sexeA;
        users.datenaissance = dpdate.getValue().toString();
        users.imagePath = imagePath;
        users.identifiant=tFidentifiant.getText();
        users.mdp=tFmdp.getText();
        usersGetway.save(users);
        clear();
    }
    
    public void clear(){
        tfName.setText(null);
        tfNum.setText(null);
        tfPName.setText(null);
        tfParcours.setText(null);
        dpdate.setValue(null);
        imvUsrImg.setImage(null);
        tFidentifiant.setText(null);
        tFmdp.setText(null);
    }
    
}
