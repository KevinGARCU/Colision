package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Dibujar extends JPanel implements ActionListener  {
    
    
    private Personaje Phill;
    private Personaje Golb;
    private Timer timer;
    private String escenario = "Abstraccion.png";
    ImageIcon imag;
    
    public Dibujar(){
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        
        
        imag = new ImageIcon(this.getClass().getResource(escenario));
        
        Phill = new Personaje();
        Phill.Principal();
        
        Golb = new Personaje();
        Golb.malo();
        
        timer = new Timer(5, this);
        timer.start(); 
    }
          
    @Override
    public void paint (Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(imag.getImage(), 0, 0, this);
        g2d.drawImage(Phill.getImagen(), Phill.getX(), Phill.getY(), this);
        g2d.drawImage(Golb.getImagen(), Golb.getX(), Golb.getY(), this);
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Phill.mover();
        Golb.mover();
        colision();
        repaint();
    }
    
    public void colision(){
        if(Phill.getX()>= Golb.getX()-50 && Phill.getX()<= Golb.getX()+50){
            if(Phill.getY()>= Golb.getY()-60 && Phill.getY()<= Golb.getY()+60){
                System.out.println("Se tocaron los personajes");
            }
        }
    }
    
    private class TAdapter extends KeyAdapter{
        
        public void keyReleased (KeyEvent e){
            Phill.keyReleased(e);
            Golb.keyReleased2(e);
        }
        
        public void keyPressed(KeyEvent e){
            Phill.keyPressed(e);
            Golb.keyPressed2(e);
        }
    }
        
}
