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

```text
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

