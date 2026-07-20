# Ejemplo 3: Simulación Avanzada con Interrupciones de Procesos

Modelado de una actividad que se ve interrumpida abruptamente por un evento prioritario externo (ej. una máquina que falla a mitad de una producción).

python
import simpy

def proceso_principal(env):
    try:
        print(f"Proceso principal en marcha en el tiempo {env.now}")
        # Intenta trabajar durante 10 unidades de tiempo
        yield env.timeout(10)
        print(f"Proceso principal completado con éxito en el tiempo {env.now}")
    except simpy.Interrupt as interrupcion:
        print(f"¡ALERTA! El proceso fue interrumpido en el tiempo {env.now} debido a: {interrupcion.cause}")

def generador_fallo(env, proceso_a_interrumpir):
    # Espera 4 unidades de tiempo antes de lanzar una falla
    yield env.timeout(4)
    proceso_a_interrumpir.interrupt("Fallo eléctrico en el sistema")

env = simpy.Environment()
proceso_activo = env.process(proceso_principal(env))
env.process(generador_fallo(env, proceso_activo))

env.run()
