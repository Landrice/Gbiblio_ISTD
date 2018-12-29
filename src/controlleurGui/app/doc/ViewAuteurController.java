/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui.app.doc;

import bdd.SQLSyntax;
import bdd.bddConnection;
import gettersSetters.AuteurBLL;
import gettersSetters.Auteur;
import gettersSetters.AuteurGetway;
import gettersSetters.ListAuteur;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
public class ViewAuteurController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<ListAuteur> tblBrand;
    @FXML
    private TableColumn<Object, Object> tblCollumId;
    @FXML
    private TableColumn<Object, Object> tblCollumName;
    @FXML
    private TableColumn<Object, Object> tblCollumCreator;
    @FXML
    private TableColumn<Object, Object> tblClmDate;
    @FXML
    private Button btnAdd;
    @FXML
    private TableColumn<Object, Object> tblCollumFirName;
    
    private String usrId;
    private String usrName;
    private String brandId;
    private String creatorId;
    private String supplyerId;

    SQLSyntax sql = new SQLSyntax();
    Auteur brands = new Auteur();
    AuteurGetway brandsGetway = new AuteurGetway();
    AuteurBLL brandBLL = new AuteurBLL();

    private userNameMedia media;

    bddConnection dbCon = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        usrName = media.getNom();
        this.media = media;
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
    private void tfSearchOnKeyPress(KeyEvent event) {
        System.out.println(usrId);
        brands.brandDitails.clear();
        brands.auteurnom = tfSearch.getText();
        tblBrand.setItems(brands.brandDitails);
        tblCollumId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCollumName.setCellValueFactory(new PropertyValueFactory<>("auteurnom"));
        tblCollumFirName.setCellValueFactory(new PropertyValueFactory<>("auteurprenom"));
        tblCollumCreator.setCellValueFactory(new PropertyValueFactory<>("creatorId"));
        tblClmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        brandsGetway.searchView(brands);
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        brands.brandDitails.clear();
        showDetails();
    }


    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (tblBrand.getSelectionModel().getSelectedItem() != null) {
            viewDetails();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        ListAuteur selectedBrand = tblBrand.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Voulez vous vraiment supprimer \n Cliquer sur ok");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            brands.id = selectedBrand.getId();
            System.out.println(brands.id + "On hear");
            brandBLL.delete(brands);
            tblBrand.getItems().clear();
            showDetails();
        }
    }

    @FXML
    private void miSearch(ActionEvent event) {
        tblBrand.getSelectionModel().clearSelection();
        tfSearch.requestFocus();
    }

    @FXML
    private void miUpdate(ActionEvent event) {
        btnUpdateOnAction(event);
    }

    @FXML
    private void miSeeUpdateHistory(ActionEvent event) {
    }

    @FXML
    private void miDelete(ActionEvent event) {
        btnDeleteOnAction(event);
    }

    @FXML
    private void miAddNew(ActionEvent event) {
        btnAddOnAction(event);
    }

    @FXML
    private void tblBrandOnClick(MouseEvent event) {
        int click = event.getClickCount();
        if (click == 2) {
            viewDetails();
        }
    }

    @FXML
    private void btnAddOnAction(ActionEvent event) {
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/app/doc/AddAuteur.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddAuteurController supplyerController = fxmlLoader.getController();
            media.setId(usrId);
            supplyerController.setMedia(media);
            supplyerController.lblCaption.setText("Ajouter Auteur");
            supplyerController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tfSearchOnAction(event);
    }
    public void showDetails() {
        tblBrand.setItems(brands.brandDitails);
        tblCollumId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCollumName.setCellValueFactory(new PropertyValueFactory<>("auteurnom"));
        tblCollumFirName.setCellValueFactory(new PropertyValueFactory<>("auteurprenom"));
        tblCollumCreator.setCellValueFactory(new PropertyValueFactory<>("creatorId"));
        tblClmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        brandsGetway.view(brands);
    }
    
    private void viewDetails() {
        ListAuteur selectedBrand = tblBrand.getSelectionModel().getSelectedItem();
        String items = selectedBrand.getId();
        if (!items.equals(null)) {
            userNameMedia media = new userNameMedia();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/app/doc/AddAuteur.fxml"));
            try {
                fxmlLoader.load();
                Parent parent = fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                AddAuteurController addBrandController = fxmlLoader.getController();
                media.setId(usrId);
                addBrandController.setMedia(media);
                addBrandController.lblCaption.setText("Details sur les Auteurs");
                addBrandController.btnUpdate.setVisible(true);
                addBrandController.btnSave.setVisible(false);
                addBrandController.brandId = selectedBrand.getId();
                addBrandController.showDetails();
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
}
