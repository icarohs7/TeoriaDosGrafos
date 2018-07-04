package com.github.icarohs7.unoxgraph.operacoes.redesfluxo

import com.github.icarohs7.unoxkcommons.estatico.Matriz
import java.util.Arrays

/**
 * Classe representando o resultado do algoritmo de Ford And Fulkerson,
 * contendo o fluxo máximo de um grafo e sua matriz residual
 */
data class ResultadoFulkerson(val fluxoMaximo: Double, val matrizResidual: Matriz<Double>, val iteracoes: String) {
	
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false
		
		other as ResultadoFulkerson
		
		if (fluxoMaximo != other.fluxoMaximo) return false
		if (!Arrays.equals(matrizResidual, other.matrizResidual)) return false
		if (iteracoes != other.iteracoes) return false
		
		return true
	}
	
	override fun hashCode(): Int {
		var result = fluxoMaximo.hashCode()
		result = 31 * result + Arrays.hashCode(matrizResidual)
		result = 31 * result + iteracoes.hashCode()
		return result
	}
	
}