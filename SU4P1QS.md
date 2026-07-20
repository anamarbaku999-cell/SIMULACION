# ¿Qué es la librería SimPy?

*SimPy* es un framework de simulación basado en eventos discretos (DES) para Python. Permite modelar sistemas del mundo real donde los cambios de estado ocurren en momentos específicos del tiempo debido a la ocurrencia de eventos (como la llegada de un cliente, el fallo de una máquina o la finalización de un servicio).

## Conceptos Clave

1.  *Environment (Entorno - simpy.Environment):* Es el motor central de la simulación. Gestiona el reloj de simulación y la ejecución de los eventos programados en orden cronológico.
2.  *Processes (Procesos):* Son funciones generadoras de Python (def con sentencias yield). Representan a los actores activos del sistema (ej. clientes, vehículos, tareas).
3.  *Events (Eventos):* Son desencadenadores que controlan el flujo temporal. Un proceso puede esperar a que un evento ocurra mediante yield event. El evento más común es env.timeout(delay), que avanza el reloj de la simulación.
4.  *Resources (Recursos - simpy.Resource):* Representan elementos con capacidad limitada que los procesos solicitan y liberan (ej. cajeros de un banco, servidores, montacargas). Si el recurso está lleno, el proceso se forma en una cola automáticamente.
