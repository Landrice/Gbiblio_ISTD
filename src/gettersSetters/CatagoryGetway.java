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
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Admin
 */
public class CatagoryGetway {
    SQLSyntax sql = new SQLSyntax();
    bddConnection dbCon = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    public void save(Catagory catagory) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into "+db+".categorie values(?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, catagory.catagoryName);
            pst.setString(3, LocalDate.now().toString());
            pst.setString(4, catagory.creatorId);
            
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Ajout avec succès");
            alert.setContentText("categorie" + "  '" + catagory.catagoryName + "' " + "ajouté avec suucès");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void view(Catagory catagory) {
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".categorie");
            rs = pst.executeQuery();
            while (rs.next()) {
                catagory.id = rs.getString(1);
                catagory.catagoryName = rs.getString(2);                
                catagory.date = rs.getString(3);
                catagory.creatorId = rs.getString(4);
                catagory.catagoryDetails.addAll(new ListCatagory(catagory.id, catagory.catagoryName, catagory.date, catagory.creatorId));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectedView(Catagory catagory) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".categorie where Id=?");
            pst.setString(1, catagory.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                catagory.id = rs.getString(1);
                catagory.catagoryName = rs.getString(2);
                catagory.date = rs.getString(3);
                catagory.creatorId = rs.getString(4);
                //catagory.supplyerName = sql.getName(catagory.supplyerId, catagory.supplyerName, "fournisseur");
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void searchView(Catagory catagory) {
        con = dbCon.geConnection();
        catagory.catagoryDetails.clear();

        try {
            pst = con.prepareStatement("select * from "+db+".categorie where nomCategorie like ? ORDER BY nomCategorie");

            pst.setString(1, "%" + catagory.catagoryName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                catagory.id = rs.getString(1);
                catagory.catagoryName = rs.getString(2);
                catagory.date = rs.getString(3);
                catagory.creatorId = rs.getString(4);

                catagory.creatorName = sql.getName(catagory.creatorId, catagory.catagoryName, "utilisateur");

                catagory.catagoryDetails.addAll(new ListCatagory(catagory.id, catagory.catagoryName, catagory.date, catagory.creatorId));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Catagory catagory) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("update "+db+".categorie set nomCategorie=?  where Id=?");
            pst.setString(1, catagory.catagoryName);
            pst.setString(2, catagory.id);
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Modification avec succès");
            alert.setContentText("Categorie" + "  '" + catagory.catagoryName + "' " + "modifié avec succès");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            System.out.println("Erreur sur "+e);
        }

    }

    public void delete(Catagory catagory) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("delete from "+db+".categorie where Id=?");
            pst.setString(1, catagory.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
