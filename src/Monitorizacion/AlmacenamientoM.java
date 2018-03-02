/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitorizacion;

import java.util.Observable;

/**
 *
 * @author mustapha
 */
public class AlmacenamientoM extends Observable{
    private boolean estadoAceite=true;
    private boolean estadoSistema=true;
    
    
    public void cambiarEstadoAceite(boolean estado){
        estadoAceite=estado;
        setChanged();
        notifyObservers();
    }
    
    public void cambiarEstadoSistema(boolean estado){
        estadoSistema=estado;
        setChanged();
        notifyObservers();
    }
    
    public boolean getEstadoAceiteMotor(){
        return estadoAceite;
    }
    
    public boolean getEstadoSistema(){
        return estadoSistema;
    }
}
