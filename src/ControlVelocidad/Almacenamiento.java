/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVelocidad;
import java.util.Observable;
/**
 *
 * @author mustapha
 */
public class Almacenamiento extends Observable{
    private double velocidad=0;
    private double velocidadDigital=0;
    private double velocidadSeleccionada=0;
    boolean isSetVelocidadAuto=false;
    
    public void setVelocidad(double v){
        velocidad = v;
        velocidadDigital=(v/17.5)*10;
        if(velocidadDigital<0)
            velocidadDigital=0;
        setChanged();
        notifyObservers();  
    }
    
    public void setVelocidadSeleccionada(double v){
        velocidadSeleccionada = v;
        isSetVelocidadAuto = true;
        setChanged();
        this.notifyObservers();
    }
    
    public double getVelocidad(){
        return velocidad;
    }
    
    public double getVelocidadDigital(){
        return velocidadDigital;
    }
    
    public double getVelocidadSeleccionada(){
        return velocidadSeleccionada;
    }

    public void unsetVelocidadAuto(){
        isSetVelocidadAuto=false;
    }
    public void enableVelAuto(){
        isSetVelocidadAuto=true;
        setChanged();
        this.notifyObservers();
    }
    public boolean isSetVelocidadAuto(){
        return isSetVelocidadAuto;
    }
}
