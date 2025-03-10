package es.udc.sistemasinteligentes;

import java.util.ArrayList;
import java.util.Stack;
import java.util.HashSet;

public class EstrategiaProfundidad implements EstrategiaBusqueda {

    /**
     * Reconstruye la solución desde un nodo meta hasta el nodo inicial
     * @param nodoMeta Nodo que contiene el estado meta
     * @return Array de nodos que representan el camino desde el estado inicial hasta el estado meta
     */
    private Nodo[] reconstruye_sol(Nodo nodoMeta) {
        ArrayList<Nodo> camino = new ArrayList<>();
        Nodo actual = nodoMeta;

        // Recorremos desde el nodo meta hasta el inicial
        while (actual != null) {
            camino.addFirst(actual); // Añadimos al principio para invertir el orden
            actual = actual.getPadre();
        }

        // Convertimos ArrayList a array
        return camino.toArray(new Nodo[0]);
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        // Conjunto de estados explorados
        HashSet<Estado> explorados = new HashSet<>();

        // Frontera: pila para implementar DFS (último en entrar, primero en salir)
        Stack<Nodo> frontera = new Stack<>();

        // Creamos el nodo inicial y lo añadimos a la frontera
        Nodo nodoInicial = new Nodo(p.getEstadoInicial());
        frontera.push(nodoInicial);

        int i = 1;
        System.out.println((i++) + " - Empezando búsqueda en profundidad desde " + nodoInicial.getEstado());

        while (!frontera.isEmpty()) {
            // Extraemos el nodo de la cima de la pila (último añadido)
            Nodo nodoActual = frontera.pop();
            System.out.println((i++) + " - Explorando nodo: " + nodoActual.getEstado());

            // Comprobamos si es meta
            if (p.esMeta(nodoActual.getEstado())) {
                System.out.println((i++) + " - FIN - " + nodoActual.getEstado());
                if (p instanceof ProblemaCuadradoMagico pcm) {
                    System.out.println("Nodos expandidos: " + pcm.getNodosExpandidos());
                    System.out.println("Nodos creados: " + pcm.getNodosCreados());
                }
                return reconstruye_sol(nodoActual);
            }

            // Añadimos el estado a explorados
            explorados.add(nodoActual.getEstado());
            System.out.println((i++) + " - Añadido a explorados: " + nodoActual.getEstado());

            // Obtenemos todas las acciones aplicables
            Accion[] accionesDisponibles = p.acciones(nodoActual.getEstado());
            System.out.println((i++) + " - " + accionesDisponibles.length + " acciones disponibles");

            // Para cada acción, generamos los sucesores en orden inverso (para mantener el orden en la pila)
            for (int j = accionesDisponibles.length - 1; j >= 0; j--) {
                Accion acc = accionesDisponibles[j];
                Estado estadoSucesor = p.result(nodoActual.getEstado(), acc);
                System.out.println((i++) + " - RESULT(" + nodoActual.getEstado() + "," + acc + ")=" + estadoSucesor);

                // Si el estado no ha sido explorado y no está en la frontera
                if (!explorados.contains(estadoSucesor)) {
                    boolean enFrontera = false;

                    // Verificamos si el estado ya está en la frontera
                    for (Nodo n : frontera) {
                        if (n.getEstado().equals(estadoSucesor)) {
                            enFrontera = true;
                            break;
                        }
                    }

                    if (!enFrontera) {
                        // Creamos un nuevo nodo para el sucesor y lo añadimos a la frontera
                        Nodo nodoSucesor = new Nodo(estadoSucesor, nodoActual, acc, acc.getCoste());
                        frontera.push(nodoSucesor);
                        System.out.println((i++) + " - Añadido a frontera: " + estadoSucesor);
                    } else {
                        System.out.println((i++) + " - " + estadoSucesor + " ya está en la frontera");
                    }
                } else {
                    System.out.println((i++) + " - " + estadoSucesor + " ya explorado");
                }
            }
        }

        // Si llegamos aquí, no se ha encontrado solución
        throw new Exception("No se ha podido encontrar una solución");
    }
}