package com.github.icarohs7.unoxgraph.extensoes

import com.github.icarohs7.unoxgraph.Grafo

/**
 * Retorna a lista de vértices incidindo sobre o vértice atual
 * @receiver Int
 * @param grafo Grafo
 * @return Int
 */
fun Int.getEntradas(grafo: Grafo) = grafo.arestas.filter { aresta -> aresta.destino == this }.map { aresta -> aresta.origem }