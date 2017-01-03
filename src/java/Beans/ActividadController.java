/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Clases.Actividad;
import Clases.ActividadHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named(value = "actividadController")
@SessionScoped
public class ActividadController implements Serializable{

    ActividadHelper helper;

    private Actividad selected = new Actividad();
    private List<Actividad> items = new ArrayList<>();
    
    public ActividadController() {
         helper = new ActividadHelper();
    }
    
    public void crearActividad(){
        helper.create(selected);
        selected= new Actividad();
    }
    
    public Actividad getSelected(){
        if(selected == null){
            selected = new Actividad();
        }
        return selected;
    }
    
    public void setSelected(Actividad selected){
        this.selected=selected;
    }
    
    public List<Actividad> getItems(){
        loadItems();
        return this.items;
    }

    public void setItems(List<Actividad> items) {
        this.items = items;
    }
    
    public void loadItems(){
        this.items=helper.findAll();
    }
    
    public Actividad getActividad(int id){
        return helper.findById(id);
    }
}
