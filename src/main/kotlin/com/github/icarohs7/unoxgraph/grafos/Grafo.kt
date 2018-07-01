package com.github.icarohs7.unoxgraph.grafos

import com.github.icarohs7.unoxgraph.estatico.ForaDoGrafoException
import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.estatico.NumeroDeVerticesInsuficienteException
import com.github.icarohs7.unoxgraph.extensoes.plusAssign
import com.github.icarohs7.unoxkcommons.estatico.Matriz
import com.github.icarohs7.unoxkcommons.extensoes.cells
import com.github.icarohs7.unoxkcommons.extensoes.para
import com.github.icarohs7.unoxkcommons.extensoes.por
import com.github.icarohs7.unoxkcommons.funcoes.matrizOf

open class Grafo private constructor() {
	
	var direcionado = true
		protected set
	
	var tamanho
		get() = matrizAdjacencia.size
		protected set(value) {
			matrizAdjacencia = matrizOf(value por value) { INFINITO }
			visitados = Array(value) { false }
		}
	
	lateinit var matrizAdjacencia: Matriz<Double>
		protected set
	
	lateinit var visitados: Array<Boolean>
		protected set
	
	val arestas: List<Aresta>
		get() = matrizAdjacencia.cells.filter { it.value != INFINITO }.map { Aresta(it.row, it.col, it.value) }.distinct()
	
	val vertices: List<Int>
		get() = (arestas.map { it.origem } + arestas.map { it.destino }).distinct().sorted()
	
	fun desmarcarTodosOsVertices() {
		visitados.fill(false)
	}
	
	fun addAresta(origem: Int, destino: Int, peso: Double = 0.0) {
		try {
			matrizAdjacencia[origem][destino] = peso
			if (!direcionado) matrizAdjacencia[destino][origem] = peso
		} catch (e: IndexOutOfBoundsException) {
			throw ForaDoGrafoException()
		}
	}
	
	fun addAresta(aresta: Aresta) {
		addAresta(aresta.origem, aresta.destino, aresta.peso)
	}
	
	fun excluirAresta(origem: Int, destino: Int) {
		matrizAdjacencia[origem][destino] = INFINITO
		if (!direcionado) matrizAdjacencia[destino][origem] = INFINITO
	}
	
	fun excluirAresta(aresta: Aresta) {
		excluirAresta(aresta.origem, aresta.destino)
	}
	
	fun hasAberto(): Boolean {
		return visitados.reduce { acc, b -> acc || b }
	}
	
	fun getVizinhos(vertice: Int, abertos: Boolean = false): List<Int> {
		val vizinhos = arestas.filter { it.origem == vertice }.map { it.destino }
		return if (abertos) vizinhos.filterNot { visitados[it] } else vizinhos
	}
	
	/**
	 * Função de comparação de 2 grafos
	 */
	override fun equals(other: Any?): Boolean {
		return when {
			this === other -> true
			other == null -> false
			this is Grafo.Ponderado && other !is Grafo.Ponderado -> false
			this is Grafo.NaoPonderado && other !is Grafo.NaoPonderado -> false
			this is Grafo.PonderadoComFluxo && other !is Grafo.PonderadoComFluxo -> false
			
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
	
	/**
	 * Função de hashing da classe
	 */
	override fun hashCode(): Int {
		return matrizAdjacencia.hashCode() + direcionado.hashCode()
	}
	
	private fun toPonderado(): Grafo.Ponderado {
		val grafo = Grafo.Ponderado()
		grafo.tamanho = this.tamanho
		grafo.direcionado = this.direcionado
		grafo.matrizAdjacencia = this.matrizAdjacencia
		return grafo
	}
	
	private fun toPonderadoComFluxo(): Grafo.PonderadoComFluxo {
		val grafo = Grafo.PonderadoComFluxo()
		grafo.tamanho = this.tamanho
		grafo.direcionado = this.direcionado
		grafo.matrizAdjacencia = this.matrizAdjacencia
		return grafo
	}
	
	private fun toNaoPonderado(): Grafo.NaoPonderado {
		val grafo = Grafo.NaoPonderado()
		grafo.tamanho = this.tamanho
		grafo.direcionado = this.direcionado
		grafo.matrizAdjacencia = this.matrizAdjacencia
		return grafo
	}
	
	/**
	 * Funções factory
	 */
	companion object {
		
		/**
		 * Criar um grafo a partir de um tamanho
		 */
		private fun ofASize(tamanho: Int, direcionado: Boolean): Grafo {
			return Grafo().apply {
				this.tamanho = tamanho
				this.direcionado = direcionado
			}
		}
		
		/**
		 * Criar um grafo a partir da matriz de adjacência
		 */
		private fun fromTheMatrix(matrizAdjacencia: Matriz<Double>, direcionado: Boolean): Grafo {
			return Grafo().apply {
				tamanho = matrizAdjacencia.size
				this.direcionado = direcionado
				this.matrizAdjacencia = matrizOf(tamanho por tamanho) { matrizAdjacencia[it.row][it.col] }
			}
		}
		
		/**
		 * Criar um grafo a partir de um caminho composto por uma lista de vértices inteiros
		 */
		private fun withThePath(vararg vertices: Int, direcionado: Boolean): Grafo {
			if (vertices.size < 2) throw NumeroDeVerticesInsuficienteException()
			
			return Grafo().apply {
				this.tamanho = (vertices.max()!! + 1)
				this.direcionado = direcionado
				vertices.dropLast(1).forEachIndexed { index, vertice -> this += vertice para vertices[index + 1] }
			}
		}
	}
	
	/**
	 * Classe representando a aresta de um grafo
	 */
	data class Aresta(val origem: Int, val destino: Int, val peso: Double = 0.0) {
		
		override fun toString(): String {
			return "Aresta($origem,$destino) = $peso"
		}
		
		override fun equals(other: Any?): Boolean {
			if (this === other) return true
			if (javaClass != other?.javaClass) return false
			
			other as Aresta
			
			if (origem != other.origem || origem != other.destino) return false
			if (destino != other.destino || destino != other.origem) return false
			if (peso != other.peso) return false
			
			return true
		}
		
		override fun hashCode(): Int {
			var result = origem
			result = 31 * result + destino
			result = 31 * result + peso.hashCode()
			return result
		}
	}
	
	/**
	 * Classe representando um grafo contendo peso nas arestas
	 */
	class Ponderado internal constructor() : Grafo() {
		
		companion object {
			fun ofASize(tamanho: Int, direcionado: Boolean): Grafo.Ponderado {
				return Grafo.ofASize(tamanho, direcionado).toPonderado()
			}
			
			fun fromTheMatrix(matrizAdjacencia: Matriz<Double>, direcionado: Boolean): Grafo.Ponderado {
				return Grafo.fromTheMatrix(matrizAdjacencia, direcionado).toPonderado()
			}
			
			fun withThePath(vararg vertices: Int, direcionado: Boolean): Grafo.Ponderado {
				return Grafo.withThePath(*vertices, direcionado = direcionado).toPonderado()
			}
		}
	}
	
	/**
	 * Classe representando um grafo contendo peso e limite de fluxo nas arestas
	 */
	class PonderadoComFluxo internal constructor() : Grafo() {
		
		var matrizFluxo: Matriz<Double>
			protected set
		
		val arestasFluxo: List<Aresta>
			get() = matrizFluxo.cells.filter { it.value != INFINITO }.map { Aresta(it.row, it.col, it.value) }.distinct()
		
		init {
			matrizFluxo = matrizOf(tamanho por tamanho) { INFINITO }
		}
		
		companion object {
			fun ofASize(tamanho: Int, direcionado: Boolean): Grafo.PonderadoComFluxo {
				return Grafo.ofASize(tamanho, direcionado).toPonderadoComFluxo()
			}
			
			fun fromTheMatrix(matrizAdjacencia: Matriz<Double>, direcionado: Boolean): Grafo.PonderadoComFluxo {
				return Grafo.fromTheMatrix(matrizAdjacencia, direcionado).toPonderadoComFluxo()
			}
			
			fun withThePath(vararg vertices: Int, direcionado: Boolean): Grafo.PonderadoComFluxo {
				return Grafo.withThePath(*vertices, direcionado = direcionado).toPonderadoComFluxo()
			}
		}
	}
	
	/**
	 * Classe representando um grafo sem peso nas arestas
	 */
	class NaoPonderado internal constructor() : Grafo() {
		
		companion object {
			fun ofASize(tamanho: Int, direcionado: Boolean): Grafo.NaoPonderado {
				return Grafo.ofASize(tamanho, direcionado).toNaoPonderado()
			}
			
			fun fromTheMatrix(matrizAdjacencia: Matriz<Double>, direcionado: Boolean): Grafo.NaoPonderado {
				return Grafo.fromTheMatrix(matrizAdjacencia, direcionado).toNaoPonderado()
			}
			
			fun withThePath(vararg vertices: Int, direcionado: Boolean): Grafo.NaoPonderado {
				return Grafo.withThePath(*vertices, direcionado = direcionado).toNaoPonderado()
			}
		}
	}
}
