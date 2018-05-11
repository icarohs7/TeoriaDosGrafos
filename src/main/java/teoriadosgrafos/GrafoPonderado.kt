package teoriadosgrafos

import teoriadosgrafos.excecoes.ForaDoGrafoException

/**
 * Classe representando um grafo ponderado e suas operações
 *
 * @author [Icaro D Temponi](https://github.com/icarohs7)
 */
class GrafoPonderado(matrizDeAdjacencia: Array<IntArray>, direcionado: Boolean) : Grafo(matrizDeAdjacencia, direcionado) {
	/**
	 * Construir o grafo ponderado utilizando o valor não direcionado como padrão
	 * @param matrizDeAdjacencia Array<IntArray>: A matriz representando o grafo
	 */
	constructor(matrizDeAdjacencia: Array<IntArray>) : this(matrizDeAdjacencia, false)
	
	/**
	 * Construir um grafo ponderado vazio
	 * @param tamanho Int: A quantidade de vértices contidas no grafo
	 * @param direcionado Boolean: Se o grafo é ou não direcionado
	 */
	constructor(tamanho: Int, direcionado: Boolean) : this(Array<IntArray>(tamanho) { IntArray(tamanho) }, direcionado) {
		resetMatrizDeAdjacencia()
	}
	
	/**
	 * Reset matriz de adjacencia.
	 */
	private fun resetMatrizDeAdjacencia() {
		for (i in matrizDeAdjacencia.indices) {
			for (j in 0 until matrizDeAdjacencia[0].size) {
				/* Definir todos os pesos da matriz de adjacência para infinitos */
				matrizDeAdjacencia[i][j] = Grafo.INFINITO
			}
		}
	}
	
	/**
	 * Adicionar uma aresta ao grafo
	 *
	 * @param origem  Vértice de origem da aresta
	 * @param destino Vértice de destino da aresta
	 * @param peso    Peso da aresta
	 *
	 */
	@Suppress("NAME_SHADOWING")
	fun addAresta(origem: Int, destino: Int, peso: Int) {
		val origem = origem - 1
		val destino = destino - 1
		try {
			matrizDeAdjacencia[origem][destino] = peso
			if (!direcionado) {
				matrizDeAdjacencia[destino][origem] = peso
			}
		} catch (e: IndexOutOfBoundsException) {
			throw ForaDoGrafoException("Tentou acessar uma aresta fora do grafo: " + e.message)
		}
		
	}
}
