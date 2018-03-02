
package ControlVelocidad;

import InterfazGrafica.PosicionPalanca;
import static InterfazGrafica.PosicionPalanca.PARAR;
import java.util.Observable;


public class Palanca extends Observable{
    PosicionPalanca posicion = PARAR;
    ControlVelocidad controlVelocidad;
    
    public Palanca(ControlVelocidad controlV){
        controlVelocidad = controlV;
    }
    
    public PosicionPalanca getPosicionPalanca(){
        return posicion;
    }
    
    public void cambiarPosicionPalanca(PosicionPalanca p){
        posicion = p;
        controlVelocidad.cambiarPalanca(posicion);
        setChanged();
        notifyObservers();
    }
}
