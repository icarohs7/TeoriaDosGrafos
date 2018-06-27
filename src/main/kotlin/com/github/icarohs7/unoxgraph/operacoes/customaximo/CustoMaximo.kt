package com.github.icarohs7.unoxgraph.operacoes.customaximo

import com.github.icarohs7.unoxgraph.Grafo
import com.github.icarohs7.unoxgraph.extensoes.entradasParaOVertice
import com.github.icarohs7.unoxgraph.operacoes.ordemtopologica.khan
import kotlin.math.max

object CustoMaximo {
	
	@Suppress("LocalVariableName")
	fun calcular(grafo: Grafo): Int {
		// Ordenar topológicamente
		val V = grafo.ordemTopologica.khan()
		
		// Função recursiva para cálculo da maior distância de um vértice comparando todos caminhos possíveis
		fun dist(v: Int): Int {
			return if ((grafo entradasParaOVertice v).isEmpty())
				0
			else
				1 + (grafo entradasParaOVertice v).fold(0) { acc, i -> max(acc, dist(i)) }
		}
		
		// Returnar max(dist(v))
		return V.fold(0) { acc, v -> max(acc, dist(v)) }
	}
}