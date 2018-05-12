package teoriadosgrafos

import teoriadosgrafos.excecoes.ForaDoGrafoException

/**
 * Classe representando um grafo ponderado e suas operações
 *
 * @author [Icaro D Temponi](https://github.com/icarohs7)
 */
class GrafoPonderado(matrizDeAdjacencia: Array<DoubleArray>, direcionado: Boolean) : Grafo(matrizDeAdjacencia, direcionado) {
	/**
	 * Construir o grafo ponderado utilizando o valor não direcionado como padrão
	 * @param matrizDeAdjacencia Array<IntArray>: A matriz representando o grafo
	 */
	constructor(matrizDeAdjacencia: Array<DoubleArray>) : this(matrizDeAdjacencia, false)
	
	/**
	 * Construir um grafo ponderado vazio
	 * @param tamanho Int: A quantidade de vértices contidas no grafo
	 * @param direcionado Boolean: Se o grafo é ou não direcionado
	 */
	constructor(tamanho: Int, direcionado: Boolean)
			: this(Array<DoubleArray>(tamanho) { DoubleArray(tamanho) { INFINITO } }, direcionado)
	
	/**
	 * Adicionar uma aresta ao grafo
	 * @param origem  Vértice de origem da aresta
	 * @param destino Vértice de destino da aresta
	 * @param peso    Peso da aresta
	 */
	@Suppress("NAME_SHADOWING")
	fun addAresta(origem: Int, destino: Int, peso: Double) {
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
	
	/**
	 * To string de um grafo
	 * @return String
	 */
	override fun toString(): String {
		return buildString {
			matrizDeAdjacenciaAsString.forEach { linha ->
				linha.forEach { elem ->
					append("$elem, ")
				}
				append("\n")
			}
		}
	}
}
