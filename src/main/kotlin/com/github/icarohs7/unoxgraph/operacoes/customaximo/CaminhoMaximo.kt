package com.github.icarohs7.unoxgraph.operacoes.customaximo

import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxgraph.operacoes.ordemtopologica.ordemTopologicaKhan

/**
 * Injetar função de cálculo de caminho máximo
 * na classe grafo
 */
fun Grafo.caminhoMaximo(): List<Int> {
	return calcularCaminhoMaximo(this)
}

private fun calcularCaminhoMaximo(grafo: Grafo): List<Int> {
	/* Ordenar topológicamente */
	val verticesOrdenados = grafo.ordemTopologicaKhan()
	
	/* Função recursiva para cálculo da maior distância de um vértice */
	fun dist(v: Int): List<Int> {
		return when {
			grafo.entradasParaOVertice(v).isEmpty() -> mutableListOf(v)
			
			else -> {
				mutableListOf(v) +
						grafo.entradasParaOVertice(v).fold<Int, List<Int>>(emptyList()) { acc, i ->
							val a = dist(i)
							if (a.size > acc.size) a else acc
						}
			}
		}
	}
	
	/* Gerar lista de caminho máximo */
	val res: List<Int> = verticesOrdenados.fold(emptyList()) { acc, i ->
		val a = dist(i)
		if (a.size > acc.size) a else acc
	}
	
	/* Retornar a lista contento o caminho máximo corrigida */
	return res.drop(1)
}