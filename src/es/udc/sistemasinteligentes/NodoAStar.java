package es.udc.sistemasinteligentes;

// Esta clase extiende la clase Nodo original e implementa Comparable
public class NodoAStar extends Nodo implements Comparable<NodoAStar> {
    private float f;  // f(n) = g(n) + h(n)

    // Constructor para nodo raíz: se calcula f como coste inicial (0) + h
    public NodoAStar(Estado estado, float h) {
        super(estado);
        this.f = h;
    }

    // Constructor para nodos sucesores: se calcula f sumando el coste acumulado (g) y la heurística (h)
    public NodoAStar(Estado estado, NodoAStar padre, Accion accion, float costeAccion, float h) {
        // Utilizamos el constructor de la clase base para calcular g(n)
        super(estado, padre, accion, costeAccion);
        // f = g(n) (calculado en la clase base) + h(n)
        this.f = padre.getCoste() + costeAccion + h;
    }

    public float getF() {
        return f;
    }

    @Override
    public int compareTo(NodoAStar otro) {
        return Float.compare(this.f, otro.getF());
    }

    @Override
    public String toString() {
        return super.toString() + ", f=" + f;
    }
}
