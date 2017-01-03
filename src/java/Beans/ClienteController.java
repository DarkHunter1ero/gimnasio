/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Clases.Cliente;
import Clases.ClienteHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named(value = "clienteController")
@SessionScoped
public class ClienteController implements Serializable{

    ClienteHelper helper;

    private Cliente selected = new Cliente();
    private List<Cliente> items = new ArrayList<>();
    
    public ClienteController() {
         helper = new ClienteHelper();
    }
    
    public void crearCliente(){
        helper.create(selected);
        selected= new Cliente();
    }
    
    public Cliente getSelected(){
        if(selected == null){
            selected = new Cliente();
        }
        return selected;
    }
    
    public void setSelected(Cliente selected){
        this.selected=selected;
    }
    
    public List<Cliente> getItems(){
        loadItems();
        return this.items;
    }

    public void setItems(List<Cliente> items) {
        this.items = items;
    }
    
    public void loadItems(){
        this.items=helper.findAll();
    }
    
    public Cliente getCliente(int id){
        return helper.findById(id);
    }
    
    public String modificarCliente(){
        helper.update(selected);
        return "gestionarCliente.xhtml?faces-redirect=true"; 
    }
    
    public String curdButton(String id, String action){
        for(Cliente item : items){
            if(item.getCI().equals(id))
                this.selected=item;
        }
        switch(action){
            case "detalle": 
                    return "detalleCliente.xhtml?faces-redirect=true"; 
            case "modificar":
                return "modificarCliente.xhtml?faces-redirect=true"; 
            case "borrar":
                break;
        }
        return "detalleCliente.xhtml?faces-redirect=true"; 
    }
}
