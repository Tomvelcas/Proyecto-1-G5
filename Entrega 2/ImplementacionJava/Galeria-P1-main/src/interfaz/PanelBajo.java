package interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class PanelBajo extends JPanel implements ActionListener{
    private JButton btnUs;
    private JButton btnSave;
    private JButton btnLogin;
    private JButton btnVentas;
    
    private VentanaPrincipal principal;
    
    public PanelBajo(VentanaPrincipal ventanaPrincipal) {
        
        principal = ventanaPrincipal;
        UIManager.put("Button.background", Color.decode("#6B4945"));

        setLayout(new GridLayout(1, 3));
        setBackground(Color.decode("#6B4945"));

        btnUs = new JButton("Sobre nosotros");
        btnUs.addActionListener(this);
        btnUs.setActionCommand("US");
        add(btnUs);

        btnVentas = new JButton("Ventas");
        btnVentas.addActionListener(this);
        btnVentas.setActionCommand("VENTAS");
        add(btnVentas);

        btnSave = new JButton("Guardar");
        btnSave.addActionListener(this);
        btnSave.setActionCommand("SAVE");
        add(btnSave);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);
        btnLogin.setActionCommand("LOGIN");
        add(btnLogin);

        String hexaColor = "#6B4945";


        btnUs.setForeground(Color.WHITE);
        btnVentas.setForeground(Color.WHITE);
        btnSave.setForeground(Color.WHITE);
        btnLogin.setForeground(Color.WHITE);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String comando = e.getActionCommand();
        if(comando.equals("US")){
            JOptionPane.showMessageDialog(null, "Somos el grupo 5 para DPOO :)");
        } else if(comando.equals("SAVE")){
            principal.salvarGaleria();
        } else if(comando.equals("LOGIN")){
            principal.login();
        } else if(comando.equals("VENTAS")){
            Map<String, Integer> ventasDiarias = principal.getGaleria().contarVentasDiarias();
            javax.swing.JFrame frame = new javax.swing.JFrame("Sales Visualization");
            VisualizadorVentas panel = new VisualizadorVentas(ventasDiarias);
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            // Ajustar el tamaño del frame para que se ajuste a la cuadrícula y las etiquetas
            int frameWidth = 52 * (20 + 2) + 30 + 50;
            int frameHeight = 7 * (20 + 2) + 30 + 50;
            frame.setSize(frameWidth, frameHeight);
            frame.setVisible(true);
        }
    }
}
