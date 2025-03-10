package es.udc.sistemasinteligentes;

public class CuadradoTest {

    public static void main(String[] args) throws Exception {
        // Crear un tablero 3x3 parcialmente relleno
        int[][] tableroInicial = {
                {4, 9, 2},
                {3, 5, 0},
                {0, 0, 0}
        };

        ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial =
                new ProblemaCuadradoMagico.EstadoCuadradoMagico(tableroInicial);

        ProblemaBusqueda problema = new ProblemaCuadradoMagico(estadoInicial);

        System.out.println("Estado inicial:");
        System.out.println(estadoInicial);

        System.out.println("Buscando solución usando búsqueda en profundidad...");
        EstrategiaBusqueda estrategiaProfundidad = new EstrategiaProfundidad();
        Nodo[] solucionProfundidad = estrategiaProfundidad.soluciona(problema);

        System.out.println("\nCamino de solución (Profundidad):");
        for (int i = 0; i < solucionProfundidad.length; i++) {
            System.out.println("Paso " + i + ":");
            System.out.println(solucionProfundidad[i].getEstado());
            if (i < solucionProfundidad.length - 1) {
                System.out.println("Acción: " + solucionProfundidad[i+1].getAccion());
            }
        }

        // Reiniciamos el problema para la búsqueda en anchura
        tableroInicial = new int[][]{
                {4, 9, 2},
                {3, 5, 0},
                {0, 0, 0}
        };

        estadoInicial = new ProblemaCuadradoMagico.EstadoCuadradoMagico(tableroInicial);
        problema = new ProblemaCuadradoMagico(estadoInicial);

        System.out.println("\nBuscando solución usando búsqueda en anchura...");
        EstrategiaBusqueda estrategiaAnchura = new EstrategiaAnchura();
        Nodo[] solucionAnchura = estrategiaAnchura.soluciona(problema);

        System.out.println("\nCamino de solución (Anchura):");
        for (int i = 0; i < solucionAnchura.length; i++) {
            System.out.println("Paso " + i + ":");
            System.out.println(solucionAnchura[i].getEstado());
            if (i < solucionAnchura.length - 1) {
                System.out.println("Acción: " + solucionAnchura[i+1].getAccion());
            }
        }
    }
}