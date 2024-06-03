package interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class PanelOperador extends JPanel implements ActionListener{

    private VentanaPrincipal principal;

    private JButton btnReq1;
    private JButton btnReq2;
    private JButton btnReq3;

    public PanelOperador(VentanaPrincipal ventanaPrincipal) {
        principal = ventanaPrincipal;

        setLayout(new GridLayout(3, 1));
        UIManager.put("Button.background", Color.decode("#E89275"));

        btnReq1 = new JButton("Terminar Subasta");
        btnReq1.setActionCommand("REQ1");
        btnReq1.addActionListener(this);
        add(btnReq1);

        btnReq2 = new JButton("Recibir/registrar oferta");
        btnReq2.setActionCommand("REQ2");
        btnReq2.addActionListener(this);
        add(btnReq2);

        btnReq3 = new JButton("Evaluar oferta");
        btnReq3.setActionCommand("REQ3");
        btnReq3.addActionListener(this);
        add(btnReq3);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String comando = e.getActionCommand();

        if(comando.equals("REQ1")){
            // Terminar Subasta
            principal.terminarSubastaInterfaz();
        }else if(comando.equals("REQ2")){
            // Recibir y registrar oferta
            principal.recibirRegistrarOfertaInterfaz();
        }else if(comando.equals("REQ3")){
            // Evaluar oferta
            principal.evaluarOfertaInterfaz();
        }
    }

}
