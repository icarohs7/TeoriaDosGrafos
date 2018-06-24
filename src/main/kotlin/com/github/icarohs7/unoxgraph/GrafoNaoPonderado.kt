package com.github.icarohs7.unoxgraph

class GrafoNaoPonderado(matrizDeAdjacencia: Array<DoubleArray>, direcionado: Boolean = false) : Grafo(matrizDeAdjacencia, direcionado) {
	
	constructor(tamanho: Int, direcionado: Boolean = false) : this(
		Array<DoubleArray>(tamanho) {
			DoubleArray(tamanho) { Grafo.INFINITO }
		}, direcionado)
	
	val metodosDeBusca = MetodosDeBusca(this)
	
	/**
	 * Classe fornecendo acesso aos métodos de busca
	 * @property grafo GrafoNaoPonderado
	 * @constructor
	 */
	class MetodosDeBusca(val grafo: GrafoNaoPonderado)
}
