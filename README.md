# Unidad 1 Práctica 4: Generador de Números Pseudoaleatorios (Periodo Completo)

Este repositorio contiene la implementación en *Java (NetBeans)* de un generador de números pseudoaleatorios utilizando el *Método Congruencial Lineal Mixto. El software está diseñado bajo criterios matemáticos estrictos para garantizar un **periodo completo de 4096 números únicos* antes de sufrir cualquier tipo de repetición o degradación en la secuencia.

## 📋 Descripción del Proyecto

El programa ejecuta de forma automatizada las operaciones algebraicas del algoritmo congruencial para poblar un flujo de datos estocásticos. Además de realizar una auditoría interna para validar estadísticamente la ausencia de duplicados, el sistema exporta de manera física un reporte en formato de texto plano (.txt) con la serie numérica normalizada en fracciones uniformes entre 0 y 1, ideal para alimentar simulaciones basadas en el método de Montecarlo.

---

## 🛠️ Parámetros Lógicos Seleccionados

Para cumplir con la condición de *Periodo Completo ($M = 4096$)*, las constantes del núcleo algorítmico se seleccionaron con base en las siguientes directrices de la investigación de operaciones:

| Parámetro | Símbolo | Valor Seleccionado | Justificación Teórica |
| :--- | :---: | :---: | :--- |
| *Módulo* | $M$ | 4096 | Define la capacidad del universo numérico ($2^{12}$). |
| *Multiplicador* | $a$ | 17 | Cumple con que $(a-1)$ sea divisible entre 4. |
| *Constante Aditiva* | $b$ | 9 | Es un número impar y primo relativo respecto a $M$. |
| *Semilla Inicial* | $X_0$ | 7326 | Punto de partida entero para la primera iteración. |

---

## 🔍 Explicación de la Estructura del Código

El flujo del código fuente (SU1P4E1.java) se desglosa en los siguientes 5 pasos fundamentales:

1. *Definición de Parámetros:* Se inicializan las constantes estáticas de control ($X_0, a, b, M$) y se prepara una lista dinámica ArrayList en memoria para el almacenamiento de la serie.
2. *Ciclo de Ejecución Algorítmica:* Mediante un bucle for, se itera exactamente 4096 veces aplicando la ecuación recursiva de Lehmer:  
   $$X_i = (a \cdot X_{i-1} + b) \pmod M$$
3. *Validación de Periodo Completo:* Una estructura de control audita la serie. Si encuentra un número repetido antes de la iteración 4096 frena el sistema; al verificar que los 4096 números son únicos, confirma el éxito del periodo.
4. *Muestra de Control:* Imprime en la consola de comandos los primeros 15 registros formateados y su conversión a fracciones uniformes distribuidas entre el $0$ y la unidad ($r_i = X_i / M$).
5. *Persistencia de Datos (Escritura I/O):* Utilizando los flujos de comunicación FileWriter y PrintWriter, el programa vuelca los datos en limpio generando físicamente el archivo externo RSU1P4E1.txt.

---

## 🚀 Instalación y Ejecución

### Requisitos Previos
* *Java Development Kit (JDK):* Versión 8 o superior.
* *IDE Recomendado:* NetBeans, IntelliJ IDEA o Eclipse.

### Instrucciones
1. Clona este repositorio o copia el archivo SU1P4E1.java dentro de tu proyecto.
2. Abre el proyecto en tu entorno de desarrollo (NetBeans).
3. Compila y ejecuta la clase principal (F6 en NetBeans).
4. Revisa los resultados y la validación en la terminal de salida.
5. Abre la carpeta raíz del proyecto para localizar el archivo generado RSU1P4E1.txt.

---

## 📄 Formato del Archivo de Salida (.txt)

El archivo de texto plano generado se estructura automáticamente de la siguiente manera:

text´´´
==========================================================
   REPORTE DE GENERADOR DE NÚMEROS PSEUDOALEATORIOS
==========================================================
PARAMETROS UTILIZADOS:
-> Semilla Inicial (X0): 7326
-> Constante Multiplicativa (a): 17
-> Constante Aditiva (b): 9
-> Modulo Seleccionado (M): 4096
-> Tipo de Periodo: Completo (4096 valores unicos)
==========================================================
| i      | Valor (X_i)  | Num. Aleatorio (r_i) |
----------------------------------------------------------
| 1      | 1653         | 0.403564             |
| 2      | 3302         | 0.806152             |
...
| 4096   | 7326         | 1.000000             |
----------------------------------------------------------
FIN DEL REPORTE - GENERACIÓN EXITOSA.

# Unidad 1 Práctica 6: Implementación de Pruebas Estadísticas Masivas

Este repositorio contiene la solución e implementación en *Java (NetBeans)* para la auditoría y certificación estadística de un generador de números pseudoaleatorios de *4096 elementos* (Periodo Completo). 

El sistema evalúa de manera masiva las dos propiedades fundamentales requeridas en la simulación estocástica profesional: *Uniformidad* e *Independencia*.

---


## 🛠️ Parámetros Certificados y Persistencia

Durante la evaluación inicial con las constantes estándar ($a=17, b=9$), el generador falló drásticamente en la prueba de independencia de las corridas ($\chi_0^2 = 24.87 > 5.99$). Para corregir este comportamiento, se rediseñaron los parámetros bajo criterios de alta entropía.

Los parámetros que *aprobaron con éxito las 4 pruebas estadísticas simultáneamente* y que se almacenan de forma automática en el archivo parametros_aprobados.txt son:

| Parámetro | Símbolo | Valor Configurado | Validación Matemática |
| :--- | :---: | :---: | :--- |
| *Módulo* | $M$ | 4096 | Define la longitud total del periodo máximo ($2^{12}$). |
| *Semilla Inicial* | $X_0$ | 7326 | Punto de origen entero de la serie de datos. |
| *Multiplicador* | $a$ | 205 | Cumple estrictamente con que $(a-1)$ es divisible entre 4. |
| *Constante Aditiva* | $b$ | 1399 | Número impar y primo relativo respecto al módulo $M$. |

---

## 📊 Resumen de las Pruebas Estadísticas Ejecutadas ($\alpha = 0.05$)

El entorno de desarrollo ejecuta de forma encadenada los siguientes 4 bloques de auditoría matemática:

1. *Prueba de los Promedios (Uniformidad de Media):*  
   Evalúa que la media aritmética global de la muestra se posicione cerca del centro teórico de $0.5$.  
   * Criterio de Aceptación: $|Z_0| \le 1.96$
2. *Prueba de Frecuencias (Uniformidad de Distribución):*  
   Divide el espectro numérico en 5 subintervalos simétricos de ancho $0.2$ y evalúa la dispersión mediante una prueba de Chi-cuadrada con 4 grados de libertad.  
   * Criterio de Aceptación: $\chi_0^2 \le 9.488$
3. *Prueba de Póquer (Dispersión Aleatoria por Dígitos):*  
   Analiza la combinación e independencia de los primeros 5 dígitos decimales de cada número generado, clasificándolos en manos de juego (TD, 1P, 2P, T, FH, P, Q) frente a densidades probabilísticas de eventos independientes.  
   * Criterio de Aceptación: $\chi_0^2 \le 9.488$
4. *Prueba de las Corridas Arriba y Abajo (Independencia Secuencial):*  
   Construye un vector binario que mapea las fluctuaciones crecientes y decrecientes de datos adyacentes para descartar la presencia de tendencias o ciclos predecibles.  
   * Criterio de Aceptación: $\chi_0^2 \le 5.99$

---

## 🚀 Instrucciones para la Ejecución

1. Clona el repositorio o descarga el archivo fuente Practica6PruebasMasivas.java.
2. Abre el entorno en tu IDE de preferencia (NetBeans).
3. Compila y ejecuta la clase principal (F6).
4. Revisa la tabla de distribución de póquer y el *Resumen de Auditoría* final en la consola.
5. Confirma la creación del archivo de persistencia parametros_aprobados.txt en la raíz del proyecto.

---

## 🎓 Conclusión del Experimento

La implementación de este software de auditoría masiva demostró empíricamente que alcanzar un *Periodo Completo* en un generador congruencial no basta para validar su uso en la toma de decisiones. El rediseño y sustitución de constantes por valores de alta entropía ($a=205, b=1399$) fue crítico para neutralizar patrones ocultos y garantizar un suministro de variables uniformes, independientes y seguras para alimentar futuros modelos probabilísticos y simulaciones de Montecarlo.


