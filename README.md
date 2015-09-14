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

#### Como ejemplo se muestra una tabla donde se define un grafo con tres nodos enlazados:

| Variable Descripción | Definición Fichero |
| ------------- | ----------- |
| NodosTotales | 3 |
| NombreNodo | A | 
| PosicionX,PosicionY   | 100, 40  | 
| ValorNodo | 10 |
| EsObjetivo {seleccionar SI o NO}   | NO |
| NumeroSucesores  | 2 |
| NombreSucesores,CosteArco   | B,10 |
| NombreSucesores,CosteArco   | C,5 |
| NombreNodo | B | 
| PosicionX,PosicionY   | 60, 120 | 
| ValorNodo  | 20 |
| EsObjetivo {seleccionar SI o NO}   | NO |
| NumeroSucesores   | 0 |
| NombreNodo | C | 
| PosicionX,PosicionY   | 120, 150  | 
| ValorNodo | 15 |
| EsObjetivo {seleccionar SI o NO}   | SI |
| NumeroSucesores | 0 |

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
