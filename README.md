# AI-Search 
Software de animación de algoritmos de Inteligencia Artificial

Inteligencia Artificial y Sistemas Expertos

AI-Search: Software de animación de algoritmos de Inteligencia Artificial

Practicas para la asignatura de Inteligencia Artificial y Sistemas Expertos.
Este software de animación de algoritmos implementa una interfaz para poder entender el funcionamiento de los principales algoritmos de Inteligencia Artificial.

Se implementan los siguientes algoritmos:
•	Breadth-First Search 
•	Depth-First Search 
•	Simple Hill Climbing 
•	Steepest Ascent Hill Climbing 
•	Best First Search 
•	A* Search

Grafos para AI-Search 
El programa admite un formato determinado de archivos donde se especifican los grafos, utilizando archivos de texto plano.
Formato de archivo	Código de ejemplo	Imagen de ejemplo
Nodos_Totales
Nombre_Nodo
PosicionX,PosicionY
Valor_Nodo
Es_Objetivo {seleccionar SI o NO}
Numeros_Sucesores
Sucesores_Nombre,Coste_Arco
Sucesores_Nombre,Coste_Arco
[...] 	3
A
100,40
10
NO
2
B,10
C,5
B
60,120
20
NO
0
C
120,150
15
SI
0	 
Mejoras:
He implementado diversas mejoras para el desarrollo del programa:
Se implementan entre otras, las siguientes mejoras
•	Apertura del grafo mediante menú Abrir.
•	Se adjuntan diversos grafos de ejemplo para probar la efectividad de cada algoritmo.
•	Inclusión de las estadísticas en el desarrollo y finalización de la ejecución del algoritmo.
•	Opción de ejecutar paso a paso o modo temporizador pudiendo determina la velocidad del temporizador.
•	Implementación del algoritmo A* completo, incluyendo tratamiento de nodos cerrados para la búsqueda del camino mas optimo.
