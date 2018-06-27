package com.github.icarohs7.unoxgraph

import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxkcommons.estatico.MatrizDouble
import com.github.icarohs7.unoxkcommons.extensoes.por
import com.github.icarohs7.unoxkcommons.extensoes.preenchendoMatrizDoubleDeTamanho

class GrafoPonderado
private constructor(matrizAdjacencia: MatrizDouble, direcionado: Boolean = false) : Grafo(matrizAdjacencia, direcionado) {
	
	private constructor(tamanho: Int, direcionado: Boolean = false)
			: this(INFINITO preenchendoMatrizDoubleDeTamanho (tamanho por tamanho), direcionado)
	
	companion object {
		fun fromTheMatrix(matrizAdjacencia: MatrizDouble, direcionado: Boolean = false) = GrafoPonderado(matrizAdjacencia, direcionado)
		fun ofASize(tamanho: Int, direcionado: Boolean = false) = GrafoPonderado(tamanho, direcionado)
	}
	
	val custoMinimo = CustoMinimo(this)
	
	val arvoreGeradora = ArvoreGeradora(this)
	
	/**
	 * Classe utilizada para agrupar os métodos de custo mínimo em um grafo
	 * @property grafo GrafoPonderado
	 * @constructor
	 */
	class CustoMinimo(val grafo: GrafoPonderado)
	
	/**
	 * Classe utilizada para agrupar os métodos geradores de árvore de custo mínimo
	 * @property grafo GrafoPonderado
	 * @constructor
	 */
	class ArvoreGeradora(val grafo: GrafoPonderado)
}
