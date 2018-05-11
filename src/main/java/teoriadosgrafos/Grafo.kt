package teoriadosgrafos

import java.io.PrintStream
import java.util.LinkedList

/**
 * Classe representando um grafo e suas operações
 *
 * @author [Icaro D Temponi](https://github.com/icarohs7)
 */
abstract class Grafo
protected constructor(val matrizDeAdjacencia: Array<IntArray>, internal val direcionado: Boolean) {
	companion object {
		/**
		 * Valor representanto infinito, sua função pode variar de acordo com o grafo e algoritmo usado
		 */
		const val INFINITO = Int.MAX_VALUE / 2
	}
	
	/**
	 * Tabela de vértices que foram visitados, ou vértices abertos
	 */
	val visitados: BooleanArray = BooleanArray(matrizDeAdjacencia.size) { false }
	
	/**
	 * Property retornando a matriz de adjacência com seus elementos
	 * convertidos para string
	 */
	@Suppress("unused")
	val matrizDeAdjacenciaAsString: Array<Array<String>>
		get() = matrizDeAdjacencia.map { it.map { it.toString() }.toTypedArray() }.toTypedArray()
	
	/**
	 * Property retornando a lista de arestas contidas em um grafo
	 */
	val arestas: List<Aresta>
		get() {
			return LinkedList<Aresta>().apply {
				matrizDeAdjacencia.indices.forEach { i ->
					matrizDeAdjacencia[i].indices.forEach { j ->
						if (matrizDeAdjacencia[i][j] != INFINITO) {
							val novaAresta = Aresta(i, j, matrizDeAdjacencia[i][j])
							if (direcionado) {
								add(novaAresta)
							} else if (!arestas.contains(novaAresta)) {
								add(novaAresta)
							}
						}
					}
				}
			}
		}
	
	/**
	 * Inicializar o grafo
	 */
	fun inicializar() = visitados.indices.forEach {
		matrizDeAdjacencia[it][it] = 0
		visitados[it] = false
	}
	
	/**
	 * Retorna verdadeiro se ainda existe algum elemento aberto no grafo
	 * @return Boolean: True se existir algum elemento não visitado ou false do contrário
	 */
	fun existeAberto() = visitados.reduce { acc, b -> acc || b }
	
	/**
	 * Retorna uma lista contendo os vizinhos abertos do vértice informado
	 * @param vertice Int: O vértice do qual se deseja obter os vizinhos
	 * @param abertos Boolean: Quando verdadeiro, retorna somente os vizinhos abertos
	 * @return LinkedList<Int>: Uma lista contendo os vizinhos abertos do vértice informado
	 */
	fun getVizinhos(vertice: Int, abertos: Boolean = false): List<Int> {
		val desconexo = if (this is GrafoPonderado) Grafo.INFINITO else 0
		val vizinhos = (0 until matrizDeAdjacencia.size)
				.filter { matrizDeAdjacencia[vertice][it] != desconexo }
		return if (abertos) vizinhos.filter { !visitados[it] } else vizinhos
	}
	
	/**
	 * Imprime a matriz de adjacência do grafo na printstream passada
	 * @param out PrintStream: PrintStream que servirá de saída para a impressão da matriz
	 */
	@JvmOverloads
	@Suppress("unused")
	fun printMatriz(out: PrintStream = System.out) {
		matrizDeAdjacencia.forEach { linha ->
			linha.forEach { vertice ->
				/* Para cada aresta, imprimi-la, mesmo as não existentes */
				out.print(vertice.toString() + " ")
			}
			/* Ao fim de cada linha da matriz, quebrar a linha na impressão */
			out.println()
		}
	}
	
	/**
	 * Imprime a lista de arestas do grafo na printstream passada
	 * @param out PrintStream: PrintStream que servirá de saída para a impressão das arestas
	 */
	@JvmOverloads
	@Suppress("unused")
	fun printArestas(out: PrintStream = System.out) {
		matrizDeAdjacencia.indices.forEach { u ->
			matrizDeAdjacencia[u].indices.forEach { v ->
				if (matrizDeAdjacencia[u][v] != INFINITO) {
					/* Para cada aresta, imprimi-la e quebrar a linha */
					out.println(Aresta(u, v, matrizDeAdjacencia[u][v]))
				}
			}
		}
	}
}
