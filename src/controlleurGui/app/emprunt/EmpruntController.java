/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurGui.app.emprunt;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import gettersSetters.Emprunt;
import gettersSetters.EmpruntGetway;
import gettersSetters.Paginate;
import gettersSetters.EmpruntCard;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class EmpruntController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private FontAwesomeIconView btnNouveau;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblShowing;
    @FXML
    private ScrollPane scrollPan;
    @FXML
    private FlowPane flowPane;
    
    int totalEmprunt;
    int totalSearchPatient;
    EmpruntGetway empruntgetway=new EmpruntGetway();
    ObservableList<Emprunt> emprunts = FXCollections.observableArrayList();
    Paginate paginate = new Paginate();
    private boolean patientDefault = true;
    @FXML
    private JFXButton btnNew;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        scrollPan.viewportBoundsProperty().addListener((ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) -> {
            flowPane.setPrefWidth(newValue.getWidth());
        });

        lblTotal.setText("Loading..........");

        Platform.runLater(() -> {
            totalEmprunt = empruntgetway.total();
            loadEmprunts();
            lblTotal.setText("Total :" + totalEmprunt);
            lblShowing.setText("Affichage " + paginate.getStart() + " au " + paginate.getEnd());
            if (totalEmprunt == 0) {
                lblTotal.setText("Aucune emprunt trouvé");
                lblShowing.setVisible(false);
            }
        });
    }    

    @FXML
    private void tfSearchOnAction(ActionEvent event) {
        search();
    }

    @FXML
    private void handleSearchButton(ActionEvent event) {
         if (tfSearch.getText() == null || tfSearch.getText().trim().length() == 0) {
            patientDefault = true;
            loadEmprunts();

        } else {
            System.out.println("ici");
            searchEmprunt(tfSearch.getText().trim());
        }
    }

    @FXML
    private void btnNouveauOnAction(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/app/emprunt/NewEmprunt.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));            
            Stage nStage = new Stage();            
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }

    @FXML
    private void handlePrevButton(ActionEvent event) {
        if (paginate.getStart() > 0) {
            paginate.setStart(paginate.getStart() - paginate.getPerPage());
            paginate.setEnd(paginate.getStart() + paginate.getPerPage());
            System.out.println("Emprunt par defaut : " + patientDefault);
            if (patientDefault) {
                loadEmprunts();
                lblShowing.setText("Affichage  " + paginate.getStart() + " du " + (paginate.getStart() + paginate.getPerPage()));
            } else {
                searchEmprunt(tfSearch.getText().trim());
                lblTotal.setText("Total : " + String.valueOf(totalSearchPatient));
                lblShowing.setText("Affichage " + paginate.getStart() + " sur " + (paginate.getStart() + paginate.getPerPage()));
            }
        } else {
            handleNextButton(event);
        }
    }

    @FXML
    private void handleNextButton(ActionEvent event) {
        paginate.setStart(paginate.getStart() + paginate.getPerPage());
        paginate.setEnd(paginate.getStart() + paginate.getPerPage());
        System.out.println("Default Emprunt : " + patientDefault);
        if (patientDefault) {
            loadEmprunts();
            lblShowing.setText("Affichage " + paginate.getStart() + " du " + (paginate.getStart() + paginate.getPerPage()));
            if (emprunts.isEmpty()) {
                paginate.setStart(0);
                paginate.setEnd(10);
                loadEmprunts();
                lblShowing.setText("Affichage " + paginate.getStart() + " du " + (paginate.getStart() + paginate.getPerPage()));
            }
        } else {
            searchEmprunt(tfSearch.getText().trim());
            lblTotal.setText("Total : " + String.valueOf(totalSearchPatient));
            lblShowing.setText("Affichage " + paginate.getStart() + " du " + (paginate.getStart() + paginate.getPerPage()));
            if (emprunts.isEmpty()) {
                paginate.setStart(0);
                paginate.setEnd(10);
                searchEmprunt(tfSearch.getText().trim());
                lblShowing.setText("Affichage " + paginate.getStart() + " du " + (paginate.getStart() + paginate.getPerPage()));
            }
        }
    }
    
    private void loadEmprunts() {
        patientDefault = true;
        flowPane.getChildren().clear();
        paginate.setPerPage(12);
        paginate.setEnd(12);
        emprunts = empruntgetway.emp(paginate);
        emprunts.forEach((emprunt) -> {
            EmpruntCard card = new EmpruntCard(emprunt) {
                @Override
                protected void RetourOnAction(ActionEvent actionEvent) {
                    retour(emprunt);
                }

                
            };
            flowPane.getChildren().add(card);
        });
    }
    
    public void retour(Emprunt emp){
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Retour ?");
        alert.setHeaderText("Retour ?");
        alert.setContentText("Voulez vous vraiment confirmer le retour");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            empruntgetway.retour(emp);
        }
    }
    
    private void searchEmprunt(String query) {
        patientDefault = false;
        totalSearchPatient = empruntgetway.totalSearchPatient(query);
        paginate.setTotal(totalSearchPatient);
        lblTotal.setText("Total : " + totalSearchPatient);
        lblShowing.setText("Affichage " + paginate.getStart() + " du " + paginate.getEnd());
        paginate.setStart(0);
        paginate.setPerPage(12);
        paginate.setEnd(12);
        emprunts = empruntgetway.searchPatient(paginate, query);
        flowPane.getChildren().clear();
        emprunts.forEach((patient) -> {
            EmpruntCard card = new EmpruntCard(patient) {
                @Override
                protected void RetourOnAction(ActionEvent actionEvent) {
                    retour(patient);
                }


            };
            flowPane.getChildren().add(card);
        });

    }

    @FXML
    private void btnNewOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/app/emprunt/NewEmprunt.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));            
            Stage nStage = new Stage();            
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }

    @FXML
    private void tfSearchOnAction(KeyEvent event) {
        search();
    }
    
    public void search(){
        if (tfSearch.getText() == null || tfSearch.getText().trim().length() == 0) {
            patientDefault = true;
            loadEmprunts();
        } else {
            System.out.println("Hear");
            searchEmprunt(tfSearch.getText().trim());
        }
    }
}
