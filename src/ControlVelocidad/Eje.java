package ControlVelocidad;


public class Eje {
    private final double radio = 0.5;
    private double vueltas=0;
    
    public Eje(){
        
    }
    public void incrementarVueltas(double v){
        vueltas += v;
    }
    
    public double getRevoluciones(){
        return vueltas;
    }
    public double getRadio(){
        return radio;
    }
    
    public void restaurar(){
        vueltas = 0;
    }
}
