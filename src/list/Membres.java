/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

import gettersSetters.ListMembres;
import java.sql.Blob;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 *
 * @author Ralande
 */
public class Membres {
    public ObservableList<String> allMembr = FXCollections.observableArrayList();

    public String id;
    public String nom;
    public String prenom;
    public String datenaissance;
    public String numero;
    public String parcours;
    public String imagePath;
    public Blob photo;
    public String sexe;
    public Image image;
    public String identifiant;
    public String mdp;

    public ObservableList<ListMembres> employeeLists = FXCollections.observableArrayList();
}
