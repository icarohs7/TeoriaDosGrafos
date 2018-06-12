package teoriadosgrafos

import teoriadosgrafos.excecoes.ForaDoGrafoException

/**
 * Classe representando um grafo e suas operações
 *
 * @author [Icaro D Temponi](https://github.com/icarohs7)
 */
abstract class Grafo
protected constructor(val matrizDeAdjacencia: Array<DoubleArray>, val direcionado: Boolean) {
	companion object {
		/**
		 * Valor representando não adjacência em grafos ponderados e não ponderados
		 */
		const val INFINITO = (Int.MAX_VALUE / 2).toDouble()
	}
	
	/**
	 * Tabela de vértices que foram visitados, ou vértices abertos
	 */
	val visitados: BooleanArray = BooleanArray(matrizDeAdjacencia.size) { false }
	
	/**
	 * Property retornando a lista de arestas contidas em um grafo
	 */
	val arestas: List<Aresta>
		get() {
			return mutableListOf<Aresta>().also { arestas ->
				matrizDeAdjacencia.indices.forEach { i ->
					matrizDeAdjacencia[0].indices.forEach { j ->
						val aresta = Aresta(i, j, matrizDeAdjacencia[i][j])
						if (aresta.peso != INFINITO && !arestas.contains(aresta)) {
							arestas.add(Aresta(i, j, matrizDeAdjacencia[i][j]))
						}
					}
				}
			}
		}
	
	/**
	 * Property retornando a lista de vértices contidos no grafo
	 */
	val vertices: List<Int>
		get() {
			return matrizDeAdjacencia
				.indices
				.filter {
					it in arestas.map { it.origem }
							|| it in arestas.map { it.destino }
				}
		}
	
	@Suppress("LeakingThis")
	val ordemTopologica = OrdemTopologica(this)
	
	@Suppress("LeakingThis")
	val custoMaximo = CustoMaximo(this)
	
	/**
	 * Inicializar o grafo - Marcando todos os vértices como não visitados
	 */
	fun inicializar() = visitados.indices.forEach {
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
		val vizinhos = (0 until matrizDeAdjacencia.size)
			.filter { matrizDeAdjacencia[vertice][it] != Grafo.INFINITO }
		return if (abertos) vizinhos.filter { !visitados[it] } else vizinhos
	}
	
	/**
	 * Adicionar uma aresta ao grafo --***1..N***
	 * @param origem  Vértice de origem da aresta
	 * @param destino Vértice de destino da aresta
	 * @param peso    Peso da aresta
	 */
	@Suppress("NAME_SHADOWING")
	fun addAresta(origem: Int, destino: Int, peso: Double = 0.0) {
		if (peso == Grafo.INFINITO) return
		
		val origem = origem - 1
		val destino = destino - 1
		try {
			matrizDeAdjacencia[origem][destino] = peso
			if (!direcionado) {
				matrizDeAdjacencia[destino][origem] = peso
			}
		} catch (e: IndexOutOfBoundsException) {
			throw ForaDoGrafoException("Tentou acessar uma aresta fora do grafo: " + e.message)
		}
		
	}
	
	/**
	 * Adiciona uma aresta ao grafo
	 * @param aresta Aresta
	 */
	fun addAresta(aresta: Aresta) = addAresta(aresta.origem + 1, aresta.destino + 1, aresta.peso)
	
	/**
	 * Exclui uma aresta conectando 2 vértices --***1..N***
	 * @param origem Int: O vértice a partir de qual a aresta sairá
	 * @param destino Int: O vértice que será conectado ao vértice origem
	 */
	@Suppress("NAME_SHADOWING")
	fun excluirAresta(origem: Int, destino: Int) {
		val origem = origem - 1
		val destino = destino - 1
		
		matrizDeAdjacencia[origem][destino] = Grafo.INFINITO
		if (!direcionado) {
			matrizDeAdjacencia[destino][origem] = Grafo.INFINITO
		}
	}
	
	/**
	 * [excluirAresta]
	 * @param aresta Aresta
	 */
	fun excluirAresta(aresta: Aresta) = excluirAresta(aresta.origem + 1, aresta.destino + 1)
	
	/**
	 * Converte a matriz de adjacência para uma matriz de strings
	 */
	@Suppress("unused")
	fun toStringBidimensionalList() = matrizDeAdjacencia.map { it.map { it.toString() } }
	
	/**
	 * To string de um grafo
	 * @return String
	 */
	override fun toString(): String {
		return buildString {
			matrizDeAdjacencia.forEach { linha ->
				linha.forEach { elem ->
					append(if (elem == Grafo.INFINITO) "INF" else if (this@Grafo is GrafoNaoPonderado) "1" else "$elem")
					append("\t\t")
				}
				append("\n\n")
			}
			arestas
				.map { Aresta(it.origem + 1, it.destino + 1, it.peso) }
				.withIndex()
				.forEach { (index, aresta) ->
					if (index % 3 == 0) append("\n")
					append("$aresta\t\t")
				}
		}
	}
	
	/**
	 * Classe agrupando os algoritmos de ordenação topológica
	 * @property grafo Grafo
	 * @constructor
	 */
	class OrdemTopologica(val grafo: Grafo)
	
	/**
	 * Classe agrupando os algoritmos de caminho máximo
	 * @property grafo Grafo
	 * @constructor
	 */
	class CustoMaximo(val grafo: Grafo)
}
