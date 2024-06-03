package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelSuperior extends JPanel{

    private JLabel lblImage;

    public PanelSuperior() {
        lblImage = new JLabel("                                                           ");
        
        add(lblImage);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        // Set the font and color for the banner
        Font font = new Font("Arial", Font.BOLD, 30);
        g2d.setFont(font);
        g2d.setColor(Color.decode("#737373"));
        Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, getWidth(), getHeight());

        g2d.fill(rect);
        g2d.setColor(Color.WHITE);
        
        // Calculate the position to center the banner horizontally
        java.awt.FontMetrics fm = g2d.getFontMetrics();
        int stringWidth = fm.stringWidth("Galeria G5");
        int x = (getWidth() - stringWidth) / 2;
        
        // Draw the banner text
        g2d.drawString("Galeria G5", x, getHeight()/2 + 12);
        
        g2d.draw(rect);
    }
    
}
