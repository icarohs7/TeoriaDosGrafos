package edu.grafos.metodosdebusca.ponderado

import edu.grafos.Grafo
import edu.grafos.GrafoPonderado

class FloydWarshall {
	companion object {
		fun buscar(grafo: GrafoPonderado): ResultadoWarshall {
			/* Criar a matriz de distâncias */
			val d = Array(grafo.matrizDeAdjacencia.size) { IntArray(grafo.matrizDeAdjacencia.size) }
			val prox = Array(grafo.matrizDeAdjacencia.size) { IntArray(grafo.matrizDeAdjacencia.size) }
			/* Inicializar as matrizes */
			for (u in 0 until d.size) {
				for (v in 0 until d.size) {
					d[u][v] = Grafo.INFINITO
					prox[u][v] = -1
				}
			}
			/* Definir os valores iniciais das distâncias */
			grafo.arestas.forEach { aresta ->
				val u = aresta.origem
				val v = aresta.destino
				d[u][v] = aresta.peso
				prox[u][v] = v
			}
			for (i in 0 until d.size) {
				d[i][i] = 0
			}
			
			/* Executar a busca */
			for (k in 0 until d.size) {
				for (u in 0 until d.size) {
					for (v in 0 until d.size) {
						if (d[u][v] > d[u][k] + d[k][v]) {
							d[u][v] = d[u][k] + d[k][v]
							prox[u][v] = prox[u][k]
						}
					}
				}
			}
			
			return ResultadoWarshall(d, prox)
		}
	}
}