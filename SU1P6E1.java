package SIMULACION;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class SU1P6E1 {
public static void main(String[] args) {
        // 1. Parámetros de la Práctica 4 (Periodo Completo de 4096)
      
    int semilla = 7326;
    int a = 205;       // Nuevo multiplicador con alta entropía
    int b = 1399;      // Nueva constante aditiva prima relativa
    int M = 4096;
    
    List<Double> numeros = new ArrayList<>();
    int actual = semilla;
        
        // Generación de la serie de 4096 números normalizados entre 0 y 1
        for (int i = 0; i < M; i++) {
            int siguiente = (a * actual + b) % M;
            numeros.add((double) siguiente / M);
            actual = siguiente;
        }

        System.out.println("==========================================================================");
        System.out.println("===      PRÁCTICA 6: IMPLEMENTACIÓN DE PRUEBAS ESTADÍSTICAS MASIVAS     ===");
        System.out.println("==========================================================================");
        System.out.println("-> Procesando " + M + " números pseudoaleatorios generados...");

        // Ejecución de las 4 pruebas estadísticas requeridas
        boolean pasoPromedios = evaluarPromedios(numeros);
        boolean pasoFrecuencias = evaluarFrecuencias(numeros);
        boolean pasoPoquer = evaluarPoquer(numeros);
        boolean pasoCorridas = evaluarCorridas(numeros);

        System.out.println("\n==========================================================================");
        System.out.println("===                       RESUMEN DE AUDITORÍA                         ===");
        System.out.println("==========================================================================");
        System.out.println("¿Paso la prueba de promedios?: " + (pasoPromedios ? "SÍ" : "NO"));
        System.out.println("¿Paso la prueba de frecuencias?: " + (pasoFrecuencias ? "SÍ" : "NO"));
        System.out.println("¿Paso la prueba de póquer?: " + (pasoPoquer ? "SÍ" : "NO"));
        System.out.println("¿Paso la prueba de corridas?: " + (pasoCorridas ? "SÍ" : "NO"));

        // Guarda los parámetros en el archivo de texto si pasa las pruebas
        if (pasoPromedios && pasoFrecuencias && pasoPoquer && pasoCorridas) {
            guardarParametrosAprobados("RSU1P6E1.txt", semilla, a, b, M);
        }
    }

    public static boolean evaluarPromedios(List<Double> numeros) {
        System.out.println("\n>>> 1. PRUEBA DE LOS PROMEDIOS");
        int N = numeros.size();
        double suma = 0;
        for (double num : numeros) suma += num;
        double promedio = suma / N;

        double z0 = (promedio - 0.5) * Math.sqrt(12 * N);
        double zCritico = 1.96; 

        System.out.printf("   Promedio: %.5f | Z0: %.5f | Limite: +-%.2f%n", promedio, z0, zCritico);
        boolean pasa = Math.abs(z0) <= zCritico;
        System.out.printf("   CONCLUSIÓN: Como |Z0| = %.4f <= %.2f, %s.%n", Math.abs(z0), zCritico, 
                pasa ? "no se rechaza H0 (Pasa)" : "se rechaza H0 (Falla)");
        return pasa;
    }

    public static boolean evaluarFrecuencias(List<Double> numeros) {
        System.out.println("\n>>> 2. PRUEBA DE FRECUENCIAS (5 SUBINTERVALOS)");
        int N = numeros.size();
        int k = 5;
        int[] fo = new int[k];
        double fe = (double) N / k; // 4096 / 5 = 819.2

        for (double num : numeros) {
            int intervalo = (int) (num * k);
            if (intervalo >= k) intervalo = k - 1;
            fo[intervalo]++;
        }

        double chi0 = 0;
        for (int i = 0; i < k; i++) {
            chi0 += Math.pow(fo[i] - fe, 2) / fe;
        }

        double chiCritico = 9.488; // 4 grados de libertad
        System.out.printf("   Chi-cuadrada calculada: %.5f | Valor critico: %.3f%n", chi0, chiCritico);
        boolean pasa = chi0 <= chiCritico;
        System.out.printf("   CONCLUSIÓN: Como Chi0^2 = %.4f <= %.3f, %s.%n", chi0, chiCritico, 
                pasa ? "no se rechaza H0 (Pasa)" : "se rechaza H0 (Falla)");
        return pasa;
    }

    public static boolean evaluarPoquer(List<Double> numeros) {
        System.out.println("\n>>> 3. PRUEBA DE PÓQUER (5 DECIMALES)");
        int N = numeros.size();
        int[] fo = new int[7]; // TD, 1P, 2P, T, FH, P, Q

        for (double num : numeros) {
            String str = String.format("%.5f", num);
            String decimales = str.substring(str.indexOf(".") + 1);
            if (decimales.length() > 5) decimales = decimales.substring(0, 5);
            
            Map<Character, Integer> conteo = new HashMap<>();
            for (char c : decimales.toCharArray()) {
                conteo.put(c, conteo.getOrDefault(c, 0) + 1);
            }
            
            List<Integer> valores = new ArrayList<>(conteo.values());
            Collections.sort(valores, Collections.reverseOrder());

            if (valores.get(0) == 5) fo[6]++; // Quintilla
            else if (valores.get(0) == 4) fo[5]++; // Póquer
            else if (valores.get(0) == 3 && valores.get(1) == 2) fo[4]++; // Full House
            else if (valores.get(0) == 3) fo[3]++; // Tercia
            else if (valores.get(0) == 2 && valores.get(1) == 2) fo[2]++; // Dos Pares
            else if (valores.get(0) == 2) fo[1]++; // Un Par
            else fo[0]++; // Todos Diferentes
        }

        // Probabilidades teóricas fijas para 5 decimales independientes
        double[] prob = {0.3024, 0.5040, 0.1080, 0.0720, 0.0090, 0.0045, 0.0001};
        double chi0 = 0;

        // Agrupamiento dinámico si FE < 5 (FH, P, Q se unen por seguridad matemática)
        double foAcum = 0, feAcum = 0;
        
        System.out.printf("   | %-18s | %-10s | %-10s |%n", "Mano", "FO", "FE");
        String[] manos = {"Todos Dif (TD)", "Un Par (1P)", "Dos Pares (2P)", "Tercia (T)", "Full House", "Poquer (P)", "Quintilla (Q)"};
        
        for (int i = 0; i < 7; i++) {
            double fe_i = N * prob[i];
            System.out.printf("   | %-18s | %-10d | %-10.2f |%n", manos[i], fo[i], fe_i);
            
            if (i < 4) {
                chi0 += Math.pow(fo[i] - fe_i, 2) / fe_i;
            } else {
                foAcum += fo[i];
                feAcum += fe_i;
            }
        }
        chi0 += Math.pow(foAcum - feAcum, 2) / feAcum; // Añade categoría agrupada

        double chiCritico = 9.488; // 4 grados de libertad después de agrupar categorías
        System.out.printf("   Chi-cuadrada calculada (con agrupamiento): %.5f | Valor critico: %.3f%n", chi0, chiCritico);
        boolean pasa = chi0 <= chiCritico;
        System.out.printf("   CONCLUSIÓN: Como Chi0^2 = %.4f <= %.3f, %s.%n", chi0, chiCritico, 
                pasa ? "no se rechaza H0 (Pasa)" : "se rechaza H0 (Falla)");
        return pasa;
    }

    public static boolean evaluarCorridas(List<Double> numeros) {
        System.out.println("\n>>> 4. PRUEBA DE LAS CORRIDAS (ARRIBA Y ABAJO)");
        int N = numeros.size();
        List<Integer> sec = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            sec.add(numeros.get(i) < numeros.get(i + 1) ? 0 : 1);
        }

        int fo_1 = 0, fo_2 = 0, fo_MayorIgual3 = 0;
        int longitudActual = 1;
        int totalCorridas = 0;

        for (int i = 0; i < sec.size() - 1; i++) {
            if (sec.get(i).equals(sec.get(i + 1))) {
                longitudActual++;
            } else {
                if (longitudActual == 1) fo_1++;
                else if (longitudActual == 2) fo_2++;
                else fo_MayorIgual3++;
                totalCorridas++;
                longitudActual = 1;
            }
        }
        if (longitudActual == 1) fo_1++;
        else if (longitudActual == 2) fo_2++;
        else fo_MayorIgual3++;
        totalCorridas++;

        double totalEsperadoEC = (2.0 * N - 1.0) / 3.0;
        double fe_1 = (2.0 * 5 * N - 7) / 24.0; // Simplificación de fórmula para i=1 con N grande
        double fe_2 = (2.0 * 11 * N - 14) / 120.0; // Simplificación para i=2
        double fe_MayorIgual3 = totalEsperadoEC - fe_1 - fe_2;

        double t1 = Math.pow(fo_1 - fe_1, 2) / fe_1;
        double t2 = Math.pow(fo_2 - fe_2, 2) / fe_2;
        double t3 = Math.pow(fo_MayorIgual3 - fe_MayorIgual3, 2) / fe_MayorIgual3;
        double chi0 = t1 + t2 + t3;

        double chiCritico = 5.99; // 2 grados de libertad
        System.out.printf("   Total Corridas (h): %d | Chi-cuadrada calculada: %.5f | Valor critico: %.2f%n", 
                totalCorridas, chi0, chiCritico);
        boolean pasa = chi0 <= chiCritico;
        System.out.printf("   CONCLUSIÓN: Como Chi0^2 = %.4f <= %.2f, %s.%n", chi0, chiCritico, 
                pasa ? "no se rechaza H0 (Pasa)" : "se rechaza H0 (Falla)");
        return pasa;
    }

    public static void guardarParametrosAprobados(String ruta, int sem, int a, int b, int m) {
        try (FileWriter fw = new FileWriter(ruta); PrintWriter pw = new PrintWriter(fw)) {
            pw.println("==========================================================");
            pw.println("   REGISTRO DE PARÁMETROS CONFIGURADOS COMPATIBLES (APROBADOS)");
            pw.println("==========================================================");
            pw.println("Los siguientes parametros superaron exitosamente las 4 pruebas");
            pw.println("estadisticas de uniformidad e independencia (Alpha = 0.05):");
            pw.println("-> Semilla Inicial (X0): " + sem);
            pw.println("-> Constante Multiplicativa (a): " + a);
            pw.println("-> Constante Aditiva (b): " + b);
            pw.println("-> Modulo (M): " + m);
            pw.println("-> Estatus del Generador: CERTIFICADO / COMPATIBLE");
            pw.println("==========================================================");
            System.out.println("\n[ÉXITO] Archivo '" + ruta + "' creado con los parámetros auditados.");
        } catch (IOException e) {
            System.out.println("Error al escribir archivo: " + e.getMessage());
        }
    }    
}
