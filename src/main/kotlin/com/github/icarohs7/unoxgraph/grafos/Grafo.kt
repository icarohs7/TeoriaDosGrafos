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
import java.util.Arrays

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
		
		if (this is PonderadoComFluxo) {
			matrizFluxo[origem][destino] = INFINITO
			if (!direcionado) matrizFluxo[destino][origem] = INFINITO
		}
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
				val matrizGrafo1 = this.matrizAdjacencia
				val matrizGrafo2 = (other as Grafo).matrizAdjacencia
				
				// Sua matriz de adjacência deve ter as mesmas dimensões
				if (matrizGrafo1.size != matrizGrafo2.size) return false
				else if (matrizGrafo1[0].size != matrizGrafo2[0].size) return false
				
				// Devem conter a mesma matriz de adjacência
				for (i in 0 until matrizGrafo1.size) {
					for (j in 0 until matrizGrafo1[0].size) {
						if (matrizGrafo1[i][j] != matrizGrafo2[i][j]) return false
					}
				}
				
				// Devem ser igualmente direcionados ou não direcionados
				if (this.direcionado != other.direcionado) return false
				
				// Se contiverem limite de fluxo, devem ser iguais
				if (this is PonderadoComFluxo && other is PonderadoComFluxo) return this.matrizFluxo.cells == other.matrizFluxo.cells
				
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
	
	internal fun toPonderado(): Grafo.Ponderado {
		val grafo = Grafo.Ponderado()
		grafo.tamanho = this.tamanho
		grafo.direcionado = this.direcionado
		grafo.matrizAdjacencia = this.matrizAdjacencia
		return grafo
	}
	
	internal fun toPonderadoComFluxo(): Grafo.PonderadoComFluxo {
		val grafo = Grafo.PonderadoComFluxo(this.tamanho)
		grafo.direcionado = this.direcionado
		grafo.matrizAdjacencia = this.matrizAdjacencia
		return grafo
	}
	
	internal fun toNaoPonderado(): Grafo.NaoPonderado {
		val grafo = Grafo.NaoPonderado()
		grafo.tamanho = this.tamanho
		grafo.direcionado = this.direcionado
		grafo.matrizAdjacencia = this.matrizAdjacencia
		return grafo
	}
	
	override fun toString(): String {
		return "Grafo(\n" +
				"direcionado=$direcionado\n" +
				"matrizAdjacencia=${matrizAdjacencia.cells.filter { it.value != INFINITO }}\n" +
				"visitados=${Arrays.toString(visitados)}\n" +
				")"
	}
	
	internal open fun copy(): Grafo {
		return Grafo().also { grafo ->
			grafo.tamanho = this.tamanho
			grafo.direcionado = this.direcionado
			grafo.visitados = this.visitados
			grafo.matrizAdjacencia = this.matrizAdjacencia
		}
	}
	
	/**
	 * Funções factory
	 */
	companion object {
		
		/**
		 * Criar um grafo a partir de um tamanho
		 */
		private fun ofASize(tamanho: Int, direcionado: Boolean): Grafo {
			return Grafo().also { grafo ->
				grafo.tamanho = tamanho
				grafo.direcionado = direcionado
			}
		}
		
		/**
		 * Criar um grafo a partir da matriz de adjacência
		 */
		private fun fromTheMatrix(matrizAdjacencia: Matriz<Double>, direcionado: Boolean): Grafo {
			return Grafo().also { grafo ->
				grafo.tamanho = matrizAdjacencia.size
				grafo.direcionado = direcionado
				grafo.matrizAdjacencia = matrizOf(grafo.tamanho) { matrizAdjacencia[it.row][it.col] }
			}
		}
		
		/**
		 * Criar um grafo a partir de um caminho composto por uma lista de vértices inteiros
		 */
		private fun withThePath(vararg vertices: Int, direcionado: Boolean): Grafo {
			if (vertices.size < 2) throw NumeroDeVerticesInsuficienteException()
			
			return Grafo().also { grafo ->
				grafo.tamanho = (vertices.max()!! + 1)
				grafo.direcionado = direcionado
				vertices.dropLast(1).forEachIndexed { index, vertice -> grafo += vertice para vertices[index + 1] }
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
	
	/**
	 * Classe representando um grafo contendo peso e limite de fluxo nas arestas
	 */
	class PonderadoComFluxo internal constructor(tamanho: Int) : Grafo() {
		
		var matrizFluxo: Matriz<Double>
			protected set
		
		val arestasFluxo: List<Aresta>
			get() = matrizFluxo.cells.filter { it.value != INFINITO }.map { Aresta(it.row, it.col, it.value) }.distinct()
		
		init {
			this.tamanho = tamanho
			matrizFluxo = matrizOf(tamanho por tamanho) { INFINITO }
		}
		
		/**
		 * Operação de adição de aresta com fluxo
		 */
		operator fun plusAssign(other: Pair<Aresta, Double>) {
			val aresta = other.first
			addAresta(aresta)
			matrizFluxo[aresta.origem][aresta.destino] = other.second
			if (!direcionado) matrizFluxo[aresta.destino][aresta.origem] = other.second
		}
		
		/**
		 * Realiza a cópia do grafo, gerando outra instância equivalente
		 */
		override fun copy(): Grafo.PonderadoComFluxo {
			return Grafo.PonderadoComFluxo(this.tamanho).also { grafo ->
				grafo.direcionado = this.direcionado
				grafo.matrizAdjacencia = this.matrizAdjacencia
				grafo.matrizFluxo = this.matrizFluxo
				grafo.visitados = this.visitados
			}
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
}
