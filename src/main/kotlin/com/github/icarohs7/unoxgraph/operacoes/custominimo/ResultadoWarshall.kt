package com.github.icarohs7.unoxgraph.operacoes.custominimo

import com.github.icarohs7.unoxcommons.estatico.MatrizDouble
import com.github.icarohs7.unoxcommons.estatico.MatrizInteira
import java.util.Arrays

data class ResultadoWarshall(val distancias: MatrizDouble, val proximos: MatrizInteira) {
	
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
	
	/**
	 * Implementação da comparação de igualdade entre 2 objetos ResultadoPonderado
	 * @param other Any?
	 * @return Boolean
	 */
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false
		
		other as ResultadoWarshall
		
		if (!Arrays.equals(distancias, other.distancias)) return false
		if (!Arrays.equals(proximos, other.proximos)) return false
		
		return true
	}
	
	/**
	 * Implementação do hashing de um objeto filho da classe atual baseado em seus 2 atributos
	 * @return Int
	 */
	override fun hashCode(): Int {
		var result = Arrays.hashCode(distancias)
		result = 31 * result + Arrays.hashCode(proximos)
		return result
	}
	
}