/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ralande
 */
public class bddModel {

    Properties properties = new Properties();
    InputStream inputStream;
    String db;
    

    public void loadPropertiesFile() {
        String path = System.getProperty("user.home");
   new File(path + "\\Documents\\DBISTD").mkdir();
        try {
            inputStream = new FileInputStream(path +"\\Documents\\DBISTD\\bdd.config");
            properties.load(inputStream);
            db = properties.getProperty("db");
        } catch (IOException e) {
            System.out.println("Erreur du Creation/lecture du fichier" + e);
        }
    }

    PreparedStatement pst;

    public void createDataBase() {
        loadPropertiesFile();
        bddConnection con = new bddConnection();

        try {
            System.out.println("Commencement----01");
            pst = con.mkDataBase().prepareStatement("create database if not exists " + db + " DEFAULT CHARACTER SET utf8 \n"
                    + "  DEFAULT COLLATE utf8_general_ci");
            pst.execute();
            System.out.println("Commencement----02");
            pst = con.mkDataBase().prepareStatement("create table if not exists " + db + ".`Utilisateur` (\n"
                    + "  `Id` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `nom` VARCHAR(100) NOT NULL,\n"
                    + "   `prenom` VARCHAR(100) NULL,\n"
                    + "  `email` VARCHAR(45) NULL,\n"
                    + "  `telephone` VARCHAR(45) NULL,\n"
                    + "   `adresse` VARCHAR(45) NULL,\n"
                    + "   `mdp` VARCHAR(255) NULL,\n"
                    + "   `photo` MEDIUMBLOB NULL,\n"
                    + "   `createurID` VARCHAR(45) NULL,\n"
                    + "   PRIMARY KEY (`Id`));");
            pst.execute();
            System.out.println("Commencement----03");
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`Editeur` (\n"
                    + "  `Id` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `nomEditeur` VARCHAR(45) NOT NULL,\n"
                    + "  `localisation` VARCHAR(255) NULL,\n"
                    + "  `siteWeb` VARCHAR(255) NULL,\n"
                    + "  `contact` VARCHAR(45) NULL,\n"
                    + "  `idUtilisateur` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`));");

            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`Categorie` (\n"
                    + "  `Id` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `nomCategorie` VARCHAR(45) NOT NULL,\n"
                    + "  `dateDajoutCategorie` VARCHAR(45) NULL,\n"
                    + "  `idUtilisateur` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`));");

            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`Auteur` (\n"
                    + "  `Id` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `nom` VARCHAR(45) NULL,\n"
                    + "  `prenom` VARCHAR(45) NULL,\n"
                    + "  `dateDajoutAuteur` VARCHAR(45) NULL,\n"
                    + "  `idUtilisateur` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`));");

            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`Document` (\n"
                    + "  `Id` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `idDocument` VARCHAR(45) NOT NULL,\n"
                    + "  `titre` VARCHAR(45) NOT NULL,\n"
                    + "  `nombre` INT NULL,\n"
                    + "   `date` VARCHAR(45) NULL,\n"
                    + "  `type` VARCHAR(45) NULL,\n"
                    + "  `nombrePage` INT NULL,\n"
                    + "  `langue` VARCHAR(45) NULL,\n"
                    + "  `description` VARCHAR(255) NULL,\n"
                    + "  `idEditeur` INT NOT NULL,\n"
                    + "  `idCategorie` INT NOT NULL,\n"
                    + "  `idAuteur` INT NOT NULL,\n"
                    + "  `idUtilisateur` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`id`));");

            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`Membres` (\n"
                    + "  `Id` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `numeroEtudiant` VARCHAR(45) NOT NULL,\n"
                    + "  `nom` VARCHAR(100) NULL,\n"
                    + "  `prenom` VARCHAR(100) NULL,\n"
                    + "  `sexe` VARCHAR(45) NULL,\n"
                    + "   `parcours` VARCHAR(45) NOT NULL,\n"
                    + "   `dateNaissance` VARCHAR(45) NULL,\n"
                    + "  `photo` MEDIUMBLOB NULL,\n"
                    + "   `identifiant` VARCHAR(45) NULL,\n"
                    + "   `mdp` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`Id`));");

            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`Emprunt` (\n"
                    + "  `Id` INT NOT NULL AUTO_INCREMENT,\n"
                    + "   `dateEmprunt` VARCHAR(45) NOT NULL,\n"
                    + "  `dateRetour` VARCHAR(45) NOT NULL,\n"
                    + "  `idMembres` VARCHAR(100) NOT NULL,\n"
                    + "  `idDocuments` VARCHAR(100) NOT NULL,\n"
                    + "  `etat` VARCHAR(45) NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`));");

            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`DroitUtilisateur` (\n"
                    + "  `Id` INT NOT NULL AUTO_INCREMENT,\n"
                    + "   `ajoutDocument` TINYINT(1) NOT NULL,\n"
                    + "  `ajoutAuteur` TINYINT(1) NOT NULL,\n"
                    + "   `ajoutCategorie` TINYINT(1) NOT NULL,\n"
                    + "    `ajoutEditeur` TINYINT(1) NOT NULL,\n"
                    + "  `ajoutMembre` TINYINT(1) NOT NULL,\n"
                    + "   `miseAjourDocument` TINYINT(1) NOT NULL,\n"
                    + "  `miseAjourAuteur` TINYINT(1) NOT NULL,\n"
                    + "  `miseAjourCategorie` TINYINT(1) NOT NULL,\n"
                    + "   `miseAjourEditeur` TINYINT(1) NOT NULL,\n"
                    + "  `miseAjourMembre` TINYINT(1) NOT NULL,\n"
                    + "  `gererUtilisateur` TINYINT(1) NOT NULL,\n"
                    + "  `idUtilisateur` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`));");

            pst.execute();
            System.out.println("Bdd crée avec succes");

        } catch (SQLException e) {
            System.err.println("Ato ai: " + e);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/gui/ConfigureServeur.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Configuration du serveur de la base de données");
                stage.showAndWait();
            } catch (IOException ex1) {
                Logger.getLogger(bddModel.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
}
