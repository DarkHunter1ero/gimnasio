/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Clases.Actividad;
import Clases.ActividadHelper;
import DataBase.DBGenericClass;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Named(value = "actividadController")
@SessionScoped
public class ActividadController extends genericConverter<Actividad> implements Serializable{

    ActividadHelper helper;

    private Actividad selected = new Actividad();
    private List<Actividad> items = new ArrayList<>();

//    public ActividadController(ActividadHelper helper, DBGenericClass helper) {
//        super(helper);
//        this.helper = helper;
//    }
    
    public ActividadController() {
         helper = new ActividadHelper();
         super.helper = helper;
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
    
    public void modificar(){
        helper.update(selected);
        selected = new Actividad();
    }
    
    public void guardarCambios(){
        if(selected.getId()==0){
            crearActividad();
        }else{
            modificar();
        }
    }
    
    public void clearController(){
        selected = new Actividad();
    }
    
    public void delete(Actividad act){
        act.setActivo(false);
        helper.update(act);
    }
    
    public void prepareToCreate(){
        selected=new Actividad();
    }
}
