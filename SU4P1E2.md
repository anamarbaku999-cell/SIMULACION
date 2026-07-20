# Ejemplo 2: Simulación de Líneas de Espera (Recurso Compartido M/M/1)

Simulación clásica de teoría de colas donde múltiples clientes compiten por un único servidor (recurso limitado).

python
import simpy
import random

def cliente(env, nombre, servidor):
    print(f"--> {nombre} llega a la fila en el tiempo {env.now:.2f}")
    
    # Solicitar el uso del recurso (servidor)
    with servidor.request() as peticion:
        yield peticion
        print(f"=== {nombre} empieza a ser atendido en el tiempo {env.now:.2f}")
        
        # Tiempo de servicio exponencial (ej. media de 4 minutos)
        tiempo_servicio = random.expovariate(1.0 / 4.0)
        yield env.timeout(tiempo_servicio)
        print(f"<-- {nombre} sale del sistema en el tiempo {env.now:.2f}")

def generador_clientes(env, servidor):
    i = 0
    while True:
        i += 1
        env.process(cliente(env, f"Cliente {i}", servidor))
        # Tiempo entre llegadas exponencial (ej. cada 3 minutos)
        tiempo_llegada = random.expovariate(1.0 / 3.0)
        yield env.timeout(tiempo_llegada)

env = simpy.Environment()
caja = simpy.Resource(env, capacity=1) # 1 solo servidor disponible
env.process(generador_clientes(env, caja))

# Simular las primeras 2 horas (120 minutos)
env.run(until=120)
