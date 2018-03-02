package InterfazGrafica;

import ControlVelocidad.ControlVelocidad;
import ControlVelocidad.Freno;
import ControlVelocidad.Motor;
import ControlVelocidad.Palanca;
import static InterfazGrafica.PosicionPalanca.ACELERAR;
import static InterfazGrafica.PosicionPalanca.MANTENER;
import static InterfazGrafica.PosicionPalanca.PARAR;
import static InterfazGrafica.PosicionPalanca.REINICIAR;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


public class PanelPalanca extends JPanel implements Observer{
    private PanelMantenimiento panelM;
    private PanelVelocidadAutomatica panelV;
    private PanelVelocimetro panelVel;
    private JToggleButton botonArrancar;
    private JButton botonFrenar;
    private ButtonGroup grupoPalanca;
    private JToggleButton botonAcelerar;
    private JToggleButton botonParar;
    private JToggleButton botonReiniciar;
    private JToggleButton botonMantener;
    private PosicionPalanca posicionPalanca = PARAR;
    private int posXpalanca=252;
    private int posYpalanca=157;
    private Rectangle2D rectVertical = new Rectangle2D.Double((getWidth()/2)+130, 125D, 30.0D, 95D);
    private Rectangle2D rectHorizontal = new Rectangle2D.Double((getWidth()/2)+100, 160D,  50D, 23.0D);
    private Palanca palanca;
    private Freno freno;
    private ControlVelocidad controlVelocidad;
    private Motor motor;
    private boolean motorEncendido;
    ActionListener actionListener;
    
    public PanelPalanca(PanelMantenimiento panelMant, PanelVelocidadAutomatica pV, PanelVelocimetro pVel, ControlVelocidad controlV){
         panelM = panelMant;
         panelV = pV;
         panelVel = pVel;
         controlVelocidad=controlV;
         palanca = controlVelocidad.getPalanca();
         freno = controlVelocidad.getFreno();
         motor = controlVelocidad.getMotor();
         initComponents();        
    }
    
    private void initComponents(){
        setLayout(null);
        setSize(200, 280);
        botonArrancar = new JToggleButton();
        botonArrancar.setText("Arrancar");
        actionListener = new BotonArrancarActionListner(this, panelM, panelV, panelVel, motor);
        botonArrancar.addActionListener(actionListener); 
        add(botonArrancar);
        Insets insets = this.getInsets();
        Dimension size = botonArrancar.getPreferredSize();
        botonArrancar.setBounds(getWidth()/2 + insets.left, 10 + insets.top, size.width, size.height);
        
        botonFrenar = new JButton(" Pisar Freno ");
        botonFrenar.setContentAreaFilled(false);
        botonFrenar.setOpaque(true);
        botonFrenar.setBackground(Color.yellow);
        BotonFrenoMouseAdapter mouseAdapter = new BotonFrenoMouseAdapter(freno);
        botonFrenar.addMouseListener(mouseAdapter);
        add(botonFrenar);
        insets = this.getInsets();
        size = botonFrenar.getPreferredSize();
        botonFrenar.setBounds((getWidth()/2) - 10 + insets.left, 50 + insets.top, size.width, size.height);
        
        grupoPalanca = new javax.swing.ButtonGroup();
        botonAcelerar = new JToggleButton("Acelerar");
        
        botonParar = new JToggleButton("Parar");
        botonMantener = new JToggleButton("Mantener");
        botonReiniciar = new JToggleButton("Reiniciar");
        
        
        grupoPalanca.add(botonAcelerar);
        grupoPalanca.add(botonParar);
        grupoPalanca.add(botonMantener);
        grupoPalanca.add(botonReiniciar);
        
        add(botonAcelerar);
        add(botonParar);
        add(botonReiniciar);
        add(botonMantener);
        
        ActionListener palancaActionListner = new PalancaActionListner(this, panelV);
        botonAcelerar.addActionListener(palancaActionListner);
        botonMantener.addActionListener(palancaActionListner);
        botonReiniciar.addActionListener(palancaActionListner);
        botonParar.addActionListener(palancaActionListner);
        
        botonAcelerar.setActionCommand("1");
        botonMantener.setActionCommand("2");
        botonReiniciar.setActionCommand("3");
        botonParar.setActionCommand("4");
        
        add(botonAcelerar);
        add(botonParar);
        add(botonReiniciar);
        add(botonMantener);
        
        insets = this.getInsets();
        size = botonAcelerar.getPreferredSize();
        botonAcelerar.setBounds((getWidth()/2) + 5 + insets.left, 100 + insets.top, size.width, size.height);
        
        insets = this.getInsets();
        size = botonReiniciar.getPreferredSize();
        botonReiniciar.setBounds((getWidth()/2) + 5 + insets.left, 220 + insets.top, size.width, size.height);
        
        insets = this.getInsets();
        size = botonMantener.getPreferredSize();
        botonMantener.setBounds((getWidth()/2) + 60 + insets.left, 160 + insets.top, size.width, size.height);
        
        insets = this.getInsets();
        size = botonParar.getPreferredSize();
        botonParar.setBounds((getWidth()/2) - 70 + insets.left, 160 + insets.top, size.width, size.height);
        
        this.desactivarBotones();
       
        
        
        
    }
     public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        Graphics2D g2 = (Graphics2D)g;
        
        if(posicionPalanca==PARAR){
            posXpalanca=(getWidth()/2)-28;
            posYpalanca=157;
        }
        else if(posicionPalanca==ACELERAR){
            posXpalanca=getWidth()/2;
            posYpalanca=125;
        }
        else if(posicionPalanca==REINICIAR){
            posXpalanca=getWidth()/2;
            posYpalanca=190;
        }
        else{
            posXpalanca=getWidth()/2;
            posYpalanca=157;
        }
            
            g2.setPaint(Color.white);
            g2.fill(this.rectVertical);
            g2.fill(this.rectHorizontal);
            g2.setColor(Color.BLACK);
            g2.fillOval(posXpalanca,posYpalanca,30, 30);
    }
     
     public void setPosicionPalanca(PosicionPalanca posP){
         posicionPalanca = posP;
         repaint();
     }
     
     public void desactivarBotones(){
         botonAcelerar.setEnabled(false);
         botonMantener.setEnabled(false);
         grupoPalanca.clearSelection();
         botonParar.setEnabled(false);
         botonReiniciar.setEnabled(false);
         botonFrenar.setEnabled(false);
     }
     public void desactivarBoton(PosicionPalanca pos){
         if(pos==MANTENER)
            botonMantener.setEnabled(false);
         else if(pos==REINICIAR)
            botonReiniciar.setEnabled(false);
         else if(pos==PARAR)
             botonParar.setEnabled(false);
     }
     public void desactivarBotonArrancar(){
         botonArrancar.setEnabled(false);
     }
     public void activarBotonArrancar(){
         botonArrancar.setEnabled(true);
     }
     public void activarBoton(PosicionPalanca pos){
         if(pos==MANTENER)
            botonMantener.setEnabled(true);
         else if(pos==REINICIAR)
            botonReiniciar.setEnabled(true);
         else if(pos==PARAR)
             botonParar.setEnabled(true);
     }
     
     public void activarBotones(){
         botonAcelerar.setEnabled(true);
         botonMantener.setEnabled(true);
         botonParar.setEnabled(true);
         botonReiniciar.setEnabled(true);
         botonFrenar.setEnabled(true);
     }
    
     public BotonArrancarActionListner getBotonArrancar(){
         return (BotonArrancarActionListner) actionListener;
     }
     
    public Palanca getPalanca(){
        return palanca;
    }
    
    public void apagar(){
        this.desactivarBotones();
        this.setPosicionPalanca(PARAR);
        botonArrancar.setText("Arrancar");
        botonArrancar.setSelected(false);
        panelM.desactivarEtiquetas();
        panelV.setEstado("              ");
        panelV.repaint();
        panelV.setVelocidadAuto("     ");
        panelVel.setVelocidadDigital("     ");
        panelVel.repaint();
        panelV.repaint();
        panelM.repaint();
    }
    public void encender(){
        botonArrancar.setText(" Apagar ");
        this.activarBotones();
        panelM.activarEtiquetas();
        panelV.setEstado("Apagado");
        panelVel.setVelocidadDigital("0,0");
        this.desactivarBoton(MANTENER);
        this.desactivarBoton(REINICIAR);
        panelVel.repaint();
        panelV.repaint();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(o == palanca){
            posicionPalanca = palanca.getPosicionPalanca();
            this.repaint();
        }
        else if(o==motor && ((Integer) arg).intValue()==1){
            if(!motor.leerEstado())
                this.apagar();
            else
                this.encender();
        }
    }
    
}
