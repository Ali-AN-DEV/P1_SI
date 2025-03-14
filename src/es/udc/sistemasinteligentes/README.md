# Práctica de Sistemas Inteligentes: Estrategias de Búsqueda

## Descripción General

Práctica que implementa estrategias de búsqueda en espacios de estados, desarrollada en el curso de Sistemas Inteligentes 2024/2025.
>Enlace al enunciado y base del proyecto: https://udconline.udc.gal/mod/folder/view.php?id=261556
## Estructura del Proyecto

### Ejercicio 1: Modificación de Estrategias de Búsqueda

#### Clases Principales
- `Nodo`: epresenta un nodo en el árbol de búsqueda, encapsulando el estado actual, el nodo padre, la acción aplicada y el coste acumulado.
- `EstrategiaBusqueda`: Interfaz que define la estructura común a todas las estrategias de búsqueda.
- `Estrategia4`: Implementación básica de búsqueda, la cual explora en profundidad pero falla al llegar a callejones sin salida.
- `EstrategiaBusquedaGrafo`: Extiende la búsqueda básica para trabajar sobre grafos, manteniendo una frontera que almacena estados sucesores no explorados, permitiendo backtracking y evitando redundancias.

#### Objetivos

##### Parte A: Implementación de Nodos
- Se crea una clase `Nodo` que encapsula: 
  - **Estado actual**: Representa la configuración/posición actual en el espacio de estados 
  - **Nodo padre**: Indica el nodo desde el cual se llegó al estado actual
  - **Acción aplicada** La acción que de la que se transitó del nodo padre al actual 
  - **Coste acumulado (g)** El coste total acumulado desde el estado inicial hasta el nodo actual
- Integración con la búsqueda:
  - Modificamos `Estrategia4` para trabajar con nodos permitiendo una posterior reconstrucción del camino solución una vez alcanzado el estado meta. 

##### Parte B: Búsqueda en Grafo
- Superar las limitaciones de la búsqueda en árbol 
  - Las estrategias simples pueden acabar sin solución cuando se encuentran estados sin sucesores
- Implementar frontera de exploración en Cola o pila que mantiene los estados sucesores generados pero no explorados permitiendo reexaminar caminos alternativos y evitar exploración redundante
- Permitir exploración sistemática de espacios de estados para recorrer el espacio de estado de forma completa

### Ejercicio 2: Problema del cuadrado mágico

#### Clases principales
- `ProblemaCuadradoMagico`: Formalización del problema definiendo el estado inicial, test de meta y acciones disponibles
- `EstrategiaProfundidad`: Estrategia de búsqueda de profundidad
- `EstrategiaAnchura`: Estrategia de búsqueda de anchura para rcorrer el espacio de estados nivel a nivel
- `MainCuadradoMagico`: Para probar el problema con el ejemplo propuesto

- El problema del cuadrado mágico consiste en:
    - Una matriz NxN donde se deben colocar los números del 1 a N**2
    - La suma de los valores de cada fila, columna y diagonal principal debe ser la misma
    - Se parte de una cuadrícula parcialmente rellena y se debe completar.

##### Parte A: Formalizar el problema del cuadrado mágico
- Creamos la clase `ProblemaCuadradoMagico` y se definen subclases de Estado y Acción.
- Implementar las estrategias de búsqueda de anchura y profundidad
- Contabilizar los nodos creados

##### Parte B: Definir Heurística
- Definir una heurística apropiada y analizar si es admisible y consistente
- Se modifica la clase Nodo creando la clase `NodoAStar` para incluir el valor f (f = g + h) y se implementa la estrategia A* en la clase `EstrategiaAStar`, la cual utiliza una cola de prioridad para seleccionar el nodo con menor f.

## Conceptos Aprendidos (Ejercicio 1)

1. Representación de problemas de búsqueda
2. Estructura de nodos de búsqueda
3. Algoritmos de búsqueda no informada
4. Estrategias para evitar redundancia
5. Reconstrucción de caminos de solución

## Características de las Estrategias (Ejercicio 1)

## Conceptos (Ejercicio 2) 

1. Heurística: Una función heurística estima el coste restante (h(n)) para alcanzar el estado meta desde un nodo n. Su diseño es crítico: debe ser admisible (no sobrestima) y, de ser posible, consistente (cumple la desigualdad triangular con el coste de la acción).
2. A (A-Star):*El algoritmo A* combina el coste acumulado (g(n)) y la estimación heurística (h(n)) para formar la función f(n) = g(n) + h(n). Se utiliza una cola de prioridad para expandir el nodo con el menor valor f, equilibrando la exploración de caminos prometedores con la exhaustividad de la búsqueda.
3. Implementación Práctica: Se extiende la clase Nodo para incluir el valor f y se modifica la estrategia A* (EstrategiaAStar) para trabajar con estos nodos, gestionando la frontera mediante una PriorityQueue.

### Estrategia4
- Búsqueda en profundidad simple
- Falla si encuentra un callejón sin salida

### EstrategiaBusquedaGrafo
- Mantiene una frontera de nodos
- Explora sistemáticamente todos los caminos
- Permite backtracking
