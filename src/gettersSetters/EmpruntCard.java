/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gettersSetters;

import gbiblio_istd.Gbiblio_ISTD;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;

/**
 *
 * @author Ralande
 */
public abstract class EmpruntCard extends AnchorPane {

    protected final HBox hBox;
    protected final VBox vBox;
    protected final ImageView imageView;
    protected final VBox vBox0;
    protected final HBox hBox00;
    protected final Label label;
    protected final Label labe00;
    protected final Label labe000;
    protected final Label label0;
    protected final Label label101;
    protected final Label label102;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final Label label31;
    protected final ButtonBar buttonBar;
    protected final Button button0;
    protected final Button button1;
    protected final Button button2;

    protected final String enmruntId;
    private String sexe = "";
    private String etat="";

    public EmpruntCard(Emprunt emp) {
        
        enmruntId = emp.getId();
        hBox = new HBox();
        vBox = new VBox();
        imageView = new ImageView();
        vBox0 = new VBox();
        hBox00 = new HBox();
        label = new Label();
        labe00 = new Label();
        labe000 = new Label();
        label0 = new Label();
        label101= new Label();
        label102 = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        label31 = new Label();
        buttonBar = new ButtonBar();
        button0 = new Button();
        button1 = new Button();
        button2 = new Button();

        setPrefHeight(196.0);
        setPrefWidth(451.0);
        getStyleClass().add("card");
        getStylesheets().add(Gbiblio_ISTD.class.getResource("/css/main.css").toExternalForm());

        AnchorPane.setBottomAnchor(hBox, 0.0);
        AnchorPane.setLeftAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        AnchorPane.setTopAnchor(hBox, 0.0);
        hBox.setPrefHeight(182.0);
        hBox.setPrefWidth(325.0);
        hBox.setSpacing(7.0);        
        hBox00.setSpacing(3);
        vBox.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        vBox.setPrefHeight(182.0);
        vBox.setPrefWidth(172.0);

        imageView.setFitHeight(121.0);
        imageView.setFitWidth(150.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
       
        try {
            showImage(emp);
        } catch (SQLException ex) {
            Logger.getLogger(EmpruntCard.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
        }

        vBox0.setPrefHeight(162.0);
        vBox0.setPrefWidth(275.0);
        
        labe00.getStyleClass().add("card-etu");
        labe000.getStyleClass().add("card-num");
        label.getStyleClass().add("card-header");
        
        
        if(emp.getEtatemprunt().equals("true")){
            etat="Non rendu";
            label102.setTextFill(Color.RED);
            label102.setText("Etat : " + etat );
        }else if(emp.getEtatemprunt().equals("false")){
            etat="Rendu";
            button1.getStyleClass().add("btn-secondary");
            button1.setDisable(true);
            label102.setTextFill(Color.GREEN);
            label102.setText("Etat : " + etat );
        }
        sexe = emp.getSexeetudiant();
        
        label.setText(emp.titredocument);
        labe00.setText(emp.numerodocument);
        labe000.setText(emp.numeroetudiant);
        label0.setText("Emprunté par : " + emp.getNometudiant() );
        label101.setText("Prenom : " + emp.getPrenometudiant() );
        
        label1.setText("Sexe : " + sexe);
        
         label2.setText("Parcours : " + emp.getParcoursetudiant());
         label3.setText("Emprunt du : "+emp.getDateemprunt());
         label31.setText("Date retour prévue : "+emp.getDateretour());
         


        buttonBar.setPrefHeight(40.0);
        buttonBar.setPrefWidth(200.0);

        button1.setMnemonicParsing(false);
        button1.setOnAction(this::RetourOnAction);
        button1.getStyleClass().add("btn-primary");
        button1.setCursor(Cursor.HAND);
        button1.setText("Retour emprunt");
        VBox.setMargin(buttonBar, new Insets(7.0, 0.0, 0.0, 0.0));

        button2.setMnemonicParsing(false);
        button2.setPrefHeight(41.0);
        button2.setPrefWidth(69.0);
        button2.getStyleClass().add("card-btn-delete");
        button2.setText("✘");
        HBox.setMargin(button2, new Insets(-25.0, -25.0, 0.0, 0.0));
        
        hBox00.getChildren().add(labe00);
        hBox00.getChildren().add(labe000);
        vBox.getChildren().add(imageView);
        hBox.getChildren().add(vBox);
        vBox0.getChildren().add(label);
        vBox0.getChildren().add(hBox00);
        vBox0.getChildren().add(label0);
        vBox0.getChildren().add(label101);
        vBox0.getChildren().add(label1);
        vBox0.getChildren().add(label2);
        vBox0.getChildren().add(label3);
        vBox0.getChildren().add(label31);
        vBox0.getChildren().add(label102);
        buttonBar.getButtons().add(button1);
        vBox0.getChildren().add(buttonBar);
        hBox.getChildren().add(vBox0);
       // hBox.getChildren().add(button2);
        getChildren().add(hBox);

    }
    
    private void showImage(Emprunt emp) throws SQLException{
        //Image img;
        imageView.setImage(new Image(getClass().getResource("/images/avatarj.jpg").toExternalForm()));
    }
    protected abstract void RetourOnAction(javafx.event.ActionEvent actionEvent);

}

