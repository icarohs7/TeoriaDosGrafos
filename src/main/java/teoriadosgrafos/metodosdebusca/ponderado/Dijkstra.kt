package teoriadosgrafos.metodosdebusca.ponderado

import teoriadosgrafos.GrafoPonderado
import teoriadosgrafos.excecoes.ArestaNegativaException

/**
 * Classe representando o algoritmo de busca
 * de menor caminho de Dijkstra em um grafo ponderado
 *
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
abstract class Dijkstra {
	companion object {
		fun buscar(origem: Int, grafo: GrafoPonderado): ResultadoPonderado {
			/* Processo de inicialização do grafo */
			val d = grafo.distancia
			val p = grafo.precedentes
			grafo.inicializar()
			d[origem] = 0
			p[origem] = 0
			
			/* Enquanto existirem vértices abertos */
			while (grafo.existeAberto()) {
				/* u Recebe o vértice aberto mais próximo à origem */
				val u = grafo.menorDistancia()
				grafo.visitados[u] = true
				/* Relaxar todos os vizinhos abertos de u */
				grafo.getVizinhos(u).forEach { v ->
					val alternativo = d[u] + grafo.matrizDeAdjacencia[u][v]
					/* Caso haja alguma aresta negativa no grafo, lançar um erro */
					if (grafo.matrizDeAdjacencia[u][v] < 0) {
						throw ArestaNegativaException("Existe uma ou mais arestas de peso negativo no grafo")
					}
					/* Se a distância da aresta E(u,v) somada à distância
					 * d(s,u) for menor que a distância d(s,v), alterar a última */
					if (alternativo < d[v]) {
						d[v] = alternativo
						p[v] = u
					}
				}
			}
			
			/* Retornar o resultado contendo as distâncias e os precedentes */
			return ResultadoPonderado(d, p)
		}
	}
}