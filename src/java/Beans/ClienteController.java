
package Beans;

import Clases.Actividad;
import Clases.Cliente;
import Clases.ClienteHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Named(value = "clienteController")
@SessionScoped
public class ClienteController extends genericConverter<Cliente> implements Serializable{

    ClienteHelper helper;

    private Cliente selected = new Cliente();
    private List<Cliente> items = new ArrayList<>();
    
    public ClienteController() {
         helper = new ClienteHelper();
         super.helper=helper;
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
    
    public void modificarCliente(){
        helper.update(selected);
    }
    
    public void clearController(){
        selected = new Cliente();
    }
    
    public void delete(Cliente cli){
        helper.delete(cli);
    }
    
    public void prepareToCreate(){
        selected = new Cliente();
    }
    
    public void guardarCambios(){
        if(!verificarExistencia(selected)){
            crearCliente();
        }else{
            modificarCliente();
        }
    }
    
    public boolean verificarExistencia(Cliente cli){
        return helper.exist(cli);
    }
}
