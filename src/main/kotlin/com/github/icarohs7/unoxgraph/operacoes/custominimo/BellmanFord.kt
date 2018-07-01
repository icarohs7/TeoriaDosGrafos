package com.github.icarohs7.unoxgraph.operacoes.custominimo

import com.github.icarohs7.unoxgraph.estatico.CicloNegativoException
import com.github.icarohs7.unoxgraph.estatico.TipoDeGrafoIncorretoException
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxkcommons.extensoes.preenchendoArrayIntDeTamanho

/**
 * Função de extensão injetando o cálculo de custo mínimo utilizando
 * o algoritmo de Bellman Ford na classe grafo
 */
fun Grafo.custoMinimoBellmanFord(origem: Int): ResultadoPonderado {
	return BellmanFord.buscar(origem, this as? Grafo.Ponderado ?: throw TipoDeGrafoIncorretoException())
}

private object BellmanFord {
	
	@Suppress("NAME_SHADOWING")
	fun buscar(origem: Int, grafo: Grafo.Ponderado): ResultadoPonderado {
		/* Processo de inicialização do grafo */
		grafo.desmarcarTodosOsVertices()
		val dist = DoubleArray(grafo.matrizAdjacencia.size) { grafo.matrizAdjacencia[origem][it] }.also { it[origem] = 0.0 }
		val prev = 0 preenchendoArrayIntDeTamanho grafo.tamanho
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