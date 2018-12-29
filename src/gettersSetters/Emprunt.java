/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gettersSetters;

import java.sql.Blob;
import javafx.scene.image.Image;

/**
 *
 * @author Ralande
 */
public class Emprunt {
    public String id;
    public String numerodocument;    
    public String numeroetudiant;
    public String nometudiant;
    public String prenometudiant;
    public String sexeetudiant;
    public String parcoursetudiant;
    public String titredocument;
    public String typedocument;
    public Blob photo;
    public Image image;
    public String dateemprunt;
    public String dateretour;
    public String etatemprunt;

    public Emprunt() {
    }
    
    

    public Emprunt(String id, String numerodocument, String numeroetudiant, String dateemprunt, String dateretour, String etatemprunt) {
        this.id = id;
        this.numerodocument = numerodocument;
        this.numeroetudiant = numeroetudiant;
        this.dateemprunt = dateemprunt;
        this.dateretour = dateretour;
        this.etatemprunt = etatemprunt;
    }

    public Emprunt(String id, String numerodocument, String numeroetudiant, String nometudiant, String prenometudiant, String sexeetudiant, String parcoursetudiant, String titredocument, String typedocument, Blob photo, String dateemprunt, String dateretour, String etatemprunt) {
        this.id = id;
        this.numerodocument = numerodocument;
        this.numeroetudiant = numeroetudiant;
        this.nometudiant = nometudiant;
        this.prenometudiant = prenometudiant;
        this.sexeetudiant = sexeetudiant;
        this.parcoursetudiant = parcoursetudiant;
        this.titredocument = titredocument;
        this.typedocument = typedocument;
        this.photo = photo;
        this.dateemprunt = dateemprunt;
        this.dateretour = dateretour;
        this.etatemprunt = etatemprunt;
        //this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumerodocument() {
        return numerodocument;
    }

    public void setNumerodocument(String numerodocument) {
        this.numerodocument = numerodocument;
    }

    public String getNumeroetudiant() {
        return numeroetudiant;
    }

    public void setNumeroetudiant(String numeroetudiant) {
        this.numeroetudiant = numeroetudiant;
    }

    public String getNometudiant() {
        return nometudiant;
    }

    public void setNometudiant(String nometudiant) {
        this.nometudiant = nometudiant;
    }

    public String getPrenometudiant() {
        return prenometudiant;
    }

    public void setPrenometudiant(String prenometudiant) {
        this.prenometudiant = prenometudiant;
    }

    public String getSexeetudiant() {
        return sexeetudiant;
    }

    public void setSexeetudiant(String sexeetudiant) {
        this.sexeetudiant = sexeetudiant;
    }

    public String getParcoursetudiant() {
        return parcoursetudiant;
    }

    public void setParcoursetudiant(String parcoursetudiant) {
        this.parcoursetudiant = parcoursetudiant;
    }

    public String getTitredocument() {
        return titredocument;
    }

    public void setTitredocument(String titredocument) {
        this.titredocument = titredocument;
    }

    public String getTypedocument() {
        return typedocument;
    }

    public void setTypedocument(String typedocument) {
        this.typedocument = typedocument;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getDateemprunt() {
        return dateemprunt;
    }

    public void setDateemprunt(String dateemprunt) {
        this.dateemprunt = dateemprunt;
    }

    public String getDateretour() {
        return dateretour;
    }

    public void setDateretour(String dateretour) {
        this.dateretour = dateretour;
    }

    public String getEtatemprunt() {
        return etatemprunt;
    }

    public void setEtatemprunt(String etatemprunt) {
        this.etatemprunt = etatemprunt;
    }
    
    
    
}
