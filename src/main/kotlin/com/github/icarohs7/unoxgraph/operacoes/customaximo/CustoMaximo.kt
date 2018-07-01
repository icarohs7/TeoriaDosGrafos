package com.github.icarohs7.unoxgraph.operacoes.customaximo

import com.github.icarohs7.unoxgraph.extensoes.entradasParaOVertice
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxgraph.operacoes.ordemtopologica.ordemTopologicaKhan
import kotlin.math.max

/**
 * Injetar a função de cálculo de custo máximo
 * na classe grafo
 */
fun Grafo.custoMaximo(): Int {
	return calcularCustoMaximo(this)
}

private fun calcularCustoMaximo(grafo: Grafo): Int {
	// Ordenar topológicamente
	val verticesOrdenados = grafo.ordemTopologicaKhan()
	
	// Função recursiva para cálculo da maior distância de um vértice comparando todos caminhos possíveis
	fun dist(v: Int): Int {
		return when (grafo.entradasParaOVertice(v).isEmpty()) {
			true -> 0
			false -> 1 + grafo.entradasParaOVertice(v).fold(0) { acc, i -> max(acc, dist(i)) }
		}
	}
	
	// Returnar max(dist(v))
	return verticesOrdenados.fold(0) { acc, v -> max(acc, dist(v)) }
}