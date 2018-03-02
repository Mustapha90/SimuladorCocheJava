/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitorizacion;

/**
 *
 * @author mustapha
 */
public class RelojM extends Thread{
    private AlmacenamientoM almacenM;
    
    public RelojM(AlmacenamientoM alM){
        almacenM=alM;
    }
    
    @Override
    public void run(){
        while(true){
            try{ 
                Thread.sleep(10 * 60 * 1000);
                almacenM.cambiarEstadoAceite(false);
                Thread.sleep(10 * 60 * 1000);
                almacenM.cambiarEstadoSistema(false);
            }catch(java.lang.InterruptedException e){
            e.printStackTrace();
            }
    }
  }
    
    
}
