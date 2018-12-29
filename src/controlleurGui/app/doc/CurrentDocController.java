/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui.app.doc;

import bdd.BddPropreties;
import bdd.SQLSyntax;
import bdd.bddConnection;
import gettersSetters.CurrentProduct;
import gettersSetters.CurrentProductBLL;
import gettersSetters.CurrentProductGetway;
import gettersSetters.ListDoc;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class CurrentDocController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnRefresh;
    @FXML
    public AnchorPane apCombobox;
    @FXML
    private Button btnAddNew;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<ListDoc> tblViewCurrentStore;
    @FXML
    private MenuItem miSellSelected;
    @FXML
    private TableColumn<Object, Object> tblClmDocID;
    @FXML
    private TableColumn<Object, Object> tblClmTitre;
    @FXML
    private TableColumn<Object, Object> tblClmNombre;
    @FXML
    private TableColumn<Object, Object> tblClmAut;
    @FXML
    private TableColumn<Object, Object> tblClmEdit;
    @FXML
    private TableColumn<Object, Object> tblClmCat;
    @FXML
    private TableColumn<Object, Object> tblClmType;
    @FXML
    private TableColumn<Object, Object> tblClmDate;
    @FXML
    private TableColumn<Object, Object> tblClmDocAddBy;
    @FXML
    private TableColumn<Object, Object> tblClmDesc;
    CurrentProduct productCurrent = new CurrentProduct();
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();
    CurrentProductBLL currentProductBLL = new CurrentProductBLL();

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    private String usrId;
    String suplyerId;
    String suplyerName;
    String brandId;
    String brandName;
    String catagoryId;
    String catagoryName;
    String rmaID;
    String rmaName;
    SQLSyntax sql = new SQLSyntax();

    private userNameMedia media;
    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }

    bddConnection dbCon = new bddConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    ListDoc lstpr;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void tfSearchOnKeyRelese(KeyEvent event) {
        productCurrent.idDoc = tfSearch.getText();
        productCurrent.titre = tfSearch.getText();
        currentProductGetway.searchView(productCurrent);
    }

    @FXML
    private void btnRefreshOnACtion(ActionEvent event) {
        searchAll();
    }


    @FXML
    private void btnAddNewOnAction(ActionEvent event) {
        
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
    private void btnUpdateOnAction(ActionEvent event) {
        if (!tblViewCurrentStore.getSelectionModel().isEmpty()) {
            viewSelected();
        } else {

        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Voulez vous supprimer l'élement selectionné? \n cliquer sur OK pour confirmer");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String item = tblViewCurrentStore.getSelectionModel().getSelectedItem().getId();
            System.out.println("Product id" + item);
            productCurrent.id = item;
            currentProductBLL.delete(productCurrent);
            btnRefreshOnACtion(event);
        }
    }

    @FXML
    private void miSellSelectedOnAction(ActionEvent event) {
    }

    @FXML
    private void tblViewCurrentStoreOnClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            if (!tblViewCurrentStore.getSelectionModel().isEmpty()) {
                viewSelected();
            } else {
                System.out.println("EMPTY SELECTION");
            }
        } else {
            tblViewCurrentStore.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    tblViewCurrentStore.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                }
            });

        }
    }

    @FXML
    private void tblViewCurrentStoreOnScroll(ScrollEvent event) {
        if (event.isInertia()) {
            System.out.println("ALT DOWN");
        } else {
            System.out.println("Noting");
        }
    }

    @FXML
    private void keyreleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.UP)) {
        } // fleche bas
        else if (event.getCode().equals(KeyCode.DOWN)) {
            String iyyy = tblViewCurrentStore.getSelectionModel().getSelectedItem().getIdDoc();
            System.out.println(iyyy);
        }
    }
    
    public void settingPermission() {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from " + db + ".droitutilisateur where id=?");
            pst.setString(1, usrId);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(7) == 0) {
                    btnUpdate.setDisable(true);
                    btnDelete.setDisable(true);
                }
                if (rs.getInt(2) == 0) {
                    btnAddNew.setDisable(true);
                }
                 else {

                }
            }
        } catch (SQLException ex) {
             Logger.getLogger(CurrentDocController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewDetails() {
        //viewFistTenOrderByCategorie

        System.out.println("CLCKED");
        tblViewCurrentStore.setItems(productCurrent.currentDoctList);
        tblClmDocID.setCellValueFactory(new PropertyValueFactory<>("idDoc"));
        tblClmTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        //tblClmProductName.setStyle("-fx-background-color:red;");
        tblClmNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblClmAut.setCellValueFactory(new PropertyValueFactory<>("auteurID"));
        tblClmEdit.setCellValueFactory(new PropertyValueFactory<>("editionID"));
        tblClmCat.setCellValueFactory(new PropertyValueFactory<>("categorieID"));
        tblClmType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblClmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblClmDocAddBy.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
        tblClmDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        currentProductGetway.viewFistTen(productCurrent);

        //tblViewCurrentStore.setRowFactory((final TableView<ListProduct> p)->new TableRow());
        
    }
    public void searchAll(){
        System.out.println("CLCKED");
        tblViewCurrentStore.setItems(productCurrent.currentDoctList);
        tblClmDocID.setCellValueFactory(new PropertyValueFactory<>("idDoc"));
        tblClmTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        //tblClmProductName.setStyle("-fx-background-color:red;");
        tblClmNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblClmAut.setCellValueFactory(new PropertyValueFactory<>("auteurID"));
        tblClmEdit.setCellValueFactory(new PropertyValueFactory<>("editionID"));
        tblClmCat.setCellValueFactory(new PropertyValueFactory<>("categorieID"));
        tblClmType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblClmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblClmDocAddBy.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
        tblClmDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        currentProductGetway.view(productCurrent);
    }
    
    
    private void viewSelected() {
        userNameMedia userMedia = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/app/doc/AddDoc.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddDocController addProductController = fxmlLoader.getController();
            userMedia.setId(usrId);
            addProductController.setNameMedia(userMedia);
            addProductController.lblHeader.setText("DETAILS DES PRODUITS");
            addProductController.btnUpdate.setVisible(true);
            addProductController.btnSave.setVisible(false);
            addProductController.id = tblViewCurrentStore.getSelectionModel().getSelectedItem().getId();
            addProductController.viewSelected();
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
