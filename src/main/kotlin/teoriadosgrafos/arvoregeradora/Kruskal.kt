package teoriadosgrafos.arvoregeradora

import teoriadosgrafos.GrafoPonderado

/**
 * Singleton representandoo algoritmo para da
 * Árvore Geradora de Custo Mínimo
 * de Kruskal
 *
 *
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
object Kruskal {
	private val conjuntos = mutableSetOf<SubConjunto>()
	/**
	 * Gera a MST utilizando o argoritmo de Kruskal
	 * @param grafo GrafoPonderado -- O grafo em que a operação será executada
	 * @return MST -- A Árvore Geradora de Custo Mínimo
	 */
	fun gerar(grafo: GrafoPonderado): MST {
		/* Variável contendo a matriz de adjacência */
		val w = grafo.matrizDeAdjacencia
		/* Criar a árvore geradora */
		val mst = MST(w.size)
		/* Limpar conjuntos */
		conjuntos.clear()
		/* Definir conjuntos iniciais */
		w.indices.forEach { conjuntos.add(SubConjunto(it, mutableSetOf(it))) }
		/* Arestas contidas no grafo */
		val arestas = grafo.arestas.sortedBy { aresta -> aresta.peso }
		
		/* Para cada aresta dentro do grafo */
		arestas.forEach { aresta ->
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
	 * Encontrar o subconjunto de um elemento
	 * @param i Int -- O elemento a ser encontrado
	 * @return Int -- O índice do subconjunto ao qual o elemento pertence
	 */
	private fun find(i: Int): Int {
		/* Retorna o identificador do conjunto do elemento passado
		 * e caso o conjunto não seja encontrado, lançar uma exceção */
		return conjuntos
			.find { it.members.contains(i) }?.id
				?: throw RuntimeException("Elemento não pertence a nenhum conjunto")
	}
	
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
			throw RuntimeException("O conjunto informado não existe")
		}
	}
	
	/**
	 * Classe representando um subconjunto
	 * @property id Int -- O identificador do subconjunto
	 * @property members MutableSet<Int> -- Os membros do conjunto
	 * @constructor
	 */
	data class SubConjunto(val id: Int, val members: MutableSet<Int>)
}
