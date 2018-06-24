package com.github.icarohs7.unoxgraph.operacoes.custominimo

import java.util.Arrays

@Suppress("MemberVisibilityCanBePrivate")
data class ResultadoPonderado(val distancias: DoubleArray, val predecessores: IntArray) {
	
	fun buscar(destino: Int): List<Int> {
		/* Variável contendo o próximo vértice */
		var i = destino - 1
		/* Variável que armazenará o menor caminho da
		 * origem ao destino */
		val caminho = arrayListOf<Int>()
		while (i != 0) {
			/* Enquanto o vértice origem não for alcançado
			 * apartir do destino, adicionar o precedente do vértice
			 * e marcá-lo como atual na próxima iteração*/
			caminho.add(i + 1)
			i = predecessores[i]
		}
		/* Por fim, adicionar a origem ao caminho */
		caminho.add(1)
		/* Retornar o caminho invertido */
		return caminho.reversed()
	}
	
	/**
	 * Implementação da comparação de igualdade entre 2 objetos ResultadoPonderado
	 * @param other Any?
	 * @return Boolean
	 */
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false
		
		other as ResultadoPonderado
		
		if (!Arrays.equals(distancias, other.distancias)) return false
		if (!Arrays.equals(predecessores, other.predecessores)) return false
		
		return true
	}
	
	/**
	 * Implementação do hashing de um objeto filho da classe atual baseado em seus 2 atributos
	 * @return Int
	 */
	override fun hashCode(): Int {
		var result = Arrays.hashCode(distancias)
		result = 31 * result + Arrays.hashCode(predecessores)
		return result
	}
}