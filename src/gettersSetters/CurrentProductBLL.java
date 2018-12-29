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
public class CurrentProductBLL {
    
    bddConnection dbCon = new bddConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    
    SQLSyntax sql = new SQLSyntax();
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();

    public void save(CurrentProduct currentProduct) {
        if (isUniqName(currentProduct)) {
            currentProductGetway.save(currentProduct);
        }
        
    }

    public void update(CurrentProduct currentProduct) {
        if(isNotNull(currentProduct)){
        
            if (checkUpdateCondition(currentProduct)) {
                currentProductGetway.update(currentProduct);
            } else if (isUniqName(currentProduct)) {
                currentProductGetway.update(currentProduct);
            }
        
        }else System.out.println("Erreur, else de ifNotNull");
    }

    public boolean isUniqName(CurrentProduct currentProduct) {
        System.out.println("WE ARE IS IS UNIT NAME");
        boolean isUniqname = false;
        try {
            pst = con.prepareStatement("select * from "+db+".document where idDocument=?");
            pst.setString(1, currentProduct.idDoc);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur: Non Unique");
                alert.setContentText("Produit id" + "  '" + currentProduct.idDoc + "' " + "n'est pas unique");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                
                return isUniqname;
            }
            isUniqname = true;
        } catch (SQLException e) {
            System.out.println("Erreur SQL: "+e);
        }
        return isUniqname;
    }


    public boolean checkUpdateCondition(CurrentProduct currentProduct) {
        boolean isTrueUpdate = false;
            try {
                pst = con.prepareStatement("select * from "+db+".document where Id=? and idDocument=?");
                pst.setString(1, currentProduct.id);
                pst.setString(2, currentProduct.idDoc);
                rs = pst.executeQuery();
                while (rs.next()) {

                    return isTrueUpdate = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return isTrueUpdate;
    }

    public boolean isNotNull(CurrentProduct currentProduct) {
        
        boolean isNotNull = false;
        if (currentProduct.idDoc.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setHeaderText("ERREUR : Null");
                alert.setContentText("Veuillez remplir les champs");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            
            return isNotNull;
        }else{
        System.out.println("Ato izy");
        isNotNull=true;
        }
        return isNotNull;
    }

    public Object delete(CurrentProduct currentProduct){
        
            currentProductGetway.delete(currentProduct);
        
        return currentProduct;
    }
    
}
