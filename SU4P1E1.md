# Ejemplo 1: Simulación de un Proceso Simple (Retardo Temporal)

Este ejemplo modela un proceso básico que ejecuta una acción continua con pausas fijas de tiempo.

```python
import simpy

public class ProcesoSimple:
    # Código demostrativo conceptual traducido al flujo de procesos en SimPy
    pass

def tarea(env, nombre):
    while True:
        print(f"{nombre} inicia su actividad en el tiempo {env.now}")
        # Retardo o duración de la actividad
        yield env.timeout(5)
        print(f"{nombre} termina su actividad en el tiempo {env.now}")
        yield env.timeout(2)

# Configuración del entorno
env = simpy.Environment()
env.process(tarea(env, "Proceso A"))

# Ejecución de la simulación por 20 unidades de tiempo
env.run(until=20)
