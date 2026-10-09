public class CartaCatedratico extends Carta {
    private final String departamento;
    private final int llamadasAtencion;
    private final int tiempoAtencion;

    public CartaCatedratico(int id, String nombre, int costoEnergia,
                            String descripcion, String departamento,
                            int llamadasAtencion, int tiempoAtencion) {
        super(id, nombre, costoEnergia, descripcion);

        if (departamento == null || departamento.trim().isEmpty()) {
            throw new IllegalArgumentException("El departamento no puede estar vacio.");
        }
        if (llamadasAtencion <= 0) {
            throw new IllegalArgumentException(
                    "Las llamadas de atencion deben ser mayores que cero.");
        }
        if (tiempoAtencion <= 0) {
            throw new IllegalArgumentException(
                    "El tiempo de atencion debe ser mayor que cero.");
        }

        this.departamento = departamento.trim();
        this.llamadasAtencion = llamadasAtencion;
        this.tiempoAtencion = tiempoAtencion;
    }

    @Override
    public String jugarCarta(Jugador jugadorActual, Jugador jugadorRival) {
        jugadorRival.agregarLlamadasAtencion(llamadasAtencion);
        int energiaRecuperada = jugadorActual.recuperarEnergia(tiempoAtencion);

        return jugadorActual.getNombre() + " jugo " + getNombre()
                + ". " + jugadorRival.getNombre() + " recibio "
                + llamadasAtencion + " llamada(s) de atencion y "
                + jugadorActual.getNombre() + " recupero "
                + energiaRecuperada + " punto(s) de energia.";
    }

    @Override
    public String getTipo() {
        return "Catedratico";
    }

    public String getDepartamento() {
        return departamento;
    }

    public int getLlamadasAtencion() {
        return llamadasAtencion;
    }

    public int getTiempoAtencion() {
        return tiempoAtencion;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Departamento: " + departamento
                + ", Llamadas de atencion: " + llamadasAtencion
                + ", Tiempo de atencion: " + tiempoAtencion;
    }
}
