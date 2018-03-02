package ControlVelocidad;

import InterfazGrafica.PosicionPalanca;
import static InterfazGrafica.PosicionPalanca.ACELERAR;
import static InterfazGrafica.PosicionPalanca.MANTENER;
import static InterfazGrafica.PosicionPalanca.PARAR;


public class ControlVelocidad {
    private Palanca palanca;
    private Deposito deposito;
    private Reloj reloj;
    private Freno freno;
    private Motor motor;
    private Almacenamiento al;
    private CalculadorVelocidad calcV;
    private Eje eje;
    private PosicionPalanca posPalanca = PARAR;
    private int valor = 0;
    private boolean mantenerFirstClick=false;
    private boolean incrementar = true; 
    public ControlVelocidad(){
        reloj = new Reloj(this);
        palanca = new Palanca(this);
        freno = new Freno();
        al = new Almacenamiento();
        eje = new Eje();
        calcV = new CalculadorVelocidad(al,eje);
        deposito = new Deposito();
        motor = new Motor(deposito);
    }
    
    public void controlarVelocidad(){
        double revoluciones = eje.getRevoluciones();
        boolean isPressedFreno=freno.getEstadoFreno();
        
        if(isPressedFreno){
            if(revoluciones-1 < 0){
                eje.restaurar();
                calcV.almacenarVelocidad(calcV.calcularVelocidad());
            }
            else{
                eje.incrementarVueltas(-1D);
                calcV.almacenarVelocidad(calcV.calcularVelocidad());
            }
        }
        else{                    
            if(posPalanca==ACELERAR){
                mantenerFirstClick=false;
                al.unsetVelocidadAuto();
                if(eje.getRevoluciones()< 265D){
                    eje.incrementarVueltas(0.1D);
                    calcV.almacenarVelocidad(calcV.calcularVelocidad());
                }
                else{
                    if(revoluciones==265)
                        eje.incrementarVueltas(0.5);
                    else
                        eje.incrementarVueltas(-0.5);
                }
                deposito.disminuirCombustible(deposito.getNivelActual()/100000);
            }
            else if(posPalanca==MANTENER){
                if(!mantenerFirstClick){
                    calcV.almacenarVelocidadSeleccionada(calcV.calcularVelocidad());
                    mantenerFirstClick = true;
                }
                else{
                    al.enableVelAuto();
                    if(incrementar){
                        eje.incrementarVueltas(0.1D);
                        calcV.almacenarVelocidad(calcV.calcularVelocidad());
                    if(al.getVelocidad()>= al.getVelocidadSeleccionada())
                        incrementar=false;
                    }
                    else{
                        eje.incrementarVueltas(-0.1D);
                        calcV.almacenarVelocidad(calcV.calcularVelocidad());
                    if(al.getVelocidad()<=al.getVelocidadSeleccionada()- 2.5)
                        incrementar=true;                   
                    }
                    
                    deposito.disminuirCombustible(deposito.getNivelActual()/100000);
                }
            }
            else if(posPalanca==PARAR){
                al.unsetVelocidadAuto();
                if(al.getVelocidad()> 0.0D){
                    eje.incrementarVueltas(-0.1D);
                    calcV.almacenarVelocidad(calcV.calcularVelocidad());
                }
            }
            else{
                if(al.getVelocidad() < al.getVelocidadSeleccionada()){
                    eje.incrementarVueltas(0.1D);
                    calcV.almacenarVelocidad(calcV.calcularVelocidad());
                }
                else if(al.getVelocidad() > al.getVelocidadSeleccionada()){
                    eje.incrementarVueltas(-0.1D);
                    calcV.almacenarVelocidad(calcV.calcularVelocidad());
                }
                else
                    palanca.cambiarPosicionPalanca(MANTENER);
                
                deposito.disminuirCombustible(deposito.getNivelActual()/100000);
            }
        }
    }
    
    public void cambiarPalanca(PosicionPalanca p){
        posPalanca = p;
    }
    public Reloj getReloj(){
        return reloj;
    }
    
    public Palanca getPalanca(){
        return palanca;
    }
    
    public Almacenamiento getAlmacenamiento(){
        return al;
    }
    
    public Freno getFreno(){
        return freno;
    }
    public Motor getMotor(){
        return motor;
    }
    
    public Deposito getDeposito(){
        return deposito;
    }
}
