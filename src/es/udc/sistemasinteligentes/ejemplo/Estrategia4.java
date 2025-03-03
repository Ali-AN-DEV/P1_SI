package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

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
            camino.add(0, actual); // Añadimos al principio para invertir el orden
            actual = actual.getPadre();
        }

        // Convertimos ArrayList a array
        return camino.toArray(new Nodo[0]);
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        ArrayList<Estado> explorados = new ArrayList<>();

        // Creamos el nodo inicial
        Nodo nodoActual = new Nodo(p.getEstadoInicial());
        explorados.add(nodoActual.getEstado());

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + nodoActual.getEstado());

        while (!p.esMeta(nodoActual.getEstado())) {
            System.out.println((i++) + " - " + nodoActual.getEstado() + " no es meta");
            Accion[] accionesDisponibles = p.acciones(nodoActual.getEstado());
            boolean modificado = false;

            for (Accion acc : accionesDisponibles) {
                Estado estadoSucesor = p.result(nodoActual.getEstado(), acc);
                System.out.println((i++) + " - RESULT(" + nodoActual.getEstado() + ","+ acc + ")=" + estadoSucesor);

                if (!explorados.contains(estadoSucesor)) {
                    // Creamos un nuevo nodo para el sucesor
                    nodoActual = new Nodo(estadoSucesor, nodoActual, acc, acc.getCoste());
                    System.out.println((i++) + " - " + estadoSucesor + " NO explorado");
                    explorados.add(estadoSucesor);
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoSucesor);
                    break;
                }
                else
                    System.out.println((i++) + " - " + estadoSucesor + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }

        System.out.println((i++) + " - FIN - " + nodoActual.getEstado());

        // Reconstruimos y devolvemos el camino de solución
        return reconstruye_sol(nodoActual);
    }
}