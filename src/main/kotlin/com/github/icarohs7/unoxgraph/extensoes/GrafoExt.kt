package com.github.icarohs7.unoxgraph.extensoes

import com.github.icarohs7.unoxgraph.Aresta
import com.github.icarohs7.unoxgraph.Grafo

/**
 * Adicionar uma aresta ao grafo
 */
operator fun Grafo.plusAssign(aresta: Aresta) = addAresta(aresta)

operator fun Grafo.plusAssign(aresta: Pair<Int, Int>) = addAresta(aresta.first, aresta.second)

/**
 * Remover uma aresta do grafo
 */
operator fun Grafo.minusAssign(aresta: Aresta) = excluirAresta(aresta)

operator fun Grafo.minusAssign(aresta: Pair<Int, Int>) = excluirAresta(aresta.first, aresta.second)

/**
 * Retorna a lista de vértices incidindo sobre o vértice atual
 */
infix fun Grafo.entradasParaOVertice(v: Int) = arestas.filter { aresta -> aresta.destino == v }.map { aresta -> aresta.origem }
