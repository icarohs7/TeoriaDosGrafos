package teoriadosgrafos.extensoes

import teoriadosgrafos.Grafo

/**
 * Retorna o número de arestas de entradas para um determinado vértice
 * --**Utiliza vértices iniciando de 0 a N**
 * @receiver Int
 * @param grafo Grafo
 * @return Int
 */
fun Int.getEntradas(grafo: Grafo) = grafo.arestas.count { aresta -> aresta.destino == this }