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
public class Catagory {
    public String id;
    public String catagoryName;
    public String creatorId;
    public String creatorName;
    public String date;

    public ObservableList<ListCatagory> catagoryDetails = FXCollections.observableArrayList();
}
