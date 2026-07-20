# Ejemplo 3: Gestión de Interrupciones en Procesos

Se modela una situación del mundo real donde un proceso de producción en marcha se ve interrumpido abruptamente por un evento prioritario externo o una falla en el sistema.

## Código de la Simulación

```python
import simpy

def proceso_maquina(env):
    """Proceso principal que representa una máquina trabajando."""
    try:
        print(f"[Máquina] Inicia ciclo de trabajo largo en el tiempo {env.now}")
        # La máquina intenta operar de forma continua por 10 unidades de tiempo
        yield env.timeout(10)
        print(f"[Máquina] Ciclo de trabajo completado con éxito en el tiempo {env.now}")
    except simpy.Interrupt as interrupcion:
        # Bloque que se ejecuta únicamente si ocurre una interrupción externa
        print(f"[¡ALERTA!] Operación abortada en el tiempo {env.now}")
        print(f"[¡ALERTA!] Causa del paro: {interrupcion.cause}")

def simulador_falla(env, maquina_a_interrumpir):
    """Proceso externo que genera una falla inesperada en el sistema."""
    # Espera 4 unidades de tiempo antes de detonar el problema
    yield env.timeout(4)
    print(f"[Sistema] Se ha detectado una anomalía crítica en el hardware.")
    # Interrumpe el proceso de la máquina enviando un mensaje con la causa
    maquina_a_interrumpir.interrupt("Fallo por sobrecalentamiento eléctrico")

# Configuración del entorno de SimPy
print("--- Iniciando Simulación con Interrupciones ---")
env = simpy.Environment()

// Registrar e iniciar el proceso de la máquina
proceso_activo = env.process(proceso_maquina(env))

// Registrar e iniciar el proceso que provocará la falla
env.process(simulador_falla(env, proceso_activo))

# Ejecutar la simulación completa
env.run()
print("--- Fin de la Simulación ---")
```
