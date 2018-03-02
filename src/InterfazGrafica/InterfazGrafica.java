package InterfazGrafica;

import java.awt.Dimension;
import javax.swing.JApplet;
import ControlVelocidad.Almacenamiento;
import ControlVelocidad.ControlVelocidad;
import ControlVelocidad.Palanca;
import ControlVelocidad.Reloj;
import ControlVelocidad.Deposito;
import ControlVelocidad.Freno;
import ControlVelocidad.Motor;
import Monitorizacion.AlmacenamientoM;
import Monitorizacion.Monitorizacion;
import com.sun.j3d.utils.applet.MainFrame;

public class InterfazGrafica extends JApplet{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8410630423818225713L;
	AlmacenamientoM almacenM = new AlmacenamientoM();
    private ControlVelocidad control = new ControlVelocidad();
    Reloj reloj=control.getReloj();
    Palanca palanca=control.getPalanca();
    Deposito observableDeposito=control.getDeposito();
    private PanelMantenimiento panelMantenimiento = new PanelMantenimiento(control, almacenM);
    private PanelVelocimetro panelVelocimetro = new PanelVelocimetro(control.getAlmacenamiento(), control.getMotor());
    private Monitorizacion monit = new Monitorizacion(panelMantenimiento, control, almacenM);
    private PanelVelocidadAutomatica panelVelocidadAutomatica= new PanelVelocidadAutomatica(control.getAlmacenamiento());
    private PanelPalanca panelPalanca = new PanelPalanca(panelMantenimiento, panelVelocidadAutomatica, panelVelocimetro, control);
    BotonArrancarActionListner botonArrancarActionListner= panelPalanca.getBotonArrancar();
    Almacenamiento observableAlmacenamiento=control.getAlmacenamiento();
    Freno freno = control.getFreno();
    Motor motor = control.getMotor();
    
    public InterfazGrafica(){
        observableAlmacenamiento.addObserver(panelVelocimetro);
        observableAlmacenamiento.addObserver(panelVelocidadAutomatica);
        observableDeposito.addObserver(monit);
        palanca.addObserver(panelPalanca);
        motor.addObserver(panelPalanca);
        freno.addObserver(monit);
        almacenM.addObserver(monit);
    }

    @Override
    public void init(){
        resize(600, 590);
        setLayout(null);
        add(this.panelVelocimetro);
        Dimension d = this.panelVelocimetro.getPreferredSize();
        this.panelVelocimetro.setBounds(0, 0, d.width, d.height);
        d = this.panelVelocidadAutomatica.getPreferredSize();
        this.panelVelocidadAutomatica.setBounds(315, 0, d.width, d.height);
        add(this.panelVelocidadAutomatica);
        this.panelMantenimiento.setBounds(0, 320, 360, 270);
        add(this.panelMantenimiento);
        this.panelPalanca.setBounds(350, 320, 260, 310);
        add(this.panelPalanca);
        
        reloj.start();
        validate();
    }

    public static void main( String[] args ) throws InterruptedException {
        InterfazGrafica prueba = new InterfazGrafica();  
        //prueba.init();
        MainFrame mainFrame; 
        mainFrame = new MainFrame(prueba,600,560);
    }
    
}
