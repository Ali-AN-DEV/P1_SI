# Práctica de Sistemas Inteligentes: Estrategias de Búsqueda

## Descripción General

Práctica que implementa estrategias de búsqueda en espacios de estados, desarrollada en el curso de Sistemas Inteligentes 2024/2025.
>Enlace al enunciado y base del proyecto: https://udconline.udc.gal/mod/folder/view.php?id=261556
## Estructura del Proyecto

### Ejercicio 1: Modificación de Estrategias de Búsqueda

#### Clases Principales
- `Nodo`: Representa un nodo en el árbol de búsqueda
- `EstrategiaBusqueda`: Interfaz para estrategias de búsqueda
- `Estrategia4`: Estrategia de búsqueda básica
- `EstrategiaBusquedaGrafo`: Estrategia de búsqueda en grafo mejorada

#### Objetivos

##### Parte A: Implementación de Nodos
- Crear estructura de nodos para capturar:
    - Estado actual
    - Nodo padre
    - Acción que llevó al estado
    - Coste acumulado
- Modificar estrategia de búsqueda para usar nodos
- Implementar reconstrucción de camino de solución

##### Parte B: Búsqueda en Grafo
- Superar limitaciones de estrategias anteriores
- Implementar frontera de exploración
- Permitir exploración sistemática de espacios de estados

### Ejercicio 2: [Pendiente]

- Descripción de objetivos
- Clases a implementar
- Modificaciones requeridas

## Conceptos Aprendidos (Ejercicio 1)

1. Representación de problemas de búsqueda
2. Estructura de nodos de búsqueda
3. Algoritmos de búsqueda no informada
4. Estrategias para evitar redundancia
5. Reconstrucción de caminos de solución

## Características de las Estrategias (Ejercicio 1)

### Estrategia4
- Búsqueda en profundidad simple
- Falla si encuentra un callejón sin salida

### EstrategiaBusquedaGrafo
- Mantiene una frontera de nodos
- Explora sistemáticamente todos los caminos
- Permite backtracking

## Aplicaciones Prácticas

- Planificación de rutas
- Resolución de puzzles
- Sistemas de planificación automática
- Problemas de scheduling

## Requisitos

- Java 8 o superior
- IDE compatible con Java (IntelliJ IDEA recomendado)



## Próximos Pasos

- [ ] Completar Ejercicio 2
- [ ] Documentación adicional
- [ ] Pruebas de las estrategias implementadas

