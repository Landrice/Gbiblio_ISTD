/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gettersSetters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class Auteur {
     public String id;
    public String auteurnom;
    public String auteurprenom;
    public String creatorId;
    public String date;
    public String creatorName;

    public ObservableList<ListAuteur> brandDitails = FXCollections.observableArrayList();

    
}
