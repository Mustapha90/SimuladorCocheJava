/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVelocidad;

public class CalculadorVelocidad {
    private Almacenamiento al;
    private Eje eje;
    
    public CalculadorVelocidad(Almacenamiento a, Eje e){
        al = a;
        eje = e;
    }
    
    
    public double calcularVelocidad(){
        return eje.getRevoluciones();
    }
    
    public void almacenarVelocidad(double v){
        al.setVelocidad(v);
    }
    
    public void almacenarVelocidadSeleccionada(double v){
        al.setVelocidadSeleccionada(v);
    }
    public void almacenarDistancia(double d){
        al.setVelocidad(d);
    }
}
