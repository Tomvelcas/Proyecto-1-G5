package consola;

import galeria.Galeria;
import galeria.compraYsubasta.Compra;
import galeria.compraYsubasta.Oferta;
import galeria.compraYsubasta.Subasta;
import galeria.inventarioYpiezas.Pieza;
import galeria.usuarios.Cajero;
import galeria.usuarios.Comprador;
import galeria.usuarios.Empleado;
import galeria.usuarios.OperadorSubasta;


public class ConsolaEmpleado {
    public static void main(Galeria galeria, Empleado empleado) {
        if (empleado instanceof Cajero) {
            menuCajero(galeria, (Cajero) empleado);
        } else if (empleado instanceof OperadorSubasta) {
            menuOperadorSubasta(galeria, (OperadorSubasta) empleado);
        } else {
            System.out.println("Error: Invalid employee type");
        }
    }

    public static void menuCajero(Galeria galeria, Cajero cajero) {

        int option = -1;

        do {
            System.out.println("\n");
            System.out.println("--------------------");
            System.out.println("Menu Cajero");
            System.out.println("1. Registrar pago");
            System.out.println("2. Entregar pieza");
            System.out.println("3. Pago con tarjeta de crédito");
            System.out.println("0. Salir\n");
            option = Integer.parseInt(GaleriaConsole.input("Ingrese una opcion: "));

            switch (option) {
                case 1:
                    registrarPago(galeria, cajero);
                    break;
                case 2:
                    entregarPieza(galeria, cajero);
                    break;
                case 3:
                    PagoTC(galeria, cajero);
                    break;
                case 0:
                    System.out.println("Saliendo del menú Cajero...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while (option != 0) ;
        
    }

    public static void menuOperadorSubasta(Galeria galeria, OperadorSubasta operadorSubasta) {
        
        int option = -1;
        do {
            System.out.println("\n");
            System.out.println("----------  Menú Operador de Subasta ----------");
            System.out.println("1. Terminar subasta");
            System.out.println("2. Recibir y registrar oferta");
            System.out.println("3. Evaluar oferta");
            System.out.println("0. Salir\n");
            option = Integer.parseInt(GaleriaConsole.input("Ingrese una opcion: "));

            switch (option) {
                case 1:
                    terminarSubasta(galeria, operadorSubasta);
                    break;
                case 2:
                    recibirRegistrarOferta(galeria, operadorSubasta);
                    break;
                case 3:
                    evaluarOferta(galeria, operadorSubasta);
                    break;
                case 0:
                    System.out.println("Saliendo del menú Operador de Subasta...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;

            }
        }
        while (option != 0) ;
        
    }

    public static void registrarPago(Galeria galeria, Cajero cajero){
        
        String idCompra = GaleriaConsole.input("Ingrese el ID de la compra: ");
        String idPieza = GaleriaConsole.input("Ingrese el nombre de la pieza: ");
        String idComprador = GaleriaConsole.input("Ingrese el ID del comprador: ");

        Compra compra = galeria.encontrarCompra(idCompra);
        Pieza pieza = galeria.getInventario().buscarPieza(idPieza);
        Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(idComprador);

        if (compra != null && pieza != null && comprador != null) {
            String rta = cajero.registrarPago(compra, pieza, idComprador);
            System.out.println(rta);
        }
        else {
            System.out.println("Error: Invalid purchase, piece or buyer ID");
        }

        
    } 
    private static void PagoTC(Galeria galeria, Cajero cajero) {
        String pasarela = GaleriaConsole.input("Ingrese el nompre de la pasarela (PayUPasarela o PayPalPasarela): ");
        String idComprador= GaleriaConsole.input("Ingrese el id del comprador que va a realizar el pago: ");
        String montoS= GaleriaConsole.input("Ingrese el monto del pago: ");
        int monto= Integer.parseInt(montoS);
        String nTarjeta= GaleriaConsole.input("Ingrese el numero de la tarjeta de credito: (16 dígitos): ");
        String pin= GaleriaConsole.input("Ingrese el pin de seguridad (3 dígitos al costado de la tarjeta): ");
        System.out.println("\n"+cajero.RealizarPagoTarjeta(pasarela, idComprador, nTarjeta, monto, pin));
        
        
    }

    public static void entregarPieza(Galeria galeria, Cajero cajero){
        
        String idPieza = GaleriaConsole.input("Ingrese el nombre de la pieza: ");
        String idComprador = GaleriaConsole.input("Ingrese el ID del comprador: ");

        Pieza pieza = galeria.getInventario().buscarPieza(idPieza);
        Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(idComprador);

        if (pieza != null && comprador != null) {
            String rtaIdComprador = cajero.entregarPieza(pieza, idComprador);
            System.out.println("Pieza entregada al comprador con ID: " + rtaIdComprador);
        }
        else {
            System.out.println("Error: Invalid piece or buyer ID");
        }
    }

    public static void terminarSubasta(Galeria galeria, OperadorSubasta operadorSubasta){
    
        String idSubasta = GaleriaConsole.input("Ingrese el ID de la subasta: ");

        String rta = operadorSubasta.terminarSubasta(idSubasta);
        System.out.println(rta);
        
    }

    public static void recibirRegistrarOferta(Galeria galeria, OperadorSubasta operadorSubasta){
    
        String idSubasta = GaleriaConsole.input("Ingrese el ID de la subasta: ");
        String idComprador = GaleriaConsole.input("Ingrese el ID del comprador de la oferta: ");

        Subasta subasta = galeria.encontrarSubasta(idSubasta);
        Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(idComprador);

        Oferta oferta = null;
        if (comprador != null){
            int valorOferta = Integer.parseInt(GaleriaConsole.input("Ingrese el valor de la oferta: "));
            oferta = new Oferta(valorOferta, comprador);
        }
        else {
            System.out.println("Error: Invalid buyer ID");
        }


        if (subasta != null && oferta != null) {
            String rta = operadorSubasta.recibirRegistrarOferta(oferta, idSubasta);
            System.out.println(rta);
        }
        else {
            System.out.println("Error: Invalid auction or offer ID");
        }
        

    }

    public static void evaluarOferta(Galeria galeria, OperadorSubasta operadorSubasta){
        
        String idSubasta = GaleriaConsole.input("Ingrese el ID de la subasta:");
        String idOferta = GaleriaConsole.input("Ingrese el ID de la oferta:");

        Subasta subasta = galeria.encontrarSubasta(idSubasta);
        if (subasta == null) {
            System.out.println("Error: Invalid auction ID");
        }
        else {
            Oferta oferta = subasta.encontrarOferta(idOferta);
            if (oferta == null) {
                System.out.println("Error: Invalid offer ID");
            }
            else {
                Boolean rta = operadorSubasta.evaluarOferta(oferta, idSubasta);
                if (rta) {
                    System.out.println("La oferta supera el valor inicial");
                }
                else {
                    System.out.println("La oferta no supera el valor inicial");
                }

            }

        }
        
    }    






}
