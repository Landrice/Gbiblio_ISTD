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
public class ListAuteur {
    public String id;
    public String auteurnom;
    public String auteurprenom;
    public String creatorId;
    public String date;

    public ListAuteur(String id, String auteurnom, String auteurprenom, String creatorId, String date) {
        this.id = id;
        this.auteurnom = auteurnom;
        this.auteurprenom = auteurprenom;
        this.creatorId = creatorId;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuteurnom() {
        return auteurnom;
    }

    public void setAuteurnom(String auteurnom) {
        this.auteurnom = auteurnom;
    }

    public String getAuteurprenom() {
        return auteurprenom;
    }

    public void setAuteurprenom(String auteurprenom) {
        this.auteurprenom = auteurprenom;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

   
}
