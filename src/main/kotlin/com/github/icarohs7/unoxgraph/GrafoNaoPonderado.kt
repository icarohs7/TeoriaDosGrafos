package com.github.icarohs7.unoxgraph

import com.github.icarohs7.unoxcommons.estatico.MatrizDouble
import com.github.icarohs7.unoxcommons.extensoes.para
import com.github.icarohs7.unoxcommons.extensoes.por
import com.github.icarohs7.unoxcommons.extensoes.preenchendoMatrizDoubleDeTamanho
import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.estatico.NumeroDeVerticesInsuficienteException
import com.github.icarohs7.unoxgraph.extensoes.plusAssign

class GrafoNaoPonderado(matrizDeAdjacencia: MatrizDouble, direcionado: Boolean = false) : Grafo(matrizDeAdjacencia, direcionado) {
	
	constructor(tamanho: Int, direcionado: Boolean = false)
			: this(INFINITO preenchendoMatrizDoubleDeTamanho (tamanho por tamanho), direcionado)
	
	/**
	 * Construir um grafo não ponderado, direcionado por padrão, com a sintaxe:
	 * GrafoPonderado(0, 1, 2), onde os valores passados no construtor são os vértices
	 * de forma que o grafo forme um caminho equivalente à ordem
	 */
	constructor(vararg vertices: Int, direcionado: Boolean = true) : this(vertices.max()!! + 1, direcionado) {
		if (vertices.size < 2) throw NumeroDeVerticesInsuficienteException()
		
		for (v in 0 until vertices.size - 1) {
			this += vertices[v] para vertices[v + 1]
		}
	}
	
	val metodosDeBusca = MetodosDeBusca(this)
	
	/**
	 * Classe fornecendo acesso aos métodos de busca
	 * @property grafo GrafoNaoPonderado
	 * @constructor
	 */
	class MetodosDeBusca(val grafo: GrafoNaoPonderado)
}
