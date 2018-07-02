package com.github.icarohs7.unoxgraph.extensoes

import com.github.icarohs7.unoxgraph.grafos.Grafo.Aresta

/**
 * Atribuir um limite de fluxo a uma aresta
 */
fun Aresta.comFluxo(fluxo: Double): Pair<Aresta, Double> {
	return this to fluxo
}