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
public class EditeurBLL {
    EditeurGetway supplyerGetway = new EditeurGetway();

    public void save(Editeur supplyer){
        supplyerGetway.save(supplyer);
    }
    
    public Object delete(Editeur supplyer){
        
            supplyerGetway.deleteParmanently(supplyer);
        
        return supplyer;
    }
}
