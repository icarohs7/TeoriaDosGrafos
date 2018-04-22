package teoriadosgrafos.metodosdebusca.ponderado

import teoriadosgrafos.Grafo
import teoriadosgrafos.GrafoPonderado
import teoriadosgrafos.excecoes.ArestaNegativaException

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
				val u = grafo.menorDistancia(origem)
				grafo.visitados[u] = true
				/* Relaxar todos os vizinhos abertos de u */
				grafo.getVizinhos(u, Grafo.INFINITO).forEach { v ->
					val alternativo = d[u] + grafo.matrizDeAdjacencia[u][v]
					if (grafo.matrizDeAdjacencia[u][v] < 0) {
						throw ArestaNegativaException("Existe uma ou mais arestas de peso negativo no grafo")
					}
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