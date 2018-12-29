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

/**
 *
 * @author Admin
 */
public class CurrentProductGetway {

    bddConnection dbCon = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    SQLSyntax sql = new SQLSyntax();

    public void save(CurrentProduct currentProduct) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into " + db + ".document values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, currentProduct.idDoc);
            pst.setString(3, currentProduct.titre);
            pst.setString(4, currentProduct.nombre);
            pst.setString(5, currentProduct.date);
            pst.setString(6, currentProduct.type);
            pst.setString(7, currentProduct.nombrepage);
            pst.setString(8, currentProduct.langue);
            pst.setString(9, currentProduct.description);
            pst.setString(10, currentProduct.idediteur);
            pst.setString(11, currentProduct.idcategorie);
            pst.setString(12, currentProduct.idauteur);
            pst.setString(13, currentProduct.idutilisateur);
            pst.executeUpdate();
            pst.close();
            con.close();
            
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Succes");
            a.setHeaderText("Ajout succès");
            a.setContentText("Le document "+currentProduct.titre+" a été ajouté avec succès");
            a.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Too Many Connection");
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setHeaderText("Erreur d'ajout du document");
            a.setContentText(ex.getMessage());
            a.showAndWait();

        }

    }

    public void view(CurrentProduct currentProduct) {
        currentProduct.currentDoctList.clear();
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("SELECT SQL_NO_CACHE * FROM " + db + ".document ORDER BY titre");
            rs = pst.executeQuery();
            while (rs.next()) {

                currentProduct.id = rs.getString(1);
                currentProduct.idDoc = rs.getString(2);
                currentProduct.titre = rs.getString(3);
                currentProduct.nombre = rs.getString(4);
                currentProduct.date= rs.getString(5);
                currentProduct.type = rs.getString(6);
                currentProduct.nombrepage = rs.getString(7);
                currentProduct.langue = rs.getString(8);
                currentProduct.description = rs.getString(9);
                currentProduct.idediteur = rs.getString(10);
                currentProduct.idcategorie = rs.getString(11);
                currentProduct.idauteur = rs.getString(12);
                currentProduct.idutilisateur = rs.getString(13);
                currentProduct.auteurNom = sql.getName(currentProduct.idauteur, currentProduct.auteurNom, "auteur");
                currentProduct.editionNom = sql.getName(currentProduct.idediteur, currentProduct.editionNom, "editeur");
                currentProduct.categorieNom = sql.getName(currentProduct.idcategorie, currentProduct.categorieNom, "categorie");
                currentProduct.nom = sql.getName(currentProduct.idutilisateur, currentProduct.nom, "utilisateur");
                currentProduct.currentDoctList.addAll(new ListDoc(currentProduct.id, currentProduct.idDoc, currentProduct.titre, currentProduct.nombre, currentProduct.description, currentProduct.auteurNom, currentProduct.editionNom, currentProduct.categorieNom, currentProduct.type, currentProduct.nombrepage, currentProduct.langue, currentProduct.nom, currentProduct.date));
            }
            pst.close();
            con.close();
            rs.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void selectedView(CurrentProduct currentProduct) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from " + db + ".document where Id=?");
            pst.setString(1, currentProduct.id);
            rs = pst.executeQuery();
            while (rs.next()) {
//                
                currentProduct.id = rs.getString(1);
                currentProduct.idDoc = rs.getString(2);
                currentProduct.titre = rs.getString(3);
                currentProduct.nombre = rs.getString(4);
                currentProduct.date= rs.getString(5);
                currentProduct.type = rs.getString(6);
                currentProduct.nombrepage = rs.getString(7);
                currentProduct.langue = rs.getString(8);
                currentProduct.description = rs.getString(9);
                currentProduct.idediteur = rs.getString(10);
                currentProduct.idcategorie = rs.getString(11);
                currentProduct.idauteur = rs.getString(12);
                currentProduct.idutilisateur = rs.getString(13);
                currentProduct.auteurNom = sql.getName(currentProduct.idauteur, currentProduct.auteurNom, "auteur");
                currentProduct.editionNom = sql.getName(currentProduct.idediteur, currentProduct.editionNom, "editeur");
                currentProduct.categorieNom = sql.getName(currentProduct.idcategorie, currentProduct.categorieNom, "categorie");
                currentProduct.nom = sql.getName(currentProduct.idutilisateur, currentProduct.nom, "utilisateur");
                currentProduct.currentDoctList.addAll(new ListDoc(currentProduct.id, currentProduct.idDoc, currentProduct.titre, currentProduct.nombre, currentProduct.description, currentProduct.auteurNom, currentProduct.editionNom, currentProduct.categorieNom, currentProduct.type, currentProduct.nombrepage, currentProduct.langue, currentProduct.nom, currentProduct.date));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewFistTen(CurrentProduct currentProduct) {
        con = dbCon.geConnection();

        currentProduct.currentDoctList.clear();
        try {
            pst = con.prepareStatement("select * from " + db + ".document ORDER BY titre");
            rs = pst.executeQuery();
            while (rs.next()) {

                currentProduct.id = rs.getString(1);
                currentProduct.idDoc = rs.getString(2);
                currentProduct.titre = rs.getString(3);
                currentProduct.nombre = rs.getString(4);
                currentProduct.date= rs.getString(5);
                currentProduct.type = rs.getString(6);
                currentProduct.nombrepage = rs.getString(7);
                currentProduct.langue = rs.getString(8);
                currentProduct.description = rs.getString(9);
                currentProduct.idediteur = rs.getString(10);
                currentProduct.idcategorie = rs.getString(11);
                currentProduct.idauteur = rs.getString(12);
                currentProduct.idutilisateur = rs.getString(13);
                currentProduct.auteurNom = sql.getName(currentProduct.idauteur, currentProduct.auteurNom, "auteur");
                currentProduct.editionNom = sql.getName(currentProduct.idediteur, currentProduct.editionNom, "editeur");
                currentProduct.categorieNom = sql.getName(currentProduct.idcategorie, currentProduct.categorieNom, "categorie");
                currentProduct.nom = sql.getName(currentProduct.idutilisateur, currentProduct.nom, "utilisateur");
                currentProduct.currentDoctList.addAll(new ListDoc(currentProduct.id, currentProduct.idDoc, currentProduct.titre, currentProduct.nombre, currentProduct.description, currentProduct.auteurNom, currentProduct.editionNom, currentProduct.categorieNom, currentProduct.type, currentProduct.nombrepage, currentProduct.langue, currentProduct.nom, currentProduct.date));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }      
    public void searchView(CurrentProduct currentProduct) {
        con = dbCon.geConnection();

        currentProduct.currentDoctList.clear();
        try {
            pst = con.prepareStatement("select * from " + db + ".document where idDocument like ? or titre like ?");
            pst.setString(1, "%" + currentProduct.idDoc + "%");
            pst.setString(2, "%" + currentProduct.titre + "%");
            rs = pst.executeQuery();
            while (rs.next()) {

                currentProduct.id = rs.getString(1);
                currentProduct.idDoc = rs.getString(2);
                currentProduct.titre = rs.getString(3);
                currentProduct.nombre = rs.getString(4);
                currentProduct.date= rs.getString(5);
                currentProduct.type = rs.getString(6);
                currentProduct.nombrepage = rs.getString(7);
                currentProduct.langue = rs.getString(8);
                currentProduct.description = rs.getString(9);
                currentProduct.idediteur = rs.getString(10);
                currentProduct.idcategorie = rs.getString(11);
                currentProduct.idauteur = rs.getString(12);
                currentProduct.idutilisateur = rs.getString(13);
                currentProduct.auteurNom = sql.getName(currentProduct.idauteur, currentProduct.auteurNom, "auteur");
                currentProduct.editionNom = sql.getName(currentProduct.idediteur, currentProduct.editionNom, "editeur");
                currentProduct.categorieNom = sql.getName(currentProduct.idcategorie, currentProduct.categorieNom, "categorie");
                currentProduct.nom = sql.getName(currentProduct.idutilisateur, currentProduct.nom, "utilisateur");
                currentProduct.currentDoctList.addAll(new ListDoc(currentProduct.id, currentProduct.idDoc, currentProduct.titre, currentProduct.nombre, currentProduct.description, currentProduct.auteurNom, currentProduct.editionNom, currentProduct.categorieNom, currentProduct.type, currentProduct.nombrepage, currentProduct.langue, currentProduct.nom, currentProduct.date));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sView(CurrentProduct currentProduct) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("select * from " + db + ".document where idDocument=?");
            pst.setString(1, currentProduct.idDoc);
            rs = pst.executeQuery();
            while (rs.next()) {
                currentProduct.id = rs.getString(1);
                currentProduct.idDoc = rs.getString(2);
                currentProduct.titre = rs.getString(3);
                currentProduct.nombre = rs.getString(4);
                currentProduct.date= rs.getString(5);
                currentProduct.type = rs.getString(6);
                currentProduct.nombrepage = rs.getString(7);
                currentProduct.langue = rs.getString(8);
                currentProduct.description = rs.getString(9);
                currentProduct.idediteur = rs.getString(10);
                currentProduct.idcategorie = rs.getString(11);
                currentProduct.idauteur = rs.getString(12);
                currentProduct.idutilisateur = rs.getString(13);
                currentProduct.auteurNom = sql.getName(currentProduct.idauteur, currentProduct.auteurNom, "auteur");
                currentProduct.editionNom = sql.getName(currentProduct.idediteur, currentProduct.editionNom, "editeur");
                currentProduct.categorieNom = sql.getName(currentProduct.idcategorie, currentProduct.categorieNom, "categorie");
                currentProduct.nom = sql.getName(currentProduct.idutilisateur, currentProduct.nom, "utilisateur");
                //currentProduct.currentDoctList.addAll(new ListDoc(currentProduct.id, currentProduct.idDoc, currentProduct.titre, currentProduct.nombre, currentProduct.description, currentProduct.auteurNom, currentProduct.editionNom, currentProduct.categorieNom, currentProduct.type, currentProduct.nombrepage, currentProduct.langue, currentProduct.nom, currentProduct.date));

            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cbSupplyer(CurrentProduct currentProduct) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("select * from " + db + ".Editeur");
            rs = pst.executeQuery();
            while (rs.next()) {
                currentProduct.fournisseurList = rs.getString(2);
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(CurrentProduct currentProduct) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("update " + db + ".document set idDocument=?, titre=?, nombre=?, description=?, "
                    + "idEditeur=?, idCategorie=?, idAuteur=?,"
                    + "idUtilisateur=?, Date=?,type=?,nombrePage=?,langue=? where Id=?");
            pst.setString(1, currentProduct.idDoc);
            pst.setString(2, currentProduct.titre);
            pst.setString(3, currentProduct.nombre);
            pst.setString(4, currentProduct.description);
            pst.setString(5, currentProduct.idediteur);
            pst.setString(6, currentProduct.idcategorie);
            pst.setString(7, currentProduct.idauteur);
            pst.setString(8, currentProduct.idutilisateur);
            pst.setString(9, currentProduct.date);
            pst.setString(10, currentProduct.type);
            pst.setString(11, currentProduct.nombrepage);
            pst.setString(12, currentProduct.langue);
            pst.setString(13, currentProduct.id);
            pst.executeUpdate();
            pst.close();
            con.close();
            //    rs.close();
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Succes");
            a.setHeaderText("Modification avec succès");
            a.setContentText("Le document "+currentProduct.idDoc+" a été bine modifié avec succès");
            a.showAndWait();

        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ato erreur sql 01: " + ex);
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setHeaderText("Erreur du modification");
            a.setContentText(ex.getMessage());
            a.showAndWait();
        }
    }

    public void delete(CurrentProduct currentProduct) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("delete from " + db + ".Document where Id=?");
            pst.setString(1, currentProduct.id);
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Succes");
            a.setHeaderText("Suppression avec succès");
            a.setContentText("Le document supprimé succès");
            a.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setHeaderText("Erreur de Suppression");
            a.setContentText(ex.getMessage());
            a.showAndWait();
        }
    }
}
