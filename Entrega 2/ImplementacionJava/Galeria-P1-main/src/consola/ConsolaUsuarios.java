package consola;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import galeria.Galeria;
import galeria.compraYsubasta.Compra;
import galeria.compraYsubasta.Oferta;
import galeria.compraYsubasta.Subasta;
import galeria.inventarioYpiezas.Fotografia;
import galeria.inventarioYpiezas.Inventario;
import galeria.inventarioYpiezas.Pieza;
import galeria.inventarioYpiezas.Pintura;
import galeria.inventarioYpiezas.Video;
import galeria.usuarios.AdministradorGaleria;
import galeria.usuarios.Cajero;
import galeria.usuarios.Comprador;
import galeria.usuarios.ControladorUsuarios;
import galeria.usuarios.OperadorSubasta;
import galeria.usuarios.Propietario;
import persistencia.PersistenciaGaleria;

public class ConsolaUsuarios {
    
    //Setup para mostrar el funcionamiento de los reqs
    //Galeria
    public static Fotografia foto1= new Fotografia("La nina y el buitre","Kevin Carter", 1993, "Sudán","20-03-2024", true, 30000,  false, "10", "200");
    public static Fotografia foto2= new Fotografia("La torre de Piza", "Edith Piaf", 1355, "Italia","20-12-2024", true, 50000, false, "26", "300");
    public static Video video1= new Video("La Vie", "Edith Piaf", 1983, "Francia","14-11-2024", false, false, 20000,  "40", "200");
    public static Pintura pintura1=new Pintura("La Flor", "Juan Antonio", 1964, "Italia","20-12-2025", false, true, 70000,  77, 53, "Oleo");
    public static Pintura pintura2=new Pintura("Vida", "Juan Antonio", 1994, "Italia","20-12-2024", true, false, 80000,  77, 53, "Oleo");
    public static Inventario inventario1= new Inventario();
    public static ControladorUsuarios controlador= new ControladorUsuarios();
    public static Galeria galeriaConsola = new Galeria(inventario1,controlador);
    public static Pintura pintura3=new Pintura("Mona Lisa", "Leonardo da Vinci", 1506, "Italia","20-10-2024", true, false, 1000000, 77, 53, "Oleo");
    
    //Usuarios
    public static Comprador comprador= new Comprador("LuisP", "12345", "Luis Perez","3456289290", 1000000,galeriaConsola.getInventario().getPiezasDisponibleVenta(), "547293");
    public static Propietario propietario= new Propietario("LuisP", "12345", "Luis Perez", "3456289290","547293");
    public static AdministradorGaleria admin= new AdministradorGaleria("fabio24", "1226745", "Administrador",galeriaConsola, "562901");
    public static Cajero cajero= new  Cajero("juanito2", "762598", "Cajero",galeriaConsola, "4439035");
    public static OperadorSubasta operador= new OperadorSubasta("andresP", "12235345", "Operador",galeriaConsola, "653907");

    //Subastas y Compras
    public static Oferta oferta1= new Oferta(20000, comprador);
    public static Oferta oferta2= new Oferta(40000, comprador);
    public static Oferta ofertaRecibir= new Oferta(25000, comprador);
    public static Subasta subasta1= new Subasta("6748899",39034,15000,video1);
    public static Compra compra1= new Compra("567890", 20000, "tarjeta", "La Vie","547293","09/10/2023");
    public static Compra compra2= new Compra("587650", 70000, "tarjeta", "La Flor","547293","24/08/2023");
    public static Compra compra3= new Compra("567790", 100000, "tarjeta", "Vida","547293","15/01/2023");
    public static Compra compra4= new Compra("587340", 45000, "tarjeta", "La torre de Piza","547293","30/05/2023");
    public static Compra compra5= new Compra("588140", 28000, "tarjeta", "La nina y el buitre","547293","30/05/2023");
    public static Compra compra6= new Compra("521140", 290000, "tarjeta", "Mona Lisa","547293","30/05/2023");
    public static Compra compra7= new Compra("567743", 100000, "tarjeta", "La Vie","547293","15/01/2023");
    public static Compra compra8= new Compra("524340", 290000, "tarjeta", "Mona Lisa","547293","30/05/2023");
    public static Compra compra9= new Compra("520743", 100000, "tarjeta", "La Vie","547293","24/03/2023");


    
    public static void setUp(){
        
        galeriaConsola.getInventario().guardarEnBodega(foto1);
        galeriaConsola.getInventario().guardarEnBodega(foto2);
        galeriaConsola.getInventario().guardarEnBodega(video1);
        galeriaConsola.getInventario().guardarEnBodega(pintura1);
        galeriaConsola.getInventario().guardarEnBodega(pintura2);
        galeriaConsola.getInventario().guardarEnBodega(pintura3);
        galeriaConsola.getInventario().pasarAExhibicion(pintura3);
        galeriaConsola.getInventario().ponerEnDisponibles(foto2);
        galeriaConsola.getInventario().ponerEnDisponibles(pintura3);
        galeriaConsola.getInventario().ponerEnDisponibles(pintura2);
        
        galeriaConsola.getControladorUsuarios().agregarComprador(comprador);
        galeriaConsola.getControladorUsuarios().agregarPropietario(propietario);
        galeriaConsola.getControladorUsuarios().agregarEmpleado(admin);
        galeriaConsola.getControladorUsuarios().agregarEmpleado(cajero);
        galeriaConsola.getControladorUsuarios().agregarEmpleado(operador);
        
        subasta1.agregarOferta(oferta1);
        subasta1.agregarOferta(oferta2);
        galeriaConsola.agregarSubasta(subasta1);
        propietario.agregarPieza(foto1);
        propietario.agregarPieza(video1);
        propietario.agregarPieza(pintura1);
        propietario.pasarAPasadas(pintura1);
        galeriaConsola.agregarCompra(compra1);
        galeriaConsola.agregarCompra(compra2);
        galeriaConsola.agregarCompra(compra3);
        galeriaConsola.agregarCompra(compra4);
        galeriaConsola.agregarCompra(compra5);
        galeriaConsola.agregarCompra(compra6);
        galeriaConsola.agregarCompra(compra7);
        galeriaConsola.agregarCompra(compra8);
        galeriaConsola.agregarCompra(compra9);
        comprador.agregarCompra(compra1);
        comprador.agregarCompra(compra2);
        comprador.agregarCompra(compra3);
        comprador.agregarCompra(compra4);
        comprador.agregarCompra(compra5);
        comprador.agregarCompra(compra6);
        comprador.agregarCompra(compra7);
        comprador.agregarCompra(compra8);
        comprador.agregarCompra(compra9);
        galeriaConsola.setAdministradorGaleria(admin);
    }


    
    // COMPRADOR
    public static void menuComprador() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        setUp();
        do {
            System.out.println("\n--- Menú Comprador ---");
            System.out.println("1. Ver historial de compras");
            System.out.println("2. Realizar compra fija");
            System.out.println("3. Salir");
            System.out.print("Seleccione una acción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    verHistorialCompras();
                    break;
                case 2:
                    realizarCompraFija();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 3);

        scanner.close();
    }

    private static void verHistorialCompras() {
        
        List<Compra> misCompras=comprador.getmisCompras();
        System.out.println("Tus compras son:");
        System.out.println(misCompras.size());
        for (Compra compra : misCompras) {
            
            System.out.println("Pieza:"+ compra.getTituloPieza()+ " Valor Pagado: "+ compra.getValorPagado()); 
        }
            
    }

    public static void realizarCompraFija() {
        System.out.println("El resultado de la compra de la pieza con título "+ foto1.getTitulo()+ " fue: "); 
        System.out.println(comprador.realizarCompraFija(foto1)); 
        System.out.println("El resultado de la compra de la pieza con título "+ foto2.getTitulo()+ " fue: "); 
        System.out.println(comprador.realizarCompraFija(foto2));

    }


    // PROPIETARIO
    public static void menuPropietario() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        setUp();
        do {
            System.out.println("\n--- Menú Propietario ---");
            System.out.println("1. Ver mis piezas actuales");
            System.out.println("2. Ver mis piezas pasadas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una acción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    verMisPiezasActuales();
                    break;
                case 2:
                    verMisPiezasPasadas();
                    break;
                // Agrega aquí la lógica para las opciones restantes del menú del propietario
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 3);

        scanner.close();
    }

    private static void verMisPiezasActuales() {
        
        List<Pieza> piezasActuales=propietario.getMisPiezasActuales();
        System.out.println("Tus piezas actuales son:");
        for (Pieza pieza : piezasActuales) {
            System.out.println(pieza.getTitulo()); 
        }
            
        }


    private static void verMisPiezasPasadas()  {
        
        List<Pieza> piezasPasadas=propietario.getMisPiezasPasadas();
        System.out.println("Tus piezas pasadas son:");
        for (Pieza pieza : piezasPasadas) {
            System.out.println(pieza.getTitulo()); 
        }
            
        }


    // ADMINISTRADOR DE GALERIA
    public static void menuAdministradorGaleria() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        setUp();
        do {
            System.out.println("\n--- Menú Administrador de Galería ---");
            System.out.println("1. Registrar ingreso de pieza");
            System.out.println("2. Confirmar venta");
            System.out.println("3. Devolución de pieza");
            System.out.println("4. Verificar comprador");
            System.out.println("5. Aumentar límite de crédito");
            System.out.println("6. Verificar seriedad de oferta");
            System.out.println("7. Bloquear pieza");
            System.out.println("8. Desbloquear pieza");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una acción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarIngresoPieza();
                    break;
                case 2:
                    confirmarVenta();
                    break;
                case 3:
                    devolucionPieza();
                    break;
                case 4:
                    verificarComprador();
                    break;
                case 5:
                    aumentarLimite();
                    break;
                case 6:
                    verificarSeriedadOferta();
                    break;
                case 7:
                    bloquearPieza();
                    break;
                case 8:
                    desbloquearPieza();
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void registrarIngresoPieza() {
        admin.registrarIngresoPieza(pintura3);
        System.out.println("Se agrego la pieza con la siguiente info a la bodega de la galeria:");
        System.out.println("Título: "+pintura3.getTitulo());
        System.out.println("Año Creación: "+pintura3.getAnioCreacion());
        System.out.println("Lugar Creación: "+pintura3.getLugarCreacion());
        
    }

    private static void confirmarVenta() {
        
        admin.confirmarVenta(compra1,foto1,"547293");
        System.out.println("Se confirmo la venta de la pieza "+foto1.getTitulo()+" por un precio de "+compra1.getValorPagado());
    }

    private static void devolucionPieza() {
        
        System.out.println("El propietario tenia "+ propietario.getMisPiezasActuales().size()+" piezas.");
        admin.devolucionPieza(video1,"547902");
        List<Pieza> piezasActuales=propietario.getMisPiezasActuales();
        System.out.println("Se elimino la pieza: "+video1.getTitulo()+ " y al propietario le quedaron: "+ piezasActuales.size()+" piezas.");
        
            
        }
    

    private static void verificarComprador() {
        
        
        System.out.println("El resultado de la verificación de la existencia del comprador con id 547293 fue: ");
        System.out.println(admin.verificarComprador("547293"));
        
    }

    private static void aumentarLimite() {
       
        System.out.println("El límite anterior del comprador 547293 es: "+comprador.getLimiteCompras());
        admin.aumentarLimite("547293", 200000);
        System.out.println("El nuevo límite del comprador 547293 es: "+comprador.getLimiteCompras());
        
    }



    private static void verificarSeriedadOferta() {
        
        Comprador comprador=galeriaConsola.getControladorUsuarios().obtenerComprador("547293");
        System.out.println("El limite de compras del comprador 547293 es "+comprador.getLimiteCompras());
        System.out.println("La oferta es de 20000");
        System.out.println(admin.verificarSeriedadOferta("547293", 20000));
    }

    private static void bloquearPieza() {
        admin.bloquearPieza("La niña y el buitre");
        System.out.println("Se bloqueo la pieza con el siguiente título:");
        System.out.println("Título: "+foto1.getTitulo());
        System.out.println("Esta bloqueada: "+foto1.isBloqueada());
        
    }

    private static void desbloquearPieza() {
        admin.desbloquearPieza("La Flor");
        System.out.println("Se desbloqueo la pieza con el siguiente título:");
        System.out.println("Título: "+pintura1.getTitulo());
        System.out.println("Esta bloqueada: "+pintura1.isBloqueada());
    }




    // OPERADOR DE SUBASTAS
    public static void menuOperadorSubastas() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        setUp();
        do {
            System.out.println("\n--- Menú Operador de Subastas ---");
            System.out.println("1. Terminar subasta");
            System.out.println("2. Recibir y registrar oferta");
            System.out.println("3. Evaluar oferta");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una acción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    terminarSubasta();
                    break;
                case 2:
                    recibirRegistrarOferta();
                    break;
                case 3:
                    evaluarOferta();
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void terminarSubasta() {
        
        String respuesta=operador.terminarSubasta("6748899");
        System.out.println("El resultado de terminar la subasta 6748899 fue:");
        System.out.println(respuesta);
        
    }

    private static void recibirRegistrarOferta() {
        
        
        String respuesta=operador.recibirRegistrarOferta(ofertaRecibir,"6748899");
        System.out.println("Se recibio la oferta con un valor de "+ofertaRecibir.getValorOferta()+" a la subasta con id 6748899 y el resultado de su registro es:");
        System.out.println(respuesta);
    }

    private static void evaluarOferta() {
        
        if (operador.evaluarOferta(ofertaRecibir,"6748899")==true){
            System.out.println("La oferta es correcta pues supera el valor inicial de la subasta");
        }
        else{
            System.out.println("La oferta no es correcta pues no supera el valor inicial de la subasta");
        }
    }



    // CAJERO
    public static void menuCajero() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        setUp();
        do {
            System.out.println("\n--- Menú Cajero ---");
            System.out.println("1. Registrar pago");
            System.out.println("2. Entregar pieza");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una acción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarPago();
                    break;
                case 2:
                    entregarPieza();
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void registrarPago() {
        
        System.out.println("El estado de registro del pago de la compra con id 567890 por el comprador con el id 547293 es: ");
        System.out.println(cajero.registrarPago(compra1, foto1,"547293"));
    }

    private static void entregarPieza() {
        
        String id=cajero.entregarPieza(foto2, "547293");
        System.out.println("La pieza con título "+ foto2.getTitulo()+ " fue entregada al comprador con id 547293");
        System.out.println("Ahora las piezas del comprador son: ");
        for (Pieza pieza :galeriaConsola.getControladorUsuarios().obtenerPropietario(id).getMisPiezasActuales() ) {
            System.out.println(pieza.getTitulo());
            
        }
    }

    public static void salvar() throws FileNotFoundException {
        setUp();
        PersistenciaGaleria.salvarGaleria(galeriaConsola);
    }
}
