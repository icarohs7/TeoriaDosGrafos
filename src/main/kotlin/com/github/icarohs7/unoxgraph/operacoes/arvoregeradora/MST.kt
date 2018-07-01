package com.github.icarohs7.unoxgraph.operacoes.arvoregeradora

import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.grafos.Grafo.Aresta
import com.github.icarohs7.unoxkcommons.extensoes.cells
import com.github.icarohs7.unoxkcommons.funcoes.matrizOf
import java.util.Arrays

class MST(val tamanho: Int) {
	
	@Suppress("MemberVisibilityCanBePrivate")
	val tree = matrizOf(tamanho) { INFINITO }
	
	internal fun addAresta(aresta: Aresta, direcionado: Boolean = false) {
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
	val arestas: List<Aresta>
		get() = tree.cells.filter { it.value != INFINITO }.map { Aresta(it.row, it.col, it.value) }.distinct()
	
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false
		
		other as MST
		
		if (tamanho != other.tamanho) return false
		if (tree.cells != other.tree.cells) return false
		
		return true
	}
	
	override fun hashCode(): Int {
		var result = tamanho
		result = 31 * result + Arrays.hashCode(tree)
		return result
	}
	
}