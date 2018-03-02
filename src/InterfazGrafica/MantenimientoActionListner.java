package InterfazGrafica;

import ControlVelocidad.ControlVelocidad;
import ControlVelocidad.Deposito;
import ControlVelocidad.Freno;
import ControlVelocidad.Motor;
import static InterfazGrafica.PosicionPalanca.ACELERAR;
import static InterfazGrafica.PosicionPalanca.MANTENER;
import static InterfazGrafica.PosicionPalanca.PARAR;
import static InterfazGrafica.PosicionPalanca.REINICIAR;
import Monitorizacion.AlmacenamientoM;
import Monitorizacion.Monitorizacion;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class MantenimientoActionListner implements ActionListener{

    private boolean selected = false;
    private PanelMantenimiento panelMantenimiento;
    private Deposito deposito;
    private ControlVelocidad controlV;
    private Freno freno;
    private Motor motor;
    private AlmacenamientoM almacenM;
    
    public MantenimientoActionListner(PanelMantenimiento panelM, ControlVelocidad control, AlmacenamientoM almacen){
        panelMantenimiento = panelM; 
        controlV=control;
        deposito = control.getDeposito();
        freno=control.getFreno();
        motor=control.getMotor();
        almacenM=almacen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int action = Integer.parseInt(e.getActionCommand());

        switch(action) {
        case 1:
                almacenM.cambiarEstadoAceite(true);
                break;
        case 2: 
                freno.cambiarPastillasFreno();
                break;
        case 3: 
                almacenM.cambiarEstadoSistema(true);
                break;
        case 4: 
               deposito.repostar();
               break;
        }
    }
}