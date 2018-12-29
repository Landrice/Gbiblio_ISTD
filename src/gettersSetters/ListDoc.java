/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gettersSetters;

/**
 *
 * @author Ralande
 */
public class ListDoc {
    public String id;
    public String idDoc;
    public String titre;
    public String nombre;
    public String description;
    public String idauteur;
    public String idedition;
    public String categorieID;
    public String type;
    public String nombrepage;
    public String langue;
    
    public String utilisateur;
    public String date;

    public ListDoc(String id, String idDoc, String titre, String nombre, String description, String auteurID, String editionID, String categorieID, String type, String nombrepage, String langue, String utilisateur, String date) {
        this.id = id;
        this.idDoc = idDoc;
        this.titre = titre;
        this.nombre = nombre;
        this.description = description;
        this.idauteur = auteurID;
        this.idedition = editionID;
        this.categorieID = categorieID;
        this.type = type;
        this.nombrepage = nombrepage;
        this.langue = langue;
        this.utilisateur = utilisateur;
        this.date = date;
    }

    

    public String getNombrepage() {
        return nombrepage;
    }

    public void setNombrepage(String nombrepage) {
        this.nombrepage = nombrepage;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    
    // Accesseurs
    public String getId() {
        return id;
    }

    // Mutateurs
    public void setId(String id) {
        this.id = id;
    }

    public String getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuteurID() {
        return idauteur;
    }

    public void setAuteurID(String auteurID) {
        this.idauteur = auteurID;
    }

    public String getEditionID() {
        return idedition;
    }

    public void setEditionID(String editionID) {
        this.idedition = editionID;
    }

    public String getCategorieID() {
        return categorieID;
    }

    public void setCategorieID(String categorieID) {
        this.categorieID = categorieID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    

}
