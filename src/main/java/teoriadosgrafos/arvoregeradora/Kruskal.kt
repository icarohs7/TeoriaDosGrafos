package teoriadosgrafos.arvoregeradora

import teoriadosgrafos.GrafoPonderado

abstract class Kruskal {
	companion object {
		fun gerar(grafo: GrafoPonderado): MST {
			val w = grafo.matrizDeAdjacencia
			/* Criar a árvore geradora */
			val mst = MST(w.size)
			/* Criar lista de subconjuntos */
			val subsets = Array(w.size) { SubConjunto(0, 0) }
			/* Definir subconjuntos iniciais */
			for (i in 0 until w.size) {
				subsets[i].parent = i
				subsets[i].rank = 0
			}
			/* Arestas contidas no grafo */
			val arestas = grafo.arestas
					.sortedBy { aresta -> aresta.peso }
					.toMutableList()
			
			var i = 0
			/* Número de arestas a serem utilizadas é igual a V-1 */
			while (i < w.size) {
				/* Pegar a menor aresta */
				val proxAresta = arestas.removeAt(0)
				i++
				
				val x = find(subsets, proxAresta.origem)
				val y = find(subsets, proxAresta.destino)
				
				/* Se as arestas não pertencerem ao mesmo ciclo,
				 * inclui-las no resultado */
				if (x != y) {
					mst.addAresta(proxAresta)
					union(subsets, x, y)
				}
			}
			
			return mst
		}
		
		/**
		 * Função auxiliar utilizada para executar funções repetidas
		 * @param repetitions Int -- Quantas vezes uma ação vai se repetir
		 * @param func Function0<Unit> -- A função que será repetida
		 */
		fun exec(repetitions: Int, func: () -> Unit) {
			for (i in 0 until repetitions) {
				func()
			}
		}
		
		/**
		 * Encontrar o subconjunto de um elemento
		 * @param subsets Array<subConjunto> -- A lista de subconjuntos
		 * @param i Int -- O elemento a ser encontrado
		 * @return Int -- O índice do subconjunto ao qual o elemento pertence
		 */
		fun find(subsets: Array<SubConjunto>, i: Int): Int {
			// find root and make root as parent of i (path compression)
			if (subsets[i].parent != i)
				subsets[i].parent = find(subsets, subsets[i].parent)
			
			return subsets[i].parent
		}
		
		/**
		 * Realiza a união de dois conjuntos x e y
		 * @param subsets Array<SubConjunto> -- A lista de subconjuntos
		 * @param x Int -- O índice do primeiro conjunto
		 * @param y Int -- O índice do segundo conjunto
		 */
		fun union(subsets: Array<SubConjunto>, x: Int, y: Int) {
			val xroot = find(subsets, x)
			val yroot = find(subsets, y)
			
			// Attach smaller rank tree under root of high rank tree
			// (union by Rank)
			if (subsets[xroot].rank < subsets[yroot].rank) {
				subsets[xroot].parent = yroot
			} else if (subsets[xroot].rank > subsets[yroot].rank) {
				subsets[yroot].parent = xroot
			}
			// If ranks are same, then make one as root and increment
			// its rank by one
			else {
				subsets[yroot].parent = xroot
				subsets[xroot].rank++
			}
		}
		
		/**
		 * Classe representando um subconjunto
		 * @property parent Int
		 * @property rank Int
		 * @constructor
		 */
		data class SubConjunto(var parent: Int, var rank: Int)
	}
	
}