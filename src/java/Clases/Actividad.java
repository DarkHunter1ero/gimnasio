package Clases;
// Generated Jan 4, 2017 12:51:37 AM by Hibernate Tools 4.3.1



/**
 * Actividad generated by hbm2java
 */
public class Actividad  implements java.io.Serializable {


     private int id;
     private String nombre;
     private float precio;
     private String tipo;
     private boolean activo;

    public Actividad() {
    }

    public Actividad(int id, String nombre, float precio, String tipo, boolean activo) {
       this.id = id;
       this.nombre = nombre;
       this.precio = precio;
       this.tipo = tipo;
       this.activo = activo;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public float getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public boolean isActivo() {
        return this.activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Actividad other = (Actividad) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}


