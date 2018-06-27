package com.github.icarohs7.unoxgraph.operacoes.metodosdebusca

import com.github.icarohs7.unoxgraph.GrafoNaoPonderado
import com.github.icarohs7.unoxgraph.estatico.CaminhoNaoEncontradoException
import java.util.LinkedList
import java.util.Stack

object BuscaEmLargura {
	
	fun buscar(origem: Int, destino: Int, grafo: GrafoNaoPonderado): List<Int> {
		grafo.inicializar()
		grafo.visitados[origem] = true
		val caminho = LinkedList<Int>()
		val caminhoInvertido = Stack<Int>()
		
		/* Lançar exceção caso o destino não seja encontrado */
		if (!bfs(origem, destino, grafo, caminho, caminhoInvertido)) throw CaminhoNaoEncontradoException()
		
		/* Retornar o caminho da origem para o destino */
		return caminhoInvertido
	}
	
	/**
	 * Procedimento recursivo responsável pela busca em largura
	 */
	private tailrec fun bfs(origem: Int, destino: Int, grafo: GrafoNaoPonderado, proximosVertices: LinkedList<Int>, caminhoInvertido:
	Stack<Int>): Boolean {
		caminhoInvertido += origem   // Adicionar origem ao caminho
		
		if (origem == destino) return true  // Caso base
		
		for (verticeVizinho in grafo.getVizinhos(origem, abertos = true)) {    // Enfileirar vizinhos abertos do vértice origem
			grafo.visitados[verticeVizinho] = true
			proximosVertices += verticeVizinho
		}
		
		return if (!proximosVertices.isEmpty())
			bfs(proximosVertices.remove(), destino, grafo, proximosVertices, caminhoInvertido)  // Chamada recursiva para o próximo vértice
		else
			false
	}
}
