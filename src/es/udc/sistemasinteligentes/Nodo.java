package es.udc.sistemasinteligentes;

/**
 * Ejercicio 1: Clase Nodo para representar los estados en el árbol de búsqueda
 */

public class Nodo {
    private Estado estado;      // Estado actual
    private Nodo padre;         // Nodo padre en el árbol de búsqueda
    private Accion accion;      // Acción que llevó a este estado
    private float coste;        // Coste acumulado hasta este nodo

    /**
     * Constructor para nodo raíz (sin padre)
     * @param estado Estado inicial
     */
    public Nodo(Estado estado) {
        this.estado = estado;
        this.padre = null;
        this.accion = null;
        this.coste = 0;
    }

    /**
     * Constructor para nodos con padre
     * @param estado Estado actual
     * @param padre Nodo padre
     * @param accion Acción aplicada para llegar a este estado
     * @param costeAccion Coste de la acción aplicada
     */
    public Nodo(Estado estado, Nodo padre, Accion accion, float costeAccion) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
        this.coste = padre.getCoste() + costeAccion;
    }

    // Getters
    public Estado getEstado() {
        return estado;
    }

    public Nodo getPadre() {
        return padre;
    }

    public Accion getAccion() {
        return accion;
    }

    public float getCoste() {
        return coste;
    }

    @Override
    public String toString() {
        return "Nodo{estado=" + estado + ", accion=" + accion + ", coste=" + coste + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Nodo nodo = (Nodo) obj;
        return estado.equals(nodo.estado);
    }

    @Override
    public int hashCode() {
        return estado.hashCode();
    }
}