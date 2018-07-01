package com.github.icarohs7.unoxgraph.operacoes.metodosdebusca

import com.github.icarohs7.unoxgraph.estatico.CaminhoNaoEncontradoException
import com.github.icarohs7.unoxgraph.estatico.TipoDeGrafoIncorretoException
import com.github.icarohs7.unoxgraph.grafos.Grafo
import java.util.Stack

/**
 * Injeta a função de busca em profundidade no grafo
 */
fun Grafo.buscaEmProfundidade(origem: Int, destino: Int): List<Int> {
	return buscaEmProfundidade(origem, destino, this as? Grafo.NaoPonderado ?: throw TipoDeGrafoIncorretoException())
}

private fun buscaEmProfundidade(origem: Int, destino: Int, grafo: Grafo): List<Int> {
	grafo.desmarcarTodosOsVertices()
	val caminhoInvertido = Stack<Int>()
	
	/* Lançar uma exceção caso o destino não seja encontrado */
	if (!dfs(origem, destino, grafo, caminhoInvertido)) throw CaminhoNaoEncontradoException()
	
	/* Retornar o caminho da origem para o destino */
	return caminhoInvertido
}

/**
 * Procedimento recursivo responsável pela busca em profundidade
 */
private fun dfs(origem: Int, destino: Int, grafo: Grafo, caminhoInvertido: Stack<Int>): Boolean {
	grafo.visitados[origem] = true  // Marcar o vértice
	caminhoInvertido.push(origem)   // Adicioná-lo ao caminho
	
	if (origem == destino) return true // Caso base
	
	for (verticeVizinho in grafo.getVizinhos(origem, abertos = true)) {
		if (dfs(verticeVizinho, destino, grafo, caminhoInvertido)) { // Descer um nível na busca e retornar o resultado
			return true
		}
	}
	
	return false    // Destino não encontrado
}
