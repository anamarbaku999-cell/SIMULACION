# Ejemplo Práctico 2: Integración Numérica (Cálculo de Áreas)

## 1. Planteamiento del Problema
El método Montecarlo es sumamente útil para calcular integrales definidas que son difíciles de resolver analíticamente. Vamos a aproximar el área bajo la curva de la función:

$$f(x) = x^2$$

En el intervalo desde $x = 0$ hasta $x = 2$. El área máxima teórica (un rectángulo que encierra la función en ese rango) tiene una base de $2$ y una altura de $f(2) = 4$, por lo que $A_{\text{total}} = 2 \times 4 = 8$.



## 2. Algoritmo de Solución
1. Se genera un número pseudoaleatorio $U_1$ para transformar al rango del eje X: $x = 0 + (2 - 0) \cdot U_1$.
2. Se genera un segundo número pseudoaleatorio $U_2$ para transformarlo al rango del eje Y: $y = 0 + (4 - 0) \cdot U_2$.
3. Evaluamos si el punto generado queda por debajo de la curva, es decir, si $y \leq f(x)$.
4. El área estimada se calcula como: 

$$\text{Área} \approx \text{Área Total} \times \frac{\text{Puntos bajo la curva}}{N}$$

## 3. Tabla de Simulación (Primeras 5 iteraciones)

| Iteración ($i$) | $U_1$ | Coordenada $x$ ($2 \cdot U_1$) | $U_2$ | Coordenada $y$ ($4 \cdot U_2$) | $f(x) = x^2$ | ¿Bajo la curva? ($y \leq f(x)$) |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | 0.250 | 0.500 | 0.100 | 0.400 | 0.250 | No |
| 2 | 0.750 | 1.500 | 0.300 | 1.200 | 2.250 | Sí |
| 3 | 0.400 | 0.800 | 0.050 | 0.200 | 0.640 | Sí |
| 4 | 0.900 | 1.800 | 0.850 | 3.400 | 3.240 | No |
| 5 | 0.500 | 1.000 | 0.200 | 0.800 | 1.000 | Sí |

## 4. Conclusión del Experimento
El valor exacto de la integral resuelto por cálculo clásico es $\int_{0}^{2} x^2 dx = \left[ \frac{x^3}{3} \right]_{0}^{2} = \frac{8}{3} \approx 2.6666$. 

Tras ejecutar la simulación con $N = 5,000$ pares de números pseudoaleatorios, el software arroja un total de $1,672$ puntos bajo la curva, resultando en:

$$\text{Área Estimada} = 8 \times \frac{1672}{5000} = 2.6752$$

Lo que representa un margen de error sumamente bajo.
