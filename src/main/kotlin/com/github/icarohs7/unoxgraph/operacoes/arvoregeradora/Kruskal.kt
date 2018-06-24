package com.github.icarohs7.unoxgraph.operacoes.arvoregeradora

import com.github.icarohs7.unoxgraph.ConjuntoInexistenteException
import com.github.icarohs7.unoxgraph.ElementoSemConjuntoException
import com.github.icarohs7.unoxgraph.GrafoPonderado

object Kruskal {
	
	private val conjuntos = mutableListOf<SubConjunto>()
	
	fun gerar(grafo: GrafoPonderado): MST {
		/* Criar a árvore geradora */
		val mst = MST(grafo.matrizDeAdjacencia.size)
		/* Limpar conjuntos */
		conjuntos.clear()
		/* Definir conjuntos iniciais */
		grafo.matrizDeAdjacencia.indices.forEach { conjuntos.add(SubConjunto(it, mutableListOf(it))) }
		
		/* Para cada aresta dentro do grafo em ordem ascendente de peso */
		grafo.arestas
			.sortedBy { it.peso }
			.forEach { aresta ->
				/* Se os vértices não pertencerem ao mesmo conjunto,
				 * Uni-los e adicionar a aresta à Árvore Geradora de Custo Mínimo */
				if (find(aresta.origem) != find(aresta.destino)) {
					mst.addAresta(aresta, grafo.direcionado)
					union(find(aresta.origem), find(aresta.destino))
				}
			}
		
		/* Por fim, retornar a árvore */
		return mst
	}
	
	/**
	 * Encontrar o subconjunto de um elemento ou
	 * lançar uma exceção caso o mesmo não seja encontrado
	 * @param i Int -- O elemento a ser encontrado
	 * @return Int -- O índice do subconjunto ao qual o elemento pertence
	 */
	private fun find(i: Int) = conjuntos.find { it.members.contains(i) }?.id
			?: throw ElementoSemConjuntoException()
	
	/**
	 * Realiza a união de dois conjuntos U e V
	 * @param u SubConjunto -- O primeiro conjunto
	 * @param v SubConjunto -- O segundo conjunto
	 */
	private fun union(u: Int, v: Int) {
		/* Pesquisar o ID dos conjuntos passados */
		val conjuntoU = conjuntos.find { it.id == u }
		val conjuntoV = conjuntos.find { it.id == v }
		
		if (conjuntoU != null && conjuntoV != null) {
			/* Caso os identificadores sejam válidos, adicionar
			 * todos os membros do segundo conjunto ao primeiro e
			  * em seguida destruir o segundo conjunto*/
			conjuntoU.members.addAll(conjuntoV.members)
			conjuntos.remove(conjuntoV)
		} else {
			/* Caso ao menos um dos identificadores seja inválido,
			 * lançar uma exceção */
			throw ConjuntoInexistenteException()
		}
	}
	
	data class SubConjunto(val id: Int, val members: MutableList<Int>)
}
