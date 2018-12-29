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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Ralande
 */
public class EmpruntGetway {
    bddConnection dbConnection = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    SQLSyntax sql=new SQLSyntax();
    
    public void save(Emprunt emprunt){
        con = dbConnection.geConnection();
        try {
                pst = con.prepareStatement("insert into "+db+".emprunt values(?,?,?,?,?,?)");
                pst.setString(1, null);
                pst.setString(2, emprunt.dateemprunt);
                pst.setString(3, emprunt.dateretour);
                pst.setString(4, emprunt.numeroetudiant);
                pst.setString(5, emprunt.numerodocument);
                pst.setString(6, "true");
                pst.executeUpdate();
                pst.close();
                con.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès :");
                alert.setHeaderText("Succès");
                alert.setContentText("Emprunt avec succès");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException e) {
                e.printStackTrace();
                 Alert a=new Alert(Alert.AlertType.ERROR);
            a.setTitle("Echec");
            a.setHeaderText("Emprunt non reussie");
            a.setContentText(e.getMessage());
            a.showAndWait();
            e.printStackTrace();
            }
    }
    
     public ObservableList<Emprunt> emp(Paginate paginate) {
         ObservableList<Emprunt> listData = FXCollections.observableArrayList();
         con = dbConnection.geConnection();
         Emprunt e=new Emprunt();
         try {
            pst = con.prepareStatement("select * from "+db+".emprunt order by etat desc limit " + paginate.getStart() + "," + paginate.getEnd());
            rs = pst.executeQuery();
            while (rs.next()) {
                listData.add(new Emprunt(rs.getString(1),
                        rs.getString(5), 
                        rs.getString(4), 
                        sql.getnometu(rs.getString(4),e.nometudiant), 
                        sql.getprenometu(rs.getString(4),e.prenometudiant), 
                        sql.getsexeetu(rs.getString(4),e.sexeetudiant), 
                        sql.getparcoursetu(rs.getString(4),e.parcoursetudiant), 
                        sql.gettitreDoc(rs.getString(5),e.titredocument), 
                        sql.gettypeDoc(rs.getString(5),e.typedocument), 
                        sql.getblobetu(rs.getString(4),e.photo), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getString(6)));
                              
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         
         return listData;
     }    
    public boolean delete(int i){
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("delete from "+db+".emprunt where Id="+i);
            pst.executeUpdate();
            pst.close();
            con.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public int total(){
        int total=0;
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("select count(Id) from "+db+".emprunt");
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
            pst.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    
    public void retour(Emprunt emp){
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("update "+db+".emprunt set etat=? where Id=?");
            pst.setString(1, "false");
            pst.setString(2, emp.getId());
            pst.executeUpdate();
            pst.close();
            con.close();
            
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Reussie");
            a.setHeaderText("Retour reussie");
            a.setContentText("Retour de l'emprunt reussie");
            a.showAndWait();

        } catch (SQLException e) {
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setTitle("Echec");
            a.setHeaderText("Retour non reussie");
            a.setContentText(e.getMessage());
            a.showAndWait();
            e.printStackTrace();
        }
    }
    public int totalSearchPatient(String query) {
        int total = 0;
        con = dbConnection.geConnection();
        
        try {
            pst = con.prepareStatement("select count(Id) from "+db+"emprunt where idMembres like ? or idDocuments like ? or etat like ?");
            pst.setString(1, query + "%");
            pst.setString(2, query + "%");
            pst.setString(3, query + "%");
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return total;
    }
    
    
    
    public ObservableList<Emprunt> searchPatient(Paginate paginate, String query) {
        ObservableList<Emprunt> listData = FXCollections.observableArrayList();
        Emprunt e=new Emprunt();
        sql=new SQLSyntax();
        String isid=new String();
        sql.getIdDoc(query, isid);
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".emprunt where idDocuments like ? order by etat limit " + paginate.getStart() + "," + paginate.getEnd());
            pst.setString(1, "%"+isid+"%");
            rs = pst.executeQuery();
            while (rs.next()) {
                listData.add(new Emprunt(rs.getString(1),
                        rs.getString(5), 
                        rs.getString(4), 
                        sql.getnometu(rs.getString(4),e.nometudiant), 
                        sql.getprenometu(rs.getString(4),e.prenometudiant), 
                        sql.getsexeetu(rs.getString(4),e.sexeetudiant), 
                        sql.getparcoursetu(rs.getString(4),e.parcoursetudiant), 
                        sql.gettitreDoc(rs.getString(5),e.titredocument), 
                        sql.gettypeDoc(rs.getString(5),e.typedocument), 
                        sql.getblobetu(rs.getString(4),e.photo), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getString(6)));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return listData;
    }
    
    
}
