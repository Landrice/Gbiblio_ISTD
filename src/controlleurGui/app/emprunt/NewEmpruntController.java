/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui.app.emprunt;

import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import gbiblio_istd.Gbiblio_ISTD;
import gettersSetters.CurrentProduct;
import gettersSetters.CurrentProductGetway;
import gettersSetters.Emprunt;
import gettersSetters.EmpruntGetway;
import gettersSetters.ListDoc;
import gettersSetters.ListMembres;
import gettersSetters.MembresGetWay;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import list.Membres;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class NewEmpruntController implements Initializable {

    @FXML
    private MenuButton mbtnCustomer;
    @FXML
    private TextField tfCustomerSearch;
    @FXML
    private TableColumn<Object, Object> tblClmCustomerName;
    @FXML
    private TableColumn<Object, Object> tblClmCustomerPhoneNo;
    @FXML
    private TextField tfProductId;
    @FXML
    private MenuButton mbtnselectProduit;
    @FXML
    private TextField tfProduitSearch;
    @FXML
    private TableColumn<Object, Object> tblClmProduitID;
    @FXML
    private TableColumn<Object, Object> tblClmProduitNom;
    @FXML
    private Button btnClose;
    @FXML
    private JFXDatePicker dpemp;
    @FXML
    private JFXDatePicker dpret;
    @FXML
    private TableView<ListMembres> tbletu;
    @FXML
    private TableView<ListDoc> tblLivr;
    
    Membres membres=new Membres();
    CurrentProductGetway produitgetway = new CurrentProductGetway();
    CurrentProduct currrentProduct = new CurrentProduct();
    MembresGetWay membresgetway=new MembresGetWay();
    CurrentProduct prod = new CurrentProduct();
    EmpruntGetway empruntg=new EmpruntGetway();
    @FXML
    private JFXButton btnAjout;
    public String docid;
    public String etuid;
    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void tfCustomerSearchOnKeyReleased(KeyEvent event) {
    }

    @FXML
    private void tblCustomerOnClick(MouseEvent event) {
        mbtnCustomer.setText(tbletu.getSelectionModel().getSelectedItem().membrenumero);
        etuid = tbletu.getSelectionModel().getSelectedItem().getMembreid();
        System.out.println(etuid);
        mbtnCustomer.hide();
    }

    @FXML
    private void mbtnCustomerOnClicked(MouseEvent event) {
        membres.numero = tfCustomerSearch.getText().trim();
        tbletu.setItems(membres.employeeLists);
        tblClmCustomerName.setCellValueFactory(new PropertyValueFactory<>("membrenumero"));
        tblClmCustomerPhoneNo.setCellValueFactory(new PropertyValueFactory<>("mambrenom"));
        membresgetway.view(membres);
    }



    @FXML
    private void tfProduitrecherche(KeyEvent event) {
        tfProductId.setText(tblLivr.getSelectionModel().getSelectedItem().getTitre());
        docid = tblLivr.getSelectionModel().getSelectedItem().getIdDoc();
    }

    @FXML
    private void tblProduitOnClick(MouseEvent event) {
        tfProductId.setText(tblLivr.getSelectionModel().getSelectedItem().getTitre());
        docid = tblLivr.getSelectionModel().getSelectedItem().getIdDoc();
        System.out.println(docid);        
        ListDoc lst = tblLivr.getSelectionModel().getSelectedItem();
        String items = lst.getIdDoc();
        //int itemsInt = Integer.parseInt(items);
        tfProductId.setText(items);
        mbtnselectProduit.hide();
    }

    @FXML
    private void mbtnProduitOnactionselect(MouseEvent event) {
        prod.titre = tfProduitSearch.getText().trim();
        tblLivr.setItems(prod.currentDoctList);
        tblClmProduitID.setCellValueFactory(new PropertyValueFactory<>("idDoc"));
        tblClmProduitNom.setCellValueFactory(new PropertyValueFactory<>("titre"));
        produitgetway.searchView(prod);
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnAjoutOnAction(ActionEvent event) {
       if(isNotNull()){
           Emprunt e=new Emprunt();
           e.dateretour=dpemp.getValue().toString();
           e.dateemprunt=dpret.getValue().toString();
           e.numerodocument=docid;
           e.numeroetudiant=mbtnCustomer.getText();
           
           
           
           
           bddConnection dbCon = new bddConnection();
        con = dbCon.geConnection();
        
         if (con != null) {
              try {
                pst = con.prepareStatement("SELECT * FROM " + db + ".emprunt where idMembres='"+mbtnCustomer.getText().trim()+"' and etat='true'");
                rs = pst.executeQuery();
              if (rs.next()) {
                  
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Membre ayant une emprunt");
        alert.setContentText("Le membre selectionné a déja emprunté un livre \nVoulez vous comfirmer l'emprunt d'un autre livre ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            empruntg.save(e);
            clear();
        }          

        } else {
            empruntg.save(e);
            clear();
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
       
    }
    public boolean isNotNull() {
        boolean isNotNull = false;

        if (mbtnCustomer.getText().matches("Selectionner l'etudiant") || tfProductId.getText() == null || dpemp.getValue()==null || dpret.getValue()==null ) {
//            Dialogs.create().title("").masthead("ERROR").message("Please fill requere field").styleClass(org.controlsfx.dialog.Dialog.STYLE_CLASS_UNDECORATED).showError();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR");
            alert.setContentText("Veuiller remplir les champs necessaires");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            return isNotNull;
        } else {
            isNotNull = true;
        }
        return isNotNull;
    }
    
    public void clear(){
        tfProductId.setText(null);
        dpemp.setValue(null);
        dpret.setValue(null);
        mbtnCustomer.setText("Selectionner l'etudiant");
        
    }
}
