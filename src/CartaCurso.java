public class CartaCurso extends Carta {
    private final int creditos;
    private final int nivelDificultad;

    public CartaCurso(int id, String nombre, int costoEnergia,
                      String descripcion, int creditos,
                      int nivelDificultad) {
        super(id, nombre, costoEnergia, descripcion);

        if (creditos <= 0) {
            throw new IllegalArgumentException(
                    "Los creditos deben ser mayores que cero.");
        }
        if (nivelDificultad < 1 || nivelDificultad > 5) {
            throw new IllegalArgumentException(
                    "El nivel de dificultad debe estar entre 1 y 5.");
        }

        this.creditos = creditos;
        this.nivelDificultad = nivelDificultad;
    }

    @Override
    public String jugarCarta(Jugador jugadorActual, Jugador jugadorRival) {
        jugadorActual.agregarCreditos(creditos);

        return jugadorActual.getNombre() + " jugo " + getNombre()
                + " y obtuvo " + creditos + " credito(s).";
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    public int getCreditos() {
        return creditos;
    }

    public int getNivelDificultad() {
        return nivelDificultad;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Creditos: " + creditos
                + ", Nivel de dificultad: " + nivelDificultad;
    }
}
