/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ralande
 */
public class BddPropreties {
    
    Properties properties = new Properties();
    InputStream inputStream;
    OutputStream output = null;

    public void mkDbProperties() {
        String path = System.getProperty("user.home");
   new File(path + "\\Documents\\DBISTD").mkdir();
        
        try {
            output = new FileOutputStream(path +"\\Documents\\DBISTD\\bdd.config");
            properties.setProperty("host", "localhost");
            properties.setProperty("port", "3306");
            properties.setProperty("db", "gbiblio_ist");
            properties.setProperty("user", "root");
            properties.setProperty("password", "");
            properties.store(output, null);
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BddPropreties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BddPropreties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String loadPropertiesFile() {
        String path = System.getProperty("user.home");
   new File(path + "\\Documents\\DBISTD").mkdir();
        try {
            inputStream = new FileInputStream(path +"\\Documents\\DBISTD\\bdd.config");
            properties.load(inputStream);
            return properties.getProperty("db");
        } catch (IOException e) {
            System.out.println("Eto erreur ny fichier: ConfigSConto /"+e);
        }
        return "";
    }
}
