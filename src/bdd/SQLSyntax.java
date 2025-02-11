/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;

/**
 *
 * @author Ralande
 */
public class SQLSyntax {
    
    bddConnection dbCon = new bddConnection();
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    
     public void basicPermission(String usrName){
        bddConnection dbc = new bddConnection();
        con = dbc.geConnection();

        try {
            pst = con.prepareStatement("Select Id FROM "+db+".utilisateur where nom=?");
            pst.setString(1, usrName);
            rs = pst.executeQuery();
            while (rs.next()) {
                pst = con.prepareStatement("insert into "+db+".droitutilisateur values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, null);
                pst.setInt(2, 1);
                pst.setInt(3, 1);
                pst.setInt(4, 1);
                pst.setInt(5, 1);
                pst.setInt(6, 1);
                pst.setInt(7, 1);
                pst.setInt(8, 1);
                pst.setInt(9, 1);
                pst.setInt(10, 1);
                pst.setInt(11, 1);
                pst.setInt(12, 1);
                pst.setInt(13, rs.getInt("Id"));

                pst.executeUpdate();
            }con.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(SQLSyntax.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public void registration(String id,String userName,String fullName,
            String emailAddress,String contactNumber,
            String address,String password,
            String date,String imagePath ){
        con = dbCon.geConnection();
        
        try {
            pst = con.prepareStatement("insert into "+db+".utilisateur values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, userName);
            pst.setString(3, fullName);
            pst.setString(4, emailAddress);
            pst.setString(5, contactNumber);
            pst.setString(6, address);
            pst.setString(7, password);
            if(imagePath!=null){
                System.out.println("I am hear");
                InputStream is;
                try {
                    is = new FileInputStream(new File(imagePath));
                    pst.setBlob(8, is);
                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                }
               
            }else{
                pst.setBlob(8, (Blob) null);
            }
            
            pst.setString(9, id);
            
            pst.executeUpdate();
            pst.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQLSyntax.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void userPermissionUpdate(int id){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement(null);
        } catch (SQLException ex) {
            Logger.getLogger(SQLSyntax.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void creatorNameFindar( String creatorId, Label creatorName){

        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".utilisateur where Id=?");
            pst.setString(1, creatorId);
            rs = pst.executeQuery();
            while (rs.next()) {
                creatorName.setText(rs.getString(2));
            }con.close();
            pst.close();
            rs.close();

        } catch (SQLException ex) {
           System.out.println(ex);
        }
    }

    public String getName(String id, String name, String tableName){

        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+"."+tableName+" where Id=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()){
                name = rs.getString(2);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print(e);
        }

        return name;
    }
    
        public String getNameR(String id, String name, String tableName){

        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+"."+tableName+" where Id=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()){
                name = rs.getString(3);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print(e);
        }

        return name;
    }

    public String getIdNo( String name,String id, String tableName,String fieldName){

        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+"."+tableName+" where "+fieldName+" =?");
            pst.setString(1, name);
            rs = pst.executeQuery();
            while (rs.next()){
                id = rs.getString(1);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
    
    public String gettitreDoc(String id, String name){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".document where idDocument=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()){
                name = rs.getString(3);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print(e);
        }
        return name;
    }
    
    public String gettypeDoc(String id, String name){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".document where idDocument=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()){
                name = rs.getString(6);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print(e);
        }
        return name;
    }
    public String getnometu(String id, String name){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".membres where numeroEtudiant=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()){
                name = rs.getString(3);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print(e);
        }
        return name;
    }
    
    public String getprenometu(String id, String name){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".membres where numeroEtudiant=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()){
                name = rs.getString(4);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print(e);
        }
        return name;
    }
    
    public String getsexeetu(String id, String name){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".membres where numeroEtudiant=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()){
                name = rs.getString(5);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print(e);
        }
        return name;
    }
    public String getparcoursetu(String id, String name){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".membres where numeroEtudiant=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()){
                name = rs.getString(6);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print(e);
        }
        return name;
    }
    
     public Blob getblobetu(String id, Blob name){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".membres where numeroEtudiant=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()){
                name = rs.getBlob(6);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print(e);
        }
        return name;
    }
     
     public String getIdDoc(String titre, String name){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".document where titre=?");
            pst.setString(1, titre);
            rs = pst.executeQuery();
            while (rs.next()){
                name = rs.getString(3);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print(e);
        }
        return name;
    }
}
