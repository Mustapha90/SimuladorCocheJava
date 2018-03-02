package ControlVelocidad;

import java.util.Observable;


public class Freno extends Observable{
    private boolean isPressed = false;
    private double pastillasFreno=100;
    private boolean okPastillasFreno=true;
    public Freno(){
        
    }
    
    public void presionarFreno(){
        isPressed = true;
        pastillasFreno-=pastillasFreno/100000;
        if((int)pastillasFreno<=0)
            okPastillasFreno=false;
        setChanged();
        notifyObservers();
    }
    
    public void soltarFreno(){
        isPressed = false;
    }
    
    
    public boolean getEstadoFreno(){
        return isPressed;
    }
    
    public boolean getEstadoPastillasFreno(){
        return okPastillasFreno;
    }
    
    public void cambiarPastillasFreno(){
        pastillasFreno = 100;
        okPastillasFreno=true;
        setChanged();
        notifyObservers();
    }
}
