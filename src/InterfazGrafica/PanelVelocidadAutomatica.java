/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import ControlVelocidad.Almacenamiento;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import java.util.Observer;
import java.util.Observable;


public class PanelVelocidadAutomatica extends JPanel implements Observer{
    private String velocidadAuto="    ";
    private String estado="              ";
    private Almacenamiento observableAlmacenamiento = null;
    
    public PanelVelocidadAutomatica(Almacenamiento al){
        observableAlmacenamiento=al;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        setSize(295, 320);
        setBackground(Color.black);

        g2.setFont(new Font("TimesRoman", 0, 20));
        
        
        int posX, posY;
        
        posX=155;
        posY=200;
        FontMetrics fm = g2.getFontMetrics();
        Rectangle r = fm.getStringBounds(velocidadAuto, g2).getBounds();
        g2.drawRect(r.x + posX - 5, r.y + posY, r.width+15, r.height);
        g2.setColor(Color.gray);
        g2.fillRect(r.x + posX - 5, r.y + posY, r.width+15, r.height);
        g2.setColor(Color.white);
        DecimalFormat oneDigit = new DecimalFormat("#,##0.0");
        
        
        g2.drawString("Velocidad Automatica", r.x + posX - 80, posY-30);
        g2.drawString(velocidadAuto, posX, posY);
        
        
        posX=130;
        posY=100;
        
        g2.setFont(new Font("TimesRoman", 0, 22));
        fm = g2.getFontMetrics();
        r = fm.getStringBounds(estado, g2).getBounds();
        g2.drawRect(r.x + posX - 5, r.y + posY, r.width+8, r.height);
        g2.setColor(Color.gray);
        g2.fillRect(r.x + posX - 5, r.y + posY, r.width+8, r.height);
        g2.setColor(Color.white);
        g2.drawString(estado, posX, posY);
   }
    
    public void setEstado(String s){
        estado = s;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public void setVelocidadAuto(String v){
        velocidadAuto = v;
    }

    @Override
    public void update(Observable obs, Object arg) {
        if(obs == observableAlmacenamiento){
            DecimalFormat oneDigit = new DecimalFormat("#,##0.0");
            if(observableAlmacenamiento.isSetVelocidadAuto()){
                velocidadAuto=String.valueOf(oneDigit.format((observableAlmacenamiento.getVelocidadSeleccionada()/17.5)*10));
                this.estado="Manteniendo";
            }
             else
                 velocidadAuto="    ";
                 this.repaint();
        }
    }
}
