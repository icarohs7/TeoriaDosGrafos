package teoriadosgrafos

import teoriadosgrafos.excecoes.CaminhoNaoEncontradoException
import java.util.LinkedList
import java.util.Queue

/**
 * Classe representando um grafo não ponderado e suas operações
 *
 * @author [Icaro D Temponi](https://github.com/icarohs7)
 */
class GrafoNaoPonderado
@JvmOverloads
constructor
	(matrizDeAdjacencia: Array<DoubleArray>, direcionado: Boolean = false)
	: Grafo(matrizDeAdjacencia, direcionado) {
	
	init {
		//Permitir somente 0 ou 1 nos valores das células da matriz de adjacência
		for (linha in matrizDeAdjacencia) {
			for (celula in linha) {
				if (!(celula == 0.0 || celula == 1.0)) {
					throw CaminhoNaoEncontradoException("A matriz de adjacência para grafos ponderados só suporta os valores 0 ou 1")
				}
			}
		}
	}
	
	/**
	 * Propriedade fornecendo acesso aos métodos de busca
	 */
	val metodosDeBusca: MetodosDeBusca
		get() = MetodosDeBusca(this)
	
	/**
	 * Exclui uma aresta conectando 2 vértices
	 * @param origem Int: O vértice a partir de qual a aresta sairá
	 * @param destino Int: O vértice que será conectado ao vértice origem
	 */
	@Suppress("NAME_SHADOWING")
	fun excluirAresta(origem: Int, destino: Int) {
		val origem = origem - 1
		val destino = destino - 1
		
		matrizDeAdjacencia[origem][destino] = 0.0
		if (!direcionado) {
			matrizDeAdjacencia[destino][origem] = 0.0
		}
	}
	
	/**
	 * Retorna uma fila contendo os vértices adjacentes ao vértice indicado
	 * @param vertice Int: Vértice do qual se deseja obter os vizinhos
	 * @return Queue<Int>: Fila contendo os vértices vizinhos ao vértice selecionado
	 */
	fun logConexoes(vertice: Int): Queue<Int> {
		val conexoes = LinkedList<Int>()
		
		for (i in matrizDeAdjacencia.indices) {
			if (matrizDeAdjacencia[vertice][i] == 1.0) {
				conexoes.offer(i + 1)
			}
		}
		
		return conexoes
	}
	
	/**
	 * Classe fornecendo acesso aos métodos de busca
	 * @property grafo GrafoNaoPonderado
	 * @constructor
	 */
	class MetodosDeBusca(val grafo: GrafoNaoPonderado)
}
