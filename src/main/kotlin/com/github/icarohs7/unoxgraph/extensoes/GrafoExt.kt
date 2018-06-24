package com.github.icarohs7.unoxgraph.extensoes

import com.github.icarohs7.unoxgraph.Aresta
import com.github.icarohs7.unoxgraph.Grafo

/**
 * Adicionar uma aresta ao grafo
 */
operator fun Grafo.plusAssign(aresta: Aresta) = addAresta(aresta)

/**
 * Adicionar uma aresta ao grafo --***1..N***
 */
operator fun Grafo.plusAssign(inOut: Pair<Int, Int>) = addAresta(inOut.first, inOut.second)

/**
 * Remover uma aresta do grafo
 */
operator fun Grafo.minusAssign(aresta: Aresta) = excluirAresta(aresta)

/**
 * Remover uma aresta do grafo --***1..N***
 */
operator fun Grafo.minusAssign(inOut: Pair<Int, Int>) = excluirAresta(inOut.first, inOut.second)
