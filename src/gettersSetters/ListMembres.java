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
public class ListMembres {
    public String membreid;
    public String membrenumero;
    public String mambrenom;

    public ListMembres(String membreid, String membrenumero, String mambrenom) {
        this.membreid = membreid;
        this.membrenumero = membrenumero;
        this.mambrenom = mambrenom;
    }

    public String getMembreid() {
        return membreid;
    }

    public void setMembreid(String membreid) {
        this.membreid = membreid;
    }

    public String getMembrenumero() {
        return membrenumero;
    }

    public void setMembrenumero(String membrenumero) {
        this.membrenumero = membrenumero;
    }

    public String getMambrenom() {
        return mambrenom;
    }

    public void setMambrenom(String mambrenom) {
        this.mambrenom = mambrenom;
    }
    
    
}
