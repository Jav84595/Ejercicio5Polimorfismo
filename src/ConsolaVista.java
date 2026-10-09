import java.util.List;
import java.util.Scanner;

public class ConsolaVista {
    private final Scanner scanner;

    public ConsolaVista() {
        this(new Scanner(System.in));
    }

    public ConsolaVista(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("El scanner no puede ser nulo.");
        }
        this.scanner = scanner;
    }

    public void mostrarEncabezado() {
        System.out.println("UVG Card Battle");
    }

    public int leerOpcionMenuPrincipal() {
        System.out.println();
        System.out.println("1. Listar cartas del mazo");
        System.out.println("2. Buscar carta por ID");
        System.out.println("3. Buscar carta por nombre");
        System.out.println("4. Ordenar mazo por costo de energia");
        System.out.println("5. Iniciar partida");
        System.out.println("6. Salir");
        return leerEnteroEnRango("Seleccione una opcion: ", 1, 6);
    }

    public int leerOpcionTurno() {
        System.out.println();
        System.out.println("1. Jugar carta");
        System.out.println("2. Tomar carta");
        System.out.println("3. Pasar turno");
        return leerEnteroEnRango("Seleccione una accion: ", 1, 3);
    }

    public String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            mostrarError("El valor no puede estar vacio.");
        }
    }

    public int leerEnteroPositivo(String mensaje) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor > 0) {
                return valor;
            }
            mostrarError("Debe ingresar un numero entero mayor que cero.");
        }
    }

    public void mostrarCartas(List<Carta> cartas) {
        System.out.println();
        System.out.println("Cartas del mazo");
        if (cartas == null || cartas.isEmpty()) {
            System.out.println("No hay cartas disponibles.");
            return;
        }
        for (Carta carta : cartas) {
            mostrarCarta(carta);
        }
    }

    public void mostrarCarta(Carta carta) {
        if (carta == null) {
            mostrarError("No se puede mostrar una carta nula.");
            return;
        }
        System.out.println(carta);
    }

    public void mostrarEstadoPartida(Partida partida) {
        System.out.println();
        System.out.println("Turno " + partida.getNumeroTurno());
        System.out.println("Jugador actual: "
                + partida.getJugadorActual().getNombre());
        System.out.println("Cartas restantes: "
                + partida.getMazo().cantidadCartas());
        System.out.println(partida.getJugadorUno());
        System.out.println(partida.getJugadorDos());
    }

    public void mostrarCartaTomada(Carta carta) {
        System.out.println("Carta tomada:");
        mostrarCarta(carta);
    }

    public void mostrarResultadoFinal(Partida partida) {
        System.out.println();
        System.out.println("Partida finalizada");
        System.out.println(partida.getJugadorUno());
        System.out.println(partida.getJugadorDos());
        if (partida.isEmpate()) {
            System.out.println("Resultado: empate.");
        } else {
            System.out.println("Ganador: " + partida.getGanador().getNombre());
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }

    private int leerEnteroEnRango(String mensaje, int minimo, int maximo) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor >= minimo && valor <= maximo) {
                return valor;
            }
            mostrarError("Seleccione una opcion entre "
                    + minimo + " y " + maximo + ".");
        }
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException excepcion) {
                mostrarError("Formato incorrecto. Ingrese un numero entero.");
            }
        }
    }
}
