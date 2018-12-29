/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gettersSetters;

import bdd.BddPropreties;
import bdd.SQLSyntax;
import bdd.bddConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Admin
 */
public class AuteurBLL {
    SQLSyntax sql = new SQLSyntax();

    bddConnection dbCon = new bddConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    AuteurGetway brandsGetway = new AuteurGetway();

    public void save(Auteur brands) {
        if (isUniqName(brands)) {
            brandsGetway.save(brands);
        }
    }

    public void update(Auteur brands) {
         brandsGetway.update(brands);
    }

    public void delete(Auteur brands){
        brandsGetway.delete(brands);
    }

    public boolean isUniqName(Auteur brands) {
        boolean uniqSupplyer = false;
        try {
            pst = con.prepareCall("select * from "+db+".auteur where nom=?");
            pst.setString(1, brands.auteurnom);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("in not uniq");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setHeaderText("ERREUR: Déja utilisé");
                alert.setContentText("L'Auteur Existe déja");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
               
                return uniqSupplyer;
            }
            uniqSupplyer = true;
        } catch (SQLException ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqSupplyer;
    }

}
