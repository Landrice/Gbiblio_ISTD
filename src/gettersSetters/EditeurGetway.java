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
public class EditeurGetway {
    bddConnection dbCon = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    SQLSyntax sql=new SQLSyntax();

    public void save(Editeur supplyer) {
        con = dbCon.geConnection();
        
            try {
                con = dbCon.geConnection();
                pst = con.prepareCall("insert into "+db+".editeur values(?,?,?,?,?,?)");
                pst.setString(1, null);
                pst.setString(2, supplyer.nom);
                pst.setString(3, supplyer.localisation);
                pst.setString(4, supplyer.siteweb);
                pst.setString(5, supplyer.contact);
                pst.setString(6, supplyer.creatorId);
                pst.executeUpdate();
                con.close();
                pst.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succèss");
                alert.setHeaderText("Ajout avec succes");
                alert.setContentText("Editeur" + "  '" + supplyer.nom + "' " + "Ajouté avec succès");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException ex) {
                Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
                
               System.out.println("Asa eto edy erreur"+ supplyer.nom);
            }
        

    }

    public void view(Editeur supplyer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select * from "+db+".editeur");
            rs = pst.executeQuery();
            while (rs.next()) {
                supplyer.id = rs.getString(1);
                supplyer.nom = rs.getString(2);
                supplyer.localisation = rs.getString(3);
                supplyer.siteweb = rs.getString(4);
                supplyer.contact = rs.getString(5);
                supplyer.creatorId = rs.getString(6);
                supplyer.nom = sql.getName(supplyer.creatorId, supplyer.nom, "utilisateur");
                supplyer.supplyerDetails.addAll(new ListEditeur(supplyer.id, supplyer.nom, supplyer.localisation, supplyer.siteweb, supplyer.contact,supplyer.nom));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
//            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception: "+ex);
        }
    }

    public void searchView(Editeur supplyer) {
        supplyer.supplyerDetails.clear();
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".editeur where nomEditeur like ? or localisation like ? ORDER BY nomEditeur");
            pst.setString(1, "%" + supplyer.nom + "%");
            pst.setString(2, "%" + supplyer.localisation + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                supplyer.id = rs.getString(1);
                supplyer.nom = rs.getString(2);
                supplyer.localisation = rs.getString(3);
                supplyer.siteweb = rs.getString(4);
                supplyer.contact = rs.getString(5);
                supplyer.creatorId = rs.getString(6);
                supplyer.nom = sql.getName(supplyer.creatorId, supplyer.nom, "utilisateur");
                supplyer.supplyerDetails.addAll(new ListEditeur(supplyer.id, supplyer.nom, supplyer.localisation, supplyer.siteweb, supplyer.contact,supplyer.nom));
            }
            rs.close();
            con.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectedView(Editeur supplyer) {
        System.out.println("nom :" + supplyer.nom);
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".editeur where Id=?");
            pst.setString(1, supplyer.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                supplyer.id = rs.getString(1);
                supplyer.nom = rs.getString(2);
                supplyer.localisation = rs.getString(3);
                supplyer.siteweb = rs.getString(4);
                supplyer.contact = rs.getString(5);
                supplyer.creatorId = rs.getString(6);
            }
            rs.close();
            con.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Editeur supplyer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("update "+db+".editeur set nomEditeur=? , localisation=?,siteWeb=? ,contact=? where Id=?");
            pst.setString(1, supplyer.nom);
            pst.setString(2, supplyer.localisation);
            pst.setString(3, supplyer.siteweb);
            pst.setString(4, supplyer.contact);
            pst.setString(5, supplyer.id);
            pst.executeUpdate();
            con.close();
            pst.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Modification avec succès");
            alert.setContentText("Le fournisseur" + "  '" + supplyer.nom + "' " + "a été modifié");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteParmanently(Editeur supplyer) {
        con = dbCon.geConnection();
        try {
            System.out.println("and i am hear");
            con = dbCon.geConnection();
            pst = con.prepareCall("delete from "+db+".editeur where Id=?");
            pst.setString(1, supplyer.id);
            pst.executeUpdate();
            con.close();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
