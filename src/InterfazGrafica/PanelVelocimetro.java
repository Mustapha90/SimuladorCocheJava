package InterfazGrafica;

import ControlVelocidad.Almacenamiento;
import ControlVelocidad.Motor;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.util.Observer;
import java.util.Observable;

public class PanelVelocimetro
  extends JPanel implements Observer
{
  private double velocidad=0;
  private GeneralPath aguja;
  private AffineTransform t;
  BufferedImage velocimetro;
  private Motor motor;
  private Rectangle2D cuadroDistancia;
  private Almacenamiento observableAlmacenamiento = null;
  String velocidadDigital="0,0";
 
  public PanelVelocimetro(Almacenamiento obsAl,  Motor m)
  {
    observableAlmacenamiento = obsAl;
    motor = m;
    this.velocidad = 0.0D;    
    this.aguja = new GeneralPath();
    this.aguja.moveTo(-12.0F, 0.0F);
    this.aguja.lineTo(0.0F, 12.0F);
    this.aguja.lineTo(150.0F, 0.0F);
    this.aguja.lineTo(0.0F, -12.0F);
    this.aguja.lineTo(-12.0F, 0.0F);
    this.cuadroDistancia = new Rectangle2D.Double(297.0D, 310.0D, 68.0D, 25.0D);
    this.t = new AffineTransform();
  }
  
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    setSize(320, 320);
    setBackground(Color.black);
    try {
          velocimetro = ImageIO.read(new File(System.getProperty("user.dir")+"/src/resources/speed.jpg"));
    } catch (IOException ex) {
          Logger.getLogger(PanelVelocimetro.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    int x = (this.getWidth() - velocimetro.getWidth(null)) / 2;
    int y = (this.getHeight() - velocimetro.getHeight(null)) / 2;
    g2.setPaint(Color.white);
    g2.drawImage(velocimetro, x, y, null);
    g.setFont(new Font("Times Roman", 0, 16));
    if(!motor.leerEstado())
        velocidadDigital="     ";
    
    this.render(g2, String.valueOf(velocidadDigital), (getWidth()/2) + 50, (getHeight()/2) + 80);
    g2.translate(getWidth()/2, getHeight()/2);
    this.t.setToRotation(Math.toRadians(88.5D + this.velocidad));
    g2.transform(this.t);
    g2.setColor(new Color(153, 0, 0));
    g2.fill(this.aguja);
    
  }
  private void render(Graphics2D g, String s, int x, int y) {
        g.drawString(s, x, y);
        FontMetrics fm = g.getFontMetrics();
        Rectangle r = fm.getStringBounds(s, g).getBounds();
        g.drawRect(r.x + x - 5, r.y + y, r.width+15, r.height);
        g.drawString("KM", r.x - 5 + x, y-20);
    }
  

    @Override
    public void update(Observable obs, Object arg) {
        if(obs == observableAlmacenamiento){
            velocidad = observableAlmacenamiento.getVelocidad();
            DecimalFormat oneDigit = new DecimalFormat("#,##0.0");
            velocidadDigital = String.valueOf(oneDigit.format(observableAlmacenamiento.getVelocidadDigital()));
            this.repaint();
        }
    }

    public void setVelocidadDigital(String s) {
        velocidadDigital=s;
    }
}
