package teoriadosgrafos.operacoes.custominimo

import teoriadosgrafos.ArestaNegativaException
import teoriadosgrafos.GrafoPonderado

object Dijkstra {
	
	@Suppress("NAME_SHADOWING")
	fun buscar(origem: Int, grafo: GrafoPonderado): ResultadoPonderado {
		val origem = origem - 1
		/* Processo de inicialização do grafo */
		grafo.inicializar()
		val dist = DoubleArray(grafo.matrizDeAdjacencia.size) { grafo.matrizDeAdjacencia[origem][it] }
		val prev = IntArray(grafo.matrizDeAdjacencia.size) { 0 }
		grafo.visitados[origem] = true
		/* Conjunto de vértices do grafo */
		val q = (0 until grafo.matrizDeAdjacencia.size)
			.filter { it != origem }
			.toMutableList()
		
		/* Enquanto existirem vértices abertos */
		while (!q.isEmpty()) {
			/* u recebe o vértice contendo a menor distância */
			val u = q.reduce { acc, i -> if (dist[i] < dist[acc]) i else acc }
			/* Remover u de q */
			q.remove(u)
			/* Relaxar todos os vizinhos abertos de u */
			grafo.getVizinhos(u, true).forEach { v ->
				/* Caso haja alguma aresta negativa no grafo, lançar um erro */
				if (grafo.matrizDeAdjacencia[u][v] < 0) {
					throw ArestaNegativaException()
				}
				val alternativo = dist[u] + grafo.matrizDeAdjacencia[u][v]
				/* Se a distância da aresta E(u,v) somada à distância
				 * dist(s,u) for menor que a distância dist(s,v), alterar a última */
				if (alternativo < dist[v]) {
					dist[v] = alternativo
					prev[v] = u
				}
			}
		}
		/* Retornar o resultado contendo as distâncias e os precedentes */
		return ResultadoPonderado(dist, prev)
	}
}