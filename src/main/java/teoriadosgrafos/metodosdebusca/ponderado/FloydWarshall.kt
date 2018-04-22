package teoriadosgrafos.metodosdebusca.ponderado

import teoriadosgrafos.Grafo
import teoriadosgrafos.GrafoPonderado

class FloydWarshall {
	companion object {
		fun buscar(grafo: GrafoPonderado): ResultadoWarshall {
			/* Quantidade de vértices */
			val tamanho = grafo.matrizDeAdjacencia.size
			/* Pesos */
			val w = grafo.matrizDeAdjacencia
			/* Criar e Inicializar as matrizes de distâncias e próximos */
			val d = Array<Array<Int>>(tamanho) { Array<Int>(tamanho) { Grafo.INFINITO } }
			val prox = Array<Array<Int>>(tamanho) { Array<Int>(tamanho) { -1 } }
			/* Definir os valores iniciais das distâncias */
			for (u in 0 until tamanho) {
				for (v in 0 until tamanho) {
					if (w[u][v] != Grafo.INFINITO) {
						d[u][v] = w[u][v]
						prox[u][v] = v
					}
					if (u == v) {
						d[u][v] = 0
					}
				}
			}
			
			/* Executar a busca */
			for (u in 0 until d.size) {
				for (s in 0 until d.size) {
					for (v in 0 until d.size) {
						if (d[s][u] + w[u][v] < d[s][v]) {
							d[s][v] = d[s][u] + w[u][v]
							prox[s][v] = u
						}
					}
				}
			}
			
			/* Retornar o resultado contendo as distâncias e os próximos */
			return ResultadoWarshall(d, prox)
		}
	}
}