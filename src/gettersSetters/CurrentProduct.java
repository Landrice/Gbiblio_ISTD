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
public class CurrentProduct {           
    public String id;
    public String idDoc;
    public String titre;
    public String nombre;
    public String description;
    public String type;
    public String nombrepage;
    public String langue;
    public String idediteur;
    public String idcategorie;
    public String idauteur;
    //public String idedition;
    public String prix;
    public String idutilisateur;
    public String date;
    
    public String garantie;
    public String auteurNom;
    public String editionNom;
    public String categorieNom;
    public String nom;
    public String fournisseurList;
    
    
    
    public ObservableList<ListDoc> currentDoctList = FXCollections.observableArrayList();
}
