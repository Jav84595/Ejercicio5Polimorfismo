import java.util.Random;

public class Main {
    private static final int CANTIDAD_CARTAS = 15;

    public static void main(String[] args) {
        Mazo mazo = crearMazoInicial();
        ConsolaVista vista = new ConsolaVista();
        JuegoControlador controlador = new JuegoControlador(mazo, vista);
        controlador.iniciar();
    }

    public static Mazo crearMazoInicial() {
        Mazo mazo = new Mazo();
        Random random = new Random();

        for (int id = 1; id <= CANTIDAD_CARTAS; id++) {
            int tipo = id <= 3 ? id - 1 : random.nextInt(3);
            mazo.agregarCarta(crearCarta(id, tipo, random));
        }

        return mazo;
    }

    private static Carta crearCarta(int id, int tipo, Random random) {
        switch (tipo) {
            case 0:
                return crearCartaCatedratico(id, random);
            case 1:
                return crearCartaCurso(id, random);
            case 2:
                return crearCartaEvento(id, random);
            default:
                throw new IllegalArgumentException("Tipo de carta no valido.");
        }
    }

    private static Carta crearCartaCatedratico(int id, Random random) {
        String[] departamentos = {
            "Ciencias de la Computacion",
            "Matematica",
            "Fisica",
            "Ingenieria"
        };
        String departamento = departamentos[random.nextInt(departamentos.length)];
        int costo = random.nextInt(5) + 1;
        int llamadas = random.nextInt(4) + 1;
        int tiempo = random.nextInt(3) + 1;

        return new CartaCatedratico(
                id,
                "Catedratico " + departamento + " " + id,
                costo,
                "Catedratico del departamento de " + departamento + ".",
                departamento,
                llamadas,
                tiempo);
    }

    private static Carta crearCartaCurso(int id, Random random) {
        String[] cursos = {
            "Programacion Orientada a Objetos",
            "Calculo",
            "Fisica",
            "Algoritmos y Estructuras de Datos",
            "Bases de Datos"
        };
        String curso = cursos[random.nextInt(cursos.length)];
        int costo = random.nextInt(5) + 1;
        int creditos = random.nextInt(5) + 2;
        int dificultad = random.nextInt(5) + 1;

        return new CartaCurso(
                id,
                curso + " " + id,
                costo,
                "Curso academico de " + curso + ".",
                creditos,
                dificultad);
    }

    private static Carta crearCartaEvento(int id, Random random) {
        String[] eventos = {
            "Semana de Parciales",
            "Feria de Clubes",
            "Cierre de Semestre",
            "Semana de Proyectos",
            "Actividad Deportiva"
        };
        String evento = eventos[random.nextInt(eventos.length)];
        int costo = random.nextInt(5) + 1;
        int cambioActual = random.nextInt(7) - 3;
        int cambioRival = random.nextInt(7) - 3;

        if (cambioActual == 0 && cambioRival == 0) {
            cambioActual = 1;
        }

        return new CartaEventoCampus(
                id,
                evento + " " + id,
                costo,
                "Evento universitario: " + evento + ".",
                cambioActual,
                cambioRival);
    }
}
