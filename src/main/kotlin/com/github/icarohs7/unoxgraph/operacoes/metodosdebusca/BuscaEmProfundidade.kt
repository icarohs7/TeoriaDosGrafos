package com.github.icarohs7.unoxgraph.operacoes.metodosdebusca

import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.extensoes.removeLast
import com.github.icarohs7.unoxgraph.grafos.Grafo

/**
 * Injeta a função de busca em profundidade no grafo
 */
fun Grafo.buscaEmProfundidade(origem: Int, destino: Int, vararg ignorados: Double): List<Int> {
	elementosIgnorados = listOf(INFINITO) + ignorados.toList()
	return buscaEmProfundidade(origem, destino, this)
}

private fun buscaEmProfundidade(origem: Int, destino: Int, grafo: Grafo): List<Int> {
	grafo.desmarcarTodosOsVertices()
	val caminho = mutableListOf<Int>()
	return if (dfs(origem, destino, grafo, caminho)) caminho else emptyList()
}

/**
 * Procedimento recursivo responsável pela busca em profundidade
 */
private fun dfs(origem: Int, destino: Int, grafo: Grafo, caminho: MutableList<Int>): Boolean {
	grafo.visitados[origem] = true // Marcar o vértice
	caminho += origem // Adicioná-lo ao caminho
	
	if (origem == destino) // Destino encontrado
		return true
	
	val vizinhos = grafo.getVizinhos(origem, abertos = true).filterNot { grafo[origem to it] in elementosIgnorados }
	for (verticeVizinho in vizinhos)
		if (dfs(verticeVizinho, destino, grafo, caminho)) // Realizar busca em cada vizinho
			return true
	
	caminho.removeLast() // Remover vértice levando ao caminho incorreto
	
	return false // Destino não encontrado
}

/**
 * Valores ignorados como pesos de arestas
 */
private var elementosIgnorados = listOf(INFINITO)
