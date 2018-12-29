/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getway;

import bdd.BddPropreties;
import bdd.bddConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ralande
 */
public class AuthGetway {

    bddConnection dbCon = new bddConnection();
    public Connection con;
    public ResultSet rs;
    public PreparedStatement pst;

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    public int totalUser() {
        int total = 0;
        con = dbCon.geConnection(); 
        try {
            pst = con.prepareStatement("SELECT count(*) FROM " + db + ".utilisateur");
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
            rs.close();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(AuthGetway.class.getName()).log(Level.SEVERE, null, ex);
        }

        return total;
    }

    public int totalMembres() {
        int total = 0;
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select count(*) from " + db + ".membres");
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
            rs.close();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(AuthGetway.class.getName()).log(Level.SEVERE, null, ex);
        }

        return total;
    }

    public int totalDoc() {
        int total = 0;
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select count(*) from " + db + ".document");
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
            rs.close();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(AuthGetway.class.getName()).log(Level.SEVERE, null, ex);
        }

        return total;
    }

    public int totalEmprunt() {
        int total = 0;
        String vrai = "true";
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select count(*) from " + db + ".emprunt where etat='" + vrai + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
            rs.close();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(AuthGetway.class.getName()).log(Level.SEVERE, null, ex);
        }

        return total;
    }

}
