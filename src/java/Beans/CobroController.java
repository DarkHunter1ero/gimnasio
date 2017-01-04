/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Clases.CobroHelper;
import Clases.Cobro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named(value = "cobroController")
@SessionScoped
public class CobroController implements Serializable{

    CobroHelper helper;

    private Cobro selected = new Cobro();
    private List<Cobro> items = new ArrayList<>();
    
    public CobroController() {
         helper = new CobroHelper();
    }
    
    public void crearCobro(){
        helper.create(selected);
        selected= new Cobro();
    }
    
    public Cobro getSelected(){
        if(selected == null){
            selected = new Cobro();
        }
        return selected;
    }
    
    public void setSelected(Cobro selected){
        this.selected=selected;
    }
    
    public List<Cobro> getItems(){
        loadItems();
        return this.items;
    }

    public void setItems(List<Cobro> items) {
        this.items = items;
    }
    
    public void loadItems(){
        this.items=helper.findAll();
    }
    
    public Cobro getCobro(int id){
        return helper.findById(id);
    }
}
