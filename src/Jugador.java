public class Jugador {
    public static final int ENERGIA_MAXIMA = 10;

    private final String nombre;
    private int energia;
    private int creditos;
    private int llamadasAtencion;
    private Carta cartaEnMano;

    public Jugador(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }

        this.nombre = nombre.trim();
        energia = ENERGIA_MAXIMA;
        creditos = 0;
        llamadasAtencion = 0;
        cartaEnMano = null;
    }

    public void tomarCarta(Carta carta) {
        if (carta == null) {
            throw new IllegalArgumentException("La carta no puede ser nula.");
        }
        if (cartaEnMano != null) {
            throw new IllegalStateException("El jugador ya tiene una carta.");
        }
        cartaEnMano = carta;
    }

    public String jugarCarta(Jugador jugadorRival) {
        if (jugadorRival == null) {
            throw new IllegalArgumentException("El jugador rival no puede ser nulo.");
        }
        if (jugadorRival == this) {
            throw new IllegalArgumentException("El jugador no puede ser su propio rival.");
        }
        if (cartaEnMano == null) {
            throw new IllegalStateException("El jugador no tiene una carta.");
        }

        Carta cartaJugada = cartaEnMano;
        gastarEnergia(cartaJugada.getCostoEnergia());
        String resultado = cartaJugada.jugarCarta(this, jugadorRival);
        cartaEnMano = null;
        return resultado;
    }

    public void gastarEnergia(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad de energia debe ser mayor que cero.");
        }
        if (energia < cantidad) {
            throw new IllegalStateException("El jugador no tiene suficiente energia.");
        }
        energia -= cantidad;
    }

    public int recuperarEnergia(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad de energia debe ser mayor que cero.");
        }
        return modificarEnergia(cantidad);
    }

    public int modificarEnergia(int cantidad) {
        int energiaAnterior = energia;
        long energiaCalculada = (long) energia + cantidad;

        if (energiaCalculada < 0) {
            energia = 0;
        } else if (energiaCalculada > ENERGIA_MAXIMA) {
            energia = ENERGIA_MAXIMA;
        } else {
            energia = (int) energiaCalculada;
        }

        return energia - energiaAnterior;
    }

    public void agregarCreditos(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad de creditos debe ser mayor que cero.");
        }
        creditos += cantidad;
    }

    public void agregarLlamadasAtencion(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "Las llamadas de atencion deben ser mayores que cero.");
        }
        llamadasAtencion += cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEnergia() {
        return energia;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getLlamadasAtencion() {
        return llamadasAtencion;
    }

    public Carta getCartaEnMano() {
        return cartaEnMano;
    }

    public boolean tieneCarta() {
        return cartaEnMano != null;
    }

    @Override
    public String toString() {
        return "Jugador: " + nombre
                + ", Energia: " + energia
                + ", Creditos: " + creditos
                + ", Llamadas de atencion: " + llamadasAtencion
                + ", Carta en mano: "
                + (cartaEnMano == null ? "Ninguna" : cartaEnMano.getNombre());
    }
}
