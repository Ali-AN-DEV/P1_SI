package es.udc.sistemasinteligentes;

public interface EstrategiaBusqueda {
    /**
     * Soluciona el problema de búsqueda, obteniendo un camino de nodos desde el estado inicial hasta un estado meta
     * @param p Problema a solucionar
     * @return Array de nodos que representan el camino desde el estado inicial hasta un estado meta
     */
    public abstract Nodo[] soluciona(ProblemaBusqueda p) throws Exception;
}