package com.github.icarohs7.unoxgraph.operacoes.arvoregeradora

import com.github.icarohs7.unoxgraph.estatico.ConjuntoInexistenteException
import com.github.icarohs7.unoxgraph.estatico.ElementoSemConjuntoException
import com.github.icarohs7.unoxgraph.estatico.TipoDeGrafoIncorretoException
import com.github.icarohs7.unoxgraph.grafos.Grafo

/**
 * Injetar a função de calculo de kruskal na classe grafo
 */
fun Grafo.mstKruskal(): MST {
	return kruskal(this as? Grafo.Ponderado ?: throw TipoDeGrafoIncorretoException())
}

private val conjuntos = mutableListOf<SubConjunto>()

private fun kruskal(grafo: Grafo.Ponderado): MST {
	/* Criar a árvore geradora */
	val mst = MST(grafo.tamanho)
	/* Limpar conjuntos */
	conjuntos.clear()
	/* Definir conjuntos iniciais */
	(0 until grafo.tamanho).forEach { conjuntos.add(SubConjunto(it, mutableListOf(it))) }
	
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
 */
private fun find(i: Int) = conjuntos.find { it.members.contains(i) }?.id ?: throw ElementoSemConjuntoException()

/**
 * Realiza a união de dois conjuntos U e V
 */
private fun union(pai: Int, filho: Int) {
	/* Pesquisar o ID dos conjuntos passados */
	val conjuntoPai = conjuntos.find { it.id == pai }
	val conjuntoFilho = conjuntos.find { it.id == filho }
	
	if (conjuntoPai != null && conjuntoFilho != null) {
		conjuntoPai.members += conjuntoFilho.members
		conjuntos -= conjuntoFilho
	} else {
		throw ConjuntoInexistenteException()
	}
}

private data class SubConjunto(val id: Int, val members: MutableList<Int>)
