import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Mazo {
    private final List<Carta> cartas;
    private final Random random;

    public Mazo() {
        cartas = new ArrayList<>();
        random = new Random();
    }

    public void agregarCarta(Carta carta) {
        if (carta == null) {
            throw new IllegalArgumentException("La carta no puede ser nula.");
        }
        for (Carta cartaRegistrada : cartas) {
            if (cartaRegistrada.getId() == carta.getId()) {
                throw new IllegalArgumentException(
                        "Ya existe una carta con el ID " + carta.getId() + ".");
            }
        }
        cartas.add(carta);
    }

    public Carta buscarCarta(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        for (Carta carta : cartas) {
            if (carta.getId() == id) {
                return carta;
            }
        }
        throw new IllegalArgumentException(
                "No existe una carta con el ID " + id + ".");
    }

    public Carta buscarCarta(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }

        String nombreBuscado = nombre.trim();
        for (Carta carta : cartas) {
            if (carta.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return carta;
            }
        }
        throw new IllegalArgumentException(
                "No existe una carta con el nombre " + nombreBuscado + ".");
    }

    public void ordenarPorCostoEnergia() {
        cartas.sort(Comparator.comparingInt(Carta::getCostoEnergia)
                .thenComparingInt(Carta::getId));
    }

    public Carta tomarCartaAleatoria() {
        if (cartas.isEmpty()) {
            throw new IllegalStateException("No quedan cartas en el mazo.");
        }
        int indice = random.nextInt(cartas.size());
        return cartas.remove(indice);
    }

    public List<Carta> obtenerCartas() {
        return new ArrayList<>(cartas);
    }

    public int cantidadCartas() {
        return cartas.size();
    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }
}
