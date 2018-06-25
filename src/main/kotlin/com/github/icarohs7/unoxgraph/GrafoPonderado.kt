package com.github.icarohs7.unoxgraph

import com.github.icarohs7.unoxcommons.estatico.MatrizDouble
import com.github.icarohs7.unoxcommons.extensoes.por
import com.github.icarohs7.unoxcommons.extensoes.preenchendoMatrizDoubleDeTamanho
import com.github.icarohs7.unoxgraph.estatico.INFINITO

class GrafoPonderado(matrizDeAdjacencia: MatrizDouble, direcionado: Boolean = false) : Grafo(matrizDeAdjacencia, direcionado) {
	
	constructor(tamanho: Int, direcionado: Boolean = false)
			: this(INFINITO preenchendoMatrizDoubleDeTamanho (tamanho por tamanho), direcionado)
	
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
