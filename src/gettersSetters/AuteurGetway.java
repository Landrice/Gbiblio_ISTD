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
public class AuteurGetway {
     SQLSyntax sql = new SQLSyntax();

    bddConnection dbCon = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    public void save(Auteur brands) {
        con=dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into "+db+".auteur values(?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, brands.auteurnom);
            pst.setString(3, brands.auteurprenom);
            pst.setString(4, LocalDate.now().toString());
            pst.setString(5, brands.creatorId);
            pst.executeUpdate();
            con.close();
            pst.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succèss");
            alert.setHeaderText("Ajout avec succès");
            alert.setContentText("Auteur " + "  '" + brands.auteurnom + "' " + "ajouté avec succès");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void view(Auteur brands) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareCall("select * from "+db+".auteur");
            rs = pst.executeQuery();
            while (rs.next()) {
                brands.id = rs.getString(1);
                brands.auteurnom = rs.getString(2);
                brands.auteurprenom = rs.getString(3);
                brands.date = rs.getString(4);
                brands.creatorId = rs.getString(5);
                brands.creatorName = sql.getName(brands.creatorId, brands.creatorName, "utilisateur");
                brands.brandDitails.addAll(new ListAuteur(brands.id, brands.auteurnom, brands.auteurprenom, brands.creatorName, brands.date));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void selectedView(Auteur brands) {
        con = dbCon.geConnection();

        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".auteur where Id=?");
            pst.setString(1, brands.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                brands.id = rs.getString(1);
                brands.auteurnom = rs.getString(2);
                brands.auteurprenom = rs.getString(3);
                brands.date = rs.getString(4);
                
            }
            con.close();
            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchView(Auteur brands) {
        con = dbCon.geConnection();

        brands.brandDitails.clear();
        System.out.println("name :" + brands.auteurnom);

        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".auteur where nom like ? OR prenom like ? ORDER BY nom");
            System.out.println("Brand name in Brand Object");
            pst.setString(1, "%" + brands.auteurnom + "%");
            pst.setString(2, "%" + brands.auteurprenom + "%");

            rs = pst.executeQuery();
            while (rs.next()) {
                brands.id = rs.getString(1);
                brands.auteurnom = rs.getString(2);
                brands.auteurprenom = rs.getString(3);
                brands.date = rs.getString(4);
                brands.creatorId = rs.getString(5);
                brands.creatorName = sql.getName(brands.creatorId, brands.creatorName, "utilisateur");
                brands.brandDitails.addAll(new ListAuteur(brands.id, brands.auteurnom, brands.auteurprenom, brands.creatorName, brands.date));
            }
            con.close();
            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Auteur brands) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("delete from "+db+".auteur where Id=?");
            pst.setString(1, brands.id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Auteur brands) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("update "+db+".auteur set nom=?, prenom=? where Id=?");
            pst.setString(1, brands.auteurnom);
            pst.setString(2, brands.auteurprenom);
            pst.setString(3, brands.id);
            pst.executeUpdate();
            con.close();
            pst.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucèss");
            alert.setHeaderText("Modification avec succès ");
            alert.setContentText("Modification" + "  '" + brands.auteurnom + "' " + " avec succès");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    

}
