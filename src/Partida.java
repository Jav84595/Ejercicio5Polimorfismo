public class Partida {
    public static final int CREDITOS_PARA_GANAR = 20;
    public static final int LIMITE_LLAMADAS_ATENCION = 10;
    public static final int ENERGIA_RECUPERADA_POR_TURNO = 2;
    public static final int CARTAS_MINIMAS = 10;

    private final Mazo mazo;
    private final Jugador jugadorUno;
    private final Jugador jugadorDos;
    private Jugador jugadorActual;
    private Jugador jugadorRival;
    private Jugador ganador;
    private int numeroTurno;
    private boolean finalizada;

    public Partida(Mazo mazo, Jugador jugadorUno, Jugador jugadorDos) {
        if (mazo == null) {
            throw new IllegalArgumentException("El mazo no puede ser nulo.");
        }
        if (mazo.cantidadCartas() < CARTAS_MINIMAS) {
            throw new IllegalArgumentException(
                    "El mazo debe contener al menos " + CARTAS_MINIMAS + " cartas.");
        }
        if (jugadorUno == null || jugadorDos == null) {
            throw new IllegalArgumentException("Los jugadores no pueden ser nulos.");
        }
        if (jugadorUno == jugadorDos
                || jugadorUno.getNombre().equalsIgnoreCase(jugadorDos.getNombre())) {
            throw new IllegalArgumentException(
                    "Los jugadores deben ser diferentes.");
        }

        this.mazo = mazo;
        this.jugadorUno = jugadorUno;
        this.jugadorDos = jugadorDos;
        jugadorActual = jugadorUno;
        jugadorRival = jugadorDos;
        ganador = null;
        numeroTurno = 1;
        finalizada = false;
    }

    public Carta tomarCarta() {
        validarPartidaEnCurso();
        if (jugadorActual.tieneCarta()) {
            throw new IllegalStateException(
                    "El jugador actual ya tiene una carta.");
        }

        Carta cartaTomada = mazo.tomarCartaAleatoria();
        jugadorActual.tomarCarta(cartaTomada);
        finalizarAccion();
        return cartaTomada;
    }

    public String jugarCarta() {
        validarPartidaEnCurso();
        String resultado = jugadorActual.jugarCarta(jugadorRival);
        finalizarAccion();
        return resultado;
    }

    public void pasarTurno() {
        validarPartidaEnCurso();
        finalizarAccion();
    }

    private void finalizarAccion() {
        evaluarFinalizacion();
        if (!finalizada) {
            cambiarTurno();
        }
    }

    private void evaluarFinalizacion() {
        if (cumpleCondicionVictoria(jugadorActual, jugadorRival)) {
            ganador = jugadorActual;
            finalizada = true;
            return;
        }
        if (cumpleCondicionVictoria(jugadorRival, jugadorActual)) {
            ganador = jugadorRival;
            finalizada = true;
            return;
        }
        if (mazo.estaVacio()
                && !jugadorUno.tieneCarta()
                && !jugadorDos.tieneCarta()) {
            ganador = determinarGanadorPorEstado();
            finalizada = true;
        }
    }

    private boolean cumpleCondicionVictoria(Jugador candidato, Jugador rival) {
        return candidato.getCreditos() >= CREDITOS_PARA_GANAR
                || rival.getLlamadasAtencion() >= LIMITE_LLAMADAS_ATENCION;
    }

    private Jugador determinarGanadorPorEstado() {
        if (jugadorUno.getCreditos() != jugadorDos.getCreditos()) {
            return jugadorUno.getCreditos() > jugadorDos.getCreditos()
                    ? jugadorUno : jugadorDos;
        }
        if (jugadorUno.getLlamadasAtencion()
                != jugadorDos.getLlamadasAtencion()) {
            return jugadorUno.getLlamadasAtencion()
                    < jugadorDos.getLlamadasAtencion()
                    ? jugadorUno : jugadorDos;
        }
        if (jugadorUno.getEnergia() != jugadorDos.getEnergia()) {
            return jugadorUno.getEnergia() > jugadorDos.getEnergia()
                    ? jugadorUno : jugadorDos;
        }
        return null;
    }

    private void cambiarTurno() {
        Jugador anterior = jugadorActual;
        jugadorActual = jugadorRival;
        jugadorRival = anterior;
        numeroTurno++;
        jugadorActual.recuperarEnergia(ENERGIA_RECUPERADA_POR_TURNO);
    }

    private void validarPartidaEnCurso() {
        if (finalizada) {
            throw new IllegalStateException("La partida ya finalizo.");
        }
    }

    public Mazo getMazo() {
        return mazo;
    }

    public Jugador getJugadorUno() {
        return jugadorUno;
    }

    public Jugador getJugadorDos() {
        return jugadorDos;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public Jugador getJugadorRival() {
        return jugadorRival;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public int getNumeroTurno() {
        return numeroTurno;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public boolean isEmpate() {
        return finalizada && ganador == null;
    }
}
