/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "usuarioController")
@SessionScoped
public class usuarioController implements Serializable{
    
    private String usuario;
    private String pass;
    
    public String validarLogin(){
        if(usuario.equals("ignacio") && pass.equals("nacho1993")){
            return "index.xhtml?faces-redirect=true";
        }else{
            return "";
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPass() {
        return pass;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
}
