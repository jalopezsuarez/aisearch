# AI-Search 
AI-Search: Software de animación de algoritmos de Inteligencia Artificial

## Inteligencia Artificial y Sistemas Expertos
Esta aplicación esta diseñada para ayudar en el desarrollo e investigación en el área de Inteligencia Artificial y Sistemas Expertos. La aplicación se basa en un software que realiza el recorrido mediante animaciónes en una interfaz con el fín de analizar detalladamente la ejecución y el funcionamiento de los principales algoritmos de Inteligencia Artificial.

### Algoritmos de Inteligencia Artificial Desarrollados:
- Breadth-First Search 
- Depth-First Search 
- Simple Hill Climbing 
- Steepest Ascent Hill Climbing 
- Best First Search 
- A* Search

### Grafos para AI-Search 
La aplicación admite un formato determinado de archivos donde se especifican los grafos, utilizando archivos de texto plano. Mediante el archivo se van definiendo los nodos, enlaces sucesores, etc. De tal manera que finalmente se dispone de un archivo completo con el que definir y cargar los grafos en la aplicación.

| Variable Ejemplo  | Valor Ejemplo |
|  |  |
| Nodos_Totales | 3  |  
|  |  |
| Nombre_Nodo   | A | 
| PosicionX,PosicionY   | 100, 40  | 
| Valor_Nodo   | 10 |
| Es_Objetivo {seleccionar SI o NO}   | NO |
| Numeros_Sucesores   | 2  |
| Sucesores_Nombre,Coste_Arco   | B,10  |
| Sucesores_Nombre,Coste_Arco   | C,5  |
|  |  |
| Nombre_Nodo   | B | 
| PosicionX,PosicionY   | 60, 120  | 
| Valor_Nodo   | 20 |
| Es_Objetivo {seleccionar SI o NO}   | NO |
| Numeros_Sucesores   | 0  |
|  |  |
| Nombre_Nodo   | C | 
| PosicionX,PosicionY   | 120, 150  | 
| Valor_Nodo   | 15 |
| Es_Objetivo {seleccionar SI o NO}   | SI |
| Numeros_Sucesores   | 0  |

### Funcionalidades Destacadas
- Apertura del grafo mediante menú Abrir.
- Se adjuntan diversos grafos de ejemplo para probar la efectividad de cada algoritmo.
- Inclusión de las estadísticas en el desarrollo y finalización de la ejecución del algoritmo.
- Opción de ejecutar paso a paso o modo temporizador pudiendo determina la velocidad del temporizador.
- Implementación del algoritmo A* completo, incluyendo tratamiento de nodos cerrados para la búsqueda del camino mas optimo.

## Support & Contributing

If you need any help with AI-Search, you can reach out to us via the GitHub Issues page at:
<code>[https://github.com/jalopezsuarez/aisearch/issues](https://github.com/jalopezsuarez/aisearch/issues)</code>

Keep track of development and community updates. Pull requests are welcome!

## Copyright and License

Code and documentation copyright 2006-2015. Code released under [the MIT license](https://github.com/jalopezsuarez/aisearch/blob/master/aisearch/LICENSE).
