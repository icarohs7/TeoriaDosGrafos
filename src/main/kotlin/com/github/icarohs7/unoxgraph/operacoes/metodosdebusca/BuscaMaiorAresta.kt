package com.github.icarohs7.unoxgraph.operacoes.metodosdebusca

import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.extensoes.removeLast
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxgraph.grafos.Grafo.Ponderado

/**
 * Realiza a busca em um grafo utilizando o peso das arestas
 * como critério
 */
fun Grafo.Ponderado.buscaMaiorAresta(origem: Int, destino: Int, vararg ignorados: Double): List<Int> {
	elementosIgnorados = listOf(INFINITO) + ignorados.toList()
	return buscar(origem, destino, this)
}

private fun buscar(origem: Int, destino: Int, grafo: Ponderado): List<Int> {
	grafo.desmarcarTodosOsVertices()
	val caminho = mutableListOf<Int>()
	
	return if (buscaMaiorAresta(origem, destino, caminho, grafo)) caminho else emptyList()
}

private fun buscaMaiorAresta(origem: Int, destino: Int, caminho: MutableList<Int>, grafo: Grafo.Ponderado): Boolean {
	grafo.visitados[origem] = true // Marcar o vértice
	caminho += origem // Adicioná-lo ao caminho
	
	if (origem == destino) // Destino encontrado
		return true
	
	val vizinhos = grafo.getVizinhos(origem, abertos = true)
		.filterNot { grafo[origem to it] in elementosIgnorados }
		.sortedWith(compareBy({ -grafo[origem to it] }, { -it }))
	for (vizinho in vizinhos) // Realizar a busca em cada vizinho
		if (buscaMaiorAresta(vizinho, destino, caminho, grafo))
			return true
	
	grafo.visitados[origem] = false
	caminho.removeLast() // Remover vizinhos levando ao caminho errado
	
	return false // Destino não encontrado
}

/**
 * Valores ignorados como pesos de arestas
 */
private var elementosIgnorados = listOf(INFINITO)