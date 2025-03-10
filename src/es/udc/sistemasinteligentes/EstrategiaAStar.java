package es.udc.sistemasinteligentes;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class EstrategiaAStar implements EstrategiaBusquedaInformada {

    private Heuristica heuristica;

    // Constructor que acepta la heurística
    public EstrategiaAStar(Heuristica heuristica) {
        this.heuristica = heuristica;
    }

    @Override
    public NodoAStar[] soluciona(ProblemaBusqueda p, Heuristica heuristica) throws Exception {
        // Actualiza la heurística con la pasada por parámetro
        this.heuristica = heuristica;

        // Frontera: PriorityQueue de NodoAStar ordenada según f = g + h
        PriorityQueue<NodoAStar> frontera = new PriorityQueue<>();
        // Conjunto de estados explorados para evitar ciclos
        HashSet<Estado> explorados = new HashSet<>();

        // Nodo inicial: estado inicial del problema, con g = 0, por lo que f = h
        Estado estadoInicial = p.getEstadoInicial();
        NodoAStar nodoInicial = new NodoAStar(estadoInicial, this.heuristica.evalua(estadoInicial));
        frontera.add(nodoInicial);

        while (!frontera.isEmpty()) {
            NodoAStar actual = frontera.poll();

            if (p.esMeta(actual.getEstado())) {
                return reconstruyeSolucion(actual);
            }

            explorados.add(actual.getEstado());

            for (Accion accion : p.acciones(actual.getEstado())) {
                Estado sucesor = p.result(actual.getEstado(), accion);

                if (!explorados.contains(sucesor)) {
                    float h = this.heuristica.evalua(sucesor);
                    NodoAStar nodoSucesor = new NodoAStar(sucesor, actual, accion, accion.getCoste(), h);
                    frontera.add(nodoSucesor);
                }
            }
        }

        throw new Exception("No se encontró solución");
    }

    // Reconstruye el camino solución retornando un array de NodoAStar
    private NodoAStar[] reconstruyeSolucion(NodoAStar nodoMeta) {
        ArrayList<NodoAStar> camino = new ArrayList<>();
        NodoAStar actual = nodoMeta;
        while (actual != null) {
            camino.addFirst(actual);  // Inserta al principio para invertir el orden
            // Se asume que todos los nodos en el camino son de tipo NodoAStar
            actual = (NodoAStar) actual.getPadre();
        }
        return camino.toArray(new NodoAStar[0]);
    }
}
