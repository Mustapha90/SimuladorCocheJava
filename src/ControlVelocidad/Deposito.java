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
public class Deposito extends Observable{
    private double nivelActual=100;
    private final double max=100;
    
    
    public Deposito(){
        
    }
    
    public void disminuirCombustible(double valor){
        nivelActual-=valor;
        setChanged();
        notifyObservers();
    }
    
    public void repostar(){
        nivelActual=max;
        setChanged();
        notifyObservers();
    }
    public double getNivelActual(){
        return nivelActual;
    }
}
