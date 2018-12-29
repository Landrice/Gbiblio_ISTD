/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui.app.doc;

import bdd.BddPropreties;
import bdd.SQLSyntax;
import bdd.bddConnection;
import com.jfoenix.controls.JFXDatePicker;
import gettersSetters.CurrentProduct;
import gettersSetters.CurrentProductBLL;
import gettersSetters.CurrentProductGetway;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class AddDocController implements Initializable {

    @FXML
    public Label lblHeader;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;
    @FXML
    private JFXDatePicker dpDate;
    @FXML
    private Button btnClose;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfQuantity;
    @FXML
    private TextField tfnbPage;
    @FXML
    private TextArea tFDescription;
    @FXML
    private ComboBox<String> cbEditeur;
    @FXML
    private ComboBox<String> cbCat;
    @FXML
    private ComboBox<String> cbAuteur;
    @FXML
    private ComboBox<String> typeDoc;
    @FXML
    private ComboBox<String> langueDoc;
    
    private String usrId;
    private userNameMedia nameMedia;

    CurrentProduct currentProduct = new CurrentProduct();
    CurrentProductBLL currentProductBLL = new CurrentProductBLL();
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();
    SQLSyntax sql = new SQLSyntax();

    bddConnection dbCon = new bddConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    @FXML
    private Button btnAddEditeur;
    @FXML
    private Button btnAddCategorie;
    @FXML
    private Button btnAddAuteur;
    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        usrId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }
    
    public String id;
    private String supplyerName;
    private String auteurid;
    private String brandName;
    private String editeurid;
    private String catagoryName;
    private String categorieid;
    private String unitId;
    private String rmaId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeDoc.getItems().addAll("Livre","Audio","Video","Support Informatique");
        langueDoc.getItems().addAll("Allemand","Anglais","Arabe","Chinois","Coreen","Croite", "Danois","Espagnol","Français","Galois","Grec","Indonesien","Italien","Japonais","Malgache","Polonais","Portugais","Roumain","Russe","Serbe","Ukrainien","Vietnamien");
        tfQuantity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    tfQuantity.setText(oldValue);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        tfnbPage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    tfnbPage.setText(oldValue);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        
    }    



    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        if(isNotNull()){
            currentProduct.idDoc = tfId.getText().trim();
            currentProduct.titre = tfTitre.getText().trim();
            currentProduct.nombre = tfQuantity.getText().trim();
            currentProduct.nombrepage = tfnbPage.getText().trim();
            currentProduct.idDoc = tfId.getText().trim();
            currentProduct.type = typeDoc.getSelectionModel().getSelectedItem();
            currentProduct.langue = langueDoc.getSelectionModel().getSelectedItem();
            currentProduct.description = tFDescription.getText().trim();
            currentProduct.date = dpDate.getValue().toString();
            currentProduct.idauteur=auteurid;
            currentProduct.idcategorie=categorieid;
            currentProduct.idediteur=editeurid;
            currentProduct.idutilisateur=usrId;
            currentProductBLL.save(currentProduct);
        }
    }



    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if(isNotNull()){
            currentProduct.idDoc = tfId.getText().trim();
            currentProduct.titre = tfTitre.getText().trim();
            currentProduct.nombre = tfQuantity.getText().trim();
            currentProduct.nombrepage = tfnbPage.getText().trim();
            currentProduct.idDoc = tfId.getText().trim();
            currentProduct.type = typeDoc.getSelectionModel().getSelectedItem();
            currentProduct.langue = langueDoc.getSelectionModel().getSelectedItem();
            currentProduct.description = tFDescription.getText().trim();
            currentProduct.date = dpDate.getValue().toString();
            currentProduct.id=id;
            currentProduct.idauteur=auteurid;
            currentProduct.idcategorie=categorieid;
            currentProduct.idediteur=editeurid;
            currentProduct.idutilisateur=usrId;
            currentProductBLL.update(currentProduct);
        }
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cbEditeurOnAction(ActionEvent event) {
        cbEditeur.getSelectionModel().getSelectedItem();
        try {
            pst = con.prepareStatement("select * from " + db + ".editeur where nomEditeur=?");
            pst.setString(1, cbEditeur.getSelectionModel().getSelectedItem());
            
            rs = pst.executeQuery();
            while (rs.next()) {
                editeurid = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddDocController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cbCatOnAction(ActionEvent event) {
        cbCat.getSelectionModel().getSelectedItem();
        try {
            pst = con.prepareStatement("select * from " + db + ".categorie where nomCategorie=?");
            pst.setString(1, cbCat.getSelectionModel().getSelectedItem());
            
            rs = pst.executeQuery();
            while (rs.next()) {
                categorieid = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddDocController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cbAuteurOnAction(ActionEvent event) {
        cbAuteur.getSelectionModel().getSelectedItem();
        try {
            pst = con.prepareStatement("select * from " + db + ".auteur where nom=?");
            pst.setString(1, cbAuteur.getSelectionModel().getSelectedItem());
            
            rs = pst.executeQuery();
            while (rs.next()) {
                auteurid = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddDocController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAddEditeurOnAction(ActionEvent event) {
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/app/doc/AddEditeur.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddEditeurController supplyerController = fxmlLoader.getController();
            media.setId(usrId);
            supplyerController.setMedia(media);            
            supplyerController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            supplyerController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAddCategorieOnAction(ActionEvent event) {
        AddCatagoryController addCatagoryController = new AddCatagoryController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/app/doc/AddCatagory.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddCatagoryController catagoryController = fxmlLoader.getController();
            media.setId(usrId);
            catagoryController.setMedia(media);
           // catagoryController.lblHeaderContent.setText("Ajouter");
            catagoryController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//

    @FXML
    private void btnAddAuteurOnAction(ActionEvent event) {       
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/app/doc/AddAuteur.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddAuteurController catagoryController = fxmlLoader.getController();
            media.setId(usrId);
            catagoryController.setMedia(media);
            catagoryController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private boolean isNotNull() {
        boolean insNotNull = false;
        if (cbEditeur.getSelectionModel().getSelectedItem() == null
                && cbEditeur.getPromptText().isEmpty()
                || cbCat.getSelectionModel().getSelectedItem() == null
                && cbCat.getPromptText().isEmpty()
                || cbAuteur.getSelectionModel().getSelectedItem() == null
                && cbAuteur.getPromptText().isEmpty()
                || tfId.getText().isEmpty()
                || tfTitre.getText().isEmpty()
                || tfQuantity.getText().isEmpty()
                ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("ERREUR: Le champ ne peut pas être vide");
            alert.setContentText("Veuillez remplir les champs necessaires");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

            insNotNull = false;
        } else {
            insNotNull = true;
        }
        return insNotNull;
    }

    @FXML
    private void cbEditeurOnCliked(MouseEvent event) {
        cbEditeur.getSelectionModel().clearSelection();
        cbEditeur.getItems().clear();
        try {
            pst = con.prepareStatement("select * from " + db + ".editeur");
            rs = pst.executeQuery();
            while (rs.next()) {
                cbEditeur.getItems().addAll(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddDocController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cbCatOnOnClick(MouseEvent event) {
        cbCat.getSelectionModel().clearSelection();
        cbCat.getItems().clear();
        try {
            pst = con.prepareStatement("select * from " + db + ".Categorie");
            rs = pst.executeQuery();
            while (rs.next()) {
                cbCat.getItems().addAll(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddDocController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cbAuteurOnOnClick(MouseEvent event) {
        cbAuteur.getSelectionModel().clearSelection();
        cbAuteur.getItems().clear();
        try {
            pst = con.prepareStatement("select * from " + db + ".auteur");
            rs = pst.executeQuery();
            while (rs.next()) {
                cbAuteur.getItems().addAll(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddDocController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void viewSelected() {
        currentProduct.id = id;
        currentProductGetway.selectedView(currentProduct);
        tfId.setText(currentProduct.idDoc);
        tfTitre.setText(currentProduct.titre);
        tfQuantity.setText(currentProduct.nombre);
        tfnbPage.setText(currentProduct.nombrepage);
        tFDescription.setText(currentProduct.description);
        dpDate.setValue(LocalDate.parse(currentProduct.date));
        cbEditeur.setPromptText(currentProduct.editionNom);
        cbCat.setPromptText(currentProduct.categorieNom);
        cbAuteur.setPromptText(currentProduct.auteurNom);
        
        switch(currentProduct.type){
            case "Livre":
                typeDoc.getSelectionModel().select(0);
                break;
            case "Audio":    
                typeDoc.getSelectionModel().select(1);
                break;
            case "Video":    
                typeDoc.getSelectionModel().select(3);
                break;
            case "Support Informatique":    
                typeDoc.getSelectionModel().select(3);
                break;
            default:
                typeDoc.getSelectionModel().select(-1);
                break;
        }
    }
    
    
}
