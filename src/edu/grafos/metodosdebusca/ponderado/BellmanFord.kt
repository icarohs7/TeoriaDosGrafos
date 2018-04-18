package edu.grafos.metodosdebusca.ponderado

import edu.grafos.GrafoPonderado

abstract class BellmanFord {
	private val grafo = GrafoPonderado(null)
	private var origem = 0
	
	/**
	 * Inicializar o grafo, preparando-o para a busca
	 */
	private fun inicializar() {
		//Todas as distâncias para infinito
		grafo.resetDistancias(grafo.INFINITO)
		//A distância da origem para ela mesma como 0
		grafo.distancia[origem] = 0
		//Todos os precedentes indefinidos
		grafo.resetPrecedentes(-1)
	}
}