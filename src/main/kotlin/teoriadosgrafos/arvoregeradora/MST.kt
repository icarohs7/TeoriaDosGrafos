package teoriadosgrafos.arvoregeradora

import teoriadosgrafos.Aresta
import teoriadosgrafos.Grafo
import java.io.PrintStream

/**
 * Classe representando uma Árvore Geradora de Custo Mínimo
 *
 * @property tamanho Int -- A quantidade de vértices contidos na árvore
 * @property tree Array<IntArray> -- A matriz contendo a árvore
 * @constructor
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
class MST(private val tamanho: Int) {
	@Suppress("MemberVisibilityCanBePrivate")
	val tree: Array<DoubleArray> = Array(tamanho) { DoubleArray(tamanho) { Grafo.INFINITO } }
	
	/**
	 * Adicionar uma aresta à arvore
	 * @param aresta Aresta -- A aresta a ser adicionada
	 */
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
	 * Imprimir a árvore
	 * @param out PrintStream -- A saída onde será impressa a árvore
	 */
	@Suppress("MemberVisibilityCanBePrivate")
	fun printTree(out: PrintStream) {
		tree.forEach {
			it.forEach { print("$it ") }
			out.println()
		}
	}
	
	/**
	 * Imprimir a árvore no console
	 */
	@Suppress("unused")
	fun printTree() {
		printTree(System.out)
	}
	
	/**
	 * Imprimir as arestas continas na árvore
	 * @param out PrintStream -- A saída onde as arestas serão impressas
	 */
	@Suppress("MemberVisibilityCanBePrivate")
	fun printEdges(out: PrintStream) {
		getArestas().forEach { aresta ->
			out.println(aresta)
		}
	}
	
	/**
	 * Imprimir as arestas contidas na árvore no console
	 */
	@Suppress("unused")
	fun printEdges() {
		printEdges(System.out)
	}
	
	/**
	 * Retorna uma lista contendo as arestas presentes na árvore geradora
	 *
	 * @return List<Aresta> -- A lista de arestas
	 */
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
	
	/**
	 * Retorna a árvore como uma matriz de strings
	 *
	 * @return Array<Array<String>> -- A matriz de Strings
	 */
	fun getTreeAsString(): Array<Array<String>> {
		/* Criar matriz */
		val stringTree = Array(tamanho) { Array(tamanho) { "" } }
		
		for (i in 0 until tamanho) {
			for (j in 0 until tamanho) {
				/* Converter os elementos da árvore para string */
				stringTree[i][j] = tree[i][j].toString()
			}
		}
		
		/* Retornar matriz */
		return stringTree
	}
}