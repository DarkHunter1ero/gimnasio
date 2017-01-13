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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

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
    
    public void modificar(){
        helper.update(selected);
        selected = new Actividad();
    }
    
    public void clearController(){
        selected = new Actividad();
    }
    
    public void delete(Actividad act){
        helper.delete(act);
    }
    
    public void prepareToCreate(){
        selected=new Actividad();
    }
    
//    public Converter obtenerConversor() {
//        return new Converter() {
//            @Override
//            public Object getAsObject(FacesContext context, UIComponent component, String value) {
//                // This method is called when HTTP request parameter is to be converted to item value.
//                // You need to convert the student ID back to Student.
//                int id = Integer.valueOf(value);
//                Actividad actividad = helper.findById(id);
//                return actividad;
//            }
//            
//            @Override
//            public String getAsString(FacesContext context, UIComponent component, Object value) {
//                // This method is called when item value is to be converted to HTTP request parameter.
//                // Normal practice is to return an unique identifier here, such as student ID.
//                Actividad actividad = (Actividad) value;
//                int id = actividad.getId();
//                return String.valueOf(id);
//            }
//        };
//    }
}
