package teoriadosgrafos.metodosdebusca.ponderado

import teoriadosgrafos.Grafo
import teoriadosgrafos.GrafoPonderado
import teoriadosgrafos.excecoes.CicloNegativoException

abstract class BellmanFord {
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
					if (alternativo < d[v]) {
						d[v] = alternativo
						p[v] = u
					}
				}
			}
			
			/* Verificar se há ciclo de peso negativo */
			/* Para cada aresta */
			grafo.arestas.forEach { aresta ->
				val alternativo = d[aresta.origem] + aresta.peso
				/* Se o peso da aresta somado à distância da origem for maior que a distância do destino,
				 * lançar uma exceção informando que há peso negativo no grafo */
				if (alternativo < d[aresta.destino]) {
					throw CicloNegativoException("O grafo contem um ciclo negativo")
				}
			}
			
			/* Retornar o resultado contendo as distâncias e os precedentes */
			return ResultadoPonderado(d, p)
		}
	}
}