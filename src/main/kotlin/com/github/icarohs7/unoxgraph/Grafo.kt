package com.github.icarohs7.unoxgraph

import com.github.icarohs7.unoxgraph.estatico.ForaDoGrafoException
import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxkcommons.estatico.MatrizDouble
import com.github.icarohs7.unoxkcommons.extensoes.deepForEachIndexed
import com.github.icarohs7.unoxkcommons.extensoes.preenchendoArrayBooleanDeTamanho

abstract class Grafo
protected constructor(val matrizAdjacencia: MatrizDouble, val direcionado: Boolean) {
	
	val visitados = false preenchendoArrayBooleanDeTamanho matrizAdjacencia.size
	
	val arestas: List<Aresta>
		get() {
			val arestas = mutableListOf<Aresta>()
			matrizAdjacencia.deepForEachIndexed { row, col, peso -> if (peso != INFINITO) arestas += Aresta(row, col, peso) }
			return arestas.distinct()
		}
	
	val vertices: List<Int>
		get() = matrizAdjacencia.indices
			.filter { vertice -> vertice in arestas.fold(listOf<Int>()) { acc, aresta -> acc + aresta.expandirVertices() } }
	
	val ordemTopologica = OrdemTopologica()
	
	val custoMaximo = CustoMaximo()
	
	fun inicializar() = visitados.fill(false)
	
	fun existeAberto() = visitados.reduce { acc, b -> acc || b }
	
	fun getVizinhos(vertice: Int, abertos: Boolean = false): List<Int> {
		val vizinhos = (0 until matrizAdjacencia.size)
			.filter { matrizAdjacencia[vertice][it] != INFINITO }
		return if (abertos) vizinhos.filter { !visitados[it] } else vizinhos
	}
	
	fun addAresta(origem: Int, destino: Int, peso: Double = 0.0) {
		if (peso == INFINITO) return
		try {
			matrizAdjacencia[origem][destino] = peso
			if (!direcionado) matrizAdjacencia[destino][origem] = peso
		} catch (e: IndexOutOfBoundsException) {
			throw ForaDoGrafoException()
		}
	}
	
	fun addAresta(aresta: Aresta) = addAresta(aresta.origem, aresta.destino, aresta.peso)
	
	fun excluirAresta(origem: Int, destino: Int) {
		matrizAdjacencia[origem][destino] = INFINITO
		if (!direcionado) matrizAdjacencia[destino][origem] = INFINITO
	}
	
	fun excluirAresta(aresta: Aresta) = excluirAresta(aresta.origem, aresta.destino)
	
	override fun equals(other: Any?): Boolean {
		return when {
			this === other -> true
			other == null -> false
			this is GrafoPonderado && other !is GrafoPonderado -> false
			this is GrafoNaoPonderado && other !is GrafoNaoPonderado -> false
			
			else -> {
				val m1 = this.matrizAdjacencia
				val m2 = (other as Grafo).matrizAdjacencia
				
				if (m1.size != m2.size) return false
				else if (m1[0].size != m2[0].size) return false
				
				for (i in 0 until m1.size) {
					for (j in 0 until m1[0].size) {
						if (m1[i][j] != m2[i][j]) return false
					}
				}
				
				if (this.direcionado != other.direcionado) return false
				
				true
			}
		}
	}
	
	override fun hashCode() = matrizAdjacencia.hashCode() + direcionado.hashCode()
	
	/**
	 * Classe agrupando os algoritmos de ordenação topológica
	 * @constructor
	 */
	inner class OrdemTopologica {
		
		val grafo: Grafo = this@Grafo
	}
	
	/**
	 * Classe agrupando os algoritmos de caminho máximo
	 * @constructor
	 */
	inner class CustoMaximo {
		
		val grafo: Grafo = this@Grafo
	}
}
