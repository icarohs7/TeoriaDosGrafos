package com.github.icarohs7.unoxgraph.extensoes

import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxgraph.grafos.Grafo.Aresta
import com.github.icarohs7.unoxkcommons.tipos.NXCell

/**
 * Adicionar uma aresta ao grafo
 */
operator fun Grafo.plusAssign(aresta: Aresta) = addAresta(aresta)

operator fun Grafo.plusAssign(aresta: Pair<Int, Int>) = addAresta(aresta.first, aresta.second)

operator fun Grafo.plusAssign(celula: NXCell<Double>) = addAresta(celula.row, celula.col, celula.value)

/**
 * Remover uma aresta do grafo
 */
operator fun Grafo.minusAssign(aresta: Aresta) = excluirAresta(aresta)

operator fun Grafo.minusAssign(aresta: Pair<Int, Int>) = excluirAresta(aresta.first, aresta.second)

operator fun Grafo.minusAssign(celula: NXCell<Double>) = excluirAresta(celula.row, celula.col)

/**
 * Retorna a lista de vértices incidindo sobre o vértice atual
 */
internal fun Grafo.entradasParaOVertice(v: Int) = arestas.filter { it.destino == v }.map { it.origem }
