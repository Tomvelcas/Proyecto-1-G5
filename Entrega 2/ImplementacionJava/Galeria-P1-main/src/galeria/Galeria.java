package galeria;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import galeria.compraYsubasta.Compra;
import galeria.compraYsubasta.Subasta;
import galeria.inventarioYpiezas.Inventario;
import galeria.inventarioYpiezas.Pieza;
import usuarios.AdministradorGaleria;
import usuarios.ControladorUsuarios;

public class Galeria {
    private Inventario inventario;
    private ControladorUsuarios controladorUsuarios;
    private AdministradorGaleria administradorGaleria;
    private Map<String, Subasta> subastas;
    private Map<String, Compra> compras;

    public Galeria(Inventario inventario, ControladorUsuarios controladorUsuarios) {
        this.inventario = inventario;
        this.controladorUsuarios = controladorUsuarios;
        this.subastas = new HashMap<String, Subasta>( );
        this.compras = new HashMap<String, Compra>( );
    }


    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public ControladorUsuarios getControladorUsuarios() {
        return controladorUsuarios;
    }

    public AdministradorGaleria getAdministrador(){
        return administradorGaleria;
    }
    public void setControladorUsuarios(ControladorUsuarios controladorUsuarios) {
        this.controladorUsuarios = controladorUsuarios;
    }

    public void setAdministradorGaleria(AdministradorGaleria administradorGaleria) {
        this.administradorGaleria = administradorGaleria;
    }
    public Map<String, Subasta> getSubastas() {
        return subastas;
    }

    public void setSubastas(Map<String, Subasta> subastas) {
        this.subastas = subastas;
    }

    public Map<String, Compra> getCompras() {
        return compras;
    }

    public void setCompras(Map<String, Compra> compras) {
        this.compras = compras;
    }

    public void agregarSubasta(Subasta subasta){
        this.subastas.put(subasta.getId(), subasta);
    }

    public void agregarCompra(Compra compra){
        this.compras.put(compra.getId(), compra);
    }

    public Subasta encontrarSubasta(String id) {
        return subastas.get(id);

    }


    public void verHistorialArtista (String nombreArtista){
        List<Compra> listacompras= (List<Compra>) compras.values();
        List<Pieza> listacomprasAutor= new LinkedList<Pieza>();

        System.out.println("Las piezas realizadas por el artista con nombre: "+nombreArtista);
        for (Compra compra : listacompras) {
            Pieza pieza=inventario.buscarPieza(compra.getTituloPieza());
            String autor=pieza.getAutor();

            if (nombreArtista==autor){
                listacomprasAutor.add(pieza);
                String titulo= pieza.getTitulo();
                int anio=pieza.getAnioCreacion();
                String fechaCompra=compra.getFecha();
                int valorPagado=compra.getValorPagado();
                System.out.println("Titulo pieza: "+titulo+" | Anio de creación: "+anio+" | Fecha de Compra: "+fechaCompra+" | valorPagado: "+valorPagado);

            }
              
        }

        if (listacomprasAutor.size()==0){
            System.out.println("No hay piezas con ese autor en la galeria");
        }

    }

    public void verHistorialPieza (String tituloPieza){
        int numero=0;
        Pieza pieza=inventario.buscarPieza(tituloPieza);
        Collection<Compra> listacompras= compras.values();
        if (pieza!=null){
            System.out.println("La historia de la pieza con título \""+pieza.getTitulo()+"\" es:");
            System.out.println("Información básica de la pieza: ");
            System.out.println("Título: "+pieza.getTitulo()+" | Autor: "+pieza.getAutor()+" | Anio creación: "+pieza.getAnioCreacion()+" | Lugar creación: "+pieza.getLugarCreacion());
            
            
            System.out.println("Información de compras y propietarios: ");
            for (Compra compra : listacompras) {
                
                String tituloCompra=compra.getTituloPieza();
                if(tituloPieza==tituloCompra){
                    numero+=1;
                    String nombreComprador= controladorUsuarios.obtenerComprador(compra.getIdComprador()).getNombre();
                    String fechaCompra=compra.getFecha();
                    int valorPagado=compra.getValorPagado();
                    System.out.println("Nombre propietario: "+nombreComprador+" | Fecha de Compra: "+fechaCompra+" | valorPagado: "+valorPagado);

                }
                
            }
            if (numero==0){
                System.out.println("Esta pieza no tiene ventas registradas en esta galería");
            }

        }
        
        else{
            System.out.println("No ha una pieza en el inventario con ese nombre");
        }

    }

    public Compra encontrarCompra(String id) {
        return compras.get(id);
    }

}

