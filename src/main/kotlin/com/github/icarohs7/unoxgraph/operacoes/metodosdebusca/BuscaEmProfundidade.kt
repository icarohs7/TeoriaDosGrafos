package com.github.icarohs7.unoxgraph.operacoes.metodosdebusca

import com.github.icarohs7.unoxgraph.Grafo
import com.github.icarohs7.unoxgraph.estatico.CaminhoNaoEncontradoException
import java.util.Stack

object BuscaEmProfundidade {
	
	fun buscar(origem: Int, destino: Int, grafo: Grafo): List<Int> {
		grafo.inicializar()
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
}
