package com.github.icarohs7.unoxgraph.operacoes.customaximo

import com.github.icarohs7.unoxgraph.Grafo
import com.github.icarohs7.unoxgraph.extensoes.entradasParaOVertice
import com.github.icarohs7.unoxgraph.operacoes.ordemtopologica.khan

object CaminhoMaximo {
	
	@Suppress("LocalVariableName")
	fun calcular(grafo: Grafo): List<Int> {
		/* Ordenar topológicamente */
		val V = grafo.ordemTopologica.khan()
		
		/* Função recursiva para cálculo da maior distância de um vértice */
		fun dist(v: Int): List<Int> {
			return if ((grafo entradasParaOVertice v).isEmpty()) {
				mutableListOf(v)
			} else {
				return mutableListOf(v) +
						(grafo entradasParaOVertice v).fold<Int, List<Int>>(emptyList()) { acc, i ->
							val a = dist(i)
							if (a.size > acc.size) a else acc
						}
			}
		}
		
		/* Gerar lista de caminho máximo */
		val res: List<Int> = V.fold(emptyList()) { acc, i ->
			val a = dist(i)
			if (a.size > acc.size) a else acc
		}
		
		/* Retornar a lista contento o caminho máximo corrigida */
		return res.drop(1)
	}
}