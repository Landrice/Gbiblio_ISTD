/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gettersSetters;

import bdd.BddPropreties;
import bdd.bddConnection;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import list.Membres;
import list.Utilisateurs;

/**
 *
 * @author Ralande
 */
public class MembresGetWay {
    bddConnection dbConnection = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    public void save(Membres membr) {

        if (isUniqName(membr)) {
            con = dbConnection.geConnection();
            try {
                pst = con.prepareStatement("insert into "+db+".membres values(?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, null);
                pst.setString(2, membr.numero);
                pst.setString(3, membr.nom);
                pst.setString(4, membr.prenom);
                pst.setString(5, membr.sexe);
                pst.setString(6, membr.parcours);
                pst.setString(7, membr.datenaissance);
                if (membr.imagePath != null) {
                    InputStream is;
                    is = new FileInputStream(new File(membr.imagePath));
                    pst.setBlob(8, is);
                } else {
                    pst.setBlob(8, (Blob) null);
                }
                pst.setString(9, membr.identifiant);
                pst.setString(10, membr.mdp);
                pst.executeUpdate();
                pst.close();
                con.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès :");
                alert.setHeaderText("Succès");
                alert.setContentText("Membres " + membr.nom + " Ajouté avec succès");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void view(Membres users) {
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".membres");
            rs = pst.executeQuery();
            while (rs.next()) {
                users.id = rs.getString(1);
                users.numero = rs.getString(2);
                users.nom = rs.getString(3);
                users.employeeLists.addAll(new ListMembres(users.id, users.numero, users.nom));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void selectedView(Membres users) {
        con = dbConnection.geConnection();
        try {
            pst = con.prepareCall("select * from "+db+".membres where Id=?");
            pst.setString(1, users.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                users.id = rs.getString(1);
                users.numero = rs.getString(2);
                users.nom = rs.getString(3);
                users.prenom = rs.getString(4);
                users.sexe = rs.getString(5);
                users.parcours = rs.getString(6);
                users.datenaissance = rs.getString(7);
                users.photo = rs.getBlob(8);
                if (users.photo != null) {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(users.photo.getBytes(1, (int) users.photo.length()));
                    users.image = new Image(byteArrayInputStream);
                } else {
                    users.image = new Image("/images/avatar.jpg"); 
                  System.out.println("Image non définie");
                }
                users.identifiant = rs.getString(9);
                users.mdp = rs.getString(10);

            }
            rs.close();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void update(Membres users) {
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("UPDATE "+db+".membres SET numeroEtudiant=?,prenom=?,sexe=?,parcours=?,dateNaissance=?, photo=COALESCE(?, photo),identifiant=?,mdp=? WHERE nom=?");
            pst.setString(1, users.numero);
            pst.setString(2, users.prenom);
            pst.setString(3, users.sexe);
            pst.setString(4, users.parcours);
            pst.setString(5, users.datenaissance);
            if (users.imagePath != null) {
                InputStream is;
                is = new FileInputStream(new File(users.imagePath));
                pst.setBlob(6, is);
            } else if (users.imagePath == null) {
                pst.setBlob(6, (Blob) null);
            }
            pst.setString(7, users.nom);
            pst.setString(8, users.identifiant);
            pst.setString(10, users.mdp);
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès :");
            alert.setHeaderText("A jour !!");
            alert.setContentText("Membres " + users.nom + " a été modifié avec succès");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(Membres users) {
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("delete from "+db+".membres where Id=?");
            pst.setString(1, users.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUniqName(Membres users) {
        con = dbConnection.geConnection();
        boolean isUniqName = false;
        try {
            pst = con.prepareStatement("select * from "+db+".membres where nom=?");
            pst.setString(1, users.nom);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERREUR :");
                alert.setHeaderText("ERREUR : Nom existe");
                alert.setContentText("Nom " + users.nom + " existe deja");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return isUniqName;
            }
            rs.close();
            pst.close();
            con.close();
            isUniqName = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUniqName;
    }
}
