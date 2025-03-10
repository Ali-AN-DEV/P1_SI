package es.udc.sistemasinteligentes;

import java.util.ArrayList;
import java.util.Arrays;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {

    public static class EstadoCuadradoMagico extends Estado {
        private final int[][] tablero;
        private final int tamano;
        private final int valorSuma; // La suma que deberían tener todas las filas, columnas y diagonales

        public EstadoCuadradoMagico(int[][] tablero) {
            this.tablero = new int[tablero.length][tablero.length];
            for (int i = 0; i < tablero.length; i++) {
                System.arraycopy(tablero[i], 0, this.tablero[i], 0, tablero.length);
            }
            this.tamano = tablero.length;
            this.valorSuma = tamano * (tamano * tamano + 1) / 2; // Fórmula de la suma mágica
        }

        public int getValorSuma() {
            return valorSuma;
        }

        public int getTamano() {
            return tamano;
        }

        public int[][] getTablero() {
            return tablero;
        }

        public int getValor(int fila, int columna) {
            return tablero[fila][columna];
        }

        // Verifica si una posición está vacía (valor 0)
        public boolean esPosicionVacia(int fila, int columna) {
            return tablero[fila][columna] == 0;
        }

        // Verifica si un número ya está usado en el tablero
        public boolean estaNumeroUsado(int numero) {
            for (int i = 0; i < tamano; i++) {
                for (int j = 0; j < tamano; j++) {
                    if (tablero[i][j] == numero) {
                        return true;
                    }
                }
            }
            return false;
        }

        // Encuentra la primera posición vacía en el tablero
        public int[] encontrarPosicionVacia() {
            for (int i = 0; i < tamano; i++) {
                for (int j = 0; j < tamano; j++) {
                    if (tablero[i][j] == 0) {
                        return new int[]{i, j};
                    }
                }
            }
            return null; // No hay posiciones vacías
        }

        // Verifica si una fila está completa y si suma correctamente
        public boolean esFilaValida(int fila) {
            int suma = 0;
            boolean hayVacios = false;

            for (int j = 0; j < tamano; j++) {
                if (tablero[fila][j] == 0) {
                    hayVacios = true;
                } else {
                    suma += tablero[fila][j];
                }
            }

            // Si la fila está completa, verificamos que suma correctamente
            return hayVacios || suma == valorSuma;
        }

        // Verifica si una columna está completa y si suma correctamente
        public boolean esColumnaValida(int columna) {
            int suma = 0;
            boolean hayVacios = false;

            for (int i = 0; i < tamano; i++) {
                if (tablero[i][columna] == 0) {
                    hayVacios = true;
                } else {
                    suma += tablero[i][columna];
                }
            }

            // Si la columna está completa, verificamos que suma correctamente
            return hayVacios || suma == valorSuma;
        }

        // Verifica si la diagonal principal está completa y si suma correctamente
        public boolean esDiagonalPrincipalValida() {
            int suma = 0;
            boolean hayVacios = false;

            for (int i = 0; i < tamano; i++) {
                if (tablero[i][i] == 0) {
                    hayVacios = true;
                } else {
                    suma += tablero[i][i];
                }
            }

            // Si la diagonal está completa, verificamos que suma correctamente
            return hayVacios || suma == valorSuma;
        }

        // Verifica si la diagonal secundaria está completa y si suma correctamente
        public boolean esDiagonalSecundariaValida() {
            int suma = 0;
            boolean hayVacios = false;

            for (int i = 0; i < tamano; i++) {
                if (tablero[i][tamano - 1 - i] == 0) {
                    hayVacios = true;
                } else {
                    suma += tablero[i][tamano - 1 - i];
                }
            }

            // Si la diagonal está completa, verificamos que suma correctamente
            return hayVacios || suma == valorSuma;
        }

        // Verifica si el tablero actual es válido
        public boolean esEstadoValido() {
            // Verificar todas las filas
            for (int i = 0; i < tamano; i++) {
                if (!esFilaValida(i)) {
                    return false;
                }
            }

            // Verificar todas las columnas
            for (int j = 0; j < tamano; j++) {
                if (!esColumnaValida(j)) {
                    return false;
                }
            }

            // Verificar diagonales
            return esDiagonalPrincipalValida() && esDiagonalSecundariaValida();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tamano; i++) {
                for (int j = 0; j < tamano; j++) {
                    sb.append(tablero[i][j]).append("\t");
                }
                sb.append("\n");
            }
            return sb.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            EstadoCuadradoMagico that = (EstadoCuadradoMagico) obj;
            if (tamano != that.tamano) return false;

            for (int i = 0; i < tamano; i++) {
                for (int j = 0; j < tamano; j++) {
                    if (tablero[i][j] != that.tablero[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = Arrays.deepHashCode(tablero);
            result = 31 * result + tamano;
            return result;
        }
    }

    public static class AccionCuadradoMagico extends Accion {
        private final int fila;
        private final int columna;
        private final int valor;

        public AccionCuadradoMagico(int fila, int columna, int valor) {
            this.fila = fila;
            this.columna = columna;
            this.valor = valor;
        }

        @Override
        public String toString() {
            return "Colocar " + valor + " en (" + fila + "," + columna + ")";
        }

        @Override
        public boolean esAplicable(Estado es) {
            EstadoCuadradoMagico estado = (EstadoCuadradoMagico) es;

            // Verificar si la posición está vacía
            if (!estado.esPosicionVacia(fila, columna)) {
                return false;
            }

            // Verificar si el número ya está usado
            return !estado.estaNumeroUsado(valor);
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadradoMagico estado = (EstadoCuadradoMagico) es;
            int[][] nuevoTablero = new int[estado.getTamano()][estado.getTamano()];

            // Copiar el tablero actual
            for (int i = 0; i < estado.getTamano(); i++) {
                System.arraycopy(estado.getTablero()[i], 0, nuevoTablero[i], 0, estado.getTamano());
            }

            // Aplicar la acción
            nuevoTablero[fila][columna] = valor;

            return new EstadoCuadradoMagico(nuevoTablero);
        }
    }

    private int nodosExpandidos;
    private int nodosCreados;

    public ProblemaCuadradoMagico(EstadoCuadradoMagico estadoInicial) {
        super(estadoInicial);
        this.nodosExpandidos = 0;
        this.nodosCreados = 1; // El nodo inicial ya está creado
    }

    public int getNodosExpandidos() {
        return nodosExpandidos;
    }

    public int getNodosCreados() {
        return nodosCreados;
    }

    public void incrementarNodosExpandidos() {
        nodosExpandidos++;
    }

    public void incrementarNodosCreados() {
        nodosCreados++;
    }

    @Override
    public boolean esMeta(Estado es) {
        EstadoCuadradoMagico estado = (EstadoCuadradoMagico) es;

        // Verificar si el tablero está completo (no hay ceros)
        for (int i = 0; i < estado.getTamano(); i++) {
            for (int j = 0; j < estado.getTamano(); j++) {
                if (estado.getValor(i, j) == 0) {
                    return false;
                }
            }
        }

        // Verificar sumas de filas
        for (int i = 0; i < estado.getTamano(); i++) {
            int suma = 0;
            for (int j = 0; j < estado.getTamano(); j++) {
                suma += estado.getValor(i, j);
            }
            if (suma != estado.getValorSuma()) {
                return false;
            }
        }

        // Verificar sumas de columnas
        for (int j = 0; j < estado.getTamano(); j++) {
            int suma = 0;
            for (int i = 0; i < estado.getTamano(); i++) {
                suma += estado.getValor(i, j);
            }
            if (suma != estado.getValorSuma()) {
                return false;
            }
        }

        // Verificar suma de diagonal principal
        int sumaDiagonal1 = 0;
        for (int i = 0; i < estado.getTamano(); i++) {
            sumaDiagonal1 += estado.getValor(i, i);
        }
        if (sumaDiagonal1 != estado.getValorSuma()) {
            return false;
        }

        // Verificar suma de diagonal secundaria
        int sumaDiagonal2 = 0;
        for (int i = 0; i < estado.getTamano(); i++) {
            sumaDiagonal2 += estado.getValor(i, estado.getTamano() - 1 - i);
        }
        return sumaDiagonal2 == estado.getValorSuma();
    }

    @Override
    public Accion[] acciones(Estado es) {
        EstadoCuadradoMagico estado = (EstadoCuadradoMagico) es;
        incrementarNodosExpandidos();

        // Encontrar la primera casilla vacía
        int[] posicionVacia = estado.encontrarPosicionVacia();
        if (posicionVacia == null) {
            return new Accion[0]; // No hay casillas vacías
        }

        int fila = posicionVacia[0];
        int columna = posicionVacia[1];
        ArrayList<Accion> accionesPosibles = new ArrayList<>();

        // Generar todas las posibles acciones para esta casilla
        for (int valor = 1; valor <= estado.getTamano() * estado.getTamano(); valor++) {
            AccionCuadradoMagico accion = new AccionCuadradoMagico(fila, columna, valor);
            if (accion.esAplicable(estado)) {
                // Verificar si el resultado de aplicar esta acción es un estado válido
                EstadoCuadradoMagico nuevoEstado = (EstadoCuadradoMagico) accion.aplicaA(estado);
                if (nuevoEstado.esEstadoValido()) {
                    accionesPosibles.add(accion);
                    incrementarNodosCreados();
                }
            }
        }

        return accionesPosibles.toArray(new Accion[0]);
    }
}