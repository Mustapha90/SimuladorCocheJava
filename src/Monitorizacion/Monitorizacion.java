package Monitorizacion;

import ControlVelocidad.ControlVelocidad;
import ControlVelocidad.Deposito;
import ControlVelocidad.Freno;
import ControlVelocidad.Motor;
import ControlVelocidad.Palanca;
import InterfazGrafica.PanelMantenimiento;
import static InterfazGrafica.PosicionPalanca.PARAR;
import java.util.Observable;
import java.util.Observer;


public class Monitorizacion implements Observer{
    
    private PanelMantenimiento panelMantenimiento;
    AlmacenamientoM almacenM;
    private Deposito deposito;
    private Palanca palanca;
    private ControlVelocidad controlV;
    private Motor motor;
    private Freno freno;
    private boolean estadoAceite=true;
    RelojM relojM;
    
    public Monitorizacion(PanelMantenimiento panelM, ControlVelocidad control, AlmacenamientoM almacen){
        panelMantenimiento = panelM;
        controlV=control;
        palanca = controlV.getPalanca();
        deposito=controlV.getDeposito();
        motor = control.getMotor();
        freno = control.getFreno();
        almacenM = almacen;
        relojM = new RelojM(almacenM);
        relojM.start();
    }


    @Override
    public void update(Observable o, Object arg) {
        if(o == deposito){
            double nivelActual=deposito.getNivelActual();
            panelMantenimiento.updateNivelCombustible(nivelActual);
            if((int)nivelActual<=0){
                palanca.cambiarPosicionPalanca(PARAR);
                motor.apagarMotor();
            }
        }
        else if(o == freno){
            if(!freno.getEstadoPastillasFreno())
                panelMantenimiento.updateEstadoPastillasFreno("   !   ");
            else
                panelMantenimiento.updateEstadoPastillasFreno("   OK   ");
        }
        else{
            if(!almacenM.getEstadoAceiteMotor())
                panelMantenimiento.updateEstadoAceiteMotor("   !   ");
            else
                panelMantenimiento.updateEstadoAceiteMotor("   OK   ");
            
            if(!almacenM.getEstadoSistema())
                panelMantenimiento.updateEstadoSistema("   !   ");
            else
                panelMantenimiento.updateEstadoSistema("   OK   ");
        }
    }
  
    public AlmacenamientoM getAlmacenM(){
        return almacenM;
    }
}
