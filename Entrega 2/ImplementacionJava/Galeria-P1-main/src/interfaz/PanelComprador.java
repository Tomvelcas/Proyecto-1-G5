package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PanelComprador extends JPanel implements ActionListener{
    private JButton req1;
    private JButton req2;
    private JButton req3;
    private JButton req4;
    private JButton req5;
    private JButton req6;
    private JButton req7;


    public PanelComprador(VentanaPrincipal ventanaPrincipal) {
        setLayout(new GridLayout(7, 1));
        req1 = new JButton("Ver catálogo");
        req1.addActionListener(this);
        req1.setActionCommand("REQ1");
        add(req1);

        req2 = new JButton("Realizar una compra");
        req2.addActionListener(this);
        req2.setActionCommand("REQ2");
        add(req2);

        req3 = new JButton("Ver mi historial de compras");
        req3.addActionListener(this);
        req3.setActionCommand("REQ3");
        add(req3);

        req4 = new JButton("Ver historial de una pieza");
        req4.addActionListener(this);
        req4.setActionCommand("REQ4");
        add(req4);

        req5 = new JButton("Ver historial de un artista");
        req5.addActionListener(this);
        req5.setActionCommand("REQ5");
        add(req5);

        req6 = new JButton("Ver mis piezas actuales");
        req6.addActionListener(this);
        req6.setActionCommand("REQ6");
        add(req6);

        req7 = new JButton("Ver mis piezas pasadas");
        req7.addActionListener(this);
        req7.setActionCommand("REQ7");
        add(req7);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if(comando.equals("REQ1")){
            JOptionPane.showMessageDialog(null, "Req 1");
        } else if(comando.equals("REQ2")){
            JOptionPane.showMessageDialog(null, "Req 2");
        } else if(comando.equals("REQ3")){
            JOptionPane.showMessageDialog(null, "Req 3");
        } else if(comando.equals("REQ4")){
            JOptionPane.showMessageDialog(null, "Req 4");
        } else if(comando.equals("REQ5")){
            JOptionPane.showMessageDialog(null, "Req 5");
        } else if(comando.equals("REQ6")){
            JOptionPane.showMessageDialog(null, "Req 6");
        } else if(comando.equals("REQ7")){
            JOptionPane.showMessageDialog(null, "Req 7");
        }
    }
}
