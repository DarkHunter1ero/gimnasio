/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Beans;

import Clases.ActividadHelper;
import Clases.Cliente;
import Clases.ClienteHelper;
import Clases.CobroHelper;
import Clases.Cobro;
import Clases.CobroActividad;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named(value = "cobroController")
@SessionScoped
public class CobroController implements Serializable{
    
    CobroHelper helper;
    ActividadHelper actividadHelper;
    ClienteHelper clienteHelper;
    
    private Cobro selected = new Cobro();
    private List<Cobro> items = new ArrayList<>();
    
    public CobroController() {
        helper = new CobroHelper();
        actividadHelper = new ActividadHelper();
        clienteHelper =  new ClienteHelper();
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
        return this.items;
    }
    
    public void setItems(List<Cobro> items) {
        this.items = items;
    }
    
    public void loadAllItems(){
        this.items=helper.findAll();
    }
    
    public void loadItemsCliente(){
        this.items=helper.findByClienteInitAll(cliente.getCI());
    }
    
    public List<Cobro> getItemsWithActividades(){
        this.items=helper.findAllInitAll();
        return items;
    }
    
    public Cobro getCobro(int id){
        return helper.findById(id);
    }
    
    public void guardarCobro(){
        Cobro cobro = getSelected();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        cobro.setFecha(dateFormat.format(date));
        cobro.setCobroActividad(itemsCobroActividad);
        cobro.setCliente(cliente);
        helper.createWithRelations(cobro);
        clearController();
        loadItemsCliente();
    }
    
    public void clearController(){
        selected = new Cobro();
        selectedCobroActividad = new CobroActividad();
        itemsCobroActividad = new ArrayList<>();
    }
    
    private CobroActividad selectedCobroActividad = new CobroActividad();
    private List<CobroActividad> itemsCobroActividad = new ArrayList<>();
    
    public CobroActividad getSelectedCobroActividad() {
        if(selectedCobroActividad == null){
            selectedCobroActividad = new CobroActividad();
        }
        return selectedCobroActividad;
    }
    
    public void setSelectedCobroActividad(CobroActividad selectedCobroActividad) {
        this.selectedCobroActividad = selectedCobroActividad;
    }
    
    public List<CobroActividad> getItemsCobroActividad() {
        return itemsCobroActividad;
    }
    
    public void setItemsCobroActividad(List<CobroActividad> itemsCobroActividad) {
        this.itemsCobroActividad = itemsCobroActividad;
    }
    
    public void addCobroActividad(){
        itemsCobroActividad.add(selectedCobroActividad);
        calcularImporteTotal();
        calcularImporteTotalConDescuento();
        selectedCobroActividad = new CobroActividad();
    }
    
    public void calcularImporteTotal(){
        float resultado = 0;
        for(CobroActividad cobroActividad : itemsCobroActividad){
            resultado += cobroActividad.getCantidad() * cobroActividad.getActividad().getPrecio();
        }
        selected.setImporte(resultado);
    }
    
    public void calcularImporteTotalConDescuento(){
        selected.setImporteFinal(selected.getImporte()-selected.getDescuento());
    }
    
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void cobrarACliente(Cliente cliente) {
        this.cliente = cliente;
        loadItemsCliente();
    }
}
