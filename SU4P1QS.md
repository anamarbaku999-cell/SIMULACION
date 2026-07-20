# ¿Qué es la librería SimPy?

*SimPy* es un framework (biblioteca) de simulación de eventos discretos. El comportamiento de los componentes activos (como vehículos, clientes o mensajes) se modela con procesos . Todos los procesos existen en un entorno . Interactúan con el entorno y entre sí a través de eventos. Los procesos se describen mediante generadores simples de Python . Se les puede llamar función de proceso o método de proceso , según se trate de una función o método normal de una clase. Durante su ciclo de vida, crean eventos y *yield* los almacenan para esperar a que ocurran.

Cuando un proceso genera un evento, se suspende . SimPy reanuda el proceso cuando se produce el evento (decimos que el evento se procesa ). Varios procesos pueden esperar el mismo evento. SimPy los reanuda en el mismo orden en que lo generaron.

Un tipo de evento importante es el *Timeout*. Los eventos de este tipo ocurren (se procesan) después de que ha transcurrido una cierta cantidad de tiempo (simulado). Permiten que un proceso se duerma (o mantenga su estado) durante el tiempo especificado. Un *Timeouty* todos los demás eventos se pueden crear llamando al método apropiado del Environmenten el que reside el proceso ( *Environment.timeout()* por ejemplo ).

## Conceptos Clave de SimPy

1.  *Environment (Entorno - simpy.Environment):* Es el motor central de la simulación. Gestiona el reloj de simulación y la ejecución de los eventos programados en orden cronológico.
2.  *Processes (Procesos):* Son funciones generadoras de Python (def con sentencias yield). Representan a los actores activos del sistema (ej. clientes, vehículos, tareas).
3.  *Events (Eventos):* Son desencadenadores que controlan el flujo temporal. Un proceso puede esperar a que un evento ocurra mediante yield event. El evento más común es env.timeout(delay), que avanza el reloj de la simulación.
4.  *Resources (Recursos - simpy.Resource):* Representan elementos con capacidad limitada que los procesos solicitan y liberan (ej. cajeros de un banco, servidores, montacargas). Si el recurso está lleno, el proceso se forma en una cola automáticamente.
