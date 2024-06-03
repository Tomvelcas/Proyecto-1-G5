package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelOperador extends JPanel implements ActionListener{

    private VentanaPrincipal principal;

    private JButton btnReq1;
    private JButton btnReq2;
    private JButton btnReq3;

    public PanelOperador(VentanaPrincipal ventanaPrincipal) {
        principal = ventanaPrincipal;

        btnReq1 = new JButton("Terminar Subasta");
        btnReq1.setActionCommand("REQ1");
        btnReq1.addActionListener(this);
        add(btnReq1);

        btnReq2 = new JButton("Recibir y registrar oferta");
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
