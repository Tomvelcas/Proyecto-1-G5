package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import galeria.Galeria;
import galeria.compraYsubasta.Compra;
import galeria.compraYsubasta.Oferta;
import galeria.inventarioYpiezas.Escultura;
import galeria.inventarioYpiezas.Fotografia;
import galeria.inventarioYpiezas.Pieza;
import galeria.inventarioYpiezas.Pintura;
import galeria.inventarioYpiezas.Video;
import persistencia.PersistenciaGaleria;
import galeria.usuarios.AdministradorGaleria;
import galeria.usuarios.Comprador;
import galeria.usuarios.Empleado;

public class VentanaPrincipal extends JFrame {

    private Galeria galeria;
    
    private PanelSuperior pSuperior;
    private PanelCentral pCentral;
    private PanelBajo panelOpciones;
    private JPanel pDerecha;

    public VentanaPrincipal() {

        cargarGaleria();
        
        setTitle("Galeria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("Entrega 2/ImplementacionJava/Galeria-P1-main/images/painting.png");
        setIconImage(icon.getImage());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setBackground(java.awt.Color.decode("#DBCDA4"));
        
        panelOpciones = new PanelBajo(this);
        add(panelOpciones, BorderLayout.SOUTH);
        
        pCentral = new PanelCentral(this);
        add(pCentral, BorderLayout.CENTER);

        pSuperior = new PanelSuperior();
        pSuperior.setPreferredSize(new Dimension(getWidth(), 80));
        add(pSuperior, BorderLayout.NORTH);

        pDerecha = new PanelDerecha();
        add(pDerecha, BorderLayout.EAST);


        mostrarObra(0);
        
    }
    
    public static void main(String[] args) {
        VentanaPrincipal princ = new VentanaPrincipal();
        princ.setVisible(true);
    }

    public void cargarGaleria() {
        try {
            galeria = PersistenciaGaleria.cargarGaleria();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la galeria", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void salvarGaleria() {
        try {
            PersistenciaGaleria.salvarGaleria(galeria);
            JOptionPane.showMessageDialog(this, "Galeria guardada con exito", "Guardado", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la galeria", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarObra(int pIndice){
        
        try {
            LinkedList<Pieza> listaPiezas = (LinkedList<Pieza>) pCentral.getListaPiezas();
            Pieza piezaActual = listaPiezas.get(pIndice);
            pCentral.actualizar(piezaActual);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo mostrar la obra", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Galeria getGaleria() {
        return galeria;
    }

    public void login() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(4, 2));

    String[] userOptions = {"Comprador", "Empleado", "Administrador"};
    JComboBox<String> userComboBox = new JComboBox<>(userOptions);
    JTextField loginField = new JTextField();
    JPasswordField passwordField = new JPasswordField();

    panel.add(new JLabel("Usuario:"));
    panel.add(userComboBox);
    panel.add(new JLabel("Login:"));
    panel.add(loginField);
    panel.add(new JLabel("Contraseña:"));
    panel.add(passwordField);

    int result = JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
        String selectedUser = (String) userComboBox.getSelectedItem();
        String login = loginField.getText();
        String password = new String(passwordField.getPassword());

        if (selectedUser == "Comprador"){
            Comprador comprador = galeria.getControladorUsuarios().getMapaLoginCompradores().get(login);
            if (comprador != null && comprador.getPassword().equals(password)){
                pDerecha = new PanelComprador(this);
                add(pDerecha, BorderLayout.EAST);
                List<Pieza> listaPiezas = new ArrayList<Pieza>();
                for (Compra compra: comprador.getmisCompras()){
                    Pieza pieza = galeria.getInventario().buscarPieza(compra.getTituloPieza());
                    listaPiezas.add(pieza);
                }
                pCentral.setListaPiezas(listaPiezas);
                mostrarObra(0);
                    
                revalidate();
                repaint();
            } 
            
            else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
       } else if (selectedUser == "Empleado"){
            Empleado empleado = galeria.getControladorUsuarios().getMapaLoginEmpleados().get(login);
            if (empleado != null && empleado.getPassword().equals(password)){
                if (empleado.getRol().equals("Operador")){
                    pDerecha = new PanelOperador(this);
                    add(pDerecha, BorderLayout.EAST);
                    revalidate();
                    repaint();
                } else if (empleado.getRol().equals("Cajero")){
                    pDerecha = new PanelCajero(this);
                    add(pDerecha, BorderLayout.EAST);
                    revalidate();
                    repaint();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
       } else if (selectedUser == "Administrador"){
            AdministradorGaleria admin = galeria.getAdministrador();
            if (admin != null && admin.getPassword().equals(password)){
                pDerecha = new PanelAdministrador(this);
                add(pDerecha, BorderLayout.EAST);
                revalidate();
                repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
       }
    }

    }
    public void ingresarPiezaInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JTextField tituloField = new JTextField();
        JTextField autorField = new JTextField();
        JTextField anioField = new JTextField();
        JTextField lugarCreacionField = new JTextField();
        JTextField fechaDevolucionField = new JTextField();
        JCheckBox bloqueadaField = new JCheckBox();
        JCheckBox disponibleVentaValorFijoField = new JCheckBox();
        JTextField precioFijoField = new JTextField();
        JTextField estiloField = new JTextField();


        panel.add(new JLabel("Titulo:"));
        panel.add(tituloField);
        panel.add(new JLabel("Autor:"));
        panel.add(autorField);
        panel.add(new JLabel("Anio:"));
        panel.add(anioField);
        panel.add(new JLabel("Lugar de creacion:"));
        panel.add(lugarCreacionField);
        panel.add(new JLabel("Fecha de devolucion:"));
        panel.add(fechaDevolucionField);
        panel.add(new JLabel("Bloqueada:"));
        panel.add(bloqueadaField);
        panel.add(new JLabel("Disponible para venta con valor fijo:"));
        panel.add(disponibleVentaValorFijoField);
        panel.add(new JLabel("Precio fijo:"));
        panel.add(precioFijoField);
        panel.add(new JLabel("Estilo: (Escultura, Fotografia, Pintura, Video)"));
        panel.add(estiloField);

        String estilo_text = estiloField.getText();

        if (estilo_text.equals("Escultura"))
        {
            JTextField altoField = new JTextField();
            JTextField anchoField = new JTextField();
            JTextField profundidadField = new JTextField();
            JTextField pesoField = new JTextField();
            JTextField materialesConstruccionField = new JTextField();
            JCheckBox necesitaElectricidadField = new JCheckBox();

            panel.add(new JLabel("Alto:"));
            panel.add(altoField);
            panel.add(new JLabel("Ancho:"));
            panel.add(anchoField);
            panel.add(new JLabel("Profundidad:"));
            panel.add(profundidadField);
            panel.add(new JLabel("Peso:"));
            panel.add(pesoField);
            panel.add(new JLabel("Materiales de construccion:"));
            panel.add(materialesConstruccionField);
            panel.add(new JLabel("Necesita electricidad:"));
            panel.add(necesitaElectricidadField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Ingresar pieza", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String titulo = tituloField.getText();
                String autor = autorField.getText();
                int anio = Integer.parseInt(anioField.getText());
                String lugarCreacion = lugarCreacionField.getText();
                String fechaDevolucion = fechaDevolucionField.getText();
                boolean bloqueada = bloqueadaField.isSelected();
                boolean disponibleVentaValorFijo = disponibleVentaValorFijoField.isSelected();
                int precioFijo = Integer.parseInt(precioFijoField.getText());
                String estilo = estiloField.getText();

                if (estilo.equals("Escultura")) {
                    int alto = Integer.parseInt(altoField.getText());
                    int ancho = Integer.parseInt(anchoField.getText());
                    int profundidad = Integer.parseInt(profundidadField.getText());
                    int peso = Integer.parseInt(pesoField.getText());
                    String materialesConstruccion = materialesConstruccionField.getText();
                    boolean necesitaElectricidad = necesitaElectricidadField.isSelected();

                    Escultura escultura = new Escultura(titulo, autor, anio, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, bloqueada, precioFijo, alto, ancho, profundidad, peso, materialesConstruccion, necesitaElectricidad);
                    galeria.getAdministrador().registrarIngresoPieza(escultura);

                    JOptionPane.showMessageDialog(this, "Pieza ingresada con exito", "Ingreso", JOptionPane.INFORMATION_MESSAGE);
                }
        }
        }

        if (estilo_text.equals( "Fotografia"))
        {
            JTextField resolucionField = new JTextField();
            JTextField tamanioField = new JTextField();

            panel.add(new JLabel("Resolucion:"));
            panel.add(resolucionField);
            panel.add(new JLabel("Tamaño:"));
            panel.add(tamanioField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Ingresar pieza", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String titulo = tituloField.getText();
                String autor = autorField.getText();
                int anio = Integer.parseInt(anioField.getText());
                String lugarCreacion = lugarCreacionField.getText();
                String fechaDevolucion = fechaDevolucionField.getText();
                boolean bloqueada = bloqueadaField.isSelected();
                boolean disponibleVentaValorFijo = disponibleVentaValorFijoField.isSelected();
                int precioFijo = Integer.parseInt(precioFijoField.getText());
                String estilo = estiloField.getText();

                if (estilo.equals("Fotografia")) {
                    String resolucion = resolucionField.getText();
                    String tamanio = tamanioField.getText();

                    Fotografia fotografia = new Fotografia(titulo, autor, anio, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, precioFijo, bloqueada, resolucion, tamanio);
                    galeria.getAdministrador().registrarIngresoPieza(fotografia);

                    JOptionPane.showMessageDialog(this, "Pieza ingresada con exito", "Ingreso", JOptionPane.INFORMATION_MESSAGE);

                }
            }
            

        }

        if (estiloField.equals( "Pintura"))
        {
            JTextField altoField = new JTextField();
            JTextField anchoField = new JTextField();
            JTextField tecnicaField = new JTextField();

            panel.add(new JLabel("Alto:"));
            panel.add(altoField);
            panel.add(new JLabel("Ancho:"));
            panel.add(anchoField);
            panel.add(new JLabel("Tecnica:"));
            panel.add(tecnicaField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Ingresar pieza", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String titulo = tituloField.getText();
                String autor = autorField.getText();
                int anio = Integer.parseInt(anioField.getText());
                String lugarCreacion = lugarCreacionField.getText();
                String fechaDevolucion = fechaDevolucionField.getText();
                boolean bloqueada = bloqueadaField.isSelected();
                boolean disponibleVentaValorFijo = disponibleVentaValorFijoField.isSelected();
                int precioFijo = Integer.parseInt(precioFijoField.getText());
                String estilo = estiloField.getText();

                if (estilo.equals("Pintura")) {
                    int alto = Integer.parseInt(altoField.getText());
                    int ancho = Integer.parseInt(anchoField.getText());
                    String tecnica = tecnicaField.getText();

                    Pintura pintura = new Pintura(titulo, autor, anio, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, bloqueada, precioFijo, alto, ancho, tecnica);
                    galeria.getAdministrador().registrarIngresoPieza(pintura);

                    JOptionPane.showMessageDialog(this, "Pieza ingresada con exito", "Ingreso", JOptionPane.INFORMATION_MESSAGE);

                }
            }

        }

        if (estiloField.equals( "Video"))
        {
            JTextField duracionField = new JTextField();
            JTextField tamanioField = new JTextField();

            panel.add(new JLabel("Duracion:"));
            panel.add(duracionField);
            panel.add(new JLabel("Tamaño:"));
            panel.add(tamanioField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Ingresar pieza", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String titulo = tituloField.getText();
                String autor = autorField.getText();
                int anio = Integer.parseInt(anioField.getText());
                String lugarCreacion = lugarCreacionField.getText();
                String fechaDevolucion = fechaDevolucionField.getText();
                boolean bloqueada = bloqueadaField.isSelected();
                boolean disponibleVentaValorFijo = disponibleVentaValorFijoField.isSelected();
                int precioFijo = Integer.parseInt(precioFijoField.getText());
                String estilo = estiloField.getText();

                if (estilo.equals("Video")) {
                    String duracion = duracionField.getText();
                    String tamanio = tamanioField.getText();

                    Video video = new Video(titulo, autor, anio, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, bloqueada, precioFijo, duracion, tamanio);
                    galeria.getAdministrador().registrarIngresoPieza(video);

                    JOptionPane.showMessageDialog(this, "Pieza ingresada con exito", "Ingreso", JOptionPane.INFORMATION_MESSAGE);
                    

                }
            }
        }



    }
    public void confirmarVentaInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JTextField tituloPiezaField = new JTextField();
        JTextField idCompradorField = new JTextField();
        JTextField valorPagadoField = new JTextField();
        JTextField tipoPagoField = new JTextField();
        JTextField idObraField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);
        panel.add(new JLabel("ID del comprador:"));
        panel.add(idCompradorField);
        panel.add(new JLabel("Valor pagado:"));
        panel.add(valorPagadoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Confirmar venta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String tituloPieza = tituloPiezaField.getText();
            String idComprador = idCompradorField.getText();
            int valorPagado = Integer.parseInt(valorPagadoField.getText());
            String tipoPago = tipoPagoField.getText();
            String idObra = idObraField.getText();

            Pieza pieza = galeria.getInventario().buscarPieza(tituloPieza);

            Compra compra = new Compra(idObra, valorPagado, tipoPago, tituloPieza, idComprador);
            String mensaje = galeria.getAdministrador().confirmarVenta(compra, pieza, idComprador);
            JOptionPane.showMessageDialog(this, mensaje, "Venta", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public void devolucionPiezaInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JTextField tituloPiezaField = new JTextField();
        JTextField idPropietarioField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);
        panel.add(new JLabel("ID del propietario:"));
        panel.add(idPropietarioField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Devolucion de pieza", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String tituloPieza = tituloPiezaField.getText();
            String idPropietario = idPropietarioField.getText();

            Pieza pieza = galeria.getInventario().buscarPieza(tituloPieza);
            galeria.getAdministrador().devolucionPieza(pieza, idPropietario);
            JOptionPane.showMessageDialog(this, "Devolucion realizada con exito", "Devolucion", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public void aumentarLimiteInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JTextField idField = new JTextField();
        JTextField aumentoField = new JTextField();

        panel.add(new JLabel("ID del comprador:"));
        panel.add(idField);
        panel.add(new JLabel("Aumento:"));
        panel.add(aumentoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Aumentar limite", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String id = idField.getText();
            int aumento = Integer.parseInt(aumentoField.getText());

            galeria.getAdministrador().aumentarLimite(id, aumento);
            JOptionPane.showMessageDialog(this, "Limite aumentado con exito", "Aumento", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public void verificarCompradorInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField idField = new JTextField();

        panel.add(new JLabel("ID del comprador:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Verificar comprador", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String id = idField.getText();

            Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(id);
            if (comprador != null){
                JOptionPane.showMessageDialog(this, "Comprador encontrado", "Comprador", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Comprador no encontrado", "Comprador", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }
    public void verificarSeriedadOfertaInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField idField = new JTextField();

        panel.add(new JLabel("ID del comprador:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Verificar seriedad de oferta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String id = idField.getText();

            Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(id);
            if (comprador != null){
                JOptionPane.showMessageDialog(this, "Comprador serio", "Seriedad", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Comprador no serio", "Seriedad", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }
    public void bloquearPiezaInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField tituloPiezaField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Bloquear pieza", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String tituloPieza = tituloPiezaField.getText();

            galeria.getInventario().bloquearPieza(tituloPieza);
            JOptionPane.showMessageDialog(this, "Pieza bloqueada con exito", "Bloqueo", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public void desbloquearPiezaInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField tituloPiezaField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Desbloquear pieza", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String tituloPieza = tituloPiezaField.getText();

            galeria.getInventario().desbloquearPieza(tituloPieza);
            JOptionPane.showMessageDialog(this, "Pieza desbloqueada con exito", "Desbloqueo", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public void historiaCompradorInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField idField = new JTextField();

        panel.add(new JLabel("ID del comprador:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Historia del comprador", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String id = idField.getText();

            Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(id);
            if (comprador != null){
                JOptionPane.showMessageDialog(this, comprador.toString(), "Historia", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Comprador no encontrado", "Historia", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }
    public void historiaArtistaInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField idField = new JTextField();

        panel.add(new JLabel("ID del artista:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Historia del artista", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String id = idField.getText();

            Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(id);
            if (comprador != null){
                JOptionPane.showMessageDialog(this, comprador.toString(), "Historia", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Artista no encontrado", "Historia", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }
    public void registrarPagoInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JTextField tituloPiezaField = new JTextField();
        JTextField idCompradorField = new JTextField();
        JTextField valorPagadoField = new JTextField();
        JTextField tipoPagoField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);
        panel.add(new JLabel("ID del comprador:"));
        panel.add(idCompradorField);
        panel.add(new JLabel("Valor pagado:"));
        panel.add(valorPagadoField);
        panel.add(new JLabel("Tipo de pago:"));
        panel.add(tipoPagoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Registrar pago", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String tituloPieza = tituloPiezaField.getText();
            String idComprador = idCompradorField.getText();
            int valorPagado = Integer.parseInt(valorPagadoField.getText());
            String tipoPago = tipoPagoField.getText();

            Pieza pieza = galeria.getInventario().buscarPieza(tituloPieza);

            Compra compra = new Compra(tituloPieza, valorPagado, tipoPago, tituloPieza, idComprador);
            String mensaje = galeria.getCajero().registrarPago(compra, pieza, idComprador);
            JOptionPane.showMessageDialog(this, mensaje, "Pago", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void entregarPiezaInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JTextField tituloPiezaField = new JTextField();
        JTextField idCompradorField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);
        panel.add(new JLabel("ID del comprador:"));
        panel.add(idCompradorField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Entregar pieza", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String tituloPieza = tituloPiezaField.getText();
            String idComprador = idCompradorField.getText();

            Pieza pieza = galeria.getInventario().buscarPieza(tituloPieza);
            String mensaje = galeria.getCajero().entregarPieza(pieza, idComprador);
            JOptionPane.showMessageDialog(this, mensaje, "Entrega", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void pagoConTarjetaInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JTextField pasarelaField = new JTextField();
        JTextField idCompradorField = new JTextField();
        JTextField numeroTarjetaField = new JTextField();
        JTextField montoField = new JTextField();
        JTextField pinField = new JTextField();

        panel.add(new JLabel("Pasarela:"));
        panel.add(pasarelaField);
        panel.add(new JLabel("ID del comprador:"));
        panel.add(idCompradorField);
        panel.add(new JLabel("Numero de tarjeta:"));
        panel.add(numeroTarjetaField);
        panel.add(new JLabel("Monto:"));
        panel.add(montoField);
        panel.add(new JLabel("Pin:"));
        panel.add(pinField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Pago con tarjeta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String pasarela = pasarelaField.getText();
            String idComprador = idCompradorField.getText();
            String numeroTarjeta = numeroTarjetaField.getText();
            int monto = Integer.parseInt(montoField.getText());
            String pin = pinField.getText();

            String mensaje = galeria.getCajero().RealizarPagoTarjeta(pasarela, idComprador, numeroTarjeta, monto, pin);
            JOptionPane.showMessageDialog(this, mensaje, "Pago", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void realizarCompraFijaInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JTextField tituloPiezaField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Realizar compra fija", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String tituloPieza = tituloPiezaField.getText();
            Pieza pieza = galeria.getInventario().buscarPieza(tituloPieza);

            String mensaje = galeria.getComprador().realizarCompraFija(pieza);
            JOptionPane.showMessageDialog(this, mensaje, "Compra", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void verCatalogo(){
        List<Pieza> listaPiezas = galeria.getInventario().getPiezasEnBodega();
        pCentral.setListaPiezas(listaPiezas);
        mostrarObra(0);
    }

    public void getmisComprasInterfaz(){
        Comprador comprador = galeria.getComprador();
        List<Pieza> listaPiezas = new ArrayList<Pieza>();
        for (Compra compra: comprador.getmisCompras()){
            Pieza pieza = galeria.getInventario().buscarPieza(compra.getTituloPieza());
            listaPiezas.add(pieza);
        }
        pCentral.setListaPiezas(listaPiezas);
        mostrarObra(0);
    }

    public void verHistorialPiezaInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField tituloPiezaField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Historial de pieza", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String tituloPieza = tituloPiezaField.getText();

            galeria.verHistorialPieza(tituloPieza);
        }
    }
    public void verHistorialArtistaInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField nombreArtistaField = new JTextField();

        panel.add(new JLabel("Nombre del artista:"));
        panel.add(nombreArtistaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Historial de artista", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String nombreArtista = nombreArtistaField.getText();

            galeria.verHistorialArtista(nombreArtista);
        }
    }
    public void vermisPiezasActualesInterfaz(){
        Comprador comprador = galeria.getComprador();
        List<Pieza> listaPiezas = new ArrayList<Pieza>();
        for (Compra compra: comprador.getmisCompras()){
            Pieza pieza = galeria.getInventario().buscarPieza(compra.getTituloPieza());
            listaPiezas.add(pieza);
        }
        pCentral.setListaPiezas(listaPiezas);
        mostrarObra(0);
    }

    public void vermisPiezasPasadasInterfaz(){
        Comprador comprador = galeria.getComprador();
        List<Pieza> listaPiezas = new ArrayList<Pieza>();
        for (Compra compra: comprador.getmisCompras()){
            Pieza pieza = galeria.getInventario().buscarPieza(compra.getTituloPieza());
            listaPiezas.add(pieza);
        }
        pCentral.setListaPiezas(listaPiezas);
        mostrarObra(0);
    }
    public void terminarSubastaInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField idSubastaField = new JTextField();

        panel.add(new JLabel("ID de la subasta:"));
        panel.add(idSubastaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Terminar subasta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String idSubasta = idSubastaField.getText();

            String mensaje = galeria.getOperadorSubasta().terminarSubasta(idSubasta);
            JOptionPane.showMessageDialog(this, mensaje, "Subasta", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void recibirRegistrarOfertaInterfaz(){

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JTextField valorOfertaField = new JTextField();
        JTextField idCompradorField = new JTextField();
        JTextField idSubastaField = new JTextField();

        panel.add(new JLabel("Valor de la oferta:"));
        panel.add(valorOfertaField);
        panel.add(new JLabel("ID del comprador:"));
        panel.add(idCompradorField);
        panel.add(new JLabel("ID de la subasta:"));
        panel.add(idSubastaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Recibir oferta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            int valorOferta = Integer.parseInt(valorOfertaField.getText());
            String idComprador = idCompradorField.getText();
            String idSubasta = idSubastaField.getText();

            Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(idComprador);
            Oferta oferta = new Oferta(valorOferta, comprador);

            String mensaje = galeria.getOperadorSubasta().recibirRegistrarOferta(oferta, idSubasta);
            JOptionPane.showMessageDialog(this, mensaje, "Oferta", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void evaluarOfertaInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField idSubastaField = new JTextField();
        JTextField valorOfertaField = new JTextField();
        JTextField idCompradorField = new JTextField();

        panel.add(new JLabel("ID de la subasta:"));
        panel.add(idSubastaField);
        panel.add(new JLabel("Valor Oferta: "));
        panel.add(valorOfertaField);
        panel.add(new JLabel("ID Comprador: "));
        panel.add(idCompradorField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Evaluar oferta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            String idSubasta = idSubastaField.getText();
            int valorOferta = Integer.parseInt(valorOfertaField.getText());
            String idComprador = idCompradorField.getText();

            Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(idComprador);
            Oferta oferta = new Oferta(valorOferta, comprador);

            boolean resultado = galeria.getOperadorSubasta().evaluarOferta(oferta, idSubasta);
            if (resultado){
                JOptionPane.showMessageDialog(this, "Oferta aceptada", "Oferta", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Oferta rechazada", "Oferta", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}

