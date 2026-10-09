public class CartaEventoCampus extends Carta {
    private final int cambioEnergiaActual;
    private final int cambioEnergiaRival;

    public CartaEventoCampus(int id, String nombre, int costoEnergia,
                             String descripcion, int cambioEnergiaActual,
                             int cambioEnergiaRival) {
        super(id, nombre, costoEnergia, descripcion);

        if (cambioEnergiaActual == 0 && cambioEnergiaRival == 0) {
            throw new IllegalArgumentException(
                    "El evento debe producir al menos un cambio de energia.");
        }

        this.cambioEnergiaActual = cambioEnergiaActual;
        this.cambioEnergiaRival = cambioEnergiaRival;
    }

    @Override
    public String jugarCarta(Jugador jugadorActual, Jugador jugadorRival) {
        int cambioAplicadoActual = jugadorActual.modificarEnergia(cambioEnergiaActual);
        int cambioAplicadoRival = jugadorRival.modificarEnergia(cambioEnergiaRival);

        return jugadorActual.getNombre() + " jugo " + getNombre()
                + ". Cambio de energia de " + jugadorActual.getNombre()
                + ": " + cambioAplicadoActual
                + ". Cambio de energia de " + jugadorRival.getNombre()
                + ": " + cambioAplicadoRival + ".";
    }

    @Override
    public String getTipo() {
        return "Evento Campus";
    }

    public int getCambioEnergiaActual() {
        return cambioEnergiaActual;
    }

    public int getCambioEnergiaRival() {
        return cambioEnergiaRival;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Cambio de energia del jugador actual: "
                + cambioEnergiaActual
                + ", Cambio de energia del jugador rival: "
                + cambioEnergiaRival;
    }
}
