package SIMULACION;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
public class SU1P4E1 {
public static void main(String[] args) {

        int semilla = 7326;
        int a = 17;
        int b = 9;
        int M = 4096;
        
        List<Integer> numeroGenerados = new ArrayList<>();
        int actual = semilla;
        
        System.out.println("=== INICIANDO GENERACIÓN DE " + M + " NÚMEROS PSEUDOALEATORIOS ===");
        

        for (int i = 0; i < M; i++) {
            int siguiente = (a * actual + b) % M;
            numeroGenerados.add(siguiente);
            actual = siguiente;
        }
        

        boolean tieneDuplicados = false;
        List<Integer> verificador = new ArrayList<>();
        for (int num : numeroGenerados) {
            if (verificador.contains(num)) {
                tieneDuplicados = true;
                break;
            }
            verificador.add(num);
        }
        
        System.out.println("\n--> VALIDACIÓN ESTADÍSTICA DE LA CORRIDA:");
        System.out.println("    ¿Tiene números repetidos antes de terminar el periodo?: " + (tieneDuplicados ? "SÍ (Ajustar constantes)" : "NO (Periodo Completo Exitoso)"));
        System.out.println("    Total de números únicos en la serie: " + verificador.size());

        System.out.println("\nMUESTRA INICIAL DE LA SERIE:");
        for (int i = 0; i < 15; i++) {
            System.out.printf("X_%d = %d (Fracción: %.4f)%n", (i + 1), numeroGenerados.get(i), (double)numeroGenerados.get(i)/M);
        }

        String nombreArchivo = "RSU1P4E1.txt";
        try (FileWriter fw = new FileWriter(nombreArchivo);
             PrintWriter pw = new PrintWriter(fw)) {
            
            pw.println("==========================================================");
            pw.println("   REPORTE DE GENERADOR DE NÚMEROS PSEUDOALEATORIOS");
            pw.println("==========================================================");
            pw.println("PARAMETROS UTILIZADOS:");
            pw.println("-> Semilla Inicial (X0): " + semilla);
            pw.println("-> Constante Multiplicativa (a): " + a);
            pw.println("-> Constante Aditiva (b): " + b);
            pw.println("-> Modulo Seleccionado (M): " + M);
            pw.println("-> Tipo de Periodo: Completo (4096 valores unicos)");
            pw.println("==========================================================");
            pw.println(String.format("| %-6s | %-12s | %-15s |", "i", "Valor (X_i)", "Num. Aleatorio (r_i)"));
            pw.println("----------------------------------------------------------");
            
            for (int i = 0; i < numeroGenerados.size(); i++) {
                int valor = numeroGenerados.get(i);
                double r_i = (double) valor / M; 
                pw.println(String.format("| %-6d | %-12d | %-20.6f |", (i + 1), valor, r_i));
            }
            
            pw.println("----------------------------------------------------------");
            pw.println("FIN DEL REPORTE - GENERACIÓN EXITOSA.");
            
            System.out.println("\n[ÉXITO] Archivo '" + nombreArchivo + "' generado correctamente en la carpeta raíz del proyecto.");
            
        } catch (IOException e) {
            System.out.println("\n[ERROR] No se pudo escribir el archivo de reporte: " + e.getMessage());
        }
    }    
}
