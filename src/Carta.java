public abstract class Carta {
    private final int id;
    private final String nombre;
    private final int costoEnergia;
    private final String descripcion;

    public Carta(int id, String nombre, int costoEnergia, String descripcion) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        if (costoEnergia <= 0) {
            throw new IllegalArgumentException("El costo de energia debe ser mayor que cero.");
        }

        this.id = id;
        this.nombre = validarTexto(nombre, "El nombre no puede estar vacio.");
        this.costoEnergia = costoEnergia;
        this.descripcion = validarTexto(descripcion, "La descripcion no puede estar vacia.");
    }

    private static String validarTexto(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor.trim();
    }

    public abstract String jugarCarta(Jugador jugadorActual, Jugador jugadorRival);

    public abstract String getTipo();

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCostoEnergia() {
        return costoEnergia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + ", Tipo: " + getTipo()
                + ", Nombre: " + nombre
                + ", Costo de energia: " + costoEnergia
                + ", Descripcion: " + descripcion;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof Carta)) {
            return false;
        }
        Carta otraCarta = (Carta) objeto;
        return id == otraCarta.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
