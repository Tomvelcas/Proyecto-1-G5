package interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class PanelAdministrador extends JPanel implements ActionListener{

    private VentanaPrincipal principal;

    private JButton btnReq1;
    private JButton btnReq2;
    private JButton btnReq3;
    private JButton btnReq4;
    private JButton btnReq5;
    private JButton btnReq6;
    private JButton btnReq7;
    private JButton btnReq8;
    private JButton btnReq9;
    private JButton btnReq10;
    private JButton btnReq11;

    public PanelAdministrador(VentanaPrincipal ventanaPrincipal) {
        principal = ventanaPrincipal;

        setLayout(new GridLayout(11, 1));
        UIManager.put("Button.background", Color.decode("#E89275"));

        btnReq1 = new JButton("Registrar ingreso de pieza");
        btnReq1.setActionCommand("REQ1");
        btnReq1.addActionListener(this);
        add(btnReq1);

        btnReq2 = new JButton("Confirmar venta");
        btnReq2.setActionCommand("REQ2");
        btnReq2.addActionListener(this);
        add(btnReq2);

        btnReq3 = new JButton("Devolución de pieza");
        btnReq3.setActionCommand("REQ3");
        btnReq3.addActionListener(this);
        add(btnReq3);

        btnReq4 = new JButton("Verificar comprador");
        btnReq4.setActionCommand("REQ4");
        btnReq4.addActionListener(this);
        add(btnReq4);

        btnReq5 = new JButton("Aumentar limite crédito");
        btnReq5.setActionCommand("REQ5");
        btnReq5.addActionListener(this);
        add(btnReq5);

        btnReq6 = new JButton("Verificar seriedad oferta");
        btnReq6.setActionCommand("REQ6");
        btnReq6.addActionListener(this);
        add(btnReq6);

        btnReq7 = new JButton("Bloquear pieza");
        btnReq7.setActionCommand("REQ7");
        btnReq7.addActionListener(this);
        add(btnReq7);

        btnReq8 = new JButton("Desbloquear pieza");
        btnReq8.setActionCommand("REQ8");
        btnReq8.addActionListener(this);
        add(btnReq8);

        btnReq9 = new JButton("Ver historia de una pieza");
        btnReq9.setActionCommand("REQ9");
        btnReq9.addActionListener(this);
        add(btnReq9);

        btnReq10 = new JButton("Ver historia de un artista");
        btnReq10.setActionCommand("REQ10");
        btnReq10.addActionListener(this);
        add(btnReq10);

        btnReq11 = new JButton("Ver historia de un comprador");
        btnReq11.setActionCommand("REQ11");
        btnReq11.addActionListener(this);
        add(btnReq11);

        UIManager.put("Button.background", Color.decode("#E89275"));


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String comando = e.getActionCommand();

        if(comando.equals("REQ1")){
            // Registrar ingreso de pieza
            principal.ingresarPiezaInterfaz();;
        }else if(comando.equals("REQ2")){
            // Confirmar venta
            principal.confirmarVentaInterfaz();
        }else if(comando.equals("REQ3")){
            // Devolución de pieza
            principal.devolucionPiezaInterfaz();
        }else if(comando.equals("REQ5")){
            // Aumentar limite crédito
            principal.aumentarLimiteInterfaz();
        }else if(comando.equals("REQ4")){
            // Verificar comprador
            principal.verificarCompradorInterfaz();
        }else if(comando.equals("REQ6")){
            // Verificar seriedad oferta
            principal.verificarSeriedadOfertaInterfaz();
        }else if(comando.equals("REQ7")){
            // Bloquear pieza
            principal.bloquearPiezaInterfaz();
        }else if(comando.equals("REQ8")){
            // Desbloquear pieza
            principal.desbloquearPiezaInterfaz();
        }else if(comando.equals("REQ11")){
            // Ver historia de un Comprador
            principal.historiaCompradorInterfaz();
        }
        else if(comando.equals("REQ9")){
            // Ver historia de una pieza
            principal.verHistorialPiezaInterfaz();
        }
        else if(comando.equals("REQ10")){
            // Ver historia de un artista
            principal.historiaArtistaInterfaz();
        }
    }
}