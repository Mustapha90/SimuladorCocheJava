/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVelocidad;

/**
 *
 * @author mustapha
 */
public class Reloj extends Thread{
    private final int intervalo = 30;
    ControlVelocidad controlV;
    
    public int getPeriodo(){
        return intervalo;
    }
    
    
    public Reloj(ControlVelocidad c){
        controlV = c;
    }
    
    @Override
    public void run()
  {
    while(true){
    try{ 
        sleep(intervalo);
    
    }catch(java.lang.InterruptedException e){
        e.printStackTrace();
    }
    
        
        controlV.controlarVelocidad();
    
    }
  }

    
}
