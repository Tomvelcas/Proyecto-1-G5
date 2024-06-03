package interfaz;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;



public class PanelDerecha extends JPanel{

    private JLabel lblStart;

    public PanelDerecha() {
        lblStart = new JLabel("Bienvenido a la Galeria");
        setBackground(Color.decode("#DBCDA4"));
        lblStart.setPreferredSize(new Dimension(200, 100));
        add(lblStart);
    }
}
