package InterfazGrafica;

import ControlVelocidad.Motor;
import static InterfazGrafica.PosicionPalanca.MANTENER;
import static InterfazGrafica.PosicionPalanca.PARAR;
import static InterfazGrafica.PosicionPalanca.REINICIAR;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class BotonArrancarActionListner implements ActionListener{

    private boolean selected = false;
    private PanelPalanca panelPalanca;
    private PanelMantenimiento panelM;
    private PanelVelocidadAutomatica panelV;
    private PanelVelocimetro panelVel;
    private Motor motor;
    
    public BotonArrancarActionListner(PanelPalanca panel, PanelMantenimiento panelMant, PanelVelocidadAutomatica pV, PanelVelocimetro pVel, Motor m){
        panelPalanca = panel;
        panelM = panelMant;
        panelV = pV;
        panelVel = pVel;
        motor = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractButton abstractButton = (AbstractButton) e.getSource();
        selected = abstractButton.getModel().isSelected();
        if(selected){
            motor.encenderMotor();
        }
        else{           
            motor.apagarMotor();
        }

    }

}