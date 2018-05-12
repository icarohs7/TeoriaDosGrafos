package teoriadosgrafos.metodosdebusca.naoponderado

import teoriadosgrafos.GrafoNaoPonderado
import teoriadosgrafos.excecoes.CaminhoNaoEncontradoException
import java.util.LinkedList
import java.util.Stack

/**
 * Singleton representando o algoritmo de busca em largura em um grafo
 * não ponderado
 *
 * @author [Icaro D Temponi](https://github.com/icarohs7)
 */
object BuscaEmLargura {
	/**
	 * Executa uma busca em largura no grafo
	 * @param origem Int: O nó de origem da busca
	 * @param destino Int: O nó de destino da busca
	 * @param grafo GrafoNaoPonderado: O grafo no qual a busca será executada
	 * @return LinkedList<*>: A fila contendo o caminho percorrido
	 */
	@Suppress("NAME_SHADOWING")
	fun buscar(origem: Int, destino: Int, grafo: GrafoNaoPonderado): List<Int> {
		val origem = origem - 1
		val destino = destino - 1
		/* Resetar o vetor de vértices visitados */
		grafo.inicializar()
		/* Marcar o primeiro vértice visitado */
		grafo.visitados[origem] = true
		
		/* Instanciar fila e pilha usadas no processo de busca */
		val caminho = LinkedList<Int>()
		val caminhoInvertido = Stack<Int>()
		
		/* Lançar exceção caso o destino não seja encontrado */
		if (!bfs(origem, destino, grafo, caminho, caminhoInvertido)) {
			throw CaminhoNaoEncontradoException("Destino não encontrado.")
		}
		
		/* Retornar o caminho da origem para o destino */
		return caminhoInvertido
	}
	
	
	/**
	 * Procedimento recursivo responsável pela busca em largura
	 * @param origem Int: O nó a partir do qual a busca será iniciada
	 * @param destino Int: O nó que se deseja encontrar partindo da origem
	 * @param grafo GrafoNaoPonderado: O grafo no qual a busca será executada
	 * @param proximosVertices LinkedList<Int>: Fila utilizada para armazenar os próximos vértices a serem visitados
	 * @param caminhoInvertido Stack<Int>: Pilha utilizada para armazenar o caminho
	 * @return Boolean: Verdadeiro se o caminho for encontrado e Falso caso contrário
	 */
	private fun bfs(origem: Int, destino: Int, grafo: GrafoNaoPonderado, proximosVertices: LinkedList<Int>, caminhoInvertido: Stack<Int>): Boolean {
		/* Empilhar vértice visitado */
		caminhoInvertido.push(origem + 1)
		
		/* Retornar caso o destino seja alcançado */
		if (origem == destino) {
			return true
		}
		
		/* Laço utilizado para enfileirar os visinhos do vértice atual */
		grafo.matrizDeAdjacencia.indices.forEach { i ->
			if (grafo.matrizDeAdjacencia[origem][i] == 1.0) {
				if (!grafo.visitados[i]) {
					grafo.visitados[i] = true
					proximosVertices.add(i)
				}
			}
		}
		
		/* Caso a chamada recursiva no nivel inferior tenha encontrado o destino
		 * retornar repassando a informação, se a fila estiver vazia retorne falso  */
		return if (!proximosVertices.isEmpty()) {
			bfs(proximosVertices.remove(), destino, grafo, proximosVertices, caminhoInvertido)
		} else false
	}
}
