package teoriadosgrafos.metodosdebusca.ponderado

import teoriadosgrafos.GrafoPonderado
import teoriadosgrafos.excecoes.CicloNegativoException

/**
 * Singleton representando o algoritmo de custo
 * mínimo de Bellman-Ford em um grafo ponderado
 *
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
object BellmanFord {
	/**
	 * Realiza a busca de menor caminho no grafo utilizando o algoritmo de Bellman-Ford
	 * @param origem Int -- O vértice de origem da busca
	 * @param grafo GrafoPonderado -- O grafo onde a busca será executada
	 * @return ResultadoPonderado -- O conjunto resultado contendo os Arrays de distâncias e Precedentes
	 */
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
			/* Fechar o vértice u */
			grafo.visitados[u] = true
			/* Relaxar todos os vizinhos abertos de u */
			grafo.getVizinhos(u).forEach { v ->
				val alternativo = d[u] + grafo.matrizDeAdjacencia[u][v]
				/* Se a distância da aresta E(u,v) somada à distância
				 * d(s,u) for menor que a distância d(s,v), alterar a última */
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