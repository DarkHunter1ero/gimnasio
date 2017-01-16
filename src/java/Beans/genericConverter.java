/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DataBase.DBGenericClass;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Diego
 */
public class genericConverter<T> {
    protected DBGenericClass helper;
    
    public Converter obtenerConversor() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext context, UIComponent component, String value) {
                // This method is called when HTTP request parameter is to be converted to item value.
                // You need to convert the student ID back to Student.
                
                Object id = (Object)value;
                T elemento = (T) helper.findById(id);
                return elemento;
            }
            
            @Override
            public String getAsString(FacesContext context, UIComponent component, Object value) {
                // This method is called when item value is to be converted to HTTP request parameter.
                // Normal practice is to return an unique identifier here, such as student ID.
                T elemento = (T) value;
                Object o = helper.getIdentifier(elemento);
                String result = String.valueOf(o);
                return result;
            }
        };
    }
}
