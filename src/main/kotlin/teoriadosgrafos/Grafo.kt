package teoriadosgrafos

abstract class Grafo
protected constructor(val matrizDeAdjacencia: Array<DoubleArray>, val direcionado: Boolean) {
	
	companion object {
		const val INFINITO = (Int.MAX_VALUE / 2).toDouble()
	}
	
	val visitados: BooleanArray = BooleanArray(matrizDeAdjacencia.size) { false }
	
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
	
	val vertices: List<Int>
		get() {
			return matrizDeAdjacencia
				.indices
				.filter {
					it in arestas.map { it.origem }
							|| it in arestas.map { it.destino }
				}
		}
	
	val ordemTopologica = OrdemTopologica()
	
	val custoMaximo = CustoMaximo()
	
	fun inicializar() = visitados.indices.forEach { vertice -> visitados[vertice] = false }
	
	
	@Suppress("unused")
	fun existeAberto() = visitados.reduce { acc, b -> acc || b }
	
	fun getVizinhos(vertice: Int, abertos: Boolean = false): List<Int> {
		val vizinhos = (0 until matrizDeAdjacencia.size)
			.filter { matrizDeAdjacencia[vertice][it] != Grafo.INFINITO }
		return if (abertos) vizinhos.filter { !visitados[it] } else vizinhos
	}
	
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
			throw ForaDoGrafoException()
		}
		
	}
	
	fun addAresta(aresta: Aresta) = addAresta(aresta.origem + 1, aresta.destino + 1, aresta.peso)
	
	@Suppress("NAME_SHADOWING")
	fun excluirAresta(origem: Int, destino: Int) {
		val origem = origem - 1
		val destino = destino - 1
		
		matrizDeAdjacencia[origem][destino] = Grafo.INFINITO
		if (!direcionado) {
			matrizDeAdjacencia[destino][origem] = Grafo.INFINITO
		}
	}
	
	fun excluirAresta(aresta: Aresta) = excluirAresta(aresta.origem + 1, aresta.destino + 1)
	
	/**
	 * Converte a matriz de adjacência para uma matriz de strings
	 */
	@Suppress("unused")
	fun toStringBidimensionalList() = matrizDeAdjacencia.map { it.map { it.toString() } }
	
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
	
	override fun equals(other: Any?): Boolean {
		return when {
			this === other -> true
			other == null -> false
			this is GrafoPonderado && other !is GrafoPonderado -> false
			this is GrafoNaoPonderado && other !is GrafoNaoPonderado -> false
			else -> {
				val m1 = this.matrizDeAdjacencia
				val m2 = (other as Grafo).matrizDeAdjacencia
				
				if (m1.size != m2.size) return false
				else if (m1[0].size != m2[0].size) return false
				
				for (i in 0 until m1.size) {
					for (j in 0 until m1[0].size) {
						if (m1[i][j] != m2[i][j]) return false
					}
				}
				
				if (this.direcionado != other.direcionado) return false
				
				true
			}
		}
	}
	
	override fun hashCode(): Int {
		return matrizDeAdjacencia.hashCode() + direcionado.hashCode()
	}
	
	/**
	 * Classe agrupando os algoritmos de ordenação topológica
	 * @constructor
	 */
	inner class OrdemTopologica {
		val grafo: Grafo = this@Grafo
	}
	
	/**
	 * Classe agrupando os algoritmos de caminho máximo
	 * @constructor
	 */
	inner class CustoMaximo {
		val grafo: Grafo = this@Grafo
	}
}
