package com.github.icarohs7.unoxgraph

import com.github.icarohs7.unoxcommons.estatico.MatrizDouble
import com.github.icarohs7.unoxcommons.extensoes.preenchendoArrayBooleanDeTamanho
import com.github.icarohs7.unoxgraph.estatico.ForaDoGrafoException
import com.github.icarohs7.unoxgraph.estatico.INFINITO

abstract class Grafo
protected constructor(val matrizDeAdjacencia: MatrizDouble, val direcionado: Boolean) {
	
	val visitados = false preenchendoArrayBooleanDeTamanho matrizDeAdjacencia.size
	
	val arestas: List<Aresta>
		get() = mutableListOf<Aresta>().also { arestas ->
			matrizDeAdjacencia.indices.forEach { i ->
				matrizDeAdjacencia[0].indices.forEach { j ->
					val aresta = Aresta(i, j, matrizDeAdjacencia[i][j])
					if (aresta.peso != INFINITO && !arestas.contains(aresta)) {
						arestas.add(Aresta(i, j, matrizDeAdjacencia[i][j]))
					}
				}
			}
		}
	
	val vertices: List<Int>
		get() = matrizDeAdjacencia
			.indices
			.filter { vertice -> vertice in arestas.fold(listOf<Int>()) { acc, aresta -> acc + aresta.expandirVertices() } }
	
	val ordemTopologica = OrdemTopologica()
	
	val custoMaximo = CustoMaximo()
	
	fun inicializar() = visitados.indices.forEach { vertice -> visitados[vertice] = false }
	
	fun existeAberto() = visitados.reduce { acc, b -> acc || b }
	
	fun getVizinhos(vertice: Int, abertos: Boolean = false): List<Int> {
		val vizinhos = (0 until matrizDeAdjacencia.size)
			.filter { matrizDeAdjacencia[vertice][it] != INFINITO }
		return if (abertos) vizinhos.filter { !visitados[it] } else vizinhos
	}
	
	fun addAresta(origem: Int, destino: Int, peso: Double = 0.0) {
		if (peso == INFINITO) return
		try {
			matrizDeAdjacencia[origem][destino] = peso
			if (!direcionado) {
				matrizDeAdjacencia[destino][origem] = peso
			}
		} catch (e: IndexOutOfBoundsException) {
			throw ForaDoGrafoException()
		}
		
	}
	
	fun addAresta(aresta: Aresta) = addAresta(aresta.origem, aresta.destino, aresta.peso)
	
	fun excluirAresta(origem: Int, destino: Int) {
		matrizDeAdjacencia[origem][destino] = INFINITO
		if (!direcionado) {
			matrizDeAdjacencia[destino][origem] = INFINITO
		}
	}
	
	fun excluirAresta(aresta: Aresta) = excluirAresta(aresta.origem, aresta.destino)
	
	override fun equals(other: Any?): Boolean {
		return when {
			this === other -> true
			other == null -> false
			this is GrafoPonderado && other !is GrafoPonderado -> false
			this is GrafoNaoPonderado && other !is GrafoNaoPonderado -> false
			
			else -> {
				val m1 = this.matrizDeAdjacencia
				val m2 = (other as Grafo).matrizDeAdjacencia
				
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
	
	override fun hashCode() = matrizDeAdjacencia.hashCode() + direcionado.hashCode()
	
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
