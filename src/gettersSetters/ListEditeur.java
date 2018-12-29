/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gettersSetters;

/**
 *
 * @author Admin
 */
public class ListEditeur {
    
    
    public String editeurid;
    public String editeurnom;
    public String editeurlocalisation;
    public String editeursite;
    public String editeurcontact;
    public String creatorId;
    public String nom;

    public ListEditeur(String supplyerId, String supplyerName) {
        this.editeurid = supplyerId;
        this.editeurnom = supplyerName;
    }

    public void setSupplyerAddress(String supplyerAddress) {
        this.editeursite = supplyerAddress;
    }



    public String getSupplyerPhoneNumber() {
        return editeurlocalisation;
    }

    public String getSupplyerAddress() {
        return editeursite;
    }

    public String getSupplyerDescription() {
        return editeurcontact;
    }

    public ListEditeur(String supplyerId, String supplyerName, String supplyerPhoneNumber, String supplyerAddress, String supplyerDescription,String nom) {
        this.editeurid = supplyerId;
        this.editeurnom = supplyerName;
        this.editeurlocalisation = supplyerPhoneNumber;
        this.editeursite = supplyerAddress;
        this.editeurcontact = supplyerDescription;
        this.nom=nom;
    }

    public String getEditeurid() {
        return editeurid;
    }

    public void setEditeurid(String editeurid) {
        this.editeurid = editeurid;
    }

    public String getEditeurnom() {
        return editeurnom;
    }

    public void setEditeurnom(String editeurnom) {
        this.editeurnom = editeurnom;
    }

    public String getEditeurlocalisation() {
        return editeurlocalisation;
    }

    public void setEditeurlocalisation(String editeurlocalisation) {
        this.editeurlocalisation = editeurlocalisation;
    }

    public String getEditeursite() {
        return editeursite;
    }

    public void setEditeursite(String editeursite) {
        this.editeursite = editeursite;
    }

    public String getEditeurcontact() {
        return editeurcontact;
    }

    public void setEditeurcontact(String editeurcontact) {
        this.editeurcontact = editeurcontact;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    


    public String getSupplyerId() {
        return editeurid;
    }

    public void setSupplyerId(String supplyerId) {
        this.editeurid = supplyerId;
    }

    public String getSupplyerName() {
        return editeurnom;
    }

    public void setSupplyerName(String supplyerName) {
        this.editeurnom = supplyerName;
    }
    
}
