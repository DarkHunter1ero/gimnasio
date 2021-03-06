package Clases;
// Generated Jan 4, 2017 12:51:37 AM by Hibernate Tools 4.3.1

import java.util.Objects;




/**
 * Cliente generated by hbm2java
 */
public class Cliente  implements java.io.Serializable {


     private String CI;
     private String nombre;
     private String cel;
     private int edad;
     private String sexo;
     private String estado;

    public Cliente() {
    }

	
    public Cliente(String CI) {
        this.CI = CI;
    }
    public Cliente(String CI, String nombre, String cel, int edad, String sexo, String estado) {
       this.CI = CI;
       this.nombre = nombre;
       this.cel = cel;
       this.edad = edad;
       this.sexo = sexo;
       this.estado = estado;
    }
   
    public String getCI() {
        return this.CI;
    }
    
    public void setCI(String CI) {
        this.CI = CI;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCel() {
        return this.cel;
    }
    
    public void setCel(String cel) {
        this.cel = cel;
    }
    public int getEdad() {
        return this.edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getSexo() {
        return this.sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.CI);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.CI, other.CI)) {
            return false;
        }
        return true;
    }

    


}


