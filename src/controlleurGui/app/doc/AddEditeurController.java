/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui.app.doc;

import gettersSetters.EffectUtility;
import gettersSetters.Editeur;
import gettersSetters.EditeurGetway;
import gettersSetters.userNameMedia;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class AddEditeurController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    public Button btnUpdate;
    @FXML
    private TextField tfLocalisation;
    @FXML
    private TextField tfSite;
    @FXML
    private TextField tfContact;
    @FXML
    public Label lblHeader;
    @FXML
    private Button btnClose;
    
     private String usrId;

    public String supplyerId;
    
    private userNameMedia media;
    @FXML
    public Button btnAdd;
    @FXML
    private AnchorPane apContent;
    
    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }

    Editeur oSupplier = new Editeur();
    EditeurGetway supplyerGetway = new EditeurGetway();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (isNotNull()) {
            oSupplier.nom = tfNom.getText().trim();
            oSupplier.localisation = tfLocalisation.getText().trim();
            oSupplier.siteweb = tfSite.getText().trim();
            oSupplier.contact = tfContact.getText().trim();
            oSupplier.creatorId = usrId;
            supplyerGetway.update(oSupplier);

            clrAll();
        }
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnUpdate.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnAdd(ActionEvent event) {
        if (isNotNull()) {
            oSupplier.nom = tfNom.getText().trim();
            oSupplier.localisation = tfLocalisation.getText().trim();
            oSupplier.siteweb = tfSite.getText().trim();
            oSupplier.contact = tfContact.getText().trim();
            oSupplier.creatorId = usrId;
            supplyerGetway.save(oSupplier);

            clrAll();
        }
    }
    public boolean isNotNull() {
        boolean isNotNull;
        if (tfNom.getText().trim().isEmpty()
                ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("ERREUR: Null");
            alert.setContentText("Veuillez remplir les champs necessaires");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            
            isNotNull = false;

        } else {
            isNotNull = true;
        }
        return isNotNull;
    }

    private void clrAll() {
        tfNom.clear();
        tfContact.clear();
        tfLocalisation.clear();
        tfSite.clear();
    }
    public void addSupplyerStage(Stage stage) {
        EffectUtility.makeDraggable(stage, apContent);
    }
    public void showDetails() {
        oSupplier.id = supplyerId;
        supplyerGetway.selectedView(oSupplier);
        tfNom.setText(oSupplier.nom);
        tfLocalisation.setText(oSupplier.localisation);
        tfSite.setText(oSupplier.siteweb);
        tfContact.setText(oSupplier.contact);
    }
}
