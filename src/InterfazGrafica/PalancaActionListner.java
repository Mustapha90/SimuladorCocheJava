package InterfazGrafica;

import ControlVelocidad.Palanca;
import static InterfazGrafica.PosicionPalanca.ACELERAR;
import static InterfazGrafica.PosicionPalanca.MANTENER;
import static InterfazGrafica.PosicionPalanca.PARAR;
import static InterfazGrafica.PosicionPalanca.REINICIAR;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class PalancaActionListner implements ActionListener{

    private boolean selected = false;
    private PanelPalanca panelPalanca;
    private PanelMantenimiento panelM;
    private Palanca palanca;
    private PosicionPalanca posicionAnterior=PARAR;
    private boolean reiniciar=false;
    PanelVelocidadAutomatica pVelAuto;
    
    public PalancaActionListner(PanelPalanca panelP, PanelVelocidadAutomatica pVelA){
        panelPalanca = panelP;
        palanca = panelP.getPalanca();
        pVelAuto = pVelA;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int action = Integer.parseInt(e.getActionCommand());

        switch(action) {
        case 1:
                palanca.cambiarPosicionPalanca(ACELERAR);
                pVelAuto.setEstado("Acelerando");
                panelPalanca.desactivarBotonArrancar();
                panelPalanca.desactivarBoton(PARAR);
                panelPalanca.activarBoton(MANTENER);
                pVelAuto.repaint();
                break;
        case 2: 
                palanca.cambiarPosicionPalanca(MANTENER);
                pVelAuto.setEstado("Menteniendo");
                panelPalanca.desactivarBoton(REINICIAR);
                panelPalanca.desactivarBotonArrancar();
                reiniciar = true;
                panelPalanca.activarBoton(PARAR);
                pVelAuto.repaint();
                break;
        case 3: 
                                palanca.cambiarPosicionPalanca(REINICIAR);
                pVelAuto.setEstado("Reiniciando");
                panelPalanca.activarBoton(PARAR);
                panelPalanca.desactivarBoton(MANTENER);
                panelPalanca.desactivarBotonArrancar();
                pVelAuto.repaint();
                break;
                
        case 4: 
                palanca.cambiarPosicionPalanca(PARAR);
                pVelAuto.setEstado("Apagado");
                panelPalanca.desactivarBoton(MANTENER);
                panelPalanca.activarBotonArrancar();
                if(reiniciar==true)
                    panelPalanca.activarBoton(REINICIAR);
                pVelAuto.repaint();
                
                break;
        }
    }
        

    

    

}