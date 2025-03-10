package es.udc.sistemasinteligentes;

public class HeuristicaCuadradoMagico extends Heuristica {

    @Override
    public float evalua(Estado e) {
        ProblemaCuadradoMagico.EstadoCuadradoMagico estado = (ProblemaCuadradoMagico.EstadoCuadradoMagico) e;
        int tamano = estado.getTamano();
        int valorSuma = estado.getValorSuma();
        int[][] tablero = estado.getTablero();
        int desviacionTotal = 0;

        // Calcular desviación en filas
        for (int i = 0; i < tamano; i++) {
            int suma = 0;
            int vacios = 0;
            for (int j = 0; j < tamano; j++) {
                if (tablero[i][j] == 0) vacios++;
                else suma += tablero[i][j];
            }
            if (vacios > 0) desviacionTotal += Math.abs(valorSuma - suma);
        }

        // Calcular desviación en columnas
        for (int j = 0; j < tamano; j++) {
            int suma = 0;
            int vacios = 0;
            for (int i = 0; i < tamano; i++) {
                if (tablero[i][j] == 0) vacios++;
                else suma += tablero[i][j];
            }
            if (vacios > 0) desviacionTotal += Math.abs(valorSuma - suma);
        }

        // Calcular desviación en diagonal principal
        int sumaDiag1 = 0;
        int vaciosDiag1 = 0;
        for (int i = 0; i < tamano; i++) {
            if (tablero[i][i] == 0) vaciosDiag1++;
            else sumaDiag1 += tablero[i][i];
        }
        if (vaciosDiag1 > 0) desviacionTotal += Math.abs(valorSuma - sumaDiag1);

        // Calcular desviación en diagonal secundaria
        int sumaDiag2 = 0;
        int vaciosDiag2 = 0;
        for (int i = 0; i < tamano; i++) {
            int j = tamano - 1 - i;
            if (tablero[i][j] == 0) vaciosDiag2++;
            else sumaDiag2 += tablero[i][j];
        }
        if (vaciosDiag2 > 0) desviacionTotal += Math.abs(valorSuma - sumaDiag2);

        // Combinar desviación total con casillas vacías
        return desviacionTotal; // Puede ajustarse según necesidades
    }
}