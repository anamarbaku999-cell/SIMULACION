# Ejemplo Práctico 1: Estimación del Valor de Pi ($\pi$)

## 1. Planteamiento del Problema
El objetivo es aproximar el valor de la constante matemática $\pi$ utilizando el método de Montecarlo de tipo geométrico. 

Imaginemos un círculo de radio $r = 1$ inscrito perfectamente dentro de un cuadrado de lado $L = 2$. 



* El área del cuadrado es: $A_{\text{cuadrado}} = L^2 = 2 \times 2 = 4$
* El área del círculo es: $A_{\text{círculo}} = \pi \cdot r^2 = \pi \cdot 1^2 = \pi$

La probabilidad $P$ de que un punto generado aleatoriamente dentro del cuadrado caiga también dentro del círculo es la razón de sus áreas:

$$P = \frac{A_{\text{círculo}}}{A_{\text{cuadrado}}} = \frac{\pi}{4}$$

Por lo tanto, si simulamos el lanzamiento de $N$ puntos aleatorios, podemos estimar $\pi$ con la siguiente relación:

$$\pi \approx 4 \times \frac{\text{Puntos dentro del círculo}}{\text{Total de puntos } (N)}$$

## 2. Algoritmo y Generación de Números Pseudoaleatorios
Para simular los puntos en el primer cuadrante (simplificando el cálculo con un cuarto de círculo donde $x \in [0,1]$ e $y \in [0,1]$):

1. Generar un número pseudoaleatorio $U_1$ para la coordenada $x$.
2. Generar un número pseudoaleatorio $U_2$ para la coordenada $y$.
3. Verificar si el punto $(x, y)$ cae dentro del círculo usando la ecuación de la circunferencia: $x^2 + y^2 \leq 1$.
4. Repetir $N$ veces y calcular la aproximación.

## 3. Tabla de Simulación (Primeras 5 iteraciones)
A continuación se muestra el comportamiento de las primeras iteraciones simuladas:

| Iteración ($i$) | Pseudoaleatorio $U_1$ ($x$) | Pseudoaleatorio $U_2$ ($y$) | $x^2 + y^2$ | ¿Dentro del círculo? ($\leq 1$) | Éxitos Acumulados | Estimación de $\pi$ |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | 0.1245 | 0.8532 | 0.7434 | Sí | 1 | $4 \times (1/1) = 4.0000$ |
| 2 | 0.9211 | 0.5412 | 1.1413 | No | 1 | $4 \times (1/2) = 2.0000$ |
| 3 | 0.3451 | 0.2145 | 0.1651 | Sí | 2 | $4 \times (2/3) = 2.6666$ |
| 4 | 0.6124 | 0.7110 | 0.8805 | Sí | 3 | $4 \times (3/4) = 3.0000$ |
| 5 | 0.2218 | 0.9841 | 1.0176 | No | 3 | $4 \times (3/5) = 2.4000$ |

## 4. Resultados con N Grandes
Al elevar el número de experimentos ($N$), la aproximación se vuelve mucho más precisa:

* Para $N = 1,000 \implies \pi \approx 3.1280$
* Para $N = 10,000 \implies \pi \approx 3.1416$
*
