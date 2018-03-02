
package InterfazGrafica;

import ControlVelocidad.ControlVelocidad;
import ControlVelocidad.Deposito;
import Monitorizacion.AlmacenamientoM;
import Monitorizacion.Monitorizacion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class PanelMantenimiento extends JPanel{
    private JButton cambiarPastillas = new JButton("Cambiar");
    private JButton cambiarAceite = new JButton("Cambiar");
    private JButton revisarSistema = new JButton("Revisar");
    private JButton repostar = new JButton("Repostar");
    private JLabel  estadoAceiteTitulo = new JLabel("Aceite Motor");
    private JLabel  estadoPastillasTitulo = new JLabel("Pastillas Freno");
    private JLabel  estadoSistemaTitulo = new JLabel("Sistema");
    private JLabel  estadoAceite = new JLabel("   OK   ");
    private JLabel  estadoPastillas = new JLabel("   OK   ");
    private JLabel  estadoSistema = new JLabel("   OK   ");
    private JLabel  combustibleLabel = new JLabel("Combustible");
    private JProgressBar barDo = new JProgressBar(0, 100);
    private double nivelDeposito=100;
    private Deposito deposito;
    private ControlVelocidad controlV;
    private AlmacenamientoM almacenM;
      
    
    public PanelMantenimiento(ControlVelocidad control, AlmacenamientoM almacen){
         controlV=control;
         deposito = control.getDeposito();
         almacenM=almacen;
         initComponents();
    }
    
    private void initComponents(){
      setLayout(null);
      setBackground(Color.BLACK);
      Insets insets = this.getInsets();     
      
      // botones
      add(cambiarPastillas);
      add(cambiarAceite);
      add(revisarSistema);
      add(repostar);
      
      //Labels
      add(estadoPastillasTitulo);
      add(estadoAceiteTitulo);
      add(estadoSistemaTitulo);
      add(estadoPastillas);
      add(estadoAceite);
      add(estadoSistema);
      add(combustibleLabel);
      
      estadoAceiteTitulo.setOpaque(true);
      estadoAceite.setOpaque(true);
      estadoPastillasTitulo.setOpaque(true);
      estadoPastillas.setOpaque(true);
      estadoSistemaTitulo.setOpaque(true);
      estadoSistema.setOpaque(true);
      combustibleLabel.setOpaque(true);
      
      estadoPastillas.setBackground(Color.green);
      estadoAceite.setBackground(Color.green);
      estadoSistema.setBackground(Color.green);
      
      Dimension size = estadoAceiteTitulo.getPreferredSize();
      estadoAceiteTitulo.setBounds(insets.left + 10, insets.top + 10, size.width, size.height);
      
      size = estadoPastillasTitulo.getPreferredSize();
      estadoPastillasTitulo.setBounds(insets.left + 130, insets.top + 10, size.width, size.height);
      
      size = estadoSistemaTitulo.getPreferredSize();
      estadoSistemaTitulo.setBounds(insets.left + 270, insets.top + 10, size.width, size.height);
      
      size = estadoAceite.getPreferredSize();
      estadoAceite.setBounds(insets.left + 30, insets.top + 40, size.width, size.height);
      
      size = estadoPastillas.getPreferredSize();
      estadoPastillas.setBounds(insets.left + 160, insets.top + 40, size.width, size.height);
      
      size = estadoSistema.getPreferredSize();
      estadoSistema.setBounds(insets.left + 280, insets.top + 40, size.width, size.height);
      
      
      size = cambiarAceite.getPreferredSize();
      cambiarAceite.setBounds(insets.left + 10, insets.top + 70, size.width, size.height);
      
      size = repostar.getPreferredSize();
      repostar.setBounds(insets.left + 10, insets.top + 200, size.width, size.height);
      
      size = cambiarPastillas.getPreferredSize();
      cambiarPastillas.setBounds(insets.left + 130, insets.top + 70, size.width, size.height);
      
      size = revisarSistema.getPreferredSize();
      revisarSistema.setBounds(insets.left + 260, insets.top + 70, size.width, size.height);
      
      size = combustibleLabel.getPreferredSize();
      combustibleLabel.setBounds(insets.left + 15, insets.top + 125, size.width, size.height);
      
      ActionListener mantActionListner = new MantenimientoActionListner(this, controlV, almacenM);
      cambiarAceite.addActionListener(mantActionListner);
      cambiarPastillas.addActionListener(mantActionListner);
      revisarSistema.addActionListener(mantActionListner);
      repostar.addActionListener(mantActionListner);
        
      cambiarAceite.setActionCommand("1");
      cambiarPastillas.setActionCommand("2");
      revisarSistema.setActionCommand("3");
      repostar.setActionCommand("4");
        
      
      add(barDo);
      barDo.setBounds(10, 150, 180, 40);
      barDo.setValue((int) nivelDeposito);
      barDo.setForeground(Color.red);
      this.desactivarEtiquetas();
    }

    
    
    public void desactivarEtiquetas(){
        barDo.setVisible(false);
        estadoAceite.setVisible(false);
        estadoSistema.setVisible(false);
        estadoPastillas.setVisible(false);  
    }
    
    public void activarEtiquetas(){
        barDo.setVisible(true);
        estadoAceite.setVisible(true);
        estadoSistema.setVisible(true);
        estadoPastillas.setVisible(true);  
    }
    
    public void updateNivelCombustible(double nivelActual) {
        this.nivelDeposito = (int)nivelActual;
        barDo.setValue((int) nivelDeposito);
    }
    
    
    public void updateEstadoPastillasFreno(String s){
        estadoPastillas.setText(s);
        if(s=="   !   ")
            estadoPastillas.setBackground(Color.red);
        else
            estadoPastillas.setBackground(Color.green);
        
    }

    public void updateEstadoAceiteMotor(String s) {
        estadoAceite.setText(s);
        if(s=="   !   ")
            estadoAceite.setBackground(Color.red);
        else
            estadoAceite.setBackground(Color.green);
    }
    
    public void updateEstadoSistema(String s) {
        estadoSistema.setText(s);
        if(s=="   !   ")
            estadoSistema.setBackground(Color.red);
        else
            estadoSistema.setBackground(Color.green);
    }

}