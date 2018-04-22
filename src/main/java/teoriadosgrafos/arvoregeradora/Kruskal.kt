package teoriadosgrafos.arvoregeradora

import teoriadosgrafos.GrafoPonderado

/**
 * Singleton representandoo algoritmo para da
 * Árvore Geradora de Custo Mínimo
 * de Kruskal
 *
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
object Kruskal {
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
		/* Criar lista de subconjuntos */
		val conjuntos = mutableSetOf<SubConjunto>()
		/* Definir conjuntos iniciais */
		for (i in 0 until w.size) {
			conjuntos.add(SubConjunto(i, mutableSetOf(i)))
		}
		/* Arestas contidas no grafo */
		val arestas = grafo.arestas
				.sortedBy { aresta -> aresta.peso }
				.toMutableList()
		
		/* Para cada aresta dentro do grafo */
		arestas.forEach { aresta ->
			/* X recebe o conjunto em que o vértice de origem da aresta se encontra */
			val x = find(conjuntos, aresta.origem)
			/* Y recebe o conjunto em que o vértice de destino da aresta se encontra */
			val y = find(conjuntos, aresta.destino)
			
			/* Se os vértices não pertencerem ao mesmo conjunto,
			 * Uni-los e adicionar a aresta à Árvore Geradora de Custo Mínimo */
			if (x != y) {
				mst.addAresta(aresta)
				union(conjuntos, x, y)
			}
		}
		
		/* Por fim, retornar a árvore */
		return mst
	}
	
	
	/**
	 * Encontrar o subconjunto de um elemento
	 * @param conjuntos Array<subConjunto> -- A lista de subconjuntos
	 * @param i Int -- O elemento a ser encontrado
	 * @return Int -- O índice do subconjunto ao qual o elemento pertence
	 */
	private fun find(conjuntos: MutableSet<SubConjunto>, i: Int): SubConjunto {
		conjuntos.forEach { conjunto ->
			/* Passar por todos os conjuntos e retornar aquele
			 * que contem o elemento I */
			if (conjunto.members.contains(i)) {
				return conjunto
			}
		}
		/* Caso haja algum erro e o elemento não pertencer a nenhum conjunto,
		 * retornar */
		throw RuntimeException("Elemento não pertence a nenhum conjunto")
	}
	
	/**
	 * Realiza a união de dois conjuntos U e V
	 * @param conjuntos MutableSet<SubConjunto> -- A lista contendo todos os conjuntos
	 * @param u SubConjunto -- O primeiro conjunto
	 * @param v SubConjunto -- O segundo conjunto
	 */
	private fun union(conjuntos: MutableSet<SubConjunto>, u: SubConjunto, v: SubConjunto) {
		/* Unir os conjuntos */
		v.members.forEach { membro ->
			/* Adicionar cada membro de V a U */
			u.members.add(membro)
		}
		/* Apagar V */
		conjuntos.remove(v)
	}
	
	/**
	 * Classe representando um subconjunto
	 * @property id Int -- O identificador do subconjunto
	 * @property members MutableSet<Int> -- Os membros do conjunto
	 * @constructor
	 */
	data class SubConjunto(var id: Int, val members: MutableSet<Int>)
}