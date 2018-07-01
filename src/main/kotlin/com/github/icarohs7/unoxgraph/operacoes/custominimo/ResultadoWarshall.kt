package com.github.icarohs7.unoxgraph.operacoes.custominimo

import com.github.icarohs7.unoxkcommons.estatico.DoubleMatriz
import com.github.icarohs7.unoxkcommons.estatico.IntMatriz

class ResultadoWarshall(val distancias: DoubleMatriz, val proximos: IntMatriz) {
	/**
	 * Recupera o menor caminho entre 2 vértices contido
	 * na matriz de menores distâncias do resultado
	 */
	fun buscar(origem: Int, destino: Int): List<Int> {
		val o = origem - 1
		val d = destino - 1
		if (proximos[o][d] == -1) {
			return arrayListOf()
		}
		val caminho = arrayListOf<Int>()
		caminho.add(origem)
		var i = o
		while (i != d) {
			i = proximos[i][d]
			caminho.add(i + 1)
		}
		return caminho
	}
}