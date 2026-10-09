public class JuegoControlador {
    private final Mazo mazo;
    private final ConsolaVista vista;

    public JuegoControlador(Mazo mazo, ConsolaVista vista) {
        if (mazo == null || vista == null) {
            throw new IllegalArgumentException(
                    "El mazo y la vista son obligatorios.");
        }
        this.mazo = mazo;
        this.vista = vista;
    }

    public void iniciar() {
        boolean continuar = true;
        vista.mostrarEncabezado();

        while (continuar) {
            int opcion = vista.leerOpcionMenuPrincipal();

            switch (opcion) {
                case 1:
                    listarCartas();
                    break;
                case 2:
                    buscarCartaPorId();
                    break;
                case 3:
                    buscarCartaPorNombre();
                    break;
                case 4:
                    ordenarMazo();
                    break;
                case 5:
                    continuar = !iniciarPartida();
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    vista.mostrarError("Opcion no valida.");
            }
        }

        vista.mostrarMensaje("Programa finalizado.");
    }

    private void listarCartas() {
        vista.mostrarCartas(mazo.obtenerCartas());
    }

    private void buscarCartaPorId() {
        try {
            int id = vista.leerEnteroPositivo("ID de la carta: ");
            vista.mostrarCarta(mazo.buscarCarta(id));
        } catch (IllegalArgumentException excepcion) {
            vista.mostrarError(excepcion.getMessage());
        }
    }

    private void buscarCartaPorNombre() {
        try {
            String nombre = vista.leerTexto("Nombre de la carta: ");
            vista.mostrarCarta(mazo.buscarCarta(nombre));
        } catch (IllegalArgumentException excepcion) {
            vista.mostrarError(excepcion.getMessage());
        }
    }

    private void ordenarMazo() {
        mazo.ordenarPorCostoEnergia();
        vista.mostrarMensaje("Mazo ordenado por costo de energia.");
        listarCartas();
    }

    private boolean iniciarPartida() {
        try {
            String nombreUno = vista.leerTexto("Nombre del jugador 1: ");
            String nombreDos = vista.leerTexto("Nombre del jugador 2: ");
            Partida partida = new Partida(
                    mazo, new Jugador(nombreUno), new Jugador(nombreDos));
            ejecutarPartida(partida);
            return true;
        } catch (IllegalArgumentException excepcion) {
            vista.mostrarError(excepcion.getMessage());
            return false;
        }
    }

    private void ejecutarPartida(Partida partida) {
        while (!partida.isFinalizada()) {
            vista.mostrarEstadoPartida(partida);
            int opcion = vista.leerOpcionTurno();

            try {
                switch (opcion) {
                    case 1:
                        vista.mostrarMensaje(partida.jugarCarta());
                        break;
                    case 2:
                        vista.mostrarCartaTomada(partida.tomarCarta());
                        break;
                    case 3:
                        partida.pasarTurno();
                        vista.mostrarMensaje("Turno finalizado.");
                        break;
                    default:
                        vista.mostrarError("Accion no valida.");
                }
            } catch (IllegalArgumentException | IllegalStateException excepcion) {
                vista.mostrarError(excepcion.getMessage());
            }
        }

        vista.mostrarResultadoFinal(partida);
    }
}
