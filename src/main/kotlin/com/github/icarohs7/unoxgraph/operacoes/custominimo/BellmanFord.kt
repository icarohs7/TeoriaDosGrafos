package com.github.icarohs7.unoxgraph.operacoes.custominimo

import com.github.icarohs7.unoxgraph.GrafoPonderado
import com.github.icarohs7.unoxgraph.estatico.CicloNegativoException

object BellmanFord {
	
	@Suppress("NAME_SHADOWING")
	fun buscar(origem: Int, grafo: GrafoPonderado): ResultadoPonderado {
		/* Processo de inicialização do grafo */
		grafo.inicializar()
		val dist = DoubleArray(grafo.matrizAdjacencia.size) { grafo.matrizAdjacencia[origem][it] }.also { it[origem] = 0.0 }
		val prev = IntArray(grafo.matrizAdjacencia.size) { 0 }
		grafo.visitados[origem] = true
		/* Conjunto de vértices do grafo */
		val q = (0 until grafo.matrizAdjacencia.size)
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
				val alternativo = dist[u] + grafo.matrizAdjacencia[u][v]
				/* Se a distância da aresta E(u,v) somada à distância
				 * dist(s,u) for menor que a distância dist(s,v), alterar a última */
				if (alternativo < dist[v]) {
					dist[v] = alternativo
					prev[v] = u
				}
			}
		}
		
		/* Verificar se há ciclo de peso negativo */
		/* Para cada aresta */
		grafo.arestas.forEach { aresta ->
			val alternativo = dist[aresta.origem] + aresta.peso
			/* Se o peso da aresta somado à distância da origem for maior que a distância do destino,
			 * lançar uma exceção informando que há peso negativo no grafo */
			if (alternativo < dist[aresta.destino]) {
				throw CicloNegativoException()
			}
		}
		
		/* Retornar o resultado contendo as distâncias e os precedentes */
		return ResultadoPonderado(dist, prev)
	}
}