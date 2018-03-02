package InterfazGrafica;

import ControlVelocidad.Freno;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractButton;


public class BotonFrenoMouseAdapter extends MouseAdapter{
  
    private Freno freno;
    public BotonFrenoMouseAdapter(Freno f){
        freno = f;
    }
  
    @Override
    public void mousePressed(MouseEvent event){
        AbstractButton abstractButton = (AbstractButton) event.getSource();
        if(abstractButton.isEnabled()){
            abstractButton.setText("Soltar Freno");
            abstractButton.setBackground(Color.yellow);
            freno.presionarFreno();
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent event){
        AbstractButton abstractButton = (AbstractButton) event.getSource();
        abstractButton.setText(" Pisar Freno ");
        freno.soltarFreno();
        
    }
  
   
}
