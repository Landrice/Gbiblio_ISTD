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

/**
 *
 * @author Admin
 */
public class CatagoryBLL {
    SQLSyntax sql = new SQLSyntax();
    CatagoryGetway catagoryGetway = new CatagoryGetway();
    bddConnection dbCon = new bddConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    public void save(Catagory catagory){
        catagoryGetway.save(catagory);
    }

    public void update(Catagory catagory){
        catagoryGetway.update(catagory);
    }
    
    public void delete(Catagory catagory){
        catagoryGetway.delete(catagory);
    }

}
