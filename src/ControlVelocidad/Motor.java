
package ControlVelocidad;

import java.util.Observable;

public class Motor extends Observable{
    private boolean motorEncendido=false;
    Deposito deposito;
    public Motor(Deposito d){
       deposito = d;
    }
    
    public boolean leerEstado(){
        return motorEncendido;
    }
    
    public void encenderMotor(){
        if((int)deposito.getNivelActual()>0){
            motorEncendido = true;
            setChanged();
            notifyObservers(new Integer(1));
        }
    }
    public void apagarMotor(){
        motorEncendido = false;
        setChanged();
        notifyObservers(new Integer(1));
    } 
       
}
