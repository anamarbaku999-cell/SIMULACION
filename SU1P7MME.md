# Investigación del Método Montecarlo

## Introducción
El *Método Montecarlo* es una técnica matemática de simulación que permite aproximar soluciones de problemas complejos mediante el uso de experimentos numéricos repetitivos y *números pseudoaleatorios*. Fue co-desarrollado por los científicos Stanislaw Ulam y John von Neumann en la década de 1940 durante el Proyecto Manhattan.

## Fundamento Matemático
El método se basa en la *Ley de los Grandes Números*, la cual establece que si repetimos un experimento aleatorio un número muy grande de veces, la frecuencia relativa de un evento se aproximará a su probabilidad teórica.

Por ejemplo, para calcular la esperanza matemática de una variable aleatoria continua $X$ con función de densidad $f(x)$ en un intervalo $[a,b]$:

$$E[g(X)] = \int_{a}^{b} g(x)f(x)dx \approx \frac{1}{N} \sum_{i=1}^{N} g(x_i)$$

Donde:
* $N$ es el número de iteraciones (muestras).
* $x_i$ son los números pseudoaleatorios generados uniformemente en el intervalo.

## Ventajas y Desventajas

| Ventajas | Desventajas |
| :--- | :--- |
| Fácil de implementar en software. | Requiere una gran potencia de cómputo para alta precisión. |
| Funciona muy bien en problemas multidimensionales. | La convergencia es relativamente lenta ($1/\sqrt{N}$). |
| Permite modelar la incertidumbre del mundo real. | Depende fuertemente de la calidad del generador de números aleatorios. |
