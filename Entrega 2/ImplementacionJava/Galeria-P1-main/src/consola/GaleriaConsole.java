package consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import persistencia.PersistenciaGaleria;
import galeria.Galeria;
import galeria.inventarioYpiezas.Pieza;
import galeria.usuarios.AdministradorGaleria;
import galeria.usuarios.Comprador;
import galeria.usuarios.Empleado;
import interfaz.VisualizadorVentas;

public class GaleriaConsole {

    public static void main(String[] args) throws IOException {
        
        int opcionMenuPrincipal;
        Galeria galeria = null;
        Map<String, Comprador> compradores = new HashMap<String, Comprador>();
        Map<String, Empleado> empleados = new HashMap<String, Empleado>();
        AdministradorGaleria administrador = null;
        
        

        // Bienvenida
        System.out.println("Bienvenido a la Galería y Casa de Subastas");

        do {
            // Menú principal
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Cargar Galería");
            System.out.println("2. Salvar Galería");
            System.out.println("3. Ingresar como Usuario");
            System.out.println("0. Salir\n");
            opcionMenuPrincipal = Integer.parseInt(input("Seleccione una opción: "));

            switch (opcionMenuPrincipal) {
                case 1:
                    galeria = PersistenciaGaleria.cargarGaleria();
                    compradores = galeria.getControladorUsuarios().getMapaLoginCompradores();
                    empleados = galeria.getControladorUsuarios().getMapaLoginEmpleados();
                    System.out.println("Se ha cargado la galeria: "+galeria.equals(galeria));
                    administrador = galeria.getAdministrador();
                    System.out.println("\nLas piezas cargadas fueron: ");
                    for(Pieza pieza: galeria.getInventario().getPiezasEnBodega()){
                        System.out.println(pieza.getTitulo());
                    }
                case 2:
                    ConsolaUsuarios.salvar();
                    break;
                case 3:
                    ingresarComoUsuario(compradores, empleados, administrador, galeria);
                    break;
                case 0:
                    System.out.println("Gracias por utilizar la Galería y Casa de Subastas. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcionMenuPrincipal != 0);

    }

    private static void ingresarComoUsuario(Map<String, Comprador> compradores, Map<String, Empleado> empleados, AdministradorGaleria administrador, Galeria galeria) throws IOException{
        int opcionIngresarUsuario;
        
        do{
        
            System.out.println("\n--- ¿Cómo que tipo de usuario desea ingresar?---");
            System.out.println("1. Comprador");
            System.out.println("2. Empleado");
            System.out.println("3. Administrador de Galería");
            System.out.println("4. Ver gráfico compras");
            System.out.println("0. Volver al Menú Principal\n");
            opcionIngresarUsuario = Integer.parseInt(input("Seleccione una opción: "));
        
            switch (opcionIngresarUsuario) {
                case 1:
                    Comprador comprador = autenticarComprador(compradores);
                    if (comprador != null) {
                        ConsolaComprador.main(galeria, comprador);
                    }
                    break;
                case 2:
                    Empleado empleado = autenticarEmpleado(empleados);
                    if (empleado != null) {
                        ConsolaEmpleado.main(galeria, empleado);
                    }
                    break;
                case 3:
                    AdministradorGaleria adminGaleria = autenticarAdministrador(administrador);
                    if (adminGaleria != null) {
                        ConsolaAdministrador.main(galeria, adminGaleria);
                    }
                    break;
                case 4:
                    Map<String,Integer> mapa= galeria.contarVentasDiarias();
                    // Crear y mostrar la visualización de ventas
                    javax.swing.JFrame frame = new javax.swing.JFrame("Sales Visualization");
                    VisualizadorVentas panel = new VisualizadorVentas(mapa);
                    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
                    frame.add(panel);
                    // Ajustar el tamaño del frame para que se ajuste a la cuadrícula y las etiquetas
                    int frameWidth = 52 * (20 + 2) + 30 + 50;
                    int frameHeight = 7 * (20 + 2) + 30 + 50;
                    frame.setSize(frameWidth, frameHeight);
                    frame.setVisible(true);
                    
                 
                    
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }

        } while (opcionIngresarUsuario != 0);


    }

    private static Comprador autenticarComprador(Map<String, Comprador> compradores){
        
        String login = input("Ingrese su login: ");
        String password = input("Ingrese su contraseña: ");
        Comprador rta = null;
        if (compradores.containsKey(login)){
            Comprador comprador = compradores.get(login);
            if (comprador.getPassword().equals(password)){
                rta = comprador;
            } else {
                System.out.println("Contraseña incorrecta.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
        return rta;
    }

    private static Empleado autenticarEmpleado(Map<String, Empleado> empleados){
        String login = input("Ingrese su login: ");
        String password = input("Ingrese su contraseña: ");
        Empleado rta = null;
        if (empleados.containsKey(login)){
            Empleado empleado = empleados.get(login);
            if (empleado.getPassword().equals(password)){
                rta = empleado;
            } else {
                System.out.println("Contraseña incorrecta.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
        return rta;
    }

    private static AdministradorGaleria autenticarAdministrador(AdministradorGaleria administrador){
        String login = input("Ingrese su login: ");
        String password = input("Ingrese su contraseña: ");
        AdministradorGaleria rta = null;
        if (administrador.getLogin().equals(login)){
            if (administrador.getPassword().equals(password)){
                rta = administrador;
            } else {
                System.out.println("Contraseña incorrecta.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
        return rta;
    }

    public static String input( String mensaje )
    {
        try
        {
            System.out.print( mensaje);
            BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
            String input = reader.readLine( );
            return input;
        }
        catch( IOException e )
        {
            System.out.println( "Error leyendo de la consola" );
        }
        return "error";
    }



}


