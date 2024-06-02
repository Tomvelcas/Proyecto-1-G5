package interfaz;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import galeria.Galeria;
import galeria.inventarioYpiezas.Pieza;


public class PanelCentral extends JPanel implements ActionListener{
    private JLabel panelImagen;
    private JPanel panelDatosObra;
    private JPanel panelNavegacion;

    private JLabel lblTitulo;
    private JLabel lblAutor;
    private JLabel lblAnio;
    private JLabel lblTipo;
    

    private TextField txtTitulo;
    private TextField txtAutor;
    private TextField txtAnio;
    private TextField txtTipo;

    private JButton btnInicio;
    private JButton btnAnterior;
    private JButton btnSiguiente;
    private JButton btnFinal;

    private int piezaActual;

    private VentanaPrincipal ventanaPrincipal;

    private List<Pieza> listaPiezas = new LinkedList<Pieza>();
    private int indiceActual = 0;

    public PanelCentral(VentanaPrincipal pPrincipal){
        ventanaPrincipal = pPrincipal;

        setBorder(new TitledBorder("Información de la obra"));
        setLayout(new BorderLayout());

        panelNavegacion = new JPanel();
        add(panelNavegacion, BorderLayout.SOUTH);
        panelNavegacion.setLayout( new GridLayout( 1, 4 ) );
        panelNavegacion.setBorder( new TitledBorder( "Navegacion" ) );
        
        panelImagen = new JLabel();
        add(panelImagen, BorderLayout.WEST);
        ImageIcon image = new ImageIcon("Entrega 2/ImplementacionJava/Galeria-P1-main/images/frame_image.png");
        panelImagen.setIcon(image);
        panelImagen.setSize(50, 50);


        panelDatosObra = new JPanel();
        add(panelDatosObra, BorderLayout.CENTER);
        panelDatosObra.setLayout( new GridLayout( 4, 2 ) );

        lblTitulo = new JLabel("Titulo: ");
        panelDatosObra.add(lblTitulo);
        txtTitulo = new TextField();
        panelDatosObra.add(txtTitulo);
        txtTitulo.setEditable(false);

        lblAutor = new JLabel("Autor: ");
        panelDatosObra.add(lblAutor);
        txtAutor = new TextField();
        panelDatosObra.add(txtAutor);
        txtAutor.setEditable(false);

        lblAnio = new JLabel("Año: ");
        panelDatosObra.add(lblAnio);
        txtAnio = new TextField();
        panelDatosObra.add(txtAnio);
        txtAnio.setEditable(false);

        lblTipo = new JLabel("Tipo: ");
        panelDatosObra.add(lblTipo);
        txtTipo = new TextField();
        panelDatosObra.add(txtTipo);
        txtTipo.setEditable(false);

        btnInicio = new JButton("Inicio");
        panelNavegacion.add(btnInicio);

        btnAnterior = new JButton("<--");
        panelNavegacion.add(btnAnterior);
        
        btnSiguiente = new JButton("-->");
        panelNavegacion.add(btnSiguiente);

        btnFinal = new JButton("Final");
        panelNavegacion.add(btnFinal);

        btnInicio.addActionListener(this);
        btnAnterior.addActionListener(this);
        btnSiguiente.addActionListener(this);
        btnFinal.addActionListener(this);

        btnInicio.setActionCommand("INICIO");
        btnAnterior.setActionCommand("ANTERIOR");
        btnSiguiente.setActionCommand("SIGUIENTE");
        btnFinal.setActionCommand("FINAL");

        listaPiezas = new LinkedList<Pieza>();
        listaPiezas = ventanaPrincipal.getGaleria().getInventario().getPiezasDisponibleVenta();



    }

    public void actualizar(Pieza pieza){

        txtTitulo.setText(pieza.getTitulo());
        txtAutor.setText(pieza.getAutor());
        txtAnio.setText(Integer.toString(pieza.getAnioCreacion()));
        txtTipo.setText(pieza.getTipoPieza());
    }

    public List<Pieza> getListaPiezas(){
        return listaPiezas;
    }

    public void setListaPiezas(List<Pieza> listaPiezas){
        this.listaPiezas = listaPiezas;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if(comando.equals("INICIO")){
            if (listaPiezas.size() > 0){
                ventanaPrincipal.mostrarObra(0);
            }
            else {
                JOptionPane.showMessageDialog(this, "No hay obras en la lista", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if(comando.equals("ANTERIOR")){
            if (indiceActual > 0){
                indiceActual--;
                ventanaPrincipal.mostrarObra(indiceActual);
            }
            else {
                JOptionPane.showMessageDialog(this, "No hay obras anteriores", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }else if(comando.equals("SIGUIENTE")){
            if (indiceActual < listaPiezas.size() - 1){
                indiceActual++;
                ventanaPrincipal.mostrarObra(indiceActual);
            }
            else {
                JOptionPane.showMessageDialog(this, "No hay obras siguientes", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if(comando.equals("FINAL")){
            if (listaPiezas.size() > 0){
                ventanaPrincipal.mostrarObra(listaPiezas.size() - 1);
            }
            else {
                JOptionPane.showMessageDialog(this, "No hay obras en la lista", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }   
    }




    
}
