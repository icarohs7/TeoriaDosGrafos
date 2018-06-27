package com.github.icarohs7.unoxgraph

import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.estatico.NumeroDeVerticesInsuficienteException
import com.github.icarohs7.unoxgraph.extensoes.plusAssign
import com.github.icarohs7.unoxkcommons.estatico.MatrizDouble
import com.github.icarohs7.unoxkcommons.extensoes.para
import com.github.icarohs7.unoxkcommons.extensoes.por
import com.github.icarohs7.unoxkcommons.extensoes.preenchendoMatrizDoubleDeTamanho

class GrafoNaoPonderado
private constructor(matrizAdjacencia: MatrizDouble, direcionado: Boolean = false) : Grafo(matrizAdjacencia, direcionado) {
	
	private constructor(tamanho: Int, direcionado: Boolean = false)
			: this(INFINITO preenchendoMatrizDoubleDeTamanho (tamanho por tamanho), direcionado)
	
	/**
	 * Construir um grafo não ponderado, direcionado por padrão, com a sintaxe:
	 * GrafoPonderado(0, 1, 2), onde os valores passados no construtor são os vértices
	 * de forma que o grafo forme um caminho equivalente à ordem
	 */
	private constructor(vararg vertices: Int, direcionado: Boolean = true) : this(vertices.max()!! + 1, direcionado) {
		if (vertices.size < 2) throw NumeroDeVerticesInsuficienteException()
		
		vertices.dropLast(1).forEachIndexed { index, vertice -> this += vertice para vertices[index + 1] }
	}
	
	companion object {
		fun fromTheMatrix(matrizAdjacencia: MatrizDouble, direcionado: Boolean = false) = GrafoNaoPonderado(matrizAdjacencia, direcionado)
		fun ofASize(tamanho: Int, direcionado: Boolean = false) = GrafoNaoPonderado(tamanho, direcionado)
		fun withThePath(vararg vertices: Int, direcionado: Boolean = true) = GrafoNaoPonderado(*vertices, direcionado = direcionado)
	}
	
	val metodosDeBusca = MetodosDeBusca(this)
	
	/**
	 * Classe fornecendo acesso aos métodos de busca
	 * @property grafo GrafoNaoPonderado
	 * @constructor
	 */
	class MetodosDeBusca(val grafo: GrafoNaoPonderado)
}
