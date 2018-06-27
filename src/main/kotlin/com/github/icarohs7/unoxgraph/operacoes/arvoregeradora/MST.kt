package com.github.icarohs7.unoxgraph.operacoes.arvoregeradora

import com.github.icarohs7.unoxgraph.Aresta
import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxkcommons.estatico.MatrizDouble
import com.github.icarohs7.unoxkcommons.extensoes.expandido
import java.util.Arrays

class MST(private val tamanho: Int) {
	
	@Suppress("MemberVisibilityCanBePrivate")
	val tree: MatrizDouble = Array(tamanho) { DoubleArray(tamanho) { INFINITO } }
	
	fun addAresta(aresta: Aresta, direcionado: Boolean = false) {
		/* Não permitir a inserção de aresta repetida */
		if (tree[aresta.origem][aresta.destino] != INFINITO) return
		
		/* Adicionar aresta */
		tree[aresta.origem][aresta.destino] = aresta.peso
		
		/* Adicionar a volta caso o grafo seja não direcionado */
		if (!direcionado) tree[aresta.destino][aresta.origem] = aresta.peso
	}
	
	/**
	 * Retorna uma lista contendo as arestas presentes na árvore geradora
	 */
	fun getArestas(): List<Aresta> {
		/* Criar lista de arestas */
		val arestas = arrayListOf<Aresta>()
		for (u in 0 until tamanho) {
			for (v in 0 until tamanho) {
				/* Para cada aresta da árvore, se a mesma não possuir
				 * peso infinito e já não estiver contida na lista,
				  * adicioná-la à lista de arestas*/
				if (tree[u][v] != INFINITO) {
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
	
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false
		
		other as MST
		
		if (tamanho != other.tamanho) return false
		if (!tree.expandido().contentEquals(other.tree.expandido())) return false
		
		return true
	}
	
	override fun hashCode(): Int {
		var result = tamanho
		result = 31 * result + Arrays.hashCode(tree)
		return result
	}
	
}