package com.github.icarohs7.unoxgraph

data class Aresta(val origem: Int, val destino: Int, val peso: Double) {
	override fun toString(): String {
		return "Aresta($origem,$destino) = $peso"
	}
	
	/**
	 * Retorna uma lista com os vértices da aresta
	 */
	fun expandirVertices() = listOf(origem, destino)
	
	/**
	 * Retorna um par contendo a lista de vértices e o peso da aresta
	 */
	fun expandirPonderado() = expandirVertices() to peso
}
