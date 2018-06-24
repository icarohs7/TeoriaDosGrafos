package com.github.icarohs7.unoxgraph.operacoes.arvoregeradora

import com.github.icarohs7.unoxgraph.Aresta
import com.github.icarohs7.unoxgraph.Grafo

class MST(private val tamanho: Int) {
	
	@Suppress("MemberVisibilityCanBePrivate")
	val tree: Array<DoubleArray> = Array(tamanho) { DoubleArray(tamanho) { Grafo.INFINITO } }
	
	fun addAresta(aresta: Aresta, direcionado: Boolean = false) {
		/* Não permitir a inserção de aresta repetida */
		if (tree[aresta.origem][aresta.destino] != Grafo.INFINITO) {
			return
		}
		
		/* Adicionar aresta */
		tree[aresta.origem][aresta.destino] = aresta.peso
		/* Adicionar a volta caso o grafo seja não direcionado */
		if (!direcionado) {
			tree[aresta.destino][aresta.origem] = aresta.peso
		}
	}
	
	/**
	 * Retorna uma lista contendo as arestas presentes na árvore geradora
	 *
	 * @return List<Aresta> -- A lista de arestas
	 */
	@Suppress("MemberVisibilityCanBePrivate")
	fun getArestas(): List<Aresta> {
		/* Criar lista de arestas */
		val arestas = arrayListOf<Aresta>()
		for (u in 0 until tamanho) {
			for (v in 0 until tamanho) {
				/* Para cada aresta da árvore, se a mesma não possuir
				 * peso infinito e já não estiver contida na lista,
				  * adicioná-la à lista de arestas*/
				if (tree[u][v] != Grafo.INFINITO) {
					val novaAresta = Aresta(u, v, tree[u][v])
					if (!arestas.contains(novaAresta)) {
						arestas.add(novaAresta)
					}
				}
			}
		}
		/* Retornar arestas */
		return arestas.distinct()
	}
}