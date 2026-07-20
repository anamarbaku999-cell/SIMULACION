# Ejemplo 2: Simulación de Líneas de Espera (Recurso Compartido M/M/1)

Este ejemplo modela una situación clásica de teoría de colas (M/M/1), donde múltiples clientes llegan de forma aleatoria a un banco y compiten por ser atendidos por un único cajero (recurso limitado con capacidad = 1).

## Código de la Simulación

```python```
import simpy
import random

### Configuración de variables aleatorias
RANDOM_SEED = 42
NUM_CAJEROS = 1
TIEMPO_PROMEDIO_SERVICIO = 4.0  # Minutos que tarda el cajero en atender
TIEMPO_PROMEDIO_LLEGADA = 3.0   # Minutos entre la llegada de cada cliente
TIEMPO_SIMULACION = 120         # Duración de la simulación en minutos

def cliente(env, nombre, banco):
    """Proceso que representa el comportamiento de un cliente."""
    print(f"--> {nombre} llega a la fila en el tiempo {env.now:.2f}")
    
### El cliente solicita el recurso (cajero)
    with banco.request() as peticion:
### Espera su turno si el cajero está ocupado
        yield peticion
        
        print(f"=== {nombre} empieza a ser atendido en el tiempo {env.now:.2f}")
### Genera un tiempo de servicio exponencial
        tiempo_servicio = random.expovariate(1.0 / TIEMPO_PROMEDIO_SERVICIO)
        yield env.timeout(tiempo_servicio)
        
        print(f"<-- {nombre} termina su trámite y sale en el tiempo {env.now:.2f}")

def generador_clientes(env, banco):
    """Proceso que genera clientes de forma continua según una distribución de Poisson."""
    contador_clientes = 0
    while True:
        contador_clientes += 1
### Crea e inicia el proceso individual para el nuevo cliente
        env.process(cliente(env, f"Cliente {contador_clientes}", banco))
        
### Genera el tiempo de espera hasta la llegada del siguiente cliente
        tiempo_llegada = random.expovariate(1.0 / TIEMPO_PROMEDIO_LLEGADA)
        yield env.timeout(tiempo_llegada)

### Configuración e inicio del entorno de SimPy
print("--- Iniciando Simulación de Líneas de Espera ---")
random.seed(RANDOM_SEED)

env = simpy.Environment()
### Definición del recurso compartido (Cajero)
cajero = simpy.Resource(env, capacity=NUM_CAJEROS)

### Registrar el proceso generador en el entorno
env.process(generador_clientes(env, cajero))

### Ejecutar la simulación
env.run(until=TIEMPO_SIMULACION)
print("--- Fin de la Simulación ---")
