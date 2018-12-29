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
public class Editeur {
    public String id;
    public String nom;
    public String localisation;
    public String siteweb;
    public String contact;
    public String creatorId;

//    public List<ListSupplyer> rowSupplyer;
    public ObservableList<ListEditeur> supplyerDetails = FXCollections.observableArrayList();

}
