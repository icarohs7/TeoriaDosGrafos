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
	fun expandirVerticesPonderado() = expandirVertices() to peso
	
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
