package com.github.icarohs7.unoxgraph.operacoes.custominimo

import com.github.icarohs7.unoxgraph.CicloNegativoException
import com.github.icarohs7.unoxgraph.Grafo
import com.github.icarohs7.unoxgraph.GrafoPonderado

object FloydWarshall {
	
	fun buscar(grafo: GrafoPonderado): ResultadoWarshall {
		/* Inicializar o grafo */
		grafo.inicializar()
		/* Quantidade de vértices */
		val tamanho = grafo.matrizDeAdjacencia.size
		/* Pesos */
		val w = grafo.matrizDeAdjacencia
		/* Criar e Inicializar as matrizes de distâncias e próximos */
		val d = Array(tamanho) { DoubleArray(tamanho) { Grafo.INFINITO } }
		val prox = Array(tamanho) { Array(tamanho) { -1 } }
		/* Processo de inicialização */
		for (u in 0 until tamanho) {
			for (v in 0 until tamanho) {
				/* Para cada aresta (u,v) definir
				 * dist[u][v] para w[u][v] */
				if (w[u][v] != Grafo.INFINITO) {
					d[u][v] = w[u][v]
					prox[u][v] = v
				}
			}
		}
		
		/* Executar a busca */
		/* K representa a definição da distância mínima partindo de cada vértice para todos os outros */
		for (k in 0 until tamanho) {
			for (i in 0 until tamanho) {
				for (j in 0 until tamanho) {
					/* Para cada distância de I para J, se a mesma for
					 * maior que a distância de I para K somada à distância
					 * de K para J, tornar a distância de I para J para a distância
					 * de I para K somada à distância de K para J, sendo K o vértice
					 * entre eles e fazendo o próximo de I para J ser o próximo de
					 * I para K*/
					if (d[i][j] > d[i][k] + d[k][j]) {
						d[i][j] = d[i][k] + d[k][j]
						prox[i][j] = prox[i][k]
					}
				}
			}
		}
		
		/* Verificação para ciclos negativos no grafo */
		for (i in 0 until tamanho) {
			if (d[i][i] < 0) {
				throw CicloNegativoException()
			}
		}
		
		/* Retornar o resultado contendo as distâncias e os próximos */
		return ResultadoWarshall(d, prox)
	}
}