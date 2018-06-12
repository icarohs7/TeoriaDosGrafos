package teoriadosgrafos.extensoes

import teoriadosgrafos.Aresta
import teoriadosgrafos.Grafo
import java.io.PrintStream
import java.util.LinkedList

/**
 * Adicionar uma aresta ao grafo
 * @receiver Grafo
 * @param aresta Aresta
 */
operator fun Grafo.plusAssign(aresta: Aresta) = addAresta(aresta)

/**
 * Adicionar uma aresta ao grafo --***1..N***
 * @receiver Grafo
 * @param inOut Pair<Int, Int>
 */
operator fun Grafo.plusAssign(inOut: Pair<Int, Int>) = addAresta(inOut.first, inOut.second)


/**
 * Remover uma aresta do grafo
 * @receiver Grafo
 * @param aresta Aresta
 */
operator fun Grafo.minusAssign(aresta: Aresta) = excluirAresta(aresta)

/**
 * Remover uma aresta do grafo --***1..N***
 * @receiver Grafo
 * @param inOut Pair<Int, Int>
 */
operator fun Grafo.minusAssign(inOut: Pair<Int, Int>) = excluirAresta(inOut.first, inOut.second)

/**
 * Retorna uma fila contendo os vértices adjacentes ao vértice indicado
 * @param vertice Int: Vértice do qual se deseja obter os vizinhos
 * @return Queue<Int>: Fila contendo os vértices vizinhos ao vértice selecionado
 */
fun Grafo.logConexoes(vertice: Int): List<Int> {
	val conexoes = LinkedList<Int>()
	
	for (i in matrizDeAdjacencia.indices) {
		if (matrizDeAdjacencia[vertice][i] != Grafo.INFINITO) {
			conexoes.offer(i + 1)
		}
	}
	
	return conexoes
}

/**
 * Imprime a matriz de adjacência do grafo na printstream passada
 * @param out PrintStream: PrintStream que servirá de saída para a impressão da matriz
 */
@JvmOverloads
@Suppress("unused")
fun Grafo.printMatriz(out: PrintStream = System.out) {
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
fun Grafo.printArestas(out: PrintStream = System.out) {
	matrizDeAdjacencia.indices.forEach { u ->
		matrizDeAdjacencia[u].indices.forEach { v ->
			if (matrizDeAdjacencia[u][v] != Grafo.INFINITO) {
				/* Para cada aresta, imprimi-la e quebrar a linha */
				out.println(Aresta(u, v, matrizDeAdjacencia[u][v]))
			}
		}
	}
}